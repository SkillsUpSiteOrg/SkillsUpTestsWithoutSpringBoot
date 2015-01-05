/*
"use strict";
*/

angular.module('SkillsUpTests')
  .controller('MainCtrl',function ($scope, localStorageService, $http) {
    var testsInStore = localStorageService.get('tests');
    $scope.tests = testsInStore || [];

    $scope.getAllTests = function () {
        $http.get('/getAllTestDescriptions').success(function(data) {
            console.log(data);
            $scope.tests = data;
            /*angular.forEach(data, function(TestsItem) {
                console.log(TestsItem);
                $scope.tests.push(TestsItem);
            });*/
            localStorageService.set('tests', $scope.tests);

        })
    };

        $scope.addNewTest = function() {
           /* $scope.testName = $("#testNameInput").val();
            $scope.dateOfCreation = $("#testCreationDatePicker").datepicker({dateFormat: 'dd,MM,yyyy'}).val();
            $scope.maxTimeToPassInMinutes = $("#testTimeToPassInput").val();*/
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

	/*$scope.$watch('tests', function () {
	  localStorageService.set('tests', $scope.tests);
	}, true);
	
	$scope.addTest = function () {
	  $scope.tests.push($scope.test);
	  $scope.test = '';
	};
	$scope.removeTest = function (index) {
	  $scope.tests.splice(index, 1);
	};*/


  });
