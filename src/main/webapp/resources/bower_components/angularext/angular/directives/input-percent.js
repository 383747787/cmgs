define(function (require) {

	angular.module('common.directives.input.percent', [])
	.directive('inputPercent', ['$parse',
		function ($parse) {
			return {
				restrict: 'A',
				require: 'ngModel',
				compile: function (element, attrs, transclude) {

					attrs.model = attrs.ngModel;
					attrs.ngModel = 'percentValue';

					return {
						post: function (scope, element, attr) {

							var model = scope.$eval(attr.model);
							scope.percentValue = (parseFloat(model) * 100).toFixed(2);
							var setter = $parse(attr.model).assign;

							scope.$watch('percentValue', function (newVal) {
								setter(scope, (parseFloat(newVal) / 100).toFixed(4));
							});

						}
					};

				}
			};
		}
	]);

});