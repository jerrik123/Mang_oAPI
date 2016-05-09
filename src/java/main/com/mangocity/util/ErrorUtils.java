/**
 * 
 */
package com.mangocity.util;

import org.apache.log4j.Logger;

import com.mangocity.ce.bean.EngineBean;
import com.mangocity.ce.exception.IllegalParamException;
import com.mangocity.ce.util.AssertUtils;

/**   
 * @Package com.mangocity.mbr.util 
 * @Description :  
 * @author YangJie
 * @email <a href='yangjie_software@163.com'>yangjie</a>
 * @date 2015-9-25
 */
public class ErrorUtils {
	private static final Logger log = Logger.getLogger(ErrorUtils.class);
	
	public static EngineBean error(EngineBean pb,String resultCode,String resultMsg) throws IllegalParamException{
		AssertUtils.assertNull(pb);
		pb.setResultCode(resultCode);
		pb.setResultMsg(resultMsg);
		return pb;
	}
}
