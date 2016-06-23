<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="line breadcrumb-box">
	<ol class="breadcrumb">
	  <li ng-class="{active: $index === 2}">
	  	运营管理系统
	  </li>
	  <li ng-class="{active: $index === 2}">
	  	首页
	  </li>
	</ol>
</div>
<h2>欢迎来到车辆信息采集管理系统！</h2>
<script>
seajs.use(['js/common/app','js/common/index'], function() {
	angular.bootstrap(document, ['App']);
});
</script>