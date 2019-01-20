<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="http://www.springframework.org/tags/form" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
    out.println("<base href=\"" + basePath + "\">");
%>
<html>
<head>
    <title>获取审批历史</title>
    <link rel="stylesheet" href="/Css/layui.css">
    <script src="/Js/layui.js"></script>
</head>
<body>
<table class="layui-table">
    <colgroup>
        <col width="120">
        <col width="110">
        <col>
    </colgroup>
    <thead>
    <tr>
        <th>审核人</th>
        <th>审核状态</th>
        <th>申请人</th>
        <th>申请原因</th>
        <th>审核建议</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${leaveHistory}" var="his">
        <tr>
            <td>${his.level.level}级主管:${his.aname}</td>
            <td>
                <c:if test="${his.statecode==0}">审核通过</c:if>
                <c:if test="${his.statecode==1}">审核被驳回</c:if>
                <c:if test="${his.statecode==2}">正在审核</c:if>
            </td>
            <td>${his.leaNo.person.uname}</td>
            <td>${his.leaNo.reasonContent.length()>5?his.leaNo.reasonContent.substring(0,5).concat("..."):his.leaNo.reasonContent}&nbsp;&nbsp;${"类型:".concat(his.leaNo.reason)}</td>
            <td>${his.suggest}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
<script src="/Js/layui.all.js"></script>
</html>
