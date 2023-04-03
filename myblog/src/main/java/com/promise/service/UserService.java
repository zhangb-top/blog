package com.promise.service;

import com.promise.pojo.User;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface UserService {

    /**
     * 添加博主账户
     */
    boolean add(User user);

    /**
     * 登录博主账户
     */
    String selectByUsername(String username);
}
