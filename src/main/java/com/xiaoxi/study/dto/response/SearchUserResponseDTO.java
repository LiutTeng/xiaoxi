package com.xiaoxi.study.dto.response;

import com.xiaoxi.study.entity.UserEsEntity;
import lombok.Data;

import java.util.List;

/**
 * @author liuteng
 */
@Data
public class SearchUserResponseDTO {

    private List<UserEsEntity> userList;

    public SearchUserResponseDTO(List<UserEsEntity> userList) {
        this.userList = userList;
    }
}
