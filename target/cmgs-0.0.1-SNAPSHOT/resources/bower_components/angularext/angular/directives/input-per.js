define(function () {

  angular.module('common.directives.input.per', [])
    .directive('inputPer', [
      function () {
        return {
          restrict: 'A',
          require: 'ngModel',
          link: function ($scope, element, attrs, ngModelCtrl) {

            var perReg = /^100(\.0{1,2})?$|^\d{1,2}(\.\d{1,2})?$/;

            ngModelCtrl.$formatters.push(function (modelValue) {
              var num = parseFloat(modelValue);
              return isNaN(num) ? '' : (num * 100).toFixed(2);
            });

            ngModelCtrl.$parsers.push(function (viewValue) {
              var num = parseFloat(viewValue);
              return isNaN(num) ? '' : (num / 100).toFixed(4);
            });

            ngModelCtrl.$validators.percent = function (modelValue, viewValue) {
              return perReg.test(viewValue);
            };

          }
        };
      }
    ]);

});