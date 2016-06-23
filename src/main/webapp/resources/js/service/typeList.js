define(function (require) {
    var http = require('bower_components/angularext/angularExt').http;
    var browserUtils = require("bower_components/utils/BrowserUtils");
    require('bower_components/commonList/index');
    
    var myApp = angular.module("App");
    myApp.controller("mainCtrl", ["$scope", "$http", "$timeout", "CommonList",  function ($scope, $http, $timeout,CommonList) {

    	var typeList = $scope.typeList = new CommonList();
    	$scope.option = {};
    	$scope.lv1List = {};
    	
    	typeList.setConfig("/service/type/list/get/data",{
    		search:{
    			lv:'',
    			parent:'',
    			name:''
        	},
            pageSize:10
        });

    	typeList.fetch();

        $scope.setStatus = function(id, isValid) {
            var params = {"id": id, "isValid": isValid};
            $http.post(angular.path + "/service/type/save", params)
                .success(function (response) {
                	response = jQuery.parseJSON(response);
                	if (response.success) {
                		typeList.fetch();
					}
                }).error(function (response) {
                	
                });
        }
        
       var loadLv1List = function() {
        	$http.post(angular.path + "/service/type/list/lv1")
            .success(function (response) {
            	response = jQuery.parseJSON(response);
            	if (response.success) {
            		$scope.lv1List[''] = '一级类目';
            		angular.forEach(response.data, function (c) {
            			$scope.lv1List[c.id] = c.name;
					})
				}
            });
        }
        
       $scope.init = function() {
    	   loadLv1List();
       }

    }]);

});