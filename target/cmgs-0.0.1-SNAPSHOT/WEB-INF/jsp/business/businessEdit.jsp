<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext['request'].contextPath}" />

<style>
	.error{color:red}
</style>

<div class="line breadcrumb-box">
	<ol class="breadcrumb">
	  <li ng-class="{active: $index === 2}">
	  	商户管理
	  </li>
	  <li ng-class="{active: $index === 2}">
	  	<a href="${ctx}/business/list">商户列表</a>
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
								<label class="col-sm-2 control-label">商户名</label>
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
								<label class="col-sm-2 control-label">联系人</label>
								<div class="col-md-4">
									<input type="text" class="form-control" ng-model="saveData.contacts" name="contacts" required autofocus ng-maxlength="10" max="10">
								</div>
								<span class="error" ng-show="baseForm.contacts.$dirty && baseForm.contacts.$invalid">*备注:必填,最多10个字符</span>
							</div>
							
							<div class="form-group">
								<label class="col-sm-2 control-label">省</label>
								<div class="col-md-4">
									<input type="text" class="form-control" ng-model="saveData.prov" name="prov" required autofocus ng-maxlength="20" max="20">
								</div>
								<span class="error" ng-show="baseForm.prov.$dirty && baseForm.prov.$invalid">*备注:必填,最多20个字符</span>
							</div>
							
							<div class="form-group">
								<label class="col-sm-2 control-label">市</label>
								<div class="col-md-4">
									<input type="text" class="form-control" ng-model="saveData.city" name="city" required autofocus ng-maxlength="20" max="20">
								</div>
								<span class="error" ng-show="baseForm.city.$dirty && baseForm.city.$invalid">*备注:必填,最多20个字符</span>
							</div>
							
							<div class="form-group">
								<label class="col-sm-2 control-label">区县</label>
								<div class="col-md-4">
									<input type="text" class="form-control" ng-model="saveData.district" name="district" required autofocus ng-maxlength="20" max="20">
								</div>
								<span class="error" ng-show="baseForm.district.$dirty && baseForm.district.$invalid">*备注:必填,最多20个字符</span>
							</div>
							
							<div class="form-group">
								<label class="col-sm-2 control-label">详细地址</label>
								<div class="col-md-4">
									<input type="text" class="form-control" ng-model="saveData.address" name="address" required autofocus ng-maxlength="100" max="100">
								</div>
								<span class="error" ng-show="baseForm.address.$dirty && baseForm.address.$invalid">*备注:必填,100个字符</span>
							</div>
							
							<div class="form-group">
								<label class="col-sm-2 control-label">服务时间</label>
								<div class="col-md-4">
									<input type="text" class="form-control" ng-model="saveData.serviceTime" name="serviceTime" placeholder="格式为06:00-18:00" required autofocus>
								</div>
								<span class="error" ng-show="baseForm.serviceTime.$dirty && baseForm.serviceTime.$invalid">*备注:必填</span>
							</div>
							
							<div class="form-group">
								<label class="col-sm-2 control-label">描述</label>
								<div class="col-md-4">
									<input type="text" class="form-control" ng-model="saveData.desc" name="desc" required autofocus ng-maxlength="200" max="200">
								</div>
								<span class="error" ng-show="baseForm.desc.$dirty && baseForm.desc.$invalid">*备注:必填,200个字符</span>
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
	seajs.use(['js/common/app','js/business/businessEdit'], function() {
		angular.bootstrap(document, [ 'App' ]);
	});
</script>