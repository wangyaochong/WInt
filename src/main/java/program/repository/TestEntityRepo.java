package program.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import program.entity.TestEntity;

import java.util.List;

@Repository
public interface TestEntityRepo extends JpaRepository<TestEntity,String>,JpaSpecificationExecutor<TestEntity>{
    List<TestEntity> findByName(String name);
//    List<TestEntity>

}
