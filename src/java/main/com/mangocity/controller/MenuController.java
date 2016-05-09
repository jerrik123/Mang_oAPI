package com.mangocity.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.mangocity.book.ErrorConstant;
import com.mangocity.book.SqlMapper;
import com.mangocity.ce.bean.EngineBean;
import com.mangocity.ce.exception.ExceptionAbstract;
import com.mangocity.util.CommonUtils;
import com.mangocity.util.ErrorUtils;
import com.mangocity.util.SqlUtil;

/**
 * 左边菜单管理
 * @author longshu.chen
 *
 */
public class MenuController {

	/**
	 * 添加接口
	 * @param eb
	 * @return
	 * @throws ExceptionAbstract
	 */
	public EngineBean addInterface(EngineBean eb) throws ExceptionAbstract{
		String method="";
		String methodName="";
		String withs="";
		Iterator<Entry<String, Object>> iter = eb.getHeadMap().entrySet().iterator();
		Map.Entry entry;
		Object key;
		while(iter.hasNext()){
			entry = (Map.Entry) iter.next();
			key= entry.getKey();
			if (((String) key).contains("method")){
				method=(String) entry.getValue();
			}
			if (((String) key).contains("methodname")){
				methodName=(String) entry.getValue();
			}
			if (((String) key).contains("withs")){
				withs=(String) entry.getValue();
			}
			
		}
		if(CommonUtils.isBlank(method)){
			return ErrorUtils.error(eb,ErrorConstant.ERROR_PARAM_NULL_10000, "method不能为空");
		}
		if(CommonUtils.isBlank(methodName)){
			return ErrorUtils.error(eb,ErrorConstant.ERROR_PARAM_NULL_10000, "methodName不能为空");
		}
		eb.setHead("method", method);
		eb.setHead("methodname", methodName);
		eb.setHead("withs", withs);
		int result=(int) SqlUtil.getInstance().selectOneString("queryInterfaceByMethodAndName", eb.getHeadMap());
		if(result>0){
			return ErrorUtils.error(eb,ErrorConstant.Inteface.ERROR_INTERFACE_EXIST, "method和methodName已经存在");
		}else{
			//int aa= SqlUtil.getInstance().insertOne("addInterface", eb.getHeadMap());
			List<SqlMapper>  sqlMapperList=new ArrayList<SqlMapper>();
			SqlMapper insertInterface = SqlMapper.getInstance().sqlId("addInterface").sqlType(SqlMapper.TransacOperation.INSERT).paramMap(eb.getHeadMap()).build();
			sqlMapperList.add(insertInterface);
			SqlMapper insertContent=SqlMapper.getInstance().sqlId("addContent").sqlType(SqlMapper.TransacOperation.INSERT).paramMap(eb.getHeadMap()).build();
			sqlMapperList.add(insertContent);
			int inresult=SqlUtil.getInstance().transactionAll(sqlMapperList);
			if(inresult>0){
				return eb;
			}else{
				return ErrorUtils.error(eb,ErrorConstant.Inteface.ERROR_INTERFACE_INSERT_FAIL, "插入接口失败");
			}
		}
		
	}
}
