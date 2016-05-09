package com.mangocity.sqlmapper;

import java.util.Map;

public interface upMapper {
	
	public int upKeyByAppId(Map<String,Object> headMap);
	public Map<String, Object>loadInfoByAppId(Map<String,Object> headMap);
}