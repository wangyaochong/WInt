package program.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import program.entity.BranchGroup;
import program.entity.Food;

import java.util.List;

@RepositoryRestResource
public interface IFoodRepo extends JpaRepository<Food,String>,JpaSpecificationExecutor<Food>{
    List<Food> queryFoodsByBranchGroup(BranchGroup branchGroup);
}
