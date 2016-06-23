define(function (require) {
    var http = require('bower_components/angularext/angularExt').http;
    var browserUtils = require("bower_components/utils/BrowserUtils");
    require('bower_components/commonList/index');
    
    var myApp = angular.module("App");
    myApp.controller("mainCtrl", ["$scope", "$http", "$timeout", "CommonList",  function ($scope, $http, $timeout,CommonList) {

    	var businessList = $scope.businessList = new CommonList();
    	$scope.option = {};
    	
    	businessList.setConfig("/business/list/get/data",{
    		search:{
    			name:'',
    			city:''
        	},
            pageSize:10
        });

    	businessList.fetch();

        $scope.setStatus = function (id, isDeleted) {
            var params = {"id": id, "isDeleted": isDeleted};
            $http.post(angular.path + "/business/save", params)
                .success(function (response) {
                	response = jQuery.parseJSON(response);
                	if (response.success) {
                		businessList.fetch();
					}
                }).error(function (response) {
                	
                });
        }

    }]);

});