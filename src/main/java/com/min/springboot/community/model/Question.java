package com.min.springboot.community.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Question {
    private Long id;
    private String title;
    private String description;
    private Long creator;
    private int commentCount;
    private int viewCount;
    private int likeCount;
    private String tag;
    private Long gmtCreate;
    private Long gmtModified;

}
