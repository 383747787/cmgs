 define(function (require) {
	/**
	 * 只有使用seajs.use才能执行define里面的function
	 * 所以在config.js中定义不能很规范得使用seajs
	 * 增加app.js专注于引入全局设置
	 */
	// 引入所有可复用的filter
	require('bower_components/angularext/angular/filters/index');
	// 引入所有可复用的directives
	require('bower_components/angularext/angular/directives/index');
	// 引入所有可复用的service
	require('bower_components/angularext/angular/services/index');
	
	//引入图片预览
	require('bower_components/angular-bootstrap-lightbox/dist/angular-bootstrap-lightbox.min.css');
	require('bower_components/angular-bootstrap-lightbox/dist/angular-bootstrap-lightbox.min');
	
	angular.module('App', ['ui.bootstrap','ngSanitize','toaster','ngAnimate','common.filters', 'common.directives','common.services','bootstrapLightbox','zeroclipboard','ngFileUpload'])
    .factory('httpInterceptor', [ '$q', '$injector',function($q, $injector) {
    		var httpRes = {};
	        var httpInterceptor = {
	                'request' : function(config) {
	                	console.log("开始请求");
	                	if(config.url&&(config.url.indexOf("html")==-1)){
	                		var rootScope = $injector.get('$rootScope');
		                	rootScope.gloading = true;
		                	if(httpRes[config.url]){
		                		return $q.reject({});
		                	}else{
		                		httpRes[config.url]=true;
		                	}
	                	}
	                    return config;
	                },  
	        		'response' : function(response) {
	        			httpRes[response.config.url]=false;
	        			var rootScope = $injector.get('$rootScope');
	                	rootScope.gloading = false;
	        			console.log("结束请求");
	                    return response;  
	                }
	        }  
	        return httpInterceptor; 
	    }   
	])
	// config the datepicker
    .config(['datepickerConfig', 'datepickerPopupConfig', function (datepickerConfig, datepickerPopupConfig) {
      /**
       * 使用datepicker只需要设置ng-model 以及is-open
       * is-open可以使用focus, ng-foucs="focus=true"
       * is-open是孤立scope的，所以每个组件都是独立的
       */

      // datePicker popup default setting
      //datepickerPopupConfig.closeOnDateSelection = false;
      datepickerPopupConfig.datepickerPopup = 'yyyy-MM-dd';
      datepickerPopupConfig.closeText = '关闭';

      // datepicker config
      datepickerConfig.formatDayTitle = 'MMMM yy';

      //datepickerConfig.minMode = 'year';
      //datepickerConfig.maxMode = 'day';
    }])
    // 分页的默认设置
    .config(['paginationConfig', function (paginationConfig) {
      paginationConfig.maxSize = 10;
      paginationConfig.itemsPerPage = 20;
      paginationConfig.previousText = '<';
      paginationConfig.nextText = '>';
      paginationConfig.firstText = "«";
      paginationConfig.lastText = "»";
    }])
    .config(['$tooltipProvider', function ($tooltipProvider) {
      $tooltipProvider.options({
        appendToBody: true,
        popupDelay: 300
      });
    }])
    .config(['$httpProvider', function ($httpProvider) {
      // Use x-www-form-urlencoded Content-Type
      //$httpProvider.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded;charset=utf-8';

      /**
       * The workhorse; converts an object to x-www-form-urlencoded serialization.
       * @param {Object} obj
       * @return {String}
       */
      var param = function(obj) {
        var query = '', name, value, fullSubName, subName, subValue, innerObj, i;

        for(name in obj) {
          value = obj[name];

          if(value instanceof Array) {
            for(i=0; i<value.length; ++i) {
              subValue = value[i];
              fullSubName = name + '[' + i + ']';
              innerObj = {};
              innerObj[fullSubName] = subValue;
              query += param(innerObj) + '&';
            }
          }
          else if(value instanceof Object) {
            for(subName in value) {
              subValue = value[subName];
              fullSubName = name + '[' + subName + ']';
              innerObj = {};
              innerObj[fullSubName] = subValue;
              query += param(innerObj) + '&';
            }
          }
          else if(value !== undefined && value !== null)
            query += encodeURIComponent(name) + '=' + encodeURIComponent(value) + '&';
        }

        return query.length ? query.substr(0, query.length - 1) : query;
      };
      $httpProvider.interceptors.push('httpInterceptor');
      // Override $http service's default transformRequest
      //$httpProvider.defaults.transformRequest = [function(data) {
      //  return angular.isObject(data) && String(data) !== '[object File]' ? param(data) : data;
      //}];
    }])
    .controller('rootCtrl', ['$scope',
      function ($scope) {
    	
      $scope.initUser = function() {
    	  window.location.href = angular.path + "/login";
      }

      var menuData = $scope.menuData = {
    		systems: [
    		{
    			active: true,
    			id: 1,
    			type: 0,
    			path: angular.path +"/index",
    			name:"运营管理中心",
    			children: [
			       {active:false,doLog:1,getLog:0,isVirtual:0,name:"个人中心",type:1,id:11, children:[ 
	                  {active:false,doLog:1,getLog:0,isVirtual:0,name:"个人信息",type:2,id:111,path:angular.path + "/user/message"}]},
	                  
	               {active:false,doLog:1,getLog:1,isVirtual:0,name:"品牌车型",type:1,id:12,path:null, children:[ 
   			          {active:false,doLog:1,getLog:0,isVirtual:0,name:"品牌列表",type:2,id:121,path:angular.path + "/brand/list"}]},
   			          
   			       {active:false,doLog:1,getLog:1,isVirtual:0,name:"服务管理",type:1,id:13,path:null, children:[ 
			           {active:false,doLog:1,getLog:0,isVirtual:0,name:"服务种类",type:2,id:133,path:angular.path + "/service/type/list"},
   			           {active:false,doLog:1,getLog:0,isVirtual:0,name:"添加种类",type:2,id:134,path:angular.path + "/service/type/edit"}]},
   			           
   			       {active:false,doLog:1,getLog:1,isVirtual:0,name:"客户管理",type:1,id:14,path:null, children:[ 
	   			      {active:false,doLog:1,getLog:0,isVirtual:0,name:"车主列表",type:2,id:141,path:angular.path + "/car/user/list"},
	   			      {active:false,doLog:1,getLog:0,isVirtual:0,name:"添加车主",type:2,id:142,path:angular.path + "/car/user/edit"},
				      {active:false,doLog:1,getLog:0,isVirtual:0,name:"车辆列表",type:2,id:143,path:angular.path + "/car/list"},
	   			      {active:false,doLog:1,getLog:0,isVirtual:0,name:"添加车辆",type:2,id:144,path:angular.path + "/car/edit"}]},
   			          
       			   {active:false,doLog:1,getLog:1,isVirtual:0,name:"商户管理",type:1,id:15,path:null, children:[ 
       			      {active:false,doLog:1,getLog:0,isVirtual:0,name:"商户列表",type:2,id:151,path:angular.path + "/business/list"},
       			      {active:false,doLog:1,getLog:0,isVirtual:0,name:"添加商户",type:2,id:152,path:angular.path + "/business/edit"}]},
       			      
   			       {active:false,doLog:1,getLog:1,isVirtual:0,name:"订单管理",type:1,id:16,path:null, children:[ 
		               {active:false,doLog:1,getLog:0,isVirtual:0,name:"订单列表",type:2,id:161,path:angular.path + "/order/list"},
		               {active:false,doLog:1,getLog:0,isVirtual:0,name:"添加订单",type:2,id:162,path:angular.path + "/order/edit"}]}
       			   
    			]
    		},
    		{
    			active: false,
    			id: 2,
    			type: 0,
    			path: angular.path +"/index",
    			name:"权限管理中心",
    		}   
    		]
        };
        var breadcrumb = $scope.breadcrumb = [];

        // console.log(menuData.systems);

        // 递推生成面包屑
        function getBreadCrumb(systems) {
          angular.forEach(systems, function (obj) {
            if (obj.active) {
              breadcrumb.push({
                name: obj.name,
                path: obj.path,
                type: obj.type
              });
              if (obj.children && obj.children.length > 0) {
                getBreadCrumb(obj.children);
              }
            }
          });
        }

        if(menuData!=null){
          getBreadCrumb(menuData.systems);
        }
        
        // 生成面包屑结束

        $scope.HELPER = {
          datePickerMode: {
            year: 'year',
            day: 'day',
            month: 'month'
          }
        };

      }
    ]);

});
