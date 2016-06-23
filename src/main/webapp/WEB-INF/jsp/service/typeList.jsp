<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext['request'].contextPath}" />

<div class="line breadcrumb-box">
	<ol class="breadcrumb">
	  <li ng-class="{active: $index === 2}">
	  	服务管理
	  </li>
	  <li ng-class="{active: $index === 2}">
	  	服务类型
	  </li>
	</ol>
</div>

<div class="row" ng-init="init()">
	<div class="col-lg-12">
		<div class="ibox float-e-margins">
			<div class="ibox-title">
				<div class="row">
					<div class="col-sm-10">
						<form novalidate="" name="form" class="ng-pristine ng-valid">
							<div class="pull-left mr10">
								<select class="form-control inline mr10"
									ng-change="typeList.search()" ng-model="typeList.filter.search.lv">
									<option value="">类目级别</option>
									<option value="1">一级</option>
									<option value="2">二级</option>
								</select>
							</div>
							<div class="pull-left mr10">
								<select class="form-control inline mr10" ng-options="k as v for (k,v) in lv1List"
									ng-change="typeList.search()" ng-model="typeList.filter.search.parent">
								</select>
							</div>
							<div class="pull-left mr10">
								<input type="text" class="form-control input-sm ng-pristine ng-valid"
									ng-model="typeList.filter.search.name" placeholder="服务类型" autocomplete="off"
									ng-change="typeList.search()">
							</div>
						</form>
					</div>
					<div class="pull-right">
						<a href="${ctx}/service/type/edit" class="btn btn-primary btn-mini">添加服务种类</a>
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
							<th>ID</th>
							<th>服务类型</th>
							<th>图标</th>
							<th>类目级别</th>
							<th>一级类目</th>
							<th>详情</th>
							<th>状态</th>
							<th>操作</th>
						</thead>
						<tbody>
							<tr ng-repeat="type in typeList.data.data">
								<td class="alc" ng-bind="type.id"></td>
								<td class="alc" ng-bind="type.name"></td>
								<td class="alc text-center">
									<img style="width:50px;" ng-if="type.icon!=null&&type.icon.length>0" ng-src="{{type.icon}}"/>
								</td>
								<td class="alc" ng-bind="type.lv"></td>
								<td class="alc" ng-bind="type.parent"></td>
								<td class="alc" ng-bind="type.detail"></td>
								<td class="alc" ng-switch on="type.isValid">
									<span ng-switch-when="1">启用</span>
									<span ng-switch-when="0">禁用</span>
								</td>
								<td class="alc" ng-switch on="type.isValid">
									<a ng-switch-when="1" href="javascript:void(0);" ng-click="setStatus(type.id,0)" class="btn btn-danger btn-sm btn-mini delete">禁用</a> 
									<a ng-switch-default href="javascript:void(0);" ng-click="setStatus(type.id,1)" class="btn btn-primary btn-sm btn-mini delete">启用</a>
									<a href="${ctx}/service/type/edit?id={{type.id}}" class="btn btn-primary btn-sm btn-mini">编辑</a> 
								</td>
							</tr>
							<tr ng-show="typeList.data.data.length==0">
								<td colspan="8" class="text-center">未找到任何记录</td>
							</tr>
						</tbody>
					</table>

					<div class="line">
							<pagination total-items="typeList.data.totalSize" ng-model="typeList.filter.page" 
								ng-change="typeList.fetch()" class="pull-right" previous-text="&lsaquo;" 
								max-size="8" next-text="&rsaquo;" items-per-page="typeList.filter.pageSize"></pagination>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<script>
	seajs.use(['js/common/app','js/service/typeList'], function() {
		angular.bootstrap(document, [ 'App' ]);
	});
</script>