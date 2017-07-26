package program.controller;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import program.controller.entry.EntryQueryPage;
import program.entity.BranchGroup;
import program.entity.Category;
import program.repository.ICategoryRepo;
import program.service.CategoryService;
import program.service.GenericService;
import program.util.ResponseInfo;

import javax.annotation.Resource;

@RestController
@RequestMapping("/Category")
public class CtCategory {
    @Resource
    ICategoryRepo categoryRepo;
    @Resource
    CategoryService categoryService;


    @RequestMapping("/save")
    public ResponseInfo save(@RequestBody Category obj){
        categoryRepo.save(obj);
        return new ResponseInfo();
    }
    @RequestMapping("/queryPage" )
    public ResponseInfo queryPage(@RequestBody EntryQueryPage<Category> query){
        GenericService<ICategoryRepo,Category> genericService=new GenericService<>();
        genericService.setRepo(categoryRepo);
        Page<Category> customers = genericService.queryPage(
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
