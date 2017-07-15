app.service("groupNewModal",function ($uibModal) {
    this.showModal=function (yesCallBack) {
        var modalInstance=$uibModal.open({
            templateUrl:distHtmlServiceUrl+"groupNewModal.html",
            controller:function ($scope) {
                $scope.branchGroup={};
                $scope.closeModal=function () {
                    modalInstance.dismiss();
                }
                $scope.clickYes=function () {
                    modalInstance.close($scope.branchGroup);
                }
            }
        })
        modalInstance.result.then(function (result) {
            console.log("groupNewModal Yes",result);
            yesCallBack(result);
        },function () {
            console.log("groupNewModal No");
        })
    }
})