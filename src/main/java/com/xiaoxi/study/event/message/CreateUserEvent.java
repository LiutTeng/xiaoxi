package com.xiaoxi.study.event.message;

import lombok.Data;

/**
 * @author liuteng
 */
@Data
public class CreateUserEvent {

    private Long id;

    private String name;

    private String sex;

    private String phone;

    public CreateUserEvent() {
    }

    public CreateUserEvent(Long id, String name, String sex, String phone) {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.phone = phone;
    }
}
