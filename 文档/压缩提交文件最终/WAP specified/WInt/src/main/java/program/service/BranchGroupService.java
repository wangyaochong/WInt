package program.service;

import com.mysql.jdbc.StringUtils;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import program.entity.BranchGroup;
import program.repository.IBranchGroupRepo;
import program.util.ObjectUtil;

import javax.annotation.Resource;

@Service
public class BranchGroupService {
    @Resource
    IBranchGroupRepo branchGroupRepo;
    public Page<BranchGroup> queryPage(BranchGroup queryExample, Integer pageNum, Integer pageSize, String orderBy, Boolean OrderAsc){
        Sort.Direction direction= Sort.Direction.DESC;//默认是降序
        if(Boolean.TRUE.equals(OrderAsc)){//如果是升序
            direction=Sort.Direction.ASC;
        }
        if (ObjectUtil.checkAllFieldNullOrEmptyAndSetEmptyNull(queryExample)) {
            if(StringUtils.isNullOrEmpty(orderBy)){
                return branchGroupRepo.findAll(new PageRequest(pageNum,pageSize));
            }else{
                return branchGroupRepo.findAll(new PageRequest(pageNum,pageSize,direction,orderBy));
            }
        }

        if(StringUtils.isNullOrEmpty(orderBy)){//如果不排序
            return  branchGroupRepo.findAll(
                    Example.of(queryExample, ExampleMatcher.matching()
                            .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)
                            .withIgnoreCase()),
                    new PageRequest(pageNum, pageSize));
        }else{//需要排序
            return branchGroupRepo.findAll( Example.of(queryExample,ExampleMatcher.matching()
                            .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)
                            .withIgnoreCase()),
                    new PageRequest(pageNum,pageSize, direction,orderBy));
        }
    }
}
