var pathName=window.document.location.pathname;
var projectName=pathName.substring(0,pathName.substr(1).indexOf('/')+1);

$("#register").click(function() {
	window.location.href = projectName + "/register";
});

$("body").keydown(function(e){
	if (e.keyCode == '13') {
		$("#login").click();
	}
});


$("#login").click(function() {
	var name  = $("input[name='name']").val();
	var password = $("input[name='password']").val();
	
	if (name==null || name=='') {
		alert('用户名不能为空');
		return;
	}
	if (password==null || password=='') {
		alert('密码不能为空');
		return;
	}
	
	$.ajax({
		url : projectName + "/login/do",
		type : "post",
		data : $('form').serialize(),
		dataType : "json",
		success : function(response) {
			var data =  jQuery.parseJSON(response);
			if (data.success) {
				window.location.href = projectName + "/index";
			} else if (!data.success) {
				alert(data.msg);
			}
		},
		error : function(response) {
			console.log(response);
		}
	});
});

