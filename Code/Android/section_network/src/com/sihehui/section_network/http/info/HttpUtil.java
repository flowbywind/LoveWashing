package com.sihehui.section_network.http.info;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.HttpEntity;
import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.HttpResponse;
import org.apache.http.HttpResponseInterceptor;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HttpContext;

import android.content.Context;
import android.util.Log;

import com.sihehui.section_network.util.Contacts;

public class HttpUtil {

	// @Deprecated
	// public static final String post(Context context, String url,
	// List<NameValuePair> params) {
	// if (params == null) {
	// params = new ArrayList<NameValuePair>();
	// }
	// params.add(new BasicNameValuePair("imei",
	// Contacts.getInstance(context).IMEI));
	// params.add(new BasicNameValuePair("version",
	// Contacts.getInstance(context).VERSION));
	// params.add(new BasicNameValuePair("partnerId",
	// Contacts.getInstance(context).CHANNEL));
	// params.add(new BasicNameValuePair("package",
	// Contacts.getInstance(context).PACKAGE));
	// try {
	// return post(url, params);
	// } catch (ProcessException e) {
	// e.printStackTrace();
	// }
	// return "";
	// }
	//
	// @Deprecated
	// public static final String post(String url, List<NameValuePair> params)
	// throws ProcessException {
	// return post(url, params, 10000, 5000);
	// }

	/**
	 * 转换参数至标准
	 * 
	 * @param input
	 * @return
	 */
	@Deprecated
	public static final List<NameValuePair> convertParatemersAndSign(
			Map<String, String> input) {
		// sort
		sortParameters(input);

		// map to list
		List<NameValuePair> parameters = new ArrayList<NameValuePair>();
		for (String key : input.keySet()) {
			parameters.add(new BasicNameValuePair(key, input.get(key)));
		}

		// sign
		addSign(parameters);

		return parameters;
	}

	/**
	 * 为所有参数增加签名
	 * 
	 * @param params
	 */
	@Deprecated
	public static final void addSign(List<NameValuePair> params) {
		StringBuffer content = new StringBuffer();
		for (NameValuePair param : params) {
			String key = param.getName();
			if ("sign".equals(key) || "_user".equals(key)) {
				continue;
			}

			String value = param.getValue();
			content.append((content.length() == 0 ? "" : "&"))
					.append(key)
					.append("=")
					.append((value == null || value.trim().length() == 0) ? ""
							: value);
		}
		// params.add(new BasicNameValuePair("sign", MD5Util.MD5Encode(content
		// .toString() + Contacts.SIGN_NAME)));
	}

	public static final String convertParameter(Map<String, ?> params) {
		List<String> keys = new ArrayList<String>(params.keySet());
		Collections.sort(keys);
		StringBuffer content = new StringBuffer();
		for (String key : keys) {
			Object value = params.get(key);
			content.append((content.length() == 0 ? "" : "&")).append(key)
					.append("=").append(value == null ? "" : value);
		}
		Log.d("convertParameter.result", content.toString());
		System.out.println("convertParameter.result "+content.toString());
		return content.toString();
	}

	/**
	 * 按照key对参数进行排序
	 * 
	 * @param params
	 */
	@Deprecated
	public static final void sortParameters(Map<String, ?> params) {
		// 按照key做排序
		List<String> keys = new ArrayList<String>(params.keySet());
		Collections.sort(keys);
	}

	/**
	 * 获取一个包函所有通用参数(imei,version,partnerId,package)的列表
	 * 
	 * @param context
	 * @return
	 */
	public static final Map<String, String> getCommonParameters(Context context) {
		Map<String, String> params = new LinkedHashMap<String, String>();
		params.put("imei", Contacts.getInstance(context).IMEI);
		params.put("version", Contacts.getInstance(context).VERSION);
		// params.put("partnerId", Contacts.getInstance(context).CHANNEL);
		params.put("package", Contacts.getInstance(context).PACKAGE);
		// params.put("clientsource", Contacts.getInstance(context).WEB_SOURCE);
		return params;
	}
	public static final Map<String, Object> getCxdCommonParameters(Context context) {
		Map<String, Object> params = new LinkedHashMap<String, Object>();
		params.put("imei", Contacts.getInstance(context).IMEI);
		params.put("version", Contacts.getInstance(context).VERSION);
		// params.put("partnerId", Contacts.getInstance(context).CHANNEL);
		params.put("package", Contacts.getInstance(context).PACKAGE);
		// params.put("clientsource", Contacts.getInstance(context).WEB_SOURCE);
		return params;
	}

	public static final boolean validateCommonParameters(
			Map<String, String> input) {
		return input != null && input.get("imei") != null
				&& input.get("version") != null && input.get("package") != null;
	}

	/**
	 * 发送一个Http协义的POST请求并返回结果
	 * 
	 * @param url
	 * @param params
	 *            至少需要包括imei,version,partnerId,package参数
	 * @param timeoutConnection
	 *            超时时间
	 * @param timeoutSocket
	 *            超时时间
	 * @return
	 * @throws ProcessException
	 */
	@Deprecated
	public static final String post(String url, List<NameValuePair> params,
			int timeoutConnection, int timeoutSocket) throws ProcessException {

		// 设置请求超时
		HttpParams httpParameters = new BasicHttpParams();
		// Set the timeout in milliseconds until a connection is established.
		// The default value is zero, that means the timeout is not used.
		HttpConnectionParams.setConnectionTimeout(httpParameters,
				timeoutConnection);
		// Set the default socket timeout (SO_TIMEOUT)
		// in milliseconds which is the timeout for waiting for data.
		HttpConnectionParams.setSoTimeout(httpParameters, timeoutSocket);

		DefaultHttpClient httpClient = new DefaultHttpClient(httpParameters);
		// add gzip
		httpClient.addRequestInterceptor(new HttpRequestInterceptor() {

			@Override
			public void process(HttpRequest request, HttpContext context)
					throws HttpException, IOException {
				if (!request.containsHeader("Accept-Encoding")) {
					request.addHeader("Accept-Encoding", "gzip");
				}
			}
		});

		httpClient.addResponseInterceptor(new HttpResponseInterceptor() {

			@Override
			public void process(HttpResponse response, HttpContext context)
					throws HttpException, IOException {
				HttpEntity entity = response.getEntity();
				if (entity != null) {
					Header ceheader = entity.getContentEncoding();
					if (ceheader != null) {
						HeaderElement[] codecs = ceheader.getElements();
						for (int i = 0; i < codecs.length; i++) {
							if (codecs[i].getName().equalsIgnoreCase("gzip")) {
								response.setEntity(new GzipDecompressingEntity(
										response.getEntity()));
								return;
							}
						}
					}
				}
			}
		});

		String result = "";
		HttpPost http = new HttpPost(url);
		BufferedReader in = null;
		try {
			// 设置实体
			http.setEntity(new UrlEncodedFormEntity(params, "utf8"));
			HttpResponse response = httpClient.execute(http);
			if (response.getStatusLine().getStatusCode() == 200) {
				// 获取实体,并转换成字符串
				// /response.getEntity().getContent();
				StringBuffer sb = new StringBuffer();
				in = new BufferedReader(new InputStreamReader(response
						.getEntity().getContent()));
				String line = in.readLine();
				while (line != null) {
					sb.append(line);
					line = in.readLine();
				}
				result = sb.toString();
				// Log.d("system","history_resXml   __>" + result);
			} else {
				throw new ProcessException("错误代码："
						+ response.getStatusLine().getStatusCode(),
						ProcessException.HTTP_STATUS_ERROR);
			}

		} catch (IOException e) {
			e.printStackTrace();
			throw new ProcessException(e.getMessage(),
					ProcessException.IO_EXCEPTION);
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (Throwable e) {
				}
			}
			httpClient.getConnectionManager().shutdown();
		}
		return result;
	}

	@Deprecated
	public static final HttpEntity post2(String url,
			List<NameValuePair> params, int timeoutConnection, int timeoutSocket)
			throws ProcessException {

		// 设置请求超时
		HttpParams httpParameters = new BasicHttpParams();
		// Set the timeout in milliseconds until a connection is established.
		// The default value is zero, that means the timeout is not used.
		HttpConnectionParams.setConnectionTimeout(httpParameters,
				timeoutConnection);
		// Set the default socket timeout (SO_TIMEOUT)
		// in milliseconds which is the timeout for waiting for data.
		HttpConnectionParams.setSoTimeout(httpParameters, timeoutSocket);

		DefaultHttpClient httpClient = new DefaultHttpClient(httpParameters);
		// add gzip
		httpClient.addRequestInterceptor(new HttpRequestInterceptor() {

			@Override
			public void process(HttpRequest request, HttpContext context)
					throws HttpException, IOException {
				if (!request.containsHeader("Accept-Encoding")) {
					request.addHeader("Accept-Encoding", "gzip");
				}
			}
		});

		httpClient.addResponseInterceptor(new HttpResponseInterceptor() {

			@Override
			public void process(HttpResponse response, HttpContext context)
					throws HttpException, IOException {
				HttpEntity entity = response.getEntity();
				if (entity != null) {
					Header ceheader = entity.getContentEncoding();
					if (ceheader != null) {
						HeaderElement[] codecs = ceheader.getElements();
						for (int i = 0; i < codecs.length; i++) {
							if (codecs[i].getName().equalsIgnoreCase("gzip")) {
								response.setEntity(new GzipDecompressingEntity(
										response.getEntity()));
								return;
							}
						}
					}
				}
			}
		});

		HttpEntity result = null;
		HttpPost http = new HttpPost(url);
		try {
			// 设置实体
			http.setEntity(new UrlEncodedFormEntity(params, "utf8"));
			HttpResponse response = httpClient.execute(http);
			if (response.getStatusLine().getStatusCode() == 200) {
				// 获取实体,并转换成字符串
				// /response.getEntity().getContent();

				result = response.getEntity();
				// Log.d("system","history_resXml   __>" + result);
			} else {
				throw new ProcessException("错误代码："
						+ response.getStatusLine().getStatusCode(),
						ProcessException.HTTP_STATUS_ERROR);
			}

		} catch (IOException e) {
			e.printStackTrace();
			throw new ProcessException(e.getMessage(),
					ProcessException.IO_EXCEPTION);
		} finally {
			// httpClient.getConnectionManager().shutdown();
		}
		return result;
	}

}
