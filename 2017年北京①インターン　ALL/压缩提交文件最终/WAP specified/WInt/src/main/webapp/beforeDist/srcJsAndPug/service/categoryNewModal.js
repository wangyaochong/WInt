app.service("categoryNewModal",function ($uibModal,$rootScope) {

    this.showModal = function (yesCallBack, id) {
        var modalInstance = $uibModal.open({
            templateUrl: distHtmlServiceUrl + "categoryNewModal.html",
            controller: function ($scope, $timeout) {
                $scope.branchGroup = {};
                $scope.isEditing = false;
                $scope.closeModal = function () {
                    modalInstance.dismiss();
                }
                $scope.clickYes = function () {
                    modalInstance.close($scope.branchGroup);
                }

                if (id != undefined) {
                    $scope.isEditing = true;
                    $scope.branchGroup = $rootScope.getcategories(id);
                    $scope.branchGroup.id = id;
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