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

controllers.controller('PlayersController', function PlayersController($scope, $modal, Players) {

    $scope.players = {};
    Players.query().$promise.then(function (players) {
        $scope.players = players;
    });

    $scope.editPlayer = function (uuid) {
        console.log('TODO implement edit player, uuid=' + uuid);
    };

    $scope.deletePlayer = function (uuid) {
        console.log('TODO implement delete player, uuid=' + uuid);
    };

    $scope.addPlayer = function () {

        var modalInstance = $modal.open({
            templateUrl: 'myModalContent.html',
            controller: ModalInstanceCtrl
        });

        modalInstance.result.then(function (player) {
            $scope.players.push(player);
        }, function () {
            console.log('Modal dismissed at: ' + new Date());
        });
    };

    var ModalInstanceCtrl = function ($scope, $modalInstance, Players) {
        $scope.player = new Players();

        $scope.ok = function () {
            // FIX hack..
            $scope.player.birthday = new Date($scope.player.birthday).getTime();
            $scope.player.$save(function (player) {
                $modalInstance.close(player);
            });
        };

        $scope.cancel = function () {
            $modalInstance.dismiss('cancel');
        };
    };

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

    $scope.xAxisTickFormatFunction = function () {
        return function (d) {
            return d;
        };
    };
    $scope.yAxisTickFormatFunction = function () {
        return function (d) {
            return d; //uncomment for date format
        };
    };

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
                if (tournament.eventUuid === $scope.event.uuid) {
                    $scope.tournaments.push(tournament);
                }
            });
        });
    });

});

controllers.controller('VenuesController', function VenuesController($scope, Venues) {

    $scope.venues = [];
    $scope.bounds = new google.maps.LatLngBounds();

    $scope.mapOptions = {
        zoom: 11,
        mapTypeId: google.maps.MapTypeId.ROAD
    };
    $scope.mapCenter = new google.maps.LatLng(47.5, 8.53);

    $scope.centerToMarkerAndOpenInfoWindow = function (venue) {
        // FIX open info window
        $scope.mapCenter = new google.maps.LatLng(venue.latitude, venue.longitude);
        $scope.markerEvents = [{
            event: 'openinfowindow',
            ids: [venue.uuid]
        }];
    };

    Venues.query().$promise.then(function (venues) {
        $scope.venues = venues;
        angular.forEach(venues, function (venue) {
            $scope.bounds.extend(new google.maps.LatLng(venue.latitude, venue.longitude));
        });
        var center = $scope.bounds.getCenter();
        $scope.mapCenter = new google.maps.LatLng(center.k, center.A);
        $scope.$broadcast('gmMarkersUpdate', 'venues');
    });

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

controllers.controller('EventsController', function EventsController($scope, $modal, Seasons, Events, Players, Venues) {

    $scope.seasons = {};
    $scope.events = {};
    $scope.players = {};
    $scope.venues = {};

    Seasons.query().$promise.then(function (seasons) {
        $scope.seasons = seasons;
    });
    Events.query().$promise.then(function (events) {
        $scope.events = events;
    });
    Players.query().$promise.then(function (players) {
        $scope.players = players;
    });
    Venues.query().$promise.then(function (venues) {
        $scope.venues = venues;
    });

    $scope.addEvent = function () {

        var modalInstance = $modal.open({
            templateUrl: 'myModalContent.html',
            controller: ModalInstanceCtrl,
            resolve: {
                players: function () {
                    return $scope.players;
                },
                venues: function () {
                    return $scope.venues;
                }
            }
        });

        modalInstance.result.then(function (event) {
            // FIX is not updated..
            $scope.events.push(event);
        }, function () {
            console.log('Modal dismissed at: ' + new Date());
        });
    };

    var ModalInstanceCtrl = function ($scope, $modalInstance, Events, players, venues) {
        $scope.event = new Events();
        $scope.players = players;
        $scope.venues = venues;

        $scope.ok = function () {
            // FIX hack..
            $scope.event.date = new Date($scope.event.date).getTime();
            $scope.event.$save(function (event) {
                $modalInstance.close(event);
            });
        };

        $scope.cancel = function () {
            $modalInstance.dismiss('cancel');
        };
    };

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