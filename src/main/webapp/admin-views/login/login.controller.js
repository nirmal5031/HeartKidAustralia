'use strict';
angular.module('loginApp')
        .controller('LoginController', [ '$scope', 'AuthenticationService', 'LoginService','$state','dataService','$rootScope', function ($scope, AuthenticationService, LoginService,$state,dataService,$rootScope) {


        var accessToken = sessionStorage.getItem('tokenId');
        if(accessToken == null){
            sessionStorage.clear();
            localStorage.clear();
            $scope.isValidUser = false;
            $state.go('/login');
            }
        else{
            $state.go('/home');
        }


            $scope.login = function () {
            var request = "/" + $scope.vm.heartkidusername + "/" + $scope.vm.heartkidpassword;

                LoginService.loginCustomer(request).then(function (successData) {

                    if (successData.status === "success") {
                        sessionStorage.setItem('userrole',successData.userrole );
                        sessionStorage.setItem('adminuser',successData.firstname+" "+ successData.lastname);
                        $scope.getAuthentionToken();
                    }
                    else {
                        $scope.isValidUser = true;
                        if (successData.status == "INVALIDCREDENTIALS") {
                            $scope.vm.error = "Invalid Credentials. Please try again";
                        }
                        else if (successData.status == "NOUSER") {
                            $scope.vm.error = "User is not registered. Please try again with valid user"
                        }

                        else {
                            $scope.vm.error = "Error . Please try again later";
                        }
                    }
                });

            };

            $scope.getAuthentionToken = function () {
                var requestOauth = 'password=' + btoa($scope.vm.heartkidpassword) + '&username=' + $scope.vm.heartkidusername + '&grant_type=password';
                AuthenticationService.getAuthention(requestOauth).then(function (successData) {
                    if (successData.access_token) {
                        sessionStorage.setItem('tokenId', successData.access_token);
                      //  $state.go('menu');

                       $state.go('form.welcome');
                    }
                });
            }


        }]);
   /* LoginController.$inject = ['$location', 'AuthenticationService', 'FlashService'];
    function LoginController($location, AuthenticationService, FlashService) {
        var vm = this;

        vm.login = login;

        (function initController() {
            // reset login status
            AuthenticationService.ClearCredentials();
        })();

        function login() {
            vm.dataLoading = true;
            AuthenticationService.Login(vm.username, vm.password, function (response) {
                if (response.success) {
                    AuthenticationService.SetCredentials(vm.username, vm.password);
                    $location.path('/home');
                } else {
                    FlashService.Error(response.message);
                    vm.dataLoading = false;
                }
            });
        };
    }

})();*/
