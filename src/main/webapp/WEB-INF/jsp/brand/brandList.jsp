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

<div class="line breadcrumb-box">
	<ol class="breadcrumb">
	  <li ng-class="{active: $index === 2}">
	  	品牌车型
	  </li>
	  <li ng-class="{active: $index === 2}">
	  	品牌列表
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
									ng-model="brandList.filter.search.name" placeholder="品牌名称" autocomplete="off">
							</div>
							<div class="pull-left mr10">
								<button type="submit" class="btn btn-sm btn-primary"
										ng-click="brandList.search()"
										ng-disabled="form.$invalid">搜索</button>
							</div>
						</form>
					</div>
					<div class="pull-right">
						<a href="javascript:void(0);" ng-click="crawlAll()" class="btn btn-primary btn-mini">同步所有数据</a>
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
							<th>品牌</th>
							<th>首字母</th>
							<th>标记</th>
							<th>状态</th>
							<th>更新时间</th>
							<th>操作</th>
							<th>同步操作</th>
						</thead>
						<tbody>
							<tr ng-repeat="brand in brandList.data.data">
								<td class="alc" ng-bind="brand.name"></td>
								<td class="alc" ng-bind="brand.alphaCode"></td>
								<td class="alc" ng-bind="brand.mark"></td>
								<td class="alc" ng-switch on="brand.isValid">
									<span ng-switch-when="1">启用</span>
									<span ng-switch-when="0">禁用</span>
								</td>
								<td class="alc" ng-bind="brand.modifyTime"></td>
								<td class="alc" ng-switch on="brand.isValid">
									<a href="javascript:void(0);" ng-click="edit(brand)" class="btn btn-primary btn-sm btn-mini">编辑</a>
									<a ng-switch-when="1" href="javascript:void(0);" ng-click="setStatus(brand.id,0)" class="btn btn-danger btn-sm btn-mini delete">禁用</a> 
									<a ng-switch-default href="javascript:void(0);" ng-click="setStatus(brand.id,1)" class="btn btn-primary btn-sm btn-mini delete">启用</a> 
									<a href="${ctx}/brand/series/list?brandId={{brand.id}}" class="btn btn-primary btn-sm btn-mini">车系</a>
								</td>
								<td class="alc">
									<a href="javascript:void(0);" ng-if="brand.isValid==1" ng-click="crawlByBrand(brand.id)" class="btn btn-warning btn-sm btn-mini delete">按品牌同步</a> 
								</td>
							</tr>
							<tr ng-show="brandList.data.data.length==0">
								<td colspan="7" class="text-center">未找到任何记录</td>
							</tr>
						</tbody>
					</table>

					<div class="line">
							<pagination total-items="brandList.data.totalSize" ng-model="brandList.filter.page" 
								ng-change="brandList.fetch()" class="pull-right" previous-text="&lsaquo;" 
								max-size="8" next-text="&rsaquo;" items-per-page="brandList.filter.pageSize"></pagination>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<script type="text/ng-template" id="template.html">
	<form class="form-horizontal" name="methodForm" ng-submit="save(methodForm.$valid)">
	<div class="modal-header">
		<h3>品牌更新</h3>
	</div>
	<div class="modal-body">
			<div class="form-group">
				<label class="control-label pull-left">品牌</label>
			    <div class="pull-left">
			      	 <input type="text" required ng-model="saveData.name" class="form-control input-box" />
			  	</div>
			</div>
			<div class="form-group">
				<label class="control-label pull-left">首字母</label>
			    <div class="pull-left">
			      	<input type="text" required ng-model="saveData.alphaCode" class="form-control input-box" />
			  	</div>
			</div>
			<div class="form-group">
				<label class="control-label pull-left">标记</label>
			    <div class="pull-left">
			      	<input type="text" required ng-model="saveData.mark" class="form-control input-box" />
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
	seajs.use(['js/common/app','js/brand/brandList'], function() {
		angular.bootstrap(document, [ 'App' ]);
	});
</script>