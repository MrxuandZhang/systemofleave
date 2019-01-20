<%@ page import="com.hongzan.util.CommonUtil" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
    out.println("<base href=\"" + basePath + "\">");
%>
<%if (CommonUtil.isNull(request.getSession().getAttribute("user"))){ response.sendRedirect("../user/index"); request.getSession().setAttribute("loginMeg","请登录!");}%>
<html>
<head>
    <title>获取个人信息</title>
</head>
<link rel="stylesheet" href="/Css/layui.css">
<script src="/Js/layui.js"></script>
<script src="/Js/jquery-3.3.1.js"></script>
<body>
<form action="leave/getPersonInfo" method="post">
  <input type="hidden" id="currentPage" name="currentPage" value="1"/>
    <table class="layui-table" style="width: 85%;">
        <colgroup>
            <col width="120">
            <col width="110">
            <col>
        </colgroup>
        <thead>
        <tr>
            <th>请假单号</th>
            <th>开始时间</th>
            <th>结束时间</th>
            <th>类型</th>
            <th>附件</th>
            <th>审核历史</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${personList}" var="person">
            <tr>
                <td>${person.leaNo}</td>
                <td style="width: 15%;">${person.begin} </td>
                <td>${person.end} </td>
                <td>${person.reason}</td>
                <c:if test="${person.list!=null&&person.list.size()>0}">
                    <td>
                        <c:forEach items="${person.list}" var="up" varStatus="vars">
                            <a href="upload/${up.upath}" download="${up.upath}" >附件${vars.count}</a>
                        </c:forEach>
                    </td>
                </c:if>
                <c:if test="${person.list==null||person.list.size()==0}">
                    <td style="width: 15%;">无附件</td>
                </c:if>
                <td style="width: 15%;">
                    <a href="/audit/getLeaveHistory/${person.leaNo}" class="layui-btn">查询</a>
                </td>
                <td>
                    <c:if test="${person.stateCode==3}">
                        <a href="/leave/get/${person.id}" class="layui-btn">修改</a>
                    </c:if>
                    <c:if test="${person.stateCode!=3}">
                        暂时无法修改
                    </c:if>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</form>
<div id="test1" align="center"></div>
</body>
<script src="/Js/layui.all.js"></script>
<script>
    $(document).ready(function () {
        layui.use('laypage', function(){
            var laypage = layui.laypage;

            //执行一个laypage实例
            laypage.render({
                elem: 'test1' //注意，这里的 test1 是 ID，不用加 # 号
                ,count: ${personList.size()} //数据总数，从服务端得到
                ,limit:5
                ,jump: function(obj, first){
                   $("#currentPage").val(obj.curr); //得到当前页，以便向服务端请求对应页的数据。
                    //首次不执行
                    if(!first){
                       document.forms[0].submit();
                    }
                }
            });
        });
    })
</script>
</html>

