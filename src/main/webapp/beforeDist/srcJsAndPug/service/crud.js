var queryRoot="/WInt/"
app.service("crud",function ($http,$q) {
    function buildUrl(url, query) {
        return url=queryRoot+url+"?"+query;
    }
    //用于RequestParam
    this.getQueryResult=function (url, query) {
        if(query===undefined){
            query="";
        }
        var result=null;
        $.ajax({
            url: buildUrl(url,query),
            async:false,
            type:"get",
            success:function (response) {
                result=response;
            }
        })
        return result;
    }

    //用于RequestParam
    this.getQueryPromise=function (url, query) {
        if(query===undefined){
            query="";
        }
        var defer=$q.defer();
        $http({
            url:buildUrl(url,query),
            method:"get"
        }).then(function (response) {
            defer.resolve(response.data)
        },function (error) {
            console.log(error);
        })
        return defer.promise;
    }

    //用于RequestParam
    this.postQueryResult=function (url, query) {
        if(query===undefined){
            query="";
        }
        var result=null;
        $.ajax({
            url: buildUrl(url,query),
            async:false,
            type:"post",
            success:function (response) {
                result=response;
            }
        })
        return result;
    }

    //用于RequestParam
    this.postQueryPromise=function (url, query) {
        if(query===undefined){
            query="";
        }
        var defer=$q.defer();
        $http({
            url:buildUrl(url,query),
            method:"post"
        }).then(function (response) {
            defer.resolve(response.data)
        },function (error) {
            console.log(error);
        })
        return defer.promise;
    }

    //用于RequestBody
    this.postObjectResult=function (url, obj) {

        var result=null;
        $.ajax({
            url:url,
            data:JSON.stringify(obj),
            type:"post",
            async:false,
            contentType: "application/json",
            // dataType:"json",
            success:function (response) {
                result=response;
            }
        })
        return result;
    }

    //用于ModelAttribute
    this.getObjectResult=function (url, obj) {
        var result=null;
        $.ajax({
            url:url,
            data:obj,
            type:"get",
            async:false,
            success:function (response) {
                result=response;
            }
        })
        return result;
    }

    //用于RequestBody
    this.postObjectPromise=function (url, obj) {
        var defer=$q.defer();
        $http({
            url:url,
            data:obj,
            method:"post"
        }).then(function (response) {
            defer.resolve(response.data);
        })
        return defer.promise;
    }

    //用于ModelAttribute
    this.getObjectPromise=function (url, obj) {
        var defer=$q.defer();
        $http({
            url:url,
            params:obj,
            method:"get"
        }).then(function (response) {
            defer.resolve(response.data);
        })
        return defer.promise;
    }
})