/**
 * Created by nea on 30/09/16.
 */
'use strict';

var MyApp = {};

var App = angular.module('MyApp', ['MyApp.filters', 'MyApp.services', 'MyApp.directives']);

// Declare app level module which depends on filters, and services
/*App.config(['$routeProvider', function ($routeProvider) {
    $routeProvider.when('/product', {
        templateUrl: 'product/layout',
        controller: ProductController
    });

    $routeProvider.otherwise({redirectTo: '/'});
}]);*/