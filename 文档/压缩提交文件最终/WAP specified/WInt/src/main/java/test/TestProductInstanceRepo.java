package test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import program.repository.IProductInstanceRepo;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:hibernate.xml","classpath:spring.xml"})
public class TestProductInstanceRepo {
    @Resource
    IProductInstanceRepo productInstanceRepo;
    @Test
    public void test(){
        System.out.println(productInstanceRepo.getAverageConsumeForOneDayInBranchByName(1l,2, "Vinegar"));
    }
}
