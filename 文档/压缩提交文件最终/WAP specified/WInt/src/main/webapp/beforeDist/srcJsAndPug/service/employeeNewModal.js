app.service("employeeNewModal",function ($uibModal,$rootScope,crud) {
    this.showModal=function (yesCallBack,obj) {
        var modalInstance=$uibModal.open({
            templateUrl:distHtmlServiceUrl+"employeeNewModal.html",
            controller:function ($scope,$timeout) {

                $scope.branchGroups=$rootScope.queryPageBranchGroup({
                    pageNum:0,
                    pageSize:singlePageSize
                }) .data.content;
                $scope.roles=crud.getQueryResult("Employee/roles").data;

                $scope.employee={};
                $scope.employee.gender="MALE";
                $scope.isEditing=false;
                $scope.modalTitle="Create Employee";
                $scope.employee.branchGroup=$scope.branchGroups[0];
                $scope.employee.role=$scope.roles[0];

                $scope.closeModal=function () {
                    modalInstance.dismiss();
                }
                $scope.clickYes=function () {
                    $scope.employee.birthDate=$("#birthDate").datepicker('getDate').getTime();
                    modalInstance.close($scope.employee);
                }

                if(obj!=undefined){
                    $scope.isEditing=true;
                    $scope.employee=obj;
                    $scope.branchGroups.forEach(function (b) {
                        if(b.id==$scope.employee.branchGroup.id){
                            $scope.employee.branchGroup=b;
                        }
                    })
                    // $scope.employee=$rootScope.getemployees(id);
                    // $scope.employee.id=id;
                    $scope.modalTitle="Edit Employee"
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
                        $("#birthDate").datepicker("update", ($scope.employee.birthDate==undefined||$scope.employee.birthDate=="")? new Date():
                            new Date($scope.employee.birthDate))//传入当前日期
                        // $("#PrivEditNew").children("div").removeClass("form-control")
                    }, 100)
                }
            }
        })
        modalInstance.result.then(function (result) {
            console.log("employeeNewModal Yes",result);
            yesCallBack(result);
        },function () {
            console.log("employeeNewModal No");
        })
    }
})

