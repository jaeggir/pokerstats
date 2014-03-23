'use strict';

var controllers = angular.module('pokerstatsApp.controllers');

controllers.controller('PlayersController', function PlayersController($scope, $modal, $filter, Players) {

    $scope.players = Players.query();

    $scope.editPlayer = function (uuid) {
        console.log('TODO implement edit player, uuid=' + uuid);
    };

    $scope.deletePlayer = function (uuid) {
        console.log('TODO implement delete player, uuid=' + uuid);
    };

    $scope.addPlayer = function () {

        var modalInstance = $modal.open({
            templateUrl: 'playerModal.html',
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
        $scope.birthday = null;

        /* DATE-PICKER STUFF */
        /*********************/

        $scope.open = function ($event) {
            $event.preventDefault();
            $event.stopPropagation();

            $scope.opened = true;
        };

        $scope.maxDate = new Date();

        $scope.dateOptions = {
            'starting-day': 1
        };

        /* END DATE-PICKER STUFF */
        /*************************/

        $scope.ok = function () {
            $scope.player.birthday = new Date(this.birthday).getTime();
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

    $scope.player = Player.get({uuid: $routeParams.uuid });

    $scope.results = [];
    $scope.averageRank = null;
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

        $scope.venue = Venue.get({uuid: event.venueUuid});
        $scope.host = Player.get({uuid: event.hostPlayerUuid});
        $scope.tournaments = Tournaments.query({eventUuid: $scope.event.uuid});
    });

});

controllers.controller('VenuesController', function VenuesController($scope, Venues, angulargmContainer) {

    $scope.venues = [];
    var gMapInstance = null;

    var gMapPromise = angulargmContainer.getMapPromise('venuesMap');
    gMapPromise.then(function (gMap) {

        gMapInstance = gMap;

        gMap.setCenter(new google.maps.LatLng(47.5, 8.53));
        gMap.setZoom(12);
        gMap.setMapTypeId(google.maps.MapTypeId.ROADMAP);

        var bounds = new google.maps.LatLngBounds();

        Venues.query().$promise.then(function (venues) {
            $scope.venues = venues;

            angular.forEach(venues, function (venue) {
                bounds.extend(new google.maps.LatLng(venue.latitude, venue.longitude));
            });

            gMap.fitBounds(bounds);

            $scope.$broadcast('gmMarkersUpdate', 'venues');
        });

    });

    $scope.centerToMarkerAndOpenInfoWindow = function (venue) {
        // FIX open info window
        gMapInstance.setCenter(new google.maps.LatLng(venue.latitude, venue.longitude));
        $scope.markerEvents = [{
            event: 'openinfowindow',
            ids: [venue.uuid]
        }];
    };

});

controllers.controller('VenueController', function VenueController($scope, $routeParams, Venue, Events,
                                                                   angulargmContainer) {

    $scope.venue = {};
    $scope.venues = []; // for the markers
    $scope.events = [];

    var gmapPromise = angulargmContainer.getMapPromise('venueMap');
    gmapPromise.then(function (gmap) {

        gmap.setCenter(new google.maps.LatLng(47.5, 8.53));
        gmap.setZoom(14);
        gmap.setMapTypeId(google.maps.MapTypeId.ROADMAP);

        Venue.get({uuid: $routeParams.uuid}).$promise.then(function (venue) {
            $scope.venue = venue;
            $scope.venues.push(venue);

            gmap.setCenter(new google.maps.LatLng(venue.latitude, venue.longitude));

            $scope.$broadcast('gmMarkersUpdate', 'venues');
        });

    });

    $scope.events = Events.query({venueUuid: $routeParams.uuid});

});

controllers.controller('EventsController', function EventsController($scope, $modal, Seasons, Events, Players, Venues) {

    $scope.seasons = Seasons.query();
    $scope.events = Events.query();
    $scope.players = Players.query();
    $scope.venues = Venues.query();

    $scope.addEvent = function () {

        var modalInstance = $modal.open({
            templateUrl: 'eventModal.html',
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

        $scope.date = null;

        /* DATE-PICKER STUFF */
        /*********************/

        $scope.open = function ($event) {
            $event.preventDefault();
            $event.stopPropagation();

            $scope.opened = true;
        };

        $scope.minDate = new Date();

        $scope.dateOptions = {
            'starting-day': 1
        };

        $scope.ok = function () {
            $scope.event.date = new Date(this.date).getTime();
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
        $scope.results = TournamentResults.query({uuid: tournament.uuid});
    });

});