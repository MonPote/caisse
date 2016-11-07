/**
 * Created by nea on 30/09/16.
 */
'use strict';

/**
 * ProductController
 * @constructor
 */
App.controller("productController", function($scope, $http) {
    $scope.callHandshake = function () {
        $http.get("/BO/api/handshake").success(function(data) {
            console.log(data);
        });
    };

    $scope.sendAgenda = function () {
        $http.get("/BO/api/sendAgenda").success(function(data) {
            console.log("data = ", data);
            $http.post("http://st/api/send_agenda", data).success(function () {
            });
        });
    };

    $scope.getMessageGet = function () {
        $http.get("/BO/api/msg?fct=toto").success(function(data) {
            console.log(data);
        });
    };

    $scope.getMessagePost = function () {
        var toto = "toto";
        $http.post("/BO/api/msg", toto).success(function(data) {
            console.log(data);
        });
    };
});