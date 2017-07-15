package program.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import program.entity.BranchGroup;
import program.repository.IBranchGroupRepo;
import program.util.ResponseInfo;

import javax.annotation.Resource;

@RestController
@RequestMapping("/BranchGroup")
public class CtBranchGroup {
    @Resource
    IBranchGroupRepo branchGroupRepo;

    @RequestMapping("/save")
    public ResponseInfo save(BranchGroup branchGroup) {
        branchGroupRepo.save(branchGroup);
        return new ResponseInfo();
    }
}
