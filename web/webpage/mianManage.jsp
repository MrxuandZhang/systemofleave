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
    <title>请假审核系统</title>
    <link rel="stylesheet" href="/Css/layui.css">
</head>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
    <div class="layui-header">
        <div class="layui-logo">欢迎您${user.uname}</div>
        <ul class="layui-nav layui-layout-left">
        </ul>
        <ul class="layui-nav layui-layout-right">
            <li class="layui-nav-item">
                <a href="javascript:;">
                    <img src="/dream.png" class="layui-nav-img">
                    ${user.name}
                </a>
                <dl class="layui-nav-child">
                </dl>
            </li>
            <li class="layui-nav-item"><a href="/user/index">退了</a></li>
        </ul>
    </div>

    <div class="layui-side layui-bg-black">
        <div class="layui-side-scroll">
            <ul class="layui-nav layui-nav-tree"  lay-filter="test">
                <li class="layui-nav-item">
                    <a class="" href="javascript:;">个人信息</a>
                    <dl class="layui-nav-child">
                        <dd><a href='/leave/toLeave' target="main">请假</a></dd>
                        <dd><a href='/leave/getPersonInfo' target="main">请假信息查看</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item">
                    <a href="javascript:;">审核信息管理</a>
                    <dl class="layui-nav-child">
                        <dd><a href='/audit/getHistory' target="main" id="entry">请假审批历史</a></dd>
                        <dd><a href='/audit/getNoAuditInfo' target="main" id="entry2">查询未审核单</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item">
                    <a href="javascript:;">系统管理</a>
                    <dl class="layui-nav-child">
                        <dd><a href="/user/index" target="_top" onclick="clearSession()">退出系统</a></dd>
                    </dl>
                </li>
            </ul>
        </div>
    </div>
    <iframe height="600px" width="100%" name="main" style="margin-left:200px;" src="leave/toLeave" scrolling="no" id="id_frm">
    </iframe>
</div>
<script type="text/javascript" src="/Js/jquery.min.js"></script>
<script type="text/javascript" src="/Js/layui.js"></script>
<script type="text/javascript" src="/Js/index.js"></script>
<script>
    /* 若权限等级不够那么将禁用对应等级功能 */
    function cantNotClick() {
        $('#entry').attr("disabled",true);
        $("#entry").css("pointer-events","none");
        $('#entry2').attr("disabled",true);
        $("#entry2").css("pointer-events","none");
    }
    window.onload= function (ev) {
        //用于获取对应登陆者的等级信息
        var _level=${user.level}
        if(_level==0){
            cantNotClick();
        }
        setIframeHeight(document.getElementById('id_frm'));
    }
    /* 删除session会话信息 */
    function clearSession() {
        $.sessionStorage.clear();
    }
    function setIframeHeight(iframe) {
        if (iframe) {
            var iframeWin = iframe.contentWindow || iframe.contentDocument.parentWindow;
            if (iframeWin.document.body) {
                iframe.height = iframeWin.document.documentElement.scrollHeight || iframeWin.document.body.scrollHeight;
            }
        }
    };
</script>
</body>
</html>
