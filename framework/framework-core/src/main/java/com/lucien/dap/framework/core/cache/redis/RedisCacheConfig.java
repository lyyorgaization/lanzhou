package com.lucien.dap.framework.core.cache.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.LinkedHashSet;
import java.util.Set;

@Component
public class RedisCacheConfig {
	@Value("${jedis.cluster}")
	private Boolean isCluster;

	@Bean
	@Autowired
	public JedisPool jedisPool(@Qualifier("jedis.pool.config") JedisPoolConfig config, @Value("${jedis.pool.host}") String host, @Value("${jedis.pool.port}") int port, @Value("${jedis.pool.timeout}") int timeout, @Value("${jedis.password}") String password) {
		if (password.length() == 0) {
			password = null;
		}
		JedisPool jedisPool = new JedisPool(config, host, port, timeout, password);
		return jedisPool;
	}

	@Bean(name = {"jedis.pool.config"})
	@Autowired
	public JedisPoolConfig jedisPoolConfig(@Value("${jedis.config.maxTotal}") int maxTotal, @Value("${jedis.pool.config.maxIdle}") int maxIdle, @Value("${jedis.pool.config.maxWaitMillis}") int maxWaitMillis) {
		JedisPoolConfig config = new JedisPoolConfig();
		config.setMaxTotal(maxTotal);
		config.setMaxIdle(maxIdle);
		config.setMaxWaitMillis(maxWaitMillis);
		return config;
	}

	@Bean
	@Autowired
	public JedisCluster jedisCluster(
			@Qualifier("jedis.pool.config") JedisPoolConfig config,
			@Value("${jedis.pool.timeout}") int timeout,
			@Value("${jedis.cluster.socketTimeout}") int socketTimeout,
			@Value("${jedis.cluster.hosts}") String hosts,
			@Value("${jedis.pool.password}") String password) {
		if (StringUtils.isEmpty(hosts) || !isCluster) {
			return null;
		} else {
			Set<HostAndPort> hostAndPortList = new LinkedHashSet();
			String[] hostPorts = hosts.split(";");
			String[] var8 = hostPorts;
			int var9 = hostPorts.length;

			for (int var10 = 0; var10 < var9; ++var10) {
				String item = var8[var10];
				if (!StringUtils.isEmpty(item)) {
					String[] tmp = item.split(":");
					HostAndPort hostAndPort = new HostAndPort(tmp[0], Integer.valueOf(tmp[1]));
					hostAndPortList.add(hostAndPort);
				}
			}
			JedisCluster jedisCluster = new JedisCluster(hostAndPortList, timeout, socketTimeout, 5, password, config);
			return jedisCluster;

		}
	}
}
