<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ page import="org.springframework.security.web.authentication.AbstractProcessingFilter" %>
<%@ page import="org.springframework.security.web.authentication.AuthenticationProcessingFilter" %>
<%@ page import="org.springframework.security.core.AuthenticationException" %>
<%
response.setHeader("Pragma","no-cache"); 
response.setHeader("Cache-Control","no-cache"); 
response.setDateHeader("Expires", 0);
%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>企业信息管理系统_用户登录</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<META HTTP-EQUIV="Pragma" CONTENT="no-cache">
<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache">
<META HTTP-EQUIV="Expires" CONTENT="0">

<style type="text/css">
<!--
/*=====搜索条=====*/
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
	/*background-color: #016aa9;*/
	overflow:hidden;
}
.STYLE1 {
	color: #000000;
	font-size: 12px;
}
/*=====搜索条结束=====*/
-->
</style>
<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>  
        <script type="text/javascript">    
            $(document).ready(function(){    
                // username 获取光标 $("#username").focus();    
            });    
            function checkForm(){    
                 var name = $("#username").val();    
                 if(name.length<=0){     
                    $("#msgName").html("the username is null!");   
                     return false;    
                 }else{     
                    $("#msgName").html("");   
                 }    
                 var pass = $("#password").val();  ; 
                 if(pass.length<=0){
                    $("#msgPass").html("the password is null!");    
                    return false;    
                 }else{
                    $("#msgPass").html("");    
                 }    
                 return true;    
                 }   
         </script>  

</head>

<body>
<form id="loginForm" action="${ctx}/j_spring_security_check" method="post" style="margin-top:1em">
<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td><table width="962" border="0" align="center" cellpadding="0" cellspacing="0">
      <tr>
        <td height="235" background="images/login_03.gif_">&nbsp;</td>
      </tr>
      <tr>
        <td height="53"><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td width="344" height="53" background="images/login_05.gif_">&nbsp;</td>
            <td width="306" background="images/login_06.gif_"><table width="100%" border="0" cellspacing="0" cellpadding="0">
            	<tr>  
                    <td colspan="3">
                     <c:if test="${not empty param.authfailed}">
                        <span id="msg" style="color: red">登录失败：
<c:set var="authException" value="${sessionScope['SPRING_SECURITY_LAST_EXCEPTION']}"/>
<c:choose>
<c:when test="${authException.class.name eq 'org.springframework.security.authentication.BadCredentialsException'}">
<fmt:message key="AbstractUserDetailsAuthenticationProvider.badCredentials"/>
</c:when>
<c:when test="${authException.class.name eq 'org.springframework.security.authentication.AuthenticationServiceException'}">
<fmt:message key="JcaptchaAuthenticationFilter.invalidCode"/>
</c:when>
<c:when test="${authException.class.name eq 'org.springframework.security.LockedException'}">
<fmt:message key="error.login.locked"/>
</c:when>
<c:when test="${authException.class.name eq 'org.springframework.security.DisabledException'}">
<fmt:message key="error.login.disabled">
<fmt:param>
<span id="seconds"></span>
</fmt:param>
</fmt:message>
</c:when>
<c:when test="${authException.class.name eq 'org.springframework.security.AccountExpiredException'}">
<fmt:message key="error.login.expired"/>
</c:when>
<c:otherwise>
<c:out value="${sessionScope.SPRING_SECURITY_LAST_EXCEPTION.message}"/>
</c:otherwise>
</c:choose>
                        </span>
                     </c:if>
                    </td>  
              </tr>
              <tr>
                <td width="20%" height="32"><div align="right"><span class="STYLE1">用&nbsp;&nbsp;户</span></div></td>
                <td width="57%"><div align="center">
                  <input type="text" id='j_username' name='j_username' class="required" style="width:150px; height:28px; border:solid 1px #7dbad7; font-size:14px; "/>
                </div></td>
                <td width="23%" height="25">&nbsp;</td>
              </tr>
              <tr>
                <td height="32"><div align="right"><span class="STYLE1">密&nbsp;&nbsp;码</span></div></td>
                <td><div align="center">
                  <input type='password' id='j_password' name='j_password' class="required" style="width:150px; height:28px; border:solid 1px #7dbad7; font-size:14px;"/>
                </div></td>
                <td>&nbsp;</td>
              </tr>
              <tr>
              <td height="32"><div align="right"><span class="STYLE1">验证码</span></div></td>
              <td><div align="center">
              <input type='text' name='j_captcha' class="required" style="width:150px; height:28px; border:solid 1px #7dbad7; font-size:14px;">
              </div></td>
              <td><img height="30" id="checkcode" alt="看不清，点击换一张" src="CheckCode.svl" border="1px" onclick="this.src='CheckCode.svl?date=' + new Date().getTime()"></td>
              </tr>
              <tr>
              <td height="32" colspan="3">
              	<div align="center">
                	<input type="image" src="images/dl.gif" width="49" height="18" border="0" >&nbsp;&nbsp;<input type="image" src="images/dl.gif" width="49" height="18" border="0" >
                </div>
              </td>             
              </tr>
            </table></td>
            <td width="312" background="images/login_07.gif_">&nbsp;</td>
          </tr>
        </table></td>
      </tr>
      <tr>
        <td height="213" background="images/login_08.gif_">&nbsp;</td>
      </tr>
    </table></td>
  </tr>
</table>
</form>
</body>
</html>
