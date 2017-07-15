app.controller('index',function ($scope,$state) {
    $scope.currentStateName=$state.current.name;
   console.log( $state.current);
})