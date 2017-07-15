package program.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import program.entity.Category;

@RepositoryRestResource
public interface ICategoryRepo extends JpaRepository<Category,String>,JpaSpecificationExecutor<Category>{

}
