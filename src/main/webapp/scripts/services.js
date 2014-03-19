'use strict';

var services = angular.module('pokerstatsApp.services');

services.factory('Foo', function ($resource) {
    return $resource('pokerstats/rest/foo', {}, {
    });
});
