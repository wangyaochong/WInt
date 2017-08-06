package program.service;


import lombok.Data;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import program.entity.BranchGroup;
import program.entity.Food;
import program.entity.FoodInstance;
import program.repository.IFoodInstanceRepo;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Repository
public class FoodInstanceService extends GenericService<IFoodInstanceRepo,FoodInstance> {
    public int currentFoodIndex=0;
    @Resource
    IFoodInstanceRepo foodInstanceRepo;
    @Resource
    EntityManager entityManager;
    @Autowired
    public FoodInstanceService(IFoodInstanceRepo foodRepo){
        this.setRepo(foodRepo);
        System.out.println("set foodRepo in init "+foodRepo.getClass());
    }
    public Double getTotalFoodListPrice(List<FoodInstance> foodInstanceList){
        Double totalPrice=0.0;
        for (FoodInstance foodInstance : foodInstanceList) {
            totalPrice+=getOneFoodPrice(foodInstance);
        }
        return totalPrice;
    }
    public Double getOneFoodPrice(FoodInstance foodInstance){
        return foodInstance.getPrice()*(100- foodInstance.getDiscountPercent())/100* foodInstance.getCount();
    }
    public Long getFoodSellingCountInBranch(BranchGroup branchGroup, Food food){
        Query nativeQuery = entityManager.createNativeQuery("select sum(count) from foodinstance where branchGroup_id=:branchGroupId and name=:name");
        nativeQuery.setParameter("branchGroupId",branchGroup.getId());
        nativeQuery.setParameter("name",food.getName());
        return Long.parseLong(nativeQuery.getSingleResult().toString());
    }

    static  int currentFoodInsertCount=1;
    static  long totalOrderCount =0;
    public List<FoodInstance> randomCreateFoodInstanceFromFoodList(List<Food> foodList, Integer foodCount, Integer fluctuateCount,Date orderCreateDate) {
        ArrayList<FoodInstance> foodInstanceList = new ArrayList<>();
        Integer count = foodCount + fluctuateCount / 2 - RandomUtils.nextInt(0, fluctuateCount + 1);//最终选取的食品数量可能多可能少，有波动
//        Food fishBurgerFromFoodList = getFishBurgerFromFoodList(foodList);
        while (count > 0) {
//            if(currentCount.equals(addSaveFoodEveryCount)){
//                for(int i=0;i<addNum;i++){
//                    foodInstanceList.add(foodInstanceRepo.save(new FoodInstance(fishBurgerFromFoodList,orderCreateDate)));//添加趋势增长的食品
//                }
//                currentCount=0l;
//                System.out.println("增加--->"+addNum+"--->条鱼汉堡的记录");
//                addNum+=2;
//            }else{
            if(0==RandomUtils.nextInt(0,50)){//某种明星产品的占比
                for(int i=0;i<currentFoodInsertCount;i++){
                    foodInstanceList.add(foodInstanceRepo.save(new FoodInstance(foodList.get(currentFoodIndex),orderCreateDate)));//选择权重更多的食品
                }
//                totalOrderCount++;
                currentFoodInsertCount+=(totalOrderCount/1000);
            }else{
                foodInstanceList.add(foodInstanceRepo.save(new FoodInstance(foodList.get(RandomUtils.nextInt(0, foodList.size())),orderCreateDate)));
            }
//            }
//            currentCount++;
            count--;
        }
        totalOrderCount++;
        return foodInstanceList;
    }

}
