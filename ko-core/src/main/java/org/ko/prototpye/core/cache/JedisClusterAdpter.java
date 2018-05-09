package org.ko.prototpye.core.cache;

import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.JedisCluster;

/**
 * 
 * 
 * JedisClusterAdpter
 *
 * @author <A>chent</A>
 *
 */
public abstract class JedisClusterAdpter {

    @Autowired
    protected JedisCluster jedisCluster;

}
