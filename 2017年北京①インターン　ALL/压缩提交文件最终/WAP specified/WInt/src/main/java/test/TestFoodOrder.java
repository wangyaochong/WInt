package test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import program.bean.StringAndNumber;
import program.repository.IFoodOrderRepo;
import program.service.FoodOrderService;
import program.service.StatisticsService;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:hibernate.xml","classpath:spring.xml"})
public class TestFoodOrder {
    @Resource
    FoodOrderService foodOrderService;
    @Resource
    IFoodOrderRepo foodOrderRepo;
    @Resource
    StatisticsService statisticsService;
    @Test
    public void test(){
//        foodOrderService.getDateAndOrderCountListByBranchGroup(null);
        List dateAndOrderCountListByBranchGroup = foodOrderRepo.getDateAndOrderCountListByBranchGroup("1");
        System.out.println(dateAndOrderCountListByBranchGroup);
//        List<StringAndNumber> dateAndOrderCountListByBranchGroup = foodOrderRepo.getDateAndOrderCountListByBranchGroup("1");
//        for (Object[] objects : dateAndOrderCountListByBranchGroup) {
//            for (Object object : objects) {
//                System.out.println(object);
//            }
//        }
//        List<StringAndNumber> list=dateAndOrderCountListByBranchGroup;
//        System.out.println(foodOrderService.getDateAndOrderCountListByBranchGroup(null));
//        System.out.println(statisticsService.getAllBranchGroupDateAndOrderCountList());
//        System.out.println(dateAndOrderCountListByBranchGroup);
    }
}
