define(function (require) {
    var http = require('bower_components/angularext/angularExt').http;
    require('bower_components/uploadHandler/index');
    require('bower_components/commonList/index');
    var browserUtils = require("bower_components/utils/BrowserUtils");

    var myApp = angular.module("App");
    myApp.controller("mainCtrl",  ["$scope","$modal","$http","$timeout","$window","Upload",'uploadHandler',"CommonList",
                                   function($scope,$modal,$http,$timeout,$window,$upload,UploadHandler,CommonList){

    	var modelList = $scope.modelList = new CommonList();
    	var brandId = $scope.brandId = $.trim($("#brandId").val());
    	var seriesId = $scope.seriesId = $.trim($("#seriesId").val());
    	var saveData = $scope.saveData = {};
    	$scope.option = {};
    	
    	$scope.modelWashTypeArray=[{
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
		$scope.tyreTypeArray=[{
			id:0,
			name:'普通胎'
		 },{
			id : 1,
			name : '缺气保用胎'
		 }];
		
    	modelList.setConfig("/brand/model/list/get/data",{
    		search:{
    			brandId:brandId,
    			seriesId:seriesId,
    			name:'',
    			displacement:'',
    			carEngineType:''
        	},
            pageSize:10
        });

    	modelList.fetch();

        $scope.setStatus = function (id, isValid) {
            var params = {"id": id, "isValid": isValid};
            $http.post(angular.path + "/brand/model/save", params)
                .success(function (response) {
                	response = jQuery.parseJSON(response);
                	if (response.success) {
                		modelList.fetch();
					}
                }).error(function (response) {
                	
                });
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

			$http.post(angular.path + "/brand/model/save",$scope.saveData)
			 .success(function(response){
				response = jQuery.parseJSON(response);
			 	if(response.success){
			 		newModal.close();
			 		modelList.fetch();

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

		$scope.edit = function(model){
			$scope.saveData = angular.copy(model);
			createDialog();
		};

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
						$scope.saveData.imgUrl = angular.path + response.data;
						alert(response.msg);
					} else {
						alert(response.msg);
					}
			 	}); 
	        }

        //进入汽车之家图片库
        $scope.goAutoHomeImg = function(item){
        	$window.open("http://car.autohome.com.cn/pic/series-s"
        		+item.id+"/"+item.seriesId+"-1.html");
        }

        //进入汽车之家车型配置
        $scope.goAutoHomeConfig = function(item){
        	$window.open("http://car.autohome.com.cn/config/spec/"+item.id+".html");
        }

        /**
		 * [search 搜索]
		 * @return {[type]} [description]
		 */
		$scope.search = function(){
			modelList.fetch();
		}

		/**
		 * [toPage 分页]
		 * @return {[type]} [description]
		 */
		$scope.toPage = function(){
			modelList.fetch();
		}

    }]);

});
