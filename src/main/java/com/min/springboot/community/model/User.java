package com.min.springboot.community.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class User {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String accountId;
    private String name;
    private String token;
    private String bio;
    private String avatarUrl;
    private Long gmtCreate;
    private Long gmtModified;
}
