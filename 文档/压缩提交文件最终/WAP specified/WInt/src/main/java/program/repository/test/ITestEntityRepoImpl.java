package program.repository.test;

import org.springframework.data.repository.query.Param;
import program.entity.test.TestEntity;
import program.repository.interfaces.ISelfTestEntityRepo;

public class ITestEntityRepoImpl implements ISelfTestEntityRepo<TestEntity,String> {
    public TestEntity selfFindByName(@Param("name") String name) {
        TestEntity build = new TestEntity();
        build.setAge(4);
        build.setName("4");
        return build;
    }
}
