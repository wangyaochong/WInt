var distRootUrl="/WInt/dist/";
var distHtmlRootUrl=distRootUrl+"html/";
var distHtmlControllerUrl=distHtmlRootUrl+"controller/";
var distHtmlServiceUrl=distHtmlRootUrl+"service/";
var distHtmlTestUrl=distHtmlRootUrl+"test/";
var singlePageSize=1000000000;
var datepickerInitTime=50;

function nullIfUndefined(obj) {
    return obj==undefined?null:obj;
}
app.run(function ($rootScope,crud,$filter) {
     $rootScope.currentUser=crud.getQueryResult("LogInLogOut/currentUser").data;



    $rootScope.currentNav="branch";
    $rootScope.distRootUrl=distRootUrl;
    $rootScope.distHtmlRootUrl=distHtmlRootUrl;
    $rootScope.distHtmlControllerUrl=distHtmlControllerUrl;
    $rootScope.distHtmlServiceUrl=distHtmlServiceUrl;
    $rootScope.distHtmlTestUrl=distHtmlTestUrl;
    $rootScope.urlIndex=distHtmlRootUrl+"index.html";
    $rootScope.urlControllerTest=distHtmlControllerUrl+"test.html";

    initGetMethod($rootScope,crud);
    initSaveMethod($rootScope,crud);
    initDeleteMethod($rootScope,crud);
    initQueryPageMethod($rootScope,crud);


    $rootScope.categoriesFunc=function () {
       $rootScope.categories=$rootScope.queryPageCategory({pageNum:0,pageSize:singlePageSize}).data.content;
    }
    $rootScope.branchGroupsFunc=function () {
        $rootScope.branchGroups=$rootScope.queryPageBranchGroup({pageNum:0,pageSize:singlePageSize}).data.content;
    }
    $rootScope.isSalesManager=function () {//是否是销售经理
        return true;
    }
    $rootScope.getOneFoodPrice = function (food) {
        var price= food.price * (100 - food.discountPercent) / 100 * food.count;

        var numberFilter=$filter("number")( price,2);
        console.log("numberFilter",numberFilter)
        return parseFloat(numberFilter);
    }
    $rootScope.getFoodListPrice = function (foodList) {
        var totalPrice = 0;
        for (var i = 0; i < foodList.length; i++) {
            totalPrice += $rootScope.getOneFoodPrice(foodList[i]);
        }
        return totalPrice;
        // return $filter("number")( totalPrice,2);
    }

    $rootScope.categoriesFunc()
    $rootScope.branchGroupsFunc();
})

function initGetMethod($rootScope, crud) {
    var apiUrlList=["customers","employees","branchGroups","salarySendConfigs","products","categories","globalConfigs","foods","foodOrders","foodPackageDiscounts"];
    var getMethod=crud.getRestResult;
    apiUrlList.forEach(function (apiUrl) {
        $rootScope["get"+apiUrl]=function (id) {
            return getMethod(apiUrl,id);
        }
    })
}
function initDeleteMethod($rootScope,crud) {
    var apiUrlList=["customers","employees","branchGroups","salarySendConfigs","products","categories","globalConfigs","foods","foodOrders","foodPackageDiscounts"];
    var deleteMethod=crud.deleteRestResult;
    apiUrlList.forEach(function (apiUrl) {
        $rootScope["delete"+apiUrl]=function (id) {
            deleteMethod(apiUrl,id);
        }
    })
}
function initQueryPageMethod($rootScope, crud) {
    var apiUrlList=["Customer","Category","Product","BranchGroup","Employee","GlobalConfig","Food","FoodOrder","FoodPackageDiscount"]

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
    var apiUrlList=["Customer","Category","Product","BranchGroup","Employee","GlobalConfig","Food","FoodOrder","FoodPackageDiscount"]
    apiUrlList.forEach(function (apiUrl) {
        $rootScope["save"+apiUrl]=function (obj) {
            saveMethod(apiUrl + savePartUrl, obj);
        }
    })
    // $rootScope.createUser=function (user) {
    //     saveMethod("User"+savePartUrl,user);
    // }
}
