package com.sihehui.section_network.http.json;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import android.util.Log;

import com.android.volley.Response.Listener;
import com.sihehui.section_network.http.info.IConnectionNetworkAble;
import com.sihehui.section_network.http.info.ProcessException;

public abstract class CallbackListener<T> implements Listener<JSONObject> {

	private IConnectionNetworkAble<T> connection;

	public CallbackListener(IConnectionNetworkAble<T> connection) {
		this.connection = connection;
	}

	@Override
	public void onResponse(JSONObject response) {
		try {
		    System.out.println("response:"+response);
			if (!isNotNull(response)) {
				connection.onError("0001", "网络异常！");
				return;
			}

			Map<String, Object> headers = parseHeaders(response);
     
			JSONObject result = response.optJSONObject("result");
			Log.d("shh","result="+result.toString());
			if (!isNotNull(result)) {
				connection.onError("0002", "处理失败");
				return;
			}

			T t = parseResult(result);

			connection.onSuccessed(headers, t, result);
		} catch (ProcessException e) {
			Log.d(logTag(), "[" + e.getCode() + "," + e.getMessage() + "]");
			connection.onError(e.getCode(), e.getMessage());
		} catch (Exception e) {
			Log.d(logTag(), "", e);
			connection.onError("0001", "网络异常！");
		}
	}

	Map<String, Object> parseHeaders(JSONObject response) throws ProcessException, Exception {
		Map<String, Object> headerMap = null;
		if (isNotNull(response) && !response.isNull("headers")) {
			headerMap = new HashMap<String, Object>();
			if (!response.optJSONObject("headers").isNull("Date")) {
				headerMap.put("Date", response.optJSONObject("headers").optLong("Date"));
			}
			if (!response.optJSONObject("headers").isNull("Expires")) {
				headerMap.put("Expires", response.optJSONObject("headers").optLong("Expires"));
			}
		}
		return headerMap;
	}

	void log(String tag, Exception e) {
		if (e instanceof ProcessException) {
			Log.d(tag, "[" + ((ProcessException) e).getCode() + "," + e.getMessage() + "]");
		} else {
			Log.d(tag, "", e);
		}
	}

	public boolean isNotNull(JSONObject jsonObject) {
		return jsonObject != null && !JSONObject.NULL.equals(jsonObject);
	}

	public boolean isNotNull(JSONArray jsonArray) {
		return jsonArray != null && jsonArray.length() > 0;
	}

	abstract T parseResult(JSONObject result) throws ProcessException, Exception;

	abstract String logTag();
}
