'use strict';

var controllers = angular.module('pokerstatsApp.controllers');

controllers.controller('TournamentController', function TournamentController($scope, $routeParams, $http, Tournament,
                                                                             $modal, TournamentResults, Utils) {

    $scope.tournament = {};
    $scope.playersEliminated = [];
    $scope.playersInTheGame = [];
    $scope.anteTotal = 0;
    Tournament.get({uuid: $routeParams.tournamentUuid}).$promise.then(function (tournament) {
        $scope.tournament = tournament;
        TournamentResults.query({uuid: tournament.uuid}).$promise.then(function (results) {
            angular.forEach(results, function (result) {
                $scope.anteTotal += result.ante;
                if (angular.isUndefined(result.eliminatedByPlayerUuid)) {
                    $scope.playersInTheGame.push(result);
                } else {
                    $scope.playersEliminated.push(result);
                }
            });
            $scope.players = $scope.playersEliminated.concat($scope.playersInTheGame);
        });
    });

    $scope.eliminatedPlayer = function (playerUuid) {
        var eliminatedPlayer = {};
        angular.forEach($scope.playersInTheGame, function (player) {
            if (player.uuid === playerUuid) {
                eliminatedPlayer = player;
            }
        });
        $http.put('/rest/1.0/tournament/' + $scope.tournament.uuid + '/result/' + playerUuid, eliminatedPlayer);

        console.log('TODO open dialog to "eliminate" player with id=' + playerUuid);
    };

    $scope.endGame = function () {
        $http.put('/rest/1.0/tournament/' + $scope.tournament.uuid).success(function (tournament) {
            $scope.tournament = tournament;
        });
    };

    $scope.openEliminatePlayerModal = function (playerUuid) {

        var eliminatedPlayer = {};
        angular.forEach($scope.playersInTheGame, function (player) {
            if (player.uuid === playerUuid) {
                eliminatedPlayer = player;
            }
        });

        var modalInstance = $modal.open({
            templateUrl: 'eliminatePlayer.html',
            controller: ModalInstanceCtrl,
            resolve: {
                eliminatedPlayer: function () {
                    return eliminatedPlayer;
                },
                playersInTheGame: function () {
                    return $scope.playersInTheGame;
                }
            }
        });

        modalInstance.result.then(function (eliminatedPlayer) {
            Utils.findAndRemove($scope.playersInTheGame, 'uuid', eliminatedPlayer.uuid);
            $scope.playersEliminated.push(eliminatedPlayer);
        });
    };

    var ModalInstanceCtrl = function ($scope, $modalInstance, eliminatedPlayer, playersInTheGame) {

        $scope.player = eliminatedPlayer;
        $scope.playersInTheGame = playersInTheGame;

        $scope.ok = function () {
            $http.put('/rest/1.0/tournament/' + eliminatedPlayer.tournamentUuid + '/result/' + eliminatedPlayer.uuid, eliminatedPlayer);
            $modalInstance.close(eliminatedPlayer);
        };

        $scope.cancel = function () {
            $modalInstance.dismiss('cancel');
        };
    };

});