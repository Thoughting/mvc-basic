<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<div id="hd">
	<div id="title">
		<h1>Mini-Web示例</h1>
		<h2>--CRUD管理界面演示</h2>
	</div>
	<div id="menu">
		<ul>
		<security:authorize ifAnyGranted="ROLE_浏览用户">
			<li><a href="${ctx}/account/user">帐号列表</a></li>
		</security:authorize>
		<security:authorize ifAnyGranted="ROLE_浏览角色">
			<li><a href="${ctx}/account/role">角色列表</a></li>
		</security:authorize>
		<security:authorize ifAnyGranted="ROLE_浏览权限">
			<li><a href="${ctx}/account/authority">权限管理</a></li>
		</security:authorize>
			<li><a href="${ctx}/j_spring_security_logout">退出登录</a></li>
		</ul>
	</div>
</div>