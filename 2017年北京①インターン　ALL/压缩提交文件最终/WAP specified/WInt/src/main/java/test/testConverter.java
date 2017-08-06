package test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import program.bean.converter.DateAndOrderCountConverter;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:hibernate.xml","classpath:spring.xml"})
public class testConverter {
    @Resource
    DateAndOrderCountConverter countConverter;
    @Test
    public void test(){
        System.out.println(countConverter.convert("a"));
    }
}
