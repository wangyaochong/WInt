package program.controller;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import program.controller.entry.EntryQueryPage;
import program.entity.BranchGroup;
import program.repository.IBranchGroupRepo;
import program.service.BranchGroupService;
import program.util.ResponseInfo;

import javax.annotation.Resource;

@RestController
@RequestMapping("/BranchGroup")
public class CtBranchGroup {
    @Resource
    IBranchGroupRepo branchGroupRepo;
    @Resource
    BranchGroupService branchGroupService;

    @RequestMapping("/save")
    public ResponseInfo save(@RequestBody BranchGroup branchGroup) {
        branchGroupRepo.save(branchGroup);
        return new ResponseInfo();
    }
    @RequestMapping("/queryPage" )
    public ResponseInfo queryPage(@RequestBody EntryQueryPage<BranchGroup> query){
        Page<BranchGroup> customers = branchGroupService.queryPage(
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
