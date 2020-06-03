# xiaoxi

# 相关使用说明
- **nacos.yml** : *nacos配置文件*

    - url : http://106.13.181.6:8848/nacos
    
    - 账号：nacos   密码：nacos
    
- **user_index.json** : *ES索引文件*
  
  - 创建索引
    
    - url : 106.13.181.6:9200/user
    
    - method : PUT
    
  - 添加索引字段
   
     - method : POST
     
     - url : http://106.13.181.6:9200/user/user/_mapping
  
   - ElasticHD : http://106.13.181.6:9800/
  
- **user.sql** : *用户表DDL*

- **XXL-Job** : *分布式统一调度平台*
  
  - url : http://106.13.181.6:9090/xxl-job-admin/
  
  - 账号 ：admin 密码：123456
  
- Sweger
  
  - url : http://localhost:8080/swagger-ui.html