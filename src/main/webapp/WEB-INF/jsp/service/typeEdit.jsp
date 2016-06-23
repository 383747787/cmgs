<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext['request'].contextPath}" />

<style>
	.error{color:red}
</style>

<div class="line breadcrumb-box">
	<ol class="breadcrumb">
	  <li ng-class="{active: $index === 2}">
	  	服务管理
	  </li>
	  <li ng-class="{active: $index === 2}">
	  	<a href="${ctx}/service/type/list">服务类型</a>
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
								<label class="col-sm-2 control-label">服务类型名称</label>
								<div class="col-md-4">
									<input type="text" class="form-control" ng-model="saveData.name" name="name" required autofocus ng-maxlength="15" max="15">
								</div>
								<span class="error" ng-show="baseForm.name.$dirty && baseForm.name.$invalid">*备注:必填,不超过15个字符</span>
							</div>
							
							<div class="form-group">
								<label class="col-sm-2 control-label">类目级别</label>
								<div class="col-md-4">
									<select class="form-control inline mr10" ng-model="saveData.lv">
										<option value="">未选择</option>
										<option value="1">一级</option>
										<option value="2">二级</option>
									</select>
								</div>
							</div>
							
							<div class="form-group" ng-show="saveData.lv=='2'">
								<label class="col-sm-2 control-label">一级类目</label>
								<div class="col-md-4">
									<select class="form-control inline mr10" ng-options="k as v for (k,v) in lv1List" ng-model="saveData.parent">
									</select>
								</div>
							</div>
							
							<div class="form-group">
								<label class="col-sm-2 control-label">图标</label>
								<div class="col-md-6 form-inline">
				                    <img ng-if="saveData.icon!=''&&!!saveData.icon" ng-src="{{saveData.icon}}" style="width: 100px; height: 100px;">
				                    <div class="m-t-xs">
				                        <button type="button" class="btn btn-primary" accept="image/*" ngf-select="upload($files)"> 选择上传图片</button>
				                    </div>
								</div>
							</div>
							
							<div class="form-group">
								<label class="col-sm-2 control-label">描述</label>
								<div class="col-md-6">
									<summernote config="editConfig" ng-model="saveData.detail" name="detail" required ng-maxlength="800" max="800"></summernote>
									<span class="error" ng-show="baseForm.detail.$dirty && baseForm.detail.$invalid">*备注:必填,不超过800个字符</span>
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
	seajs.use(['js/common/app','js/service/typeEdit'], function() {
		angular.bootstrap(document, [ 'App' ]);
	});
</script>