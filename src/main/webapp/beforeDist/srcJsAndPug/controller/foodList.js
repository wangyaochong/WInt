app.controller("foodList", function ($scope, $rootScope, orderStoreNewModal,foodSettingModal) {
    $scope.orderFoodList = [];
    $scope.categories = $rootScope.queryPageCategory({pageNum: 0, pageSize: singlePageSize}).data.content;
    $scope.foodsData = {};
    $scope.foodCategory = "";
    $scope.foodName = "";
    $scope.foodBranchGroup = ""
    $scope.priceOrder="";
    $scope.sellNumberOrder="";
    $scope.discountOrder="";
    $scope.suspendedOption="";
    $scope.changeDirection=function (prop) {
        if($scope[prop]==""){//如果点击了另一个排序
            $scope.initOrder();
        }
        $scope[prop]=$scope.getNextOrderDirection($scope[prop]);
    }
    $scope.initOrder=function () {
        $scope.priceOrder="";
        $scope.sellNumberOrder="";
        $scope.discountOrder="";
    }
    $scope.getNextOrderDirection=function(currentDirection){
        if(currentDirection=="desc"){
            return "asc";
        }
        if(currentDirection=="asc"){
            return "desc";
        }
         return "desc";;
    }
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
    $scope.$watchGroup(["foodCategory", "foodName","priceOrder","sellNumberOrder","discountOrder","suspendedOption"], function (newValues, oldValue) {
        $scope.reloadFoodList($scope.foodsData.number+1);
    })
    $scope.reloadFoodList = function (pageNumber) {
        var orderBy="";
        var orderAsc=false;
        if($scope.priceOrder!=""){
            orderBy="price";
            orderAsc=$scope.priceOrder=="asc"?true:false;
        }
        if($scope.sellNumberOrder!=""){
            orderBy="monthlySellNumber";
            orderAsc=$scope.sellNumberOrder=="asc"?true:false;
        }
        if($scope.discountOrder!=""){
            orderBy="discountPercent";
            orderAsc=$scope.discountOrder=="asc"?true:false;
        }
        var isSuspended=null;
        if($scope.suspendedOption=="true"){
            isSuspended=true;
        }else if($scope.suspendedOption=='false'){
            isSuspended=false;
        }
        $scope.foodsData = $rootScope.queryPageFood({
            condition: {
                name: $scope.foodName,
                category: {
                    name: $scope.foodCategory
                },
                branchGroup: {
                    id: $scope.foodBranchGroup
                },
                isSuspended:isSuspended
            }, pageNum: pageNumber-1, pageSize: 12,
            orderBy:orderBy==""?null:orderBy,
            orderAsc:orderBy==""?null:orderAsc
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