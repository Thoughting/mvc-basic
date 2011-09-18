<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>Mini-Web 帐号管理</title>
	<%@ include file="/common/meta.jsp" %>
	<link href="${ctx}/css/yui.css" type="text/css" rel="stylesheet"/>
	<link href="${ctx}/css/style.css" type="text/css" rel="stylesheet"/>
	<link href="${ctx}/js/validate/jquery.validate.css" type="text/css" rel="stylesheet"/>
	<script src="${ctx}/js/jquery-1.4.2.min.js" type="text/javascript"></script>
	<script src="${ctx}/js/validate/jquery.validate.js" type="text/javascript"></script>
	<script src="${ctx}/js/validate/messages_cn.js" type="text/javascript"></script>
	<script>
		$(document).ready(function() {
			//聚焦第一个输入框
			$("#loginName").focus();
			//为inputForm注册validate函数
			$("#inputForm").validate({
				rules: {
					loginName: {
						required: true,
						remote: "user!checkLoginName.action?oldLoginName=" + encodeURIComponent('${loginName}')
					},
					name: "required",
					password: {
						required: true,
						minlength:3
					},
					passwordConfirm: {
						equalTo:"#password"
					},
					email:"email",
					checkedRoleIds:"required"
				},
				messages: {
					loginName: {
						remote: "用户登录名已存在"
					},
					passwordConfirm: {
						equalTo: "输入与上面相同的密码"
					}
				}
			});
		});
	</script>
	<style type="text/css">
* {
	font-size:12px;
	margin:0;
	padding:0; 
} 
fieldset {
	padding:10px;
	margin:10px;
	width:270px;
	color:#333; 
	border:#06c dashed 1px;
} 
legend {
	color:#06c;
	font-weight:800; 
	background:#fff;
	border:#b6b6b6 solid 1px;
	padding:3px 6px;
} 
ul {
	list-style-type: none;
	margin:8px 0 4px 0;
} 
li {
	margin-top:4px;
}
</style>
</head>

<body>
<div id="doc3">
<%@ include file="/common/header.jsp" %>
<div id="bd">
	<div id="yui-main">
	<div class="yui-b">
	<h2><c:choose><c:when test="${user.id == null}">创建</c:when><c:otherwise>修改</c:otherwise></c:choose>用户</h2>
	<c:choose>
		<c:when test="${user.id == null}">
			<c:url var="actionUrl" value="account/user"/> 
			<c:url var="parameterMethod" value="post"/>
		</c:when>
		<c:otherwise>
		 	<c:url var="actionUrl" value="account/user/${user.id}"/> 
		 	<c:url var="parameterMethod" value="put"/>
		</c:otherwise>
	</c:choose>
	<form:form method="${parameterMethod}" commandName="user" action="${ctx}/${actionUrl}">
		<input type="hidden" name="id" value="${user.id}"/>
		<fieldset>  
                        <legend><fmt:message key="userFields"/></legend>
                        <ul>
                        <li>  
                            <form:label  for="loginName" path="loginName" cssErrorClass="error">  
                                <fmt:message key="loginName"/>  
                            </form:label>  
                            <form:input path="loginName" />  
                            <form:errors path="loginName" />  
                        </li>
                        <li>  
                            <form:label  for="name" path="name" cssErrorClass="error">  
                                <fmt:message key="userName"/>  
                            </form:label>  
                            <form:input path="name" />  
                            <form:errors path="name" />  
                        </li>
                        <li>  
                            <form:label  for="password" path="password" cssErrorClass="error">  
                                <fmt:message key="password"/>  
                            </form:label>  
                            <form:password path="password"/>  
                            <form:errors path="password" />  
                        </li>
                        <li>
                            <form:label  for="passwordConfirm" path="password" cssErrorClass="error">  
                                <fmt:message key="passwordConfirm"/>  
                            </form:label>  
                            <input type="password" id="passwordConfirm" name="passwordConfirm"   value=""/>
                            <!--<form:password path="password" id="passwordConfirm"/>-->  
                            <form:errors path="password" />  
                        </li>
                        <li>  
                            <form:label  for="email" path="email" cssErrorClass="error">  
                                <fmt:message key="email"/>  
                            </form:label>  
                            <form:input path="email" />  
                            <form:errors path="email" />  
                        </li>
                        <li>  
                            <form:label  for="roleList" path="roleList" cssErrorClass="error">  
                                <fmt:message key="roleName"/>  
                            </form:label>  
                             <form:checkboxes itemValue="id" itemLabel="name" path="roleList" items="${allRoleList}" delimiter="<br/>" onclick="yourOnClickMethodIfYouNeed(this);"/>
                            <form:errors path="roleList" />  
                        </li>
                        <li>
                            <security:authorize ifAnyGranted="ROLE_修改用户">
						<input class="button" type="submit" value="<fmt:message key="submit"/>"/>&nbsp;
					</security:authorize>
					<input class="button" type="button" value="<fmt:message key="return"/>" onclick="history.back()"/>  
                        </li>
                        </ul>
                  </fieldset>       
                <!--         
		<table class="noborder">
			<tr>
				<td>登录名:</td>
				<td><input type="text" name="loginName" size="40" id="loginName" value="${user.loginName}"/></td>
			</tr>
			<tr>
				<td>用户名:</td>
				<td><input type="text" id="name" name="name" size="40" value="${user.name}"/></td>
			</tr>
			<tr>
				<td>密码:</td>
				<td><input type="password" id="password" name="password" size="40" value="${user.password}"/></td>
			</tr>
			<tr>
				<td>确认密码:</td>
				<td><input type="password" id="passwordConfirm" name="passwordConfirm" size="40" value="${user.password}"/>
				</td>
			</tr>
			<tr>
				<td>邮箱:</td>
				<td><input type="text" id="email" name="email" size="40" value="${user.email}"/></td>
			</tr>
			<tr>
				<td>角色:</td>
				<td>
					<form:checkboxes itemValue="id" itemLabel="name" path="roleList" items="${allRoleList}" delimiter="<br/>" onclick="yourOnClickMethodIfYouNeed(this);"/>
					
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<security:authorize ifAnyGranted="ROLE_修改用户">
						<input class="button" type="submit" value="提交"/>&nbsp;
					</security:authorize>
					<input class="button" type="button" value="返回" onclick="history.back()"/>
				</td>
			</tr>
		</table>
		 -->
	</form:form>
	</div>
	</div>
</div>
<%@ include file="/common/footer.jsp" %>
</div>
</body>
</html>
