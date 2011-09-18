<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="org.springframework.samples.mvc.basic.account.SpringSecurityUtils" %>
<%@ include file="/common/taglibs.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>Mini-Web 权限管理</title>
	<%@ include file="/common/meta.jsp" %>
	<link href="${ctx}/css/yui.css" type="text/css" rel="stylesheet"/>
	<link href="${ctx}/css/style.css" type="text/css" rel="stylesheet"/>
	<script type="text/javascript" src="${ctx}/js/jquery-1.4.2.min.js"></script>
	<script type="text/javascript">
	<!--
		$(document).ready( function() {

		  $('a.delete').click( function() {
		    var theAnchor = this;
		    if ( confirm('Are you sure you want to delete this?' ) )
		      $.ajax({
		        type: 'delete',
		        url: $(this).attr('href'),
		        dataType: 'json',
		        success: function(data, textStatus) {
		          //if (data['ok'] == true) {
		          if (data.ok == 'true') {
		            var toRemove = $(theAnchor).attr('data-remove');
		            alert(toRemove);
		            $("#" + toRemove).remove();
		          } else {
		            alert( "Oooops!, something failed" );
		          } 
		        },
		        error: function (XMLHttpRequest, textStatus, errorThrown) {
		          alert("Ooooops!, request failed with status: " + XMLHttpRequest.status + ' ' + XMLHttpRequest.responseText);
		        }
		      });
		    return false;
		  });

		});
	//-->
</script>
</head>

<body>
<div id="doc3">
<%@ include file="/common/header.jsp" %>
<div id="bd">
	<div id="yui-main">
	<div class="yui-b">
	<div id="message"><s:actionmessage theme="custom" cssClass="success"/></div>

	<div id="filter">你好, <%=SpringSecurityUtils.getCurrentUserName()%>.</div>

	<div id="content">
		<table id="contentTable">
			<tr>
				<th>名称</th>
				<th>描述</th>
				<th>操作</th>
			</tr>
			<c:forEach  var="authority" items="${allAuthorityList}">
				<tr id="task_${authority.id}">
					<td>${authority.name}</td>
					<td>${authority.name}</td>
					<td>&nbsp;
						<security:authorize ifAnyGranted="ROLE_浏览角色">
							<security:authorize ifNotGranted="ROLE_修改角色">
								<a href="authority/${authority.id}">查看</a>&nbsp;
							</security:authorize>
						</security:authorize>

						<security:authorize ifAnyGranted="ROLE_修改角色">
							<a href="${ctx}/account/authority/${authority.id}" id="editLink-${authority.name}">修改</a>&nbsp;
							<a class="delete" href="${ctx}/account/authority/${authority.id}" id="deleteLink-${authority.name}" data-remove="task_${authority.id}">删除</a>
						</security:authorize>
					</td>
				</tr>
			</c:forEach>
		</table>
	</div>

	<div>
		<security:authorize ifAnyGranted="ROLE_修改角色">
			<a href="${ctx}/account/authority/new">增加新权限</a>
		</security:authorize>
	</div>
	</div>
	</div>
</div>
<%@ include file="/common/footer.jsp" %>
</div>
</body>
</html>
