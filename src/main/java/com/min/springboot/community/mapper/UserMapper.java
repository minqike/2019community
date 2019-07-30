package com.min.springboot.community.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.min.springboot.community.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper extends BaseMapper<User> {

    @Select("select * from User where token=#{token}")
    User findByToken(String token);

}
