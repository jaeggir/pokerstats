'use strict';

var controllers = angular.module('pokerstatsApp.controllers');

controllers.controller('MainController', function MainController($scope) {

});

controllers.controller('PlayersController', function PlayersController($scope, playersResource) {

    $scope.players = {};
    playersResource.query().$promise.then(function(players) {
        $scope.players = players;
    });

});