package program.controller;
import org.springframework.data.domain.Page;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import program.controller.entry.EntryQueryPage;
import program.entity.Food;
import program.entity.FoodOrder;
import program.repository.IFoodInstanceRepo;
import program.repository.IFoodOrderRepo;
import program.repository.IFoodRepo;
import program.service.FoodOrderService;
import program.service.FoodService;
import program.util.ResponseInfo;

import javax.annotation.Resource;
import java.util.Date;

@RestController
@RequestMapping("/FoodOrder")
public class CtFoodOrder {
    @Resource
    IFoodInstanceRepo foodInstanceRepo;
    @Resource
    IFoodOrderRepo foodOrderRepo;
    @Resource
    FoodOrderService foodOrderService;
    @RequestMapping("/save")
    public ResponseInfo save(@RequestBody FoodOrder obj){
        if(ObjectUtils.isEmpty(obj.getId())){//如果是空的，则说明是新增订单而不是修改订单状态
            //新增的订单需要保存食物列表
            obj.getFoodList().stream().forEach(foodInstance -> {
                foodInstance.setId(null);//如果带有id，会被当作更新，需要除去，每次的订单都是不同的实例
                foodInstance.setCreateTime(new Date());
                foodInstanceRepo.save(foodInstance);
            });
        }
//        obj.setOrderBeginDateTime(new Date());//对于
        FoodOrder save = foodOrderRepo.save(obj);
        System.out.println("save new order"+save);
        return new ResponseInfo();
    }
    @RequestMapping("/queryPage" )
    public ResponseInfo queryPage(@RequestBody EntryQueryPage<FoodOrder> query){
        Page<FoodOrder> customers = foodOrderService.queryPage(
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
