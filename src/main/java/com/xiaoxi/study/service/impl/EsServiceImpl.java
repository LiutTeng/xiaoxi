package com.xiaoxi.study.service.impl;

import com.xiaoxi.study.dto.request.GetUserByNameRequestDTO;
import com.xiaoxi.study.dto.request.GetUserByPhoneRequestDTO;
import com.xiaoxi.study.dto.request.SearchUserRequestDTO;
import com.xiaoxi.study.dto.response.GetUserByNameResponseDTO;
import com.xiaoxi.study.dto.response.GetUserByPhoneResponseDTO;
import com.xiaoxi.study.dto.response.SearchUserResponseDTO;
import com.xiaoxi.study.entity.UserEsEntity;
import com.xiaoxi.study.mapper.EsMapper;
import com.xiaoxi.study.service.EsService;
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
}
