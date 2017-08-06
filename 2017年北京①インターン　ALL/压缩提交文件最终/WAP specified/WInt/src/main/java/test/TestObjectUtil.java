package test;

import org.junit.Test;
import program.entity.test.TestEntity;
import program.util.ObjectUtil;

public class TestObjectUtil {
    @Test
    public void checkAllFieldNull(){
        TestEntity testEntity = new TestEntity();
//        testEntity.setAge(1);
        System.out.println(ObjectUtil.checkAllFieldNull(testEntity));
    }
}
