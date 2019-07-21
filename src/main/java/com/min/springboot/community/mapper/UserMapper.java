package com.min.springboot.community.mapper;

import com.min.springboot.community.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

    @Select("select * from User where id=#{id}")
    User findById(int id);

    @Select("select * from User where token=#{token}")
    User findByToken(String token);


    @Insert("insert into user(account_id,name,token,bio,gmt_create,gmt_modified)values(" +
            "#{accountId},#{name},#{token},#{bio},#{gmtCreate},#{gmtModified}" +
            ")")
    int insert(User user);
}
