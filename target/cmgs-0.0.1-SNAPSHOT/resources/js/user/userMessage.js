define(function (require) {
    var http = require('bower_components/angularext/angularExt').http;
    var browserUtils = require("bower_components/utils/BrowserUtils");
    var myApp = angular.module('App');
    require('bower_components/commonList/index');
	
    myApp.controller("mainCtrl", ["$scope", "$http", "$timeout", "CommonList",  function ($scope, $http, $timeout,CommonList) {

    	$scope.saveData = {};
    	$scope.isEdit = false;
    	$scope.isChangePwd = false;
    	$scope.oldPassword = '';
    	$scope.newPassword1 = '';
    	$scope.newPassword2 = '';
    	
        $scope.init = function(id) {
        	if (id != null) {
        		$http.get(angular.path + "/user/message/find?id="+id)
        		.success(function(response){
        			response = jQuery.parseJSON(response);
        			if (response.success) {
        				$scope.saveData = response.data;
        			}
        		});
			}
        }
        
        $scope.edit = function() {
        	$scope.isEdit = true;
        }
        
        $scope.changePwd = function() {
        	$scope.isChangePwd = true;
        }
        
        $scope.save = function() {
        	console.log($scope.saveData.password);
        	console.log($scope.oldPassword);
        	console.log($scope.newPassword1);
        	console.log($scope.newPassword2);
        	
        	if ($scope.saveData.name==null || $scope.saveData.name=='') {
        		alert('用户名不能为空');
        		return;
        	}
        	if ($scope.saveData.realName==null || $scope.saveData.realName=='') {
        		alert('真实姓名不能为空');
        		return;
        	}
        	if ($scope.saveData.email==null || $scope.saveData.email=='') {
        		alert('邮箱不能为空');
        		return;
        	}
        	if ($scope.isChangePwd) {
				if ($scope.oldPassword==null || $scope.oldPassword=='') {
					alert('原密码不能为空');
					return;
				}
				if ($scope.newPassword1==null || $scope.newPassword1=='') {
					alert('新密码不能为空');
					return;
				}
				if ($scope.newPassword2==null || $scope.newPassword2=='') {
					alert('请再次输入新密码');
					return;
				}
				if ($scope.oldPassword != $scope.saveData.password) {
					alert('原密码输入不正确');
					return;
				}
				if ($scope.newPassword1 != $scope.newPassword2) {
					alert('两次输入的新密码不一致');
					return;
				}
			}
        	
        	$scope.saveData.password = $scope.newPassword1;
        	$http.post(angular.path + "/user/message/save",$scope.saveData)
		 	.success(function(response){
		 		response = jQuery.parseJSON(response);
				if (response.success) {
					alert('保存成功');
					window.location.href = angular.path + "/user/message";
				}
		 	});
        }

    }]);

});