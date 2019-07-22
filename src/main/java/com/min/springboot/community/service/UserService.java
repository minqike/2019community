package com.min.springboot.community.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.min.springboot.community.model.User;

public interface UserService extends IService<User> {

    User findByToken(String token);

}
