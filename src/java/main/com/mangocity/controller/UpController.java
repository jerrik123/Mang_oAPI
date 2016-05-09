package com.mangocity.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import org.apache.log4j.Logger;

import com.mangocity.cache.CacheReload;
import com.mangocity.ce.bean.EngineBean;
import com.mangocity.ce.book.ErrorBook;
import com.mangocity.ce.exception.BusinessException;
import com.mangocity.ce.exception.ExceptionAbstract;
import com.mangocity.ce.util.New;
import com.mangocity.util.SqlUtil;

/**
 * 
 * @ClassName: ApiController
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author Syungen
 * @date 2015年9月10日 下午6:20:46
 *
 */
public class UpController {
	private static final Logger log = Logger.getLogger(UpController.class);
	public EngineBean uppip(EngineBean eb) throws ExceptionAbstract {
		return upkey(eb);
	}
	public EngineBean uptip(EngineBean eb) throws ExceptionAbstract {
		return upkey(eb);
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public EngineBean upkey(EngineBean eb) throws ExceptionAbstract {
		Map<String,Object> map = New.map();
		map.put("appId", eb.getHead("app"));
		int s  = SqlUtil.getInstance().updateOne("upKeyByAppId", eb.getHeadMap());
		if(s<1){
			throw new BusinessException(this, ErrorBook.PARAM_ESS_ERROR, "更新失败");
		}
		//重新加載緩存
		CacheReload cacheReload = CacheReload.instance();
		cacheReload.cleanCache();
		cacheReload.importNewCache();
		Map outMap  = New.map();
		outMap.put("result", s);
		eb.setBodyMap(outMap);
		return eb;
	}
	public EngineBean loadinfo(EngineBean eb) throws ExceptionAbstract {
		Map<String,Object> map = New.map();
		map.put("appId", eb.getHead("app"));
		Map<String,Object> loadinfoMap = SqlUtil.getInstance().selectOne("loadInfoByAppId", eb.getHeadMap());
		if(loadinfoMap==null){
			throw new BusinessException(this, ErrorBook.PARAM_ESS_ERROR, "获取配置信息异常");
		}
		Map outMap  = New.map();
		outMap.put("result", loadinfoMap);
		eb.setBodyMap(outMap);
		return eb;
	}
}
