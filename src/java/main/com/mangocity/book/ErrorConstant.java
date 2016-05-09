package com.mangocity.book;
/**
* @ClassName: ErrorConstant 
* @Description: (错误常量.公共错误码以1开头,业务错误码以其他数字开头) 
* @author 刘春元
* @date 2015年8月11日 下午3:42:43 
 */
public class ErrorConstant {
	/**"成功状态"*/
	public static final String SUCCESS = "000000";
	
	/**"参数为空"*/
	public static final String ERROR_PARAM_NULL_10000 = "10000";
	
	/**"插入数据失败"*/
	public static final String ERROR_INSERT_DATA_FAIL_10001 = "10001";
	
	/**"更新数据失败"*/
	public static final String ERROR_UPDATE_DATA_FAIL_10002 = "10002";
	
	/**"数据转换失败"*/
	public static final String ERROR_PARAM_PARSE_FAIL_10003 = "10003";
	
	/**"查询不到数据"*/
	public static final String ERROR_NO_RESULT_DATA = "10004";
	
	/**"删除数据失败"*/
	public static final String ERROR_DELETE_DATA_FAIL_10005 = "10005";
	
	/**"事务回滚"*/
	public static final String ERROR_TRANSACTION_ROLL_BACK_10006 = "10006";
	
	public static class Point{
		/**"状态无效"*/
		public static final String ERROR_STUS_INVALID_20001 = "20001";
		
		/**"积分账户不存在"*/
		public static final String ERROR_ACCOUNT_NOT_EXIST_20002 = "20002";
		
		/**"积分余额不足"*/
		public static final String ERROR_INSUFFICIENT_BALANCE_20003 = "20003";
		
		/**"积分扣减失败"*/
		public static final String ERROR_CUT_POINT_FAIL_20004 = "20004";
		
		/**"积分账户已冻结"*/
		public static final String ERROR_POINT_ACCOUNT_FREEZE_20005 = "20005";
		
		/**"积分账户已注销"*/
		public static final String ERROR_POINT_ACCOUNT_Logout_20006 = "20006";
		
		/**"积分账户未激活"*/
		public static final String ERROR_POINT_ACCOUNT_NOT_ACCTIVATED_20007 = "20007";
		
		/**"扣减积分数少于0"*/
		public static final String ERROR_CUT_POINTS_LESS_THAN_ZERO_20008 = "20008";
		
		/**"操作积分余额失败"*/
		public static final String ERROR_OPER_POINT_BALANCE_FAIL_20009 = "20009";
	}
	
	public static class Mbr{
		/**"会员账户不存在"*/
		public static final String ERROR_MBR_NOT_EXIST_30001 = "30001";
		/**"会员账户已经存在"*/
		public static final String ERROR_MBR_IS_EXIST_30002 = "30002";
		/**"会员账户已经冻结"*/
		public static final String ERROR_MBR_IS_FREEZE_30003 = "30003";
		/**"会员账户冲突"*/
		public static final String ERROR_MBR_IS_CONFLICT_30004 = "30004";
		/**"会员账户已经注销"*/
		public static final String ERROR_MBR_IS_LOGOUT_30005 = "30005";
		/**"多条相同会员记录"*/
		public static final String ERROR_MBR_DUPLICATE_RECORD_30006 = "30006";
		/**"会员没有绑定手机号"*/
		public static final String ERROR_MBR_NOT_BINDED_MOBILNO_30007 = "30007";
		/**"集团会员暂不支持积分扣减"*/
		public static final String ERROR_CRM_MBR_NOT_SUPPORT_POINT_CUT_30008 = "30008";
	}
	
	public static class Sms{
		/**"短信校验失败"*/
		public static final String ERROR_SMS_VALIDATE_FAIL = "40001";
		/**"短信超时"*/
		public static final String ERROR_SMS_OVERTIME_FAIL = "40002";
	}
	
	public static class Thirdparty{
		/**"第三方服务已绑定"*/
		public static final String ERROR_OPENID_IS_BINDED = "50001";
	}
	
	public static class User{
		/**"登陆名或密码无效"*/
		public static final String ERROR_USERNAME_OR_PASSWORD_INVALID = "60001";
	}
	
	public static class Inteface{
		/**接口和接口名已经存在*/
		public static final String ERROR_INTERFACE_EXIST = "70001";
		
		/**插入接口失败*/
		public static final String ERROR_INTERFACE_INSERT_FAIL = "70002";
	}
}
