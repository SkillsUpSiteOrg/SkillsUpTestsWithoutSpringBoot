/*
"use strict";
*/

angular.module('SkillsUpTests')
  .controller('MainCtrl', function ($scope, localStorageService) {
    var testsInStore = localStorageService.get('tests');
    $scope.tests = testsInStore || [];
	/*var phonecatApp = angular.module('phonecatApp', []);
 
	phonecatApp.controller('PhoneListCtrl', function ($scope, $http) {
	  $http.get('phones/phones.json').success(function(data) {
		$scope.phones = data;
	  });
	 
	});*/

	$scope.$watch('tests', function () {
	  localStorageService.set('tests', $scope.tests);
	}, true);
	
	$scope.addTest = function () {
	  $scope.tests.push($scope.test);
	  $scope.test = '';
	};
	$scope.removeTest = function (index) {
	  $scope.tests.splice(index, 1);
	};
  });
