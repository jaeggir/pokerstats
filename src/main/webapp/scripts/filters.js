'use strict';

var filters = angular.module('pokerstatsApp.filters');

filters.filter('defaultDateWithWeekday', function defaultDate($filter) {
    return function (dateString) {
        return $filter('date')(dateString, 'EEE dd. MMMM yyyy');
    };
});

filters.filter('defaultDate', function defaultDate($filter) {
    return function (dateString) {
        return $filter('date')(dateString, 'dd. MMMM yyyy');
    };
});

filters.filter('defaultDateTime', function defaultDateTime($filter) {
    return function (dateString) {
        return $filter('date')(dateString, 'dd. MMMM yyyy HH:mm');
    };
});

filters.filter('onlyActivePlayers', function onlyActivePlayers() {
    return function (items) {
        if (angular.isArray(items)) {
            var result = [];
            angular.forEach(items, function (item) {
                if (angular.isUndefined(item.eliminatedByPlayerUuid)) {
                    result.push(item);
                }
            });
            return result;
        } else {
            return [];
        }
    };
});

filters.filter('onlyEliminatedPlayers', function onlyEliminatedPlayers() {
    return function (items) {
        if (angular.isArray(items)) {
            var result = [];
            angular.forEach(items, function (item) {
                if (!angular.isUndefined(item.eliminatedByPlayerUuid)) {
                    result.push(item);
                }
            });
            return result;
        } else {
            return [];
        }
    };
});