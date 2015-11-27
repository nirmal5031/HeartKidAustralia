(function () {
    'use strict';

    angular
        .module('loginApp', ['ui.router','angularUtils.directives.dirPagination'])
        .service('dataService', function() {

            // private variable
            var dataObj;

            this.dataObj = dataObj;
        })
        .config(function($stateProvider, $urlRouterProvider) {
            // $httpProvider.interceptors.push('errorInterceptor');
            $stateProvider
                // route to show our basic form (/form)
                // nested states
                // each of these sections will have their own view
                // url will be nested (/form/profile)
                .state('form', {
                    url: '/form',
                    controller: 'HomeController',
                    templateUrl: 'admin-views/home/home.view.html'
                })
                .state('form.search', {
                    url: '/home',
                    controller: 'HomeController',
                    templateUrl: 'admin-views/home/search.view.html'

                })
                .state('form.modify', {
                    url: '/home',
                    controller: 'HomeController',
                    templateUrl: 'admin-views/home/modify.view.html'

                })
                .state('form.export', {
                    url: '/home',
                    controller: 'HomeController',
                    templateUrl: 'admin-views/home/export.view.html'

                })
                .state('form.manage', {
                    url: '/home',
                    controller: 'HomeController',
                    templateUrl: 'admin-views/home/manage.view.html'

                })
                .state('/login', {
                    url: '/login',
                    controller: 'LoginController',
                    templateUrl: 'admin-views/login/login.view.html'
                })
                .state('/home', {
                    url: '/home',
                    controller: 'HomeController',
                    templateUrl: 'admin-views/home/home.view.html'
                })
                .state('/register', {
                    url: '/home',
                    controller: 'RegisterController',
                    templateUrl: 'admin-views/register/register.view.html'

                })
                .state('/modify', {
                    url: '/home',
                    templateUrl: 'admin-views/modify/modify.view.html'
                })

            // catch all route
            // send users to the form page
            $urlRouterProvider.otherwise('/login');
        });



/*
    config.$inject = ['$routeProvider', '$locationProvider'];
    function config($routeProvider, $locationProvider) {
        $routeProvider

            .when('/login', {
                controller: 'LoginController',
                templateUrl: 'admin-views/login/login.view.html'
                })
            .when('/home', {

                controller: 'HomeController',
                templateUrl: 'admin-views/home/home.view.html'
                })
            .when('/register', {
                controller: 'RegisterController',
                templateUrl: 'admin-views/register/register.view.html',
                controllerAs: 'vm'
                })
            .when('/modify', {
                url: '/research',
                templateUrl: 'admin-views/modify/modify.view.html',
                controllerAs: 'vm'
            })

            .otherwise({ redirectTo: '/login' })

    }
    config.$inject=['$stateProvider', '$urlRouterProvider'];
    function config($stateProvider, $urlRouterProvider)
    {
        $urlRouterProvider.otherwise("/state1");
        $stateProvider
            // route to show our basic form (/form)
            .state('/modify', {
                url: '/research',
                templateUrl: 'admin-views/modify/modify.view.html',
                controllerAs: 'vm'
            })
    }*/
    //function config($stateProvider, $urlRouterProvider) {
    //    $stateProvider
    //        // route to show our basic form (/form)
    //        .state('form', {
    //            url: '/form',
    //            templateUrl: 'form.html',
    //            controller: 'formController'
    //        })
    //
    //}



})();
 /*   run.$inject = ['$rootScope', '$location', '$cookieStore', '$http'];
    function run($rootScope, $location, $cookieStore, $http) {
        // keep user logged in after page refresh
        $rootScope.globals = $cookieStore.get('globals') || {};
        if ($rootScope.globals.currentUser) {

            alert("Cusrrent user exists");
            $http.defaults.headers.common['Authorization'] = 'Basic ' + $rootScope.globals.currentUser.authdata; // jshint ignore:line
        }

        $rootScope.$on('$locationChangeStart', function (event, next, current) {
            // redirect to login page if not logged in and trying to access a restricted page
            var restrictedPage = $.inArray($location.path(), ['/login', '/register']) === -1;
            var loggedIn = $rootScope.globals.currentUser;
            if (restrictedPage && !loggedIn) {
                $location.path('/login');
            }
        });
    }*/


