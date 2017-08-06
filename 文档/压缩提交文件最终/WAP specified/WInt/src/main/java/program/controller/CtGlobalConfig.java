package program.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import program.entity.GlobalConfig;
import program.repository.IGlobalConfigRepo;
import program.util.ResponseInfo;

import javax.annotation.Resource;

@RestController
@RequestMapping("/GlobalConfig")
public class CtGlobalConfig {

    @Resource
    IGlobalConfigRepo globalConfigRepo;

    @RequestMapping("/save")
    public ResponseInfo save(@RequestBody GlobalConfig obj){
        globalConfigRepo.save(obj);
        return new ResponseInfo();
    }

}
