app.controller("customerNew",function ($scope,$rootScope,$timeout) {
    $scope.customer= {};
    $rootScope.saveCustomer.toString();
    console.log($rootScope.saveCustomer.toString());

    $scope.localSaveCustomer=function () {
        $scope.customer.birthDate=$("#birthDate").datepicker('getDate').getTime();
        $rootScope.saveCustomer($scope.customer);
    }

    initDatepicker($timeout)
})

function initDatepicker($timeout) {
    $timeout(function () {
        $("#birthDate").datepicker({
            maxViewMode: 2,//设置最多可以从月开始设置
            // language: "zh-CN",//设置语言为中文
            language: "en-US",//设置语言为英文
            autoclose: true,//设置选择日期后自动关闭
            todayHighlight: true,//设置高亮今日
            todayBtn: true,//显示今日按钮
        })
        $("#birthDate").datepicker("update", new Date())//传入当前日期
        // $("#PrivEditNew").children("div").removeClass("form-control")
    }, 100)
}