package com.hws.controller;

import com.hws.pojo.Emp;
import com.hws.pojo.Result;
import com.hws.service.EmpService;
import com.hws.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
public class LoginController {

    @Autowired
    private EmpService empService;

    @PostMapping("/login")
    public Result login(@RequestBody Emp emp){
        log.info("员工登陆：{}",emp);
        Emp e = empService.login(emp);

        //登陆成功，生成令牌，下发令牌
        if (e != null) {
            Map<String, Object> claims = new HashMap<>();
            claims.put("id", e.getId());
            claims.put("name", e.getName());
            claims.put("username", e.getUsername());

            String jwt = JwtUtils.generateJwt(claims); //jwt令牌包括当前登陆员工信息
            log.info("jwt令牌：{}",jwt);
            return Result.success(jwt);
        }

        //登陆失败，返回错误信息
        return Result.error("用户名或密码错误");
    }
}
