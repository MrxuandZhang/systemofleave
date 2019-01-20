<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="http://www.springframework.org/tags/form" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
    out.println("<base href=\"" + basePath + "\">");
%>
<html>
<body onload="javascript:document.getElementById('#uname').focus();">
<%--<s:form action="user/login" method="post" modelAttribute="u">--%>
    <%--<table cellspacing="0" border="1" align="center">--%>
        <%--<tr>--%>
            <%--<td>用户名${loginMsg}</td>--%>
            <%--<td><s:input path="uname" placeHolder="请输入用户名"></s:input></td>--%>
        <%--</tr>--%>
        <%--<tr>--%>
            <%--<td>密码</td>--%>
            <%--<td><s:password path="upwd" placeHolder="请输入密码"></s:password></td>--%>
        <%--</tr>--%>
        <%--<tr>--%>
            <%--<td colspan="2">--%>
                <%--<button type="submit">提交</button>--%>
                <%--<button type="reset">重置</button>--%>
            <%--</td>--%>
        <%--</tr>--%>
    <%--</table>--%>
    <%--<table cellspacing="0" border="1" align="center">--%>
      <%--<c:forEach items="${users.list}" var="vars">--%>
          <%--<tr>--%>
              <%--<td>${vars.id}</td>--%>
              <%--<td>${vars.upwd}</td>--%>
          <%--</tr>--%>
      <%--</c:forEach>--%>
    <%--</table>--%>
<%--</s:form>--%>
</body>
<head>
    <title>用户登录</title>
</head>
</html>
