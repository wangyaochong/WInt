app.controller("statistics",function ($scope,crud) {
    $scope.figureOption=[
        "Order Number",//0
        "Order Cash Amount",//1
        "Food Selling Cash",//2
        "Food Selling Trend"//3
    ]
    $scope.timeCycleOption=[
        'One Day',//0
        'One Week',//1
        'One Month'//2
    ]
    $scope.currentTimeCycle="One Month";
    $scope.branchGroupFoodSellingCash=crud.getQueryResult("Statistics/branchGroupFoodSellingCashByDayCount?dayCount=1").data;
    $scope.branchGroupFoodSellingCountList=crud.getQueryResult("Statistics/allBranchGroupFoodCountList").data;
    $scope.currentFigureOption="Order Number";
    var myChart = echarts.init(document.getElementById('main'));
    var data = crud.getQueryResult("Statistics/branchGroupDateAndOrderNumberCountList").data;
    myChart.setOption(initLine(data),true);
    $scope.$watchGroup(["currentFigureOption","currentTimeCycle"],function (newVal,oldVal) {
        setTimeout(function () {
            var dayCount=1;
            if($scope.timeCycleOption[0]==newVal[1]){
                dayCount=1;
            }
            if($scope.timeCycleOption[1]==newVal[1]){
                dayCount=7;
            }
            if($scope.timeCycleOption[2]==newVal[1]){
                dayCount=30;
            }
            if($scope.figureOption[0]==newVal[0]){
                var data = crud.getQueryResult("Statistics/branchGroupDateAndOrderNumberCountList").data;
                data.forEach(function (one) {
                    one.list.splice(0,one.list.length-dayCount>=0?one.list.length-dayCount:0);
                })
                myChart.setOption(initLine(data,"Order Number"),true);
            }
            if($scope.figureOption[1]==newVal[0]){
                var data = crud.getQueryResult("Statistics/branchGroupDateAndOrderCashAmountList").data;
                data.forEach(function (one) {
                    one.list.splice(0,one.list.length-dayCount>=0?one.list.length-dayCount:0);
                })
                myChart.setOption(initLine(data,"Order Cash Amount"),true);
            }
            if($scope.figureOption[2]==newVal[0]){
                var data = crud.getQueryResult("Statistics/globalFoodSellingCashByDayCount?dayCount="+dayCount).data;
                $scope.branchGroupFoodSellingCash=crud.getQueryResult("Statistics/branchGroupFoodSellingCashByDayCount?dayCount="+dayCount).data;
                myChart.setOption(initPie(data,"Global Food Selling Cash"),true);
                initAllBranchFoodSellingRatio($scope);
            }
            if($scope.figureOption[3]==newVal[0]){
                $scope.branchGroupFoodSellingCountList=crud.getQueryResult("Statistics/allBranchGroupFoodCountList").data;
                var data = crud.getQueryResult("Statistics/globalFoodSellCountList").data;
                data.mapKeys.forEach(function (key) {
                    var list=data.map[key];
                    list.splice(0,list.length-dayCount>=0?list.length-dayCount:0);
                })
                $scope.branchGroupFoodSellingCountList.forEach(function (one) {
                    one.mapKeys.forEach(function (key) {
                        var list=one.map[key];
                        list.splice(0,list.length-dayCount>=0?list.length-dayCount:0);
                    })
                })
                console.log("globalFoodSellCountList",data);
                myChart.setOption(initLineWithFoodName(data,"Global Food Selling Trend"),true);
                initAllBranchFoodSellingTrend($scope);
            }
        },100)
    })
})
function initAllBranchFoodSellingRatio($scope) {
    $scope.branchGroupFoodSellingCash.forEach(function (one) {
        var myChart=echarts.init(document.getElementById(one.branchGroup.name));
        myChart.setOption(initPie(one.list,"Food Selling Cash in "+ one.branchGroup.name),true);
    })
}
function initAllBranchFoodSellingTrend($scope) {
    $scope.branchGroupFoodSellingCountList.forEach(function (one) {
        var myChart=echarts.init(document.getElementById(one.branchGroup.name));
        myChart.setOption(initLineWithFoodName(one,"Food Selling Trend in "+ one.branchGroup.name),true);
    })
}

function initPie(data,titleText) {
    var option = {
        title : {
            text:titleText,
            // subtext: '纯属虚构',
            x:'center'
        },
        tooltip : {
            trigger: 'item',
            formatter: "{a} <br/>{b} : {c} ({d}%)"
        },
        legend: {
            orient: 'vertical',
            left: 'right',
            data: data.map(function (d) {
                return d.string;
            })
        },
        series : [
            {
                name: 'Food Selling Cash',
                type: 'pie',
                radius : '55%',
                center: ['50%', '60%'],
                data:data.map(function (d) {
                    return {value:d.number,name:d.string}
                }),
                itemStyle: {
                    emphasis: {
                        shadowBlur: 10,
                        shadowOffsetX: 0,
                        shadowColor: 'rgba(0, 0, 0, 0.5)'
                    }
                }
            }
        ]
    };
    return option;
}
function initLineWithFoodName(data,titleText) {
    var series=[];
    for(var i=0;i<data.mapKeys.length;i++){
        series[i]={
            name:data.mapKeys[i],
            type:"line",
            data:data.map[data.mapKeys[i]].map(function (d) {
                return d.number;
            })
        }
        var xAxis={
            type: 'category',
            boundaryGap: false,
            data:data.map[data.mapKeys[i]].map(function (d) {
                return d.string;
            })
        }
    }


    var option = {
        title: {
            text: titleText
            // subtext: '纯属虚构'
        },
        tooltip: {
            trigger: 'axis'
        },
        legend: {
            data:data.mapKeys,
            align:"right",
            orient:"vertical",
            right:0,
            top:40

        },
        toolbox: {
            show: true,
            feature: {
                dataZoom: {
                    yAxisIndex: 'none'
                },
                // dataView: {readOnly: false},
                magicType: {type: ['line', 'bar']},
                restore: {},
                saveAsImage: {}
            }
        },
        xAxis:  xAxis,
        yAxis: {
            type: 'value',
            // axisLabel: {
            //     formatter: '{value} °C'
            // }
        },
        series: series
    };
    return option;
}
function initLine(data,titleText) {
    var lengendData=[];
    var series=[];
    for(var i=0;i<data.length;i++){
        lengendData.push(data[i].branchGroup.name);
        series[i]={
            name:data[i].branchGroup.name,
            type:"line",
            data:data[i].list.map(function (d) {
                return d.number
            })
        }
        var xAxis={
            type: 'category',
            boundaryGap: false,
            data:data[0].list.map(function (t) { return t.string })
        }
    }
    var option = {
        title: {
            text: titleText,
            // subtext: '纯属虚构'
        },
        tooltip: {
            trigger: 'axis'
        },
        legend: {
            data:lengendData,
            align:"right",
            orient:"vertical",
            right:0,
            top:40
        },
        toolbox: {
            show: true,
            feature: {
                dataZoom: {
                    yAxisIndex: 'none'
                },
                // dataView: {readOnly: false},
                magicType: {type: ['line', 'bar']},
                restore: {},
                saveAsImage: {}
            }
        },
        xAxis:  xAxis,
        yAxis: {
            type: 'value',
            // axisLabel: {
            //     formatter: '{value} °C'
            // }
        },
        series: series
    };
    return option;
}