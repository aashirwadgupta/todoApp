myApp.controller('AppCtrl', function($scope, $http, $rootScope, $location, $localStorage, ngToast){
	$scope.message = "To Do Application";
	$scope.secondMessage = "Application for To Do";

	var path = $location.$$absUrl;
	
	this.$onInit = function() {
		//console.log($location.$$absUrl);
		//var path = $location.$$absUrl;
		$http.get(path+"/api/getAllUsers").
		then(function(response) {
			console.log("success");
			//console.log(response.data);
		}, function(response){
			console.log("failure");
			//console.log(response.data);
		});
	};
	//init();
	$scope.getToDos = function(){
		//console.log($scope.userMail);
		$localStorage.email = $scope.userMail;
		var userModel = {
				"mailId":$scope.userMail,
				"secretCode":$scope.userSecretCode
		}
		
		$http.post(path+"/api/userLogin", userModel).
		then(function(response){
			$localStorage.email = $scope.userMail;
			ngToast.success("Login Successful");
			//console.log(response);
			$location.path("todos");
		}, function(response){
			if(response.status==417){
				ngToast.danger("Login Failed!");
			} else if(response.status==400){
				ngToast.danger("Fields Can't be empty");
			}			
			else {
				ngToast.danger("User Doesn't Exist. Kindly Register");
			}
			//console.log(response);
		});
	}
	
	$scope.registerUser = function(){
		if($scope.userRegSecretCode!=$scope.userRegConfirm){
			ngToast.danger("Passwords do not match!");
		} else {
			var userObj = {
				'id':$scope.userRegMail,
				'fullName':$scope.userFullName,
				'mailId':$scope.userRegMail,
				'secretCode':$scope.userRegSecretCode,
				'dateOfBirth':$scope.dateOfBirthReg
			}

			$http.post(path+"/api/createUser").
			then(function(response) {
				//console.log("from upper");
				//console.log(response.status);
				if(response.status==200){
					ngToast.success("User Registration Successful.");
					$localStorage.email = $scope.userRegMail;
					$location.path("todos");
				}
			}, function(response){
				if(response.status==417){
					ngToast.warning("User Already Exists. Kindly Login.");					
				} else {
					ngToast.warning("Request Failed. Kindly try later.");						
				}
			});
		}
		
	}
})