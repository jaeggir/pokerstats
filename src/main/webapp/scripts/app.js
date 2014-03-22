'use strict';

angular.module('pokerstatsApp.controllers', []);
angular.module('pokerstatsApp.directives', []);
angular.module('pokerstatsApp.services', []);
angular.module('pokerstatsApp.filters', []);

var pokerstatsApp = angular.module('pokerstatsApp', [
    'ngResource',
    'ngRoute',
    'ngCookies',

    'nvd3ChartDirectives',
    'AngularGM',
    'ui.bootstrap',

    'pokerstatsApp.controllers',
    'pokerstatsApp.directives',
    'pokerstatsApp.services',
    'pokerstatsApp.filters'

]);

pokerstatsApp.config(['$routeProvider', function ($routeProvider) {
    $routeProvider.when('/', {templateUrl: 'views/main.html', controller: 'MainController'});
    $routeProvider.when('/players', {templateUrl: 'views/players.html', controller: 'PlayersController'});
    $routeProvider.when('/events', {templateUrl: 'views/events.html', controller: 'EventsController'});
    $routeProvider.when('/player/:uuid', {templateUrl: 'views/player.html', controller: 'PlayerController'});
    $routeProvider.when('/event/:uuid', {templateUrl: 'views/event.html', controller: 'EventController'});
    $routeProvider.when('/venues', {templateUrl: 'views/venues.html', controller: 'VenuesController'});
    $routeProvider.when('/venue/:uuid', {templateUrl: 'views/venue.html', controller: 'VenueController'});
    $routeProvider.when('/tournament/:uuid', {templateUrl: 'views/tournament.html', controller: 'TournamentController'});
    $routeProvider.otherwise({redirectTo: '/'});
}]);

pokerstatsApp.factory('Players', function ($resource) {
    return $resource('rest/1.0/player/');
});

pokerstatsApp.factory('Player', function ($resource) {
    return $resource('rest/1.0/player/:uuid');
});

pokerstatsApp.factory('Seasons', function ($resource) {
    return $resource('rest/1.0/season');
});

pokerstatsApp.factory('Events', function ($resource) {
    return $resource('rest/1.0/event/');
});

pokerstatsApp.factory('Event', function ($resource) {
    return $resource('rest/1.0/event/:uuid');
});

pokerstatsApp.factory('Venues', function ($resource) {
    return $resource('rest/1.0/venue/');
});

pokerstatsApp.factory('Venue', function ($resource) {
    return $resource('rest/1.0/venue/:uuid');
});

pokerstatsApp.factory('Tournaments', function ($resource) {
    return $resource('rest/1.0/tournament/');
});

pokerstatsApp.factory('Tournament', function ($resource) {
    return $resource('rest/1.0/tournament/:uuid');
});

pokerstatsApp.factory('TournamentResults', function ($resource) {
    return $resource('rest/1.0/tournament/:uuid/results');
});

pokerstatsApp.factory('PlayerResults', function ($resource) {
    return $resource('rest/1.0/player/:uuid/results');
});