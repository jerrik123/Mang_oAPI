package com.mangocity.sqlmapper;

import java.util.List;
import java.util.Map;

public interface apiMapper {
	
	/**
	 * 根据appId查询api左菜单
	 * @param headMap
	 * @return
	 */
	public List<Map<String,Object>> queryLeftMenuTreeByAppId(Map headMap);
	public List<Map<String,Object>> queryAllMenuTree(Map headMap);
	/**
	 * 加载api详细信息
	 * @param headMap
	 * @return
	 */
	public Map<String,Object> loadContent(Map headMap);
	public Map<String,Object> AllContent(Map headMap);
	/**
	 * 查询所有需要缓存的数据
	 * @param headMap
	 * @return
	 */
	public List<Map<String,Object>> queryAllCachedData();
	public int addContent(Map<String,Object> headMap);
	
	/**
	 * 通过method和methodName查询接口
	 * @param headMap
	 * @return
	 */
	public Map<String,Object> queryInterfaceByMethodAndName(Map<String,Object> headMap);
	/**
	 * 添加接口
	 * @param headMap
	 * @return
	 */
	public int addInterface(Map<String,Object> headMap);
	
}