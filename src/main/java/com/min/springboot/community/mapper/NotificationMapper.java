package com.min.springboot.community.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.min.springboot.community.model.Notification;
import com.min.springboot.community.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface NotificationMapper extends BaseMapper<Notification> {

}
