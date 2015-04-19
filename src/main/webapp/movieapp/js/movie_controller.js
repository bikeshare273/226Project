var movieapp = angular.module('movieapp', [ 'ngRoute', 'ngResource', 'smart-table' ]);

movieapp.run(function($rootScope) {
	$rootScope.hideUserNavTabs = true;
    $rootScope.hideStaticTabs = false;
    $rootScope.hideAdminNavTabs = true;
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
	
	.when('/addMovie', {
		templateUrl : 'addmovie.html',
		controller : 'addMovieController'
	})
	
	.when('/adminMovieSearch', {
		templateUrl : 'adminMovieSearchResult.html',
		controller : 'adminMovieSearchController'
	})
	
	.when('/deleteMovie', {
		templateUrl : 'deleteMovie.html',
		controller : 'deleteMovieController'
	})
	
	.when('/updateMovie', {
		templateUrl : 'updateMovie.html',
		controller : 'updateMovieController'
	})
	
	.when('/profile', {
		templateUrl : 'updateUser.html',
		controller : 'updateUserController'
	})
	
	.when('/movieSearch', {
		templateUrl : 'movieSearchResult.html',
		controller : 'serachMovieController'
	})
	
	.otherwise({
		redirectTo : '/'
	});
});

movieapp.controller('homeController',
	 				function($scope, $http, $location, $q, dataSharing, $timeout, $rootScope) {
	console.log('homeController start');
	$rootScope.hideUserNavTabs = true;
    $rootScope.hideStaticTabs = false;
    $rootScope.hideAdminNavTabs = true;
	
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
		//$location.url('/homeadmin');
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
	$rootScope.hideUserNavTabs = true;
    $rootScope.hideStaticTabs = false;
    $rootScope.hideAdminNavTabs = true;
	
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
	$rootScope.hideUserNavTabs = false;
    $rootScope.hideStaticTabs = true;
    $rootScope.hideAdminNavTabs = true;
	
	console.log('homeUserController end');
});

movieapp.controller('homeAdminController',
		function($scope, $http, $location, $q, dataSharing, $timeout, $rootScope) {
	console.log('homeAdminController start');
	$rootScope.hideUserNavTabs = true;
    $rootScope.hideStaticTabs = true;
    $rootScope.hideAdminNavTabs = false;
    

    $scope.searchMovie = function(item, event) {
		console.log("--> Submitting searching form "
				+ $scope.serach + " "
				+ $scope.serachCriteria);
		console.log("--> Submitting form ");
		var data = {
			search : $scope.serach,
			serachCriteria : $scope.serachCriteria
		};
		$location.url('/adminMovieSearch');
	};
	
	$scope.addMovieLink = function(item, event) {
		$location.url('/addMovie');
	};
	
	console.log('homeAdminController end');
});

movieapp.controller('addMovieController',
		function($scope, $http, $location, $q, dataSharing, $timeout, $rootScope) {
	console.log('addMovieController start');
	$rootScope.hideUserNavTabs = true;
    $rootScope.hideStaticTabs = true;
    $rootScope.hideAdminNavTabs = false;
    

    $scope.movieaddform_addMovie = function(item, event) {
		console.log("--> Submitting searching form "
				+ $scope.serach + " "
				+ $scope.serachCriteria);
		console.log("--> Submitting form ");
		var data = {
			search : $scope.signupform_name,
			serachCriteria : $scope.signupform_email
		};
		$scope.movieaddform_success = "Movie Added Successfully";
	};
	
	console.log('addMovieController end');
});

movieapp.controller('adminMovieSearchController',
		function($scope, $http, $location, $q, dataSharing, $timeout, $rootScope) {
	console.log('adminMovieSearchController start');
	$rootScope.hideUserNavTabs = true;
    $rootScope.hideStaticTabs = true;
    $rootScope.hideAdminNavTabs = false;
    

    $scope.queue = {
        transactions: []
    };
    
    var dataFromServer = new Array();
    for (var i = 0; i < 10; i++) {
    	dataFromServer[i] = {
    		id:i,
    		movieid: "10",
    		category: "3",
    		name:"piku",
    		actors:"abc, def",
    		description:"A cab driver (Irrfan Khan) is caught between a dysfunctional father (Amitabh Bachchan) and daughter (Deepika Padukone) as he drives them to Calcutta."
    	};
        $scope.queue.transactions.push(dataFromServer[i]);
    }
    $scope.itemsByPage=4;
    
    $scope.deleteMovie = function(movieid) {
		console.log("--> Submitting form "
				+ movieid);
		for(var i = 0; i < 10; i++){
			if(dataFromServer[i].movieid == movieid){
				dataSharing.set(dataFromServer[i]);
			}
		}
		$location.url('/deleteMovie');
    };
    
    $scope.updateMovie = function(movieid) {
		console.log("--> Submitting form "
				+ movieid);
		for(var i = 0; i < 10; i++){
			if(dataFromServer[i].movieid == movieid){
				dataSharing.set(dataFromServer[i]);
			}
		}
		$location.url('/updateMovie');
    };
	
	console.log('adminMovieSearchController end');
});

movieapp.controller('deleteMovieController',
		function($scope, $http, $location, $q, dataSharing, $timeout, $rootScope) {
	console.log('deleteMovieController start');
	$rootScope.hideUserNavTabs = true;
    $rootScope.hideStaticTabs = true;
    $rootScope.hideAdminNavTabs = false;
    
    $scope.moviedeleteform_movieid = dataSharing.get().movieid;
    $scope.moviedeleteform_movieCategory = dataSharing.get().category;
    $scope.moviedeleteform_moviename = dataSharing.get().name;
    $scope.moviedeleteform_moviedescription = dataSharing.get().description;
    $scope.moviedeleteform_actors = dataSharing.get().actors;

    $scope.moviedeleteform_deleteMovie = function() {
		console.log("--> Submitting deleteMovie form "
				+ $scope.moviedeleteform_movieid);
		console.log("--> Submitting form ");
		var data = {
			movieid : $scope.moviedeleteform_movieid
		};
		$scope.moviedeleteform_success = "Movie Deleted Successfully";
	};
	
	console.log('deleteMovieController end');
});

movieapp.controller('updateMovieController',
		function($scope, $http, $location, $q, dataSharing, $timeout, $rootScope) {
	console.log('updateMovieController start');
	$rootScope.hideUserNavTabs = true;
    $rootScope.hideStaticTabs = true;
    $rootScope.hideAdminNavTabs = false;
    
    $scope.movieupdateform_movieid = dataSharing.get().movieid;
    $scope.movieupdateform_movieCategory = dataSharing.get().category;
    $scope.movieupdateform_moviename = dataSharing.get().name;
    $scope.movieupdateform_moviedescription = dataSharing.get().description;
    $scope.movieupdateform_actors = dataSharing.get().actors;

    $scope.movieupdateform_updateMovie = function() {
		console.log("--> Submitting updateMovie form "
				+ $scope.movieupdateform_movieid);
		console.log("--> Submitting form ");
		var data = {
			movieid : $scope.movieupdateform_movieid
		};
		$scope.movieupdateform_success = "Movie Updated Successfully";
	};
	
	console.log('updateMovieController end');
});

movieapp.controller('updateUserController',
		function($scope, $http, $location, $q, dataSharing, $timeout, $rootScope) {
	console.log('updateUserController start');
	$rootScope.hideUserNavTabs = false;
    $rootScope.hideStaticTabs = true;
    $rootScope.hideAdminNavTabs = true;
    
    //get here to obtain user data
    $scope.updateuserform_email_old = "xyz@abc.net";
    $scope.updateuserform_phone_old = "000-000-0000";
    $scope.updateuserform_address_old = "#17, xyz, 123";
    $scope.updateuserform_city_old = "San Jose";
    $scope.updateuserform_state_old = "California";
    $scope.updateuserform_country_old = "USA";
    
    $scope.updateuserform_updateUser = function() {
    	console.log("--> Submitting form "
				+ $scope.updateuserform_email + " "
				+ $scope.updateuserform_password);
		console.log("--> Submitting form "
				+ $scope.updateuserform_phone + " "
				+ $scope.updateuserform_address);
		console.log("--> Submitting form "
				+ $scope.updateuserform_city + " "
				+ $scope.updateuserform_state+" "
				+ $scope.updateuserform_country);
		console.log("--> Submitting form ");
		var data = {
			movieid : $scope.movieupdateform_movieid
		};
		$scope.movieupdateform_success = "User Details Updated Successfully";
	};
	
	console.log('updateUserController end');
});


movieapp.controller('serachMovieController',
		function($scope, $http, $location, $q, dataSharing, $timeout, $rootScope) {
	console.log('serachMovieController start');
	$rootScope.hideUserNavTabs = false;
    $rootScope.hideStaticTabs = true;
    $rootScope.hideAdminNavTabs = true;
    
    $scope.movieSearch = function(item, event) {
		console.log("--> Submitting searching form "
				+ $scope.serach + " "
				+ $scope.serachCriteria);
		console.log("--> Submitting form ");
		var data = {
			search : $scope.serach,
			serachCriteria : $scope.serachCriteria
		};
		$location.url('/movieSearch');
	};
	
	console.log('serachMovieController end');
});

movieapp.controller('movieSearchResultController',
		function($scope, $http, $location, $q, dataSharing, $timeout, $rootScope) {
	console.log('movieSearchResultController start');
	$rootScope.hideUserNavTabs = false;
    $rootScope.hideStaticTabs = true;
    $rootScope.hideAdminNavTabs = true;
    
    $scope.queue = {
        transactions: []
    };
    
    var dataFromServer = new Array();
    for (var i = 0; i < 100; i++) {
    	dataFromServer[i] = {
    		id:i,
    		movieid: "10",
    		category: "3",
    		name:"piku",
    		actors:"abc, def",
    		description:"A cab driver (Irrfan Khan) is caught between a dysfunctional father (Amitabh Bachchan) and daughter (Deepika Padukone) as he drives them to Calcutta."
    	};
        $scope.queue.transactions.push(dataFromServer[i]);
    }
    $scope.itemsByPage=6;
	
	console.log('movieSearchResultController end');
});

