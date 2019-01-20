<%@ page import="com.hongzan.util.CommonUtil" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="http://www.springframework.org/tags/form" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
    out.println("<base href=\"" + basePath + "\">");
%>
<%if (CommonUtil.isNull(request.getSession().getAttribute("user"))) {response.sendRedirect("../user/index"); request.getSession().setAttribute("loginMeg","请登录!");}%>
<html>
<head>
    <title>查询流转历史</title>
</head>
<link rel="stylesheet" href="/Css/layui.css">
<script src="/Js/jquery-3.3.1.js"></script>
<script src="/Js/layui.js"></script>
<body>
<form action="audit/getHistory" method="post">
    <input type="hidden" id="currentPage" name="currentPage" value="1"/>
<table class="layui-table" lay-even lay-skin="nob" >
    <colgroup>
        <col width="10%">
        <col width="10%">
        <col>
    </colgroup>
    <thead>
    <tr>
        <th>审核人</th>
        <th>审核建议</th>
        <th>申请人</th>
        <th>申请原因</th>
        <th>审核时间</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${history}" var="his">
        <tr>
            <td>${his.level.level}级主管:${his.aname}</td>
            <td>${his.suggest}</td>
            <td>${his.leaNo.person.uname}</td>
            <td>${his.leaNo.reasonContent.length()>5?his.leaNo.reasonContent.substring(0,5).concat("..."):his.leaNo.reasonContent}&nbsp;&nbsp;${"类型:".concat(his.leaNo.reason)}</td>
            <td>${his.atime}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</form>
<div align="center" id="test1"></div>
</body>
<script src="/Js/layui.all.js"></script>
<script>
    $(document).ready(function () {
        layui.use('laypage', function(){
            var laypage = layui.laypage;

            //执行一个laypage实例
            laypage.render({
                elem: 'test1'
                ,count: ${history.size()} //数据总数，从服务端得到
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
