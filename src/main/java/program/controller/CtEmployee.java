package program.controller;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import program.controller.entry.EntryQueryPage;
import program.entity.BranchGroup;
import program.entity.Employee;
import program.entity.enums.EnumRole;
import program.repository.IEmployeeRepo;
import program.service.EmployeeService;
import program.util.ResponseInfo;

import javax.annotation.Resource;

@RestController
@RequestMapping("/Employee")
public class CtEmployee {
    @Resource
    IEmployeeRepo employeeRepo;
    @Resource
    EmployeeService employeeService;

    @RequestMapping("/roles")
    public ResponseInfo roles(){
        ResponseInfo responseInfo=new ResponseInfo();
        responseInfo.setData(EnumRole.values());
        return responseInfo;
    }




    @RequestMapping("/save")
    public ResponseInfo save(@RequestBody Employee employee){
        if(ObjectUtils.isEmpty(employee.getId()) ){
            employee.setPassword("123456");
        }
        employeeRepo.save(employee);
        return new ResponseInfo();
    }
    @RequestMapping("/queryPage" )
    public ResponseInfo queryPage(@RequestBody EntryQueryPage<Employee> query){
        Page<Employee> customers = employeeService.queryPage(
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
