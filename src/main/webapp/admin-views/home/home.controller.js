﻿(function () {
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

        .directive("passwordVerify", function () {
            return {
                require: "ngModel",
                scope: {
                    passwordVerify: '='
                },
                link: function (scope, element, attrs, ctrl) {
                    scope.$watch(function () {
                        var combined;

                        if (scope.passwordVerify || ctrl.$viewValue) {
                            combined = scope.passwordVerify + '_' + ctrl.$viewValue;
                        }
                        return combined;
                    }, function (value) {
                        if (value) {
                            ctrl.$parsers.unshift(function (viewValue) {
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

        .controller('HomeController', ['$scope', '$http', '$state', '$window', 'dataService', '$rootScope', '$anchorScroll', '$location', '$timeout', function ($scope, $http, $state, $window, dataService, $rootScope, $anchorScroll, $location, $timeout) {



            var accessToken = sessionStorage.getItem('tokenId');
            var adminuser = sessionStorage.getItem('adminuser');

            if (accessToken!= null) {
                $timeout(function () {
                    $scope.logoutadmin();
                }, 1700000);
            }
            $scope.adminuser = adminuser;
            $scope.sexArray = ["Male", "Female"];
            $scope.yesnoArray = ["Yes", "No"];
            $scope.yesnoArraycaps = ["yes", "no"];
            $scope.usertypeArray = ["Patient", "Carer"];
            $scope.titleArray = ["Mr", "Mrs", "Miss", "Mrs", "Dr", "Prof"];
            $scope.sexArray = ["Male", "Female"];
            $scope.conttypeArray = ["Phone", "Email"];
            $scope.ethnicityArray = ["Caucasian", "Aborigional / Tores Strait Island", "Mouri", "Asian", "Indian", "Black/Afican American", "European", "None of the above"];
            $scope.lstofcountryArray = ["Australia", "Afghanistan", "Albania", "Algeria", "Andorra", "Angola", "Antigua & Deps", "Argentina", "Armenia", "Austria", "Azerbaijan", "Bahamas", "Bahrain", "Bangladesh", "Barbados", "Belarus", "Belgium", "Belize", "Benin", "Bhutan", "Bolivia", "Bosnia Herzegovina", "Botswana", "Brazil", "Brunei", "Bulgaria", "Burkina", "Burundi", "Cambodia", "Cameroon", "Canada", "Cape Verde", "Central African Rep", "Chad", "Chile", "China", "Colombia", "Comoros", "Congo", "Congo {Democratic Rep}", "Costa Rica", "Croatia", "Cuba", "Cyprus", "Czech Republic", "Denmark", "Djibouti", "Dominica", "Dominican Republic", "East Timor", "Ecuador", "Egypt", "El Salvador", "Equatorial Guinea", "Eritrea", "Estonia", "Ethiopia", "Fiji", "Finland", "France", "Gabon", "Gambia", "Georgia", "Germany", "Ghana", "Greece", "Grenada", "Guatemala", "Guinea", "Guinea-Bissau", "Guyana", "Haiti", "Honduras", "Hungary", "Iceland", "India", "Indonesia", "Iran", "Iraq", "Ireland {Republic}", "Israel", "Italy", "Ivory Coast", "Jamaica", "Japan", "Jordan", "Kazakhstan", "Kenya", "Kiribati", "Korea North", "Korea South", "Kosovo", "Kuwait", "Kyrgyzstan", "Laos", "Latvia", "Lebanon", "Lesotho", "Liberia", "Libya", "Liechtenstein", "Lithuania", "Luxembourg", "Macedonia", "Madagascar", "Malawi", "Malaysia", "Maldives", "Mali", "Malta", "Marshall Islands", "Mauritania", "Mauritius", "Mexico", "Micronesia", "Moldova", "Monaco", "Mongolia", "Montenegro", "Morocco", "Mozambique", "Myanmar, {Burma}", "Namibia", "Nauru", "Nepal", "Netherlands", "New Zealand", "Nicaragua", "Niger", "Nigeria", "Norway", "Oman", "Pakistan", "Palau", "Panama", "Papua New Guinea", "Paraguay", "Peru", "Philippines", "Poland", "Portugal", "Qatar", "Romania", "Russian Federation", "Rwanda", "St Kitts & Nevis", "St Lucia", "Saint Vincent & the Grenadines", "Samoa", "San Marino", "Sao Tome & Principe", "Saudi Arabia", "Senegal", "Serbia", "Seychelles", "Sierra Leone", "Singapore", "Slovakia", "Slovenia", "Solomon Islands", "Somalia", "South Africa", "South Sudan", "Spain", "Sri Lanka", "Sudan", "Suriname", "Swaziland", "Sweden", "Switzerland", "Syria", "Taiwan", "Tajikistan", "Tanzania", "Thailand", "Togo", "Tonga", "Trinidad & Tobago", "Tunisia", "Turkey", "Turkmenistan", "Tuvalu", "Uganda", "Ukraine", "United Arab Emirates", "United Kingdom", "United States", "Uruguay", "Uzbekistan", "Vanuatu", "Vatican", "Other"];
            $scope.languageArray = ["English", "Chinese", "Italian", "Vietnamese", "Greek", "Cantonese", "Arabic", "Mandarin", "Macedonian", "French", "Spanish", "Other"];
            $scope.statelistArray = ["New South Wales", "Australian Capital Territory", "Victoria", "Queensland", "South Australia", "Western Australia", "Tasmania", "Northern Territory", "Other"];
            $scope.heartcondsArray = ["Atrial septal defect", "Ventricular septal defect", "Atrioventricular septal defect", "Patent ductus arteriosus", "Pulmonary vein anomaly", "Tricuspid atresia", "Ebstein’s anomaly ", "Dysplastic tricuspid valve", "Pulmonary stenosis", "Pulmonary atresia", "Tetralogy of Fallot", "Abnormal mitral valve", "Aortic stenosis", "Coarctation of the aorta or interrupted aorta", "Truncus arteriosus", "Bicuspid aortic valve", "Hypoplastic left heart syndrome", "Transposition of the great arteries", "Congenitally corrected transposition of the great arteries", "Double-outlet right ventricle", "Double-inlet left ventricle", "Double-inlet right ventricle", "Atrial isomerism (left or right)", "None of the above"];

            $scope.myNumber = 100;
            $scope.getNumber = function (num) {
                return new Array(num);
            }

            var istokenvalid = sessionStorage.getItem('isTokenValid');

            if (accessToken == null) {
                $state.go('/login');
            }
            else {
                var userroletype = sessionStorage.getItem('userrole');

                if (userroletype != "" && userroletype == "Admin") {
                    $scope.admin = true;
                } else {
                    $scope.admin = false;
                }
                $scope.userroleArray = ["Admin", "Coordinator"];
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
                    //$scope.searchHeartKid = false;
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
                                    $scope.ErrorMessage = "Record exported successfully (HeartKid_Results" + date + ".xls)";

                                } else {
                                    $scope.ErrorMessage = "Please refine your search and try again";
                                }

                            }
                            else {
                                var blob = new Blob([data], {type: "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"});
                                saveAs(blob, 'HeartKid_Results' + date + '.xls');
                                $scope.ErrorMessage = "Record exported successfully (HeartKid_Results" + date + ".xls";


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
                    $scope.deleteMessage = "";
                    $scope.EditMessage = "";
                    $scope.ErrorMessage = "";
                    if ($scope.formAdminData.surgerydelaycount == "") {
                        $scope.formAdminData.surgerydelaycount = null;
                    }
                    if ($scope.formAdminData.conditioncalld == 'No') {
                        $scope.formAdminData.conditioncalld = null;
                    }
                    if ($scope.formAdminData.surgeryheld == 'No') {
                        $scope.formAdminData.surgeryheld = null;
                    }
                    if ($scope.formAdminData.surgerydelay == 'No') {
                        $scope.formAdminData.surgerydelay = null;
                    }
                    if ($scope.formAdminData.anxietycond == 'No') {
                        $scope.formAdminData.anxietycond = null;
                    }
                    if ($scope.formAdminData.trvlsurg == 'No') {
                        $scope.formAdminData.trvlsurg = null;
                    }

                    if ($scope.formAdminData.agefrom == "") {
                        $scope.formAdminData.ageto = "";
                        $scope.formAdminData.age = "empty-empty";
                    }
                    else {
                        if ($scope.formAdminData.ageto == "") {
                            $scope.formAdminData.ageto = $scope.formAdminData.agefrom;
                        }
                        $scope.formAdminData.age = $scope.formAdminData.agefrom + "-" + $scope.formAdminData.ageto;
                    }


                    if ($scope.formAdminData.contactvia == 'Phone') {
                        $scope.formAdminData.contctviaphone = 'yes';
                    }
                    if ($scope.formAdminData.contactvia == 'Email') {
                        $scope.formAdminData.contctviaemail = 'yes';
                    }
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
                            $scope.searchusers = data;
                            $scope.totalrecords = data.length;
                            $location.hash('searchtable');

                            // call $anchorScroll()
                            $anchorScroll();
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
                    $scope.formAdminData.referencenumber = "";
                    $scope.formAdminData.agefrom = "";
                    $scope.formAdminData.sex = null;
                    $scope.formAdminData.state = null;
                    $scope.formAdminData.contactvia = null;
                    $scope.formAdminData.ethnicity = null;
                    $scope.formAdminData.ageto = "";
                    $scope.formAdminData.heartconds = null;
                    $scope.formAdminData.surgerydelaycount = "";
                    $scope.formAdminData.usertype = null;
                    $scope.formAdminData.surveystatus = null;
                    $scope.formAdminData.conditioncalld = null;
                    $scope.formAdminData.surgeryheld = null;
                    $scope.formAdminData.surgerydelay = null;
                    $scope.formAdminData.trvlsurg = null;
                    $scope.formAdminData.anxietycond = null;
                    $scope.deleteMessage = '';
                    $scope.EditMessage = "";
                    $scope.ErrorMessage = "";
                }
                $http({
                    url: 'heartkid/reportcount',
                    method: "GET",
                    async: false

                })

                    .then(function (response) {
                      //  var data = $.parseJSON(angular.toJson(response.data));

                        $scope.reportcount = response.data;
                        $scope.string = $scope.reportcount;
                        $scope.arrString = $scope.string.split(',');
                        $scope.reportData = [{key: "Patient", y: $scope.arrString[0]}, {
                            key: "carer",
                            y: $scope.arrString[1]
                        }, {key: "Loved one", y: $scope.arrString[2]}];
                        $scope.xFunction = function () {
                            return function (d) {
                                return d.key;
                            };
                        }
                        $scope.yFunction = function () {
                            return function (d) {
                                return d.y;
                            };
                        }

                        $scope.descriptionFunction = function () {
                            return function (d) {
                                return d.key;
                            }
                         }
                    },
                    function (response) {
                        $state.go('form.generror');
                    })
                    $http({
         url: 'heartkid/reportbarcount',
         method: "GET",
         async: false

     }) 
     
    
     .then(function(response) {
                     
         $scope.reportbarcount = response.data;
           
     $scope.config = {
    title: 'HeartKid',
    tooltips: true,
    labels: true,
    mouseover: function() {},
    mouseout: function() {},
    click: function() {},
    legend: {
      display: true,
      }
  };

     var parray
     var carray
     var larray
    
     function get_bar_values()
     {
      	 var xx=get_months();
    	var all_data=[]
    	   	
    	for(var dd=0;dd<xx.length;dd++)
    		{
       		var y_array=[]
    		var pbar_val=get_pvalues(xx[dd])
    		var cbar_val=get_cvalues(xx[dd])
    	
    		var lbar_val=get_lvalues(xx[dd])
    		y_array.push(pbar_val)
    		y_array.push(cbar_val)
    		y_array.push(lbar_val)
    		all_data.push({x:xx[dd].substring(0, 3),y:y_array})
    		}
    	 return all_data;
     }
     
     
     function get_pvalues(a_month)
     {
    	 var pvals=$scope.reportbarcount[0]
    		 if(pvals.length==0)
    		 {
    		  return 0;
    		 }
    	 
    	 for(var ii=0;ii<pvals.length;ii++)
    		 {
    		 if(a_month.substring(0, 3)==pvals[ii][2].substring(0, 3))
    			 {
    			 return pvals[ii][1];
    			 }
    		 }
    	 return 0;
     }
     
     function get_cvalues(a_month)
     {
    	
      	 if(typeof $scope.reportbarcount[1]==null)
    		 {
    		 return 0;
    		 }
         var cvals=$scope.reportbarcount[1]
         if(cvals.length==0)
		 {
		  return 0;
		 }
    	 for(var ii=0;ii<cvals.length;ii++)
    		 {
    		 if(a_month.substring(0, 2)==cvals[ii][2].substring(0, 2))
    			 {
    			 return cvals[ii][1];
    			 }
    		 }
    	 return 0; 
    	 
     }
     
     function get_lvalues(a_month)
     {
    	 
    	
    	 if(typeof $scope.reportbarcount[2]==null)
		 {
		 return 0;
		 }
    	 
         var lvals=$scope.reportbarcount[2]
         if(lvals.length==0)
		 {
		  return 0;
		 }
    	 for(var ii=0;ii<lvals.length;ii++)
    		 {
    		 if(a_month.substring(0, 2)==lvals[ii][2].substring(0, 2))
    			 {
    			 return lvals[ii][1];
    			 }
    		 }
    	 return 0;
     }
        
     var month_array=[]
     for(var i_rec=0 ; i_rec< $scope.reportbarcount.length;i_rec++)
    	 {
    	 var a_rec=$scope.reportbarcount[i_rec]
     	 }
     
     var d = new Date();
   
     $scope.data = {
    		    series: ['Patient', 'Carer', 'Loved One'],
    		    data: get_bar_values()
    		  };
      })      
    function get_months()
    {
     var monthNames = ["January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"];
    
     var today = new Date();
     var d;
     var month=[];

     for(var i = 5; i >= 0; i -= 1) {
       d = new Date(today.getFullYear(), today.getMonth() - i, 1);
       month.push(monthNames[d.getMonth()])
      }
     return month;
   
     }

                $scope.deleteuser = function (index, deluser) {

                    $http({
                        url: 'heartkid/deleterecord/' + deluser,
                        headers: {
                            'Authorization': 'Bearer ' + accessToken,
                            'Content-Type': 'application/json'
                        },
                        method: "GET"
                    })
                        .then(function (response) {
                            // success
                            $scope.deleteMessage = response.data;
                            $scope.searchusers.splice(index, 1);
                            // $scope.users = "";
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
                        headers: {
                            'Authorization': 'Bearer ' + accessToken,
                            'Content-Type': 'application/json'
                        },
                        data: $scope.formAdminData
                    })
                        .then(function (response) {
                            // success

                            if (response.data == "success") {

                                var creationMessage = response.data + $scope.formAdminData.username;
                                $scope.creationMessage = "User has been successfully created";
                                $scope.formAdminData = null;
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

                $scope.listadminuser = function () {
                    $http({
                        url: 'heartkid/listadminuser',
                        method: "GET",
                        headers: {
                            'Authorization': 'Bearer ' + accessToken,
                            'Content-Type': 'application/json'
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
                $scope.deleteuseradmin = function () {
                    var username = $scope.formAdminData.delusername;

                    $http({
                        url: 'heartkid/fetchadminuser/' + username,
                        headers: {
                            'Authorization': 'Bearer ' + accessToken,
                            'Content-Type': 'application/json'
                        },
                        method: "GET"


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
                $scope.deleteadminuser = function (index, username) {
                    $http({
                        url: 'heartkid/deleteadminuser/' + username,
                        headers: {
                            'Authorization': 'Bearer ' + accessToken,
                            'Content-Type': 'application/json'
                        },
                        method: "GET"
                    })

                        .then(function (response) {
                            var data = angular.fromJson(response.data);

                            if (data == 1) {
                                $scope.deletionMessage = "User deleted successfully  - Username : " + username;
                                $scope.users.splice(index, 1);
                            }
                            else {
                                $scope.deletionMessage = "Error in deleting user. Please try again later! ";

                            }
                        },
                        function (response) {
                            $scope.deletionMessage = "Error in deleting user. Please try again later! ";
                        })

                }

                $scope.getuserdetails = function (editID) {
                    $scope.showNumber = 'personal';
                    $scope.activeMenu = 'personal';
                    $scope.edituserdetailshow = 'true';
                    $http({
                        url: 'heartkid/getuserdetails/' + editID,
                        headers: {
                            'Authorization': 'Bearer ' + accessToken,
                            'Content-Type': 'application/json'
                        },
                        method: "GET"

                    })
                        .then(function (response) {

                            var data = angular.toJson(response.data);
                            var data1 = angular.fromJson(response.data);
                            $scope.formData = data1;
                            $location.hash('edituser');

                            // call $anchorScroll()
                            $anchorScroll();
                        },
                        function (response) {
                            alert("Error respomse----->" + response.data);
                        })
                }


                //EDIT FUCNTION
                $scope.editsavedetails = function () {
                    $scope.formData.updateddate = "admin";
                    $http({
                        url: 'heartkid/personalinfo',
                        method: "POST",
                        headers: {
                            'Authorization': 'Bearer ' + accessToken,
                            'Content-Type': 'application/json'
                        },
                        data: $scope.formData
                    })
                        .then(function (response) {
                            var data = $.parseJSON(angular.toJson(response.data));
                            $scope.searchheartkid();
                            $scope.EditMessage = "Data Saved Successfully for reference number : " + $scope.formData.referencenumber;


                        },
                        function (status) {
                            $state.go('form.generror');
                            $scope.EditMessage = "Error in saving data. Please close and login again";
                        })
                }
                $scope.closeedittable = function () {
                    $scope.edituserdetailshow = 'false';
                    $scope.EditMessage = "";
                }
                $scope.editMenu = function (menu) {
                    $scope.showNumber = menu;
                    $scope.activeMenu = menu;
                }
            }


        }])

})();

