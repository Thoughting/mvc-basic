//更新新闻函数
function update(){
		$.getJSON("profile.html",
			{},
			function(json)
			{
					$('input[name=realName]').val(json.user.realName);
					$('input[name=email]').val(json.user.email);
					//$('input[name=sex]').val(json.user.sex);
			}
		);
		return false;
	}