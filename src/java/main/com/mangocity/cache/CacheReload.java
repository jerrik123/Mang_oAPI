package com.mangocity.cache;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;

import com.mangocity.ce.bean.ActionBean;
import com.mangocity.ce.book.CacheBook;
import com.mangocity.ce.book.FileBook;
import com.mangocity.ce.cache.ApplicationManage;
import com.mangocity.ce.deploy.ConfigManage;
import com.mangocity.ce.deploy.DeployFileManage;
import com.mangocity.ce.exception.ExceptionAbstract;
import com.mangocity.ce.util.CommonUtils;
import com.mangocity.ce.util.RedisUtils;
import com.mangocity.util.SafeUtil;
import com.mangocity.util.SqlUtil;
import com.thoughtworks.xstream.XStream;

/**
 * 
 * @ClassName: CacheReload
 * @Description: (reload缓存)
 * @author 
 * @date 2015年6月18日 上午9:25:50
 *
 */
public class CacheReload {

	private static CacheReload uniqueInstance = null;
	
	private static final String CACHE_PREFIXP= "adjustCode_P";
	private static final String CACHE_PREFIXT = "adjustCode_T";
	
	private static final Logger log = Logger
			.getLogger(CacheReload.class);

	public static CacheReload instance() {
		if (uniqueInstance == null) {
			log.debug("new CacheReload() begin.....");
			uniqueInstance = new CacheReload();
			log.debug("new CacheReload() end .....");
		}
		return uniqueInstance;
	}

	public void importNewCache() {
		log.info("import new cache  begin...");
		try {
			List<Map<String,Object>> cacheList = SqlUtil.getInstance().selectList("queryAllCachedData", null);
			if(CommonUtils.isEmpty(cacheList) || cacheList.size()==0){
				log.info("没有需要缓存的数据...停止服务");
				throw new RuntimeException("没有需要缓存的数据...停止服务");
			}
			log.info("缓存的数据条数: " + cacheList.size());
			for (Map<String, Object> tMap : cacheList) {
				cache(tMap);//缓存tMap
			}
		} catch (ExceptionAbstract e) {
			throw new IllegalArgumentException("queryAllCachedData出错...");
		}
		log.info("import new cache stop...");
	}

	private void cache(Map<String, Object> tMap) {
		String key = String.valueOf(tMap.get("method"));
		//处理数据库中因为疏忽存入的空格字符
		String value = SafeUtil.MD5(SafeUtil.MD5(CommonUtils.trim(String.valueOf(tMap.get("app"))) +
				CommonUtils.trim(String.valueOf(tMap.get("method"))))
				+ CommonUtils.trim(String.valueOf(tMap.get("secret"))));
		String newKeyP = CACHE_PREFIXP + value;
		String newKeyT = CACHE_PREFIXT+ value;
		StringBuilder sb = new StringBuilder();
		sb.append(CommonUtils.trim(String.valueOf(tMap.get("app"))));
		sb.append("|");
		sb.append(CommonUtils.trim(String.valueOf(tMap.get("method"))));
		sb.append("|");
		StringBuilder sb1 = new StringBuilder(sb.toString());
		//RedisUtils.lpush(newKey, String.valueOf(tMap.get("app")));//redis lpush会自动删除key
		if(key.equals("updateMobileNo")){
			//RedisUtils.lpush(newKey, "1");
			RedisUtils.set(newKeyP,sb.append("1").append("|").append(CommonUtils.trim(String.valueOf(tMap.get("proip")))).toString());
			
			
			RedisUtils.set(newKeyT,sb1.append("1").append("|").append(CommonUtils.trim(String.valueOf(tMap.get("testip")))).toString());
			log.info("缓存key: " + newKeyP +"="+newKeyT+ " ,value: " + sb.toString() );
		}else{
			//RedisUtils.lpush(newKey, "0");
			RedisUtils.set(newKeyP,sb.append("0").append("|").append(CommonUtils.trim(String.valueOf(tMap.get("proip")))).toString());
			RedisUtils.set(newKeyT,sb1.append("0").append("|").append(CommonUtils.trim(String.valueOf(tMap.get("testip")))).toString());
			log.info("缓存key: " + newKeyP +"="+newKeyT+ " ,value: " + sb.toString() );
		}
	}

	public void cleanCache() {
		log.info("clean up redis adjustCode_* key");
		// 清除掉所有已adjustCode_开头的key
		Set<String> set = RedisUtils.keys("adjustCode_T*");
		for (String key : set) {
			RedisUtils.del(key);
		}
		log.info("clean over");
	}
}
