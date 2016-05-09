package com.mangocity.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import org.apache.log4j.Logger;

import com.mangocity.ce.bean.EngineBean;
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
public class ApiController {
	private static final Logger log = Logger.getLogger(ApiController.class);

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public EngineBean loadtree(EngineBean eb) throws ExceptionAbstract {
		Map<String,Object> map = New.map();
		map.put("appId", eb.getHead("app"));
		List s  = SqlUtil.getInstance().selectList("queryLeftMenuTreeByAppId", map);
		List s1 = new ArrayList();
		List s2 = new ArrayList();
		List s3 = new ArrayList();
		List s4 = new ArrayList();
		for (int i = 0; i < s.size(); i++) {
			if ("会员".equals(((Map) s.get(i)).get("withs"))) {
				s1.add(s.get(i));
			} else if ("积分".equals(((Map) s.get(i)).get("withs"))) {
				s2.add(s.get(i));
			} else if ("现金账户".equals(((Map) s.get(i)).get("withs"))) {
				s3.add(s.get(i));
			} else if ("代金券".equals(((Map) s.get(i)).get("withs"))) {
				s4.add(s.get(i));
			}
		}
		List all = new ArrayList();
		all.add(s1);
		all.add(s2);
		all.add(s3);
		all.add(s4);
		Map outMap  = New.map();
		outMap.put("result", all);
		eb.setBodyMap(outMap);
		return eb;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public EngineBean loadcontent(EngineBean eb) throws ExceptionAbstract {
		Map m = eb.getHeadMap();
		Set<String> set = new CopyOnWriteArraySet<String>(m.keySet());
		for (Object key : set) {
			if (((String) key).contains("method")) {
				m.put("method", m.get(key));
			}
			if (((String) key).contains("appId")) {
				m.put("appId", m.get(key));
			}
			System.out.println("key= " + key + " and value= " + m.get(key));
		}
		Map<String,Object> map = New.map();
		map.put("appId", eb.getHead("appId"));
		map.put("method", eb.getHead("method"));
		Map s = SqlUtil.getInstance().selectOne("loadContent", map);
		String in = (String) s.get("injsonexplain");
		String[] inlist = in.split("@");
		List listin = new ArrayList();
		for (String string : inlist) {
			String[] inlistinfo = string.split(":");
			Map mapin = new HashMap();
			for (int i = 0; i < inlistinfo.length; i++) {
				if (i == 0)
					mapin.put("cs", inlistinfo[i]);
				else if (i == 1)
					mapin.put("sfbx", inlistinfo[i]);
				else if (i == 2)
					mapin.put("sm", inlistinfo[i]);
			}

			listin.add(mapin);
		}
		s.put("injsonexplain", listin);
		// out
		String out = (String) s.get("outjsonexplain");
		String[] outlist = out.split("@");
		List listout = new ArrayList();
		for (String string : outlist) {
			String[] outlistinfo = string.split(":");
			Map mapout = new HashMap();
			for (int i = 0; i < outlistinfo.length; i++) {
				if (i == 0)
					mapout.put("cs", outlistinfo[i]);
				else if (i == 1)
					mapout.put("sm", outlistinfo[i]);
			}
			listout.add(mapout);
		}
		s.put("outjsonexplain", listout);

		Map outMap  = New.map();
		outMap.put("result", s);
		eb.setBodyMap(outMap);
		return eb;
	}
	public EngineBean AllContent(EngineBean eb) throws ExceptionAbstract {
		Map m = eb.getHeadMap();
		Set<String> set = new CopyOnWriteArraySet<String>(m.keySet());
		for (Object key : set) {
			if (((String) key).contains("method")) {
				m.put("method", m.get(key));
			}
			if (((String) key).contains("appId")) {
				m.put("appId", m.get(key));
			}
			System.out.println("key= " + key + " and value= " + m.get(key));
		}
		Map<String,Object> map = New.map();
		map.put("appId", eb.getHead("appId"));
		map.put("method", eb.getHead("method"));
		Map s = SqlUtil.getInstance().selectOne("AllContent", map);
		
		String in = (String) s.get("injsonexplain");
		if(null !=in){
		String[] inlist = in.split("@");
		List listin = new ArrayList();
		for (String string : inlist) {
			String[] inlistinfo = string.split(":");
			Map mapin = new HashMap();
			for (int i = 0; i < inlistinfo.length; i++) {
				if (i == 0)
					mapin.put("cs", inlistinfo[i]);
				else if (i == 1)
					mapin.put("sfbx", inlistinfo[i]);
				else if (i == 2)
					mapin.put("sm", inlistinfo[i]);
			}

			listin.add(mapin);
		}
		s.put("injsonexplain", listin);
		}
		// out
		String out = (String) s.get("outjsonexplain");
		if(null !=out){
		String[] outlist = out.split("@");
		List listout = new ArrayList();
		for (String string : outlist) {
			String[] outlistinfo = string.split(":");
			Map mapout = new HashMap();
			for (int i = 0; i < outlistinfo.length; i++) {
				if (i == 0)
					mapout.put("cs", outlistinfo[i]);
				else if (i == 1)
					mapout.put("sm", outlistinfo[i]);
			}
			listout.add(mapout);
		}
		s.put("outjsonexplain", listout);
		}
		Map outMap  = New.map();
		outMap.put("result", s);
		eb.setBodyMap(outMap);
		return eb;
	}
	public EngineBean addcontent(EngineBean eb) throws ExceptionAbstract {

		String method = "demo";
		String url = "http://domain:port/mbrse/11";
		String injson = "{}";
		String injsonexplain = "";
		String outjson = "{}";
		String outjsonexplain = "";
		Map<String,Object> map = New.map();
		
		Iterator iter = eb.getHeadMap().entrySet().iterator();
		Map injMap = New.map();
		Map outjMap = New.map();
		while (iter.hasNext()) {
			Map.Entry entry = (Map.Entry) iter.next();
			Object key = entry.getKey();
			if (((String) key).contains("method")) {
				map.put("method", entry.getValue());
			}
			if (((String) key).contains("_injson")) {
				map.put("injson", entry.getValue());
			}
			if (((String) key).contains("_outjson")) {
				map.put("outjson", entry.getValue());
			}
			if (((String) key).contains("_a_")) {
				injMap.put(key,  entry.getValue());
			}
			if (((String) key).contains("_d_")) {
				outjMap.put(key, entry.getValue());
			}
			//Object val = entry.getValue();
		}
		Iterator iterinjMap = injMap.entrySet().iterator();
		while (iterinjMap.hasNext()) {
			Map.Entry entry = (Map.Entry) iterinjMap.next();
			//injsonexplain += entry.getKey()+":"+entry.getValue()+"@";
			String key = (String) entry.getKey();
			injsonexplain += entry.getValue()+":";
			String b = key.replace("_a_", "_b_");
			injsonexplain += eb.getHead(b)+":";
			String c = b.replace("_b_", "_c_");
			injsonexplain += eb.getHead(c)+"@";
		}
		Iterator iteroutjMap = outjMap.entrySet().iterator();
		while (iteroutjMap.hasNext()) {
			Map.Entry entry = (Map.Entry) iteroutjMap.next();
			//outjsonexplain += entry.getKey()+":"+entry.getValue()+"@";
			String key = (String) entry.getKey();
			outjsonexplain += entry.getValue()+":";
			String b = key.replace("_d_", "_e_");
			outjsonexplain += eb.getHead(b)+"@";
			
		}
		//map.put("method", method);
		map.put("url", url);
		//map.put("injson", injson);
		map.put("injsonexplain", injsonexplain);
		//map.put("outjson", outjson);
		map.put("outjsonexplain", outjsonexplain);
		int count = SqlUtil.getInstance().insertOne("addContent", map);
		eb.setBody("count", count);
		return eb;
	}
	
	public EngineBean alltree(EngineBean eb) throws ExceptionAbstract {
		Map<String,Object> map = New.map();
		map.put("appId", eb.getHead("app"));
		List s  = SqlUtil.getInstance().selectList("queryAllMenuTree", map);
		List s1 = new ArrayList();
		List s2 = new ArrayList();
		List s3 = new ArrayList();
		List s4 = new ArrayList();
		for (int i = 0; i < s.size(); i++) {
			if ("会员".equals(((Map) s.get(i)).get("withs"))) {
				s1.add(s.get(i));
			} else if ("积分".equals(((Map) s.get(i)).get("withs"))) {
				s2.add(s.get(i));
			} else if ("现金账户".equals(((Map) s.get(i)).get("withs"))) {
				s3.add(s.get(i));
			} else if ("代金券".equals(((Map) s.get(i)).get("withs"))) {
				s4.add(s.get(i));
			}
		}
		List all = new ArrayList();
		all.add(s1);
		all.add(s2);
		all.add(s3);
		all.add(s4);
		Map outMap  = New.map();
		outMap.put("result", all);
		eb.setBodyMap(outMap);
		return eb;
	}
}
