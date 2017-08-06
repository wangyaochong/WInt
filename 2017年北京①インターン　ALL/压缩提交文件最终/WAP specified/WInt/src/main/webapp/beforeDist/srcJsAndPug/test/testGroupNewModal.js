app.controller('testGroupNewModal',function ($scope, branchGroupNewModal) {
    $scope.openModal=function () {
        branchGroupNewModal.showModal(function (data) {
            console.log(data);
        })
    }
})