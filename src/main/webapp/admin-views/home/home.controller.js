


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

        .directive("passwordVerify", function() {
            return {
                require: "ngModel",
                scope: {
                    passwordVerify: '='
                },
                link: function(scope, element, attrs, ctrl) {
                    scope.$watch(function() {
                        var combined;

                        if (scope.passwordVerify || ctrl.$viewValue) {
                            combined = scope.passwordVerify + '_' + ctrl.$viewValue;
                        }
                        return combined;
                    }, function(value) {
                        if (value) {
                            ctrl.$parsers.unshift(function(viewValue) {
                                var origin = scope.passwordVerify;
                                if (origin !== viewValue) {
                                    ctrl.$setValidity("passwordVerify", false);
                                    return undefined;
                                } else {
                                    ctrl.$setValidity("passwordVerify", true);
                                    return viewValue;
                                }
                            });
                        }
                    });
                }
            };
        })

    .controller('HomeController',['$scope', '$http','$state','$window','dataService','$rootScope', function($scope,$http,$state,$window,dataService,$rootScope) {

            var accessToken = sessionStorage.getItem('tokenId');
            var adminuser = sessionStorage.getItem('adminuser');
            $scope.adminuser = adminuser;


            console.log(accessToken);
                  /* $scope.$watch('$viewContentLoaded', function(){

                $http({
                    url: 'heartkid/tokenvalidate',
                    method: "GET",
                    headers: {
                        'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8',
                        'Authorization': 'Bearer ' + accessToken
                    }

                })
                    .then(function (response) {

            sessionStorage.setItem('isTokenValid',true);

                    },
                    function (response) { // optional
                        sessionStorage.setItem('isTokenValid',false);

                    })
            });*/

            var istokenvalid = sessionStorage.getItem('isTokenValid');

           if(accessToken == null) {
                $state.go('/login');
            }
            else {
                var userroletype = sessionStorage.getItem('userrole');

                if(userroletype != "null" && userroletype == "Admin")
                {
                    $scope.admin=true;
            }else{
                    $scope.admin=true;
                }
                $scope.userroleArray = ["Admin", "Viewer"];
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
                        url: 'heartkid/downloadExcel',
                        method: "POST",
                        data: $scope.formAdminData,
                        headers: {
                            'Content-type': 'application/json'
                        },
                        responseType: 'arraybuffer'
                    })

                        .success(function (data, status, headers, config) {

                            if ($scope.totalrecords > 1000) {
                                var exporttoexcel = $window.confirm('There are more than 1000 records. Do you want to export to excel ?');
                                if (exporttoexcel) {
                                    var blob = new Blob([data], {type: "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"});
                                    saveAs(blob, 'HeartKid_Results' + date + '.xls');
                                } else {
                                    $scope.ErrorMessage = "Please refine your search and try again";
                                }

                            }
                            else {
                                var blob = new Blob([data], {type: "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"});
                                saveAs(blob, 'HeartKid_Results' + date + '.xls');

                            }
                        }).error(function (data, status, headers, config) {
                            //upload failed
                        })


                }

                $scope.statusArray = ["incomplete", "success"];
               $scope.usertypeArray = ["Patient", "Carer"];

                $scope.modifyuser = function (a) {

                    var b = $.parseJSON(angular.fromJson(a));


                    $scope.formAdminData = a;


                }

                // the dialog is injected in the specified controller


                $scope.searchheartkid = function () {


                    $http({
                        url: 'heartkid/getrecord',
                        method: "POST",
                        headers: {
                            'Authorization': 'Bearer ' + accessToken,
                            'Content-Type': 'application/json'
                        },
                        data: $scope.formAdminData
                    })
                        .then(function (response) {
                            // success
                            var data = angular.fromJson(response.data);
                            var data1 = angular.toJson(response.data);

                            $scope.searchHeartKid = true;
                            $scope.users = data;
                            $scope.totalrecords = data.length;


                        },
                        function (response) { // optional
                            // failed
                            alert("Failure" + response.status);
                        });
                }


                $scope.showview = function (id) {


                    if (id == 'search') {
                        $scope.searchuser = true;
                        $scope.modifyuser = true;
                    }
                    if (id == 'modify') {
                        $scope.modifyuser = false;
                        $scope.searchuser = false;
                    }
                }

                $scope.clearall = function () {
                    $scope.formAdminData = "";
                }

                $scope.deleteuser = function (deluser) {


                    $http({
                        url: 'heartkid/deleterecord/' + deluser,
                        method: "GET"
                    })
                        .then(function (response) {
                            // success
                            $scope.deleteMessage = response.data;
                            $scope.users = "";
                        },
                        function (response) { // optional
                            // failed
                            $scope.deleteMessage = "Error in deleting the record ! Try later";

                        });

                }
                $scope.createadminuser = function () {
                    $http({
                        url: 'heartkid/createadminuser',
                        method: "POST",
                        data: $scope.formAdminData
                    })
                        .then(function (response) {
                            // success

                            if (response.data == "success") {
                                $scope.formAdminData = "";
                                var creationMessage = response.data + $scope.formAdminData.username;
                                $scope.creationMessage = "User has been successfully created";
                            }
                            else if (response.data == "useridexist") {
                                $scope.creationMessage = 'User ID is not available . Please choose different user ID';
                            }
                            else {
                                $scope.formAdminData = "";

                                $scope.creationMessage = "Error in creating a user. Please try again later";
                            }
                        },
                        function (response) { // optional
                            // failed
                            alert("Failure" + response.status);
                        });

                }

                $scope.logoutadmin = function () {
                    // var accessToken = sessionStorage.getItem('tokenId');

                    $http({
                        url: 'token/revoke',
                        method: "DELETE",
                        headers: {
                            'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8',
                            'Authorization': 'Basic ' + accessToken
                        }

                    })
                        .then(function (response) {
                            //


                        },
                        function (response) { // optional
                            // failed


                        });

                    if (accessToken != null) {
                        sessionStorage.clear();
                        localStorage.clear();
                        $scope.isValidUser = false;
                        $state.go('/login');

                    }
                    else {
                        sessionStorage.clear();
                        localStorage.clear();
                        $state.go('/login');
                    }
                }

                $scope.checktoken = function () {

                    var accessToken = sessionStorage.getItem('tokenId');
                    $http({
                        url: 'heartkid/tokenvalidate',
                        method: "GET",
                        headers: {
                            'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8',
                            'Authorization': 'Basic ' + accessToken
                        }

                    })
                        .then(function (response) {
                            //

                        },
                        function (response) { // optional
                            // failed
                            alert("Error respomse----->" + response.data);
                        })
                }
               $scope.test = function()
               {
                   alert("TEST FUNCTION");
               }
$scope.listadminuser = function()
{
    $http({
        url: 'heartkid/listadminuser',
        method: "GET",
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8',
            'Authorization': 'Basic ' + accessToken
        }

    })
        .then(function (response) {
            var data = angular.fromJson(response.data);
            $scope.users = data;
            $scope.totalrecords = data.length;
        },
        function (response) {
            alert("Error respomse----->" + response.data);
        })
}
               $scope.deleteuseradmin = function()
               {
                   var username = $scope.formAdminData.delusername;

                   $http({
                       url: 'heartkid/fetchadminuser/'+username,
                       method: "GET"
                       /* headers: {
                        'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8',
                        'Authorization': 'Basic ' + accessToken
                        }*/

                   })

                       .then(function (response) {
                           var data = angular.fromJson(response.data);

                           $scope.delusers = data;
                           $scope.totalrecords = data.length;
                       },
                       function (response) {
                           alert("Error respomse----->" + response.data);
                       })

               }
               $scope.deleteadminuser = function(username)
               {

                   $http({
                       url: 'heartkid/deleteadminuser/'+username,
                       method: "GET"
                       /* headers: {
                        'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8',
                        'Authorization': 'Basic ' + accessToken
                        }*/

                   })

                       .then(function (response) {
                           var data = angular.fromJson(response.data);

                           if(data==1)
                           {
                               $scope.deletionMessage= "User successfully deleted";
                               $scope.delusers = "";
                           }
                           else{
                               $scope.deletionMessage= "Error in deleting user. Please try again later! ";

                           }
                       },
                       function (response) {
                           $scope.deletionMessage= "Error in deleting user. Please try again later! ";
                       })

               }
            }

        }])

})();

