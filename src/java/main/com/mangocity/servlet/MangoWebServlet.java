package com.mangocity.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mangocity.ce.bean.EngineBean;
import com.mangocity.ce.deploy.ConfigManage;
import com.mangocity.ce.exception.ExceptionAbstract;
import com.mangocity.ce.web.prosess.WebRequestProcess;
import com.mangocity.util.SqlUtil;

public class MangoWebServlet extends HttpServlet {
	static {
		ConfigManage.instance().initSystem();
		/*try {
			//SqlLiteUtil.getInstance().init();
			//SqlUtil.getInstance().init();
		} catch (ExceptionAbstract e) {
			throw new RuntimeException("SqlUtil或SqlLiteUtil初始化错误...");
		}*/
	}

	/**
	 * 统一接收请求入口
	 */
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		EngineBean bean = new EngineBean();
		bean.setRequest(req);
		bean.setResponse(resp);
		WebRequestProcess reqProcess = WebRequestProcess.getInstance();
		try {
			reqProcess.setCharacterEncoding(bean);
			reqProcess.transformRequestParam(bean);
 			Object obj =reqProcess.distribution(bean);
			reqProcess.responseProcess(obj,bean.getResponse());
		} catch (ExceptionAbstract e) {
			e.printStackTrace();
		}
	}
}
