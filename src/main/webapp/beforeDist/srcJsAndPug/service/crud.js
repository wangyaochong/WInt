var queryRoot="/WInt/"
app.service("crud",function ($http,$q) {
    function buildQueryUrl(url, query) {
        if(query==undefined||query==null||query==""){
            return queryRoot+url;
        }
        return queryRoot+url+"?"+query;
    }
    function buildRestUrl(url, rest) {
        return queryRoot+url+"/"+rest;
    }
    //用于RequestParam
    this.getQueryResult=function (url, query) {
        if(query===undefined){
            query="";
        }
        var result=null;
        $.ajax({
            url: buildQueryUrl(url,query),
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
            url:buildQueryUrl(url,query),
            method:"get"
        }).then(function (response) {
            defer.resolve(response.data)
        },function (error) {
            console.log(error);
        })
        return defer.promise;
    }
    //用于RequestParam
    this.getRestResult=function (url, query) {
        if(query===undefined){
            query="";
        }
        var result=null;
        $.ajax({
            url: buildRestUrl(url,query),
            async:false,
            type:"get",
            success:function (response) {
                result=response;
            }
        })
        return result;
    }
    //用于RequestParam
    this.getRestPromise=function (url, query) {
        if(query===undefined){
            query="";
        }
        var defer=$q.defer();
        $http({
            url:buildRestUrl(url,query),
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
            url: buildQueryUrl(url,query),
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
            url:buildQueryUrl(url,query),
            method:"post"
        }).then(function (response) {
            defer.resolve(response.data)
        },function (error) {
            console.log(error);
        })
        return defer.promise;
    }


    this.deleteRestResult=function (url, rest) {
        if(rest===undefined){
            rest="";
        }
        var result=null;
        $.ajax({
            url: buildRestUrl(url,rest),
            async:false,
            type:"delete",
            success:function (response) {
                result=response;
            }
        })
        return result;
    }
    this.deleteRestPromise=function (url, rest) {
        if(rest===undefined){
            rest="";
        }
        var defer=$q.defer();
        $http({
            url:buildRestUrl(url,rest),
            method:"delete"
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
            url:buildQueryUrl(url),
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
            url:buildQueryUrl(url),
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
            url:buildQueryUrl(url),
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
            url:buildQueryUrl(url),
            params:obj,
            method:"get"
        }).then(function (response) {
            defer.resolve(response.data);
        })
        return defer.promise;
    }
})