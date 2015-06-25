package com.sihehui.section_network.http.json;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NetworkResponse;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.Response.Listener;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.sihehui.section_network.http.info.IFailureListener;
import com.sihehui.section_network.http.info.ProcessError;

public class MultiPartStringRequest extends Request<JSONObject> implements
		MultiPartRequest {

	private final Listener<JSONObject> mListener;
	/* To hold the parameter name and the File to upload */
	private Map<String, Object> fileUploads = new HashMap<String, Object>();

	/* To hold the parameter name and the string content to upload */
	private Map<String, String> stringUploads = new HashMap<String, String>();

	/**
	 * Creates a new request with the given method.
	 * 
	 * @param method
	 *            the request {@link Method} to use
	 * @param url
	 *            URL to fetch the string at
	 * @param listener
	 *            Listener to receive the String response
	 * @param failureListener
	 *            Error listener, or null to ignore errors
	 */
	public MultiPartStringRequest(int method, String url,
			Listener<JSONObject> listener, final IFailureListener failListener) {
		super(Method.POST, url,
				new com.android.volley.Response.ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError error) {
						if (error instanceof ParseError) {
							Log.d("JSONObjectRequest:", "Parse Error", error);
							failListener.onError("0001", "网络异常！");
						} else if (error instanceof TimeoutError) {
							failListener.onConnectionTimeoutError();
						} else if (error instanceof NoConnectionError) {
							failListener.onNetworkError();
						} else if (error instanceof ServerError) {
							Log.d("JSONObjectRequest:", "server error", error);
							failListener.onError("0001", "网络异常！");
						} else if (error instanceof NetworkError) {
							Log.d("JSONObjectRequest:", "network error", error);
							failListener.onError("0001", "网络异常！");
						} else if (error instanceof AuthFailureError) {
							Log.d("JSONObjectRequest:", "AuthFailure error",
									error);
							failListener.onError("0001", "网络异常！");
						} else if (error instanceof ProcessError) {
							failListener.onError(
									((ProcessError) error).getCode(),
									error.getMessage());
						}

					}
				});
		mListener = listener;
	}

	// public void addFileUpload(String param, Object file) {
	// fileUploads.put(param, file);
	// }
	//
	// public void addStringUpload(String param, String content) {
	// stringUploads.put(param, content);
	// }

	/**
	 * 要上传的文件
	 */
	public Map<String, Object> getFileUploads() {
		return fileUploads;
	}

	/**
	 * 要上传的参数
	 */
	public Map<String, String> getStringUploads() {
		return stringUploads;
	}

	@Override
	protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
		try {
			String jsonString = new String(response.data,
					HttpHeaderParser.parseCharset(response.headers));
			Log.d("shh", "jsonString=" + jsonString);
			JSONObject result = new JSONObject(jsonString);
			Log.d("shh", "result result result.toString()=" + result.toString());
			// JSONObject errors = result.optJSONObject("resultMessage");
			// Log.d("shh", "errors=" + errors);

			if (result != null && !result.isNull("resultMessage")) {// 错误处理
				return Response.error(new ProcessError(result
						.optString("resultMessage"), result
						.optString("resultMessage")));
			}
			// String resultMessage=result.optString("resultMessage");
			// String ret = result.optString("result");
			// if (ret.startsWith("\"") && ret.endsWith("\"")) {
			// ret = ret.substring(1, ret.length() - 1);
			// }
			// Log.d("shh","ret="+ret);
			// String total="resultMessage:"+resultMessage+",result:"+ret;
			// result = new JSONObject(total);
			JSONObject headers = new JSONObject();
			if (response.headers.get("Date") != null) {
				headers.put("Date", HttpHeaderParser
						.parseDateAsEpoch(response.headers.get("Date")));
			}
			if (response.headers.get("Expires") != null) {
				headers.put("Expires", HttpHeaderParser
						.parseDateAsEpoch(response.headers.get("Expires")));
			}
			// if (response.headers.get("Set-Cookie") != null &&
			// cookieSharedPreferences != null) {
			// Log.d("Set-Cookie","url = " + url + "==>Set-Cookiet = " +
			// response.headers.get("Set-Cookie"));
			// Editor editor = cookieSharedPreferences.edit();
			// editor.putString("Set-Cookie",
			// response.headers.get("Set-Cookie"));
			// editor.commit();
			// }
			JSONObject json = new JSONObject();
			json.put("headers", headers);
			json.put("result", result);
			Response<JSONObject> _response = Response.success(json,
					HttpHeaderParser.parseCacheHeaders(response));
			return _response;
		} catch (UnsupportedEncodingException e) {
			return Response.error(new ParseError(e));
		} catch (JSONException je) {
			return Response.error(new ParseError(je));
		}
	}

	// String parsed;
	// try {
	// parsed = new String(response.data,
	// HttpHeaderParser.parseCharset(response.headers));
	// } catch (UnsupportedEncodingException e) {
	// parsed = new String(response.data);
	// }
	// return Response.success(parsed,
	// HttpHeaderParser.parseCacheHeaders(response));
	// }

	@Override
	protected void deliverResponse(JSONObject response) {
		if (mListener != null) {

			mListener.onResponse(response);
		}
	}

	/**
	 * 空表示不上传
	 */
	public String getBodyContentType() {
		return null;
	}

}
