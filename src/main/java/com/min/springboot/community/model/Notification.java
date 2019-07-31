package com.min.springboot.community.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Notification {

    @TableId(type = IdType.AUTO)
    private Long id;
    private Long notifier;
    private String notifierName;
    private Long receiver;
    private Long outerid;
    private String outerTitle;
    private Integer type;
    private Integer status;
    private Long gmtCreate;

}
