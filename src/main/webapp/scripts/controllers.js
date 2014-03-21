'use strict';

var controllers = angular.module('pokerstatsApp.controllers');

controllers.controller('MainController', function MainController($scope, Seasons, Event) {
    $scope.season = {};
    $scope.events = [];
    Seasons.query({filter: 'current'}).$promise.then(function (season) {
        $scope.season = season[0];
        angular.forEach($scope.season.events, function (eventUuid) {
            Event.get({eventUuid: eventUuid}).$promise.then(function (event) {
                $scope.events.push(event);
            });
        });
    });
});

controllers.controller('PlayersController', function PlayersController($scope, Players) {

    $scope.players = {};
    Players.query().$promise.then(function (players) {
        $scope.players = players;
    });

});

controllers.controller('EventController', function EventController($scope, $routeParams, Event, Venue, Player) {

    $scope.event = {};
    $scope.venue = {};
    $scope.host = {};
    Event.get({eventUuid: $routeParams.uuid}).$promise.then(function (event) {
        $scope.event = event;
        Venue.get({venueUuid: event.venueUuid}).$promise.then(function (venue) {
            $scope.venue = venue;
        });
        Player.get({playerUuid: event.hostPlayerUuid}).$promise.then(function (player) {
            $scope.host = player;
        });
    });

});