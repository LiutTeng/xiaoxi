package com.xiaoxi.study.service;

import com.xiaoxi.study.entity.UserEsEntity;

import java.util.List;

/**
 * @author liuteng
 */
public interface EsService {

    List<UserEsEntity> getUserByPhone(String phone);

    List<UserEsEntity> getUserByName(String name);

}
