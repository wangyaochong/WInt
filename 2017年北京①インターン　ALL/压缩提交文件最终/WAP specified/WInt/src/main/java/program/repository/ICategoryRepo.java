package program.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import program.entity.BranchGroup;
import program.entity.Category;

import java.util.List;

@RepositoryRestResource
public interface ICategoryRepo extends JpaRepository<Category,String>,JpaSpecificationExecutor<Category>{
    List<Category> findAllByBranchGroup(BranchGroup branchGroup);


}
