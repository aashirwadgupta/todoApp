myApp.controller("ToDoCtrl", function($scope, $http, $localStorage, $location, ngToast){

	var path = $location.$$absUrl.replace($location.$$url, "");
	
	this.$onInit = function(){
		console.log($location);
		var userId = $localStorage.email;
		if(userId!=undefined){
			$http.get(path+"/api/getToDo?id="+userId).
			then(function(response) {
				//console.log("from upper");
				//console.log(response.data);
				$scope.userMail = response.data.id;
				$scope.toDoList = response.data.taskList;
			}, function(response){
				//console.log("from lower");
				//console.log($localStorage.email);
			});
		} else {
			$location.path("");
			ngToast.danger("User not logged in");
		}
	};
	
	
	$scope.createToDo = function(toDoText){
		console.log(toDoText);
		if(toDoText!=undefined){
			var toDoObject = [];
			var dateObj = new Date();
			var toDoTime = dateObj.getTime();
			var toDoArr = new Array();
			var newToDo = {
					"toDoId":toDoObject.length+1,
					"edit":false,
					"isCompleted":false,
					"toDoText":toDoText,
					"toDoTime":toDoTime
			}
			var dateKey = dateObj.getDate()+"-"+(parseInt(dateObj.getMonth())+1)+"-"+dateObj.getFullYear();
			
			if($scope.toDoList!=undefined){
				var arrLen = parseInt(toDoArr.length);
				var toDoArr = $scope.toDoList;
				//toDoArr[arrLen] = newToDo;
				toDoArr.push(newToDo);
				/*toDoObject = $scope.toDoList;
				console.log(toDoObject[dateKey]);
				if(toDoObject[dateKey]!=undefined){
					toDoArr = toDoObject[dateKey];
					var arrLen = parseInt(toDoArr.length);
					
					//toDoArr.push(newToDo);
					toDoArr[arrLen] = newToDo;
					//toDoObject[dateKey] = toDoArr;
				} else {
					//toDoArr.push(newToDo);
					//toDoObject[dateKey] = toDoArr;
				}*/
				//console.log(toDoArr);
				//console.log(toDoObject);
				$scope.toDoList = toDoArr;
			} else {
				var toDoArrIn = [newToDo];
				$scope.toDoList = toDoArrIn;
				//console.log(toDoArrIn);
				//console.log($scope.toDoList);
			}
		}
		$scope.toDoText=null;
	}
	
	$scope.editToDo = function(toDoItem){
		//console.log(toDoItem);
		var i = $scope.toDoList.indexOf(toDoItem);
		//console.log(i);
		toDoItem.edit = true;
		//console.log(toDoItem);
		$scope.toDoList[i] = toDoItem;
		//delete $scope.toDoList[i];
	}
	
	$scope.updateToDo = function(toDoItem){
		//console.log(toDoItem);
		var i = $scope.toDoList.indexOf(toDoItem);
		//console.log(i);
		toDoItem.edit = false;
		var toDoTime = dateObj.getTime();
		toDoItem.toDoTime=toDoTime;
		//console.log(toDoItem);
		$scope.toDoList[i] = toDoItem;
		//delete $scope.toDoList[i];
	}
	
	$scope.completeToDo = function(toDoItem){
		//console.log(toDoItem);
		var i = $scope.toDoList.indexOf(toDoItem);
		//console.log(i);
		toDoItem.isCompleted = true;
		//console.log(toDoItem);
		$scope.toDoList[i] = toDoItem;
		//delete $scope.toDoList[i];
	}
	
	$scope.deleteToDo = function(toDoItem){
		console.log(toDoItem);
		console.log(toDoItem);
		var i = $scope.toDoList.indexOf(toDoItem);
		//console.log(i);
		//delete $scope.toDoList[i];
		$scope.toDoList.splice(i, 1 );
		//console.log($scope.toDoList);
	}
	
	$scope.saveToDos = function(){
		var finalObj = {
			'id':$localStorage.email,
			'taskList':$scope.toDoList
		}
		$http.post(path+"/api/updateToDo", finalObj).
		then(function(){
			ngToast.success("ToDo List for "+$localStorage.email+" is saved!");
		}, function(){
			ngToast.danger("Saving of ToDo List for "+$localStorage.email+" failed!");
		});
	}
	
	$scope.logout = function(){
		$localStorage.email = undefined;
		$location.path("");
	}
	
});