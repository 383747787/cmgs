define(function (require) {
    var http = require('bower_components/angularext/angularExt').http;
    var browserUtils = require("bower_components/utils/BrowserUtils");
    var myApp = angular.module('App');
    require('bower_components/commonList/index');
	
    myApp.controller("mainCtrl", ["$scope", "$http", "$timeout", "CommonList",  function ($scope, $http, $timeout,CommonList) {

    	$scope.saveData = {};
    	
        $scope.init = function(id) {
        	if (id != null) {
        		$http.get(angular.path + "/business/find?id="+id)
        		.success(function(response){
        			response = jQuery.parseJSON(response);
        			if (response.success) {
        				$scope.saveData = response.data;
        			}
        		});
			}
        }
        
        $scope.save = function() {
        	$http.post(angular.path + "/business/save",$scope.saveData)
		 	.success(function(response){
		 		response = jQuery.parseJSON(response);
				if (response.success) {
					alert('保存成功');
					window.location.href = angular.path + "/business/list";
				}
		 	});
        }
        
        $scope.upload = function(files) {
        	var file = files[0];
            var fd = new FormData();
            fd.append('file', file); 
            
            $http({
                  method:'POST',
                  url: angular.path + "/upload/img",
                  data: fd,
                  headers: {'Content-Type':undefined},
                  transformRequest: angular.identity 
             }).success(function(response){
		 		response = jQuery.parseJSON(response);
				if (response.success) {
					$scope.saveData.avatar = angular.path + response.data;
					alert(response.msg);
				} else {
					alert(response.msg);
				}
		 	}); 
        }

    }]);

});