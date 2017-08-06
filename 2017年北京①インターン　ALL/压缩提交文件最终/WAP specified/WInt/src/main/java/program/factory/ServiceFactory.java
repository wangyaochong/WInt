package program.factory;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import program.entity.Food;
import program.repository.IFoodRepo;
import program.service.GenericService;

import javax.annotation.Resource;

@Service
public class ServiceFactory {
    @Resource
    IFoodRepo foodRepo;

//    @Bean
//    public GenericService<IFoodRepo,Food> foodService(){
//        GenericService<IFoodRepo,Food> genericService=new GenericService<>();
//        genericService.setRepo(foodRepo);
//        return genericService;
//    }

}
