package program.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import program.entity.FoodOrder;
import program.repository.IFoodOrderRepo;

@Service
public class FoodOrderService extends GenericService<IFoodOrderRepo,FoodOrder> {
    @Autowired
    public FoodOrderService(IFoodOrderRepo repo){
        this.setRepo(repo);
        System.out.println("set FoodOrderService in init "+repo.getClass());
    }
}
