/**
 * Created by nea on 30/09/16.
 */
'use strict';

/* Directives */

var AppDirectives = angular.module('MyApp.directives', []);

AppDirectives.directive('appVersion', ['version', function (version) {
    return function (scope, elm, attrs) {
        elm.text(version);
    };
}]);