package program.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import program.entity.FoodInstance;
@RepositoryRestResource
public interface IFoodInstanceRepo extends JpaRepository<FoodInstance,String>,JpaSpecificationExecutor<FoodInstance>{

}
