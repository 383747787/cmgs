<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext['request'].contextPath}" />

<style>
	.error{color:red}
</style>

<div class="line breadcrumb-box">
	<ol class="breadcrumb">
	  <li ng-class="{active: $index === 2}">
	  	个人中心
	  </li>
	  <li ng-class="{active: $index === 2}">
	  <a href="${ctx}/user/message">个人信息</a>
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
									<span ng-bind="saveData.name" ng-show="!isEdit"></span>
									<input type="text" class="form-control" ng-model="saveData.name" name="name" ng-show="isEdit" required autofocus  ng-maxlength="20" max="20">
								</div>
								<span class="error" ng-show="baseForm.name.$dirty && baseForm.name.$invalid && isEdit">*备注:必填,最多20个字符</span>
							</div>
							
							<div class="form-group">
								<label class="col-sm-2 control-label">真实姓名</label>
								<div class="col-md-4">
									<span ng-bind="saveData.realName" ng-show="!isEdit"></span>
									<input type="text" class="form-control" ng-model="saveData.realName" name="realName" ng-show="isEdit" required autofocus ng-maxlength="20" max="20">
								</div>
								<span class="error" ng-show="baseForm.realName.$dirty && baseForm.realName.$invalid && isEdit">*备注:必填,最多20个字符</span>
							</div>
							
							<div class="form-group">
								<label class="col-sm-2 control-label">邮箱</label>
								<div class="col-md-4">
									<span ng-bind="saveData.email" ng-show="!isEdit"></span>
									<input type="text" class="form-control" ng-model="saveData.email" name="email" ng-show="isEdit" required autofocus>
								</div>
								<span class="error" ng-show="baseForm.email.$dirty && baseForm.email.$invalid && isEdit">*备注:必填</span>
							</div>
							
							<div class="form-group" ng-show="!isChangePwd && isEdit">
								<label class="col-sm-2 control-label">密码</label>
								<div class="col-md-4">
									<input type="button" ng-click="changePwd()" class="btn btn-primary" value="修改密码">
								</div>
							</div>
							
							<div class="form-group" ng-show="isChangePwd">
								<label class="col-sm-2 control-label">原密码</label>
								<div class="col-md-4">
									<input type="password" class="form-control" ng-model="oldPassword" name="oldPassword" autofocus>
								</div>
							</div>
							
							<div class="form-group" ng-show="isChangePwd">
								<label class="col-sm-2 control-label">新密码</label>
								<div class="col-md-4">
									<input type="password" class="form-control" ng-model="newPassword1" name="newPassword1" autofocus>
								</div>
							</div>
							
							<div class="form-group" ng-show="isChangePwd">
								<label class="col-sm-2 control-label">再次输入新密码</label>
								<div class="col-md-4">
									<input type="password" class="form-control" ng-model="newPassword2" name="newPassword2" autofocus>
								</div>
							</div>
							
						</form>
					</div>
				</div>
				
				<div class="form-group">
					<div class="col-sm-4 col-sm-offset-2">
						<input type="button" ng-show="!isEdit" ng-click="edit()" class="btn btn-primary" value="编辑">
						<input type="button" ng-show="isEdit" ng-disabled="baseForm.$invalid" ng-click="save()" class="btn btn-primary" value="保存">
					</div>
				</div>
				
			</div>
		</div>
	</div>
</div>

<script>
	seajs.use(['js/common/app','js/user/userMessage'], function() {
		angular.bootstrap(document, [ 'App' ]);
	});
</script>