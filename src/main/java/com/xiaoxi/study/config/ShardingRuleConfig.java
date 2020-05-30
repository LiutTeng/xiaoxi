package com.xiaoxi.study.config;

import io.shardingsphere.api.config.rule.ShardingRuleConfiguration;
import io.shardingsphere.core.yaml.sharding.YamlShardingConfiguration;
import io.shardingsphere.core.yaml.sharding.YamlShardingRuleConfiguration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.io.File;

/**
 * @author liuteng
 */
@Configuration
public class ShardingRuleConfig {

    @Value("${spring.profiles.active}")
    private String profile;

    @Bean("shardingConfig")
    public ShardingRuleConfiguration getShardingRuleConfig() throws Exception {

        ClassPathResource classPathResource = new ClassPathResource("sharding/" + profile + "/sharding.yml");

        File yamlFile = classPathResource.getFile();

        YamlShardingConfiguration yamlShardingRuleConfiguration = YamlShardingConfiguration.unmarshal(yamlFile);

        YamlShardingRuleConfiguration shardingRule = yamlShardingRuleConfiguration.getShardingRule();
        if (null == shardingRule) {
            throw new Exception("YamlShardingRuleConfiguration is Null!");
        }
        return shardingRule.getShardingRuleConfiguration();
    }

}
