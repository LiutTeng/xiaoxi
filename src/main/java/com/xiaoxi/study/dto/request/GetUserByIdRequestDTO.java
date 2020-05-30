package com.xiaoxi.study.dto.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author liuteng
 */
@Data
public class GetUserByIdRequestDTO {

    @NotNull(message = "ID不能为空")
    private Long id;

}
