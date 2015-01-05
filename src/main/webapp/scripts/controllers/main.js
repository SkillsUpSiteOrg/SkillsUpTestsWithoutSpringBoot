/*
"use strict";
*/

angular.module('SkillsUpTests')
  .controller('MainCtrl',function ($scope, localStorageService, $http) {
    var testsInStore = localStorageService.get('tests');
    //$scope.tests = testsInStore || [];

    $scope.getAllTests = function () {
        $http.get('/getAllTestDescriptions').success(function(data) {
            console.log(data);
            $scope.tests = data;
            localStorageService.set('tests', $scope.tests);

        })
    };

    $scope.addNewTest = function() {
        console.log($scope.testName);
        /*console.log($scope.dateOfCreation);*/
        console.log($scope.maxTimeToPassInMinutes);
        $http({
            method: 'POST',
            url: '/addNewTestDescription',
            data: $.param({"testName":$scope.testName, "maxTimeToPassInMinutes":$scope.maxTimeToPassInMinutes}),
            headers: {'Content-Type': 'application/x-www-form-urlencoded'}
        })
            .success(function(data) {
            $scope.message = data;
                console.log($scope.message);
        });
        $scope.testName = '';
        $scope.maxTimeToPassInMinutes = '';
    };

  });
