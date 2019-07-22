package com.min.springboot.community.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.min.springboot.community.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface QuestionMapper extends BaseMapper<Question> {
//
//    @Insert("insert into question(title,description,creator,comment_Count,view_Count,like_Count,tag,gmt_Create,gmt_Modified)" +
//            "values(#{title},#{description},#{creator},#{commentCount},#{viewCount},#{likeCount},#{tag},#{gmtCreate},#{gmtModified})")
//    int insert(Question question);
//
//    @Select("select count(1) as cnt from question")
//    int getQuestionCount();
}
