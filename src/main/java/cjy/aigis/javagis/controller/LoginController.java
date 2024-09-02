package cjy.aigis.javagis.controller;

import cjy.aigis.javagis.pojo.User;
import cjy.aigis.javagis.util.JwtUtils;
import cjy.aigis.javagis.util.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;


@Slf4j
@RestController
public class LoginController {
    @PostMapping("/login")
    public Result<?> login(@RequestBody User user){
        log.info("login");
        if("admin".equals(user.getUsername()) && "123456".equals(user.getPassword())){
            Map<String, String> claims = new HashMap<>();
            claims.put("username", user.getUsername());
            String token = JwtUtils.generateJwt(claims);
            return Result.success(token);
        }

        return Result.userNotFound();
    }
}
