'use strict';

var controllers = angular.module('pokerstatsApp.controllers');

controllers.controller('MainController', function MainController($scope, Seasons, Event) {
    $scope.season = {};
    $scope.events = [];
    Seasons.query({filter: 'current'}).$promise.then(function (season) {
        $scope.season = season[0];
        angular.forEach($scope.season.events, function (eventUuid) {
            Event.get({uuid: eventUuid}).$promise.then(function (event) {
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

controllers.controller('PlayerController', function PlayersController($scope, $routeParams, Player) {

    $scope.player = {};
    Player.get({uuid: $routeParams.uuid }).$promise.then(function (player) {
        $scope.player = player;
    });

});

controllers.controller('EventController', function EventController($scope, $routeParams, Event, Venue, Player,
                                                                   Tournaments) {

    $scope.event = {};
    $scope.venue = {};
    $scope.host = {};
    $scope.tournaments = [];
    Event.get({uuid: $routeParams.uuid}).$promise.then(function (event) {

        $scope.event = event;

        Venue.get({uuid: event.venueUuid}).$promise.then(function (venue) {
            $scope.venue = venue;
        });
        Player.get({uuid: event.hostPlayerUuid}).$promise.then(function (player) {
            $scope.host = player;
        });
        Tournaments.query().$promise.then(function (tournaments) {
            angular.forEach(tournaments, function (tournament) {
               if ($scope.tournaments.indexOf(tournament.uuid) > -1) {
                   $scope.tournament.push(tournament);
               }
            });
            $scope.tournaments = tournaments;
        });
    });

});

controllers.controller('VenueController', function VenueController($scope, $routeParams, Venue) {

    $scope.venue = {};
    Venue.get({uuid: $routeParams.uuid}).$promise.then(function (venue) {
        $scope.venue = venue;
    });

});

controllers.controller('TournamentController', function TournamentController($scope, $routeParams, Tournament) {

    $scope.tournament = {};
    Tournament.get({uuid: $routeParams.uuid}).$promise.then(function (venue) {
        $scope.tournament = venue;
    });

});