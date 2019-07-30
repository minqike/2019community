package com.min.springboot.community.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.min.springboot.community.dto.QuestionDTO;
import com.min.springboot.community.mapper.QuestionMapper;
import com.min.springboot.community.model.Question;
import com.min.springboot.community.model.User;
import com.min.springboot.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class QuestionServiceImpl  extends ServiceImpl<QuestionMapper, Question>  implements QuestionService {
    @Autowired
    private  QuestionMapper questionMapper;

    @Override
    @Transactional
    public int insert(Question question , HttpServletRequest request) throws Exception {
        User user = (User) request.getSession().getAttribute("user");
        Question newquestion = new Question();
        if (question.getId()!=null) {
            newquestion = questionMapper.selectById(question.getId());
            if (newquestion.getCreator()!=user.getId()){
                throw new Exception("没有权限");
            }
        }
        newquestion.setTitle(question.getTitle());
        newquestion.setDescription(question.getDescription());
        newquestion.setTag(question.getTag());
        if (question.getId()!=null) {
            newquestion.setGmtModified(System.currentTimeMillis());
            return questionMapper.updateById(newquestion);
        }else{
            newquestion.setCreator(user.getId());
            newquestion.setCommentCount(0);
            newquestion.setViewCount(0);
            newquestion.setLikeCount(0);
            newquestion.setGmtCreate(System.currentTimeMillis());
            newquestion.setGmtModified(newquestion.getGmtCreate());
            return questionMapper.insert(newquestion);
        }

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

    @Override
    @Transactional
    public void incView(Long id) {
        questionMapper.incView(id);
    }

    @Override
    @Transactional
    public void incComment(Long id) {
        questionMapper.incComment(id);
    }
}
