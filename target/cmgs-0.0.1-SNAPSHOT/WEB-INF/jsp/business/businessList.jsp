<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext['request'].contextPath}" />

<div class="line breadcrumb-box">
	<ol class="breadcrumb">
	  <li ng-class="{active: $index === 2}">
	  	商户管理
	  </li>
	  <li ng-class="{active: $index === 2}">
	  	商户列表
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
									ng-model="businessList.filter.search.name" placeholder="商户名" autocomplete="off">
							</div>
							<div class="pull-left mr10">
								<input type="text" class="form-control input-sm ng-pristine ng-valid"
									ng-model="businessList.filter.search.city" placeholder="城市" autocomplete="off">
							</div>
							<div class="pull-left mr10">
								<button type="submit" class="btn btn-sm btn-primary"
										ng-click="businessList.search()"
										ng-disabled="form.$invalid">搜索</button>
							</div>
						</form>
					</div>
					<div class="pull-right">
						<a href="${ctx}/business/edit" class="btn btn-primary btn-mini">添加商户</a>
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
							<th>名称</th>
							<th>手机号</th>
							<th>头像</th>
							<th>联系人</th>
							<th>城市</th>
							<th>地区</th>
							<th>服务时间</th>
							<th>描述</th>
							<th>操作</th>
						</thead>
						<tbody>
							<tr ng-repeat="business in businessList.data.data">
								<td class="alc" ng-bind="business.name"></td>
								<td class="alc" ng-bind="business.phone"></td>
								<td class="alc text-center">
									<img style="width:80px;" ng-if="business.avatar!=null&&business.avatar.length>0" ng-src="{{business.avatar}}"/>
								</td>
								<td class="alc" ng-bind="business.contacts"></td>
								<td class="alc" ng-bind="business.city"></td>
								<td class="alc" ng-bind="business.district"></td>
								<td class="alc" ng-bind="business.serviceTime"></td>
								<td class="alc" ng-bind="business.desc"></td>
								<td class="alc">
									<a href="${ctx}/business/edit?id={{business.id}}" class="btn btn-primary btn-sm btn-mini">编辑</a>
									<a href="javascript:void(0);"  ng-click="setStatus(business.id,1)" class="btn btn-danger btn-sm btn-mini delete">删除</a> 
								</td>
							</tr>
							<tr ng-show="businessList.data.data.length==0">
								<td colspan="9" class="text-center">未找到任何记录</td>
							</tr>
						</tbody>
					</table>

					<div class="line">
							<pagination total-items="businessList.data.totalSize" ng-model="businessList.filter.page" 
								ng-change="businessList.fetch()" class="pull-right" previous-text="&lsaquo;" 
								max-size="8" next-text="&rsaquo;" items-per-page="businessList.filter.pageSize"></pagination>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<script>
	seajs.use(['js/common/app','js/business/businessList'], function() {
		angular.bootstrap(document, [ 'App' ]);
	});
</script>