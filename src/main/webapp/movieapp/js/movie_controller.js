var movieapp = angular.module('movieapp', [ 'ngRoute', 'ngResource']);

movieapp.run(function($rootScope) {
	$rootScope.OnNavTabs = false;
    $rootScope.OffNavTabs = true;
    //$rootScope.image_path = "{'background-image':'url(/movieapp/img/background_blur.jpg)'}";
    $rootScope.image_path1 = "{'background-image':'url(/movieapp/img/background_blur.jpg)'}";
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

movieapp.directive('passwordMatch', [function () {
    return {
        restrict: 'A',
        scope:true,
        require: 'ngModel',
        link: function (scope, elem , attrs,control) {
            var checker = function () {
 
                //get the value of the first password
                var e1 = scope.$eval(attrs.ngModel); 
 
                //get the value of the other password  
                var e2 = scope.$eval(attrs.passwordMatch);
                return e1 == e2;
            };
            scope.$watch(checker, function (n) {
 
                //set the form control to valid if both 
                //passwords are the same, else invalid
                control.$setValidity("unique", n);
            });
        }
    };
}]);

// configure our routes
movieapp.config(function($routeProvider) {
	$routeProvider

	// route for the welcome page
	.when('/', {
		templateUrl : 'home.html',
		controller : 'homeController'
	})
	
	.when('/register', {
		templateUrl : 'register.html',
		controller : 'registerController'
	})
	
	.when('/home', {
		templateUrl : 'HomeUser.html',
		controller : 'homeUserController'
	})
	
	.when('/homeadmin', {
		templateUrl : 'HomeAdmin.html',
		controller : 'homeAdminController'
	})
	
	.otherwise({
		redirectTo : '/'
	});
});

movieapp.controller('homeController',
	 				function($scope, $http, $location, $q, dataSharing, $timeout, $rootScope) {
	console.log('homeController start');
	
	$scope.loginform_login = function(item, event) {
		console.log("--> Submitting form "
				+ $scope.loginform_email + " "
				+ $scope.loginform_password);
		console.log("--> Submitting form ");
		var data = {
			email : $scope.loginform_email,
			password : $scope.loginform_password
		};
		$location.url('/home');
		/*var response = $http.post("../../api/v1/users", data,
				{});
		response
				.success(function(dataFromServer, status,
						headers, config) {
					if (dataFromServer.message == null
							|| dataFromServer.message == "") {
						$scope.signupform_success = "User created successfully";
					} else {
						$scope.signupform_error = dataFromServer.message;
					}
				});
		response.error(function(data, status, headers, config) {
			if (response.status === 401
					|| response.status === 400) {
				$scope.loginform_error = "Invalid request";
				$location.url('/');
				return $q.reject(response);
			}
		});*/
	};
	
	$scope.clickRegister = function(){
		$location.url('/register');
	}


	console.log('homeController end');
});


movieapp.controller('registerController',
			function($scope, $http, $location, $q, dataSharing, $timeout, $rootScope) {
	console.log('registerController start');
	$rootScope.OnNavTabs = false;
    $rootScope.OffNavTabs = true;
	
	$scope.signupform_signup = function(item, event) {
		console.log("--> Submitting form "
				+ $scope.signupform_email + " "
				+ $scope.signupform_password);
		console.log("--> Submitting form "
				+ $scope.signupform_phone + " "
				+ $scope.signupform_address);
		console.log("--> Submitting form "
				+ $scope.signupform_city + " "
				+ $scope.signupform_state+" "
				+ $scope.signupform_country);
		console.log("--> Submitting form ");
		var data = {
			name : $scope.signupform_name,
			email : $scope.signupform_email,
			phone : $scope.signupform_phone,
			address : $scope.signupform_address,
			city : $scope.signupform_city,
			state : $scope.signupform_state,
			country : $scope.signupform_country
		};
		/*var response = $http.post("../../api/v1/users", data,
				{});
		response
				.success(function(dataFromServer, status,
						headers, config) {
					if (dataFromServer.message == null
							|| dataFromServer.message == "") {
						$scope.signupform_success = "User created successfully";
					} else {
						$scope.signupform_error = dataFromServer.message;
					}
				});
		response.error(function(data, status, headers, config) {
			if (response.status === 401
					|| response.status === 400) {
				$scope.loginform_error = "Invalid request";
				$location.url('/');
				return $q.reject(response);
			}
		});*/
	};
	console.log('registerController end');
});


movieapp.controller('homeUserController',
			function($scope, $http, $location, $q, dataSharing, $timeout, $rootScope) {
	console.log('homeUserController start');
	$rootScope.OnNavTabs = true;
    $rootScope.OffNavTabs = false;
	
	
	console.log('homeUserController end');
});

movieapp.controller('homeAdminController',
		function($scope, $http, $location, $q, dataSharing, $timeout, $rootScope) {
	console.log('homeAdminController start');
	$rootScope.OnNavTabs = true;
    $rootScope.OffNavTabs = false;
	
	console.log('homeAdminController end');
});
