package com.xiaoxi.study.dto.request;

import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * @author liuteng
 */
@Data
public class CreateUserRequestDTO {

    @NotBlank(message = "姓名不能为空")
    private String name;

    @NotNull(message = "手机号不能为空")
    @Pattern(regexp = "1[3|4|5|7|8|9][0-9]\\d{8}", message = "手机号格式不正确")
    private String phone;

    @NotNull(message = "性别不能为空")
    @Range(min = 0, max = 1, message = "性别取值范围不正确")
    private Integer sex;

}
