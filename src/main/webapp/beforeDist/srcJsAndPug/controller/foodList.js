app.controller("foodList", function ($scope, $rootScope, orderStoreNewModal,foodSettingModal) {
    $scope.orderFoodList = [];
    $scope.categories = $rootScope.queryPageCategory({pageNum: 0, pageSize: singlePageSize}).data.content;
    $scope.foodsData = {};
    $scope.foodCategory = "";
    $scope.foodName = "";
    $scope.foodBranchGroup = ""
    if ($rootScope.currentUser != null) {
        $scope.foodBranchGroup = $rootScope.currentUser.branchGroup.id;
    }
    $scope.setFood=function (food) {
        foodSettingModal.showModal(function () {
            var id=food.id;
            food=$rootScope.getfoods(food.id);
            food.id=id;
            for(var i=0;i<$scope.foods.length;i++){
                if($scope.foods[i].id==food.id){
                    $scope.foods[i]=food;
                }
            }
        },food);
    }
    $scope.$watchGroup(["foodCategory", "foodName"], function (newValues, oldValue) {
        $scope.reloadFoodList($scope.foodsData.number+1);
    })
    $scope.reloadFoodList = function (pageNumber) {
        $scope.foodsData = $rootScope.queryPageFood({
            condition: {
                name: $scope.foodName,
                category: {
                    name: $scope.foodCategory
                },
                branchGroup: {
                    id: $scope.foodBranchGroup
                }
            }, pageNum: pageNumber-1, pageSize: 12
        }).data;
        $scope.foods = $scope.foodsData.content;
        $scope.pageNumbers=[];
        for(var i=1;i<=$scope.foodsData.totalPages;i++){
            $scope.pageNumbers.push(i);
        }
    }

    $scope.clearSearch = function () {
        $scope.foodCategory = "";
        $scope.foodName = "";
    }
    $scope.setCategory = function (x) {
        $scope.foodCategory = x;
    }
    $scope.createNewOrder = function () {
        orderStoreNewModal.showModal(function(data){
            $rootScope.saveFoodOrder(data);
        },$scope.orderFoodList);
    }
    $scope.clearCart = function () {
        for (var i = 0; i < $scope.orderFoodList.length; i++) {
            $scope.orderFoodList[i].count = 1;
        }
        $scope.orderFoodList = [];
    }

    $scope.removeFromOrderFoodList = function (o) {
        for (var i = 0; i < $scope.orderFoodList.length; i++) {
            if (o.id == $scope.orderFoodList[i].id) {
                if ($scope.orderFoodList[i].count == 1) {
                    $scope.orderFoodList.splice(i, 1);
                } else {
                    $scope.orderFoodList[i].count--;
                }
            }
        }
    }
    $scope.addToOrderFoodList = function (o) {
        for (var i = 0; i < $scope.orderFoodList.length; i++) {
            if (o.id == $scope.orderFoodList[i].id) {
                $scope.orderFoodList[i].count++;
                return;
            }
        }
        $scope.orderFoodList.push(o);
    }
    $scope.reloadFoodList(1);
})