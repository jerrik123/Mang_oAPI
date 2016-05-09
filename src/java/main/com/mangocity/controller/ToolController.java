package com.mangocity.controller;

import java.util.Map;

import org.apache.log4j.Logger;

import com.mangocity.ce.bean.EngineBean;
import com.mangocity.ce.book.ErrorBook;
import com.mangocity.ce.exception.BusinessException;
import com.mangocity.ce.exception.ExceptionAbstract;
import com.mangocity.ce.util.CommonUtils;
import com.mangocity.ce.util.DES;
import com.mangocity.ce.util.New;
import com.mangocity.ce.util.RSA;
import com.mangocity.util.SafeUtil;

/**
 * 
 * @ClassName: UserController
 * @Description: 用户控制器服务
 * @author Yangjie
 * @date 2015年11月20日 下午6:20:46
 */
public class ToolController {
	private static final Logger log = Logger.getLogger(ToolController.class);

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public EngineBean todoCode(EngineBean eb) throws ExceptionAbstract {
		Map outMap = New.map();
		if(CommonUtils.isBlank((String) eb.getHead("appid"))){
			throw new BusinessException(this, ErrorBook.PARAM_ESS_ERROR, "appid不能是空");
		}if(CommonUtils.isBlank((String) eb.getHead("methods"))){
			throw new BusinessException(this, ErrorBook.PARAM_ESS_ERROR, "方法名不能是空");
		}
		outMap.put("adjustCode", SafeUtil.MD5(SafeUtil
				.MD5(eb.getHead("appid")+"" + eb.getHead("methods"))
				+ "" + eb.getHead("appkey")));
		eb.setBody("result", outMap);
		
		return eb;
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public EngineBean toRsaa(EngineBean eb) throws ExceptionAbstract {
		Map outMap = New.map();
		if(CommonUtils.isBlank((String) eb.getHead("rsaa"))){
			throw new BusinessException(this, ErrorBook.PARAM_ESS_ERROR, "公钥不能是空");
		}if(CommonUtils.isBlank((String) eb.getHead("rsab"))){
			throw new BusinessException(this, ErrorBook.PARAM_ESS_ERROR, "明文不能是空");
		}
		String outtemp = RSA.create().encodeByPublicKey(eb.getHead("rsab").toString(),eb.getHead("rsaa").toString());
		if(outtemp ==null)throw new BusinessException(this, ErrorBook.PARAM_ESS_ERROR, "这个我真加密不了");
		outMap.put("outstring", outtemp);
		
		eb.setBody("result", outMap);
		
		return eb;
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public EngineBean toRsab(EngineBean eb) throws ExceptionAbstract {
		Map outMap = New.map();
		if(CommonUtils.isBlank((String) eb.getHead("rsaa"))){
			throw new BusinessException(this, ErrorBook.PARAM_ESS_ERROR, "公钥不能是空");
		}if(CommonUtils.isBlank((String) eb.getHead("rsad"))){
			throw new BusinessException(this, ErrorBook.PARAM_ESS_ERROR, "密文不能是空");
		}
		String outtemp = RSA.create().decodeByPublicKey(eb.getHead("rsad").toString(),eb.getHead("rsaa").toString());
		if(outtemp ==null)throw new BusinessException(this, ErrorBook.PARAM_ESS_ERROR, "这个我真解密不了");
		outMap.put("outstring", outtemp);
		eb.setBody("result", outMap);
		
		return eb;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public EngineBean toDesa(EngineBean eb) throws ExceptionAbstract {
		Map outMap = New.map();
		if(CommonUtils.isBlank((String) eb.getHead("desa"))){
			throw new BusinessException(this, ErrorBook.PARAM_ESS_ERROR, "密钥不能是空");
		}if(CommonUtils.isBlank((String) eb.getHead("desb"))){
			throw new BusinessException(this, ErrorBook.PARAM_ESS_ERROR, "明文不能是空");
		}
		
		outMap.put("outstring", DES.encryptDES(eb.getHead("desb").toString(), eb.getHead("desa").toString()));
		eb.setBody("result", outMap);
		
		return eb;
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public EngineBean toDesb(EngineBean eb) throws ExceptionAbstract {
		Map outMap = New.map();
		if(CommonUtils.isBlank((String) eb.getHead("desa"))){
			throw new BusinessException(this, ErrorBook.PARAM_ESS_ERROR, "密钥不能是空");
		}if(CommonUtils.isBlank((String) eb.getHead("desd"))){
			throw new BusinessException(this, ErrorBook.PARAM_ESS_ERROR, "密文不能是空");
		}
		String outtemp = DES.decryptDES(eb.getHead("desd").toString(), eb.getHead("desa").toString());
		if(outtemp ==null)throw new BusinessException(this, ErrorBook.PARAM_ESS_ERROR, "这个我真解不了");
		outMap.put("outstring",outtemp );
		eb.setBody("result", outMap);
		
		return eb;
	}
}
