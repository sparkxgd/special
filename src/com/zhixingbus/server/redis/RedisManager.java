package com.zhixingbus.server.redis;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import com.jfinal.log.Logger;

public class RedisManager {
	public static Map<String, JedisPool> poolMap=new HashMap<String, JedisPool>();
	public static List<RedisBean> redisList=new ArrayList<RedisBean>();
    private String config = "redis.properties";
    private Properties properties;
    private final Logger logger = Logger.getLogger(getClass());
    public RedisManager(String config) {
    	this.config = config;
   	}
	public void init() {
		loadProperties();
		Enumeration<Object> enums = properties.keys();
	    while (enums.hasMoreElements()) {
	         String key = enums.nextElement() + "";
	         if (!key.endsWith("host") || !isEnableJob(key)) {
	                continue;
	            }
	         	String host = properties.get(key)+"";
	            String port = properties.getProperty(portKey(key));
	            String dbIndex = properties.getProperty(dbIndexKey(key));
	            String password = properties.getProperty(passwordKey(key));
	            String enable = properties.getProperty(enable(key));
	            RedisBean b=new RedisBean();
	            b.setHost(host);
	            b.setPort(Integer.valueOf(port));
	            b.setPassword(password);
	            b.setDbIndex(Integer.valueOf(dbIndex));
	            b.setEnable(Boolean.getBoolean(enable));
	            redisList.add(b);
	            
	    }
	    for(RedisBean bean:redisList){
			GenericObjectPoolConfig config = new GenericObjectPoolConfig();
			config.setMaxWaitMillis(2000);
			config.setMaxIdle(10);
			config.setMaxTotal(5000);
			config.setTestOnBorrow(true);
			JedisPool pool = new JedisPool(config, bean.getHost(), bean.getPort(), 10000, bean.getPassword(), bean.getDbIndex());
			poolMap.put(bean.getHost(), pool);
	    }

	}
    public static RedisCache getJedisCache(String cacheName, RedisManager redisManager,String redisName) {
        Jedis jedis = null;
        RedisCache cache = null;
        try {
            jedis = RedisManager.getJedisPool(redisName).getResource();
            cache = new RedisCache(cacheName, jedis);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cache;
    }


    public static void returnResource(Jedis jedis, RedisManager redisManager,String host) {
        if (redisManager == null || RedisManager.getJedisPool(host) == null || jedis == null) {
            return;
        }
        RedisManager.getJedisPool(host).returnResource(jedis);
    }

	public static JedisPool getJedisPool(String host) {
		return poolMap.get(host);
	}

	

	public void destroy() {
		for(String p:poolMap.keySet()){
			poolMap.get(p).destroy();
		}
	}
    private void loadProperties() {
        properties = new Properties();
        InputStream is = RedisManager.class.getClassLoader().getResourceAsStream(config);
        try {
            properties.load(is);
        } catch (IOException e) {
        	logger.error("redis", e);
        }
        logger.debug("------------load Propteries---------------");
        logger.debug(properties.toString());
        logger.debug("------------------------------------------");
    }
    private boolean isEnableJob(String enableKey) {
        Object enable = properties.get(enableKey);
        if (enable != null && "false".equalsIgnoreCase((enable + "").trim())) {
            return false;
        }
        return true;
    }
    private String enable(String key) {
        return key.substring(0, key.lastIndexOf("host")) + "enable";
    }
    private String portKey(String key) {
        return key.substring(0, key.lastIndexOf("host")) + "port";
    }
    private String dbIndexKey(String key) {
        return key.substring(0, key.lastIndexOf("host")) + "dbIndex";
    }
    private String passwordKey(String key) {
        return key.substring(0, key.lastIndexOf("host")) + "password";
    }
    
}
