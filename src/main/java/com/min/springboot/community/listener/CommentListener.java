package com.min.springboot.community.listener;

import com.min.springboot.community.event.CommentPublishEvent;
import com.min.springboot.community.model.Comment;
import com.min.springboot.community.model.Notification;
import com.min.springboot.community.model.Question;
import com.min.springboot.community.model.User;
import com.min.springboot.community.service.NotificationService;
import com.min.springboot.community.service.QuestionService;
import com.min.springboot.community.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * 流程
 * 1.定义一个事件CommentPublishEvent(回复发布事件)**需要继承ApplicationEvent
 * 2.定义一个监听器,监听事件 **@Component和@EventListener组合使用
 * 3.定义一个事件发布的服务 CommentEventPublisher(发布事件),用来发布事件 **@Service
 * 4.在需要发布的时候调用publishCommentPublishEvent中定义的方法发布事件 **服务中注入发布器,然后调用
 */

@Component
public class CommentListener {

    @Autowired
    QuestionService questionService;

    @Autowired
    NotificationService notificationService;

    @Autowired
    UserService userService;

    //回复数+1
    @EventListener
    public void doCommentPublishEvent1(CommentPublishEvent event){
        System.out.println("回复数+1");
        Comment comment = (Comment) event.getSource();
        System.out.println(comment.getId()+ "questinid=" + comment.getParentId());
        questionService.incComment(comment.getParentId());

    }

    //给目标人添加通知
    @EventListener
    public void doCommentPublishEvent2(CommentPublishEvent event){
        System.out.println("给目标人添加通知");
        Comment comment = (Comment) event.getSource();
        Question question = questionService.getById(comment.getParentId());
        if (comment.getCommentator()==question.getCreator()){
            System.out.println("同一用户不需要添加通知");
            return;
        }
        User user = userService.getById(comment.getCommentator());
        Notification notification = new Notification();
        notification.setNotifier(comment.getCommentator());
        notification.setReceiver(question.getCreator());
        notification.setNotifierName(user.getName());
        notification.setOuterid(comment.getParentId());
        notification.setOuterTitle(question.getTitle());
        notification.setStatus(0);
        notification.setType(0);
        notification.setGmtCreate(System.currentTimeMillis());
        notificationService.save(notification);

    }
}
