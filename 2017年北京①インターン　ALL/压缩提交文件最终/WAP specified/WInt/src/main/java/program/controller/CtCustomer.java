package program.controller;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import program.controller.entry.EntryQueryPage;
import program.entity.Customer;
import program.entity.enums.EnumRole;
import program.repository.ICustomerRepo;
import program.service.CustomerService;
import program.util.ResponseInfo;
import javax.annotation.Resource;

@RestController
@RequestMapping("/Customer")
public class CtCustomer {
    @Resource
    ICustomerRepo customerRepo;
    @Resource
    CustomerService customerService;



    @RequestMapping("/save")
    public ResponseInfo save(@RequestBody Customer customer) {
        customerRepo.save(customer);
        return new ResponseInfo();
    }

    @RequestMapping("/queryPage" )
    public ResponseInfo queryPage(@RequestBody EntryQueryPage<Customer> query){
        Page<Customer> customers = customerService.queryPage(
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
