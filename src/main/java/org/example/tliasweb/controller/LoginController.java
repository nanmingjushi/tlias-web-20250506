package org.example.tliasweb.controller;

/* 
    @author nanchao 
    @date 2025/4/23
*/

import lombok.extern.slf4j.Slf4j;
import org.example.tliasweb.pojo.Emp;
import org.example.tliasweb.pojo.LoginInfo;
import org.example.tliasweb.pojo.Result;
import org.example.tliasweb.service.EmpService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class LoginController {

    private static Logger log = LoggerFactory.getLogger(LoginController.class);


    @Autowired
    private EmpService empService;

    @PostMapping("/login")
    public Result login(@RequestBody Emp emp){
        log.info("员工登录，emp={}",emp);
        LoginInfo loginInfo = empService.login(emp);
        if(loginInfo!=null){
            return Result.success(loginInfo);
        }
        return Result.error("用户名或密码错误");

    }


}
