package program.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import program.entity.ProductInstance;

@RepositoryRestResource
public interface IProductInstanceRepo  extends JpaRepository<ProductInstance,String>,JpaSpecificationExecutor<ProductInstance>{

}
