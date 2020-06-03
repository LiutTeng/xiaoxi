package com.xiaoxi.study.dto.request;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * @author liuteng
 */
@Data
public class GetUserByPhoneRequestDTO {

    @NotNull(message = "手机号不能为空")
    @Pattern(regexp = "1[3|4|5|7|8|9][0-9]\\d{8}", message = "手机号格式不正确")
    private String phone;

}
