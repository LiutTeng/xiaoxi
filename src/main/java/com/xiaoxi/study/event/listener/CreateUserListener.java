package com.xiaoxi.study.event.listener;

import com.alibaba.fastjson.JSON;
import com.google.common.eventbus.Subscribe;
import com.xiaoxi.study.entity.UserEsEntity;
import com.xiaoxi.study.event.message.CreateUserEvent;
import com.xiaoxi.study.mapper.EsMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author liuteng
 */
@Slf4j
@SuppressWarnings({"UnstableApiUsage", "unused"})
@Component
public class CreateUserListener {

    private EsMapper esMapper;

    public CreateUserListener(EsMapper esMapper) {
        this.esMapper = esMapper;
    }

    @Subscribe
    public void handleEvent(CreateUserEvent event) {
        log.info("EventBus监听事件, Type:{}, Event:{}", event.getClass().getSimpleName(), JSON.toJSONString(event));
        esMapper.save(new UserEsEntity(event.getId(), event.getName(), event.getSex(), event.getPhone()));
    }
}
