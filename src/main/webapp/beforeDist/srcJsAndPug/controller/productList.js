app.controller('productList',function ($scope,NgTableParams,$rootScope,productNewModal,$timeout,crud,$filter) {
    $scope.addNewProduct=function (id) {
        productNewModal.showModal(function (data) {
            $rootScope.saveProduct(data);
            $scope.customerListTableParams.reload();
        },id);
    }
    $scope.startProductSimulation=function () {
        setInterval(function () {
            $scope.$apply(function () {
                $scope.reload();
            })
        },2000)
        crud.getQueryResult("Product/startSimulation");
    }
    $scope.idealProductManage=function () {
        crud.getQueryResult("Product/idealProductManage");

    }
    $scope.getNeedToPurchaseCount=function (one) {
        if(one.count<one.countToAlarm||one.predictedConsumingDays<one.daysLeftToAlarm){
            return parseInt($filter("number")( one.predictedConsumingRatePerDay*one.daysToRecharge,0));
        }
        return 0;
    }
    $scope.categories=$rootScope.queryPageCategory({pageNum:0,pageSize:singlePageSize}).data.content;


    $scope.reload=function () {
        $scope.customerListTableParams = new NgTableParams({count: 10}, {
            counts: [],//代表用户不可以切换每页显示的数量
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
                        // id:filter.id==undefined?null:filter.id,
                        name:filter.name==undefined?null:filter.name,
                        description:filter.description==undefined?null:filter.description,
                        address:filter.address==undefined?null:filter.address,
                        branchGroup: {
                            id: $scope.foodBranchGroup
                        },

                        // age:filter.age==undefined?null:filter.age,
                        // gender:filter.gender==undefined?null:filter.gender
                    },
                    pageNum:params.page()-1,
                    pageSize:params.count()
                }
                if(orderBy.length!=0){
                    queryPageObject.orderBy=Object.keys(sorting)[0];
                    queryPageObject.orderAsc=  (orderBy[0][0]=='+'?true:false);
                }

                if(queryPageObject.orderBy==""||queryPageObject.orderBy==undefined||queryPageObject.orderBy==null){
                    queryPageObject.orderBy="count";
                    queryPageObject.orderAsc=true;
                }

                var queryPage = $rootScope.queryPageProduct(queryPageObject);
                // if(queryPage.)
                console.log(queryPage);
                params.total(queryPage.data.totalElements);
                return queryPage.data.content;
            }
        });
    }
   $scope.reload();

})