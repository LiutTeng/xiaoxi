package com.xiaoxi.study.dto.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author liuteng
 */
@Data
public class GetUserByNameRequestDTO {

    @NotBlank(message = "姓名不能为空")
    private String name;
}
