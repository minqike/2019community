package com.min.springboot.community.publish;

import com.min.springboot.community.event.CommentPublishEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

@Service
public class CommentEventPublisher {

    @Autowired
    private ApplicationContext applicationContext;

    public void  publishCommentPublishEvent(Object source,String data){
        CommentPublishEvent myEvent = new CommentPublishEvent(source, data);
        applicationContext.publishEvent(myEvent);
    }
}
