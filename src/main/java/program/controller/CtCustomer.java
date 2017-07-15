package program.controller;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.Validate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import program.controller.entry.EntryQueryPage;
import program.entity.Customer;
import program.repository.ICustomerRepo;
import program.service.CustomerService;
import program.util.ObjectUtil;
import program.util.ResponseInfo;
import javax.annotation.Resource;
import java.util.stream.Stream;

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
    public ResponseInfo queryPage(@RequestBody EntryQueryPage<Customer> customer){
        Page<Customer> customers = customerService.queryPage(
                customer.getCondition(),
                customer.getPageNum(),
                customer.getPageSize(),
                customer.getOrderBy(),
                customer.getOrderAsc()
        );
        ResponseInfo responseInfo = new ResponseInfo();
        responseInfo.setData(customers);
        return responseInfo;
    }

}
