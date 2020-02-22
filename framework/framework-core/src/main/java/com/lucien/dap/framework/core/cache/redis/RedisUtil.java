//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.lucien.dap.framework.core.cache.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;

import java.util.*;

@Component("RedisUtil")
public class RedisUtil {
    private static final Logger log = LoggerFactory.getLogger(RedisUtil.class);
    private JedisPool jedisPool;
    JedisCluster jedisCluster;

    @Autowired
    public void setJedisPool(JedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }

    @Autowired
    public void setJedisCluster(JedisCluster jedisCluster) {
        this.jedisCluster = jedisCluster;
    }

    Boolean isCluster;

    @Value("${jedis.cluster}")
    public void setCluster(Boolean cluster) {
        isCluster = cluster;
    }

    public void set(String keyPrefix, String key, String value) {
        String finalKey;
        if (StringUtils.isEmpty(keyPrefix)) {
            finalKey = key;
        } else {
            finalKey = keyPrefix.concat(key);
        }

        this.set(finalKey, value);
    }

    public void set(String key, String value) {
        Jedis jedis = null;

        try {
            if (this.isCluster) {
                this.jedisCluster.set(key, value);
            } else {
                jedis = this.jedisPool.getResource();
                jedis.set(key, value);
            }
        } catch (Exception var8) {
            log.error(var8.getMessage());
        } finally {
            if (!this.isCluster && jedis != null) {
                jedis.close();
            }

        }

    }

    public void setEx(String keyPrefix, String key, int expireSeconds, String value) {
        String finalKey;
        if (StringUtils.isEmpty(keyPrefix)) {
            finalKey = key;
        } else {
            finalKey = keyPrefix.concat(key);
        }

        this.setEx(finalKey, expireSeconds, value);
    }

    public void setEx(String keyPrefix, String key, int expireInSeconds, byte[] value) {
        String finalKey;
        if (StringUtils.isEmpty(keyPrefix)) {
            finalKey = key;
        } else {
            finalKey = keyPrefix.concat(key);
        }

        this.setEx(finalKey, expireInSeconds, value);
    }

    public Long setNx(String keyPrefix, String key, String value) {
        return this.setNx(keyPrefix, key, value, 216000);
    }


    public void setEx(String key, int expireSeconds, String value) {
        Jedis jedis = null;

        try {
            if (this.isCluster) {
                this.jedisCluster.setex(key, expireSeconds, value);
            } else {
                jedis = this.jedisPool.getResource();
                jedis.setex(key, expireSeconds, value);
            }
        } catch (Exception var9) {
            log.error(var9.getMessage());
        } finally {
            if (!this.isCluster && jedis != null) {
                jedis.close();
            }
        }
    }

    public void setEx(String key, int expireSeconds, byte[] value) {
        Jedis jedis = null;

        try {
            if (this.isCluster) {
                this.jedisCluster.setex(key.getBytes(), expireSeconds, value);
            } else {
                jedis = this.jedisPool.getResource();
                jedis.setex(key.getBytes(), expireSeconds, value);
            }
        } catch (Exception var9) {
            log.error(var9.getMessage());
        } finally {
            if (!this.isCluster && jedis != null) {
                jedis.close();
            }

        }
    }

    public Long setNx(String keyPrefix, String key, String value, int expireSeconds) {
        String finalKey = null;
        if (StringUtils.isEmpty(keyPrefix)) {
            finalKey = key;
        } else {
            finalKey = keyPrefix.concat(key);
        }

        Jedis jedis = null;
        Long retVal = 0L;

        try {
            if (this.isCluster) {
                retVal = this.jedisCluster.setnx(finalKey, value);
                this.jedisCluster.expire(finalKey, expireSeconds);
            } else {
                jedis = this.jedisPool.getResource();
                retVal = jedis.setnx(finalKey, value);
                jedis.expire(finalKey, expireSeconds);
            }
        } catch (Exception var12) {
            log.error(var12.getMessage());
        } finally {
            if (!this.isCluster && jedis != null) {
                jedis.close();
            }

        }
        return retVal;

    }


    public void incrBy(String key, long by) {
        this.incr(key, by);
    }


    public Long incr(String key, long by) {
        Jedis jedis = null;
        Long retVal = 0L;

        try {
            if (this.isCluster) {
                retVal = this.jedisCluster.incrBy(key, by);
            } else {
                jedis = this.jedisPool.getResource();
                retVal = jedis.incrBy(key, by);
            }
        } catch (Exception var10) {
            log.error(var10.getMessage());
        } finally {
            if (!this.isCluster && jedis != null) {
                jedis.close();
            }

        }

        return retVal;

    }

    public Long incrBy(String key, long by, int expireSeconds) {
        Long incr = this.incr(key, by);
        if (incr == 1) {
            if (this.isCluster) {
                this.jedisCluster.expire(key, expireSeconds);
            } else {
                this.jedisPool.getResource().expire(key, expireSeconds);
                this.jedisPool.getResource().close();
            }
        }
        return incr;
    }

    public String get(String key) {
        Jedis jedis = null;

        try {
            String retVal;
            if (this.isCluster) {
                retVal = this.jedisCluster.get(key);
            } else {
                jedis = this.jedisPool.getResource();
                retVal = jedis.get(key);
            }

            String var4 = retVal;
            return var4;
        } catch (Exception var8) {
            log.error(var8.getMessage());
        } finally {
            if (!this.isCluster && jedis != null) {
                jedis.close();
            }

        }
        return null;

    }


    public byte[] getByte(String key) {
        Jedis jedis = null;

        try {
            byte[] retVal;
            if (this.isCluster) {
                retVal = this.jedisCluster.get(key.getBytes());
            } else {
                jedis = this.jedisPool.getResource();
                retVal = jedis.get(key.getBytes());
            }

            if (retVal != null && retVal.length == 0) {
                ;
            }

            byte[] var4 = retVal;
            return var4;
        } catch (Exception var8) {
            log.error(var8.getMessage());
        } finally {
            if (!this.isCluster && jedis != null) {
                jedis.close();
            }

        }

        return null;
    }


    public byte[] getByteEx(String keyPrefix, String key) {
        String finalKey = null;
        if (StringUtils.isEmpty(keyPrefix)) {
            finalKey = key;
        } else {
            finalKey = keyPrefix.concat(key);
        }

        return this.getByte(finalKey);
    }


    public String getEx(String keyPrefix, String key) {
        String finalKey = null;
        if (StringUtils.isEmpty(keyPrefix)) {
            finalKey = key;
        } else {
            finalKey = keyPrefix.concat(key);
        }

        return this.get(finalKey);
    }


    public void del(String key) {
        Jedis jedis = null;

        try {
            if (this.isCluster) {
                this.jedisCluster.del(key);
            } else {
                jedis = this.jedisPool.getResource();
                jedis.del(key);
            }
        } catch (Exception var7) {
            log.error(var7.getMessage());
        } finally {
            if (!this.isCluster && jedis != null) {
                jedis.close();
            }

        }
    }


    public void delEx(String keyPrefix, String key) {
        String finalKey;
        if (StringUtils.isEmpty(keyPrefix)) {
            finalKey = key;
        } else {
            finalKey = keyPrefix.concat(key);
        }

        this.del(finalKey);
    }


    public void hset(String keyPrefix, String key, int expireSeconds, String field, String value) {
        String finalKey;
        if (StringUtils.isEmpty(keyPrefix)) {
            finalKey = key;
        } else {
            finalKey = keyPrefix.concat(key);
        }

        Jedis jedis = null;

        try {
            if (this.isCluster) {
                this.jedisCluster.hset(finalKey, field, value);
                this.jedisCluster.expire(finalKey, expireSeconds);
            } else {
                jedis = this.jedisPool.getResource();
                jedis.hset(finalKey, field, value);
                jedis.expire(finalKey, expireSeconds);
            }
        } catch (Exception var12) {
            log.error(var12.getMessage());
        } finally {
            if (!this.isCluster && jedis != null) {
                jedis.close();
            }

        }
    }

    public String hget(String keyPrefix, String key, String field) {
        String finalKey;
        if (StringUtils.isEmpty(keyPrefix)) {
            finalKey = key;
        } else {
            finalKey = keyPrefix.concat(key);
        }

        Jedis jedis = null;

        try {
            String retVal;
            if (this.isCluster) {
                retVal = this.jedisCluster.hget(finalKey, field);
            } else {
                jedis = this.jedisPool.getResource();
                retVal = jedis.hget(finalKey, field);
            }

            String var7 = retVal;
            return var7;
        } catch (Exception var11) {
            log.error(var11.getMessage());
        } finally {
            if (!this.isCluster && jedis != null) {
                jedis.close();
            }

        }

        return null;

    }


    public void sadd(String key, int expireSeconds, String... value) {
        if (value != null && value.length != 0) {
            Jedis jedis = null;
            try {
                if (this.isCluster) {
                    this.jedisCluster.sadd(key, value);
                    if (expireSeconds != 0) {
                        this.jedisCluster.expire(key, expireSeconds);
                    }
                } else {
                    jedis = this.jedisPool.getResource();
                    jedis.sadd(key, value);
                    if (expireSeconds != 0) {
                        jedis.expire(key, expireSeconds);
                    }
                }
            } catch (Exception var11) {
                log.error(var11.getMessage());
            } finally {
                if (!this.isCluster && jedis != null) {
                    jedis.close();
                }

            }

        }
    }

    public boolean sismember(String key, String value) {
        Jedis jedis = null;

        try {
            if (this.isCluster) {
                return this.jedisCluster.sismember(key, value);
            } else {
                jedis = this.jedisPool.getResource();
                return jedis.sismember(key, value);
            }
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return false;
        } finally {
            if (!this.isCluster && jedis != null) {
                jedis.close();
            }
        }
    }

    public Long sCard(String key) {
        Jedis jedis = null;

        try {
            if (this.isCluster) {
                return this.jedisCluster.scard(key);
            } else {
                jedis = this.jedisPool.getResource();
                return jedis.scard(key);
            }
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return 0L;
        } finally {
            if (!this.isCluster && jedis != null) {
                jedis.close();
            }
        }
    }


    public Set<String> smembers(String keyPrefix, String key) {
        String finalKey = null;
        if (StringUtils.isEmpty(keyPrefix)) {
            finalKey = key;
        } else {
            finalKey = keyPrefix.concat(key);
        }

        return this.smembers(finalKey);

    }

    public Set<String> smembers(String key) {
        Jedis jedis = null;

        try {
            Set retVal;
            if (this.isCluster) {
                retVal = this.jedisCluster.smembers(key);
            } else {
                jedis = this.jedisPool.getResource();
                retVal = jedis.smembers(key);
            }

            Set var4 = retVal;
            return var4;
        } catch (Exception var8) {
            log.error(var8.getMessage());
        } finally {
            if (!this.isCluster && jedis != null) {
                jedis.close();
            }

        }

        return null;
    }

    public long srem(String key, String... value) {
        Jedis jedis = null;
        try {
            long retVal;
            if (this.isCluster) {
                retVal = this.jedisCluster.srem(key, value);
            } else {
                jedis = this.jedisPool.getResource();
                retVal = jedis.srem(key, value);
            }
            return retVal;
        } catch (Exception ex) {
            log.error(ex.getMessage());
        } finally {
            if (!this.isCluster && jedis != null) {
                jedis.close();
            }
        }
        return 0;
    }

    private TreeSet<String> getClusterKeys(String pattern) {
        if (this.isCluster) {
            return null;
        } else {
            TreeSet<String> keys = new TreeSet();
            Map<String, JedisPool> clusterNodes = this.jedisCluster.getClusterNodes();
            Iterator var4 = clusterNodes.keySet().iterator();

            while (var4.hasNext()) {
                String k = (String) var4.next();
                JedisPool jp = clusterNodes.get(k);
                Jedis connection = jp.getResource();

                try {
                    keys.addAll(connection.keys(pattern));
                } catch (Exception var12) {
                    log.error(var12.getMessage());
                } finally {
                    connection.close();
                }
            }

            return keys;
        }
    }


    public void delKeys(String keyPrefix, String pattern) {
        if (!StringUtils.isEmpty(keyPrefix) || !StringUtils.isEmpty(pattern)) {
            String finalPattern;
            if (!StringUtils.isEmpty(keyPrefix)) {
                finalPattern = keyPrefix.concat(pattern);
            } else {
                finalPattern = pattern;
            }

            Jedis jedis = null;

            try {
                Object keySet;
                if (this.isCluster) {
                    keySet = this.getClusterKeys(finalPattern);
                } else {
                    jedis = this.jedisPool.getResource();
                    keySet = jedis.keys(finalPattern);
                }

                if (!CollectionUtils.isEmpty((Collection) keySet)) {
                    Iterator var6 = ((Set) keySet).iterator();

                    while (var6.hasNext()) {
                        String key = (String) var6.next();
                        if (this.isCluster) {
                            this.jedisCluster.del(key);
                        } else if (jedis != null) {
                            jedis.del(key);
                        }
                    }

                    return;
                }
            } catch (Exception var11) {
                log.error(var11.getMessage());
                return;
            } finally {
                if (!this.isCluster && jedis != null) {
                    jedis.close();
                }

            }
        }
    }

    public String combineKeys(String... keys) {
        if (keys != null && keys.length != 0) {
            String retVal = keys[0];

            for (int i = 1; i < keys.length; ++i) {
                if (retVal.endsWith("_")) {
                    retVal = retVal.concat(keys[i]);
                } else {
                    retVal = retVal.concat("_").concat(keys[i]);
                }
            }

            return retVal;
        } else {
            return null;
        }
    }

    public String combineValues(String... values) {
        if (values != null && values.length != 0) {
            String retVal = values[0];

            for (int i = 1; i < values.length; ++i) {
                if (retVal.endsWith(",")) {
                    retVal = retVal.concat(values[i]);
                } else {
                    retVal = retVal.concat(",").concat(values[i]);
                }
            }

            return retVal;
        } else {
            return null;
        }
    }

    public String lPush(String key, Integer expireSeconds, String... values) {
        if (values != null && values.length != 0) {
            Jedis jedis = null;

            try {
                if (this.isCluster) {
                    this.jedisCluster.lpush(key, values);
                    if (expireSeconds != null) {
                        this.jedisCluster.expire(key, expireSeconds);
                    }
                } else {
                    jedis = this.jedisPool.getResource();
                    jedis.lpush(key, values);
                    if (expireSeconds != null) {
                        jedis.expire(key, expireSeconds);
                    }
                }
            } catch (Exception ex) {
                log.error(ex.getMessage());
            } finally {
                if (!this.isCluster && jedis != null) {
                    jedis.close();
                }
            }
        }
        return null;
    }

    public Long rpush(String key, String value) {
        Jedis jedis = null;

        try {
            if (!this.isCluster) {
                jedis = this.jedisPool.getResource();
                return jedis.rpush(key, value);
            } else {
                return this.jedisCluster.rpush(key, value);
            }
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return null;
        } finally {
            if (!this.isCluster && jedis != null) {
                jedis.close();
            }
        }
    }

    public String lindex(String key, Integer index) {
        Jedis jedis = null;

        try {
            if (!this.isCluster) {
                jedis = this.jedisPool.getResource();
                return jedis.lindex(key, index);
            } else {
                return this.jedisCluster.lindex(key, index);
            }
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return null;
        } finally {
            if (!this.isCluster && jedis != null) {
                jedis.close();
            }
        }
    }

    public List<String> lrange(String key, long start, long end) {
        Jedis jedis = null;

        try {
            if (!this.isCluster) {
                jedis = this.jedisPool.getResource();
                return jedis.lrange(key, start, end);
            } else {
                return this.jedisCluster.lrange(key, start, end);
            }
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return null;
        } finally {
            if (!this.isCluster && jedis != null) {
                jedis.close();
            }
        }
    }

    public Long lrem(String key, long count, String value) {
        Jedis jedis = null;

        try {
            if (!this.isCluster) {
                jedis = this.jedisPool.getResource();
                return jedis.lrem(key, count, value);
            } else {
                return this.jedisCluster.lrem(key, count, value);
            }
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return null;
        } finally {
            if (!this.isCluster && jedis != null) {
                jedis.close();
            }
        }
    }

    public String lPop(String key) {
        Jedis jedis = null;

        try {
            if (!this.isCluster) {
                jedis = this.jedisPool.getResource();
                return jedis.lpop(key);
            } else {
                return this.jedisCluster.lpop(key);
            }
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return null;
        } finally {
            if (!this.isCluster && jedis != null) {
                jedis.close();
            }
        }
    }

    public String rPop(String key) {
        Jedis jedis = null;

        try {
            if (!this.isCluster) {
                jedis = this.jedisPool.getResource();
                return jedis.rpop(key);
            } else {
                return this.jedisCluster.rpop(key);
            }
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return null;
        } finally {
            if (!this.isCluster && jedis != null) {
                jedis.close();
            }
        }
    }

    public long lLen(String key) {
        Jedis jedis = null;

        try {
            if (!this.isCluster) {
                jedis = this.jedisPool.getResource();
                return jedis.llen(key);
            } else {
                return this.jedisCluster.llen(key);
            }
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return 0;
        } finally {
            if (!this.isCluster && jedis != null) {
                jedis.close();
            }
        }
    }

    /**
     * 将哈希表 key 中的域 field 的值设为 value 。
     * 如果 key 不存在，一个新的哈希表被创建并进行 HSET 操作。
     * 如果域 field 已经存在于哈希表中，旧值将被覆盖。
     *
     * @param key
     * @param field
     * @param value
     * @return
     */
    public Long hset(String key, String field, String value) {
        Jedis jedis = null;

        try {
            if (!this.isCluster) {
                jedis = this.jedisPool.getResource();
                return jedis.hset(key, field, value);
            } else {
                return this.jedisCluster.hset(key, field, value);
            }
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return null;
        } finally {
            if (!this.isCluster && jedis != null) {
                jedis.close();
            }
        }
    }

    /**
     * 返回哈希表 key 中给定域 field 的值。
     *
     * @param key
     * @param field
     * @return
     */
    public String hget(String key, String field) {
        Jedis jedis = null;

        try {
            if (!this.isCluster) {
                jedis = this.jedisPool.getResource();
                return jedis.hget(key, field);
            } else {
                return this.jedisCluster.hget(key, field);
            }
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return null;
        } finally {
            if (!this.isCluster && jedis != null) {
                jedis.close();
            }
        }
    }

    /**
     * 返回哈希表中所有数据
     *
     * @param key
     * @return
     */
    public Map<String, String> hgetAll(String key) {
        Jedis jedis = null;

        try {
            if (!this.isCluster) {
                jedis = this.jedisPool.getResource();
                return jedis.hgetAll(key);
            } else {
                return this.jedisCluster.hgetAll(key);
            }
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return null;
        } finally {
            if (!this.isCluster && jedis != null) {
                jedis.close();
            }
        }
    }


    /**
     * 返回哈希表中的所有key
     *
     * @param key
     * @return
     */
    public Set<String> hKeys(String key) {
        Jedis jedis = null;

        try {
            if (!this.isCluster) {
                jedis = this.jedisPool.getResource();
                return jedis.hkeys(key);
            } else {
                return this.jedisCluster.hkeys(key);
            }
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return null;
        } finally {
            if (!this.isCluster && jedis != null) {
                jedis.close();
            }
        }
    }

    /**
     * 同时将多个 field-value (域-值)对设置到哈希表 key 中。
     * 此命令会覆盖哈希表中已存在的域。
     * 如果 key 不存在，一个空哈希表被创建并执行 HMSET 操作。
     *
     * @param key
     * @param value
     * @return
     */
    public String hmset(String key, Map<String, String> value) {
        Jedis jedis = null;

        try {
            if (!this.isCluster) {
                jedis = this.jedisPool.getResource();
                return jedis.hmset(key, value);
            } else {
                return this.jedisCluster.hmset(key, value);
            }
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return null;
        } finally {
            if (!this.isCluster && jedis != null) {
                jedis.close();
            }
        }
    }

    /**
     * 将哈希表 key 中的域 field 的值设置为 value ，当且仅当域 field 不存在。
     * 若域 field 已经存在，该操作无效。
     * 如果 key 不存在，一个新哈希表被创建并执行 HSETNX 命令。
     *
     * @param key
     * @param value
     * @return
     */
    public Long hsetnx(String key, String field, String value) {
        Jedis jedis = null;

        try {
            if (!this.isCluster) {
                jedis = this.jedisPool.getResource();
                return jedis.hsetnx(key, field, value);
            } else {
                return this.jedisCluster.hsetnx(key, field, value);
            }
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return null;
        } finally {
            if (!this.isCluster && jedis != null) {
                jedis.close();
            }
        }
    }

    /**
     * 将哈希表 key 中的域 field 的值设置为 value ，当且仅当域 field 不存在。
     * 若域 field 已经存在，该操作无效。
     * 如果 key 不存在，一个新哈希表被创建并执行 HSETNX 命令。
     *
     * @param key
     * @return
     */
    public Long hlen(String key) {
        Jedis jedis = null;

        try {
            if (!this.isCluster) {
                jedis = this.jedisPool.getResource();
                return jedis.hlen(key);
            } else {
                return this.jedisCluster.hlen(key);
            }
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return null;
        } finally {
            if (!this.isCluster && jedis != null) {
                jedis.close();
            }
        }
    }

    /**
     * 返回哈希表 key 中，一个或多个给定域的值。
     * 如果给定的域不存在于哈希表，那么返回一个 nil 值。
     * 因为不存在的 key 被当作一个空哈希表来处理，所以对一个不存在的 key 进行 HMGET 操作将返回一个只带有 nil 值的表。
     *
     * @param key
     * @param field
     * @return
     */
    public List<String> hmget(String key, String field) {
        Jedis jedis = null;

        try {
            if (!this.isCluster) {
                jedis = this.jedisPool.getResource();
                return jedis.hmget(key, field);
            } else {
                return this.jedisCluster.hmget(key, field);
            }
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return null;
        } finally {
            if (!this.isCluster && jedis != null) {
                jedis.close();
            }
        }
    }

    /**
     * 查看哈希表 key 中，给定域 field 是否存在。F
     *
     * @param key
     * @param field
     * @return
     */
    public boolean hexists(String key, String field) {
        Jedis jedis = null;

        try {
            if (!this.isCluster) {
                jedis = this.jedisPool.getResource();
                return jedis.hexists(key, field);
            } else {
                return this.jedisCluster.hexists(key, field);
            }
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return false;
        } finally {
            if (!this.isCluster && jedis != null) {
                jedis.close();
            }
        }
    }

    /**
     * 删除哈希表 key 中的一个或多个指定域，不存在的域将被忽略。
     *
     * @param key
     * @param field
     * @return
     */
    public Long hdel(String key, String field) {
        Jedis jedis = null;

        try {
            if (!this.isCluster) {
                jedis = this.jedisPool.getResource();
                return jedis.hdel(key, field);
            } else {
                return this.jedisCluster.hdel(key, field);
            }
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return 0L;
        } finally {
            if (!this.isCluster && jedis != null) {
                jedis.close();
            }
        }
    }

    /**
     * 为哈希表 key 中的域 field 的值加上增量 increment 。
     * 增量也可以为负数，相当于对给定域进行减法操作。
     * 如果 key 不存在，一个新的哈希表被创建并执行 HINCRBY 命令。
     * 如果域 field 不存在，那么在执行命令前，域的值被初始化为 0 。
     * 对一个储存字符串值的域 field 执行 HINCRBY 命令将造成一个错误。
     * 本操作的值被限制在 64 位(bit)有符号数字表示之内。
     *
     * @param key
     * @param field
     * @param value
     * @return
     */
    public Long hincrBy(String key, String field, Long value) {
        Jedis jedis = null;

        try {
            if (!this.isCluster) {
                jedis = this.jedisPool.getResource();
                return jedis.hincrBy(key, field, value);
            } else {
                return this.jedisCluster.hincrBy(key, field, value);
            }
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return 0L;
        } finally {
            if (!this.isCluster && jedis != null) {
                jedis.close();
            }
        }
    }
}
