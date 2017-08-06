$stateProvider

    .state('testComponent',{
        url:'/testComponent',
        templateUrl:distHtmlTestUrl+"testComponent.html"
    })

    .state('testComponent.testHttp',{
        url: '/testHttp',
        templateUrl:distHtmlTestUrl+"testHttp.html"
    })
    .state('testComponent.testGroupNewModal',{
        url:'/testGroupNewModal',
        templateUrl:distHtmlTestUrl+'testGroupNewModal.html'
    })

    .state('testComponent.testCrud',{
        url:'/testCrud',
        templateUrl:distHtmlTestUrl+'testCrud.html'
    });