'use strict';

var controllers = angular.module('pokerstatsApp.controllers');

controllers.controller('EventsController', function EventsController($scope, $modal, Seasons, Events, Players, Venues) {

    $scope.currentSeason = null;
    Seasons.query().$promise.then(function (seasons) {
        $scope.seasons = seasons;
        angular.forEach(seasons, function (season) {
            if (season.current) {
                $scope.currentSeason = season;
            }
            season.events = Events.query({seasonUuid: season.uuid});
        });
    });
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
            $scope.currentSeason.events.push(event);
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
