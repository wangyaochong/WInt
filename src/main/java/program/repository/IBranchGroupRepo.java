package program.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import program.entity.BranchGroup;

@RepositoryRestResource
public interface IBranchGroupRepo extends JpaRepository<BranchGroup,String> ,JpaSpecificationExecutor<BranchGroup> {

}
