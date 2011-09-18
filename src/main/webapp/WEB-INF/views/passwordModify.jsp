<%@ page contentType="text/html;charset=UTF-8" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>无标题文档</title>
<link href="styles/admin2.css" rel="stylesheet" type="text/css">

</head>

<body leftmargin="0" topmargin="0">
<script language="JavaScript">
	function checkForm()
	{
		oldPassword=document.getElementById("oldPassword");
		newPassword=document.getElementById("newPassword");
		rePassword=document.getElementById("rePassword");
		if(oldPassword.value==null||oldPassword.value=="")
		{
			alert("旧密码不能为空!");
			return false;
		}
		if(newPassword.value==null||newPassword.value=="")
		{
			alert("新密码不能为空!");
			return false;
		}
		if(rePassword.value==null||rePassword.value=="")
		{
			alert("确认密码不能为空!");
			return false;
		}
		if(newPassword.value!=rePassword.value)
		{
			alert("新密码与确认密码不一致!");
			return false;
		}
		return true;
	}
		

</script>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td height="20" bgcolor="#F8F5F8">个人资料管理<img src="images/arrow.jpg" >个人密码修改</td>
  </tr>
</table>

<br>
<form method="post" action="/admin/PasswordChange" styleId="form" onsubmit="return checkForm()">
<table class=color-border-table width="80%" border="0" cellspacing="1" cellpadding="0" align="center">

  <tr class=color-bg-tr2>
    <td width="40%" height="20" align="right" class="form-left">旧密码：</td>
    <td class="form-right" width="70%" height="20">
    	<input type="text" name="oldPassword" property="oldPassword" size="15" maxlength="30" /> *（请输入原有密码）
    	<html:errors property="oldPassword" /></td>
  </tr>
  <tr class=color-bg-tr2>
    <td width="40%" height="20" align="right" class="form-left">新密码：</td>
    <td width="60%" height="20" class="form-right">
			<input type="text" name="newPassword" property="newPassword" size="15" maxlength="30" /> *（6-12个数字或字母）
			<html:errors property="newPassword" /></td>
  </tr>
  <tr class=color-bg-tr2>
    <td width="40%" height="20" align="right" class="form-left">确认密码：</td>
    <td width="60%" height="20" class="form-right">
			<input class=input-list name="rePassword" id="rePassword" type="password" size="15" /> *（6-12个数字或字母）</td>
  </tr>
</table>

<table align="center" width="80%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td align="center" height="39">
    	<input name="Submit2"  type="submit" class=button-2letter  value="修改">
      <input name="Submit22"  type="button" class=button-2letter onClick="history.back()" value="返回">
    </td>
  </tr>
</table>
</form>
</body>
</html>
