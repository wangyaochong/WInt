package program.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import program.entity.FoodPackageDiscount;
import program.repository.IFoodPackageDiscountRepo;

@Service
public class FoodPackageDiscountService   extends GenericService<IFoodPackageDiscountRepo,FoodPackageDiscount>{
    @Autowired
    public FoodPackageDiscountService(IFoodPackageDiscountRepo repo){
        this.setRepo(repo);
        System.out.println("set FoodPackageDiscountService in init "+repo.getClass());
    }
}
