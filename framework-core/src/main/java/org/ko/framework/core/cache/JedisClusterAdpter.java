package org.ko.framework.core.cache;

import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.JedisCluster;

/**
 * 
 * 
 * JedisClusterAdpter
 *
 */
public abstract class JedisClusterAdpter {

    @Autowired
    protected JedisCluster jedisCluster;

}
