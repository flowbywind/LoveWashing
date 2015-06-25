package com.sihehui.section_network.http.info;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageLoader.ImageCache;
import com.android.volley.toolbox.ImageLoader.ImageContainer;
import com.android.volley.toolbox.ImageLoader.ImageListener;
import com.android.volley.toolbox.Volley;
import com.sihehui.section_network.http.json.MultiPartStack;
import com.sihehui.section_network.http.json.MultiPartStringRequest;
import com.sihehui.section_network.util.Contacts;
import com.sihehui.section_network.util.Util;

public class BaseClientAPI {

	private final static String TAG = BaseClientAPI.class.getName();

	protected static final String SET_COOKIE_KEY = "Set-Cookie";
	protected static final String COOKIE_KEY = "Cookie";

	protected Context context;
	protected RequestQueue requestQueue;
	protected SharedPreferences cookieSharedPreferences;

	/**
	 * @param context
	 */
	public BaseClientAPI(Context context) {
		this.context = context;
		cookieSharedPreferences = context.getSharedPreferences(
				"cookieSharedPreferences", Context.MODE_PRIVATE);
		requestQueue = Volley.newRequestQueue(context, new MultiPartStack());
	}

	protected void post(String uri, Map<String, Object> files,
			Map<String, String> input, Listener<JSONObject> listener,
			IFailureListener failureListener) {
		Map<String, String> signMap = new HashMap<String, String>();// 签名用
		Map<String, String> params = HttpUtil.getCommonParameters(context);//
		if (input.get("uid") != null && !"".equals(input.get("uid"))) {
			params.put("uid", input.get("uid"));
		}
		if (input.get("token") != null && !"".equals(input.get("token"))) {
			params.put("token", input.get("token"));
		}
		// 通用参数
		Log.d(TAG, "input===" + input);
		// signMap.putAll(params);
		if (input != null) {
			signMap.putAll(input);
		}
		// String sign = MD5Util.MD5Encode(HttpUtil.convertParameter(signMap) +
		String url = getStandardURL(context, uri, params);
		System.out.println("url:" + url);
		addJSONObjectRequest(url, files, signMap, listener, failureListener);

	}

	protected void post(String uri, Map<String, String> input,
			Listener<JSONObject> listener, IFailureListener failureListener) {
		post(uri, null, input, listener, failureListener);

	}

	protected void post(Map<String, Object> input, String uri,
			Listener<JSONObject> listener, IFailureListener failureListener) {
		// Map<String, Object> signMap = new HashMap<String, Object>();// 签名用
		// Map<String, Object> params =
		// HttpUtil.getCxdCommonParameters(context);//
		// if (input.get("uid") != null && !"".equals(input.get("uid"))) {
		// params.put("uid", input.get("uid"));
		// }
		// if (input.get("token") != null && !"".equals(input.get("token"))) {
		// params.put("token", input.get("token"));
		// }
		// 通用参数
		Log.d(TAG, "input===" + input);
		// signMap.putAll(params);
		// if (input != null) {
		// signMap.putAll(input);
		// }
		// String sign = MD5Util.MD5Encode(HttpUtil.convertParameter(signMap) +
		Map<String, String> totalMap = new HashMap<String, String>();// 签名用
		input.put("merchantId", "1");

		for (String key : input.keySet()) {
			totalMap.put(key, "" + input.get(key));
		}
		// totalMap.put("value", Util.map2Json(input));
		// totalMap.put("sName", uri);
		// totalMap.put("sName", Contacts.ServiceURL.CXD_AuthCode);
		// getStandardURL(context, uri, totalMap)
		String url = Contacts.getInstance(context).SERVER + uri;
		System.out.println("url:" + url);
		addJSONObjectRequest(url, null, totalMap, listener, failureListener);

	}

	protected void get(String uri, Map<String, Object> files,
			Map<String, String> input, Listener<JSONObject> listener,
			IFailureListener failureListener) {
		Map<String, String> params = HttpUtil.getCommonParameters(context);
		if (input != null) {
			params.putAll(input);
		}
		// Log.d(TAG, "getCLIENT_SOURCE===" +
		// Contacts.getInstance(context).CLIENT_SOURCE);
		// params.put("clientsource",
		// Contacts.getInstance(context).CLIENT_SOURCE);
		// String sign = MD5Util.MD5Encode(HttpUtil.convertParameter(params) +
		// Contacts.SIGN_NAME);
		// params.put("sign", sign);
		String url = getStandardURL(context, uri, params);// URL(uri,
		addJSONObjectRequest(url, files, input, listener, failureListener);
	}

	protected void addJSONObjectRequest(String url,
			final Map<String, Object> files, final Map<String, String> params,
			Listener<JSONObject> listener, IFailureListener failureListener) {
		Log.d(TAG, "url = " + url);
		// JSONObject params = convertJSONObject(input);
		// Map<String, String> headers = new HashMap<String, String>();
		// if (cacheUserVO != null && cacheUserVO.getSessionId() != null) {
		// headers.put(COOKIE_KEY, "JSESSIONID=" + cacheUserVO.getSessionId()
		// + ";Path=/;");
		// }
		// addCookie(url, headers);
		// Log.d(TAG,
		// "url = " + url + "-->" + COOKIE_KEY + " = "
		// + headers.get(COOKIE_KEY));
		// JSONObjectRequest request = new JSONObjectRequest(url, headers,
		// params,
		// cookieSharedPreferences, listener, failureListener);
		// request.setTag(context);

		MultiPartStringRequest multiPartRequest = new MultiPartStringRequest(
				Request.Method.POST, url, listener, failureListener) {

			@Override
			public Map<String, Object> getFileUploads() {
				return files;
			}

			@Override
			public Map<String, String> getStringUploads() {
				return params;
			}

		};

		requestQueue.add(multiPartRequest);

	}

	/**
	 * Adds cookie to request headers if exists.
	 * 
	 * @param headers
	 *            Request Headers
	 */
	public final void addCookie(String url, Map<String, String> headers) {
		String setCookie = cookieSharedPreferences
				.getString(SET_COOKIE_KEY, "");
		StringBuilder builder = new StringBuilder();
		if (!"".equals(setCookie)) {
			String[] cookies = setCookie.split(";");
			for (String cookie : cookies) {
				if (cookie != null && cookie.startsWith("route=")) {
					builder.append(cookie.trim());
					break;
				}
			}

		}
		if (headers.containsKey(COOKIE_KEY)) {
			if (builder.length() > 0 && !builder.toString().endsWith(";")) {
				builder.append(";");
			}
			builder.append(headers.get(COOKIE_KEY));
		}
		if (builder.length() > 0) {
			headers.put(COOKIE_KEY, builder.toString());
		}
	}

	public static ImageContainer downloadImage(Context context,
			String imageUrl, ImageCache imageCache, ImageListener imageListener) {
		ImageLoader imageLoader = new ImageLoader(
				Volley.newRequestQueue(context), imageCache);
		ImageContainer imageContainer = imageLoader
				.get(imageUrl, imageListener);
		return imageContainer;
	}

	public void cancelAll() {
		requestQueue.cancelAll(context);
	}

	public static String getStandardURL(Context context, String uri) {
		return getStandardURL(context, uri, null);
	}

	public static String getStandardURL(Context context, String uri,
			Map<String, String> input) {
		StringBuilder builder = new StringBuilder(
				Contacts.getInstance(context).SERVER);
		if (uri != null && uri.length() > 0) {
			// if (!uri.startsWith("/")) {
			// builder.append("/");
			// }
			// Map<String, String> params = new HashMap<String, String>();
			//
			// if (input != null) {
			// params.putAll(input);
			// }

			// if (!HttpUtil.validateCommonParameters(params)) {
			// params.putAll(HttpUtil.getCommonParameters(context));
			// }
			//
			String query = HttpUtil.convertParameter(input);
			// if (params.get("sign") == null) {
			// String sign = MD5Util.MD5Encode(query + Contacts.SIGN_NAME);
			// query += ("&sign=" + sign);
			// }

			// if (uri.contains("?") && !uri.endsWith("?")) {
			uri += ("&" + query);
			// } else if (uri.endsWith("?")) {
			// uri += query;
			// } else {
			// uri += ("?" + query);
			// }
			builder.append(uri);
		}
		Log.d(TAG, "url-->" + builder.toString());
		return builder.toString();
	}

	protected String getURL(String url) {
		StringBuilder builder = new StringBuilder(url);
		// if (cacheUserVO != null && cacheUserVO.getSessionId() != null
		// && !"".equals(cacheUserVO.getSessionId().trim())) {
		// if (builder.indexOf("?") > -1) {
		// builder.insert(builder.indexOf("?"), ";jsessionid="
		// + cacheUserVO.getSessionId());
		// } else {
		// builder.append(";jsessionid=").append(
		// cacheUserVO.getSessionId());
		// }
		// }
		Log.d(TAG, "url-->" + builder.toString());
		return builder.toString();
	}
}
