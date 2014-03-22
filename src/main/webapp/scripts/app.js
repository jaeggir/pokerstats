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
    'ui.select2',

    'pokerstatsApp.controllers',
    'pokerstatsApp.directives',
    'pokerstatsApp.services',
    'pokerstatsApp.filters'

]);

pokerstatsApp.config(['$routeProvider', function ($routeProvider) {
    $routeProvider.when('/players', {templateUrl: 'views/players.html', controller: 'PlayersController'});
    $routeProvider.when('/events', {templateUrl: 'views/events.html', controller: 'EventsController'});
    $routeProvider.when('/player/:uuid', {templateUrl: 'views/player.html', controller: 'PlayerController'});
    $routeProvider.when('/event/:uuid', {templateUrl: 'views/event.html', controller: 'EventController'});
    $routeProvider.when('/venues', {templateUrl: 'views/venues.html', controller: 'VenuesController'});
    $routeProvider.when('/venue/:uuid', {templateUrl: 'views/venue.html', controller: 'VenueController'});
    $routeProvider.when('/tournament/:uuid', {templateUrl: 'views/tournament.html', controller: 'TournamentController'});
    $routeProvider.otherwise({redirectTo: '/events'});
}]);

pokerstatsApp.constant('API_VERSION', '1.0');

pokerstatsApp.factory('Players', function ($resource, API_VERSION) {
    return $resource('rest/' + API_VERSION + '/player/');
});

pokerstatsApp.factory('Player', function ($resource, API_VERSION) {
    return $resource('rest/' + API_VERSION + '/player/:uuid');
});

pokerstatsApp.factory('Seasons', function ($resource, API_VERSION) {
    return $resource('rest/' + API_VERSION + '/season');
});

pokerstatsApp.factory('Events', function ($resource, API_VERSION) {
    return $resource('rest/' + API_VERSION + '/event/');
});

pokerstatsApp.factory('Event', function ($resource, API_VERSION) {
    return $resource('rest/' + API_VERSION + '/event/:uuid');
});

pokerstatsApp.factory('Venues', function ($resource, API_VERSION) {
    return $resource('rest/' + API_VERSION + '/venue/');
});

pokerstatsApp.factory('Venue', function ($resource, API_VERSION) {
    return $resource('rest/' + API_VERSION + '/venue/:uuid');
});

pokerstatsApp.factory('Tournaments', function ($resource, API_VERSION) {
    return $resource('rest/' + API_VERSION + '/tournament/');
});

pokerstatsApp.factory('Tournament', function ($resource, API_VERSION) {
    return $resource('rest/' + API_VERSION + '/tournament/:uuid');
});

pokerstatsApp.factory('TournamentResults', function ($resource, API_VERSION) {
    return $resource('rest/' + API_VERSION + '/tournament/:uuid/results');
});

pokerstatsApp.factory('PlayerResults', function ($resource, API_VERSION) {
    return $resource('rest/' + API_VERSION + '/player/:uuid/results');
});