package program.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import program.entity.Employee;
import program.util.ResponseInfo;

@Controller
@RequestMapping("Employee")
public class CtEmployee {
    @RequestMapping("/save")
    public ResponseInfo save(@RequestBody Employee employee){

        return new ResponseInfo();
    }
}
