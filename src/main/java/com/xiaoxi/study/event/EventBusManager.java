package com.xiaoxi.study.event;

import com.alibaba.fastjson.JSON;
import com.google.common.eventbus.AsyncEventBus;
import com.xiaoxi.study.event.listener.CreateUserListener;
import com.xiaoxi.study.event.message.CreateUserEvent;
import com.xiaoxi.study.util.ThreadPoolUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.Executor;

/**
 * @author liuteng
 */
@Slf4j
@SuppressWarnings("UnstableApiUsage")
@Component
public class EventBusManager extends AsyncEventBus {

    private CreateUserListener createUserListener;

    private static Executor executor = ThreadPoolUtil.getInstance("EventBus").getThreadPoolExecutor();

    public EventBusManager(CreateUserListener createUserListener) {
        super(executor);
        this.createUserListener = createUserListener;
    }

    @PostConstruct
    public void init () {
        super.register(createUserListener);
    }

    public void post(CreateUserEvent event) {
        log.info("EventBus发布事件, Type:{}, Event:{}", event.getClass().getSimpleName(), JSON.toJSONString(event));
        super.post(event);
    }
}
