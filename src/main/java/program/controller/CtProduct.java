package program.controller;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import program.controller.entry.EntryQueryPage;
import program.entity.Customer;
import program.entity.Product;
import program.repository.IProductRepo;
import program.service.ProductService;
import program.util.ResponseInfo;

import javax.annotation.Resource;

@RestController
@RequestMapping("/Product")
public class CtProduct {

    @Resource
    IProductRepo productRepo;
    @Resource
    ProductService productService;

    @RequestMapping("/save")
    public ResponseInfo save(@RequestBody Product obj){
        productRepo.save(obj);
        return new ResponseInfo();
    }
    @RequestMapping("/queryPage" )
    public ResponseInfo queryPage(@RequestBody EntryQueryPage<Product> query){
        Page<Product> customers = productService.queryPage(
                query.getCondition(),
                query.getPageNum(),
                query.getPageSize(),
                query.getOrderBy(),
                query.getOrderAsc()
        );
        ResponseInfo responseInfo = new ResponseInfo();
        responseInfo.setData(customers);
        return responseInfo;
    }


}
