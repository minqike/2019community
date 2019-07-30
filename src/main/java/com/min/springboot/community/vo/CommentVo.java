package com.min.springboot.community.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentVo {

    private Long commentId;
    private Long parentId;
    private String content;
    private Integer type;
}
