package program.repository.test;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import program.entity.test.TestEntity;
import program.repository.interfaces.ISelfTestEntityRepo;

import java.util.List;

@RepositoryRestResource(path="testEntity")
public interface ITestEntityRepo extends JpaRepository<TestEntity,String>,JpaSpecificationExecutor<TestEntity>,ISelfTestEntityRepo<TestEntity,String>{
    @RestResource(path = "findByName")
    List<TestEntity> findByName(@Param("name") String name);

    @RestResource(path = "findByDescription")
    TestEntity findByDescription(@Param("description") String description);
}
