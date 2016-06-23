<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext['request'].contextPath}" />

<style>
	.error{color:red}
</style>

<div class="line breadcrumb-box">
	<ol class="breadcrumb">
	  <li ng-class="{active: $index === 2}">
	  	客户管理
	  </li>
	  <li ng-class="{active: $index === 2}">
	  	<a href="${ctx}/car/user/list">车主列表</a>
	  </li>
	  <li ng-class="{active: $index === 2}">
	  	添加/编辑
	  </li>
	</ol>
</div>

<div class="wrapper wrapper-content animated fadeInRight" ng-init="init(${id})">
	<div class="row">
		<div class="col-lg-12">
			<div class="ibox float-e-margins">
				<div class="ibox-content icons-box">
					<div class="row">
						<form name="baseForm" method="get" class="form-horizontal" ng-submit="save(baseForm.$valid)" novalidate>
						
							<div class="form-group">
								<label class="col-sm-2 control-label">用户名</label>
								<div class="col-md-4">
									<input type="text" class="form-control" ng-model="saveData.name" name="name" required autofocus ng-maxlength="15" max="15">
								</div>
								<span class="error" ng-show="baseForm.name.$dirty && baseForm.name.$invalid">*备注:必填,不超过15个字符</span>
							</div>
							
							<div class="form-group">
								<label class="col-sm-2 control-label">手机号</label>
								<div class="col-md-4">
									<input type="text" class="form-control" ng-model="saveData.phone" name="phone" required autofocus ng-maxlength="11" max="11">
								</div>
								<span class="error" ng-show="baseForm.phone.$dirty && baseForm.phone.$invalid">*备注:必填,11个字符</span>
							</div>
							
							<div class="form-group">
								<label class="col-sm-2 control-label">头像</label>
								<div class="col-md-6 form-inline">
				                    <img ng-if="saveData.avatar!=''&&!!saveData.avatar" ng-src="{{saveData.avatar}}" style="width: 100px; height: 100px;">
				                    <div class="m-t-xs">
				                        <button type="button" class="btn btn-primary" accept="image/*" ngf-select="upload($files)"> 选择上传图片</button>
				                    </div>
								</div>
							</div>
							
							<div class="form-group">
								<label class="col-sm-2 control-label">邮箱</label>
								<div class="col-md-4">
									<input type="email" class="form-control" ng-model="saveData.chatMail" name="chatMail" required autofocus>
								</div>
								<span class="error" ng-show="baseForm.chatMail.$dirty && baseForm.chatMail.$invalid">*备注:必填,邮箱</span>
							</div>
							
						</form>
					</div>
				</div>
				
				<div class="form-group">
					<div class="col-sm-4 col-sm-offset-2">
						<input type="button" ng-disabled="baseForm.$invalid" ng-click="save()" class="btn btn-primary" value="保存">
					</div>
				</div>
				
			</div>
		</div>
	</div>
</div>

<script>
	seajs.use(['js/common/app','js/car/userEdit'], function() {
		angular.bootstrap(document, [ 'App' ]);
	});
</script>