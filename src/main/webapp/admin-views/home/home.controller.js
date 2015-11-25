


(function () {
    'use strict';

    angular.module('loginApp')

        .filter('isEmpty', function () {
            var bar;
            return function (obj) {
                for (bar in obj) {
                    if (obj.hasOwnProperty(bar)) {
                        return false;
                    }
                }
                return true;
            };
        })

    .controller('HomeController',['$scope', '$http','$state', function($scope,$http,$state) {

            var accessToken = sessionStorage.getItem('tokenId');
            //alert("inside home sontroler"+accessToken);
            if(accessToken == null){
                //alert("Null please go to login page");
              //  $state.go('/login');
                $state.go('/login');
               // $location.path('http://localhost:8080/heartkidaustralia/adminindex.html#/login');
            }
            else {

                $scope.showModal = false;
                $scope.buttonClicked = "";
                $scope.toggleModal = function (btnClicked) {
                    $scope.buttonClicked = btnClicked;
                    $scope.showModal = !$scope.showModal;
                };
                $scope.sort = function (keyname) {
                    $scope.sortKey = keyname;   //set the sortKey to the param passed
                    $scope.reverse = !$scope.reverse; //if true make it false and vice versa
                }


                $scope.exportData = function () {
                    $scope.searchHeartKid = false;
                    var date = new Date().getDate() + "_" + new Date().getMonth() + "_" + new Date().getFullYear();

                    $http({
                        url: 'heartkid/downloadExcel'   ,
                        method: "POST",
                        data: $scope.formAdminData,
                        headers: {
                            'Content-type': 'application/json'
                        },
                        responseType: 'arraybuffer'
                    })

                        .success(function (data, status, headers, config) {
                            alert("SUCESS" + data);
                            var blob = new Blob([data], {type: "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"});
                            saveAs(blob, 'HeartKid_Results' + date + '.xls');
                        }).error(function (data, status, headers, config) {
                            //upload failed
                        })


                }

                $scope.statusArray = ["incomplete", "success"];

                $scope.modifyuser = function (a) {
                    alert("value of a is" + a);
                    var b = $.parseJSON(angular.fromJson(a));
                    alert("bbbbb" + b);

                    $scope.formAdminData = a;


                }

                // the dialog is injected in the specified controller


                $scope.searchheartkid = function () {


                    $http({
                        url: 'heartkid/getrecord',
                        method: "POST",
                        data: $scope.formAdminData
                    })
                        .then(function (response) {
                            // success
                            var data = angular.fromJson(response.data);
                            var data1 = angular.toJson(response.data);
                            alert("result data1  ---" + data1);
                           // alert("result dataaaaaaaaaaaaaaaaaa ---" + data);
                            $scope.searchHeartKid = true;
                            $scope.users = data;

                            alert("$scope.users----"+$scope.users);


                        },
                        function (response) { // optional
                            // failed
                            alert("Failure" + response.status);
                        });
                }

                $scope.logoutadmin = function() {

                    alert("logging out admin fucntion");

                    $http({
                        url: 'heartkid/getrecord',
                        method: "POST",
                        data: $scope.formAdminData
                    })
                        .then(function (response) {
                            // success
                            var data = angular.fromJson(response.data);

                            var data1 = angular.toJson(response.data);
                            alert("result data1  ---" + data1);
                            $scope.users = data;


                        },
                        function (response) { // optional
                            // failed
                            alert("Failure" + response.status);
                        });


                }

            }
            $scope.showview = function(id)
            {
                alert("Show view---"+id);

                if(id=='search')
                {
                    $scope.searchuser = true;
                    $scope.modifyuser = true;
                }
                if(id=='modify')
                {
                    $scope.modifyuser = false;
                    $scope.searchuser = false;
                }
            }
            $scope.clearall = function()
            {
                $scope.formAdminData = "";
            }

    }])

})();

   /* HomeController.$inject = ['UserService', '$rootScope'];
    function HomeController(UserService, $rootScope) {
        var vm = this;

        vm.user = null;
        vm.allUsers = [];
        vm.deleteUser = deleteUser;

        initController();

        function initController() {
            loadCurrentUser();
            loadAllUsers();
        }

        function loadCurrentUser() {
            UserService.GetByUsername($rootScope.globals.currentUser.username)
                .then(function (user) {
                    vm.user = user;
                });
        }

        function loadAllUsers() {
            UserService.GetAll()
                .then(function (users) {
                    vm.allUsers = users;
                });
        }

        function deleteUser(id) {
            UserService.Delete(id)
            .then(function () {
                loadAllUsers();
            });
        }
    }
*/

