package com.min.springboot.community.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.min.springboot.community.dto.QuestionDTO;
import com.min.springboot.community.model.Question;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface QuestionMapper extends BaseMapper<Question> {


    @Select("SELECT question.*,IFNULL(user.avatar_url,'') AS avatar_url FROM question LEFT JOIN user ON user.ID=question.creator ORDER BY question.gmt_modified DESC")
    List<QuestionDTO> listQuestionDTO();

    @Select("SELECT question.*,IFNULL(user.avatar_url,'') AS avatar_url FROM question LEFT JOIN user ON user.ID=question.creator where question.creator=#{uid} ORDER BY question.gmt_modified DESC")
    List<QuestionDTO> listMyQuestionDTO(Long uid);

    @Update("update question set view_count=view_count+1 where id=#{id} ")
    void incView(Long id);

    @Update("update question set comment_count=comment_count+1 where id=#{id} ")
    void incComment(Long id);
}
