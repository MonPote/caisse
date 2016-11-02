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
            $http.post("http://st/api/handshake", data).success(function () {
            });
        });

    }
});