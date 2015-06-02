//package com.sihehui.section_network.http.json;
//
//import java.io.UnsupportedEncodingException;
//import java.util.LinkedHashMap;
//import java.util.Map;
//
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import android.content.SharedPreferences;
//import android.content.SharedPreferences.Editor;
//import android.util.Log;
//
//import com.android.volley.AuthFailureError;
//import com.android.volley.NetworkError;
//import com.android.volley.NetworkResponse;
//import com.android.volley.NoConnectionError;
//import com.android.volley.ParseError;
//import com.android.volley.Request;
//import com.android.volley.Response;
//import com.android.volley.Response.Listener;
//import com.android.volley.ServerError;
//import com.android.volley.TimeoutError;
//import com.android.volley.VolleyError;
//import com.android.volley.toolbox.HttpHeaderParser;
//import com.sihehui.section_network.http.info.IFailureListener;
//import com.sihehui.section_network.http.info.ProcessError;
//
//public class JSONObjectRequest extends Request<JSONObject> {
//
//	private String url;
//	private Map<String, String> params;
//	private Map<String, String> headers;
//
//	private final Listener<JSONObject> listener;
//
//	private SharedPreferences cookieSharedPreferences;
//
//	public JSONObjectRequest(String url, Map<String, String> headers, Map<String, String> params,
//			Listener<JSONObject> listener, final IFailureListener failListener) {
//		this(url, headers, params, null, listener, failListener);
//	}
//
//	public JSONObjectRequest(String url, Map<String, String> headers, Map<String, String> params,
//			SharedPreferences cookieSharedPreferences, Listener<JSONObject> listener,
//			final IFailureListener failListener) {
//
//		super(params == null ? Method.GET : Method.POST, url, new com.android.volley.Response.ErrorListener() {
//
//			@Override
//			public void onErrorResponse(VolleyError error) {
//				if (error instanceof ParseError) {
//					Log.d("JSONObjectRequest:", "Parse Error", error);
//					failListener.onError("0001", "网络异常！");
//				} else if (error instanceof TimeoutError) {
//					failListener.onConnectionTimeoutError();
//				} else if (error instanceof NoConnectionError) {
//					failListener.onNetworkError();
//				} else if (error instanceof ServerError) {
//					Log.d("JSONObjectRequest:", "server error", error);
//					failListener.onError("0001", "网络异常！");
//				} else if (error instanceof NetworkError) {
//					Log.d("JSONObjectRequest:", "network error", error);
//					failListener.onError("0001", "网络异常！");
//				} else if (error instanceof AuthFailureError) {
//					Log.d("JSONObjectRequest:", "AuthFailure error", error);
//					failListener.onError("0001", "网络异常！");
//				} else if (error instanceof ProcessError) {
//					failListener.onError(((ProcessError) error).getCode(), error.getMessage());
//				}
//
//			}
//		});
//		this.listener = listener;
//		this.headers = headers;
//		this.params = params;
//		if (params != null) {
//			this.setShouldCache(false);
//		}
//		this.url = url;
//		this.cookieSharedPreferences = cookieSharedPreferences;
//	}
//
//	protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
//		try {
//			String jsonString = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
//			JSONObject result = new JSONObject(jsonString);
//			if (!result.isNull("error_code")) {// 错误处理
//				return Response.error(new ProcessError(result.optString("error_code"), result.optString("error_msg")));
//			}
//
//			JSONObject headers = new JSONObject();
//			if (response.headers.get("Date") != null) {
//				headers.put("Date", HttpHeaderParser.parseDateAsEpoch(response.headers.get("Date")));
//			}
//			if (response.headers.get("Expires") != null) {
//				headers.put("Expires", HttpHeaderParser.parseDateAsEpoch(response.headers.get("Expires")));
//			}
//			if (response.headers.get("Set-Cookie") != null && cookieSharedPreferences != null) {
//				Log.d("Set-Cookie","url = " + url + "==>Set-Cookiet = " + response.headers.get("Set-Cookie"));
//				Editor editor = cookieSharedPreferences.edit();
//				editor.putString("Set-Cookie", response.headers.get("Set-Cookie"));
//				editor.commit();
//			}
//			JSONObject json = new JSONObject();
//			json.put("headers", headers);
//			json.put("result", result);
//			Response<JSONObject> _response = Response.success(json, HttpHeaderParser.parseCacheHeaders(response));
//			return _response;
//		} catch (UnsupportedEncodingException e) {
//			return Response.error(new ParseError(e));
//		} catch (JSONException je) {
//			return Response.error(new ParseError(je));
//		}
//	}
//
//	@Override
//	public Map<String, String> getHeaders() throws AuthFailureError {
//		super.getHeaders();
//		Map<String, String> headers = new LinkedHashMap<String, String>();
//		if (this.headers.size() > 0)
//			headers.putAll(this.headers);// cookie or etc
//		headers.put("Accept", "application/json");
//		return headers;
//	}
//
//	@Override
//	protected void deliverResponse(JSONObject response) {
//		listener.onResponse(response);
//	}
//
//	@Override
//	protected Map<String, String> getParams() throws AuthFailureError {
//		return this.params;
//	}
// }
