package com.min.springboot.community.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.min.springboot.community.mapper.UserMapper;
import com.min.springboot.community.model.User;
import com.min.springboot.community.service.UserService;

public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    private UserMapper mapper;
    @Override
    public User findByToken(String token) {
        return mapper.findByToken(token);
    }
}
