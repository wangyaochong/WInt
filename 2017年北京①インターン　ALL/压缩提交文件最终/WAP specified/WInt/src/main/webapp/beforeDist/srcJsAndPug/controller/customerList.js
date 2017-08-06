app.controller('customerList',function ($scope,NgTableParams,$rootScope,customerNewModal) {
    $scope.addNewCustomer=function (id) {
        customerNewModal.showModal(function (data) {
            $rootScope.saveCustomer(data);
            $scope.customerListTableParams.reload();
        },id);
    }



    $scope.customerListTableParams = new NgTableParams({count: 25}, {
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
                    name:filter.name==undefined?null:filter.name,
                    phoneNumber:filter.phoneNumber==undefined?null:filter.phoneNumber,
                    email:filter.email==undefined?null:filter.email,
                    gender:filter.gender==undefined?null:filter.gender
                },
                pageNum:params.page()-1,
                pageSize:params.count()
            }
            if(orderBy.length!=0){
                queryPageObject.orderBy=Object.keys(sorting)[0];
                queryPageObject.orderAsc=  (orderBy[0][0]=='+'?true:false);
            }



            var queryPageCustomer = $rootScope.queryPageCustomer(queryPageObject);
            console.log(queryPageCustomer);
            params.total(queryPageCustomer.data.totalElements);
            return queryPageCustomer.data.content;

        }
    });
})