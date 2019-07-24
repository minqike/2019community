package com.min.springboot.community.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.min.springboot.community.dto.QuestionDTO;
import com.min.springboot.community.mapper.QuestionMapper;
import com.min.springboot.community.mapper.UserMapper;
import com.min.springboot.community.model.Question;
import com.min.springboot.community.model.User;
import com.min.springboot.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class QuestionServiceImpl  extends ServiceImpl<QuestionMapper, Question>  implements QuestionService {
    @Autowired
    private  QuestionMapper questionMapper;

    @Override
    public int insert(Question question , HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        Question newquestion = new Question();
        newquestion.setCreator(user.getId());
        newquestion.setTitle(question.getTitle());
        newquestion.setDescription(question.getDescription());
        newquestion.setTag(question.getTag());
        newquestion.setCommentCount(0);
        newquestion.setViewCount(0);
        newquestion.setLikeCount(0);
        newquestion.setGmtCreate(System.currentTimeMillis());
        newquestion.setGmtModified(newquestion.getGmtCreate());

        return questionMapper.insert(newquestion);
    }

    @Override
    public int getQuestionCount() {
        return questionMapper.selectCount(null);
    }

    public PageInfo<QuestionDTO> listQuestionDTO(int page, int size){
        PageHelper.startPage(page,size);
        List<QuestionDTO> dtoList = questionMapper.listQuestionDTO();
        PageInfo<QuestionDTO> info = new PageInfo<>(dtoList);
        return info;

    }

    @Override
    public PageInfo<QuestionDTO> listMyQuestionDTO(int page, int size, Long uid) {
        PageHelper.startPage(page,size);
        List<QuestionDTO> dtoList = questionMapper.listMyQuestionDTO(uid);
        PageInfo<QuestionDTO> info = new PageInfo<>(dtoList);
        return info;
    }


}
