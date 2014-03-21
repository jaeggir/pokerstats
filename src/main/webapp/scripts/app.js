'use strict';

angular.module('pokerstatsApp.controllers', []);
angular.module('pokerstatsApp.directives', []);
angular.module('pokerstatsApp.services', []);
angular.module('pokerstatsApp.filters', []);

var pokerstatsApp = angular.module('pokerstatsApp', [
    'ngResource',
    'ngRoute',
    'ngCookies',

    'pokerstatsApp.controllers',
    'pokerstatsApp.directives',
    'pokerstatsApp.services',
    'pokerstatsApp.filters'

]);

pokerstatsApp.config(['$routeProvider', function ($routeProvider) {
    $routeProvider.when('/', {templateUrl: 'views/main.html', controller: 'MainController'});
    $routeProvider.when('/players', {templateUrl: 'views/players.html', controller: 'PlayersController'});
    $routeProvider.otherwise({redirectTo: '/'});
}]);

pokerstatsApp.factory('playersResource', function($resource) {
    return $resource('rest/1.0/player/');
});