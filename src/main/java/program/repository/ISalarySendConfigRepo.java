package program.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import program.entity.SalarySendConfig;

@RepositoryRestResource
public interface ISalarySendConfigRepo extends JpaRepository<SalarySendConfig,String>,JpaSpecificationExecutor<SalarySendConfig> {

}
