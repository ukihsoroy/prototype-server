package org.ko.prototpye.core.cache;

import com.alibaba.fastjson.JSONObject;
import org.ko.prototpye.core.exception.GeneralException;

import java.util.List;
import java.util.Map;

/**
 * 
 * BaseRedisDao
 *
 * @author <A>calder</A>
 *
 */
public class BaseRedisDao extends JedisClusterAdpter {

    /**
     * 新增
     * 
     * @param key
     * @param data
     * @return
     * @throws GeneralException
     */
    public String add(String key, String data) throws GeneralException {
        return jedisCluster.set(key, data);
    }

    /**
     * 新增
     * 
     * @param key
     * @param data
     * @return
     * @throws GeneralException
     */
    public String addObject(String key, Object data) throws GeneralException {
        return jedisCluster.set(key, JSONObject.toJSONString(data));
    }

    /**
     * 修改
     * 
     * @param key
     * @param data
     * @return
     * @throws GeneralException
     */
    public boolean update(String key, String data) throws GeneralException {
        jedisCluster.set(key, data);
        return false;
    }

    /**
     * 修改
     * 
     * @param key
     * @param data
     * @return
     * @throws GeneralException
     */
    public boolean updateObject(String key, Object data) throws GeneralException {
        jedisCluster.set(key, JSONObject.toJSONString(data));
        return false;
    }

    /**
     * 获取
     * 
     * @param key
     * @return
     * @throws GeneralException
     */
    public String get(String key) throws GeneralException {
        String data = jedisCluster.get(key);
        return data;
    }

    /**
     * 获取
     * 
     * @param key
     * @return
     * @throws GeneralException
     */
    public <R> R getObject(String key, Class<R> classze)
            throws GeneralException {
        String dataJson = jedisCluster.get(key);
        if (dataJson != null) {
            R data = (R) JSONObject.parseObject(dataJson, classze);
            return data;
        }
        return null;
    }

    /**
     * 是否存在
     * 
     * @param key
     * @return
     */
    public Boolean isExist(String key) {
        return jedisCluster.exists(key);
    }

    /**
     * 删除
     * 
     * @param key
     * @return
     * @throws GeneralException
     */
    public Long delete(String key) throws GeneralException {
        return jedisCluster.del(key);
    }

    /**
     * 自增ID
     *
     * @param key
     * @return 自增Id
     */
    public Long newId(String key) {
        return jedisCluster.incr(key);
    }

    /**
     * Hash get all
     *
     * @param key
     */
    public Map<String, String> hgetall(String key) {
        return jedisCluster.hgetAll(key);
    }

    /**
     * get Children Ids
     *
     * @param key
     */
    public List<String> lrange(String key) {
        return jedisCluster.lrange(key, 0L, -1L);
    }

    /**
     * lren range
     *
     * @param key
     */
    public Long lrem(String key, Long id, String value) {
        return jedisCluster.lrem(key, id, value);
    }

    /**
     * Hash set
     *
     * @param key
     * @param map
     */
    public void hmset(String key, Map<String, String> map) {
        jedisCluster.hmset(key, map);
    }

    /**
     * Hash flied set
     *
     * @param key
     * @param flied
     * @param value
     */
    public void hset(String key, String flied, String value) {
        jedisCluster.hset(key, flied, value);
    }

    /**
     * push List
     *
     * @param key
     * @param value
     */
    public void rpush(String key, String[] value) {
        jedisCluster.rpush(key, value);
    }

    /**
     * hget
     *
     * @param key
     * @param field
     */
    public String hget(String key, String field) {
        return jedisCluster.hget(key, field);
    }

    /**
     * hdel
     *
     * @param key
     * @param field
     */
    public Long hdel(String key, String... field) {
        return jedisCluster.hdel(key, field);
    }
}
