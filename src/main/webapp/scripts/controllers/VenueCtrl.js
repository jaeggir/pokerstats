'use strict';

var controllers = angular.module('pokerstatsApp.controllers');

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