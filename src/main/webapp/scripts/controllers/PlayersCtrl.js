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