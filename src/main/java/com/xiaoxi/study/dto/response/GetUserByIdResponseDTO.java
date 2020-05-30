package com.xiaoxi.study.dto.response;

import lombok.Data;

import java.util.Date;

/**
 * @author liuteng
 */
@Data
public class GetUserByIdResponseDTO {

    private Long id;

    private String name;

    private String phone;

    private int sex;

    private Date createTime;

    public GetUserByIdResponseDTO() {
    }

    public GetUserByIdResponseDTO(Long id, String name, String phone, int sex, Date createTime) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.sex = sex;
        this.createTime = createTime;
    }
}
