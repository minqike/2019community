package com.min.springboot.community.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.min.springboot.community.model.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CommentMapper extends BaseMapper<Comment> {
    @Select("select * from comment where parent_id = #{id} order by gmt_create desc")
    List<Comment> getCommnets(Long id);
}
