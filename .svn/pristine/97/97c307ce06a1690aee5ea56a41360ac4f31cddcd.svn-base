package com.zhixingbus.server.redis;

import org.apache.commons.lang3.SerializationUtils;

import redis.clients.jedis.Jedis;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class RedisCache {
	private String cacheName;
	private Jedis cache;

	public Object get(Object key,String host) {
		if (null == key)
			return null;
		byte[] b = cache
				.get((cacheName + ":" + String.valueOf(key)).getBytes());
		RedisManager.getJedisPool(host).returnResource(cache);
		return b == null ? null : SerializationUtils.deserialize(b);
	}

	public void put(Object key, Object value,String host) {
		cache.set(
				(cacheName + ":" + String.valueOf(key)).getBytes(),
				value == null ? null : SerializationUtils
						.serialize((Serializable) value));
		RedisManager.getJedisPool(host).returnResource(cache);
	}
	
	public void putEx(Object key, Object value, int seconds,String host) {
		cache.set(
				(cacheName + ":" + String.valueOf(key)).getBytes(),
				value == null ? null : SerializationUtils
						.serialize((Serializable) value));
		
		cache.setex((cacheName + ":" + String.valueOf(key)).getBytes(), seconds, value == null ? null : SerializationUtils
				.serialize((Serializable) value));
		RedisManager.getJedisPool(host).returnResource(cache);
	}

	@SuppressWarnings("rawtypes")
	public List getKeys(String host) {
		List<Object> keys = new ArrayList<Object>();
		Set<byte[]> list = cache.keys(String.valueOf("*").getBytes());
		for (byte[] bs : list) {
			keys.add(bs == null ? null : SerializationUtils.deserialize(bs));
		}
		RedisManager.getJedisPool(host).returnResource(cache);
		return keys;
	}

	@SuppressWarnings("rawtypes")
	public List getObjects(String host) {
		List<Object> objects = new ArrayList<Object>();
		Set<String> set = cache.keys(cacheName + "*");
		for (String key : set) {
			byte[] b = cache.get((String.valueOf(key)).getBytes());
			if (b != null) {
				Object object = SerializationUtils.deserialize(b);
				objects.add(object);
			}
		}
		RedisManager.getJedisPool(host).returnResource(cache);
		return objects;
	}

	public void remove(Object key,String host) {
		cache.expire((cacheName + ":" + String.valueOf(key)).getBytes(), 0);
		RedisManager.getJedisPool(host).returnResource(cache);
	}

	public void removeAllS(String host) {
		@SuppressWarnings("rawtypes")
		List keys = this.getKeys(host);
		for (Object key : keys) {
			this.remove(key,host);
		}
	}
	
	public void removeAll(String cacheName,String host) {
		Set<String> keys = cache.keys(cacheName + "*");
		Iterator<String> it = keys.iterator();
		while(it.hasNext()){
			String key = it.next();
			cache.del(key);
		}
	}

	public RedisCache(String region, Jedis cache) {
		this.cache = cache;
		this.cacheName = region;
	}

	public Jedis getCache() {
		return cache;
	}

	public void setCache(Jedis cache) {
		this.cache = cache;
	}

	public String getCacheName() {
		return cacheName;
	}

	public void setCacheName(String cacheName) {
		this.cacheName = cacheName;
	}
}
