package com.promise.controller;

import com.promise.controller.result.UserResult;
import com.promise.pojo.User;
import com.promise.service.UserService;
import com.promise.util.TokenUtils;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.iv.RandomIvGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    @ResponseBody
    public UserResult login(@RequestBody User user) {
        String password = userService.selectByUsername(user.getUsername());
        if (password == null) return new UserResult(Code.LOGIN_ERR, "用户不存在，联系博主开启注册权限");
        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        encryptor.setPassword("jasypt");
        encryptor.setAlgorithm("PBEWithHMACSHA512AndAES_256");
        encryptor.setIvGenerator(new RandomIvGenerator());

        String plainText = encryptor.decrypt(password);

        String token = plainText.equals(user.getPassword()) ? TokenUtils.token(user.getUsername(),
                user.getPassword()) : null;
        Integer code = plainText.equals(user.getPassword()) ? Code.LOGIN_SUC : Code.LOGIN_ERR;
        String msg = plainText.equals(user.getPassword()) ? "登录成功" : "用户不存在，联系博主开启注册权限";
        return new UserResult(code, msg, token);
    }

    @PostMapping("/register")
    @ResponseBody
    public UserResult logon(@RequestBody User user) {
        String password = userService.selectByUsername(user.getUsername());
        if (password != null) return new UserResult(Code.LOGON_ERR, "用户名已存在");

        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        encryptor.setPassword("jasypt");
        encryptor.setAlgorithm("PBEWithHMACSHA512AndAES_256");
        encryptor.setIvGenerator(new RandomIvGenerator());

        user.setPassword(encryptor.encrypt(user.getPassword()));
        boolean flag = userService.add(user);

        Integer code = flag ? Code.LOGON_SUC : Code.LOGON_ERR;
        String msg = flag ? "注册成功" : "注册失败";

        return new UserResult(code, msg);
    }

    // 测试拦截器是否生效
    /*@GetMapping
    public UserResult test() {
        return new UserResult(1, "");
    }*/
}
