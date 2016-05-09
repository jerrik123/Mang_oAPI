package com.mangocity.util;

import com.mangocity.ce.safe.SafeUtil;

public class URLUtil {

	/**
	 * 生成加密后的Url Md5(time+url)
	 * @param url
	 * @return
	 */
	public static String getMd5Url(String url){
		StringBuffer sb = new StringBuffer();
		long curTime = System.currentTimeMillis();
		sb.append(curTime);
		sb.append(url);
		return SafeUtil.MD5(sb.toString());
	}
}
