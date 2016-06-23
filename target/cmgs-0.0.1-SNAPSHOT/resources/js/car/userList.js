define(function (require) {
    var http = require('bower_components/angularext/angularExt').http;
    var browserUtils = require("bower_components/utils/BrowserUtils");
    require('bower_components/commonList/index');
    
    var myApp = angular.module("App");
    myApp.controller("mainCtrl", ["$scope", "$http", "$timeout", "CommonList",  function ($scope, $http, $timeout,CommonList) {

    	var carUserList = $scope.carUserList = new CommonList();
    	$scope.option = {};
    	
    	carUserList.setConfig("/car/user/list/get/data",{
    		search:{
    			name:''
        	},
            pageSize:10
        });

    	carUserList.fetch();

        $scope.setStatus = function (id, isValid) {
            var params = {"id": id, "isValid": isValid};
            $http.post(angular.path + "/car/user/save", params)
                .success(function (response) {
                	response = jQuery.parseJSON(response);
                	if (response.success) {
                		carUserList.fetch();
					}
                }).error(function (response) {
                	
                });
        }

    }]);

});