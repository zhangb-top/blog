package com.promise.service.impl;

import com.promise.dao.UserDao;
import com.promise.pojo.User;
import com.promise.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public boolean add(User user) {
        userDao.add(user);
        return true;
    }

    @Override
    public String selectByUsername(String username) {
        return userDao.selectByUsername(username);
    }
}
