package com.mangocity.util;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.mangocity.ce.bean.EngineBean;

/**
 * 写Cookie工具类
 */
public class CookieUtils {
	private static Logger log = Logger.getLogger(CookieUtils.class);

	/**
	 * 写Cookie
	 * 
	 * @param eb
	 * @param cookie
	 */
	public static void writeCookie(EngineBean eb, Cookie cookie) {
		writeCookie(eb.getResponse(), cookie);
	}

	public static void writeCookie(EngineBean eb, String key, String value) {
		writeCookie(eb.getResponse(), key,value);
	}

	public static void writeCookie(HttpServletResponse response, Cookie cookie) {
		checkForNull(response, cookie);
		response.addCookie(cookie);
	}

	public static void writeCookie(HttpServletResponse response, String key,
			String value) {
		checkForNull(response, key, value);
		Cookie cookie = new Cookie(key, value);
		writeCookie(response, cookie);
	}

	/**
	 * 读取所有Cookie
	 * 
	 * @param eb
	 * @param cookie
	 */
	public static Cookie[] readCookies(EngineBean eb) {
		return readCookies(eb.getRequest());
	}

	public static Cookie[] readCookies(HttpServletRequest request) {
		checkForNull(request);
		Cookie[] cookies = request.getCookies();
		return cookies;
	}

	/**
	 * 读指定名字的Cookie
	 * 
	 * @param eb
	 * @param cookie
	 */
	public static Cookie readCookieByName(EngineBean eb, String cookieName) {
		return readCookieByName(eb.getRequest(), cookieName);
	}
	
	public static Cookie readCookieByName(HttpServletRequest request, String cookieName) {
		checkForNull(request, cookieName);
		Cookie[] cookies = readCookies(request);
		for (Cookie tCookie : cookies) {
			if (tCookie.getName().equals(cookieName)) {
				return tCookie;
			}
		}
		log.info("获取Cookie: " + cookieName + "失败");
		return null;
	}

	public static <T> void checkForNull(T... t) {
		List<T> list = Arrays.asList(t);
		String tStr = null;
		for (int i = 0, len = list.size(); i < len; i++) {
			if (null == list.get(i)) {
				throw new IllegalArgumentException("参数不能有空值");
			}
			if (list.get(i) instanceof String) {
				if (null == list.get(i)) {
					throw new IllegalArgumentException("参数不能有空值");
				} else {
					tStr = String.valueOf(list.get(i));
					if (tStr.trim().equals("")) {
						throw new IllegalArgumentException("参数不能有空值");
					}
				}
			}
		}
	}

}
