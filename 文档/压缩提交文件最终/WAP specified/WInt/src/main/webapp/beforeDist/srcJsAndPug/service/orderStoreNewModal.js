app.service("orderStoreNewModal",function ($uibModal,$rootScope) {
    this.showModal = function (yesCallBack, foodList) {
        var modalInstance = $uibModal.open({
            templateUrl: distHtmlServiceUrl + "orderStoreNewModal.html",
            controller: function ($scope, $timeout) {
                $scope.order={
                    orderStatus:'COOKING',
                    orderBeginDateTime:new Date(),
                    foodList:foodList,
                    cashier:$rootScope.currentUser,
                    orderType:"STORE",
                    totalPrice:$rootScope.getFoodListPrice(foodList),
                    branchGroup:$rootScope.currentUser.branchGroup
                }
                $scope.isEditing = false;
                $scope.closeModal = function () {
                    modalInstance.dismiss();
                }
                $scope.clickYes = function () {
                    modalInstance.close($scope.order);
                }
            }
        })
        modalInstance.result.then(function (result) {
            console.log("categoryNewModal Yes", result);
            yesCallBack(result);
        }, function () {
            console.log("categoryNewModal No");
        })
    }
})