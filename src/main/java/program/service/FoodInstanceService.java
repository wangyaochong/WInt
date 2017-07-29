package program.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import program.entity.BranchGroup;
import program.entity.Food;
import program.entity.FoodInstance;
import program.repository.IFoodInstanceRepo;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Repository
public class FoodInstanceService extends GenericService<IFoodInstanceRepo,FoodInstance> {
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
}
