package com.zhixingbus.server.redis;

import com.jfinal.plugin.IPlugin;

public class RedisPlugin implements IPlugin {
	private static RedisManager redisManager;

	public RedisPlugin(String prop) {
		RedisPlugin.redisManager = new RedisManager(prop);
	}
	@Override
	public boolean start() {
		redisManager.init();
        RedisKit.init(redisManager);
		return true;
	}

	@Override
	public boolean stop() {
		redisManager.destroy();
		return true;
	}

}
