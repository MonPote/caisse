/**
 * Created by nea on 30/09/16.
 */
'use strict';

/* Filters */

var AppFilters = angular.module('MyApp.filters', []);

AppFilters.filter('interpolate', ['version', function (version) {
    return function (text) {
        return String(text).replace(/\%VERSION\%/mg, version);
    }
}]);
