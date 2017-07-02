package program.controller;

import org.junit.Test;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import program.entity.TestEntity;
import program.entity.entityInterface.IType;
import program.service.CrudService;
import program.service.PageListService;
import program.service.bean.PageBean;

import javax.annotation.Resource;
import java.io.File;
import java.util.*;

/**
 * Created by【王耀冲】on 【2017/6/11】 at 【20:58】.
 */
@Controller
@RequestMapping("test")
public class TestController {
    @Resource
    CrudService crudService;
    @Resource
    PageListService<TestEntity> pageListService;

    public TestController(){
        System.out.println("init");
        File file=new File("./");
        System.out.println(file.getAbsolutePath());
    }
    @RequestMapping("test")
    @ResponseBody
    public Map<String,String> test(){
        Map<String,String>result=new HashMap<>();
        result.put("test","testaabb");
        return result;
    }
    @RequestMapping("testRequestBody")
    @ResponseBody
    public Map<String,String> testRequestBody(@RequestBody TestEntity testEntity){
        Map<String,String> result=new HashMap<>();
        result.put("name",testEntity.getName());
        result.put("age",testEntity.getAge().toString());
        return result;
    }
    @RequestMapping("testModelAttribute")
    @ResponseBody
    public Map<String,String> testModelAttribute(TestEntity testEntity){
        Map<String,String> result=new HashMap<>();
        result.put("name1",testEntity.getName());
        result.put("age",testEntity.getAge().toString());
        return result;
    }

    @RequestMapping("getTestEntities")
    @ResponseBody
    public List<TestEntity> getEntities(){
        return crudService.getAll(TestEntity.class);
    }


    @RequestMapping("addTestEntity")
    @ResponseBody
    public Map<String,String> addTestEntity(){
        Map<String,String > result=new HashMap<>();
        result.put("result","ok");
        for(int d=0;d<5;d++){
            for(int i=0;i<20;i++){
                Date tmpDate=new Date();
                Calendar calendar=new GregorianCalendar();
                calendar.setTime(tmpDate);
                calendar.add(calendar.DATE,-1);
                tmpDate=calendar.getTime();
                TestEntity testEntity=new TestEntity("test",18,tmpDate.getTime(),tmpDate.getTime(),"test","testDescription", IType.TEST);
                crudService.saveOrUpdateOne(testEntity);
            }
        }
        return result;
    }

    @RequestMapping("getTestEntityPage")
    @ResponseBody
    public PageBean<TestEntity> getTestEntityPage(){
        return pageListService.getPageBean(new TestEntity(),1,5,null,null);
    }

}
