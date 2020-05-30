package com.xiaoxi.study.config;

import com.zaxxer.hikari.HikariDataSource;
import io.shardingsphere.api.config.rule.ShardingRuleConfiguration;
import io.shardingsphere.shardingjdbc.api.ShardingDataSourceFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @author liuteng
 */
@Configuration
@ConfigurationProperties(prefix = "sharding")
public class DataSourceConfig  {

    private Map<String, HikariDataSource> dataSource;

    public Map<String, HikariDataSource> getDataSource() {
        return dataSource;
    }

    public void setDataSource(Map<String, HikariDataSource> dataSource) {
        this.dataSource = dataSource;
    }

    @Bean("datasource")
    public DataSource getDataSource(@Qualifier("shardingConfig") ShardingRuleConfiguration shardingRuleConfig,
                                    @Qualifier("properties") Properties properties) throws SQLException {

        Map<String, DataSource> dataSourceMap = new HashMap<>();
        dataSource.forEach(dataSourceMap::put);
        return ShardingDataSourceFactory.createDataSource(dataSourceMap, shardingRuleConfig, new HashMap<>(), properties);
    }


    @Bean("properties")
    public Properties getProperties(){
        // 获取数据源对象
        Properties props=new Properties();
        /*
         * ==== Properties取值范围 ====
         *
         * SQL_SHOW("sql.show", String.valueOf(Boolean.FALSE), Boolean.TYPE),
         * ACCEPTOR_SIZE("acceptor.size", String.valueOf(Runtime.getRuntime().availableProcessors() * 2), Integer.TYPE),
         * EXECUTOR_SIZE("executor.size", String.valueOf(0), Integer.TYPE),
         * MAX_CONNECTIONS_SIZE_PER_QUERY("max.connections.size.per.query", String.valueOf(1), Integer.TYPE),
         * PROXY_FRONTEND_FLUSH_THRESHOLD("proxy.frontend.flush.threshold", String.valueOf(128), Integer.TYPE),
         * PROXY_TRANSACTION_TYPE("proxy.transaction.type", "LOCAL", String.class),
         * PROXY_OPENTRACING_ENABLED("proxy.opentracing.enabled", String.valueOf(Boolean.FALSE), Boolean.TYPE),
         * PROXY_BACKEND_USE_NIO("proxy.backend.use.nio", String.valueOf(Boolean.FALSE), Boolean.TYPE),
         * PROXY_BACKEND_MAX_CONNECTIONS("proxy.backend.max.connections", String.valueOf(8), Integer.TYPE),
         * PROXY_BACKEND_CONNECTION_TIMEOUT_SECONDS("proxy.backend.connection.timeout.seconds", String.valueOf(60), Integer.TYPE),
         * CHECK_TABLE_METADATA_ENABLED("check.table.metadata.enabled", String.valueOf(Boolean.FALSE), Boolean.TYPE);
         */
        props.put("sql.show", "true");

        return props;
    }

}
