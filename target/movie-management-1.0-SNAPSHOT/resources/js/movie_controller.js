var movieapp = angular.module('movieapp', [ 'ngRoute', 'ngResource']);

movieapp.run(function($rootScope) {
    $rootScope.OffNavTabs = true;
    $rootScope.OffLogoutTabs = true;
});

//factory for sharing data between controller
movieapp.factory('dataSharing', function() {
	 var sharedData = {}
	 function set(data) {
		 sharedData = data;
	 }
	 function get() {
		 return sharedData;
	 }

	 return {
	  set: set,
	  get: get
	 }

});

// configure our routes
movieapp.config(function($routeProvider) {
	$routeProvider

	// route for the welcome page
	.when('/', {
		templateUrl : 'home.html',
		controller : 'homeController'
	})
	
	.otherwise({
		redirectTo : '/'
	});
});

movieapp.controller('homeController', function($scope, $rootScope, $http, $location) {
	// create a message to display in our view
	console.log('homeController start');
	
	console.log('homeController end');
	
});