'use strict';

var filters = angular.module('pokerstatsApp.filters');

filters.filter('defaultDate', function defaultDate() {
    return function (dateString) {
        return moment(dateString).format('Do MMMM YYYY');
    };
});

filters.filter('defaultDateTime', function defaultDateTime() {
    return function (dateString) {
        return moment(dateString).format('Do MMMM YYYY, h:mm');
    };
});

filters.filter('filterBySeason', function filterBySeason() {
    return function (season) {
        return season;
    };
});