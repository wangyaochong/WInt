app.controller('orderList',function ($scope,$rootScope,customerNewModal,NgTableParams) {
    $scope.orders=$rootScope.queryPageFoodOrder({pageNum: 0, pageSize: 25}).data.content;
    $scope.ordersTableParams = new NgTableParams({count: 25}, {
        counts: [25,50,100],//代表用户不可以切换每页显示的数量
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
                    cashier:{
                        name:filter.cashier==undefined?null:filter.cashier
                    }
                },
                pageNum:params.page()-1,
                pageSize:params.count()
            }
            if(orderBy.length!=0){
                queryPageObject.orderBy=Object.keys(sorting)[0];
                queryPageObject.orderAsc=  (orderBy[0][0]=='+'?true:false);
            }

            var queryPageCustomer = $rootScope.queryPageFoodOrder(queryPageObject);
            console.log(queryPageCustomer);
            params.total(queryPageCustomer.data.totalElements);
            return queryPageCustomer.data.content;

        }
    });
})