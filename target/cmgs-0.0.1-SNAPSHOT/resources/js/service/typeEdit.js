define(function (require) {
    var http = require('bower_components/angularext/angularExt').http;
    var browserUtils = require("bower_components/utils/BrowserUtils");
    var myApp = angular.module('App');
    require('bower_components/commonList/index');
	
    myApp.controller("mainCtrl", ["$scope", "$http", "$timeout", "CommonList",  function ($scope, $http, $timeout,CommonList) {

    	$scope.saveData = {
    		lv:'',
    		parent:''
    	};
    	$scope.lv1List = {};
    	
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
        
        $scope.init = function(id) {
        	loadLv1List();
        	
        	$http.get(angular.path + "/service/type/find?id="+id)
		 	.success(function(response){
		 		response = jQuery.parseJSON(response);
				if (response.success) {
					$scope.saveData = response.data;
					$scope.saveData.lv = $scope.saveData.lv +'';
					$scope.saveData.parent = $scope.saveData.parent +'';
				}
		 	});
        }
        
        $scope.save = function() {
        	if ($scope.saveData.lv=='' || $scope.saveData.lv==null) {
				alert('类目级别不能为空');
				return;
			}
        	if ($scope.saveData.lv==2 && ($scope.saveData.parent=='' || $scope.saveData.parent==null)) {
				alert('一级类目级别不能为空');
				return;
			}
        	$http.post(angular.path + "/service/type/save",$scope.saveData)
		 	.success(function(response){
		 		response = jQuery.parseJSON(response);
				if (response.success) {
					alert('保存成功');
					window.location.href = angular.path + "/service/type/list";
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
					$scope.saveData.icon = angular.path + response.data;
					alert(response.msg);
				} else {
					alert(response.msg);
				}
		 	}); 
        }

    }]);

});