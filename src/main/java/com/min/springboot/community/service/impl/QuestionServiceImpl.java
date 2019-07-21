package com.min.springboot.community.service.impl;

import com.min.springboot.community.mapper.QuestionMapper;
import com.min.springboot.community.model.Question;
import com.min.springboot.community.model.User;
import com.min.springboot.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class QuestionServiceImpl implements QuestionService {
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
        return questionMapper.getQuestionCount();
    }
}
