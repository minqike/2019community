package com.min.springboot.community.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
  @TableId(type = IdType.AUTO)
  private Long id;
  private Long parentId;
  private Integer type;
  private Long commentator;
  private Integer likeCount;
  private String content;
  private Integer commentCount;
  private Long gmtCreate;
  private Long gmtModified;

}
