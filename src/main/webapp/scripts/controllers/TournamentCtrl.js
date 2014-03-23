'use strict';

var controllers = angular.module('pokerstatsApp.controllers');

controllers.controller('TournamentController', function TournamentController($scope, $routeParams, Tournament,
                                                                             TournamentResults) {

    $scope.tournament = {};
    $scope.results = [];
    Tournament.get({uuid: $routeParams.uuid}).$promise.then(function (tournament) {
        $scope.tournament = tournament;
        $scope.results = TournamentResults.query({uuid: tournament.uuid});
    });

});