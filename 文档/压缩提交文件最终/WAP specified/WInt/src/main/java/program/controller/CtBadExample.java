package program.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import program.util.ResponseInfo;

@RestController
@RequestMapping("/BadExample")
public class CtBadExample {
    @RequestMapping("/queryFoodOrderPage")
    public ResponseInfo queryUserPage(@RequestParam("filter") String filter,
                                      @RequestParam("orderBy") String orderBy,
                                      @RequestParam("orderAsc") Boolean orderAsc ,
                                      @RequestParam("pageNum") Long pageNum,
                                      @RequestParam("pageSize") Long pageSize){


        return new ResponseInfo();
    }






}
