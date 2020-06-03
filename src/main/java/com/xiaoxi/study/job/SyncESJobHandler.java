package com.xiaoxi.study.job;

import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.annotation.XxlJob;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author liuteng
 * 同步数据至ES
 */
@Slf4j
@Component
public class SyncESJobHandler {

    @XxlJob("syncESJobHandler")
    public ReturnT<String> execute(String s) throws Exception {
        log.info("SyncESJobHandler running ...  parameter:{}", s);
        return null;
    }

}
