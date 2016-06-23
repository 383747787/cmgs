<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext['request'].contextPath}" />

<style type="text/css">
	.select-box,.input-box{
		width:200px;
	}
	.input-boxs{
		padding-bottom: 60px;
	}
	.input-min{
		width:100px;
	}
	.control-label{
		width:100px;
		padding-right: 10px;
	}
	body.modal-open{
		overflow-y: hidden !important;
	}
	input.ng-invalid {
		border: 1px solid #e5e6e7;
	}
	input.ng-valid{
		border:1px solid #e5e6e7;
	}
	.red{
		color: red;
	}
	.mr10{
		margin: 0 10px 0 0;
	}
	.image{
		width: 80px;
	}

	.imgs-container{
		position: fixed;
		z-index: 99999;
		background: #fff;
		border:1px solid #ddd;
		width: 545px;
	}
	.imgs-container .imgs-box{
		padding:25px 40px 10px;
	}
	.imgs-container .imgs-box li{
		float: left;
		width: 85px;
		height: 85px;
		text-align: center;
		line-height: 85px;
		margin: 0 25px 15px 0;
		border: 2px solid #fff;
	}
	.imgs-container .imgs-box ul{
		max-height: 310px;
		overflow: auto;
	}
	.imgs-container .imgs-box img{
		max-height: 100%;
		max-width: 100%;
	}
	.imgs-container .btn-box{
		padding: 10px;
	}
	.imgs-container .imgs-box li.active{
		border: 2px solid #f8ac59;
	}
	.wrapper-content{
		position: relative;
	}
</style>

<input id="brandId" type="hidden" value="${brandId}" />
<input id="seriesId" type="hidden" value="${seriesId}" />

<div class="line breadcrumb-box">
	<ol class="breadcrumb">
	  <li ng-class="{active: $index === 2}">
	  	品牌车型
	  </li>
	  <li ng-class="{active: $index === 2}">
	  	<a href="${ctx}/brand/list">品牌列表</a>
	  </li>
	  <li ng-class="{active: $index === 2}">
	  	<a href="${ctx}/brand/series/list?brandId=${brandId}">车系列表</a>
	  </li>
	  <li ng-class="{active: $index === 2}">
	  	车型列表
	  </li>
	</ol>
</div>

<div class="row">
	<div class="col-lg-12">
		<div class="ibox float-e-margins">
			<div class="ibox-title clearfix">
				<form name="form">
					<div class="clearfix">
						<div class="pull-left mr10">
							<input type="text" ng-model="modelList.filter.search.name" placeholder="车型名称" class="form-control input-sm"/>
					  	</div>
					  	<div class="pull-left mr10">
							<input type="text" ng-model="modelList.filter.search.displacement" placeholder="排量" class="form-control input-sm"/>
					  	</div>
						<div class="pull-left mr10">
							<input type="text" ng-model="modelList.filter.search.carEngineType" placeholder="发动机型号" class="form-control input-sm"/>
					  	</div>
					  	<div class="pull-left">
							<button type="button" ng-click="search()" class="btn btn-primary btn-sm">搜索</button>
						</div>
					</div>
				</form>
			</div>
			<div class="ibox-content icons-box">
				<div ng-class="option.success?'alert alert-success':'alert alert-danger'" ng-if="option.msg!=null&&option.msg!=''" role="alert">
					<span ng-bind="option.msg"></span>
				</div>
				<table class="table table-bordered table-striped">
					<thead>
						<th class="text-center">车型</th>
						<th class="text-center">洗车车型</th>
						<th class="text-center">大小车型</th>
						<th class="text-center">照片</th>
						<th class="text-center">发动机</th>
						<th class="text-center">厂商指导价</th>
						<th class="text-center">整备质量</th>
						<th class="text-center">座位数</th>
						<th class="text-center">轮胎规格</th>
						<th class="text-center">状态</th>
						<th class="text-center">更新时间</th>
						<th class="text-center">操作</th>
					</thead>
					<tbody>
						<tr ng-repeat="model in modelList.data.data">
							<td class="alc text-center"><span ng-bind="model.name"></span></td>
							<td class="alc text-center"  ng-switch on="model.modelWashType">
								<span ng-switch-when="1">普通车</span>
								<span ng-switch-when="2">SUV</span>
								<span ng-switch-when="3">七座</span>
								<span ng-switch-when="4">微面</span>
							</td>
							<td class="alc text-center"  ng-switch on="model.washType">
								<span ng-switch-when="1">小车</span>
								<span ng-switch-when="2">大车</span>
							</td>
							<td class="alc text-center">
								<img style="width:85px;" ng-if="model.imgUrl!=null&&model.imgUrl.length>0" ng-src="{{model.imgUrl}}"/>
							</td>
							<td class="alc text-center">
								<span ng-bind="model.displacement"></span>T&nbsp;
								<span ng-bind="model.carHorsepower"></span>马力&nbsp;
								<span ng-bind="model.carEngineType"></span>
							</td>
							<td class="alc text-center"><span ng-bind="model.guidePrice"></span><span ng-if="model.guidePrice>0">万</span></td>
							<td class="alc text-center"><span ng-bind="model.carValue"></span></td>
							<td class="alc text-center"><span ng-bind="model.carSeats"></span></td>
							<td class="alc text-center">
								<span ng-bind="model.frontTyreSize"></span><br/>
								<span ng-bind="model.rearTyreSize"></span>
							</td>
							<td class="alc text-center" ng-switch="model.isValid">
								<span ng-switch-when="0">禁用</span>
								<span ng-switch-when="1">启用</span>
							</td>
							<td class="alc text-center"><span ng-bind="model.modifyTime | date:'yyyy-MM-dd HH:mm:ss'"></span></td>
							<td class="alc text-center">	
								<a ng-if="model.isValid==1" href="javascript:void(0);"  ng-click="setStatus(model.id,0)" class="btn btn-danger btn-sm btn-mini delete">禁用</a> 
								<a ng-if="model.isValid==0"href="javascript:void(0);" ng-click="setStatus(model.id,1)" class="btn btn-primary btn-sm btn-mini delete">启用</a>
								<a href="javascript:void(0);" ng-click="edit(model)" class="btn btn-primary btn-sm btn-mini">编辑</a>
							</td>
						</tr>
						<tr>
							<td colspan="13">
								<pagination total-items="modelList.data.totalSize" ng-model="modelList.filter.page" 
								ng-change="modelList.fetch()" class="pull-right" previous-text="&lsaquo;" 
								max-size="8" next-text="&rsaquo;" items-per-page="modelList.filter.pageSize"></pagination>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</div>

<script type="text/ng-template" id="template.html">
	<form class="form-horizontal" name="methodForm" ng-submit="save(methodForm.$valid)">
	<div class="modal-header">
		<h3>车型更新</h3>
	</div>
	<div class="modal-body">
			<div class="form-group">
				<label class="control-label pull-left">车型</label>
			    <div class="pull-left">
			      	 <input type="text" required ng-model="saveData.name" class="form-control input-box" />
			  	</div>
			</div>
            <div class="form-group">
				<label class="control-label pull-left">洗车类型</label>
			    <div class="pull-left">
			      <select class="form-control" ng-model="saveData.modelWashType" 
				   ng-options="washType.id as washType.name for washType in modelWashTypeArray">
				  </select>
			  	</div>
			</div>
         	<div class="form-group">
				<label class="control-label pull-left">大小车型</label>
			    <div class="pull-left">
			      <select class="form-control" ng-model="saveData.washType" 
				   ng-options="washCarType.id as washCarType.name for washCarType in WashTypeArray">
				  </select>
			  	</div>
			</div>
			<div class="form-group">
				<label class="control-label pull-left">照片</label>
			    <div class="pull-left">
			    	<img style="width:100px;" ng-if="saveData.imgUrl!=null&&saveData.imgUrl.length>0" ng-src="{{saveData.imgUrl}}" class="image mr10"/>
			    	<a href="javascript:void(0);" ngf-select="upload($files)" class="btn btn-primary mr10">修改图片</a>
					<a href="javascript:void(0);" ng-click="goAutoHomeImg(saveData)">进入汽车之家图片库</a>
			  	</div>
			</div>
			<div class="form-group">
				<label class="control-label pull-left">发动机</label>
			    <div class="pull-left">
			      	<input type="text" required placeholder="排量" ng-model="saveData.displacement" class="form-control input-min pull-left mr10" /> 
			      	<input type="text" required placeholder="马力" ng-model="saveData.carHorsepower" class="form-control input-min pull-left mr10" />
			      	<input type="text" required placeholder="气缸" ng-model="saveData.carEngineType" class="form-control input-min pull-left mr10" />
			  	</div>
			</div>
			<div class="form-group">
				<label class="control-label pull-left">厂商指导价</label>
			    <div class="pull-left">
			      	<input type="text" required ng-model="saveData.guidePrice" class="form-control input-box" />
			  	</div>
			</div>
			<div class="form-group">
				<label class="control-label pull-left">座位数</label>
			    <div class="pull-left">
			      	<input type="text" required ng-model="saveData.carSeats" class="form-control input-box" />
			  	</div>
			</div>
			<div class="form-group">
				<label class="control-label pull-left">整备质量</label>
			    <div class="pull-left mr10">
			      	<input type="text" ng-model="saveData.carValue" required class="form-control input-box" />
			  	</div>
				<div class="pull-left">
					<a href="javascript:void(0);" ng-click="goAutoHomeConfig(saveData)">进入汽车之家车型配置</a>
			  	</div>
			</div>
			<div class="form-group">
				<label class="control-label pull-left">前胎规格</label>
			    <div class="pull-left">
			      	<input type="text" ng-model="saveData.frontTyreSize" required placeholder="面宽/扁平比/直径" class="form-control input-box" />
			  	</div>
			</div>
			<div class="form-group">
				<label class="control-label pull-left">后胎规格</label>
			    <div class="pull-left">
			      	<input type="text" ng-model="saveData.rearTyreSize" required placeholder="面宽/扁平比/直径" class="form-control input-box" />
			  	</div>
			</div>
	</div>
	<div class="modal-footer">
		<span ng-if="option.saveErrorMsg!=null&&option.saveErrorMsg!=''"><span ng-bind="option.saveErrorMsg"></span></span>
		<button type="submit" class="btn btn-primary" >确认</button>
		<button type="button" class="btn btn-warning" ng-click="cancel()">取消</button>
	</div>
	</form>
</script>

<script>
	seajs.use(['js/common/app','js/brand/modelList'], function() {
		angular.bootstrap(document, [ 'App' ]);
	});
</script>
