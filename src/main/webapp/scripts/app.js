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
    $routeProvider.when('/player/:uuid', {templateUrl: 'views/player.html', controller: 'PlayerController'});
    $routeProvider.when('/event/:uuid', {templateUrl: 'views/event.html', controller: 'EventController'});
    $routeProvider.when('/venue/:uuid', {templateUrl: 'views/venue.html', controller: 'VenueController'});
    $routeProvider.otherwise({redirectTo: '/'});
}]);

pokerstatsApp.factory('Players', function($resource) {
    return $resource('rest/1.0/player/');
});

pokerstatsApp.factory('Player', function($resource) {
    return $resource('rest/1.0/player/:playerUuid');
});

pokerstatsApp.factory('Seasons', function($resource) {
    return $resource('rest/1.0/season');
});

pokerstatsApp.factory('Events', function($resource) {
    return $resource('rest/1.0/event/');
});

pokerstatsApp.factory('Event', function($resource) {
    return $resource('rest/1.0/event/:eventUuid');
});

pokerstatsApp.factory('Venues', function($resource) {
    return $resource('rest/1.0/venue/');
});

pokerstatsApp.factory('Venue', function($resource) {
    return $resource('rest/1.0/venue/:venueUuid');
});