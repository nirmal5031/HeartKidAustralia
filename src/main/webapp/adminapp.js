(function () {
    'use strict';

    angular
        .module('loginApp', ['ngRoute', 'ngCookies','angularUtils.directives.dirPagination','angularModalService'])
        .config(config);

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

    };
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


