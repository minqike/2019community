package com.min.springboot.community.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class QuestionDTO {
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
    private String avatarUrl;
}
