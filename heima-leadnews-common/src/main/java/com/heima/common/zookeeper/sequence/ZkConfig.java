package com.heima.common.zookeeper.sequence;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * ZkConfig
 *
 * @author Paulson
 * @date 2020/2/9 21:47
 */
@Data
@Configuration
@ConfigurationProperties(prefix="zk")
@PropertySource("classpath:zookeeper.properties")
public class ZkConfig {

    String host;
    String sequencePath;

    /**
     * 这是最快的数据库连接池
     * @return
     */
    @Bean
    public ZookeeperClient zookeeperClient(){
        return new ZookeeperClient(this.host,this.sequencePath);
    }

}