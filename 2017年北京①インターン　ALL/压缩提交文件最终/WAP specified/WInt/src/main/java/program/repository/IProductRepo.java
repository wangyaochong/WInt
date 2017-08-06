package program.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import program.entity.BranchGroup;
import program.entity.Product;

import java.util.List;

@RepositoryRestResource
public interface IProductRepo extends JpaRepository<Product,String> ,JpaSpecificationExecutor<Product>{
    List<Product> findAllByBranchGroup(BranchGroup branchGroup);
}
