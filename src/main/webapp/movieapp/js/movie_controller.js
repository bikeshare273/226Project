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
	
	.when('/playMovie', {
		templateUrl : 'playMovie.html',
		controller : 'playMovieController'
	})
	
	// logout
	.when('/logout', {
		templateUrl : 'home.html',
		controller : 'logoutController'
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
			username : $scope.loginform_email,
			password : $scope.loginform_password
		};
		//$location.url('/home');
		//$location.url('/homeadmin');
		if($scope.loginform_email == "admin@localhost" && $scope.loginform_password == "admin"){
			$location.url('/homeadmin');
		}else{
			var response = $http.post("../../api/v1/login", data,
					{});
			response
					.success(function(dataFromServer, status,
							headers, config) {
						$location.url('/home');
					});
			response.error(function(data, status, headers, config) {
				if (response.status === 401
						|| response.status === 400) {
					$scope.loginform_error = "Invalid request";
					$location.url('/');
					return $q.reject(response);
				}
			});
		}
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
				+ $scope.signupform_name + " "
				+ $scope.signupform_email );
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
			mobile_number : $scope.signupform_phone,
			password: $scope.signupform_password,
			address : $scope.signupform_address,
			city : $scope.signupform_city,
			state : $scope.signupform_state,
			country : $scope.signupform_country
		};
		var response = $http.post("../../api/v1/users", data,
				{});
		response
				.success(function(dataFromServer, status,
						headers, config) {
					$scope.signupform_success = "User created successfully";
				});
		response.error(function(data, status, headers, config) {
			if (response.status === 401
					|| response.status === 400) {
				$scope.loginform_error = "Invalid request";
				$location.url('/');
				return $q.reject(response);
			}
		});
	};
	console.log('registerController end');
});


movieapp.controller('homeUserController',
			function($scope, $http, $location, $q, dataSharing, $timeout, $rootScope) {
	console.log('homeUserController start');
	$rootScope.hideUserNavTabs = false;
    $rootScope.hideStaticTabs = true;
    $rootScope.hideAdminNavTabs = true;
	
  //session validation
	var response = $http.get("../../api/v1/loggedin");
	response.success(function(dataFromServer, status,
					headers, config) {
		console.log("loggedin" + dataFromServer);
				if (dataFromServer) {
					//validated go ahead
				}else{
					$rootScope.hideUserNavTabs = true;
				    $rootScope.hideStaticTabs = false;
				    $rootScope.hideAdminNavTabs = true;
					$location.url('/');
				}
			});
	response.error(function(data, status, headers, config) {
		if (response.status === 401
				|| response.status === 400) {
			//$scope.loginform_error = "Invalid request";
			$location.url('/');
			return $q.reject(response);
		}
	});
	
	//search movie
	$scope.searchMovie = function(item, event) {
		console.log("--> Submitting searching form "
				+ $scope.serach + " "
				+ $scope.serachCriteria);
		console.log("--> Submitting form ");
		var data = {
				searchString : $scope.serach
		};
		 //get movies data based on search
		var response;
		if($scope.serachCriteria == '1'){
			response = $http.post("../../api/v1/getmoviesforlanguage", data, {});
		}else if($scope.serachCriteria == '2'){
			response = $http.post("../../api/v1/getmoviesforyear", data, {});
		}else if($scope.serachCriteria == '3'){
			response = $http.post("../../api/v1/getmoviesbyname", data, {});
		}else if($scope.serachCriteria == '4'){
			if($scope.serach == "horror"){
				data["searchString"] = "1";
			}else if($scope.serach == "comedy"){
				data["searchString"] = "2";
			}
			response = $http.post("../../api/v1/getmoviesofcategory", data, {});
		}
		
		 
		response.success(function(dataFromServer, status,
						headers, config) {
					var moviesArray = new Array();
					if (dataFromServer.length > 0) {
						console.log("call once");
						moviesArray["dataLength"] = dataFromServer.length;
						moviesArray["moviesData"] = dataFromServer;
						console.log("set dataLength "+moviesArray["dataLength"]);
						console.log("set moviesData "+moviesArray["moviesData"]);
						dataSharing.set(moviesArray);
						$location.url('/movieSearch');
					}else{
						$rootScope.hideUserNavTabs = true;
					    $rootScope.hideStaticTabs = false;
					    $rootScope.hideAdminNavTabs = true;
						//$location.url('/');
					}
				});
		response.error(function(data, status, headers, config) {
			if (response.status === 401
					|| response.status === 400) {
				$scope.error = "Invalid request";
				$location.url('/');
				return $q.reject(response);
			}
		});
		
		//$location.url('/adminMovieSearch');
	};
    
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
				searchString : $scope.serach
		};
		 //get movies data based on search
		var response;
		if($scope.serachCriteria == '1'){
			response = $http.post("../../api/v1/getmoviesforlanguage", data, {});
		}else if($scope.serachCriteria == '2'){
			response = $http.post("../../api/v1/getmoviesforyear", data, {});
		}else if($scope.serachCriteria == '3'){
			response = $http.post("../../api/v1/getmoviesbyname", data, {});
		}else if($scope.serachCriteria == '4'){
			if($scope.serach == "horror"){
				data["searchString"] = "1";
			}else if($scope.serach == "comedy"){
				data["searchString"] = "2";
			}
			response = $http.post("../../api/v1/getmoviesofcategory", data, {});
		}
		
		 
		response.success(function(dataFromServer, status,
						headers, config) {
					var moviesArray = new Array();
					if (dataFromServer.length > 0) {
						console.log("call once");
						moviesArray["dataLength"] = dataFromServer.length;
						moviesArray["moviesData"] = dataFromServer;
						console.log("set dataLength "+moviesArray["dataLength"]);
						console.log("set moviesData "+moviesArray["moviesData"]);
						dataSharing.set(moviesArray);
						$location.url('/adminMovieSearch');
					}else{
						$rootScope.hideUserNavTabs = true;
					    $rootScope.hideStaticTabs = false;
					    $rootScope.hideAdminNavTabs = true;
						//$location.url('/');
					}
				});
		response.error(function(data, status, headers, config) {
			if (response.status === 401
					|| response.status === 400) {
				$scope.error = "Invalid request";
				$location.url('/');
				return $q.reject(response);
			}
		});
		
		//$location.url('/adminMovieSearch');
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
				+ $scope.movieaddform_moviename + " "
				+ $scope.movieaddform_moviedescription + " "
				+ $scope.movieaddform_movieCategory + " "
				+ $scope.movieaddform_actors + " "
				+ $scope.movieaddform_movie_image_link + " "
				+ $scope.movieaddform_Movie_Year + " "
				+ $scope.movieaddform_Movie_Language + " "
				+ $scope.movieaddform_Movie_URL + " ");
		console.log("--> Submitting form ");
		var data = {
			moviename : $scope.movieaddform_moviename,
			description : $scope.movieaddform_moviedescription,
			categoryid : $scope.movieaddform_movieCategory,
			actors : $scope.movieaddform_actors,
			moviefilepath : $scope.movieaddform_movie_image_link,
			url : $scope.movieaddform_Movie_URL,
			year : $scope.movieaddform_Movie_Year,
			language : $scope.movieaddform_Movie_Language,
		};
		
		var response = $http.post("../../api/v1/addmovie", data,
				{});
		response
				.success(function(dataFromServer, status,
						headers, config) {
					$scope.movieaddform_success = "Movie Added successfully";
				});
		response.error(function(data, status, headers, config) {
			if (response.status === 401
					|| response.status === 400) {
				$scope.error = "Invalid request";
				$location.url('/');
				return $q.reject(response);
			}
		});
		
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
    
    var movies = dataSharing.get().moviesData;
    console.log("movieslength "+dataSharing.get().dataLength);
    console.log("moviesData "+dataSharing.get().moviesData);
    for(var i=0; i<dataSharing.get().dataLength; i++){
    	console.log("movies"+movies[i]);
    	 $scope.queue.transactions.push(movies[i]);
    }
    $scope.itemsByPage=4;
    
    $scope.deleteMovie = function(movieid) {
		console.log("--> Submitting form "
				+ movieid);
		var deleteMovieData = {};
		for(var i = 0; i < movies.length; i++){
			if(movies[i].movieid == movieid){
				deleteMovieData["data"] = movies[i];
				console.log("delete movie data set "+deleteMovieData);
				dataSharing.set(deleteMovieData);
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
    
    var moviesDeleteData = dataSharing.get().data;
    console.log("movie delete received "+moviesDeleteData);
    $scope.moviedeleteform_movieid = moviesDeleteData.movieid;
    $scope.moviedeleteform_movieCategory = moviesDeleteData.categoryid.categoryname;
    $scope.moviedeleteform_moviename = moviesDeleteData.moviename;
    $scope.moviedeleteform_moviedescription = moviesDeleteData.description;
    $scope.moviedeleteform_actors = moviesDeleteData.actors;

    $scope.moviedeleteform_deleteMovie = function() {
		console.log("--> Submitting deleteMovie form "
				+ $scope.moviedeleteform_movieid);
		console.log("--> Submitting form ");
		var data = {
			searchString : $scope.moviedeleteform_movieid
		};
		
		var response = $http.post("../../api/v1/deletemovie", data,
				{});
		response
				.success(function(dataFromServer, status,
						headers, config) {
					$scope.moviedeleteform_success = "Movie Deleted successfully";
				});
		response.error(function(data, status, headers, config) {
			if (response.status === 401
					|| response.status === 400) {
				$scope.error = "Invalid request";
				$location.url('/');
				return $q.reject(response);
			}
		});
		
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
	var response = $http.get("../../api/v1/fetchuser");
	response.success(function(dataFromServer, status,
					headers, config) {
				if (dataFromServer) {
					$scope.updateuserform_name = dataFromServer.name;
					$scope.updateuserform_email = dataFromServer.email;
				    $scope.updateuserform_phone = dataFromServer.mobile_number;
				    $scope.updateuserform_address = dataFromServer.address;
				    $scope.updateuserform_city = dataFromServer.city;
				    $scope.updateuserform_state = dataFromServer.state;
				    $scope.updateuserform_country = dataFromServer.country;
				}else{
					$rootScope.hideUserNavTabs = true;
				    $rootScope.hideStaticTabs = false;
				    $rootScope.hideAdminNavTabs = true;
					$location.url('/');
				}
			});
	response.error(function(data, status, headers, config) {
		if (response.status === 401
				|| response.status === 400) {
			$scope.error = "Invalid request";
			$location.url('/');
			return $q.reject(response);
		}
	});
    
    $scope.updateuserform_updateUser = function() {
    	console.log("--> Submitting form "
				+ $scope.updateuserform_name + " "
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
				name : $scope.updateuserform_name,
				email : $scope.updateuserform_email,
				mobile_number : $scope.updateuserform_phone,
				password: $scope.updateuserform_password,
				address : $scope.updateuserform_address,
				city : $scope.updateuserform_city,
				state : $scope.updateuserform_state,
				country : $scope.updateuserform_country
			};
			var response = $http.post("../../api/v1/updateuser", data,
					{});
			response
					.success(function(dataFromServer, status,
							headers, config) {
						$scope.updateuserform_success = "User updated successfully";
					});
			response.error(function(data, status, headers, config) {
				if (response.status === 401
						|| response.status === 400) {
					$scope.error = "Invalid request";
					$location.url('/');
					return $q.reject(response);
				}
			});
	};
	
	console.log('updateUserController end');
});


movieapp.controller('serachMovieController',
		function($scope, $http, $location, $q, dataSharing, $timeout, $rootScope) {
	console.log('serachMovieController start');
	$rootScope.hideUserNavTabs = false;
    $rootScope.hideStaticTabs = true;
    $rootScope.hideAdminNavTabs = true;
    
    $scope.queue = {
            transactions: []
        };
        
    var movies = dataSharing.get().moviesData;
    console.log("movieslength "+dataSharing.get().dataLength);
    console.log("moviesData "+dataSharing.get().moviesData);
    for(var i=0; i<dataSharing.get().dataLength; i++){
    	console.log("movies"+movies[i]);
    	 $scope.queue.transactions.push(movies[i]);
    }
    $scope.itemsByPage=4;
    
    $scope.openMoviePage = function(movieid) {
    	
    	var data = {};
    	data["movieid"] = movieid;
    	dataSharing.set(data);
    	$location.url('/playMovie');
    	
    };
    
	console.log('serachMovieController end');
});


movieapp.controller('playMovieController',
		function($scope, $http, $location, $q, dataSharing, $timeout, $rootScope) {
	console.log('playMovieController start');
	$rootScope.hideUserNavTabs = false;
    $rootScope.hideStaticTabs = true;
    $rootScope.hideAdminNavTabs = true;
    
    $scope.movie_name = dataSharing.get().name;
    $scope.movie_description = dataSharing.get().description;
    $scope.movie_category = dataSharing.get().category;
    $scope.movie_actors = dataSharing.get().actors;
    $scope.movie_rating = dataSharing.get().rating;
    $scope.movie_id = dataSharing.get().movieid;
    
    //get movie data
    var movieid = dataSharing.get().movieid
    console.log("movie data for movieid "+movieid);
    
    var data = {
    		searchString : movieid
	};
	var response = $http.post("../../api/v1/getmoviebyid", data,
			{});
	response
			.success(function(dataFromServer, status,
					headers, config) {
				
				$scope.movie_name = dataFromServer.moviename;
				$scope.movie_description = dataFromServer.description;
				$scope.movie_category = dataFromServer.categoryid.categoryname;
				$scope.movie_actors = dataFromServer.actors;
				$scope.movie_rating = dataFromServer.averageRating;
				
				
			});
	response.error(function(data, status, headers, config) {
		if (response.status === 401
				|| response.status === 400) {
			$scope.error = "Invalid request";
			$location.url('/');
			return $q.reject(response);
		}
	});
    
    
    //all stars empty first
    var selectedStars = 0;
    $scope.starclassname={}
    for(var i=1; i < 6; i++){
		$scope.starclassname[i] = "glyphicon glyphicon-star-empty";
	}
	//after clicking on stars
    $scope.clickedStar = function(starid) {
    	selectedStars = 0;
    	for(var i=0; i<6; i++){
    		if(i <= starid){
    			selectedStars++;
    			$scope.starclassname[i] = "glyphicon glyphicon-star";
    		}else{
    			$scope.starclassname[i] = "glyphicon glyphicon-star-empty";
    		}
    	}
    };
    
    //post comment
    $scope.postComment = function() {
    	console.log("comment "+$scope.usercomment+" on "+movieid);
    	var data = {
    			movieid : movieid,
    			comment : $scope.usercomment
    	};
    	var response = $http.post("../../api/v1/addcomment", data,
    			{});
        
    	response
    			.success(function(dataFromServer, status,
    					headers, config) {
    				console.log(dataFromServer);
    				$scope.comment_success = "Comment Added Successfully";
    			});
    	response.error(function(data, status, headers, config) {
    		if (response.status === 401
    				|| response.status === 400) {
    			$scope.error = "Invalid request";
    			$location.url('/');
    			return $q.reject(response);
    		}
    	});
    	
    };
    
    //load comments
    $scope.queue = {
        transactions: []
    };
    var data = {
    		searchString : movieid,
	};
	var response = $http.post("../../api/v1/getcommentsformovie", data,
			{});
    
	response
			.success(function(dataFromServer, status,
					headers, config) {
				for (var i = 0; i < dataFromServer.length; i++) {
					console.log("comment "+i);
					$scope.queue.transactions.push(dataFromServer[i]);
				}
			});
	response.error(function(data, status, headers, config) {
		if (response.status === 401
				|| response.status === 400) {
			$scope.error = "Invalid request";
			$location.url('/');
			return $q.reject(response);
		}
	});
	
	
    /*for (var i = 0; i < 100; i++) {
    	dataFromServer[i] = {
    		comment:"The film proves that good clean and neat comedy without any cheap and vulgar dialogs or gestures is definitely more entertaining and enjoyable."
    	};
        $scope.queue.transactions.push(dataFromServer[i]);
    }*/
    $scope.itemsByPage=6;
    
	console.log('playMovieController end');
});

//create the controller and inject Angular's $scope
movieapp.controller('logoutController', function($scope, $http, $rootScope) {
	// create a message to display in our view
	console.log('logout');
	$rootScope.hideUserNavTabs = true;
    $rootScope.hideStaticTabs = false;
    $rootScope.hideAdminNavTabs = true;
   
	var data = {};
	var response = $http.post("../../api/v1/logout", data,
			{});
    
	response
			.success(function(dataFromServer, status,
					headers, config) {
				console.log(dataFromServer);
				if (dataFromServer == true) {
					console.log("logout sucessfully");
					 
				}
			});
	response.error(function(data, status, headers, config) {
		if (response.status === 401
				|| response.status === 400) {
			$scope.loginform_error = "Invalid request";
			$location.url('/');
			return $q.reject(response);
		}
	});
});
