//列出数据函数$(json.list.model).each(function() {
function list(){
		$.getJSON("account/user/list",
				{},
				function(json){
					var html='<tr class="color-bg-tr2" align="right"><td align="center"><input type="checkbox" name="all" value="checkbox"  onclick="checkall()">全选</td><td height="22" align=center>注册名</td><td height="22" align="center">真实姓名</td><td align=center>操作</td></tr>';
					$.each(json.list.user,
							function(i,user){
								if((i++)%2==0)
									html+='<tr align="right" bgcolor="#F1F6FC"><td width="10%" align="center"><input type="checkbox" name="rowId" value="'+user.userId+'"></td><td height="20" width="25%" align=center><a href="/admin/UserDetail.do?userId="'+user.userId+'>'+user.userName+'</a></td><td height="20" width="25%" align="center">'+user.realName+'</td><td align=center width="15%"><a href="/admin/PreResetPassword.do?userName="'+user.userId+'>重置密码</a></td></tr>';
								else
									html+='<tr align="right" bgcolor="#FFFFFF"><td width="10%" align="center"><input type="checkbox" name="rowId" value="'+user.userId+'"></td><td height="20" width="25%" align=center><a href="/admin/UserDetail.do?userId="'+user.userId+'>'+user.userName+'</a></td><td height="20" width="25%" align="center">'+user.realName+'</td><td align=center width="15%"><a href="/admin/PreResetPassword.do?userName="'+user.userId+'>重置密码</a></td></tr>';
								//html+='<tr align="center" height="30" class="News"><td><a href="showNews.action?nid='+user.userId+'">'+user.realName+'</a></td><td><a href="showNews.action?nid='+user.userId+'">'+user.userId+'</a></td><td><a href="showNews.action?nid='+user.userId+'">'+user.userName+'</a></td><td><a href="AJAXNews/delete.action?nid='+user.userId+'">删除</a> <a href="update.action?nid='+user.userId+'">修改</a></td></tr>';
							}
						)
					$("#userlist").empty();
					$("#userlist").append(html);
				}
		);
		return false;
	}