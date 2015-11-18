
// create our angular app and inject ngAnimate and ui-router
// =============================================================================
angular.module('formApp', ['ui.router'])

// configuring our routes
// =============================================================================
    .config(function($stateProvider, $urlRouterProvider) {
       // $httpProvider.interceptors.push('errorInterceptor');
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
            .state('home', {
                url: '/form/research',
                templateUrl: 'views/home.html'
            })

            .state('form.profile', {
                url: '/research',
                templateUrl: 'views/form-profile.html'
            })
            .state('form.carer-profile', {
                url: '/research',
                templateUrl: 'views/form-carer-profile.html'
            })
            .state('form.treatment', {
                url: '/research',
                templateUrl: 'views/form-treatmnt.html'
            })

            // url will be /form/interests
            .state('form.burden-1', {
                url: '/research',
                templateUrl: 'views/form-burdendisease1.html'
            })
            .state('form.burden-add', {
                url: '/research',
                templateUrl: 'views/form-burdendisease-additional.html'
            })
            .state('form.burden-2', {
                url: '/research',
                templateUrl: 'views/form-burdendisease2.html'
            })
            .state('form.burden-3', {
                url: '/research',
                templateUrl: 'views/form-burdendisease3.html'
            })
            // url will be /form/payment
            .state('form.thankyou', {
                url: '/research',
                templateUrl: 'views/confirmation.html'
            });

        // catch all route
        // send users to the form page
        $urlRouterProvider.otherwise('/form/research');
    })

    .directive('ngHover', function() {
        return {
            link: function(scope, element) {
                element.bind('mouseenter', function() {
                    angular.element(
                        element.children()[0]).addClass('redclass')
                })
            }
        }
    })

    .directive('windowExit', function($window) {
        return {
            restrict: 'AE',
            //performance will be improved in compile
            compile: function(element, attrs){
                var myEvent = $window.attachEvent || $window.addEventListener,
                    chkevent = $window.attachEvent ? 'onbeforeunload' : 'beforeunload'; /// make IE7, IE8 compatable

                myEvent(chkevent, function (e) { // For >=IE7, Chrome, Firefox
                    var confirmationMessage = ' ';  // a space
                    (e || $window.event).returnValue = "Please complete the survey before exiting. You may just need 10-15 minutes of time. Thank You";
                    return confirmationMessage;
                });
            }
        };
    })
// our controller for the form
// =============================================================================
    .controller('formController', function($scope) {

        // we will store all of our form data in this object
        $scope.formData = {};

        // function to process the form
        $scope.processForm = function() {
            alert('You have subitted the form!');
        };

    })

    //services
    .service('dataService', function() {

        // private variable
        var _dataObj;

        this.dataObj = _dataObj;
    })

    .controller('homepagecontroller', function($scope,$http,dataService) {


        $scope.$watch('$viewContentLoaded', function(){


            $http({
                url: 'http://localhost:8080/heartkid/regcount',
                method: "GET"
                         })
                .then(function(response) {
                    var data = $.parseJSON(angular.toJson(response.data));

                    $scope.regcount = data;

                },
                function(response) { // optional
                    // failed
                    alert("ERROR---->"+response.status);
                    var statuscode = $.parseJSON(angular.toJson(response.status));

                })
        });
        var empty = "";
                $scope.proceedtosurvey = function() {
                $http({
                    url: 'http://localhost:8080/heartkid/referencegen',
                    method: "GET",
                    data:empty

                })
                    .then(function(response) {
                                var data = $.parseJSON(angular.toJson(response.data));
                        dataService.dataObj = data;


                        },
                        function(response) { // optional
                            // failed
                            alert("ERROR---->"+response.status);
                            var statuscode = $.parseJSON(angular.toJson(response.status));

                        })
            }
    })

    .controller('personalInfoContrler', function ($scope, $http, dataService) {

        $scope.formData.referencenumber = dataService.dataObj;
        $scope.showcarerdetails = 'false';

        var progress = setInterval(function () {
            var $bar = $('.bar');
            if ($bar.width() >= 400) {
                clearInterval(progress);
                $('.progress').removeClass('active');
            } else {
                $bar.width($bar.width() + 40);
            }
            $bar.text($bar.width() / 4 + "%");
        }, 800);





        //$scope.colorsArray = ["Yes", "No"];
       /* $scope.formData.countrybrth = 'Australia';*/
        $scope.yesnoArray = ["Yes", "No"];
        $scope.usertypeArray = ["Patient", "Carer","Filling this form on behalf of your passed loved one"];
        $scope.titleArray = ["Mr","Mrs","Miss","Mrs","Dr","Prof"];
        $scope.conttypeArray = ["Phone","Email"];
        $scope.ethnicityArray = ["Caucasian","Aborigional / Tores Strait Island","Mouri","Asian","Indian","Black/Afican American","European","None of the above"];
        $scope.lstofcountryArray = ["Australia","Afghanistan","Albania","Algeria","Andorra","Angola","Antigua & Deps","Argentina","Armenia","Austria","Azerbaijan","Bahamas","Bahrain","Bangladesh","Barbados","Belarus","Belgium","Belize","Benin","Bhutan","Bolivia","Bosnia Herzegovina","Botswana","Brazil","Brunei","Bulgaria","Burkina","Burundi","Cambodia","Cameroon","Canada","Cape Verde","Central African Rep","Chad","Chile","China","Colombia","Comoros","Congo","Congo {Democratic Rep}","Costa Rica","Croatia","Cuba","Cyprus","Czech Republic","Denmark","Djibouti","Dominica","Dominican Republic","East Timor","Ecuador","Egypt","El Salvador","Equatorial Guinea","Eritrea","Estonia","Ethiopia","Fiji","Finland","France","Gabon","Gambia","Georgia","Germany","Ghana","Greece","Grenada","Guatemala","Guinea","Guinea-Bissau","Guyana","Haiti","Honduras","Hungary","Iceland","India","Indonesia","Iran","Iraq","Ireland {Republic}","Israel","Italy","Ivory Coast","Jamaica","Japan","Jordan","Kazakhstan","Kenya","Kiribati","Korea North","Korea South","Kosovo","Kuwait","Kyrgyzstan","Laos","Latvia","Lebanon","Lesotho","Liberia","Libya","Liechtenstein","Lithuania","Luxembourg","Macedonia","Madagascar","Malawi","Malaysia","Maldives","Mali","Malta","Marshall Islands","Mauritania","Mauritius","Mexico","Micronesia","Moldova","Monaco","Mongolia","Montenegro","Morocco","Mozambique","Myanmar, {Burma}","Namibia","Nauru","Nepal","Netherlands","New Zealand","Nicaragua","Niger","Nigeria","Norway","Oman","Pakistan","Palau","Panama","Papua New Guinea","Paraguay","Peru","Philippines","Poland","Portugal","Qatar","Romania","Russian Federation","Rwanda","St Kitts & Nevis","St Lucia","Saint Vincent & the Grenadines","Samoa","San Marino","Sao Tome & Principe","Saudi Arabia","Senegal","Serbia","Seychelles","Sierra Leone","Singapore","Slovakia","Slovenia","Solomon Islands","Somalia","South Africa","South Sudan","Spain","Sri Lanka","Sudan","Suriname","Swaziland","Sweden","Switzerland","Syria","Taiwan","Tajikistan","Tanzania","Thailand","Togo","Tonga","Trinidad & Tobago","Tunisia","Turkey","Turkmenistan","Tuvalu","Uganda","Ukraine","United Arab Emirates","United Kingdom","United States","Uruguay","Uzbekistan","Vanuatu","Vatican","Other"];
        $scope.languageArray = ["English","Chinese","Italian","Vietnamese","Greek","Cantonese","Arabic","Mandarin","Macedonian","French","Spanish"];
        $scope.statelistArray = ["New South Wales","Australian Capital Territory","Victoria","Queensland","South Australia","Western Australia","Tasmania","Northern Territory"] ;
        $scope.showcontactvia = 'true' ;

        $scope.keydownpstcde = function()
        {
            var n = $scope.formData.postcode;


            if (n >= 1000 && n <= 2999)
                $scope.formData.state = 'New South Wales';
                if ((n >= 3000 && n <= 3999) || (n >= 8000 && n <= 8999))
                    $scope.formData.state = 'Victoria';
                    if ((n >= 4000 && n <= 4999)|| (n >= 9000 && n <= 9999))
                        $scope.formData.state = 'Queensland';
                        if (n >= 5000 && n <= 5999)
                            $scope.formData.state = 'South Australia';
                            if (n >= 6000 && n <= 6999)
                                $scope.formData.state = 'Western Australia';
                                if (n >= 7000 && n <= 7999)
                                    $scope.formData.state = 'Tasmania';
                                    if (n >= 0800 && n <= 0999)
                                        $scope.formData.state = 'Northern Territory';
            if ((n >= 0200 && n <= 0299 ))
                $scope.formData.state = 'Australian Capital Territory';
        }

        $scope.conctagree = function() {
            var selectd = $scope.formData.conctagree;
            if(selectd == 'yes')
            {
                $scope.showcontactvia = 'false' ;
            }
            else
            {
                $scope.showcontactvia = 'true' ;
                $scope.showcontactviaemail = 'false' ;
                $scope.showcontactviaphone = 'false' ;
                $scope.formData.contctviaphone = "false";
                $scope.formData.contctviaemail = "false";
            }
        }
        $scope.opencal = function($event) {

            $event.preventDefault();
            $event.stopPropagation();
            $scope.opened = true;
        };
        $scope.contctviaphone = function() {

            var selectd = $scope.formData.contctviaphone;
            if(selectd == 'yes')
            {
                $scope.showcontactviaphone = 'true' ;
            }
            else
            {
                $scope.showcontactviaphone = 'false' ;
            }
        }
        $scope.contctviaemail = function() {
            var selectd = $scope.formData.contctviaemail;

            if(selectd == 'yes')
            {
                $scope.showcontactviaemail = 'true' ;
            }
            else
            {
                $scope.showcontactviaemail = 'false' ;
            }
        }
        $scope.personalInfoSubmit = function(){
            var formstatus = "incomplete";

            $scope.formData.surveystatus = formstatus;
                       $http({
                url: 'http://localhost:8080/heartkid/personalinfo',
                method: "POST",
                data: $scope.formData
            })
                .then(function(response) {
                        // success
                       var data = $.parseJSON(angular.toJson(response.data));
                               $scope.formData.id=data.id;
                               alert("data------------------>"+data);

                },
                    function(status) { // optional
                        // failed
                        var statuscode = $.parseJSON(angular.toJson(response.status));
                        alert("ERROR---->"+statuscode);


                    })
        }

        $scope.usertypesel = function()
        {
            var selectdusertyp = $scope.formData.usertype;

            if(selectdusertyp =="Carer")
            {

                $scope.showcarerdetails = 'true';
            }
        }



    })

    .controller('burdendiseaseController', function ($scope, $http,dataService) {

        var usertype = $scope.formData.usertype;
        $scope.showcarerdetails = 'false';


      /* if(usertype =="Patient" )
       {
        $scope.showcarerdetails = 'false';
       }
        else if(usertype =="Carer")
       {
           alert("carer");
           $scope.showcarerdetails = 'true';
       }*/
        $scope.scale1to5 = ["0","1", "2","3","4","5"];
        $scope.scale1to10 = ["1", "2","3","4","5","6","7","8","9","10",">10"];
        $scope.scale1to20 = ["1", "2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20",">20"];
        $scope.yesnoArray = ["Yes", "No"];
        $scope.rating = 5;
        $scope.costinvolvedArray= ["< 100","200 – 300","400 – 500","> 500"];
        $scope.heartcondsArray = ["Atrial septal defect","Ventricular septal defect","Atrioventricular septal defect","Patent ductus arteriosus","Pulmonary vein anomaly","Tricuspid atresia","Ebstein’s anomaly ","Dysplastic tricuspid valve","Pulmonary stenosis","Pulmonary atresia","Tetralogy of Fallot","Abnormal mitral valve","Aortic stenosis","Coarctation of the aorta or interrupted aorta","Truncus arteriosus","Bicuspid aortic valve","Hypoplastic left heart syndrome","Transposition of the great arteries","Congenitally corrected transposition of the great arteries","Double-outlet right ventricle","Double-inlet left ventricle","Double-inlet right ventricle","Atrial isomerism (left or right)","None of the above"];

        $scope.change_condition = function() {
                var selectd = $scope.formData.conditioncalld;
                if(selectd == 'Yes')
                {
                    $scope.knowncondition = 'true' ;
                }
                else
                {
                    $scope.knowncondition = 'false' ;
                }
            }
        $scope.surgeryheld = function() {
            var selectd = $scope.formData.surgeryHeld;
            if(selectd == 'Yes')
            {  $scope.surgerydelayed = 'true' ; }
            else
            {
                $scope.surgerydelayed = 'false' ;
            }
        }
        $scope.childtoadultdoc = function() {
            var selectd = $scope.formData.careage16;

            if(selectd == 'Yes')
            {
                $scope.showchildtoheartdoc = 'true' ;
            }
            else
            {
                $scope.showchildtoheartdoc = 'false' ;
            }
        }
        $scope.anxietycond = function() {
            var selectd = $scope.formData.anxietycond;
            if(selectd == 'Yes')
            {
                $scope.anxietycondimpact = 'true' ;
            }
            else
            {
                $scope.anxietycondimpact = 'false' ;
            }
        }
        $scope.curentlywork = function() {

            var selectd = $scope.formData.curntwork;
            if(selectd == 'Yes')
            {
                $scope.currentworkdetails = 'true' ;
            }
            else
            {
                $scope.currentworkdetails = 'false' ;
            }

        }
        $scope.eductnchallng = function() {

            var selectd = $scope.formData.eductnchallng;
            if(selectd == 'Yes')
            {
                $scope.showschoolgrdchal = 'true' ;
            }
            else
            {
                $scope.showschoolgrdchal = 'false' ;
            }

        }
        $scope.condimpactschl = function() {
            var selectd = $scope.formData.condimpactschl;
            if(selectd == 'Yes')
            {
                $scope.showcondimpactschooldesc = 'true' ;
            }
            else
            {
                $scope.showcondimpactschooldesc = 'false' ;
            }
        }
          $scope.siblingscount = function() {
              var selectd = $scope.formData.siblingcount;

              if (selectd == '0') {
                  $scope.showimpactsiblingwitchd = 'false';
              }
              else {
                  $scope.showimpactsiblingwitchd = 'true';
              }
          }

        /*$scope.go = function(ai) {
        alert(ai);
        }
        $scope.changeColor = function(person, bool) {
            if(bool === true) {
                $scope.personColour = {color: '#'+person.colour};
            } else if (bool === false) {
                $scope.personColour = {color: 'white'}; //or, whatever the original color is
            }
        };*/
        $scope.myClass = [];
        $scope.nodes = [
            {id: 1,rate: "1" },
            {id: 2, rate: "2" },
            {id: 3, rate: "3" },
            { id: 4,rate: "4" },
            {id: 5, rate: "5" },{id: 6, rate: "6" },{id: 7, rate: "7" },{id: 8, rate: "8" },{id: 9, rate: "9" },{id: 10, rate: "10" },{id: 11, rate: ">10" }


        ];
        $scope.nodes1to5 = [
            {id: 1,rate: "1" },
            {id: 2, rate: "2" },
            {id: 3, rate: "3" },
            { id: 4,rate: "4" },
            {id: 5, rate: "5" },{id: 11, rate: ">5" }


        ];
        $scope.nodeclick = function(node) {
            $scope.formData.heartdoc = node;
            $scope.myClass = "test";
            $scope.getClass = function (nodeclass) {

                return {
                    bluebarrating: node === nodeclass

                }
            }
        }
        $scope.emergdept = function(emergnode) {
            $scope.formData.emergdeptvisit = emergnode;
            $scope.getenerClass = function (emernodeclass) {

                return {
                    bluebarrating: emergnode === emernodeclass

                }


            }
        }
        $scope.chdlivingimpact = function(node) {
            $scope.formData.chdlivingimpc = node;
            $scope.getchdimpaClass = function (nodeclass) {
                return {
                    bluebarrating: node === nodeclass

                }


            }
        }
        $scope.chdimpactwork = function(node) {
            $scope.formData.chdimpactwork = node;
            $scope.chdimpactworkClass = function (nodeclass) {
                return {
                    bluebarrating: node === nodeclass

                }


            }
        }
        $scope.missschooldays = function(node) {
            $scope.formData.missschooldays = node;
            $scope.missschooldaysClass = function (nodeclass) {
                return {
                    bluebarrating: node === nodeclass

                }


            }
        }
        $scope.Surgerydelaycount = function(node) {
            $scope.formData.Surgerydelaycount = node;
            $scope.SurgerydelaycountClass = function (nodeclass) {
                return {
                    bluebarrating: node === nodeclass

                }


            }
        }
        $scope.surgdelayed = function(node) {
            var surgdel = $scope.formData.surgerydelay ;

          if(surgdel == 'Yes')
          {
                $scope.surgerydelayedcount = 'true';

          }
            else
          {
              $scope.surgerydelayedcount = 'false';
          }
        }

        $scope.siblicksel = function(node) {
         $scope.formData.siblingchld = node;
            $scope.getClasssiblicksel = function (nodeclass) {
                return {
                    bluebarrating: node === nodeclass

                }


            }
        }

        $scope.siblickselCHDimpct = function(node) {
            $scope.formData.frstsurgerysel = node;
            $scope.getClassfrstsurgerysel = function (nodeclass) {
                return {
                    bluebarrating: node === nodeclass
                }


            }
        }


        $scope.frstsurgerysel = function(node) {
            $scope.formData.frstsurgerysel = node;
            $scope.getClassfrstsurgerysel = function (nodeclass) {
                return {
                    bluebarrating: node === nodeclass

                }
            }
        }

        $scope.hosptlsurgerysel = function(node) {
            $scope.formData.hosptlsurgery = node;
            $scope.getClasshosptlsurgery = function (nodeclass) {
                return {
                    bluebarrating: node === nodeclass

                }


            }
        }


        $scope.educsupporthospsel = function(node) {
            $scope.formData.educsupporthosp = node;
            $scope.getClasseducsupporthosp = function (nodeclass) {
                return {
                    bluebarrating: node === nodeclass

                }


            }
        }


        $scope.transpaedtoadultsel = function(node) {
            $scope.formData.transpaedtoadult = node;
            $scope.getClasstranspaedtoadult = function (nodeclass) {
                return {
                    bluebarrating: node === nodeclass

                }


            }
        }
        $scope.feelsupportsel = function(node) {
            $scope.formData.feelsupport = node;
            $scope.getClassfeelsupport = function (nodeclass) {
                return {
                    bluebarrating: node === nodeclass

                }
            }
        }
        $scope.doctorcountsee = function(node) {
            $scope.formData.doctorcountsee = node;
            $scope.getClassdoctorcountsee = function (nodeclass) {
                return {
                    bluebarrating: node === nodeclass

                }
            }
        }
        $scope.traveldistdoc = function(node) {
            $scope.formData.traveldistdoc = node;
            $scope.getClasstraveldistdoc = function (nodeclass) {
                return {
                    bluebarrating: node === nodeclass
                }
            }
        }
        $scope.aftrsurgfeel = function(node) {
            $scope.formData.aftrsurgfeel = node;
            $scope.getClassaftrsurgfeel = function (nodeclass) {
                return {
                    bluebarrating: node === nodeclass
                }
            }
        }
        $scope.ratetransitionsel = function(node) {
            $scope.formData.ratetransition = node;
            $scope.getClassratetransition = function (nodeclass) {
                return {
                    bluebarrating: node === nodeclass
                }
            }
        }

        $scope.savetreatmentform = function(){
            var formstatus = "incomplete";
            $scope.formData.surveystatus = formstatus;
            $http({
                url: 'http://localhost:8080/heartkid/diseasequant',
                method: "POST",
                data:$scope.formData
            })
                .then(function(response) {
                    },
                    function(response) { // optional

                        alert("Failure"+response.status);
                    });
        }


        $scope.saveburdendiseaseform = function(){
            var formstatus = "incomplete";
            $scope.formData.surveystatus = formstatus;
            $http({
                url:'http://localhost:8080/heartkid/burdendisease',
                method: "POST",
                data:$scope.formData
            })
                .then(function(response) {
                        // success
                        alert("Sucess"+response);
                    },
                    function(response) { // optional
                        // failed
                        alert("Failure"+response.status);
                    });
        }


        $scope.saveprodeductionform = function(){
            var formstatus = "incomplete";
            $scope.formData.surveystatus = formstatus;
            $http({
                url: 'http://localhost:8080/heartkid/producteducation',
                method: "POST",
                data:$scope.formData
            })
                .then(function(response) {
                        // success
                        alert("Sucess"+response);
                    },
                    function(response) { // optional
                        // failed
                        alert("Failure"+response.status);
                    });
        }


        $scope.savequalitycareform = function(){
            var formstatus = "incomplete";
            $scope.formData.surveystatus = formstatus;

            $http({
                url: 'http://localhost:8080/heartkid/qualitycare',
                method: "POST",
                data:$scope.formData
            })
                .then(function(response) {
                        // success
                        alert("Sucess"+response);
                    },
                    function(response) { // optional
                        // failed
                        alert("Failure"+response.status);
                    });
        }



        $scope.saveouthospitalform = function(){

            var formstatus = "success";
            $scope.formData.surveystatus = formstatus;
            $http({
                url: 'http://localhost:8080/heartkid/outhospital',
                method: "POST",
                data:$scope.formData
            })
                .then(function(response) {
                        // success
                        var data = response.data;
                        var obj1 = angular.toJson(data);
                        var result = $.parseJSON(obj1);
                       alert("result final ---"+result);

                    },
                    function(response) { // optional
                        // failed
                        alert("Failure"+response.status);
                    });
        }

    });

