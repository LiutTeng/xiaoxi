package com.xiaoxi.study.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiaoxi.study.dto.request.CreateUserRequestDTO;
import com.xiaoxi.study.dto.request.GetUserByIdRequestDTO;
import com.xiaoxi.study.dto.response.CreateUserResponseDTO;
import com.xiaoxi.study.dto.response.GetUserByIdResponseDTO;
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
    /**
     * 分页查询
     */
    List<UserEntity> queryList(Page<UserEntity> page, Integer enable);

    GetUserByIdResponseDTO getUserById(GetUserByIdRequestDTO request);

}
