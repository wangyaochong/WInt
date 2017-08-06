app.controller("testHttp",function ($scope,crud) {
    $scope.test="tes222aadddaaaaabbc";
    var result = crud.getQueryPromise("test/test","");
    result.then(function (data) {
        console.log("getQueryPromise","test",data);
    })
    var queryResult = crud.getQueryResult("test/test","");
    console.log("getQueryResult","test",queryResult);
    var obj={age:18,name:'wangyaochong'};

    var getObjectResult = crud.getObjectResult("test/testModelAttribute",obj);
    console.log("getObjectResult","testModelAttribute",getObjectResult);
    // var postObjectResult = crud.postObjectResult("test/testModelAttribute",obj);
    // console.log("postObjectResult",postObjectResult);

    // var getObjectResult2 = crud.getObjectResult("test/testRequestBody",obj);
    // console.log("getObjectResult2",getObjectResult2);

    var postObjectResult2 = crud.postObjectResult("test/testRequestBody",obj);
    console.log("postObjectResult2","testRequestBody",postObjectResult2);


    crud.postObjectPromise("test/testRequestBody",obj).then(function (data) {
        console.log("postObjectPromise","testRequestBody",data);
    })
    // crud.getObjectPromise("test/testRequestBody",obj).then(function (data) {
    //     console.log("getObjectPromise","testRequestBody",data);
    // })
    crud.getObjectPromise("test/testModelAttribute",obj).then(function (data) {
        console.log("getObjectPromise","testModelAttribute",data);
    })

})