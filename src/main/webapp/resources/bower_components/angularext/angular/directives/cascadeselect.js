/**
 * 选择已开城市和地区指令
 * 使用方式
 * <location ng-model="cityDistrict" city="city" district="district"></location>
 * city 初始化城市默认为-1
 * district 初始化地区默认为-1
 * ng-model 父scope的model  值对象{city:{id:城市ID,name:城市Name},district:{id:区域ID,name:区域Name}}，如{city:{id:'22',name:'杭州'},district:{id:'33',name:'西湖区'}}
 */
define(function (require) {
	
	angular.module('common.directives.cascadeselect')
	.directive('ccselect', ['$http','$timeout',function ($http,$timeout) {

		var template = '<div class="form-inline">'
						+'	<select class="form-control inline" ng-repeat="sl in selects" ng-init="index = $index" style="margin-right:10px;" ng-change="change(index)" ng-model="city" ng-options="k as v for (k,v) in sl.options"></select>'
						+'</div>';
			return {
				template: template,
				require:"?ngModel",
				scope: {
					configs:"="
				},
				link: function (scope, element, attr, ngModel) {
					scope.selects = [];
					var configs = scope.configs || [];
					/*{
						url:"",
						key:"",
						defaultValue:""
					}*/
					for (var i = 0; i < config.length; i++) {
						var config = configs[i];
						
					};
				}
			};
		}
	]);

});