<!DOCTYPE html>
<html lang="en" class="no-js">
    <head>
        <meta charset="utf-8">
        <title>注册</title>
		<meta name="keywords" content="" />
		<meta name="description" content="" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="">
        <meta name="author" content="">

        <!-- CSS -->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/reset.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/supersized.css">
        <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/resources/img/qiche.jpg">

        <!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
        <!--[if lt IE 9]>
            <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
        <![endif]-->
    </head>

    <body>
        <div class="page-container">
        	<h1>车辆信息采集管理系统</h1><br><br>
            <form>
                <input type="text" name="name" placeholder="用户名" autocomplete="off" required="required">
                <input type="password" name="password" placeholder="密码" autocomplete="off">
                <input type="password" name="passwordAgain" placeholder="再次输入密码" autocomplete="off">
                <input type="text" name="realName" placeholder="真实姓名" autocomplete="off">
                <input type="text" name="email" placeholder="邮箱" autocomplete="off">
                <button type="button" id="register">注册</button>
                <button type="button" style="background: #0080ff;border: 1px solid #0080c0;" id="login">返回</button>
                <div class="error"><span>+</span></div>
            </form>
        </div>
		
        <!-- Javascript -->
        <script src="${pageContext.request.contextPath}/resources/js/login/jquery-1.8.2.min.js"></script>
        <script src="${pageContext.request.contextPath}/resources/js/login/supersized.3.2.7.min.js"></script>
        <script src="${pageContext.request.contextPath}/resources/js/login/supersized-init.js"></script>
        <script src="${pageContext.request.contextPath}/resources/js/login/scripts.js"></script>
        <script src="${pageContext.request.contextPath}/resources/js/login/register.js"></script>

    </body>

</html>


