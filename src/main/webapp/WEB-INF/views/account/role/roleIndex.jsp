<%@page contentType="text/html;charset=UTF-8"%>
<html>
<head>
	<title></title>
	<script src="${ctx}/js/jquery/jquery-1.4.2.min.js" type="text/javascript"></script>
	<script src="${ctx}/js/modules/role.js" type="text/javascript"></script> 
	<link href="${ctx}/css/admin2.css" rel="stylesheet" type="text/css" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">


<script language="JavaScript" type="text/JavaScript">
<!--

	/*function submitDelete(oSrc)
	{
		isCheck = hasSelected();
		if(isCheck == true)
		{
			if (confirm ("是否确认删除已选定信息!"))
			{
				oSrc.action = "/admin/RoleDelete.do";
				oSrc.submit();
			}
		}
		else
		{
			alert("请先选择要删除的记录");
		}
	}*/
	function submitdelete(roleId)
	{
			if (confirm ("是否确认删除已选定信息!"))
			{
				document.location.replace("/admin/RoleDelete.do?roleId="+roleId);
			}
	}


	function hasSelected()
	{
		var r = 0;
		for(var i = 0;i < document.form2.elements.length; i++)
	    {
	    	var e = document.form2.elements[i];
	    	if(e.name == 'rowId' && e.checked == 1)
	    		r = 1;
	    }
	    return r;
	}
	
	function checkall()
	{
		for(var i = 0;i < document.form2.elements.length; i++)
	    {
	    	var e = document.form2.elements[i];
	    	if(e.name == 'rowId')

	    		e.checked = document.form2.all.checked;
	    }
	}
	
//-->
</script>
</head>

<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<table width="100%" border="0" cellpadding="0" cellspacing="0">
  <tr> 
    <td height="20" bgcolor="#F8F5F8">用户管理<img src="${ctx}/images/admin/arrow.jpg" width="12" height="8" align="absmiddle">角色列表</td>
  </tr>
</table>

<form name="form2" method=POST action="">
<table height="23" border="0" cellpadding="0" cellspacing="0">
  <tr>
      <td height="23">   
      	<a href="${ctx}/admin/RoleAdd.do"><img src="${ctx}/images/admin/ico_add.gif" border="0"></a>    	
		<!--<img src="/images/admin/ico1_3.gif" style="cursor:hand"  onclick="submitDelete(form2)" />-->
      </td>
 </tr>

</table>

  <table class=color-border-table width="100%" border="0" cellspacing="1" cellpadding="1" id="rolelist">
    <tr class=color-bg-tr2 align="center">
      <td align="center"><input type="checkbox" name="all" value="checkbox"  onclick="checkall()">全选</td>
      <td>角色名称</td>
      <td>角色显示名称</td>
      <td>操作</td>
    </tr>


    <tr>
      <td align="center"><input type="checkbox" name="rowId" value="" ></td>
      <td align="center"></td>
      <td align="center"><a href="${ctx}/admin/RoleModify.do?roleId=" target="_self"></a></td>
      <td align="center"><a href="${ctx}/admin/RoleModify.do?roleId=" target="_self">修改</a>/<a href="#" onClick="submitdelete();">删除</a></td>
    </tr>

    	  </form>
    </table>
  <table  width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="color-border-title2">

  	<tr>
  		<td align="left">

    	</td>
    	<td align="right">

			</td>
			</tr>

	</table>

</body>
</html>
<script language="JavaScript">
 list();
</script>
