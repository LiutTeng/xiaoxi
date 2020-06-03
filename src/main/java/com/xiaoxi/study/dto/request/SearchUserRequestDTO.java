package com.xiaoxi.study.dto.request;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @author liuteng
 */
@Data
public class SearchUserRequestDTO {

    private String name;

    private String phone;

    private String sex;

    @NotNull(message = "页码不能为空")
    @Min(value = 1, message = "页码不能小于1")
    private Integer pageNo;

    @NotNull(message = "页大小不能为空")
    @Max(value = 100, message = "页大小不能大于100")
    private Integer pageSize;

}
