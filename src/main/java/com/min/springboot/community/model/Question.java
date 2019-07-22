package com.min.springboot.community.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Question {
    @TableId(type = IdType.AUTO)
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
