define(function (require) {
    var http = require('bower_components/angularext/angularExt').http;
    var browserUtils = require("bower_components/utils/BrowserUtils");
    var myApp = angular.module('App');
    require('bower_components/commonList/index');
	
    myApp.controller("mainCtrl", ["$scope", "$http", "$timeout", "CommonList",  function ($scope, $http, $timeout,CommonList) {

    	$scope.saveData = {};
    	$scope.lv1ServiceList = {};
    	$scope.lv2ServiceList = {};
    	
    	$scope.businessList = new CommonList();
    	$scope.businessList.setConfig("/business/list/get/data",{
    		search:{
    			name:''
        	},
            pageSize:10
        });
    	
    	var user = {};

        $scope.init = function(id) {
        	$scope.loadlv1ServiceList();
        	$scope.businessList.fetch();
        	
        	if(id != null) {
        		$http.get(angular.path + "/order/find?id="+id)
        		.success(function(response){
        			response = jQuery.parseJSON(response);
        			if (response.success) {
        				$scope.saveData = response.data;
        				$scope.loadlv2ServiceList($scope.saveData.lv1ServiceType);
        				$scope.saveData.lv1ServiceType = $scope.saveData.lv1ServiceType + '';
        				$scope.saveData.lv2ServiceType = $scope.saveData.lv2ServiceType + '';
        				$scope.saveData.type = $scope.saveData.type + '';
        				$scope.saveData.businessId = $scope.saveData.businessId + '';
        			}
        		});
        	}
        }
        
        $scope.save = function() {
        	$http.post(angular.path + "/order/save",$scope.saveData)
		 	.success(function(response){
		 		response = jQuery.parseJSON(response);
				if (response.success) {
					alert('保存成功');
					window.location.href = angular.path + "/order/list";
				}
		 	});
        }
        
        $scope.loadlv1ServiceList = function() {
        	$http.get(angular.path + "/service/type/lv1")
            .success(function (response) {
            	response = jQuery.parseJSON(response);
            	if (response.success) {
            		angular.forEach(response.data, function (c) {
            			$scope.lv1ServiceList[c.id] = c.name;
					})
				}
            });
        }
        
        $scope.loadlv2ServiceList = function(parent) {
        	$http.get(angular.path + "/service/type/lv2?parent="+parent)
            .success(function (response) {
            	response = jQuery.parseJSON(response);
            	if (response.success) {
            		angular.forEach(response.data, function (c) {
            			$scope.lv2ServiceList[c.id] = c.name;
					})
				}
            });
        }
        
        $scope.selectUserByphone = function(phone) {
        	$http.get(angular.path + "/car/user/find/phone?phone="+$scope.saveData.userPhone)
            .success(function (response) {
            	response = jQuery.parseJSON(response);
            	if (response.success) {
            		user = response.data;
            		if (user == null) {
            			alert('此手机号不存在或未注册，请重新输入');
            			return;
            		}
            		$scope.saveData.userId = user.id;
            		$scope.save();
				}
            });
        }
        
    }]);

});