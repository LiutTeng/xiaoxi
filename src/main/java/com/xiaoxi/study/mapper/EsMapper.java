package com.xiaoxi.study.mapper;

import com.xiaoxi.study.entity.UserEsEntity;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

/**
 * @author liuteng
 */
@Repository
public interface EsMapper extends ElasticsearchRepository<UserEsEntity, Long> {

}
