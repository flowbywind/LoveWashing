package com.sihehui.section_network.http.info;

public class ProcessException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8800737846857592599L;

	public static final String IO_EXCEPTION = "999998"; // 访问接口IO错误
	public static final String HTTP_STATUS_ERROR = "999997"; // 访问接口HTTP状态异常
	public static final String HTTP_PARATEMS_ERROR = "999996"; // 通用参数不全

	/**
	 * 错误代码。
	 * */
	private String code;

	public ProcessException() {
	}

	public ProcessException(String p0) {
		super(p0);
	}

	public ProcessException(String p0, String code) {
		super(p0);
		this.setCode(code);
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
}
