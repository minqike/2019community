package com.min.springboot.community.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.min.springboot.community.mapper.UserMapper;
import com.min.springboot.community.model.User;
import com.min.springboot.community.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper mapper;

    @Override
    public User findByToken(String token) {
        return mapper.findByToken(token);
    }
}
