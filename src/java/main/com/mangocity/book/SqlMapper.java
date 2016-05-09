package com.mangocity.book;

import java.io.Serializable;
import java.util.Map;

import com.mangocity.ce.util.CommonUtils;

/**
 * 封装MyBatis SQL操作需要的必要参数
 * @author YangJie
 */
public class SqlMapper implements Serializable {
	private static final long serialVersionUID = -3174410420561306453L;
	
	public enum TransacOperation{
		INSERT("C"),DELETE("D"),UPDATE("U");
		
		private final String sqlType;
		
		private TransacOperation(String sqlType){
			this.sqlType = sqlType;
		}
		
		public String getSqlType(){
			return sqlType;
		}
	}
	
	/**sqlId*/
	private  String sqlId;
	
	/**增加、删除、修改*/
	private  TransacOperation transacOperation;
	
	/**入参*/
	private  Map<String,Object> paramMap;
	
	private SqlMapper(Builder builder) {
		this.sqlId = builder.sqlId;
		this.transacOperation = builder.transacOperation;
		this.paramMap = builder.paramMap;
	}

	public String getSqlId() {
		return sqlId;
	}

	public Map<String, Object> getParamMap() {
		return paramMap;
	}
	
	public TransacOperation getTransacOperation() {
		return transacOperation;
	}

	@Override
	public String toString() {
		return "sqlId=" + sqlId + ", sqlType=" + transacOperation.getSqlType()
				+ ", paramMap=" + paramMap;
	}
	
	public void reset(){
		this.sqlId = null;
		this.transacOperation = null;
		this.paramMap = null;
	}
	
	public static Builder getInstance(){
		return new SqlMapper.Builder();
	}

	public static class Builder{
		private String sqlId;
		private TransacOperation transacOperation;
		private Map<String,Object> paramMap;
		
		public Builder sqlId(String sqlId){
			this.sqlId = sqlId;
			return this;
		}
		
		public Builder sqlType(TransacOperation transacOperation){
			this.transacOperation = transacOperation;
			return this;
		}
		
		public Builder paramMap(Map<String,Object> paramMap){
			this.paramMap = paramMap;
			return this;
		}
		
		public SqlMapper build(){
			if(CommonUtils.isBlank(sqlId)){
				throw new IllegalArgumentException("sqlId必须指定");
			}
			if(null == transacOperation || CommonUtils.isBlank(transacOperation.getSqlType())){
				throw new IllegalArgumentException("transacOperation必须指定");
			}
			if(CommonUtils.isEmpty(paramMap)){
				throw new IllegalArgumentException("paramMap必须指定");
			}
			return new SqlMapper(this);
		}
	}
}
