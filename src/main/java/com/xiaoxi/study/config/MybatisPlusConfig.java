package com.xiaoxi.study.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.pagination.optimize.JsqlParserCountOptimize;
import com.xiaoxi.study.interceptor.CryptoInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author liuteng
 */
@EnableTransactionManagement
@Configuration
@MapperScan("com.example.demo.mapper")
public class MybatisPlusConfig {

    @Bean("paginationInterceptor")
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        paginationInterceptor.setCountSqlParser(new JsqlParserCountOptimize(true));
        paginationInterceptor.setDbType(DbType.MYSQL);
        return paginationInterceptor;
    }

    @Bean("cryptoInterceptor")
    public CryptoInterceptor cryptoInterceptor() {
        return new CryptoInterceptor();
    }
}

