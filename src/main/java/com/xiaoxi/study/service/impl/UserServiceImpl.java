package com.xiaoxi.study.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiaoxi.study.dto.request.CreateUserRequestDTO;
import com.xiaoxi.study.common.constants.SexEnum;
import com.xiaoxi.study.dto.request.GetUserByIdRequestDTO;
import com.xiaoxi.study.dto.response.CreateUserResponseDTO;
import com.xiaoxi.study.dto.response.GetUserByIdResponseDTO;
import com.xiaoxi.study.entity.UserEntity;
import com.xiaoxi.study.mapper.UserMapper;
import com.xiaoxi.study.service.UserService;
import com.xiaoxi.study.util.IDGenerateUtil;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author liuteng
 */
@Service
public class UserServiceImpl implements UserService {

    private IDGenerateUtil idGenerateUtil;

    private UserMapper userMapper;

    public UserServiceImpl(IDGenerateUtil idGenerateUtil, UserMapper userMapper) {
        this.idGenerateUtil = idGenerateUtil;
        this.userMapper = userMapper;
    }

    @Override
    public CreateUserResponseDTO save(CreateUserRequestDTO request) {

        UserEntity user = new UserEntity();
        user.setId(idGenerateUtil.nextId());
        user.setName(request.getName());
        user.setPhone(request.getPhone());
        user.setCreateTime(new Date());
        user.setEnable(true);
        user.setVersion(1L);
        user.setSex(SexEnum.getSexEnum(request.getSex()));

        userMapper.insert(user);

        return new CreateUserResponseDTO(user.getId());
    }

    @Override
    public GetUserByIdResponseDTO getUserById(GetUserByIdRequestDTO request) {
        UserEntity userEntity = userMapper.selectById(request.getId());
        return new GetUserByIdResponseDTO(userEntity.getId(), userEntity.getName(), userEntity.getPhone(),
                userEntity.getSex().getCode(), userEntity.getCreateTime());
    }

    @Override
    public int deleteById(Long id) {
        return userMapper.deleteById(id);
    }

    @Override
    public int updateById(UserEntity user) {
        return userMapper.updateById(user);
    }

    @Override
    public List<UserEntity> queryList(Page<UserEntity> page, Integer enable) {
        return userMapper.selectPageByEnable(page, enable).getRecords();
    }
}
