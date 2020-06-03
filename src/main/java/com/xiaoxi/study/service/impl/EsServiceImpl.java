package com.xiaoxi.study.service.impl;

import com.xiaoxi.study.entity.UserEsEntity;
import com.xiaoxi.study.mapper.EsMapper;
import com.xiaoxi.study.service.EsService;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author liuteng
 */
@Service
public class EsServiceImpl implements EsService {

    private static final String FORMAT = "*%s*";

    private EsMapper esMapper;

    public EsServiceImpl(EsMapper esMapper) {
        this.esMapper = esMapper;
    }

    @Override
    public List<UserEsEntity> getUserByPhone(String phone) {
        List<UserEsEntity> userList = new ArrayList<>();
        // 精确匹配
        QueryBuilder builder = QueryBuilders.matchQuery("phone", phone);
        Optional.ofNullable(esMapper.search(builder)).ifPresent(res-> res.forEach(userList::add));
        return userList;
    }

    @Override
    public List<UserEsEntity> getUserByName(String name) {
        List<UserEsEntity> userList = new ArrayList<>();
        // 模糊查询
        QueryBuilder builder = QueryBuilders.wildcardQuery("name", String.format(FORMAT, name));
        Optional.ofNullable(esMapper.search(builder)).ifPresent(res-> res.forEach(userList::add));
        return userList;
    }
}
