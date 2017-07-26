package program.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import program.entity.Food;
import program.repository.IFoodRepo;

import javax.annotation.PostConstruct;
import java.util.List;

@Repository
public class FoodService extends GenericService<IFoodRepo,Food> {
    @Autowired
    public FoodService(IFoodRepo foodRepo){
        this.setRepo(foodRepo);
        System.out.println("set foodRepo in init "+foodRepo.getClass());
    }
    public Double getTotalFoodListPrice(List<Food> foodList){
        Double totalPrice=0.0;
        for (Food food : foodList) {
            totalPrice+=getOneFoodPrice(food);
        }
        return totalPrice;
    }
    public Double getOneFoodPrice(Food food){
        return food.getPrice()*(100-food.getDiscountPercent())/100*food.getCount();
    }
}
