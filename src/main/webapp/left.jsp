<%request.setCharacterEncoding("UTF-8");%>
<%@page contentType="text/html;charset=UTF-8"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
   "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>无标题文档</title>
<link rel="stylesheet" type="text/css" href="css/menu.css" />
	<script type="text/javascript" src="js/jquery.js"></script>
	<script src="js/menu.js" type="text/javascript"></script>
   <!--[if lt IE 8]>
   <style type="text/css">
   li a {display:inline-block;}
   li a {display:block;}
   </style>
   <![endif]-->
</head>
<body>
	<ul id="menu">
		<li>
			<a href="#">Weblog Tools</a>
			<ul>
				<li><a href="http://www.pivotx.net/">PivotX</a></li>
				<li><a href="http://www.wordpress.org/">WordPress</a></li>
				<li><a href="http://www.textpattern.com/">Textpattern</a></li>
				<li><a href="http://typosphere.org/">Typo</a></li>
			</ul>
		</li>
		<li>
			<a href="#">系统管理</a>
			<ul>
				<li><a href="account/user" target="I2">帐号列表</a></li>
				<li><a href="account/role" target="I2">角色列表</a></li>
				<li><a href="account/authority" target="I2">权限列表</a></li>
			</ul>
		</li>
		<li><a href="account/user" target="I2">Marco's blog (no submenu)</a></li>
		<li>
			<a href="#">Cool Stuff</a>
			<ul>
				<li><a href="http://www.apple.com/">Apple</a></li>
				<li><a href="http://www.nikon.com/">Nikon</a></li>
				<li><a href="http://www.xbox.com/en-US/">XBOX360</a></li>
				<li><a href="http://www.nintendo.com/">Nintendo</a></li>
			</ul>
		</li>
		<li>
			<a href="#">Search Engines</a>
			<ul>
				<li><a href="http://search.yahoo.com/">Yahoo!</a></li>
				<li><a href="http://www.google.com/">Google</a></li>
				<li><a href="http://www.ask.com/">Ask.com</a></li>
				<li><a href="http://www.live.com/?searchonly=true">Live Search</a></li>
			</ul>
		</li>
	</ul>
</body>
</html>