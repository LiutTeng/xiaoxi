package com.xiaoxi.study.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author liuteng
 */
@Data
@TableName("sequence")
public class SequenceEntity {

    @TableField(value = "id")
    private Long id;

    @TableField(value = "ip")
    private String ip;

    public SequenceEntity() {
    }

    public SequenceEntity(String ip) {
        this.ip = ip;
    }
}
