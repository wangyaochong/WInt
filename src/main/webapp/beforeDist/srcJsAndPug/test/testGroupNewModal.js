app.controller('testGroupNewModal',function ($scope, groupNewModal) {
    $scope.openModal=function () {
        groupNewModal.showModal(function (data) {
            console.log(data);
        })
    }
})