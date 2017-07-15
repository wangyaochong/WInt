package program.service;

import com.mysql.jdbc.StringUtils;
import lombok.Data;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import program.entity.Customer;
import program.repository.ICustomerRepo;
import program.util.ObjectUtil;

import javax.annotation.Resource;

@Service
public class CustomerService {
    @Resource
    ICustomerRepo customerRepo;
    public Page<Customer> queryPage(Customer customer,Integer pageNum,Integer pageSize,String orderBy,Boolean OrderAsc){
        Sort.Direction direction= Sort.Direction.DESC;//默认是降序
        if(Boolean.TRUE.equals(OrderAsc)){//如果是升序
            direction=Sort.Direction.ASC;
        }
        if (ObjectUtil.checkAllFieldNullOrEmpty(customer)) {
            if(StringUtils.isNullOrEmpty(orderBy)){
                return customerRepo.findAll(new PageRequest(pageNum,pageSize));
            }else{
                return customerRepo.findAll(new PageRequest(pageNum,pageSize,direction,orderBy));
            }
        }
        if(StringUtils.isNullOrEmpty(orderBy)){//如果不排序
            return  customerRepo.findAll(Example.of(customer, ExampleMatcher.matchingAll()), new PageRequest(pageNum, pageSize));
        }else{//需要排序
            return customerRepo.findAll(Example.of(customer, ExampleMatcher.matchingAll()),new PageRequest(pageNum,pageSize, direction,orderBy));
        }
    }
}
