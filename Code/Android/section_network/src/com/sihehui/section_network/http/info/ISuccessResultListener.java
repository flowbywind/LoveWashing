package com.sihehui.section_network.http.info;

import java.util.Map;

import org.json.JSONObject;

public interface ISuccessResultListener<T> {
	void onSuccessed(T result) throws ProcessException;

	/**
	 * @param headers
	 * 			Date and Expires;
	 * @param result
	 * @param json
	 * @throws ProcessException
	 */
	void onSuccessed(Map<String, Object> headers, T result, JSONObject json) throws ProcessException;
}
