
// create our angular app and inject ngAnimate and ui-router
// =============================================================================
angular.module('formAppadmin', ['ui.router'])

// configuring our routes
// =============================================================================
    .config(function($stateProvider, $urlRouterProvider) {

        $stateProvider

            // route to show our basic form (/form)
            .state('form', {
                url: '/form',
                templateUrl: 'form.html',
                controller: 'formController'
            })

            // nested states
            // each of these sections will have their own view
            // url will be nested (/form/profile)

            // url will be /form/payment
            .state('form.login', {
                url: '/login',
                templateUrl: 'views/form-admin-login.html'
            })


        // catch all route


        // send users to the form page
        $urlRouterProvider.otherwise('/form/login');
    })
    .controller('formController', function($scope) {

        // we will store all of our form data in this object
        $scope.formData = {};

        // function to process the form
        $scope.processForm = function() {
            alert('You have subitted the form!');
        };

    })
.controller('adminlogincntrl', function($scope) {
        $scope.loginsubmit = function() {
            var username = $scope.formData.username;
            var password = $scope.formData.password;

            alert("submitting login admin form"+username+password);
        }
    });

//directivessss


// our controller for the form
// =============================================================================









/*global angular */
/*
 jQuery UI Datepicker plugin wrapper

 @note If ? IE8 make sure you have a polyfill for Date.toISOString()
 @param [ui-date] {object} Options to pass to $.fn.datepicker() merged onto uiDateConfig
 */

