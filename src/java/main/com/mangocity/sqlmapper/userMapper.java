package com.mangocity.sqlmapper;

import java.util.List;
import java.util.Map;

public interface userMapper {
	
	/**
	 * 用户登录
	 * @param headMap
	 * @return
	 */
	public Map<String,Object> userLogin(Map<String,Object> headMap);
	
}