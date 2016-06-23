define(function (require) {
    var http = require('bower_components/angularext/angularExt').http;
    var browserUtils = require("bower_components/utils/BrowserUtils");
    require('bower_components/commonList/index');
    
    var myApp = angular.module("App");
    myApp.controller("mainCtrl", ["$scope", "$http", "$timeout", "CommonList",  function ($scope, $http, $timeout,CommonList) {

    	var carList = $scope.carList = new CommonList();
    	$scope.option = {};
    	
    	carList.setConfig("/car/list/get/data",{
    		search:{
    			carNum:''
        	},
            pageSize:10
        });

    	carList.fetch();

        $scope.setStatus = function (id, isDeleted) {
            var params = {"id": id, "isDeleted": isDeleted};
            $http.post(angular.path + "/car/save", params)
                .success(function (response) {
                	response = jQuery.parseJSON(response);
                	if (response.success) {
                		carList.fetch();
					}
                }).error(function (response) {
                	
                });
        }

    }]);

});