<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="org.springframework.samples.mvc.basic.account.SpringSecurityUtils" %>
<%@ include file="/common/taglibs.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>Mini-Web 帐号管理</title>
	<%@ include file="/common/meta.jsp" %>
	<link href="${ctx}/css/yui.css" type="text/css" rel="stylesheet"/>
	<link href="${ctx}/css/style.css" type="text/css" rel="stylesheet"/>
	<script src="${ctx}/js/jquery-1.4.2.min.js" type="text/javascript"></script>
	<script src="${ctx}/js/table.js" type="text/javascript"></script>
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
		            alert(toRemove);alert($("#" + toRemove));
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
	<form:form method="get" commandName="query" action="${ctx}/account/user" id="mainForm" >
		<input type="hidden" name="pageNumber" id="pageNo" value="${query.pageNumber}"/>


		<div id="message"><s:actionmessage theme="custom" cssClass="success"/></div>
		<div id="filter">
			你好, <%=SpringSecurityUtils.getCurrentUserName()%>.&nbsp;&nbsp;
			登录名: <input type="text" name="filter_EQS_loginName" value="${param['filter_EQS_loginName']}" size="9"/>
			姓名或Email: <input type="text" name="filter_LIKES_name_OR_email"
							 value="${param['filter_LIKES_name_OR_email']}" size="9"/>
			<input type="button" value="搜索" onclick="search();"/>
		</div>
		<div id="content">
			<table id="contentTable">
				<tr>
					<th><a href="javascript:sort('loginName','asc')">登录名</a></th>
					<th><a href="javascript:sort('name','asc')">姓名</a></th>
					<th><a href="javascript:sort('email','asc')">电邮</a></th>
					<th>角色</th>
					<th>操作</th>
				</tr>

				<c:forEach var="user" items="${page.result}">
					<tr id="task_${user.id}">
						<td>${user.loginName}&nbsp;</td>
						<td>${user.name}&nbsp;</td>
						<td>${user.email}&nbsp;</td>
						<td>${user.roleNames}&nbsp;</td>
						<td>&nbsp;
							<security:authorize ifAnyGranted="ROLE_浏览用户">
								<security:authorize ifNotGranted="ROLE_修改用户">
									<a href="user/${user.id}">查看</a>&nbsp;
								</security:authorize>
							</security:authorize>

							<security:authorize ifAnyGranted="ROLE_修改用户">
								<a href="${ctx}/account/user/${user.id}" id="editLink-${user.name}">修改</a>&nbsp;
								<a class="delete" href="${ctx}/account/user/${user.id}" id="deleteLink-${user.name}" data-remove="task_${user.id}">删除</a>
							</security:authorize>
						</td>
					</tr>
				</c:forEach>
			</table>
		</div>

		<div>
			第${page.pageNumber}页, 共${page.totalPages}页
			<a href="javascript:jumpPage(1)">首页</a>
			<c:if test="${page.hasPreviousPage}"><a href="javascript:jumpPage(${page.prePage})">上一页</a></c:if>
			<c:if test="${page.hasNextPage}"><a href="javascript:jumpPage(${page.nextPage})">下一页</a></c:if>
			<a href="javascript:jumpPage(${page.totalPages})">末页</a>

			<security:authorize ifAnyGranted="ROLE_修改用户">
				<a href="${ctx}/account/user/new">增加新用户</a>
			</security:authorize>
		</div>
	</form:form>
	</div>
	</div>
</div>
<%@ include file="/common/footer.jsp" %>
</div>
</body>
</html>
