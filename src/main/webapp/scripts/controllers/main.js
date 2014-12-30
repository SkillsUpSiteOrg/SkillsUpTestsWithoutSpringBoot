/*
"use strict";
*/

var skillsUpTestsApp = angular.module('SkillsUpTests', []);

skillsUpTestsApp.controller('TestListController', ['$scope', '$http', function($scope, $http) {
    $http.get('/getAllTestDescriptions').success(function(data) {
        $scope.tests = data;
        console.log(data);
    });

    /*$scope.showDate = showDate;
    function showDate($scope, dateOfCreationFromController){
        console.log(dateOfCreationFromController);
        var dateOfCreationNotFormatted = new Date(dateOfCreationFromController);
        var dateOfCreation = dateOfCreationNotFormatted.getFullYear() + "-" + dateOfCreationNotFormatted.getMonth() + "-" +
            dateOfCreationNotFormatted.getDate();
        console.log(dateOfCreation);
        return dateOfCreation;
    }*/

    $scope.getAllTests = function() {
        $http.get('/getAllTestDescriptions').success(function(data) {
            $scope.tests = data;
            console.log(data);
        });
    };

    $scope.addNewTest = function() {
        var testName = $("#testNameInput").val();
        var dateOfCreation = $("#testCreationDatePicker").datepicker({dateFormat: 'dd,MM,yyyy'}).val();
        var maxTimeToPassInMinutes = $("#testTimeToPassInput").val();
        console.log(testName);
        console.log(dateOfCreation);
        console.log(maxTimeToPassInMinutes);
        $http({
            method: 'POST',
            url: '/addNewTestDescription',
            data: $.param({"testName":testName, "dateOfCreation":dateOfCreation, "maxTimeToPassInMinutes":maxTimeToPassInMinutes}),
            headers: {'Content-Type': 'application/x-www-form-urlencoded'}
        }).success(function(data) {
            //$scope.tests = data;
            console.log(data);
        });
    };
}]);
