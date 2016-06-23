/**
 * 本文替换处理
 * 默认替换手机号中间4位
 */


define(function (require) {
	
	angular.module('common.directives.textreplace',[])
	.run(["$templateCache", function($templateCache) {
	  $templateCache.put("popoverHtml",'<div><button ui-zeroclip zeroclip-text="{{orgText}}" class="btn btn-primary">复制</button></div>');
	}])
	.directive('textReplace', ['$timeout',function ($timeout) {
		var template = '<div style="display:inline-block;"><a popover-template="popoverHtml" title="点击复制" tabindex="0" popover-trigger="focus" ng-style="styles" ng-bind-template="{{replaceText}}"></a></div>';
		return {
			restrict: 'AE',
			template: template,
			replace: true,
			scope:{
				options:"=options",
				orgText:"=orgtext"
			},
			link: function postLink($scope, element, attrs,ngModel) {

				var options = {
					regex:/^([1|0]\d{3})([\d|\-]{2,5})(\d{3})$/,
					replaceRegex:"$1****$3",
					isCopy:true
				};

				options = angular.extend(options,$scope.options || {});

				if(options.regex.test($scope.orgText)){
					$scope.replaceText = $scope.orgText.replace(options.regex,options.replaceRegex);
				}else{
					$scope.replaceText = "";
					return ;
				}
				$scope.styles = {color:'#333',cursor:'text'};
				if(options.isCopy){
					$scope.styles = {borderBottom: '1px #0099CC dashed',display:'inline-block'};
					$scope.popoverHtml = 'popoverHtml';
				}else{
					$scope.popoverHtml = '';
				}
			}
		};
	}]);

});