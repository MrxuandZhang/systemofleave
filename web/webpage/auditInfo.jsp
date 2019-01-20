<%@ page import="com.hongzan.util.CommonUtil" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="http://www.springframework.org/tags/form" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
    out.println("<base href=\"" + basePath + "\">");
%>
<%if (CommonUtil.isNull(request.getSession().getAttribute("user"))){ response.sendRedirect("../user/index"); request.getSession().setAttribute("loginMeg","请登录!");}%>

<html>
<head>
    <title>审核</title>
</head>
<script src="/Js/jquery-3.3.1.js"></script>
<link rel="stylesheet" href="/Css/layui.css">
<script src="/Js/layui.js"></script>
<body>
<form action="audit/getNoAuditInfo" method="post">
    <input type="hidden" id="currentPage" name="currentPage" value="1"/>
    <table class="layui-table" lay-even lay-skin="nob" >
        <colgroup>
            <col width="10%">
            <col width="10%">
            <col>
        </colgroup>
        <thead>
        <tr>
            <th>申请人</th>
            <th>类型</th>
            <th>申请原因</th>
            <th>申请时间</th>
            <th>操作类型</th>
        </tr>
        </thead>
        <tbody>

        <c:forEach items="${NoauditInfo}" var="audit">
            <tr>
                <td>${audit.level.uname}</td>
                <td>${audit.leaNo.reason}</td>
                <td>${audit.leaNo.reasonContent.length()>5?audit.leaNo.reasonContent.substring(0,5).concat('...'):audit.leaNo.reasonContent}</td>
                <td>${audit.leaNo.begin}</td>
                <td>
                    <c:if test="${audit.statecode==2&&audit.level.uname!=user.uname}">
                        <a href="/audit/updateCode/${audit.leaNo.leaNo}"  class="layui-btn" onclick=" return confirm('是否驳回?')">驳回</a>
                        <a href="/audit/updateMeg?leaNo=${audit.leaNo.leaNo}" onclick=" return agree(this)" class="layui-btn" id="agreeBtn">同意</a>
                    </c:if>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</form>
<div align="center" id="pageTable"></div>
<%--</iframe>--%>
</body>
<script src="/Js/layui.all.js"></script>
<script type="text/javascript">
    function agree(a) {
        var uri = a.href;
        //例子2
        layer.prompt({
            formType: 2,
            title: '请输入建议',
            area: ['300px', '50px'] //自定义文本域宽高
        }, function(value, index, elem){
            /*获取建议信息，追加参数，使用a标签模拟跳转*/
            uri+="&suggest="+value;
            var c = document.createElement('a')
            c.href = uri;
            c.style.display='none'
            document.body.appendChild(c)
            c.click()
            /*关闭窗体*/
            layer.close(index);
            $(a).css("display","none");
            $(a).prop("disabled",true);
        });
        return false;
    }
    $(document).ready(function () {
        layui.use('laypage', function(){
            var laypage = layui.laypage;

            //执行一个laypage实例
            laypage.render({
                elem: 'pageTable' //注意，这里的 test1 是 ID，不用加 # 号
                ,count: ${NoauditInfo.size()} //数据总数，从服务端得到
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
