<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext['request'].contextPath}" />

<div class="line breadcrumb-box">
	<ol class="breadcrumb">
	  <li ng-class="{active: $index === 2}">
	  	客户管理
	  </li>
	  <li ng-class="{active: $index === 2}">
	  	车主列表
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
									ng-model="carUserList.filter.search.name" placeholder="车主用户名" autocomplete="off">
							</div>
							<div class="pull-left mr10">
								<button type="submit" class="btn btn-sm btn-primary"
										ng-click="carUserList.search()"
										ng-disabled="form.$invalid">搜索</button>
							</div>
						</form>
					</div>
					<div class="pull-right">
						<a href="${ctx}/car/user/edit;" class="btn btn-primary btn-mini">添加车主</a>
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
							<th>邮箱</th>
							<th>车牌号</th>
							<th>注册时间</th>
							<th>操作</th>
						</thead>
						<tbody>
							<tr ng-repeat="carUser in carUserList.data.data">
								<td class="alc" ng-bind="carUser.name"></td>
								<td class="alc" ng-bind="carUser.phone"></td>
								
								<td class="alc text-center">
									<img style="width:50px;" ng-if="carUser.avatar!=null&&carUser.avatar.length>0" ng-src="{{carUser.avatar}}"/>
								</td>
								<td class="alc" ng-bind="carUser.chatMail"></td>
								<td class="alc" ng-bind="carUser.carNum"></td>
								<td class="alc" ng-bind="carUser.createTime"></td>
								<td class="alc">
									<a href="${ctx}/car/user/edit?id={{carUser.id}}" class="btn btn-primary btn-sm btn-mini">编辑</a>
									<a href="javascript:void(0);" ng-click="setStatus(carUser.id,0)" class="btn btn-danger btn-sm btn-mini delete">删除</a> 
								</td>
							</tr>
							<tr ng-show="carUserList.data.data.length==0">
								<td colspan="7" class="text-center">未找到任何记录</td>
							</tr>
						</tbody>
					</table>

					<div class="line">
							<pagination total-items="carUserList.data.totalSize" ng-model="carUserList.filter.page" 
								ng-change="carUserList.fetch()" class="pull-right" previous-text="&lsaquo;" 
								max-size="8" next-text="&rsaquo;" items-per-page="carUserList.filter.pageSize"></pagination>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<script>
	seajs.use(['js/common/app','js/car/userList'], function() {
		angular.bootstrap(document, [ 'App' ]);
	});
</script>