package com.xiaoxi.study.dto.response;

import com.xiaoxi.study.common.pojo.BaseResponseDTO;

/**
 * @author liuteng
 */
public class CreateUserResponseDTO extends BaseResponseDTO {

    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CreateUserResponseDTO() {
    }

    public CreateUserResponseDTO(Long id) {
        this.id = id;
    }
}
