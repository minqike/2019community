package com.min.springboot.community.event;

import lombok.Data;
import org.springframework.context.ApplicationEvent;

@Data
public class CommentPublishEvent extends ApplicationEvent {

    private String data;

    public CommentPublishEvent(Object source, String data) {
        super(source);
        this.data = data;
    }
}
