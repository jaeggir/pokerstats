'use strict';

var controllers = angular.module('pokerstatsApp.controllers');

controllers.controller('TournamentController', function TournamentController($scope, $routeParams, Tournament,
                                                                             TournamentResults) {

    $scope.tournament = {};
    $scope.results = [];
    $scope.anteTotal = 0;
    Tournament.get({uuid: $routeParams.tournamentUuid}).$promise.then(function (tournament) {
        $scope.tournament = tournament;
        TournamentResults.query({uuid: tournament.uuid}).$promise.then(function (results) {
            $scope.results = results;
            angular.forEach($scope.results, function (result) { $scope.anteTotal += result.ante; });
        });
    });

    $scope.killPlayer = function (playerUuid) {
        console.log('TODO open dialog to "kill" player with id=' + playerUuid);
    };

});