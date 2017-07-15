package program.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import program.entity.Product;
import program.repository.IProductRepo;
import program.util.ResponseInfo;

import javax.annotation.Resource;

@Controller
@RequestMapping("/Product")
public class CtProduct {

    @Resource
    IProductRepo productRepo;

    @RequestMapping("/save")
    public ResponseInfo save(@RequestBody Product obj){
        productRepo.save(obj);
        return new ResponseInfo();
    }
}
