package program.controller;

import org.springframework.data.domain.Example;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import program.entity.Customer;
import program.entity.Employee;
import program.repository.ICustomerRepo;
import program.repository.IEmployeeRepo;
import program.util.ResponseInfo;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/LogInLogOut")
public class CtLogInLogOut {
    @Resource
    ICustomerRepo customerRepo;
    @Resource
    IEmployeeRepo employeeRepo;

    @RequestMapping("/currentUser")
    public ResponseInfo currentUser(HttpServletRequest request){
        ResponseInfo responseInfo = new ResponseInfo();
        responseInfo.setData( request.getSession().getAttribute("currentUser"));
        return responseInfo;
    }

    @RequestMapping("/logIn")
    public ResponseInfo logIn(HttpServletRequest request, String account, String password) {
        if (customerRepo.exists(account)){
            Customer customer = customerRepo.getOne(account);
            request.getSession().setAttribute("currentUser", customer);
            return new ResponseInfo(ResponseInfo.statusOk, null, customer);
        }
        Employee example = new Employee();
        example.setPassword(password);
        List<Employee> all = employeeRepo.findAll(Example.of(example));
        if (!ObjectUtils.isEmpty(all)) {
            request.getSession().setAttribute("currentUser", all.get(0));
            return new ResponseInfo(ResponseInfo.statusOk, null, all.get(0));
        }
        return new ResponseInfo(ResponseInfo.statusOk, null, null);

    }
}
