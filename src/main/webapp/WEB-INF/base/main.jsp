<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>

<c:set var="resHost" value="${applicationScope.conf['app.res.host']}" />
<c:set var="ctx" value="${pageContext['request'].contextPath}" />

<!DOCTYPE HTML>
<html lang="en">
<head>
<title>车辆信息采集管理系统</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="description" content="">
<meta name="author" content="">
<!-- CSS -->
<link href="${ctx}/resources/bower_components/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet"/>
<link href="${ctx}/resources/bower_components/AngularJS-Toaster/toaster.min.css" rel="stylesheet"/>
<link href="${ctx}/resources/bower_components/eonasdan-bootstrap-datetimepicker/build/css/bootstrap-datetimepicker.min.css" rel="stylesheet"/>
<link href="${ctx}/resources/styles/admin.css" rel="stylesheet"/>
<link href="${ctx}/resources/styles/style.css" rel="stylesheet"/>
<link href="${ctx}/resources/styles/custom.css" rel="stylesheet"/>
<link rel="icon" type="image/png" href="${pageContext.request.contextPath}/resources/img/qiche.jpg">

<!-- Mainly scripts -->
<script src="${ctx}/resources/sea.js"></script>
<script src="${ctx}/resources/bower_components/jquery/dist/jquery.min.js"></script>
<script src="${ctx}/resources/bower_components/angular/angular.min.js"></script>
<script>
	angular.path = '${ctx}';
</script>
<script src="${ctx}/resources/bower_components/angular-animate/angular-animate.min.js"></script>
<script src="${ctx}/resources/bower_components/AngularJS-Toaster/toaster.js"></script>
<script src="${ctx}/resources/bower_components/angular-sanitize/angular-sanitize.min.js"></script>
<script src="${ctx}/resources/bower_components/angular-bootstrap/ui-bootstrap-tpls.min.js"></script>
<script src="${ctx}/resources/bower_components/zeroclipboard/dist/ZeroClipboard.min.js"></script>
<script src="${ctx}/resources/bower_components/angular-zeroclipboard/dist/angular-zeroclipboard.min.js"></script>
<script src="${ctx}/resources/bower_components/ng-file-upload/ng-file-upload.min.js"></script>
<script src="${ctx}/resources/bower_components/moment/min/moment.min.js"></script>
<script src="${ctx}/resources/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
<script src="${ctx}/resources/bower_components/eonasdan-bootstrap-datetimepicker/build/js/bootstrap-datetimepicker.min.js"></script>

<script src="${ctx}/resources/config.js"></script>

<script>

var _global = {
	summernoteDefaultOptions : {
		height : 200,
		lang : 'zh-CN',
		toolbar : [
		//[groupname, [button list]]

		[ 'style', [ 'bold', 'italic', 'underline', 'clear' ] ],
				[ 'font', [ 'strikethrough' ] ],
				[ 'fontsize', [ 'fontsize' ] ], [ 'color', [ 'color' ] ],
				[ 'para', [ 'ul', 'ol', 'paragraph' ] ],
				[ 'height', [ 'height' ] ], 
				['table',['table']]]
	}
};

	angular.summernoteDefaultOptions={
		height : 200,
		lang : 'zh-CN',
		toolbar : [
		//[groupname, [button list]]

		[ 'style', [ 'bold', 'italic', 'underline', 'clear' ] ],
				[ 'font', [ 'strikethrough' ] ],
				[ 'fontsize', [ 'fontsize' ] ], [ 'color', [ 'color' ] ],
				[ 'para', [ 'ul', 'ol', 'paragraph' ] ],
				[ 'height', [ 'height' ] ], ]
	}
	
</script>
</head>
<body class="pace-done" ng-controller="rootCtrl" ng-init="initUser(${user})">
	<div class="pace pace-inactive">
		<div class="pace-progress" data-progress-text="100%"
			data-progress="99" style="width: 100%;">
			<div class="pace-progress-inner"></div>
		</div>
		<div class="pace-activity"></div>
	</div>
	<div id="wrapper">
		<div class="default-bg">

			<nav loop-menu="menuData.systems" class="ups-loop-menu"></nav>
			
			<div id="page-wrapper" class="gray-bg">
				<div class="row border-bottom">
					<nav class="navbar navbar-static-top" role="navigation"
						style="margin-bottom: 0">
						<ul class="nav navbar-top-links navbar-right">
							<li><span class="m-r-sm text-muted welcome-message">您好，<strong>${user.realName}</strong></span></li>
							<li><a href="${ctx}/logout"> <i></i> 登出
							</a></li>
						</ul>
					</nav>
				</div>

				<div class="main-wrapper" ng-controller="mainCtrl">
				  <div class="panel panel-default">
						<div class="panel-body">
							<tiles:insertAttribute name="body" />
						</div>
					</div>
				</div>

			</div>
		</div>
	</div>
</body>
</html>