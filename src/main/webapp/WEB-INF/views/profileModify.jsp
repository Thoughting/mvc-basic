<%@ page contentType="text/html;charset=UTF-8" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>无标题文档</title>
<script src="js/jquery/jquery-1.2.6.min.js" type="text/javascript"></script>
<script src="js/modules/profile.js" type="text/javascript"></script> 
<link href="styles/admin2.css" rel="stylesheet" type="text/css">
<script language="JavaScript"> 
function FormCheck()
{
	var truename = document.getElementById("realName");
	if(truename.value == ""||truename.value==null){
		alert("请填写真实姓名！");
		truename.focus();
		return false;
	}
	if(truename.length>30)
	{
		alert("真实姓名要小于30个字符");
		truename.focus();
		return false;
	}
	var email=document.getElementById("email").value;
	if(email!=""){


		//判断字符串S是否是一个有效EMAIL地址
		var i=1;
		var sLength=email.length;

		while((i<sLength)&&(email.charAt(i) != "@")) i++;
		if ((i >= sLength) || (email.charAt(i) != "@"))
		{
			alert("EMAIL地址非法,请重新输入!");
			return false;
		}
		else i += 2;

		while((i<sLength)&&(email.charAt(i) != ".")) i++;
		if ((i >= sLength-1) || (email.charAt(i) != "."))
		{
			alert("EMAIL地址非法,请重新输入!");
			return false;
		}
	}
	var birth=document.getElementById("birthDay");

	var tel;
	tel=document.getElementById("phone");

	if (tel.value.length>30)
	{
	   alert("电话长度不能超过30位！");
	   tel.focus();
	   return false;
	}

	 return true;
}

</script>
</head>

<body  leftmargin="0" topmargin="0">
<table width="100%" border="0" cellspacing="0" cellpadding="0" >
  <tr>
    <td height="20" bgcolor="#F8F5F8">个人资料管理<img src="images/arrow.jpg" >个人信息修改</td>
  </tr>
</table>
<br>
<form name="demoform" action="/admin/PersonalInfoSave" method="post" onsubmit="return FormCheck()">
<table class=color-border-table width="80%" border="0" cellspacing="1" cellpadding="0" align="center">

    <tr class="color-bg-tr2">
      <td align="right" class="form-left"  width="30%" height="20">用户真实姓名：</td>
      <td class="form-right" width="70%" height="20"> 
      	<input type="text" name="realName" size="15"  maxlength="30" styleId="realName" value="${user.realName}"/>
        *（小于30个字符）
        <html:errors property="realName" /></td>
    </tr>
    <tr class="color-bg-tr2">
      <td width="30%" height="20" align="right" class="form-left">EMAIL：</td>
      <td width="70%" height="20" class="form-right"> 
      	<input type="text" name="email" size="30" maxlength="30" styleId="email" value="${user.email}"/>
      		<html:errors property="email" /></td>
    </tr>

  <tr class="color-bg-tr2">
    <td width="30%" height="20" align="right" class="form-left">性别：</td>
    <td width="70%" height="20" class="form-right">
      <select property="sex" styleId="sex" size="1">
        	<option value="1" >男</option>
        	<option value="2" selected>女</option>
      </select><html:errors property="sex" /></td>
  </tr>
    <tr class="color-bg-tr2">
      <td width="30%" height="20" align="right" class="form-left">出生日期：</td>
    <td width="70%" height="20" class="form-right"><input name="birthDay" class="table-textfile" onfocus="this.blur()" value="${user.createDate}" size="9" readonly>
        <span class="calendar">【<a href="javascript:void(0)" onclick="if(self.gfPop)gfPop.fPopCalendar(document.demoform.birthDay);return false;" HIDEFOCUS>日历</a>】
    	<%
    		java.util.Date defaultDate = (java.util.Date)request.getAttribute("date") ;
    	%>
    	<logic:notEmpty name="date">
    	<view:datepicker type="single" name="birthDay" default="<%=defaultDate%>" />
    </logic:notEmpty>
    <logic:empty name="date">
    	<view:datepicker type="single" name="birthDay" />
    	</logic:empty> 
    	<html:errors property="birthDay" />	
    		</td>
    </tr>
    <iframe width=174 height=189 name="gToday:normal:agenda.js" id="gToday:normal:agenda.js"
			src="js/ipopeng.htm" scrolling="no" frameborder="0" style="visibility:visible; z-index:999; position:absolute; left:-500px; top:0px;"> 
		</iframe>	
    <tr class="color-bg-tr2">
      <td width="30%" height="20" align="right" class="form-left">电话：</td>
      <td width="70%" height="20" class="form-right"> 
      	<input type="text" name="phone" size="30" maxlength="30" styleId="phone"/> （小于30位）
      		<html:errors property="phone" /></td>
    </tr>
  </table>

<table width="80%" border="0" cellspacing="0" cellpadding="0">
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
<script language="JavaScript">
 update();
</script>

