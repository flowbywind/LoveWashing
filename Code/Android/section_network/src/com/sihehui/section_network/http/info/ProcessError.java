package com.sihehui.section_network.http.info;

import com.android.volley.NetworkResponse;
import com.android.volley.VolleyError;

public class ProcessError extends VolleyError {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7037897481491561244L;
	private String code;

	public ProcessError(String code, String message) {
		super(message);
		this.code = code;
	}

	public ProcessError(String code, NetworkResponse response) {
		super(response);
		this.code = code;
	}

	public ProcessError(String code, String message, Exception reason) {
		super(message, reason);
		this.code = code;
	}

	public String getCode() {
		return code;
	}

}
