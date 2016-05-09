package com.mangocity.controller;

import java.util.Map;

import org.apache.log4j.Logger;

import com.mangocity.book.ErrorConstant;
import com.mangocity.ce.bean.EngineBean;
import com.mangocity.ce.book.SysBook;
import com.mangocity.ce.exception.BusinessException;
import com.mangocity.ce.exception.ExceptionAbstract;
import com.mangocity.ce.util.CommonUtils;
import com.mangocity.ce.util.RedisUtils;
import com.mangocity.util.CookieUtils;
import com.mangocity.util.SafeUtil;
import com.mangocity.util.SqlUtil;
import com.mangocity.util.URLUtil;

/**
 * 
 * @ClassName: UserController
 * @Description: 用户控制器服务
 * @author Yangjie
 * @date 2015年11月20日 下午6:20:46
 */
public class UserController {
	private static final Logger log = Logger.getLogger(UserController.class);
	private static final String REDIRECT_URL = "/redirect.mhtml?url=";
	/**
	 * 用户登录 登陆token
	 * @param wb
	 * @return
	 * @throws ExceptionAbstract
	 * @throws ClassNotFoundException 
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public EngineBean userLogin(EngineBean eb) throws ExceptionAbstract {
		log.info("UserController userLogin begin()...params: " + eb.getHeadMap());
		Map<String,Object> loginMap = SqlUtil.getInstance().selectOne("userLogin", eb.getHeadMap());
		
		
		if(CommonUtils.isEmpty(loginMap)){
			throw new BusinessException(this, ErrorConstant.User.ERROR_USERNAME_OR_PASSWORD_INVALID, "登录名或密码错误");
		}
		final String SUCCESS_URL = "WEB-INF/page/index.html";
		String md5Url = URLUtil.getMd5Url(SUCCESS_URL);

		eb.setBodyMap(loginMap);
		
		log.info("登陆成功..."+loginMap);
		String uValue = String.valueOf(loginMap.get("id"));
		String uToken = SafeUtil.MD5(uValue);//每个用户对应一个appId
		log.info("uToken: " + uToken + " ,value: " + uValue);
		//写缓存
		/*RedisUtils.set(uToken,uValue);*/

			eb.getRequest().getSession().setAttribute(md5Url, SUCCESS_URL);// key为根据当前时间和需要跳转到页面进行md5加密的值
																			// value为跳转的页面
		loginMap.put("uToken", uToken);
		eb.setBody("url", REDIRECT_URL + md5Url);
		//写登陆成功cookie uToken
		CookieUtils.writeCookie(eb, "uToken", uToken);
		CookieUtils.writeCookie(eb, "_id",String.valueOf(loginMap.get("id")));
		return eb;
	}
}
