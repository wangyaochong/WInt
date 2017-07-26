app.service("foodSettingModal",function ($uibModal,$rootScope) {

    this.showModal = function (cancelCallBack, obj) {
        var modalInstance = $uibModal.open({
            templateUrl: distHtmlServiceUrl + "foodSettingModal.html",
            controller: function ($scope, $timeout) {
                $scope.suspendedOption=[true,false];
                $scope.food=obj;
                $scope.closeModal = function () {
                    modalInstance.dismiss();
                }
                $scope.clickYes = function () {
                    modalInstance.close($scope.food);
                }

                modalInstance.result.then(function (result) {
                    $rootScope.saveFood(result);
                }, function () {
                    cancelCallBack()

                })

            }
        })

    }
})