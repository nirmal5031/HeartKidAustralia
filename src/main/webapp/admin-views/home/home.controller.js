(function () {
    'use strict';

    angular
        .module('app')
        .controller('HomeController', HomeController)

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

    .controller('heartkidadminhome',['$scope', '$http', function($scope,$http) {
            $scope.exportData = function () {
$scope.searchHeartKid = false;
var date = new Date().getDate()+"_"+new Date().getMonth()+"_"+new Date().getFullYear();

                    $http({
                        url: 'http://localhost:8080/heartkid/downloadExcel',
                        method: "POST",
                        data:$scope.formAdminData,
                        headers: {
                            'Content-type': 'application/json'
                        },
                        responseType: 'arraybuffer'
                    })

                        .success(function (data, status, headers, config) {
                            alert("SUCESS"+data);
                            var blob = new Blob([data], {type: "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"});
                            saveAs(blob, 'HeartKid_Results'+date+'.xls');
                        }).error(function (data, status, headers, config) {
                            //upload failed
                        })

                /*
                        .then(function (response) {
                            // success

                           // var data = angular.fromJson(response.data);

                            var data1 = angular.toJson(response.data);

                            alert(data1);
                            var type = response.headers('Content-Type');
                            var blob = new Blob([data1], {
                                type: 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet'
                            });
                            saveAs(blob, 'helloAgain' + '.xls');
                        },
                        function (response) { // optional
                            // failed
                            alert("Failure" + response.status);
                        })*/

            }

            $scope.statusArray = ["incomplete", "success"];
           /* $scope.personalfieldvalues =
            [{
                id: '1',
                question: 'Please select who you are?',
                scope:'formAdminData.usertype'
            }, {
                id: '2',
                question: 'First Name',
                scope:'formAdminData.firstname'
            }, {
                id: '3',
                question: 'Last Name',
                scope:'formAdminData.lastname'
            }, {
                id: '4',
                question: 'Title',
                scope:'formAdminData.title'
            }, {
                id: '5',
                question: 'Date of birth',
                scope:'formAdminData.dob'
            }, {
                id: '6',
                question: 'PostCode',
                scope:'formAdminData.postcode'
            }];

           */

            $scope.modifyuser = function(a){
                alert("value of a is"+a);
                var b = $.parseJSON(angular.fromJson(a));
                alert("bbbbb"+b);

                $scope.formAdminData = a;


            }

            // the dialog is injected in the specified controller


            $scope.searchheartkid = function() {
$scope.searchHeartKid = true;
                $http({
                    url: 'http://localhost:8080/heartkid/getrecord',
                    method: "POST",
                    data:$scope.formAdminData
                })
                    .then(function(response) {
                        // success
                        var data = angular.fromJson(response.data);

                        var data1 = angular.toJson(response.data);
                        alert("result data1  ---"+data1);
                        $scope.users = data;


                    },
                    function(response) { // optional
                        // failed
                        alert("Failure"+response.status);
                    });
            }

    }])


    HomeController.$inject = ['UserService', '$rootScope'];
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


})();