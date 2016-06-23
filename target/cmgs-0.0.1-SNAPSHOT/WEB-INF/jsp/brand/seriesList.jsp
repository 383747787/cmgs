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

<div class="line breadcrumb-box">
	<ol class="breadcrumb">
	  <li ng-class="{active: $index === 2}">
	  	品牌车型
	  </li>
	  <li ng-class="{active: $index === 2}">
	  	<a href="${ctx}/brand/list">品牌列表</a>
	  </li>
	  <li ng-class="{active: $index === 2}">
	  	车系列表
	  </li>
	</ol>
</div>

<div class="row">
	<div class="col-lg-12">
		<div class="ibox float-e-margins">
			<div class="ibox-title">
				<div class="row">
					<div class="col-sm-10">
						<form novalidate="" name="form" class="ng-pristine ng-valid">
							<div class="pull-left mr10">
								<input type="text" class="form-control input-sm ng-pristine ng-valid"
									ng-model="seriesList.filter.search.name" placeholder="车系名称" autocomplete="off"> 
							</div>
							<div class="pull-left mr10">
								<button type="submit" class="btn btn-sm btn-primary"
									ng-click="seriesList.search()"
									ng-disabled="form.$invalid">搜索</button>
							</div>
						</form>
					</div>
				</div>
			</div>
			<div class="ibox-content icons-box">
				<div ng-class="option.success?'alert alert-success':'alert alert-danger'" ng-if="option.msg!=null&&option.msg!=''" role="alert">
					<span ng-bind="option.msg"></span>
				</div>
				<div class="row">
					<table class="table table-bordered table-striped">
						<thead>
							<th>车系</th>
							<th>售价</th>
							<th>洗车类型</th>
							<th>参考车型</th>
							<th>大小车型</th>
							<th>标记</th>
							<th>状态</th>
							<th>更新时间</th>
							<th>操作</th>
							<th>同步操作</th>
						</thead>
						<tbody>
							<tr ng-repeat="series in seriesList.data.data">
								<td class="alc" ng-bind="series.name"></td>
								<td class="alc">{{series.guidePriceLow}}-{{series.guidePriceHigh}}</td>
								<td class="alc" ng-switch on="series.washCarType">
									<span ng-switch-when="1">普通车</span>
									<span ng-switch-when="2">SUV</span>
									<span ng-switch-when="3">七座</span>
									<span ng-switch-when="4">微面</span>
								</td>
								<td class="alc" ng-bind="series.classify"></td>
								<td class="alc" ng-switch on="series.washType">
									<span ng-switch-when="1">小车</span>
									<span ng-switch-when="2">大车</span>
								</td>
								<td class="alc"ng-bind="series.mark"></td>
								<td class="alc">{{series.isValid == '1'?'启用':'禁用'}}</td>
								<td class="alc" ng-bind="series.modifyTime"></td>
								<td class="alc" ng-switch on="series.isValid">
									<a href="javascript:void(0);" ng-click="edit(series)" class="btn btn-primary btn-sm btn-mini">编辑</a>
									<a ng-switch-when="1" href="javascript:void(0);"  ng-click="setStatus(series.id,0)" class="btn btn-danger btn-sm btn-mini delete">禁用</a> 
									<a ng-switch-default href="javascript:void(0);" ng-click="setStatus(series.id,1)" class="btn btn-primary btn-sm btn-mini delete">启用</a>
									<a class="btn btn-primary btn-sm btn-mini" href="${ctx}/brand/model/list?brandId=${brandId}&seriesId={{series.id}}">车型</a>
								</td>
								<td class="alc">
									<a href="javascript:void(0);" ng-if="series.isValid==1" ng-click="crawlBySeries(series.id)" class="btn btn-warning btn-sm btn-mini">按车系同步
							</tr>
							<tr ng-show="seriesList.data.data.length==0">
								<td colspan="10" class="text-center">未找到任何记录</td>
							</tr>
						</tbody>
					</table>
					
					<div class="line">
							<pagination total-items="seriesList.data.totalSize" ng-model="seriesList.filter.page" 
								ng-change="seriesList.fetch()" class="pull-right" previous-text="&lsaquo;" 
								max-size="8" next-text="&rsaquo;" items-per-page="seriesList.filter.pageSize"></pagination>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<script type="text/ng-template" id="template.html">
	<form class="form-horizontal" name="methodForm" ng-submit="save(methodForm.$valid)">
	<div class="modal-header">
		<h3>车系更新</h3>
	</div>
	<div class="modal-body">
			<div class="form-group">
				<label class="control-label pull-left">车系</label>
			    <div class="pull-left">
			      	 <input type="text" required ng-model="saveData.name" class="form-control input-box" />
			  	</div>
			</div>
			<div class="form-group">
				<label class="control-label pull-left">标记</label>
			    <div class="pull-left">
			      	 <input type="text" required ng-model="saveData.mark" class="form-control input-box" />
			  	</div>
			</div>
			<div class="form-group">
				<label class="control-label pull-left">售价</label>
			    <div class="pull-left">
			      	<input type="text" required placeholder="最低价" ng-model="saveData.guidePriceLow" class="form-control input-min pull-left mr10" /> 
			      	<input type="text" required placeholder="最高价" ng-model="saveData.guidePriceHigh" class="form-control input-min pull-left mr10" />
			  	</div>
			</div>
			<div class="form-group">
				<label class="control-label pull-left">参考车型</label>
			    <div class="pull-left">
			      	 <input type="text" required ng-model="saveData.classify" class="form-control input-box" />
			  	</div>
			</div>
            <div class="form-group">
				<label class="control-label pull-left">洗车类型</label>
			    <div class="pull-left">
			      <select class="form-control" ng-model="saveData.washCarType"
					ng-options="washType.id as washType.name for washType in washCarTypeArray">
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
	</div>
	<div class="modal-footer">
		<span ng-if="option.saveErrorMsg!=null&&option.saveErrorMsg!=''"><span ng-bind="option.saveErrorMsg"></span></span>
		<button type="submit" class="btn btn-primary" >确认</button>
		<button type="button" class="btn btn-warning" ng-click="cancel()">取消</button>
	</div>
	</form>
</script>

<script>
	seajs.use(['js/common/app','js/brand/seriesList'], function() {
		angular.bootstrap(document, [ 'App' ]);
	});
</script>