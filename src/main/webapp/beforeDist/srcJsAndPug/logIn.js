app.controller("logIn",function ($scope, $rootScope,crud,$state) {
    $scope.logIn=function () {
        var queryResult = crud.getQueryResult("LogInLogOut/logIn","account="+$scope.account+"&password="+$scope.password);
        if(queryResult.statusFlag=="ok"){//如果登陆成功
            $rootScope.currentUser=queryResult.data;
            if($rootScope.currentUser.role=="CASHIER"){
                window.location.href="/WInt/dist/html/index.html#!/foodList";
            }
        }
    }
})