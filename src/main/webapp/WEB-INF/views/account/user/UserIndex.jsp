<%@page contentType="text/html;charset=UTF-8"%>

<html>
<head>
<title></title>
<script src="${ctx}/js/jquery/jquery-1.4.2.min.js" type="text/javascript"></script>
<script src="${ctx}/js/modules/user.js" type="text/javascript"></script> 
<link href="${ctx}/css/admin2.css" rel="stylesheet" type="text/css">
</head>
<script language="javascript">
	function ToDelete()
	{
		if(hasSelected() == 1)
		{
			if (window.confirm("确实要删除这些用户吗？"))
			{ 
			    document.form2.submit();
			}
		}
		else
		{
			alert('您还没有选择用户！');
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
	function querySubmit()
	{
				form1.submit();
	}
</script>
<body leftmargin="0" topmargin="0">
<form name="form1" action="${ctx}/admin/UserList.do" method="post">	


<table width="100%" border="0" cellpadding="0" cellspacing="0">
  <tr> 
    <td height="20" bgcolor="#F8F5F8">用户管理<img src="${ctx}/images/admin/arrow.jpg" width="12" height="8" align="absmiddle">用户列表</td>
  </tr>
</table>
<table width="96%" border="0" align="center" cellpadding="0" cellspacing="0">
   <tr height="30">
    <td width="20%"> 
      <a href="${ctx}/admin/usermanager/CreateUserForm.jsp"><img src="${ctx}/images/admin/ico1_1.gif" width="58" height="18" border="0"></a>
      <img src="${ctx}/images/admin/ico1_3.gif" width="58" height="18" onClick="ToDelete();" style="cursor:hand"> 
    </td>
	</tr>
	<tr align="center">
    <td width="80%" align="left" nowrap>
    

       注册名:<input type=text name="regName"  size="10" value="">
       真实姓名:<input type=text name="chineseName"  size="10" value="">
       E-mail:<input type=text name="email"  size="10" value="">
               
            角色:<select name=allActor >
								<option value="-1" >请选择角色</option>
              	
              	<option value=""> </option>
             
            </select>
      
    </td>
 
  <td align="left" width="20%">

			<a href="#" onclick="querySubmit();"><img src="${ctx}/images/admin/ico1_7.gif" width="58" height="18" border="0"></a>
  </td></tr>
</table>
</form>
<form name="form2" action="${ctx}/admin/UserDelete.do" method="post">

<table width="96%" border="0" align="center" cellpadding="1" cellspacing="1" class="color-border-table" id="userlist">
  <tr class="color-bg-tr2" align="right"> 
    <td align="center"><input type="checkbox" name="all" value="checkbox"  onclick="checkall()">全选</td>   
    <td height="22" align=center>注册名</td>
    <td height="22" align="center">真实姓名</td>
    <!--td height="22" align="center">E-mail</td-->
    <td align=center>操作</td>
  </tr>
  <tr align="right">
  	<td width="10%" align="center">
  		<input  type="checkbox" name="rowId" value="">
  	</td> 
    <td height="20" width="25%" align=center><a href="${ctx}/admin/UserDetail.do?userId="></a></td>	
		<td height="20" width="25%" align="center"></td>
    <!--td height="20" width="25%" align="center"><- %= StringUtils.defaultString(user.getEmail())%></td--> 
    <td align=center width="15%"><a href="${ctx}/admin/PreResetPassword.do?userName=">重置密码</a></td>
  </tr>
  <input type="hidden" name="pageNo" value="">
	<table width="96%" border="0" align="center" cellpadding="0" cellspacing="0" class="color-border-title2">
		<tr>
			<td align="left">
				<paging:pageInfo empty="没有记录"/>
			</td>
			<td align="right">
			</td>
		</tr>
	</table>
</table>

</form>
</body>
</html>
<script language="JavaScript">
 list();
</script>
