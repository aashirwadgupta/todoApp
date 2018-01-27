'use strict';
var myApp = angular.module('myApp', ['ui.router', 'ngStorage', 'ngTable', 'ngSanitize', 'ngToast']);

myApp.config(function($stateProvider, $urlRouterProvider, $locationProvider, $urlMatcherFactoryProvider) {
    $urlRouterProvider.otherwise('/');
    $urlMatcherFactoryProvider.strictMode(false);
    
    $stateProvider
    .state('app', {
        url: '/',
        templateUrl: 'appCtrl.html',
        controller: 'AppCtrl'
    })
    .state('todos', {
        url: '/todos',
        templateUrl: 'todo.html',
        controller: 'ToDoCtrl'
    })
    .state('profile', {
        url: '/profile',
        templateUrl: 'profile.html',
        controller: 'ProfileCtrl'
    });
    $locationProvider.html5Mode({
    	  enabled: true,
    	  requireBase: false
    	});
}).run(function () {

}); 