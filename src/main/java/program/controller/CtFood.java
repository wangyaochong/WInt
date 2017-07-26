package program.controller;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import program.controller.entry.EntryQueryPage;
import program.entity.Category;
import program.entity.Food;
import program.repository.IFoodRepo;
import program.service.FoodService;
import program.util.ResponseInfo;

import javax.annotation.Resource;
import java.util.Collections;

@RestController
@RequestMapping("Food")
public class CtFood {
    @Resource
    IFoodRepo foodRepo;
    @Resource
    FoodService foodService;
    @RequestMapping("/save")
    public ResponseInfo save(@RequestBody Food obj){
        foodRepo.save(obj);
        return new ResponseInfo();
    }
    @RequestMapping("/queryPage" )
    public ResponseInfo queryPage(@RequestBody EntryQueryPage<Food> query){
        Page<Food> customers = foodService.queryPage(
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
