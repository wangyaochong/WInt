app.service("branchGroupNewModal",function ($uibModal,$rootScope) {
    this.showModal=function (yesCallBack,id) {
        var modalInstance=$uibModal.open({
            templateUrl:distHtmlServiceUrl+"branchGroupNewModal.html",
            controller:function ($scope,$timeout) {
                $scope.branchGroup={};
                $scope.isEditing=false;
                $scope.modalTitle="Create Branch Group";
                $scope.closeModal=function () {
                    modalInstance.dismiss();
                }
                $scope.clickYes=function () {
                    // $scope.branchGroup.createDate=$("#createDate").datepicker('getDate').getTime();
                    modalInstance.close($scope.branchGroup);
                }

                if(id!=undefined){
                    $scope.isEditing=true;
                    $scope.branchGroup=$rootScope.getbranchGroups(id);
                    $scope.branchGroup.id=id;
                    $scope.modalTitle="Edit Branch Group"
                }

                // initDatepicker($timeout)
                function initDatepicker($timeout) {
                    $timeout(function () {
                        $("#createDate").datepicker({
                            maxViewMode: 2,//设置最多可以从月开始设置
                            // language: "zh-CN",//设置语言为中文
                            language: "en-US",//设置语言为英文
                            autoclose: true,//设置选择日期后自动关闭
                            todayHighlight: true,//设置高亮今日
                            todayBtn: true,//显示今日按钮
                            format:"yyyy/mm/dd"
                        })
                        $("#createDate").datepicker("update", $scope.branchGroup.createDate==undefined? new Date():new Date( $scope.branchGroup.createDate))//传入当前日期
                        // $("#PrivEditNew").children("div").removeClass("form-control")
                    }, 100)
                }
            }
        })
        modalInstance.result.then(function (result) {
            console.log("branchGroupNewModal Yes",result);
            yesCallBack(result);
        },function () {
            console.log("branchGroupNewModal No");
        })
    }
})