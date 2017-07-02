var distRootUrl="/WInt/dist/"
var distHtmlRootUrl=distRootUrl+"html/"
var distHtmlControllerUrl=distHtmlRootUrl+"controller/"
app.run(function ($rootScope) {
    $rootScope.distRootUrl=distRootUrl;
    $rootScope.distHtmlRootUrl=distHtmlRootUrl;
    $rootScope.distHtmlControllerUrl=distHtmlControllerUrl;
    $rootScope.urlIndex=distHtmlRootUrl+"index.html";
    $rootScope.urlControllerTest=distHtmlControllerUrl+"test.html";
})
