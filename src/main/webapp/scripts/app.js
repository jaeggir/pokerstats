'use strict';

angular.module('pokerstatsApp.controllers', []);
angular.module('pokerstatsApp.directives', []);
angular.module('pokerstatsApp.services', []);

var pokerstatsApp = angular.module('pokerstatsApp', ['ngResource', 'ngRoute', 'ngCookies']);

pokerstatsApp.config(['$routeProvider', function ($routeProvider) {
    $routeProvider.when('/', {templateUrl: 'views/main.html', controller: 'MainController'});
    $routeProvider.otherwise({redirectTo: '/'});
}]);