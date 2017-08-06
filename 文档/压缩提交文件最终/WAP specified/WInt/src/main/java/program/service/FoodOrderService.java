package program.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import program.entity.FoodOrder;
import program.repository.IBranchGroupRepo;
import program.repository.IFoodOrderRepo;

import javax.annotation.Resource;
import javax.persistence.EntityManager;

@Service
public class FoodOrderService extends GenericService<IFoodOrderRepo,FoodOrder> {

    @Resource
    IBranchGroupRepo branchGroupRepo;
    @Resource
    EntityManager entityManager;
    @Autowired
    public FoodOrderService(IFoodOrderRepo repo){
        this.setRepo(repo);
        System.out.println("set FoodOrderService in init "+repo.getClass());
    }
}
