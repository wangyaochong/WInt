
angular.isUndefinedOrNull = function (val) {
    return angular.isUndefined(val) || val === null
}
//以上定义的是全局js变量，不用放到rootScope中
// var app = angular.module('app', ['ngTouch','ngAnimate','ui.router', 'ngTable','ui.bootstrap']);

var app = angular.module('app', ['ui.bootstrap','ui.router', 'ngTable' ,'ui.select','ngSanitize','toaster','ngAnimate']);
app.config(function ($stateProvider, $urlRouterProvider)
{"##begin##"})