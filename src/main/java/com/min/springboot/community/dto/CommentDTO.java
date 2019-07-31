package com.min.springboot.community.dto;

import com.min.springboot.community.model.Comment;
import lombok.Data;

import java.util.List;

@Data
public class CommentDTO {
    private Integer count;
    private List<Comment> list;
}
