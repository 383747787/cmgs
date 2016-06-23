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
	  	<a href="${ctx}/car/list">车辆列表</a>
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
								<label class="col-sm-2 control-label">车牌号</label>
								<div class="col-md-4">
									<input type="text" class="form-control" ng-model="saveData.carNum" name="carNum" required autofocus ng-maxlength="7" max="7">
								</div>
								<span class="error" ng-show="baseForm.carNum.$dirty && baseForm.carNum.$invalid">*备注:必填,7个字符</span>
							</div>
							
							<div class="form-group">
								<label class="col-sm-2 control-label">里程数</label>
								<div class="col-md-4">
									<input type="text" class="form-control" ng-model="saveData.kilometers" name="kilometers" required autofocus>
								</div>
								<span class="error" ng-show="baseForm.kilometers.$dirty && baseForm.kilometers.$invalid">*备注:必填</span>
							</div>
							
							<div class="form-group">
								<label class="col-sm-2 control-label">车架号</label>
								<div class="col-md-4">
									<input type="text" class="form-control" ng-model="saveData.vehicleFrameNo" name="vehicleFrameNo" required autofocus ng-maxlength="11" max="11">
								</div>
								<span class="error" ng-show="baseForm.vehicleFrameNo.$dirty && baseForm.vehicleFrameNo.$invalid">*备注:必填,11个字符</span>
							</div>
							
							<div class="form-group">
								<label class="col-sm-2 control-label">用户id</label>
								<div class="col-md-4">
									<input type="text" class="form-control" ng-model="saveData.userId" name="userId" required autofocus ng-maxlength="11" max="11">
								</div>
								<span class="error" ng-show="baseForm.userId.$dirty && baseForm.userId.$invalid">*备注:必填,11个字符</span>
							</div>
							
							<div class="form-group">
								<label class="col-sm-2 control-label">品牌</label>
								<div class="col-md-4">
									<select class="form-control inline mr10" ng-options="k as v for (k,v) in brandList" ng-model="saveData.brandId" ng-change="loadSeriesList(saveData.brandId)">
									</select>
								</div>
							</div>
							
							<div class="form-group">
								<label class="col-sm-2 control-label">车系</label>
								<div class="col-md-4">
									<select class="form-control inline mr10" ng-options="k as v for (k,v) in seriesList" ng-model="saveData.seriesId" ng-change="loadModelList(saveData.seriesId)">
									</select>
								</div>
							</div>
							
							<div class="form-group">
								<label class="col-sm-2 control-label">车型</label>
								<div class="col-md-4">
									<select class="form-control inline mr10" ng-options="k as v for (k,v) in modelList" ng-model="saveData.modelId">
									</select>
								</div>
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
	seajs.use(['js/common/app','js/car/carEdit'], function() {
		angular.bootstrap(document, [ 'App' ]);
	});
</script>