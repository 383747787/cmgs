<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext['request'].contextPath}" />

<style>
	.error{color:red}
</style>

<div class="line breadcrumb-box">
	<ol class="breadcrumb">
	  <li ng-class="{active: $index === 2}">
	  	订单管理
	  </li>
	  <li ng-class="{active: $index === 2}">
	  	<a href="${ctx}/order/list">订单列表</a>
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
								<label class="col-sm-2 control-label">类型</label>
								<div class="col-md-4">
									<select class="form-control inline mr10" ng-model="saveData.type">
										<option value="">未选择</option>
										<option value="1">买服务</option>
										<option value="2">买便宜券</option>
										<option value="3">买年卡</option>
									</select>
								</div>
							</div>
							
							<div class="form-group">
								<label class="col-sm-2 control-label">一级类目</label>
								<div class="col-md-4">
									<select class="form-control inline mr10" ng-options="k as v for (k,v) in lv1ServiceList" ng-model="saveData.lv1ServiceType" ng-change="loadlv2ServiceList(saveData.lv1ServiceType)">
									</select>
								</div>
							</div>
							
							<div class="form-group" ng-show="saveData.lv1ServiceType!=null && saveData.lv1ServiceType.length>0">
								<label class="col-sm-2 control-label">二级类目</label>
								<div class="col-md-4">
									<select class="form-control inline mr10" ng-options="k as v for (k,v) in lv2ServiceList" ng-model="saveData.lv2ServiceType">
									</select>
								</div>
							</div>
							
							<div class="form-group">
								<label class="col-sm-2 control-label">用户手机号</label>
								<div class="col-md-4">
									<input type="text" class="form-control" ng-model="saveData.userPhone" name="userPhone" required autofocus ng-maxlength="11" max="11">
								</div>
								<span class="error" ng-show="baseForm.userPhone.$dirty && baseForm.userPhone.$invalid">*备注:必填,11个字符</span>
							</div>
							
							<div class="form-group">
								<label class="col-sm-2 control-label">商户名</label>
								<div class="col-sm-10">
									<div class="row">
										<div class="col-sm-4">
											<table class="table">
												<thead>
													<tr>
														<th colspan="10">可选择的商家列表</th>
													</tr>
													<tr>
														<th colspan="10"><input placeholder="请输入商家名称" class="input-sm form-control" 
															ng-model="businessList.filter.search.name" ng-change="businessList.search()"/></th>
													</tr>
												</thead>
												<tbody>
													<tr ng-repeat="business in businessList.data.data">
														<td><input type="radio" ng-model="saveData.businessId" value="{{business.id}}" name="business.id"></input></td>
														<td ng-bind="business.name"></td>
													</tr>
												</tbody>
												<tfoot>
													<tr>
														<td colspan="10" class="text-center">
															<div>
																<pagination total-items="businessList.data.totalSize" ng-model="businessList.filter.page" 
																	ng-change="businessList.fetch()" class="pull-right" previous-text="&lsaquo;" 
																	max-size="6" next-text="&rsaquo;" items-per-page="businessList.filter.pageSize"></pagination>
															</div>
														</td>
													</tr>
												</tfoot>
											</table>
										</div>
									</div>
								</div>
							</div>
							
						</form>
					</div>
				</div>
				
				<div class="form-group">
					<div class="col-sm-4 col-sm-offset-2">
						<input type="button" ng-disabled="baseForm.$invalid" ng-click="selectUserByphone()" class="btn btn-primary" value="保存">
					</div>
				</div>
				
			</div>
		</div>
	</div>
</div>

<script>
	seajs.use(['js/common/app','js/order/orderEdit'], function() {
		angular.bootstrap(document, [ 'App' ]);
	});
</script>