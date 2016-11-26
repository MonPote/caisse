/**
 * Created by nea on 30/09/16.
 */
'use strict';

/**
 * ProductController
 * @constructor
 */
App.controller("productController", function($scope, $http) {
    $http.get("/BO/api/appip").success(function(data) {
        console.log('dataip = ', data.appip);
        $scope.model = data;
    });

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

    $scope.fetchProductsList = function() {
        $http.get('productList.json').success(function(productList){
            $scope.products = productList;
            console.log(productList.length);
        });
    };

    $scope.addNewProduct = function(newProduct) {
        $http.post('addProduct/' + newProduct).success(function() {
            $scope.fetchProductsList();
        });
        $scope.productName = '';
    };

    $scope.removeProduct = function(product) {
        $http.delete('removeProduct/' + product).success(function() {
            $scope.fetchProductsList();
        });
    };

    $scope.fetchProductsList();

    /**
     * Brands parts
     */
    $scope.fetchBrandsList = function() {
        $http.get('brandList.json').success(function(brandList){
            $scope.brands = brandList;
            console.log(brandList.length);
        });
    };

    $scope.addNewBrand = function(newBrand) {
        console.log("hello " + newBrand);
        $http.post('addBrand/' + newBrand).success(function() {
            $scope.fetchBrandsList();
        });
        $scope.brandName = '';
    };

    $scope.removeBrand = function(brand) {
        $http.delete('removeBrand/' + brand).success(function() {
            $scope.fetchBrandsList();
        });
    };

    $scope.fetchBrandsList();

    /**
     * Localization parts
     */
    $scope.fetchLocalizationsList = function() {
        $http.get('localizationList.json').success(function(localizationList){
            $scope.localizations = localizationList;
            console.log(localizationList.length);
        });
    };

    $scope.addNewLocalization = function(newLocalization) {
        $http.post('addLocalization/' + newLocalization).success(function() {
            $scope.fetchLocalizationsList();
        });
        $scope.localizationName = '';
    };

    $scope.removeLocalization = function(localization) {
        $http.delete('removeLocalization/' + localization).success(function() {
            $scope.fetchLocalizationsList();
        });
    };

    $scope.fetchLocalizationsList();

    /**
     * Store parts
     */
    $scope.fetchStoresList = function() {
        $http.get('storeList.json').success(function(storeList){
            $scope.warhouses = storeList;
            console.log(storeList.length);
        });
    };

    $scope.addNewStore = function(newStore) {
        $http.post('addStore/' + newStore).success(function() {
            $scope.fetchStoresList();
        });
        $scope.storeName = '';
    };

    $scope.removeStore = function(store) {
        $http.delete('removeStore/' + store).success(function() {
            $scope.fetchStoresList();
        });
    };

    $scope.fetchStoresList();

    /**
     * Categories parts
     */
    $scope.fetchCategoriesList = function() {
        $http.get('categoryList.json').success(function(categoryList){
            $scope.categories = categoryList;
            console.log(categoryList.length);
        });
    };

    $scope.addNewCategory = function(newCategory) {
        $http.post('addCategory/' + newCategory).success(function() {
            $scope.fetchCategorysList();
        });
        $scope.categoryName = '';
    };

    $scope.removeCategory = function(category) {
        $http.delete('removeCategory/' + category).success(function() {
            $scope.fetchCategoriesList();
        });
    };

    $scope.fetchCategoriesList();

    $scope.sendStockData = function () {
        $http.get('productList.json').success(function(productList){
            alert (JSON.stringify(productList));
            console.log(productList);
        });
    };
});