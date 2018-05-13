package org.ko.framework.core.cache;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by calder on 17/5/19.
 */
//@Configuration
public class JedisClusterConfig {

    @Value("${redis.cache.clusterNodes}")
    private String clusterNodes;
    @Value("${redis.cache.password}")
    private String password;
    @Value("${redis.cache.maxIdle}")
    private int maxIdle;
    @Value("${redis.cache.maxTotal}")
    private int maxTotal;
    @Value("${redis.cache.maxWaitMillis}")
    private int maxWaitMillis;
    @Value("${redis.cache.testOnBorrow}")
    private boolean testOnBorrow;
    @Value("${redis.cache.connectionTimeout}")
    private int connectionTimeout;
    @Value("${redis.cache.soTimeout}")
    private int soTimeout;
    @Value("${redis.cache.maxAttempts}")
    private int maxAttempts;

    @Bean
    public JedisCluster getJedisCluster() {

        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxIdle(maxIdle);
        poolConfig.setMaxTotal(maxTotal);
        poolConfig.setMaxWaitMillis(maxWaitMillis);
        poolConfig.setTestOnBorrow(testOnBorrow);

        //获取服务器数组(这里要相信自己的输入，配置文件不能配置错误)
        String[] serverArray = clusterNodes.split(",");
        Set<HostAndPort> nodes = new HashSet<HostAndPort>();

        for (String ipPort : serverArray) {
            String[] ipPortPair = ipPort.split(":");
            nodes.add(new HostAndPort(ipPortPair[0].trim(), Integer.valueOf(ipPortPair[1].trim())));
        }

        return new JedisCluster(nodes, connectionTimeout, soTimeout, maxAttempts, password, poolConfig);
    }
}
