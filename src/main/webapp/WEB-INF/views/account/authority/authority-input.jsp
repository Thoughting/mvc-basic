<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title><fmt:message key="createRoleForm.title"/></title>
	<%@ include file="/common/meta.jsp" %>
	<link href="${ctx}/css/yui.css" type="text/css" rel="stylesheet"/>
	<link href="${ctx}/css/style.css" type="text/css" rel="stylesheet"/>
	<link href="${ctx}/js/validate/jquery.validate.css" type="text/css" rel="stylesheet"/>
	<script src="${ctx}/js/jquery-1.4.2.min.js" type="text/javascript"></script>
	<script src="${ctx}/js/validate/jquery.validate.js" type="text/javascript"></script>
	<script src="${ctx}/js/validate/messages_cn.js" type="text/javascript"></script>

	<script>
		<!-- 
		$(document).ready(function() {
			//聚焦第一个输入框
			$("#name").focus();
			//为inputForm注册validate函数
			$("#inputForm").validate();
		});
		  
		function yourOnClickMethodIfYouNeed(object)
		{	
			//alert(object.value);
			//alert(_authorityList.value);
		}
		//-->
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
	<h2><c:choose><c:when test="${authority.id == null}">创建</c:when><c:otherwise>修改</c:otherwise></c:choose>权限</h2>
	<c:choose>
		<c:when test="${authority.id == null}">
			<c:url var="actionUrl" value="account/authority"/> 
			<c:url var="parameterMethod" value="post"/>
		</c:when>
		<c:otherwise>
		 	<c:url var="actionUrl" value="account/authority/${authority.id}"/> 
		 	<c:url var="parameterMethod" value="put"/>
		</c:otherwise>
	</c:choose>
	<form:form method="${parameterMethod}" commandName="authority" action="${ctx}/${actionUrl}">
		<input type="hidden" name="id" value="${authority.id}"/>
		<fieldset>  
                        <legend><fmt:message key="AuthorityForm.Fields"/></legend>
                        <ul>
                        <li>  
                            <form:label  for="name" path="name" cssErrorClass="error">  
                                <fmt:message key="authorityName"/>  
                            </form:label>  
                            <form:input path="name" />  
                            <form:errors path="name" />  
                        </li>
                        <li>
                            <security:authorize ifAnyGranted="ROLE_修改角色">
						<input class="button" type="submit" value="<fmt:message key="submit"/>"/>&nbsp;
					</security:authorize>
					<input class="button" type="button" value="<fmt:message key="return"/>" onclick="history.back()"/>  
                        </li>  
                   </ul>
		</fieldset>
	</form:form>
	</div>
	<hr>  
            <ul>  
                <li>  
                    <a href="?locale=zh_cn">zh</a> | <a href="?locale=en_us">us</a> | <a href="?locale=es_es">es</a> | <a href="?locale=de_de">de</a>  
                </li>  
            </ul> 
	</div>
</div>
<%@ include file="/common/footer.jsp" %>
</div>
</body>
</html>