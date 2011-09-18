<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>无标题文档</title>
<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}
.STYLE1 {
	font-size: 12px;
	color: #FFFFFF;
}
.STYLE2 {font-size: 9px}
.STYLE3 {
	color: #033d61;
	font-size: 12px;
}
-->
</style>

<SCRIPT language="JavaScript">
<!--
function showtime(){
var timenow;
var today=new Date();
var year=today.getFullYear();
var mon=today.getMonth()+1;
var date=today.getDate();
var day=today.getDay();
switch(day){
 case 0:day="星期日";break;
 case 1:day="星期一";break;
 case 2:day="星期二";break;
 case 3:day="星期三";break;
 case 4:day="星期四";break;
 case 5:day="星期五";break;
 case 6:day="星期六";break;
}
var hour=today.getHours();
var minu=today.getMinutes();
if(minu<10) minu="0"+minu;
var secd=today.getSeconds();
if(secd<10) secd="0"+secd;

timenow="<span class=\"STYLE1\"><span class=\"STYLE2\">■</span>"+"今天是："+year+"年"+mon+"月"+date+"日 "+day+" "+hour+":"+minu+":"+secd +"</span>";
document.getElementById("clock").innerHTML=timenow;
setTimeout("showtime()",1000);
}
window.onload=showtime;
//----->
</SCRIPT>
</head>

<body>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td height="70" background="images/main_05.gif"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td height="24"><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td width="270" height="24" background="images/main_03.gif">&nbsp;</td>
            <td width="505" background="images/main_04.gif">&nbsp;</td>
            <td>&nbsp;</td>
            <td width="21"><img src="images/main_07.gif" width="21" height="24"></td>
          </tr>
        </table></td>
      </tr>
      <tr>
        <td height="38"><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td width="270" height="38" background="images/main_09.gif">&nbsp;</td>
            <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td width="77%" height="25" valign="bottom"><table width="100%" border="0" cellspacing="0" cellpadding="0">
                  <tr>
                  	<!--
                    <td width="50" height="19"><div align="center"><a href="index.html" target="_top"><img src="images/main_12.gif" width="49" height="19"  border="0"></a></div></td>
                    <td width="50"><div align="center"><a href="javascript:history.back(-1)"><img src="images/main_14.gif" width="48" height="19" border="0"></a></div></td>
                    <td width="50"><div align="center"><a href="javascript:history.go(1)"><img src="images/main_16.gif" width="48" height="19" border="0"></a></div></td>
                    <td width="50"><div align="center"><a href="javascript:window.parent.mainFrame.I0.I2.location.stop()">停止</a></div></td>
                    <td width="50"><div align="center"><a href="javascript:window.parent.mainFrame.I0.I2.location.reload()"><img src="images/main_18.gif" width="48" height="19" border="0"></a></div></td>
                    
                    <td width="26"><div align="center"><img src="images/main_21.gif" width="26" height="19"></div></td>
                    
                    
                    <td width="100"><div align="center"><img src="images/main_22.gif" width="98" height="19"></div></td>
                    <td width="100"><div align="center">密码修改</div></td>
                    <td width="50"><div align="center"><a href="login_out.html" target="_top"><img src="images/main_20.gif" width="48" height="19" border="0"></a></div></td>
                    -->
                    <td>&nbsp;</td>
                  </tr>
                </table></td>
                <td width="320" valign="bottom"  nowrap="nowrap"><div id="clock" align="right"></div></td>
              </tr>
            </table></td>
            <td width="21"><img src="images/main_11.gif" width="21" height="38"></td>
          </tr>
        </table></td>
      </tr>
      <tr>
        <td height="8" style=" line-height:8px;"><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td width="270" background="images/main_29.gif" style=" line-height:8px;">&nbsp;</td>
            <td width="505" background="images/main_30.gif" style=" line-height:8px;">&nbsp;</td>
            <td style=" line-height:8px;">&nbsp;</td>
            <td width="21" style=" line-height:8px;"><img src="images/main_31.gif" width="21" height="8"></td>
          </tr>
        </table></td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td height="28" background="images/main_36.gif"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="177" height="28" background="images/main_32.gif"><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td width="20%"  height="22">&nbsp;</td>
            <td width="59%" valign="bottom"><div align="center" class="STYLE1">当前用户：${adminUserName}</div></td>
            <td width="21%">&nbsp;</td>
          </tr>
        </table></td>
        <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td width="65" height="28"><table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td height="23" valign="bottom"> </td>
              </tr>
            </table></td>
            <td width="3"></td>
            <td width="63"><div align="center"><a href="index.html" target="_top"><img src="images/main_12.gif" width="49" height="19"  border="0"></a></div></td>
            <td width="3"></td>
            <td width="63"><div align="center"><a href="javascript:history.back(-1)"><img src="images/main_14.gif" width="48" height="19" border="0"></a></div></td>
            <td width="3"></td>
            <td width="63"><div align="center"><a href="javascript:history.go(1)"><img src="images/main_16.gif" width="48" height="19" border="0"></a></div></td>
            <td width="3"></td>
            <td width="63"><div align="center"><a href="javascript:window.parent.mainFrame.I0.I2.location.reload()"><img src="images/main_18.gif" width="48" height="19" border="0"></a></div></td>
            <td width="3"></td>
            <td width="63"><div align="center"><a href="profileModify" target="I2"><img src="images/main_22.gif" width="98" height="19" border="0"></a></div></td>
            <td width="3"></td>
            <td width="63"><div align="center"><a href="javascript:window.parent.mainFrame.I0.I2.location.reload()"><img src="images/main_18.gif" width="48" height="19" border="0"></a></td>
            <td width="3"></td>
            <td width="63"><div align="center"><a href="${ctx}/j_spring_security_logout" target="_top"><img src="images/main_20.gif" width="48" height="19" border="0"></a></div> </td>
            <td>&nbsp;</td>
          </tr>
        </table></td>
        <td width="21"><img src="images/main_37.gif" width="21" height="28"></td>
      </tr>
    </table></td>
  </tr>
</table>
</body>
</html>
