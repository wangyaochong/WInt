package program.controller;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import program.controller.entry.EntryQueryPage;
import program.entity.Product;
import program.repository.IProductRepo;
import program.service.ProductService;
import program.util.ResponseInfo;

import javax.annotation.Resource;

@RestController
@RequestMapping("/Product")
public class CtProduct {
    public static boolean simulationStarted=false;
    public static boolean idealProductManage =false;

    @Resource
    IProductRepo productRepo;
    @Resource
    ProductService productService;

    @RequestMapping("/startSimulation")
    public ResponseInfo startSimulation(){
        simulationStarted=true;
        return new ResponseInfo();
    }
    @RequestMapping("/idealProductManage")
    public ResponseInfo idealProduct(){
        idealProductManage =true;
        return new ResponseInfo();
    }

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
