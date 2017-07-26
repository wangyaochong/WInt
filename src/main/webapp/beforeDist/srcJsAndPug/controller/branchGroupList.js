app.controller('branchGroupList',function ($scope,NgTableParams,$rootScope,branchGroupNewModal) {
    $scope.addNewBranchGroup=function (id) {
        branchGroupNewModal.showModal(function (data) {
            $rootScope.saveBranchGroup(data);
            $scope.customerListTableParams.reload();
        },id);
    }



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



            var queryPageBranchGroup = $rootScope.queryPageBranchGroup(queryPageObject);
            console.log(queryPageBranchGroup);
            params.total(queryPageBranchGroup.data.totalElements);
            return queryPageBranchGroup.data.content;

        }
    });
})