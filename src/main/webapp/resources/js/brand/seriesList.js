define(function (require) {
    var http = require('bower_components/angularext/angularExt').http;
    var browserUtils = require("bower_components/utils/BrowserUtils");
    require('bower_components/commonList/index');
    
    var myApp = angular.module("App");
    myApp.controller("mainCtrl", ["$scope", "$http", "$timeout", "CommonList", "$modal", 
                                  function ($scope, $http, $timeout, CommonList, $modal) {

    	var seriesList = $scope.seriesList = new CommonList();
    	var brandId = $scope.brandId = $.trim($("#brandId").val());
    	var saveData = $scope.saveData = {};
    	$scope.option = {};
    	
    	$scope.washCarTypeArray=[{
			id:0,
			name:'请选择'
		 },{
			id : 1,
			name : '普通车'
		 },{
			id : 2,
			name : 'SUV'
		 },{
			id : 3,
			name : '七座'
		 },{
			 id : 4,
			 name : '微面'
		 }];

		$scope.WashTypeArray=[{
			id:0,
			name:'请选择'
		 },{
			id : 1,
			name : '小车'
		 },{
			 id : 2,
			 name : '大车'
		 }];
    	
    	seriesList.setConfig("/brand/series/list/get/data",{
    		search:{
    			brandId:brandId,
    			name:''
        	},
            pageSize:10
        });

    	seriesList.fetch();

        $scope.setStatus = function (id, isValid) {
            var params = {"id": id, "isValid": isValid};
            $http.post(angular.path + "/brand/series/save", params)
                .success(function (response) {
                	response = jQuery.parseJSON(response);
                	if (response.success) {
                		seriesList.fetch();
					}
                }).error(function (response) {
                	
                });
        }
        
        $scope.crawlBySeries = function(seriesId) {
        	if (window.confirm("是否同步这个车系下所有的车型数据？")) {
        		var start = new Date().format("yyyy-MM-dd hh:mm:ss");
        		console.log('start:'+start);
        		$scope.option.msg = "正在获取数据，请勿进行任何操作";
        		$http.get(angular.path + "/brand/series/crawl?seriesId=" + seriesId)
        		.success(function(response) {
        			response = jQuery.parseJSON(response);
        			var end = new Date().format("yyyy-MM-dd hh:mm:ss");
        			console.log('end:'+end);
        			if(response.success){
        				seriesList.fetch();
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

			$http.post(angular.path + "/brand/series/save",$scope.saveData)
			 .success(function(response){
				response = jQuery.parseJSON(response);
			 	if(response.success){
			 		newModal.close();
			 		seriesList.fetch();

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

		$scope.edit = function(series){
			$scope.saveData = angular.copy(series);
			createDialog();
		};

    }]);

});