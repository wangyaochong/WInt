app.service("customerNewModal",function ($uibModal,$rootScope) {
    this.showModal=function (yesCallBack,id) {
        var modalInstance=$uibModal.open({
            templateUrl:distHtmlServiceUrl+"customerNewModal.html",
            controller:function ($scope,$timeout) {
                $scope.customer={};
                $scope.customer.gender="MALE";
                $scope.isEditing=false;
                $scope.modalTitle="Create Customer";
                $scope.closeModal=function () {
                    modalInstance.dismiss();
                }
                $scope.clickYes=function () {
                    $scope.customer.birthDate=$("#birthDate").datepicker('getDate').getTime();
                    modalInstance.close($scope.customer);
                }

                if(id!=undefined){
                    $scope.isEditing=true;
                    $scope.customer=$rootScope.getcustomers(id);
                    $scope.customer.email=id;
                    $scope.modalTitle="Edit Customer"
                }

                initDatepicker($timeout)
                function initDatepicker($timeout) {
                    $timeout(function () {
                        $("#birthDate").datepicker({
                            maxViewMode: 2,//设置最多可以从月开始设置
                            // language: "zh-CN",//设置语言为中文
                            language: "en-US",//设置语言为英文
                            autoclose: true,//设置选择日期后自动关闭
                            todayHighlight: true,//设置高亮今日
                            todayBtn: true,//显示今日按钮
                            format:"yyyy/mm/dd"
                        })
                        $("#birthDate").datepicker("update", ($scope.customer.birthDate==undefined||$scope.customer.birthDate=="")   ? new Date():new Date( $scope.customer.birthDate))//传入当前日期
                        // $("#PrivEditNew").children("div").removeClass("form-control")
                    }, 100)
                }
            }
        })
        modalInstance.result.then(function (result) {
            console.log("customerNewModal Yes",result);
            yesCallBack(result);
        },function () {
            console.log("customerNewModal No");
        })
    }
})

