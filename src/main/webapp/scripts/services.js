'use strict';

var services = angular.module('pokerstatsApp.services');

services.service('Utils', function () {

    return {

        findAndRemove: function (array, property, value) {
            $.each(array, function (index, item) {
                if (!angular.isUndefined(item) && item[property] === value) {
                    // remove from array
                    array.splice(index, 1);
                }
            });
        }
    };
});