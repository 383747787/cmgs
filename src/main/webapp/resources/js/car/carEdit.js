define(function (require) {
    var http = require('bower_components/angularext/angularExt').http;
    var browserUtils = require("bower_components/utils/BrowserUtils");
    var myApp = angular.module('App');
    require('bower_components/commonList/index');
	
    myApp.controller("mainCtrl", ["$scope", "$http", "$timeout", "CommonList",  function ($scope, $http, $timeout,CommonList) {

    	$scope.saveData = {};
    	$scope.brandList = {};
    	$scope.seriesList = {};
    	$scope.modelList = {};
    	
        $scope.init = function(id) {
        	$scope.loadBrandList();
        	
        	if (id != null) {
        		$http.get(angular.path + "/car/find?id="+id)
        		.success(function(response){
        			response = jQuery.parseJSON(response);
        			if (response.success) {
        				$scope.saveData = response.data;
        				$scope.loadSeriesList($scope.saveData.brandId);
        				$scope.loadModelList($scope.saveData.seriesId);
        				$scope.saveData.brandId = $scope.saveData.brandId + '';
        				$scope.saveData.seriesId = $scope.saveData.seriesId + '';
        				$scope.saveData.modelId = $scope.saveData.modelId + '';
        			}
        		});
			}
        }
        
        $scope.save = function() {
        	$http.post(angular.path + "/car/save",$scope.saveData)
		 	.success(function(response){
		 		response = jQuery.parseJSON(response);
				if (response.success) {
					alert('保存成功');
					window.location.href = angular.path + "/car/list";
				}
		 	});
        }
        
        $scope.loadBrandList = function() {
        	$http.get(angular.path + "/brand/all")
            .success(function (response) {
            	response = jQuery.parseJSON(response);
            	if (response.success) {
            		angular.forEach(response.data, function (c) {
            			$scope.brandList[c.id] = c.name;
					})
				}
            });
        }
        
        $scope.loadSeriesList = function(brandId) {
        	$http.get(angular.path + "/brand/series/all?brandId="+brandId)
            .success(function (response) {
            	response = jQuery.parseJSON(response);
            	if (response.success) {
            		$scope.seriesList = {};
            		angular.forEach(response.data, function (c) {
            			$scope.seriesList[c.id] = c.name;
					})
				}
            });
        }
        
        $scope.loadModelList = function(seriesId) {
        	$http.get(angular.path + "/brand/model/all?seriesId="+seriesId)
            .success(function (response) {
            	response = jQuery.parseJSON(response);
            	if (response.success) {
            		$scope.modelList = {};
            		angular.forEach(response.data, function (c) {
            			$scope.modelList[c.id] = c.name;
					})
				}
            });
        }

    }]);

});