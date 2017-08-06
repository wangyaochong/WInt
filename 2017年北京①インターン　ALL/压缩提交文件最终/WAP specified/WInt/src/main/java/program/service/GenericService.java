package program.service;

import com.mysql.jdbc.StringUtils;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import program.util.ObjectUtil;

import javax.annotation.Resource;

@Data
public class GenericService<RepoType extends JpaSpecificationExecutor&JpaRepository, T> {
    RepoType repo;
    public Page<T> queryPage(T queryExample, Integer pageNum, Integer pageSize, String orderBy, Boolean OrderAsc){
        Sort.Direction direction= Sort.Direction.DESC;//default is order desc
        if(Boolean.TRUE.equals(OrderAsc)){//if order asc
            direction=Sort.Direction.ASC;
        }
        if (ObjectUtil.checkAllFieldNullOrEmptyAndSetEmptyNull(queryExample)) {
            if(StringUtils.isNullOrEmpty(orderBy)){
                return repo.findAll(new PageRequest(pageNum,pageSize));
            }else{
                return repo.findAll(new PageRequest(pageNum,pageSize,direction,orderBy));
            }
        }
        if(StringUtils.isNullOrEmpty(orderBy)){//if not order
            return  repo.findAll(
                    Example.of(queryExample, ExampleMatcher.matching()
                            .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)
                            .withIgnoreCase()),
                    new PageRequest(pageNum, pageSize));
        }else{//if need order
            return repo.findAll( Example.of(queryExample,ExampleMatcher.matching()
                            .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)
                            .withIgnoreCase()),
                    new PageRequest(pageNum,pageSize, direction,orderBy));
        }
    }
}
