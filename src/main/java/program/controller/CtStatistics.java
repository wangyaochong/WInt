package program.controller;

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
        responseInfo.setData(allBranchGroupDateAndOrderCountList);
        return responseInfo;
    }
    @RequestMapping("/branchGroupDateAndOrderCashAmountList")//订单金额
    public ResponseInfo branchGroupDateAndOrderAmountList(){
        ResponseInfo responseInfo=new ResponseInfo();
        responseInfo.setData(allBranchGroupDateAndOrderAmountList);
        return responseInfo;
    }
    @RequestMapping("/globalFoodSellingCash")//全局食品销售额
    public ResponseInfo globalFoodSellingCash(){
        ResponseInfo responseInfo=new ResponseInfo();
        responseInfo.setData(foodSellingCashGlobal);
        return responseInfo;
    }
    @RequestMapping("/globalFoodSellingCashByDayCount")//近dayCount天簌全局食品销售额
    public ResponseInfo globalFoodSellingCashByDayCount(@RequestParam Integer dayCount){
        ResponseInfo responseInfo=new ResponseInfo();
        responseInfo.setData(statisticsService.getFoodSellingCashGlobalByDayCount(dayCount));
        return responseInfo;
    }
    @RequestMapping("/branchGroupFoodSellingCash")//按照部门的食品销售数量
    public ResponseInfo branchGroupFoodSellingCash(){
        ResponseInfo responseInfo=new ResponseInfo();
        responseInfo.setData(allBranchGroupFoodSellingCash);
        return responseInfo;
    }
    @RequestMapping("/branchGroupFoodSellingCashByDayCount")//按照部门的食品销售数量
    public ResponseInfo branchGroupFoodSellingCashByDayCount(@RequestParam Integer dayCount){
        ResponseInfo responseInfo=new ResponseInfo();
        responseInfo.setData(statisticsService.getAllBranchGroupFoodSellingCashByDayCount(dayCount));
        return responseInfo;
    }




    @RequestMapping("/allBranchGroupFoodCountList")//所有分公司的食品销售趋势
    public ResponseInfo allBranchGroupFoodCountList(){
        ResponseInfo responseInfo=new ResponseInfo();
        responseInfo.setData(allBranchGroupDateAndFoodCountList);
        return responseInfo;
    }
    @RequestMapping("/globalFoodSellCountList")//所有分公司的食品销售趋势
    public ResponseInfo globalFoodSellCountList(){
        ResponseInfo responseInfo=new ResponseInfo();
        responseInfo.setData(globalFoodSellCountList);
        return responseInfo;
    }

}
