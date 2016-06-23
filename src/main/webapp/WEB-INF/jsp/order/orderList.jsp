<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext['request'].contextPath}" />

<div class="line breadcrumb-box">
	<ol class="breadcrumb">
	  <li ng-class="{active: $index === 2}">
	  	订单管理
	  </li>
	  <li ng-class="{active: $index === 2}">
	  	订单列表
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
									ng-model="orderList.filter.search.businessName" placeholder="商户名" autocomplete="off">
							</div>
							<div class="pull-left mr10">
								<input type="text" class="form-control input-sm ng-pristine ng-valid"
									ng-model="orderList.filter.search.userPhone" placeholder="用户手机号" autocomplete="off">
							</div>
							<div class="pull-left mr10">
								<button type="submit" class="btn btn-sm btn-primary"
										ng-click="orderList.search()"
										ng-disabled="form.$invalid">搜索</button>
							</div>
						</form>
					</div>
					<div class="pull-right">
						<a href="${ctx}/order/edit" class="btn btn-primary btn-mini">添加订单</a>
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
							<th>订单id</th>
							<th>类型</th>
							<th>商户名</th>
							<th>用户手机号</th>
							<th>一级类目</th>
							<th>二级类目</th>
							<th>支付时间</th>
							<th>更新时间</th>
							<th>操作</th>
						</thead>
						<tbody>
							<tr ng-repeat="order in orderList.data.data">
								<td class="alc" ng-bind="order.id"></td>
								<td class="alc" ng-switch on="order.type">
									<span ng-switch-when="1">买服务</span>
									<span ng-switch-when="2">买便宜券</span>
									<span ng-switch-when="3">买年卡</span>
								</td>
								<td class="alc" ng-bind="order.businessName"></td>
								<td class="alc" ng-bind="order.userPhone"></td>
								<td class="alc" ng-bind="order.lv1ServiceName"></td>
								<td class="alc" ng-bind="order.lv2ServiceName"></td>
								<td class="alc" ng-bind="order.paidTime"></td>
								<td class="alc" ng-bind="order.modifyTime"></td>
								<td class="alc">
									<a href="${ctx}/order/edit?id={{order.id}}" class="btn btn-primary btn-sm btn-mini">编辑</a>
									<a href="javascript:void(0);"  ng-click="setStatus(order.id,1)" class="btn btn-danger btn-sm btn-mini delete">删除</a> 
								</td>
							</tr>
							<tr ng-show="orderList.data.data.length==0">
								<td colspan="9" class="text-center">未找到任何记录</td>
							</tr>
						</tbody>
					</table>

					<div class="line">
							<pagination total-items="orderList.data.totalSize" ng-model="orderList.filter.page" 
								ng-change="orderList.fetch()" class="pull-right" previous-text="&lsaquo;" 
								max-size="8" next-text="&rsaquo;" items-per-page="orderList.filter.pageSize"></pagination>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<script>
	seajs.use(['js/common/app','js/order/orderList'], function() {
		angular.bootstrap(document, [ 'App' ]);
	});
</script>