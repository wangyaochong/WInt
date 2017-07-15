package program.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import program.entity.Category;
import program.repository.ICategoryRepo;
import program.util.ResponseInfo;

import javax.annotation.Resource;

@Controller
@RequestMapping("/Category")
public class CtCategory {
    @Resource
    ICategoryRepo categoryRepo;

    @RequestMapping("/save")
    public ResponseInfo save(@RequestBody Category obj){
        categoryRepo.save(obj);
        return new ResponseInfo();
    }
}
