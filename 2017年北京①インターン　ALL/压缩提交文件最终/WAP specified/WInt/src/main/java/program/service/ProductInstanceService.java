package program.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import program.entity.ProductInstance;
import program.repository.IProductInstanceRepo;

@Service
public class ProductInstanceService extends GenericService<IProductInstanceRepo,ProductInstance> {
    @Autowired
    public ProductInstanceService(IProductInstanceRepo repo){
        this.setRepo(repo);
        System.out.println("set ProductInstanceService in init "+repo.getClass());
    }
}
