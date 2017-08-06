app.controller('employeeList',function ($scope,NgTableParams,$rootScope,employeeNewModal) {
    $scope.addNewEmployee=function (id) {
        employeeNewModal.showModal(function (data) {
            $rootScope.saveEmployee(data);
            $scope.customerListTableParams.reload();
        },id);
    }
    $scope.isEditingSalarySendConfig=false;
    $scope.getSendSalaryConfig=function () {
        $scope.globalConfig=$rootScope.getglobalConfigs("1");
        $scope.globalConfig.id="1";
    }
    $scope.getSendSalaryConfig();

    $scope.saveSalarySendConfig=function () {
        $rootScope.saveSalarySendConfig($scope.salarySendConfig);
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
                    branchGroup:{
                        name: filter.branchGroup==undefined?null: filter.branchGroup
                    },
                    id:filter.id==undefined?null:filter.id,
                    role:filter.role==undefined?null:filter.role,
                    name:filter.name==undefined?null:filter.name,
                    phoneNumber:filter.phoneNumber==undefined?null:filter.phoneNumber,
                    birthDate:filter.birthDate==undefined?null:filter.birthDate,
                    email:filter.email==undefined?null:filter.email,
                    age:filter.age==undefined?null:filter.age,
                    gender:filter.gender==undefined?null:filter.gender
                },
                pageNum:params.page()-1,
                pageSize:params.count()
            }
            if(orderBy.length!=0){
                queryPageObject.orderBy=Object.keys(sorting)[0];
                queryPageObject.orderAsc=  (orderBy[0][0]=='+'?true:false);
            }



            var queryPage = $rootScope.queryPageEmployee(queryPageObject);
            console.log(queryPage);
            params.total(queryPage.data.totalElements);
            return queryPage.data.content;

        }
    });
})