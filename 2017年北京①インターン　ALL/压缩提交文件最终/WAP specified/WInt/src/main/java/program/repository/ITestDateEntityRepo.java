package program.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import program.entity.test.TestDateEntity;

@RepositoryRestResource
public interface ITestDateEntityRepo extends JpaRepository<TestDateEntity,String>,JpaSpecificationExecutor<TestDateEntity>{

}
