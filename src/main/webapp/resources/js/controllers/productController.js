/**
 * Created by nea on 30/09/16.
 */
'use strict';

/**
 * ProductController
 * @constructor
 */
App.controller("productController", function($scope, $http) {
    $http.get("/BO/api/getIp").success(function(data) {
        console.log('dataip = ', data.appip);
        $scope.model = data;
    });

    $scope.callHandshake = function () {
        $http.get("/BO/api/handshake").success(function(data) {
            console.log(data);
        });
    };

    $scope.getMessageGet = function () {
        $http.get("/BO/testMsg").success(function(data) {
            console.log(data);
        });
    };

    $scope.getMessagePost = function () {
        $http.post("/BO/testService").success(function(data) {
            console.log(data);
        });
    };

    $scope.getSendFile = function () {
        $http.post("/BO/testFile").success(function(data) {
            console.log(data);
        });
    };

    $scope.checkFile = function() {
        $http.post("/BO/checkFile").success(function(data) {
            console.log(data);
        });
    };
});