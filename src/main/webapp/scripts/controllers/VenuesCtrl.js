'use strict';

var controllers = angular.module('pokerstatsApp.controllers');

controllers.controller('VenuesController', function VenuesController($scope, Venues, angulargmContainer) {

    $scope.venues = [];
    var gMapInstance = null;

    var gMapPromise = angulargmContainer.getMapPromise('venuesMap');
    gMapPromise.then(function (gMap) {

        gMapInstance = gMap;

        gMap.setCenter(new google.maps.LatLng(47.5, 8.53));
        gMap.setZoom(12);
        gMap.setMapTypeId(google.maps.MapTypeId.ROADMAP);

        var bounds = new google.maps.LatLngBounds();

        Venues.query().$promise.then(function (venues) {
            $scope.venues = venues;

            angular.forEach(venues, function (venue) {
                bounds.extend(new google.maps.LatLng(venue.latitude, venue.longitude));
            });

            gMap.fitBounds(bounds);

            $scope.$broadcast('gmMarkersUpdate', 'venues');
        });

    });

    $scope.centerToMarkerAndOpenInfoWindow = function (venue) {
        // FIX open info window
        gMapInstance.setCenter(new google.maps.LatLng(venue.latitude, venue.longitude));
        $scope.markerEvents = [{
            event: 'openinfowindow',
            ids: [venue.uuid]
        }];
    };

});