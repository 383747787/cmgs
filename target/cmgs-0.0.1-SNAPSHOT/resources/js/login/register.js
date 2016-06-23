var pathName=window.document.location.pathname;
var projectName=pathName.substring(0,pathName.substr(1).indexOf('/')+1);

$(window).load(function(){
	$('input').val('');
})

$("#register").click(register);

$("#login").click(function(){
	window.location.href = projectName + "/login";
});

$("body").keydown(function(e){
	if (e.keyCode == '13') {
		login();
	}
});

function register() {
	var name  = $("input[name='name']").val();
	var password = $("input[name='password']").val();
	var passwordAgain = $("input[name='passwordAgain']").val();
	var realName = $("input[name='realName']").val();
	var email = $("input[name='email']").val();
	
	if (name==null || name=='') {
		alert('用户名不能为空');
		return;
	}
	if (password==null || password=='') {
		alert('密码不能为空');
		return;
	}
	if (passwordAgain==null || passwordAgain=='') {
		alert('请再次输入密码');
		return;
	}
	if (password != passwordAgain) {
		alert('两次密码输入不一致，请重新输入');
		return;
	}
	if (realName==null || realName=='') {
		alert('真实姓名不能为空');
		return;
	}
	if (email==null || email=='') {
		alert('邮箱不能为空');
		return;
	}
	$.ajax({
		url : projectName + "/register/do",
		type : "post",
		data : $('form').serialize(),
		dataType : "json",
		success : function(response) {
			var data =  jQuery.parseJSON(response);
			if (data.success) {
				alert(data.msg);
				window.location.href = projectName + "/login";
			} else if (!data.success) {
				alert(data.msg);
			}
		}
	});
}