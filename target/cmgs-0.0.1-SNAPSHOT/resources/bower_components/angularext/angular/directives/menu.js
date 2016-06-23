define(function (require) {

  angular.module('common.directives.menu', [])
  .directive('loopMenu', ['$compile',
    function ($compile) {

      var systemTemplate = "" +
          '<a class="selected-system" ng-repeat="m in model" ng-if="m.active" ng-click="toggleSystems()">' +
            '{{m.name}}  ' +
            '<span class="glyphicon glyphicon-chevron-down"></span>' +
          '</a>' +
          '<ul ng-class="{\'ups-menu-systems\': true, active: systemsOpen}">' +
            '<li class="ups-menu-system" ng-repeat="m in model">' +
              '<a href="{{m.path}}" ng-bind="m.name"></a>' +
            '</li>' +
          '</ul>' +
          '<ul ng-repeat="m in model" loop-menu="m.children" ng-if="m.active" class="ups-menu-wrapper ups-menu-top"></ul>';

      var template = "" +
          // '<ul class="">' +
            '<li ng-class="{active: m.active, \'ups-menu-item\': true}" ng-repeat="m in model" ng-click="chooseThis(m)" ng-show="m.isVirtual != 1">' +
              '<span ng-if="m.type === 1" class="parent" ng-click="toggleActive(m)">' +
                '<span ng-class="{glyphicon: true, \'pull-right\': true, \'ups-active-toggle\': true, \'glyphicon-menu-right\': !m.active, \'glyphicon-menu-down\': m.active}"></span>' +
                '{{m.name}}' +
              '</span>' +
              '<a href="{{m.path}}" ng-bind="m.name" ng-if="m.type === 2" class="son"></a>' +
              '<ul loop-menu="m.children" ng-if="m.children && m.children.length > 0 && m.children[0].type < 3" class="ups-menu-wrapper"></ul>' +
            '</li>';
          // '</ul>';

      return {
        restrict: 'A',
        scope: {
          model: '=loopMenu'
        },
        link: function ($scope, element, attrs) {

          var model = $scope.model;
          $scope.ctx = angular.path;

          if (model && model.length > 0) {
            $scope.childType = model[0].type;
            if ($scope.childType < 3) {
              if ($scope.childType === 0) {
                element.append(systemTemplate);
              } else {
                element.append(template);
              }
              $compile(element.contents())($scope);
            }
          }

          $scope.toggleActive = function (m) {
            m.active = !m.active;
          };

          $scope.toggleSystems = function () {
            $scope.systemsOpen = !$scope.systemsOpen;
          };

          $scope.chooseThis = function (item) {
            item.choosed = true;
          };
        }
      };
    }
  ]);

});