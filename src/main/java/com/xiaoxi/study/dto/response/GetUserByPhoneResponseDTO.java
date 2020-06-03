package com.xiaoxi.study.dto.response;

import com.xiaoxi.study.entity.UserEsEntity;
import lombok.Data;

import java.util.List;

/**
 * @author liuteng
 */
@Data
public class GetUserByPhoneResponseDTO {

    private List<UserEsEntity> userList;

    public GetUserByPhoneResponseDTO(List<UserEsEntity> userList) {
        this.userList = userList;
    }
}
