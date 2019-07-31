package com.min.springboot.community.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.min.springboot.community.dto.CommentDTO;
import com.min.springboot.community.error.MyErrorCode;
import com.min.springboot.community.error.MyException;
import com.min.springboot.community.event.CommentPublishEvent;
import com.min.springboot.community.mapper.CommentMapper;
import com.min.springboot.community.mapper.QuestionMapper;
import com.min.springboot.community.model.Comment;
import com.min.springboot.community.model.Question;
import com.min.springboot.community.model.User;
import com.min.springboot.community.publish.CommentEventPublisher;
import com.min.springboot.community.service.CommentService;
import com.min.springboot.community.service.QuestionService;
import com.min.springboot.community.vo.CommentVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment>  implements CommentService {
    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    CommentEventPublisher commentEventPublisher;

    @Override
    @Transactional
    public Comment save(CommentVo vo, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        Long userId;
        if (user==null) {
            userId=0L;
        }else{
            userId=user.getId();
        }
        Comment comment;
        if (vo.getCommentId() == null) {
            comment = new Comment();
            comment.setParentId(vo.getParentId());
            comment.setContent(vo.getContent());
            comment.setType(vo.getType());
            comment.setCommentator(userId);
            comment.setCommentCount(0);
            comment.setLikeCount(0);
            comment.setGmtCreate(System.currentTimeMillis());
            comment.setGmtModified(comment.getGmtCreate());

            commentMapper.insert(comment);
//            //回复数加1
//            questionMapper.incComment(vo.getParentId());
            //触发发布回复事件
            commentEventPublisher.publishCommentPublishEvent(comment,"发布回复");

        }else{
            comment = commentMapper.selectById(vo.getCommentId());
            if (comment == null) {
                throw new MyException(MyErrorCode.COMMENT_NOT_FOUND);
            }
            if (comment.getCommentator() == 0) {
                throw new MyException(MyErrorCode.NON_USER);
            }
            if (comment.getCommentator() != userId) {
                throw new MyException(MyErrorCode.USER_NOT_SAME);
            }
            comment.setContent(vo.getContent());
            comment.setGmtModified(System.currentTimeMillis());
            commentMapper.updateById(comment);
        }


        return comment;
    }

    @Override
    public CommentDTO getCommnets(Long id) {
        List<Comment> commentList =  commentMapper.getCommnets(id);
        CommentDTO dto = new CommentDTO();
        dto.setList(commentList);
        dto.setCount(commentList.size());
        return dto;
    }
}
