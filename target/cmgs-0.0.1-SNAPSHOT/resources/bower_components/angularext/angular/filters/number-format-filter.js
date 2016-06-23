define(function (require) {
	
	angular.module('common.filters.number.format', [])
	.filter('percentage', function () {
		return function (input) {
			input = input || 0;
			input = parseFloat(input);
			return (input * 100).toFixed(2) + '%';
		};
	})
	.filter('discount', function () {
		return function (input) {
			if (!input) {
				return '';
			}
			input = parseFloat(input);
			return (input * 10).toFixed(2) + '折';
		};
	});

});