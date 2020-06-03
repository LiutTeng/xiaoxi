package com.xiaoxi.study.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiaoxi.study.dto.request.*;
import com.xiaoxi.study.dto.response.*;
import com.xiaoxi.study.entity.UserEntity;

import java.util.List;

/**
 * @author liuteng
 */
public interface UserService {

    CreateUserResponseDTO save(CreateUserRequestDTO request);
    /**
     * 逻辑删除
     */
    int deleteById(Long id);

    int updateById(UserEntity user);

    GetUserByPhoneResponseDTO getUserByPhone(GetUserByPhoneRequestDTO request);

    GetUserByNameResponseDTO getUserByName(GetUserByNameRequestDTO request);

    SearchUserResponseDTO search(SearchUserRequestDTO request);

    GetUserByIdResponseDTO getUserById(GetUserByIdRequestDTO request);

}
