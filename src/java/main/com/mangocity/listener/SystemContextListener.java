package com.mangocity.listener;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;

import com.mangocity.cache.CacheReload;
import com.mangocity.ce.exception.ExceptionAbstract;
import com.mangocity.ce.util.CommonUtils;
import com.mangocity.ce.util.RedisUtils;
import com.mangocity.util.SafeUtil;
import com.mangocity.util.SqlUtil;

/**
 * 
 * @ClassName: SystemContextListener
 * @Description: 容器启动清除历史缓存,加载最新数据
 * @author Yangjie
 * @date 2015年11月20日 下午6:20:46
 */
public class SystemContextListener implements ServletContextListener {
	private static final String CACHE_PREFIXP= "adjustCode_P";
	private static final String CACHE_PREFIXT = "adjustCode_T";
	private static final Logger log = Logger
			.getLogger(SystemContextListener.class);

	@Override
	public void contextDestroyed(ServletContextEvent contextEvent) {
		log.debug("SystemContextListener destroy...");
	}

	@Override
	public void contextInitialized(ServletContextEvent contextEvent) {
		try {
			SqlUtil.getInstance().init();
		} catch (ExceptionAbstract e) {
			throw new RuntimeException("SqlUtil初始化错误...");
		}
		log.debug("SystemContextListener init...");
		CacheReload cacheReload = CacheReload.instance();
		cacheReload.cleanCache();
		cacheReload.importNewCache();
	}

}
