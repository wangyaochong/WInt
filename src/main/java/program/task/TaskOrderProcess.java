package program.task;

import org.apache.commons.lang3.RandomUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import program.controller.CtFoodOrder;
import program.controller.CtProduct;
import program.entity.*;
import program.entity.enums.EnumOrderStatus;
import program.entity.enums.EnumOrderType;
import program.repository.*;
import program.service.FoodInstanceService;
import program.util.SmsUtil;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Component
public class TaskOrderProcess {
    @Resource
    IFoodOrderRepo foodOrderRepo;
    @Resource
    IFoodRepo foodRepo;
    @Resource
    IBranchGroupRepo branchGroupRepo;
    @Resource
    IFoodInstanceRepo foodInstanceRepo;
    @Resource
    FoodInstanceService foodInstanceService;
    @Resource
    IEmployeeRepo employeeRepo;
    @Resource
    IProductRepo productRepo;
    @Resource
    IProductInstanceRepo productInstanceRepo;
    final long initDelay=30000L;

    @Scheduled(fixedDelay = 5000,initialDelay =initDelay)
    public void processCOOKEDOrder(){
        if(CtFoodOrder.simulationStarted==false){
            return ;
        }
        List<FoodOrder> cooked = foodOrderRepo.findAllByOrderStatus(EnumOrderStatus.COOKED.toString());
        for (FoodOrder foodOrder : cooked) {
            foodOrder.setOrderStatus(EnumOrderStatus.DELIVERING.toString());
            foodOrderRepo.save(foodOrder);
        }
    }
    @Scheduled(fixedDelay = 13000,initialDelay = initDelay)
    public void processDELIVERINGOrder(){
        if(CtFoodOrder.simulationStarted==false){
            return ;
        }
        List<FoodOrder> cooked = foodOrderRepo.findAllByOrderStatus(EnumOrderStatus.DELIVERING.toString());
        for (FoodOrder foodOrder : cooked) {
            foodOrder.setOrderStatus(EnumOrderStatus.DELIVERED.toString());
            foodOrderRepo.save(foodOrder);
        }
    }
    @Scheduled(fixedDelay = 30000,initialDelay = initDelay)
    public void processPRECOOKOrder(){//提前准备食物
        if(CtFoodOrder.simulationStarted==false){
            return ;
        }
        for (BranchGroup branchGroup : branchGroupRepo.findAll()) {
            List<Food> foods = foodRepo.queryFoodsByBranchGroup(branchGroup);
//            List<Employee> employees = employeeRepo.queryEmployeesByBranchGroupAndRole(branchGroup, EnumRole.CASHIER.toString());
            FoodOrder foodOrder=new FoodOrder(null,null,null,foodInstanceService.randomCreateFoodInstanceFromFoodList(foods,4,2,new Date()),new Date(),null,0d,false, EnumOrderType.SYSTEM_FOR_PREPARATION.toString(),EnumOrderStatus.COOKING.toString(),branchGroup);
            foodOrderRepo.saveAndFlush(foodOrder);
        }
    }

    @Scheduled(fixedDelay = 5000,initialDelay = initDelay)
    public void checkConsumingRate(){
        for (BranchGroup branchGroup : branchGroupRepo.findAll()) {
            for (Product product : productRepo.findAllByBranchGroup(branchGroup)) {
                Double averageConsumeForOneDayInBranchByName = productInstanceRepo.getAverageConsumeForOneDayInBranchByName(branchGroup.getId(), 15, product.getName());
                product.setPredictedConsumingDays(product.getCount()/averageConsumeForOneDayInBranchByName);
                product.setPredictedConsumingRatePerDay(averageConsumeForOneDayInBranchByName);
                if(CtProduct.simulationStarted==true){
                    productInstanceRepo.save(new ProductInstance(product,new Date()));//随机消耗库存
                    product.setCount(product.getCount()-1<=0?0:product.getCount()-1);
                    //只有在使用非完美库存管理才发送短信
                    if(CtProduct.idealProductManage==false&&(product.getCount()<product.getCountToAlarm()||product.getPredictedConsumingDays()<product.getDaysLeftToAlarm())){
                        System.out.println("发送短信："+product.getName()+"库存不足");
//                        String response = SmsUtil.sendMessage(product.getName());
//                        System.out.println(response);
                    }
                    if(CtProduct.idealProductManage ==true&& (product.getCount()<product.getCountToAlarm()||product.getPredictedConsumingDays()<product.getDaysLeftToAlarm())){
                        product.setCount((int) (product.getCount()+product.getDaysToRecharge()*averageConsumeForOneDayInBranchByName));
                    }

                }
                productRepo.save(product);
            }
        }
    }
}
