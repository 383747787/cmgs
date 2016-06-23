/**
 * 日期指令
 * 使用方式
 * <cmdatepicker ng-model="datetime" cm-options="options" on-change="dateChange()"></cmdatepicker>
 * cm-options 配置信息[不是必须]
 * on-change 日期change事件触发[不是必须]
 */
define(function (require) {

	require('../../../utils/DateUtils');
	
	angular.module('common.directives.cmdatepicker',[])
	.directive('cmdatepicker', ['$timeout','$interval',function ($timeout,$interval) {

		var template = '<div class="form-inline"><div class="input-group date cm-date-time-picker">'
					+'<input type="text" class="form-control"/><span class="input-group-addon"><span class="glyphicon-calendar glyphicon"></span></span>'
					+'</div></div>';
			return {
				template: template,
				require:"?ngModel",
				replace:true,
				scope: {
					'change':"&onChange",
					'cmOptions':"=cmOptions"
				},
				link: function ($scope, element, attr, ngModel) {
					var options = $scope.cmOptions || {};
					$scope.options = angular.extend({
						format:'YYYY-MM-DD',
						dayViewHeaderFormat:"YYYY年MM月"
					},options);
					

					//YYYY-MM-DD HH:mm:ss
					var dateElement = element.find(".cm-date-time-picker");
					dateElement.datetimepicker($scope.options).on("dp.change",function(e){
						var dateTime = e.date.format($scope.options.format);
						if(ngModel){
							$timeout(function(){
								ngModel.$setViewValue(dateTime);
								if($scope.change){
									$scope.change();
								}
							},50);
						}
					});
					
					var stopTime = $interval(function(){
						if(typeof ngModel.$viewValue != "undefined"){
							dateElement.data("DateTimePicker").date(ngModel.$viewValue);
							$interval.cancel(stopTime);
						}
					},50);
					
					element.bind("$destroy",function(){
						$interval.cancel(stopTime);
					});
				}
			};
		}
	]);

});