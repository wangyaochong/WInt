package program.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import program.entity.FoodOrder;

@RepositoryRestResource
public interface ITradeRecordRepo extends JpaRepository<FoodOrder,String>,JpaSpecificationExecutor<FoodOrder>{
}
