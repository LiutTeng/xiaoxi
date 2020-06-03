package com.xiaoxi.study.service.impl;

import com.xiaoxi.study.dto.request.*;
import com.xiaoxi.study.common.constants.SexEnum;
import com.xiaoxi.study.dto.response.*;
import com.xiaoxi.study.entity.UserEntity;
import com.xiaoxi.study.entity.UserEsEntity;
import com.xiaoxi.study.event.EventBusManager;
import com.xiaoxi.study.event.message.CreateUserEvent;
import com.xiaoxi.study.mapper.EsMapper;
import com.xiaoxi.study.mapper.UserMapper;
import com.xiaoxi.study.service.UserService;
import com.xiaoxi.study.util.IDGenerateUtil;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @author liuteng
 */
@Service
public class UserServiceImpl implements UserService {

    private static final String FORMAT = "*%s*";

    private IDGenerateUtil idGenerateUtil;

    private UserMapper userMapper;

    private EsMapper esMapper;

    private EventBusManager eventBusManager;

    public UserServiceImpl(IDGenerateUtil idGenerateUtil, UserMapper userMapper, EsMapper esMapper, EventBusManager eventBusManager) {
        this.idGenerateUtil = idGenerateUtil;
        this.userMapper = userMapper;
        this.esMapper = esMapper;
        this.eventBusManager = eventBusManager;
    }

    @Override
    public CreateUserResponseDTO save(CreateUserRequestDTO request) {

        UserEntity user = new UserEntity();
        Long id = idGenerateUtil.nextId();
        user.setId(id);
        user.setName(request.getName());
        user.setPhone(request.getPhone());
        user.setCreateTime(new Date());
        user.setEnable(true);
        user.setVersion(1L);
        user.setSex(SexEnum.getSexEnum(request.getSex()));

        userMapper.insert(user);

        // 保存ES
        eventBusManager.post(new CreateUserEvent(id, request.getName(), String.valueOf(request.getSex()), request.getPhone()));

        return new CreateUserResponseDTO(user.getId());
    }

    @Override
    public GetUserByIdResponseDTO getUserById(GetUserByIdRequestDTO request) {
        UserEntity userEntity = userMapper.selectById(request.getId());
        return new GetUserByIdResponseDTO(userEntity.getId(), userEntity.getName(), userEntity.getPhone(),
                userEntity.getSex().getCode(), userEntity.getCreateTime());
    }

    /**
     * 通过手机号精确查询
     */
    @Override
    public GetUserByPhoneResponseDTO getUserByPhone(GetUserByPhoneRequestDTO request) {
        List<UserEsEntity> userList = new ArrayList<>();
        // 精确匹配
        QueryBuilder builder = QueryBuilders.matchQuery("phone", request.getPhone());
        Optional.ofNullable(esMapper.search(builder)).ifPresent(res-> res.forEach(userList::add));
        return new GetUserByPhoneResponseDTO(userList);
    }

    /**
     * 通过姓名模糊查询
     */
    @Override
    public GetUserByNameResponseDTO getUserByName(GetUserByNameRequestDTO request) {
        List<UserEsEntity> userList = new ArrayList<>();
        QueryBuilder builder = QueryBuilders.wildcardQuery("name", String.format(FORMAT, request.getName()));
        Optional.ofNullable(esMapper.search(builder)).ifPresent(res-> res.forEach(userList::add));
        return new GetUserByNameResponseDTO(userList);
    }

    /**
     * 条件组合分页查询
     */
    @Override
    public SearchUserResponseDTO search(SearchUserRequestDTO request) {
        BoolQueryBuilder builder = QueryBuilders.boolQuery();
        SearchQuery searchQuery = new NativeSearchQuery(builder);
        Pageable pageable = PageRequest.of(request.getPageNo() - 1, request.getPageSize());
        searchQuery.setPageable(pageable);
        if (StringUtils.isNotBlank(request.getName())) {
            QueryBuilder nameQuery = QueryBuilders.wildcardQuery("name", String.format(FORMAT, request.getName()));
            builder.must(nameQuery);
        }
        if (StringUtils.isNotBlank(request.getPhone())) {
            QueryBuilder phoneQuery = QueryBuilders.matchQuery("phone", request.getPhone());
            builder.must(phoneQuery);
        }
        if (StringUtils.isNotBlank(request.getSex())) {
            QueryBuilder sexQuery = QueryBuilders.matchQuery("sex", request.getSex());
            builder.must(sexQuery);
        }
        return new SearchUserResponseDTO(esMapper.search(searchQuery).getContent());
    }

    @Override
    public int deleteById(Long id) {
        return userMapper.deleteById(id);
    }

    @Override
    public int updateById(UserEntity user) {
        return userMapper.updateById(user);
    }

}
