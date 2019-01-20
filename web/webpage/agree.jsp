<%@ page import="com.hongzan.util.CommonUtil" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="http://www.springframework.org/tags/form" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
    out.println("<base href=\"" + basePath + "\">");
%>
<%if (CommonUtil.isNull(request.getSession().getAttribute("user"))) response.sendRedirect("../user/index"); request.getSession().setAttribute("loginMeg","请登录!");%>
<html>
<head>
    <title>同意</title>
</head>
<link rel="stylesheet" href="/Css/layui.css">
<script src="/Js/layui.js"></script>
<body>
<br>
<form class="layui-form" action="audit/updateMeg" method="post">
    <div class="layui-form-item">
        <label class="layui-form-label">建议:</label>
        <div class="layui-input-block">
            <input type="text"  required  lay-verify="required" name="suggest" autocomplete="off" class="layui-input">
        </div>
    </div>
</form>

<script>
    //Demo
    layui.use('form', function(){
        var form = layui.form;
        //监听提交
        form.on('submit(formDemo)', function(data){
            return false;
        });
    });
</script>
</body>
<script src="/Js/jquery-3.3.1.js"></script>
</html>
