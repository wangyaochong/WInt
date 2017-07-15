app.controller('customerList',function ($scope,NgTableParams,$rootScope) {
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
            var queryPageCustomer = $rootScope.queryPageCustomer({
                condition:{
                    number:filter.number==undefined?null:filter.number,
                    name:filter.name==undefined?null:filter.name,
                    phoneNumber:filter.phoneNumber==undefined?null:filter.phoneNumber,
                    email:filter.email==undefined?null:filter.email,
                    age:filter.age==undefined?null:filter.age
                },
                pageNum:params.page()-1,
                pageSize:params.count()
            });
            console.log(queryPageCustomer);
            params.total(queryPageCustomer.data.totalElements);
            return queryPageCustomer.data.content;

        }
    });
})