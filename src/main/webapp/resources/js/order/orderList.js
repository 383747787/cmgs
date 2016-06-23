define(function (require) {
    var http = require('bower_components/angularext/angularExt').http;
    var browserUtils = require("bower_components/utils/BrowserUtils");
    require('bower_components/commonList/index');
    
    var myApp = angular.module("App");
    myApp.controller("mainCtrl", ["$scope", "$http", "$timeout", "CommonList",  function ($scope, $http, $timeout,CommonList) {

    	var orderList = $scope.orderList = new CommonList();
    	$scope.option = {};
    	
    	orderList.setConfig("/order/list/get/data",{
    		search:{
    			userPhone:'',
    			businessName:''
        	},
            pageSize:10
        });

    	orderList.fetch();

        $scope.setStatus = function (id, isDeleted) {
            var params = {"id": id, "isDeleted": isDeleted};
            $http.post(angular.path + "/order/save", params)
                .success(function (response) {
                	response = jQuery.parseJSON(response);
                	if (response.success) {
                		orderList.fetch();
					}
                }).error(function (response) {
                	
                });
        }

    }]);

});