package com.zhixingbus.server.redis;

import java.util.List;

import com.jfinal.plugin.ehcache.IDataLoader;

public class RedisKit {
	private static volatile RedisManager redisManager;

	static void init(RedisManager redisManager) {
		RedisKit.redisManager = redisManager;
	}

    public static RedisManager getRedisManager() {
        return redisManager;
    }

    public static void put(String cacheName, Object key, Object value,String host) {
    	if(value == null) return;
        RedisManager.getJedisCache(cacheName, redisManager,host).put(key,value,host);
    }
    
    public static void putEx(String cacheName, Object key, Object value, int seconds,String host) {
    	if(value == null) return;
        RedisManager.getJedisCache(cacheName, redisManager,host).putEx(key,value, seconds,host);
    }

    @SuppressWarnings("unchecked")
    public static <T> T get(String cacheName, Object key,String host) {
    	RedisCache rCache = RedisManager.getJedisCache(cacheName, redisManager,host);
    	if(rCache == null)
    		return null;
    	
    	Object obj = rCache.get(key,host);
        return obj == null ? null : (T)obj;
    }

    @SuppressWarnings("rawtypes")
	public static List getKeys(String cacheName,String host) {
        return RedisManager.getJedisCache(cacheName, redisManager,host).getObjects(host);
    }

    public static void remove(String cacheName, Object key,String host) {
        RedisManager.getJedisCache(cacheName, redisManager,host).remove(key,host);
    }

    public static void removeAll(String cacheName,String host) {
        RedisManager.getJedisCache(cacheName, redisManager,host).removeAll(cacheName,host);
    }
    
    public static void removeAllByName(String cacheName,String host) {
        RedisManager.getJedisCache(cacheName, redisManager,host).removeAll(cacheName,host);
    }

    @SuppressWarnings("unchecked")
    public static <T> T get(String cacheName, Object key, IDataLoader dataLoader,String host) {
        Object data = get(cacheName, key,host);
        if (data == null) {
            data = dataLoader.load();
            put(cacheName, key, data,host);
        }
        return (T)data;
    }

    @SuppressWarnings("unchecked")
    public static <T> T get(String cacheName, Object key, Class<? extends IDataLoader> dataLoaderClass,String host) {
        Object data = get(cacheName, key,host);
        if (data == null) {
            try {
                IDataLoader dataLoader = dataLoaderClass.newInstance();
                data = dataLoader.load();
                put(cacheName, key, data,host);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return (T)data;
    }



}
