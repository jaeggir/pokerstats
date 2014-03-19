'use strict';

var directives = angular.module('pokerstatsApp.directives');

directives.directive('navbar', function ($location) {
    return {
        restrict: 'A',
        link: function link(scope, element) {
            var listener = function () {
                return $location.path();
            };
            var changeHandler = function (newValue) {
                $('li[data-match-route]', element).each(function (k, li) {
                    var $li = angular.element(li);
                    var pattern = $li.attr('data-match-route');
                    var regexp = new RegExp('^' + pattern + '$', 'i');
                    if (regexp.test(newValue)) {
                        $li.addClass('active');
                    } else {
                        $li.removeClass('active');
                    }
                });
            };
            scope.$watch(listener, changeHandler);
        }
    };
});

    