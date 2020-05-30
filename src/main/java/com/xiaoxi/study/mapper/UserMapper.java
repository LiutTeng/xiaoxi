package com.xiaoxi.study.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiaoxi.study.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author liuteng
 */
@Mapper
@Repository
public interface UserMapper extends BaseMapper<UserEntity> {

    IPage<UserEntity> selectPageByEnable(Page<?> page, Integer enable);

}
