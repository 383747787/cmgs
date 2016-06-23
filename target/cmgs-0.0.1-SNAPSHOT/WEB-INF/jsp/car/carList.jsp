<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext['request'].contextPath}" />

<div class="line breadcrumb-box">
	<ol class="breadcrumb">
	  <li ng-class="{active: $index === 2}">
	  	客户管理
	  </li>
	  <li ng-class="{active: $index === 2}">
	  	车辆列表
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
									ng-model="carList.filter.search.carNum" placeholder="车牌号" autocomplete="off">
							</div>
							<div class="pull-left mr10">
								<button type="submit" class="btn btn-sm btn-primary"
										ng-click="carList.search()"
										ng-disabled="form.$invalid">搜索</button>
							</div>
						</form>
					</div>
					<div class="pull-right">
						<a href="${ctx}/car/edit" class="btn btn-primary btn-mini">添加车辆</a>
					</div>
				</div>
			</div>
			<div class="ibox-content avatars-box">
				<div ng-class="option.success?'alert alert-success':'alert alert-danger'" ng-if="option.msg!=null&&option.msg!=''" role="alert">
					<span ng-bind="option.msg"></span>
				</div>
				<div class="row">
					<table class="table table-bordered table-striped">
						<thead>
							<th>车牌号</th>
							<th>品牌</th>
							<th>车系</th>
							<th>车型</th>
							<th>里程数</th>
							<th>车架号</th>
							<th>创建时间</th>
							<th>操作</th>
						</thead>
						<tbody>
							<tr ng-repeat="car in carList.data.data">
								<td class="alc" ng-bind="car.carNum"></td>
								<td class="alc" ng-bind="car.brandName"></td>
								<td class="alc" ng-bind="car.seriesName"></td>
								<td class="alc" ng-bind="car.modelName"></td>
								<td class="alc" ng-bind="car.kilometers"></td>
								<td class="alc" ng-bind="car.vehicleFrameNo"></td>
								<td class="alc" ng-bind="car.createTime"></td>
								<td class="alc">
									<a href="${ctx}/car/edit?id={{car.id}}" class="btn btn-primary btn-sm btn-mini">编辑</a>
									<a href="javascript:void(0);"  ng-click="setStatus(car.id,1)" class="btn btn-danger btn-sm btn-mini delete">删除</a> 
								</td>
							</tr>
							<tr ng-show="carList.data.data.length==0">
								<td colspan="8" class="text-center">未找到任何记录</td>
							</tr>
						</tbody>
					</table>

					<div class="line">
							<pagination total-items="carList.data.totalSize" ng-model="carList.filter.page" 
								ng-change="carList.fetch()" class="pull-right" previous-text="&lsaquo;" 
								max-size="8" next-text="&rsaquo;" items-per-page="carList.filter.pageSize"></pagination>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<script>
	seajs.use(['js/common/app','js/car/carList'], function() {
		angular.bootstrap(document, [ 'App' ]);
	});
</script>