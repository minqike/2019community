package com.min.springboot.community.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.min.springboot.community.mapper.NotificationMapper;
import com.min.springboot.community.model.Notification;
import com.min.springboot.community.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationServiceImpl extends ServiceImpl<NotificationMapper, Notification> implements NotificationService {

    @Autowired
    private NotificationMapper mapper;


}
