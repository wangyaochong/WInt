package program.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import program.entity.SalarySendConfig;
import program.repository.ISalarySendConfigRepo;
import program.repository.ISalarySendRecordRepo;
import program.util.ResponseInfo;

import javax.annotation.Resource;

@Controller
@RequestMapping("/SalarySendConfig")
public class CtSalarySendConfig {

    @Resource
    ISalarySendConfigRepo salarySendConfigRepo;

    @RequestMapping("/save")
    public ResponseInfo save(@RequestBody SalarySendConfig obj){
        salarySendConfigRepo.save(obj);
        return new ResponseInfo();
    }

}
