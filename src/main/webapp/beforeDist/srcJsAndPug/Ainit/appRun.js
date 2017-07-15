var distRootUrl="/WInt/dist/";
var distHtmlRootUrl=distRootUrl+"html/";
var distHtmlControllerUrl=distHtmlRootUrl+"controller/";
var distHtmlServiceUrl=distHtmlRootUrl+"service/";
var distHtmlTestUrl=distHtmlRootUrl+"test/";

app.run(function ($rootScope,crud) {
    $rootScope.distRootUrl=distRootUrl;
    $rootScope.distHtmlRootUrl=distHtmlRootUrl;
    $rootScope.distHtmlControllerUrl=distHtmlControllerUrl;
    $rootScope.distHtmlServiceUrl=distHtmlServiceUrl;
    $rootScope.distHtmlTestUrl=distHtmlTestUrl;
    $rootScope.urlIndex=distHtmlRootUrl+"index.html";


    $rootScope.urlControllerTest=distHtmlControllerUrl+"test.html";

    initSaveMethod($rootScope,crud);
    initQueryPageMethod($rootScope,crud);
})

function initQueryPageMethod($rootScope, crud) {
    var apiUrlList=["Customer","Employee"];
    var queryPagePartUrl="/queryPage";
    var queryPageMethod=crud.postObjectResult;
    apiUrlList.forEach(function (apiUrl) {
        $rootScope["queryPage"+apiUrl]=function (obj) {
            return queryPageMethod(apiUrl+queryPagePartUrl,obj);
        }
    })

    // var postObjectResult = crud.postObjectResult("Customer/queryPage",{condition:{age:1},pageNum:0,pageSize:2,orderBy:'email',orderAsc:false});
    // console.log(postObjectResult);
}


function initSaveMethod($rootScope,crud) {
    var saveMethod=crud.postObjectResult;
    var savePartUrl="/save";
    var apiUrlList=["Customer","Category","Product","BranchGroup"]
    apiUrlList.forEach(function (apiUrl) {
        $rootScope["save"+apiUrl]=function (obj) {
            saveMethod(apiUrl + savePartUrl, obj);
        }
    })

    // $rootScope.createUser=function (user) {
    //     saveMethod("User"+savePartUrl,user);
    // }
}
