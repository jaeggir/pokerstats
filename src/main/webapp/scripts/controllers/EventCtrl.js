'use strict';

var controllers = angular.module('pokerstatsApp.controllers');

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

    $scope.isAvailable = function () {
        // returns true if the event date is today or later
        return $scope.event !== null && $scope.event.date > (new Date().getTime() - 24 * 60 * 60 * 1000);
    };

    $scope.addTournament = function () {
        console.log('TODO implement add Tournament.');
    };

});