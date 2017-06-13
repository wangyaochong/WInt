package program.controller;

import org.junit.Test;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import program.entity.TestEntity;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by【王耀冲】on 【2017/6/11】 at 【20:58】.
 */
@Controller
@RequestMapping("test")
public class TestController {
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


}
