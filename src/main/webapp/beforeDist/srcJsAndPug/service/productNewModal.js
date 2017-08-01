app.service("productNewModal",function ($uibModal,$rootScope) {
    this.showModal=function (yesCallBack,obj) {
        var modalInstance=$uibModal.open({
            templateUrl:distHtmlServiceUrl+"productNewModal.html",
            controller:function ($scope,$timeout) {
                $scope.product={};
                $scope.product.createDate=new Date();
                $scope.product.category=$rootScope.categories[0];
                $scope.product.branchGroup=$rootScope.branchGroups[0];
                $scope.isEditing=false;
                $scope.closeModal=function () {
                    modalInstance.dismiss();
                }
                $scope.clickYes=function () {
                    modalInstance.close($scope.product);
                }
                if(obj!=undefined){
                    $scope.isEditing=true;

                    $scope.product=obj;

                    $scope.product.createDate=new Date($scope.product.createDate);
                }

                initDatepicker($timeout)
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
                        $("#createDate").datepicker("update", ( $scope.product.createDate==undefined|| $scope.product.createDate=="")? new Date():
                            new Date( $scope.product.createDate))//传入当前日期
                        // $("#PrivEditNew").children("div").removeClass("form-control")
                    }, 50)
                }
            }
        })
        modalInstance.result.then(function (result) {
            console.log("productNewModal Yes",result);
            result.createDate=new Date(result.createDate)
            yesCallBack(result);
        },function () {
            console.log("productNewModal No");
        })
    }
})