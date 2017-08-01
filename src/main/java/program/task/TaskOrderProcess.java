package program.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import program.entity.*;
import program.entity.enums.EnumOrderStatus;
import program.entity.enums.EnumOrderType;
import program.entity.util.InitValueEntity;
import program.repository.*;
import program.service.FoodInstanceService;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

//@Component
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
        List<FoodOrder> cooked = foodOrderRepo.findAllByOrderStatus(EnumOrderStatus.COOKED.toString());
        for (FoodOrder foodOrder : cooked) {
            foodOrder.setOrderStatus(EnumOrderStatus.DELIVERING.toString());
            foodOrderRepo.save(foodOrder);
        }
    }
    @Scheduled(fixedDelay = 13000,initialDelay = initDelay)
    public void processDELIVERINGOrder(){
        List<FoodOrder> cooked = foodOrderRepo.findAllByOrderStatus(EnumOrderStatus.DELIVERING.toString());
        for (FoodOrder foodOrder : cooked) {
            foodOrder.setOrderStatus(EnumOrderStatus.DELIVERED.toString());
            foodOrderRepo.save(foodOrder);
        }
    }
    @Scheduled(fixedDelay = 17000,initialDelay = initDelay)
    public void processPRECOOKOrder(){//提前准备食物
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
                productRepo.save(product);
            }
        }
    }
}
