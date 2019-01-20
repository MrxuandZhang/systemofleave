<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="http://www.springframework.org/tags/form" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    out.println("<base href=\""+basePath+"\">");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户登录</title>
<link href="/Css/login.css" rel="stylesheet" type="text/css" />
    <link rel="stylesheet" href="/Css/layui.css"/>
    <script src="/Js/layui.js"></script>
</head>
<body onload="javascript:$('#i_no').focus();">
<div class="login_box">
      <div class="login_l_img"><img src="/login-img.png" /></div>
      <div class="login">
          <div class="login_logo"><a href="/user/index"><img src="/login_logo.png" title="登录" /></a></div>
          <div class="login_name">
               <p>请假系统 </p>
          </div>
          <s:form action="/user/login"  method="post" onsubmit="return chec()" modelAttribute="u">
              <s:input path="uno" id="i_no" placeholder="用户编号"  />
              <s:password path="upwd"  id="i_password" placeholder="用户密码" />
             <font color="red">${loginMsg}&nbsp;&nbsp;&nbsp;${loginMeg}</font>
              <input value="登录" style="width:100%;" type="submit">
              <br/>
              <br/>
          </s:form>
      </div>
      <div class="copyright">某某有限公司 版权所有©2018-2020 技术支持电话：000-00000000</div>
</div>
</body>
<script src="/Js/jquery-3.3.1.js"></script>
<script src="/Js/layui.all.js"></script>
<script type="text/javascript">
function chec(){
	   var reg=/^\d\w{6,}$/
	   var _no=$("#i_no").val();
	   var _pwd=$("#i_password").val();
	   if(!reg.test(_no)||_no==''||_no.length<7){
           layer.open({
               title: '温馨提示'
               ,content: '您的编号不正确!'
           });
		   return false;
	   }else if(_pwd==''||_pwd.length<6||_pwd.length>8){
           layer.open({
               title: '温馨提示'
               ,content: '密码不允许为空且大等于6小等于8'
           });
		   return false;
	   }else{
		   return true;
	   }
}
</script>
</html>
