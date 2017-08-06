package program.controller;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import program.controller.entry.EntryQueryPage;
import program.entity.Food;
import program.entity.FoodPackageDiscount;
import program.repository.IFoodPackageDiscountRepo;
import program.service.FoodPackageDiscountService;
import program.util.ResponseInfo;

import javax.annotation.Resource;

@RestController
@RequestMapping("FoodPackageDiscount")
public class CtFoodPackageDiscount {
    @Resource
    IFoodPackageDiscountRepo foodPackageDiscountRepo;
    @Resource
    FoodPackageDiscountService foodPackageDiscountService;
    @RequestMapping("/save")
    public ResponseInfo save(@RequestBody FoodPackageDiscount obj){
        foodPackageDiscountRepo.save(obj);
        return new ResponseInfo();
    }
    @RequestMapping("/queryPage" )
    public ResponseInfo queryPage(@RequestBody EntryQueryPage<FoodPackageDiscount> query){
        Page<FoodPackageDiscount> customers = foodPackageDiscountService.queryPage(
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
