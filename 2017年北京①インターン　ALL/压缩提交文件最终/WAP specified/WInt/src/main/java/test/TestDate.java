package test;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import program.entity.test.TestDateEntity;
import program.repository.ITestDateEntityRepo;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:hibernate.xml","classpath:spring.xml"})
public class TestDate {
    @Resource
    ITestDateEntityRepo testDateEntityRepo;

    @Test
    public void test(){
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat();
        Calendar calendar=Calendar.getInstance();
        Date date=new Date();
        calendar.setTime(date);
        calendar.add(Calendar.DATE,1);
        System.out.println(calendar.getTime());
    }
    @Test
    public void testDateEntity(){
        TestDateEntity testDateEntity=new TestDateEntity();
//        testDateEntity.setId("1");
//        testDateEntity.setDateTime(new Date());
//        testDateEntity.setDateNoTime(new Date());
//        testDateEntityRepo.save(testDateEntity);
        System.out.println(testDateEntityRepo.findAll());

//        System.out.println(testDateEntityRepo.getOne("1"));
//        System.out.println(testDateEntityRepo.getOne("1"));
    }
    @Test
    public void testCalendar(){
        Calendar calendar=Calendar.getInstance();
        System.out.println(calendar.getTime());
    }


}
