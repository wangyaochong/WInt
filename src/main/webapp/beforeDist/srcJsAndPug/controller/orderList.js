app.controller('orderList',function ($scope,$rootScope,customerNewModal,NgTableParams,$timeout,toaster,crud) {
    var intervalId=null;
    // $scope.orders=$rootScope.queryPageFoodOrder({pageNum: 0, pageSize: 15}).data.content;

    $scope.simulateOrder=function () {
        crud.getQueryResult("FoodOrder/startSimulation");
    }
    $timeout(function () {
        $("#needAutoUpdate").bootstrapSwitch();
        $("#needAutoUpdate").bootstrapSwitch('state',$rootScope.needAutoUpdate);
        if($rootScope.needAutoUpdate==true){
            intervalId=setInterval(function () {
                $scope.$apply(function () {
                    $scope.reloadOrderList();
                })
            },1000)
        }
        $('#needAutoUpdate').on('switchChange.bootstrapSwitch', function (e, data) {
            var newVal=data;
            if(newVal=="true"||newVal==true){
                if(intervalId!=null){
                    return
                }
                $rootScope.needAutoUpdate=true;
                intervalId=setInterval(function () {
                    $scope.$apply(function () {
                        $scope.reloadOrderList();
                    })
                },1000)
            }else{
                $rootScope.needAutoUpdate=false;
                clearInterval(intervalId);
                intervalId=null;
            }
        });
    },50)

    $scope.setOrderStatusToCooked=function (foodOrder) {
        foodOrder.orderStatus="COOKED";
        $rootScope.saveFoodOrder(foodOrder);
    }
    $scope.reloadOrderList=function () {
        $scope.ordersTableParams = new NgTableParams({count: 10}, {
            counts: [10,25,50,100],//代表用户不可以切换每页显示的数量
            paginationMaxBlocks: 10,//最多显示的按钮
            paginationMinBlocks: 5,//最少显示的按钮
            getData: function (params) {
                var filter= params.filter();
                var thisOrderBy;
                if (params.orderBy().length == 0) {
                    thisOrderBy = null;
                } else {
                    thisOrderBy = (params.orderBy()[0]).substr(1);
                }
                var orderBy = params.orderBy();
                var sorting = params.sorting();
                var isSortBy = params.isSortBy();
                var queryPageObject={
                    condition:{
                        orderBeginDateTime:nullIfUndefined(filter.orderBeginDateTime),
                        orderType:nullIfUndefined(filter.orderType),
                        totalPrice:nullIfUndefined(filter.totalPrice),
                        // cashier:{
                        //     name:filter.cashier==undefined?null:filter.cashier
                        // }
                        branchGroup: {
                            id: $scope.foodBranchGroup
                        },
                    },
                    pageNum:params.page()-1,
                    pageSize:params.count()
                }
                if(orderBy.length!=0){
                    queryPageObject.orderBy=Object.keys(sorting)[0];
                    queryPageObject.orderAsc=  (orderBy[0][0]=='+'?true:false);
                }
                if(queryPageObject.orderBy==""||queryPageObject.orderBy==undefined||queryPageObject.orderBy==null){

                }

                if(queryPageObject.orderBy==""||queryPageObject.orderBy==undefined||queryPageObject.orderBy==null){
                    queryPageObject.orderBy="orderBeginDateTime";
                    queryPageObject.orderAsc=false;
                }

                var queryPageCustomer = $rootScope.queryPageFoodOrder(queryPageObject);
                console.log(queryPageCustomer);
                params.total(queryPageCustomer.data.totalElements);
                return queryPageCustomer.data.content;

            }
        });
    }
    $scope.reloadOrderList();
})