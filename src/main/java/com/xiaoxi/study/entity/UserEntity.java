package com.xiaoxi.study.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.xiaoxi.study.common.annotation.Decrypt;
import com.xiaoxi.study.common.annotation.Encrypt;
import com.xiaoxi.study.common.constants.SexEnum;
import lombok.Data;

import java.util.Date;

/**
 * @author liuteng
 */
@Data
@TableName("user")
public class UserEntity{

    @TableField("id")
    private Long id;

    @TableField("name")
    private String name;

    @TableField("sex")
    private SexEnum sex;

    @Encrypt
    @Decrypt
    @TableField("phone")
    private String phone;

    @TableField("create_time")
    private Date createTime;

    @TableLogic(delval = "0", value = "1")
    @TableField("enable")
    private boolean enable;

    @Version
    @TableField("version")
    private Long version;

}
