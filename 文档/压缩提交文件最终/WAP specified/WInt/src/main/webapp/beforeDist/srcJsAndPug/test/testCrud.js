app.controller('testCrud',function ($state, crud, $scope) {
    var queryResult = crud.getQueryResult('testEntity');
    console.log(queryResult);
    console.log('测试crud');

    var postObjectResult = crud.postObjectResult("Customer/queryPage",{condition:{age:1},pageNum:0,pageSize:2,orderBy:'email',orderAsc:false});
    console.log(postObjectResult);

})