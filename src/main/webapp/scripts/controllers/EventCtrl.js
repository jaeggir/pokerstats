'use strict';

var controllers = angular.module('pokerstatsApp.controllers');

controllers.controller('EventController', function EventController($scope, $location, $routeParams, $modal,
                                                                   Event, Venue, Player, Players, Tournaments) {

    $scope.event = {};
    $scope.venue = {};
    $scope.host = {};
    $scope.tournaments = [];
    $scope.players = Players.query();
    Event.get({uuid: $routeParams.uuid}).$promise.then(function (event) {
        $scope.event = event;

        $scope.venue = Venue.get({uuid: event.venueUuid});
        $scope.host = Player.get({uuid: event.hostPlayerUuid});
        $scope.tournaments = Tournaments.query({eventUuid: $scope.event.uuid});
    });

    $scope.isAvailable = function () {
        // returns true if the event date is today or later
        return $scope.event !== null && $scope.event.date > (new Date().getTime() - 24 * 60 * 60 * 1000);
    };

    $scope.createTournament = function () {
        var modalInstance = $modal.open({
            templateUrl: 'createTournamentModal.html',
            controller: ModalInstanceCtrl,
            resolve: {
                players: function () {
                    return $scope.players;
                }
            }
        });

        modalInstance.result.then(function (tournamentUuid) {
            $location.path('/event/' + $routeParams.uuid + '/tournament/' + tournamentUuid);
        });
    };

    var ModalInstanceCtrl = function ($scope, $modalInstance, players) {

        $scope.tournament = {
            eventUuid: $routeParams.uuid,
            ante: 20,
            results: {}
        };
        $scope.players = players;

        $scope.startGame = function () {
            Tournaments.save(this.tournament).$promise.then(function (tournament) {
                console.log(tournament);
                $modalInstance.close(tournament.uuid);
            });
        };

        $scope.cancel = function () {
            $modalInstance.dismiss('cancel');
        };
    };

});