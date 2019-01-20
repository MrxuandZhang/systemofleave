<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="http://www.springframework.org/tags/form" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
    out.println("<base href=\"" + basePath + "\">");
%>
<html>
<script src="/Js/jquery-3.3.1.js"></script>
<link rel="stylesheet" href="/Css/layui.css" />
<script src="/Js/layui.js"></script>
<head>
    <title>上传附件</title>
</head>
<body>
<form  method="post" action="leave/uploadLeave"  enctype="multipart/form-data">
       <div>
           <div style="text-align:center;">    
           txt数据：<input type="file"  name="file" size="40" />
           <button type="submit" class="layui-btn layui-btn-normal">提交</button>
          </div>
           ${uploadMsg}
      </div>
</form>
</body>
</html>
