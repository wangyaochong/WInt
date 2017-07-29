package program.controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import program.bean.BranchGroupAndList;
import program.bean.BranchGroupAndMap;
import program.bean.MapAndMapKeys;
import program.bean.StringAndDouble;
import program.service.StatisticsService;
import program.util.ResponseInfo;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/Statistics")
public class CtStatistics {
    @Resource
    StatisticsService statisticsService;
    private List<BranchGroupAndList> allBranchGroupFoodSellingCash;
    private List<BranchGroupAndMap> allBranchGroupDateAndFoodCountList;
    private List<StringAndDouble> foodSellingCashGlobal;
    private List<BranchGroupAndList> allBranchGroupDateAndOrderAmountList;
    private List<BranchGroupAndList> allBranchGroupDateAndOrderCountList;
    private MapAndMapKeys globalFoodSellCountList;
    @PostConstruct
    public void init(){
        allBranchGroupDateAndFoodCountList= statisticsService.getAllBranchGroupDateAndFoodCountList();
        allBranchGroupFoodSellingCash= statisticsService.getAllBranchGroupFoodSellingCash();
        foodSellingCashGlobal = statisticsService.getFoodSellingCashGlobal();
        allBranchGroupDateAndOrderAmountList = statisticsService.getAllBranchGroupDateAndOrderAmountList();
        allBranchGroupDateAndOrderCountList = statisticsService.getAllBranchGroupDateAndOrderCountList();
        globalFoodSellCountList = statisticsService.globalDateAndFoodCountList();
    }
    @RequestMapping("/branchGroupDateAndOrderNumberCountList")//订单数量
    public ResponseInfo branchGroupDateAndOrderCountList(){
        ResponseInfo responseInfo=new ResponseInfo();
        if(ObjectUtils.isEmpty(allBranchGroupDateAndOrderCountList)){
            allBranchGroupDateAndOrderCountList = statisticsService.getAllBranchGroupDateAndOrderCountList();
        }
        responseInfo.setData(allBranchGroupDateAndOrderCountList);
        return responseInfo;
    }
    @RequestMapping("/branchGroupDateAndOrderCashAmountList")//订单金额
    public ResponseInfo branchGroupDateAndOrderAmountList(){
        ResponseInfo responseInfo=new ResponseInfo();
        if(ObjectUtils.isEmpty(allBranchGroupDateAndOrderAmountList)){
            allBranchGroupDateAndOrderAmountList = statisticsService.getAllBranchGroupDateAndOrderAmountList();
        }
        responseInfo.setData(allBranchGroupDateAndOrderAmountList);
        return responseInfo;
    }
    @RequestMapping("/globalFoodSellingCash")//全局食品销售额
    public ResponseInfo globalFoodSellingCash(){
        ResponseInfo responseInfo=new ResponseInfo();
        if(ObjectUtils.isEmpty(foodSellingCashGlobal)){
            foodSellingCashGlobal = statisticsService.getFoodSellingCashGlobal();
        }
        responseInfo.setData(foodSellingCashGlobal);
        return responseInfo;
    }
    @RequestMapping("/branchGroupFoodSellingCash")//按照部门的食品销售数量
    public ResponseInfo branchGroupFoodSellingCash(){
        ResponseInfo responseInfo=new ResponseInfo();
        if(ObjectUtils.isEmpty(allBranchGroupFoodSellingCash)){
            allBranchGroupFoodSellingCash= statisticsService.getAllBranchGroupFoodSellingCash();
        }
        responseInfo.setData(allBranchGroupFoodSellingCash);
        return responseInfo;
    }
    @RequestMapping("/globalFoodSellingCashByDayCount")//近dayCount天簌全局食品销售额
    public ResponseInfo globalFoodSellingCashByDayCount(@RequestParam Integer dayCount){
        ResponseInfo responseInfo=new ResponseInfo();
        responseInfo.setData(statisticsService.getFoodSellingCashGlobalByDayCount(dayCount));
        return responseInfo;
    }
    @RequestMapping("/branchGroupFoodSellingCashByDayCount")//按照部门的食品销售数量
    public ResponseInfo branchGroupFoodSellingCashByDayCount(@RequestParam Integer dayCount){
        ResponseInfo responseInfo=new ResponseInfo();
        responseInfo.setData(statisticsService.getAllBranchGroupFoodSellingCashByDayCount(dayCount));
        return responseInfo;
    }


    //选出当天成交额最大的10个产品
    public List<BranchGroupAndMap> filterTop10AllBranchGroupFoodCountList(List<BranchGroupAndMap> list){
        List<BranchGroupAndMap> result=new ArrayList<>();
        for (BranchGroupAndMap branchGroupAndMap : list) {
            List<StringAndDouble> foodSellingCashGlobalByBranchGroupAndDayCount= statisticsService.getFoodSellingCashByBranchGroupAndDayCount(branchGroupAndMap.getBranchGroup(),1);
            ArrayList<String> foodNames=new ArrayList<>();
            for (StringAndDouble stringAndDouble : foodSellingCashGlobalByBranchGroupAndDayCount) {
                foodNames.add(stringAndDouble.getString());
            }
            BranchGroupAndMap one=new BranchGroupAndMap();
            one.setBranchGroup(branchGroupAndMap.getBranchGroup());
            one.setMap(new HashMap<>());
            one.setMapKeys(new ArrayList<>());
            //找到销售额最大的食品
            for (Object o : branchGroupAndMap.getMapKeys()) {
                if(foodNames.contains(o)){
                    one.getMapKeys().add(o);
                    one.getMap().put(o,branchGroupAndMap.getMap().get(o));
                }
            }
            result.add(one);
        }
        return result;
    }

    @RequestMapping("/allBranchGroupFoodCountList")//所有分公司的食品销售趋势
    public ResponseInfo allBranchGroupFoodCountList(){
        ResponseInfo responseInfo=new ResponseInfo();
        if(ObjectUtils.isEmpty(allBranchGroupDateAndFoodCountList)){
            allBranchGroupDateAndFoodCountList= statisticsService.getAllBranchGroupDateAndFoodCountList();
        }
        responseInfo.setData(filterTop10AllBranchGroupFoodCountList(allBranchGroupDateAndFoodCountList));
        return responseInfo;
    }

    @RequestMapping("/globalFoodSellCountList")//所有分公司的食品销售趋势
    public ResponseInfo globalFoodSellCountList(){
        //选出当天成交额最大的10个产品　　
        ResponseInfo responseInfo=new ResponseInfo();
        globalFoodSellCountList = statisticsService.globalDateAndFoodCountList();
        List<StringAndDouble> foodSellingCashGlobalByDayCount = statisticsService.getFoodSellingCashGlobalByDayCount(1);
        ArrayList<String> foodNames=new ArrayList<>();
        foodSellingCashGlobalByDayCount.forEach(foodName->{
            foodNames.add(foodName.getString());
        });
        MapAndMapKeys mapAndMapKeys=new MapAndMapKeys();
            mapAndMapKeys.setMapKeys(new ArrayList<>());
            mapAndMapKeys.setMap(new HashMap<>());
        for (Object o: globalFoodSellCountList.getMapKeys()) {
            if(foodNames.contains(o)){
                mapAndMapKeys.getMapKeys().add(o);
                mapAndMapKeys.getMap().put(o,globalFoodSellCountList.getMap().get(o));
            }
        }
        responseInfo.setData(mapAndMapKeys);
        return responseInfo;
    }

}
