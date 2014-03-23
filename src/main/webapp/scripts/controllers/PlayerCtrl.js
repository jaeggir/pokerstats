'use strict';

var controllers = angular.module('pokerstatsApp.controllers');

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