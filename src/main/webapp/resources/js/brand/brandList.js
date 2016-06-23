define(function (require) {
    var http = require('bower_components/angularext/angularExt').http;
    var browserUtils = require("bower_components/utils/BrowserUtils");
    require('bower_components/commonList/index');
    
    var myApp = angular.module("App");
    myApp.controller("mainCtrl", ["$scope", "$http", "$timeout", "CommonList", "$modal",
                                  function ($scope, $http, $timeout, CommonList, $modal) {

    	var brandList = $scope.brandList = new CommonList();
    	var saveData = $scope.saveData = {};
    	$scope.option = {};
    	
    	brandList.setConfig("/brand/list/get/data",{
    		search:{
    			name:''
        	},
            pageSize:10
        });

    	brandList.fetch();

        $scope.setStatus = function (id, isValid) {
            var params = {"id": id, "isValid": isValid};
            $http.post(angular.path + "/brand/save", params)
                .success(function (response) {
                	// response = jQuery.parseJSON(response);
                	if (response.success) {
                		brandList.fetch();
					}
                }).error(function (response) {
                	
                });
        }

        $scope.crawlByBrand = function(brandId) {
        	if (window.confirm("是否同步这个品牌下所有的车系和车型数据？")) {
        		var start = new Date().format("yyyy-MM-dd hh:mm:ss");
        		console.log('start:'+start);
        		$scope.option.msg = "正在获取数据，请勿进行任何操作";
        		$http.get(angular.path + "/brand/crawl?brandId=" + brandId)
        		.success(function(response) {
        			// response = jQuery.parseJSON(response);
        			var end = new Date().format("yyyy-MM-dd hh:mm:ss");
        			console.log('end:'+end);
        			if(response.success){
        				brandList.fetch();
        				$scope.option.success = true;
        				$scope.option.msg = response.data;
        			}else{
        				$scope.option.success = false;
        				$scope.option.msg = "同步失败";
        			}
        			$timeout(function(){
        				$scope.option.msg = "";
        			},3000);
        		});
        	}
        }
        
        $scope.crawlAll = function() {
        	if (window.confirm("同步所有数据耗时较长，是否同步？")) {
        		var start = new Date().format("yyyy-MM-dd hh:mm:ss");
            	console.log('start:'+start);
            	$scope.option.msg = "正在获取数据，请勿进行任何操作";
            	$http.get(angular.path + "/brand/crawl/all")
            		.success(function(response) {
            			// response = jQuery.parseJSON(response);
            			var end = new Date().format("yyyy-MM-dd hh:mm:ss");
    	    			console.log('end:'+end);
            			if(response.success){
            				brandList.fetch();
        			 		$scope.option.success = true;
        			 		$scope.option.msg = response.data;
        			 	}else{
        			 		$scope.option.success = false;
        			 		$scope.option.msg = "同步失败";
        			 	}
            			$timeout(function(){
            		 		$scope.option.msg = "";
            		 	},3000);
            		});
        	} else {
        		return;
        	}
        }
        
        var newModal;
		function createDialog(){
			newModal = $modal.open({
              	templateUrl: 'template.html',
              	scope:$scope,
				backdrop: 'static',
				keyboard: 'false'
            });
		}

		$scope.save = function(valid){
			if(!valid){
				return ;
			}

			$http.post(angular.path + "/brand/save",$scope.saveData)
			 .success(function(response){
				// response = jQuery.parseJSON(response);
			 	if(response.success){
			 		newModal.close();
			 		brandList.fetch();

			 		$scope.option.success = true;
			 		$scope.option.msg = "保存成功";
			 	}else{
			 		$scope.option.success = false;
			 		$scope.option.saveErrorMsg = response.message.length>0?response.message:"保存失败";
			 	}

			 	$timeout(function(){
			 		$scope.option.msg = "";
			 		$scope.option.saveErrorMsg = "";
			 	},3000);
			 });
		};

		$scope.cancel = function(){
			newModal.close();
		};

		$scope.edit = function(brand){
			$scope.saveData = angular.copy(brand);
			createDialog();
		};
        
    }]);

});