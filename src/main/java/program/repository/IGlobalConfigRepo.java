package program.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import program.entity.GlobalConfig;

@RepositoryRestResource
public interface IGlobalConfigRepo extends JpaRepository<GlobalConfig,String>,JpaSpecificationExecutor<GlobalConfig> {

}
