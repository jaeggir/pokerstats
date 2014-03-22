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

controllers.controller('PlayerController', function PlayersController($scope, $routeParams, Player, PlayerResults) {

    $scope.player = {};
    Player.get({uuid: $routeParams.uuid }).$promise.then(function (player) {
        $scope.player = player;

    });

    $scope.results = [];
    $scope.averageRank;
    $scope.averageWin = 0;
    PlayerResults.query({uuid: $routeParams.uuid}).$promise.then(function (results) {

        var rankSummary = 0;
        var winSummary = 0;
        var values = [];
        angular.forEach(results, function (result, index) {
            values.push([index + 1, -result.ante + result.win]);
            rankSummary += result.rank;
            winSummary += -result.ante + result.win;
        });
        $scope.results = [
            {
                key: 'Results',
                values: values
            }
        ];
        $scope.averageRank = Math.round(rankSummary / results.length * 100) / 100;
        $scope.averageWin = Math.round(winSummary / results.length * 100) / 100;
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

controllers.controller('VenuesController', function VenuesController($scope, Venues) {

    $scope.venues = [];
    $scope.bounds = new google.maps.LatLngBounds();

    Venues.query().$promise.then(function (venues) {
        $scope.venues = venues;
        angular.forEach(venues, function (venue) {
            $scope.bounds.extend(new google.maps.LatLng(venue.latitude, venue.longitude));
        });
        var center = $scope.bounds.getCenter();
        $scope.mapCenter = new google.maps.LatLng(center.k, center.A);
        $scope.$broadcast('gmMarkersUpdate', 'venues');
    });

    $scope.mapOptions = {
        zoom: 13,
        mapTypeId: google.maps.MapTypeId.ROAD
    };
    $scope.mapCenter = new google.maps.LatLng(47.5, 8.53);

});

controllers.controller('VenueController', function VenueController($scope, $routeParams, Venue) {

    $scope.venue = {};
    $scope.venues = []; // for the markers

    $scope.mapOptions = {
        zoom: 14,
        mapTypeId: google.maps.MapTypeId.ROAD
    };
    $scope.mapCenter = new google.maps.LatLng(47.5, 8.53);

    Venue.get({uuid: $routeParams.uuid}).$promise.then(function (venue) {
        $scope.venue = venue;

        $scope.venues.push(venue);
        $scope.mapCenter = new google.maps.LatLng(venue.latitude, venue.longitude);

        $scope.$broadcast('gmMarkersUpdate', 'venues');
    });

});

controllers.controller('EventsController', function EventsController($scope, $routeParams, Seasons, Events) {

    $scope.seasons = {};
    $scope.events = {};
    Seasons.query().$promise.then(function (seasons) {
        $scope.seasons = seasons;
    });
    Events.query().$promise.then(function (events) {
        $scope.events = events;
    });

});

controllers.controller('TournamentController', function TournamentController($scope, $routeParams, Tournament,
                                                                             TournamentResults) {

    $scope.tournament = {};
    $scope.results = [];
    Tournament.get({uuid: $routeParams.uuid}).$promise.then(function (tournament) {
        $scope.tournament = tournament;
        TournamentResults.query({uuid: tournament.uuid}).$promise.then(function (results) {
            angular.forEach(results, function (result) {
                if (result.tournamentUuid === $scope.tournament.uuid) {
                    $scope.results.push(result);
                }
            });
        });
    });

});