package com.xiaoxi.study.config;

import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;

import com.xiaoxi.study.interceptor.CryptoInterceptor;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.LocalCacheScope;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

/**
 * @author liuteng
 */
@Configuration
public class SqlSessionConfig {

    private Logger logger = LoggerFactory.getLogger(SqlSessionConfig.class);

    @Bean("mySqlSessionFactoryBean")
    public MybatisSqlSessionFactoryBean createSqlSessionFactory(@Qualifier("datasource") DataSource dataSource,
                                                                @Qualifier("paginationInterceptor") PaginationInterceptor paginationInterceptor,
                                                                @Qualifier("cryptoInterceptor") CryptoInterceptor cryptoInterceptor) {

        // MybatisSqlSessionFactory
        MybatisSqlSessionFactoryBean sqlSessionFactoryBean = null;
        try {
            // 实例SessionFactory
            sqlSessionFactoryBean = new MybatisSqlSessionFactoryBean();
            // 配置数据源
            sqlSessionFactoryBean.setDataSource(dataSource);
            // 设置 MyBatis-Plus 分页插件
            Interceptor [] plugins = {cryptoInterceptor, paginationInterceptor};
            sqlSessionFactoryBean.setPlugins(plugins);
            // 加载MyBatis配置文件
            PathMatchingResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
            sqlSessionFactoryBean.setMapperLocations(resourcePatternResolver.getResources("classpath*:mapper/*.xml"));

            MybatisConfiguration configuration = new MybatisConfiguration();
            configuration.setLocalCacheScope(LocalCacheScope.STATEMENT);
            sqlSessionFactoryBean.setConfiguration(configuration);
        } catch (Exception e) {
            logger.error("创建SqlSession连接工厂错误：{}", e.getMessage());
        }
        return sqlSessionFactoryBean;
    }

    @Bean
    public MapperScannerConfigurer myGetMapperScannerConfigurer() {
        MapperScannerConfigurer myMapperScannerConfigurer = new MapperScannerConfigurer();
        myMapperScannerConfigurer.setBasePackage("com.xiaoxi.study.mapper");
        myMapperScannerConfigurer.setSqlSessionFactoryBeanName("mySqlSessionFactoryBean");
        return myMapperScannerConfigurer;
    }

}