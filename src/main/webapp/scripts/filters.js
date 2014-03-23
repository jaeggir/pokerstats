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