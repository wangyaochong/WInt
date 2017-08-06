app.controller("navbar",function ($scope,$state) {
    $scope.stateName=$state.current.name;
    console.log($state.current.name);
})