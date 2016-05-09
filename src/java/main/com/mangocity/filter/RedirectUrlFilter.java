package com.mangocity.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.mangocity.ce.util.CommonUtils;
import com.mangocity.ce.web.distribution.FrameworkFilter;

/**
 * @Description : 跳转地址
 * @author YangJie
 * @email <a href='yangjie_software@163.com'>yangjie</a>
 * @date 2015-8-12
 */
public class RedirectUrlFilter extends FrameworkFilter {
	private static final Logger log = Logger.getLogger(RedirectUrlFilter.class);
	
	private static final String PAGE_NOT_FOUND = "404.html";

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) req;
		HttpServletResponse httpResponse = (HttpServletResponse) resp;
		handleRequest(httpRequest, httpResponse);
	}

	/**
	 * 处理请求核心方法
	 * @param request
	 * @param response
	 */
	private void handleRequest(HttpServletRequest request,
			HttpServletResponse response) {
		String url = request.getParameter("url");
		if(CommonUtils.isBlank(url)){
			log.info("url为空,服务端不发生跳转...");
			return;
		}
		String page = (String)request.getSession().getAttribute(url);
		log.info("客户端传入url: " + url + " ,session中对应的页面: " + page);
		if(CommonUtils.isBlank(page)){
			if (log.isDebugEnabled()) {
				log.debug("session中不存在该key,系统自动跳转到404页面...");
			}
			super.dispatcher(PAGE_NOT_FOUND, request, response);
			return;
		}
		if (log.isDebugEnabled()) {
			log.debug("跳转页面...");
		}
		try {
			super.dispatcher(page, request, response);
			return;
		}finally{
			//request.getSession().removeAttribute(url);
		}
	}

}
