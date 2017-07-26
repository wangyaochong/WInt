package program.service;

import com.mysql.jdbc.StringUtils;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import program.entity.Employee;
import program.repository.IEmployeeRepo;
import program.util.ObjectUtil;

import javax.annotation.Resource;

@Service
public class EmployeeService {
    @Resource
    IEmployeeRepo employeeRepo;
    public Page<Employee> queryPage(Employee queryExample, Integer pageNum, Integer pageSize, String orderBy, Boolean OrderAsc){
        Sort.Direction direction= Sort.Direction.DESC;//默认是降序
        if(Boolean.TRUE.equals(OrderAsc)){//如果是升序
            direction=Sort.Direction.ASC;
        }
        if (ObjectUtil.checkAllFieldNullOrEmptyAndSetEmptyNull(queryExample)) {
            if(StringUtils.isNullOrEmpty(orderBy)){
                return employeeRepo.findAll(new PageRequest(pageNum,pageSize));
            }else{
                return employeeRepo.findAll(new PageRequest(pageNum,pageSize,direction,orderBy));
            }
        }

        if(StringUtils.isNullOrEmpty(orderBy)){//如果不排序
            return  employeeRepo.findAll(
                    Example.of(queryExample, ExampleMatcher.matching()
                            .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)
                            .withIgnoreCase()),
                    new PageRequest(pageNum, pageSize));
        }else{//需要排序
            return employeeRepo.findAll( Example.of(queryExample,ExampleMatcher.matching()
                            .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)
                            .withIgnoreCase()),
                    new PageRequest(pageNum,pageSize, direction,orderBy));
        }
    }
}