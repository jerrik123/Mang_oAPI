package com.mangocity.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.mangocity.ce.bean.EngineBean;
import com.mangocity.ce.book.ErrorBook;
import com.mangocity.ce.book.SysBook;
import com.mangocity.ce.exception.ExceptionAbstract;
import com.mangocity.ce.exception.IllegalParamException;
import com.mangocity.ce.web.distribution.FrameworkFilter;
import com.mangocity.ce.web.prosess.WebRequestProcess;

/**
 * @Description : 核心分发类
 * @author YangJie
 * @email <a href='yangjie_software@163.com'>yangjie</a>
 * @date 2015-8-12
 */
public class DispatcherFilter extends FrameworkFilter {
	private static final Logger log = Logger.getLogger(DispatcherFilter.class);

	/**
	 * 覆盖父类的方法,适应于获取POST提交参数
	 */
	protected void doDispatcher(HttpServletRequest request,
			HttpServletResponse response) {
		WebRequestProcess reqProcess = WebRequestProcess.getInstance();
		EngineBean eb = new EngineBean();
		Object obj = null;
		eb.setRequest(request);
		eb.setResponse(response);
		String url = null;
		String name = null;
		try {
			reqProcess.setCharacterEncoding(eb);
			reqProcess.transformRequestParam(eb);
			obj = reqProcess.distribution(eb);
			
			if(!(obj instanceof EngineBean)){
				throw new IllegalParamException(this, ErrorBook.PARAM_ESS_ERROR, "只能返回EngineBean");
			}
			eb = (EngineBean)obj;
			if(null ==eb.getResultCode())eb.setResultCode(SysBook.SUCCESS);
			if(null ==eb.getResultMsg())eb.setResultMsg("请求成功");
			reqProcess.responseProcess(obj, response);
		} catch (ExceptionAbstract e) {
			eb.setResultCode(e.getErrorCode());
			eb.setResultMsg(e.getErrorMsg());
			reqProcess.responseProcess(obj, response);
		}
	}

}
