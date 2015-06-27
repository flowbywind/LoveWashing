package com.sihehui.section_network.http.json;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Request.Method;
import com.android.volley.toolbox.HurlStack;

public class MultiPartStack extends HurlStack {
	@SuppressWarnings("unused")
	private static final String TAG = MultiPartStack.class.getSimpleName();
	private final static String HEADER_CONTENT_TYPE = "Content-Type";

	@Override
	public HttpResponse performRequest(Request<?> request,
			Map<String, String> additionalHeaders) throws IOException,
			AuthFailureError {

		if (!(request instanceof MultiPartRequest)) {
			return super.performRequest(request, additionalHeaders);
		} else {
			return performMultiPartRequest(request, additionalHeaders);
		}
	}

	private static void addHeaders(HttpUriRequest httpRequest,
			Map<String, String> headers) {
		for (String key : headers.keySet()) {
			httpRequest.setHeader(key, headers.get(key));
		}
	}

	public HttpResponse performMultiPartRequest(Request<?> request,
			Map<String, String> additionalHeaders) throws IOException,
			AuthFailureError {
		HttpUriRequest httpRequest = createMultiPartRequest(request,
				additionalHeaders);
		addHeaders(httpRequest, additionalHeaders);
		addHeaders(httpRequest, request.getHeaders());
		HttpParams httpParams = httpRequest.getParams();
		int timeoutMs = request.getTimeoutMs();
		// TODO: Reevaluate this connection timeout based on more wide-scale
		// data collection and possibly different for wifi vs. 3G.
		HttpConnectionParams.setConnectionTimeout(httpParams, 5000);
		HttpConnectionParams.setSoTimeout(httpParams, timeoutMs);

		/* Make a thread safe connection manager for the client */
		HttpClient httpClient = new DefaultHttpClient(httpParams);

		return httpClient.execute(httpRequest);
	}

	static HttpUriRequest createMultiPartRequest(Request<?> request,
			Map<String, String> additionalHeaders) throws AuthFailureError {
		switch (request.getMethod()) {
		case Method.DEPRECATED_GET_OR_POST: {
			// This is the deprecated way that needs to be handled for backwards
			// compatibility.
			// If the request's post body is null, then the assumption is that
			// the request is
			// GET. Otherwise, it is assumed that the request is a POST.
			byte[] postBody = request.getBody();
			if (postBody != null) {
				HttpPost postRequest = new HttpPost(request.getUrl());
				if (request.getBodyContentType() != null)
					postRequest.addHeader(HEADER_CONTENT_TYPE,
							request.getBodyContentType());
				HttpEntity entity;
				entity = new ByteArrayEntity(postBody);
				postRequest.setEntity(entity);
				return postRequest;
			} else {
				return new HttpGet(request.getUrl());
			}
		}
		case Method.GET:
			return new HttpGet(request.getUrl());
		case Method.DELETE:
			return new HttpDelete(request.getUrl());
		case Method.POST: {
			HttpPost postRequest = new HttpPost(request.getUrl());
			postRequest.addHeader("contentType", "application/json");
			// postRequest.addHeader("contentType","application/x-www-form-urlencoded; charset=utf-8");

			// postRequest.addHeader("charset", HTTP.UTF_8);
			// postRequest.getParams().setParameter(HTTP.CONTENT_ENCODING,
			// HTTP.UTF_8);
			postRequest.getParams().setParameter(
					HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF_8");
			// postRequest.addHeader(HEADER_CONTENT_TYPE,
			// request.getBodyContentType());
			setMultiPartBody(postRequest, request);
			return postRequest;
		}
		case Method.PUT: {
			HttpPut putRequest = new HttpPut(request.getUrl());
			// if (request.getBodyContentType() != null)
			// putRequest.addHeader(HEADER_CONTENT_TYPE,
			// request.getBodyContentType());
			// setMultiPartBody(putRequest, request);
			return putRequest;
		}
		// Added in source code of Volley libray.
		// case Method.PATCH: {
		// HttpPatch patchRequest = new HttpPatch(request.getUrl());
		// if (request.getBodyContentType() != null)
		// patchRequest.addHeader(HEADER_CONTENT_TYPE,
		// request.getBodyContentType());
		// return patchRequest;
		// }
		default:
			throw new IllegalStateException("Unknown request method.");
		}
	}

	/**
	 * If Request is MultiPartRequest type, then set MultipartEntity in the
	 * httpRequest object.
	 * 
	 * @param httpRequest
	 * @param request
	 * @throws AuthFailureError
	 */
	@SuppressWarnings("deprecation")
	private static void setMultiPartBody(HttpPost httpRequest,
			Request<?> request) throws AuthFailureError {
		// HttpEntityEnclosingRequestBase httpRequest
		// Return if Request is not MultiPartRequest
		if (!(request instanceof MultiPartRequest)) {
			return;
		}

		// MultipartEntity multipartEntity = new
		// MultipartEntity(HttpMultipartMode.BROWSER_COMPATIBLE);

		MultipartEntityBuilder builder = MultipartEntityBuilder.create();

		/* example for setting a HttpMultipartMode */
		// builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);

		// Iterate the fileUploads
		Map<String, Object> fileUpload = ((MultiPartRequest) request)
				.getFileUploads();
		if (fileUpload != null) {
			if (fileUpload != null) {
				Set<String> set = fileUpload.keySet();
				for (Iterator<String> iterator = set.iterator(); iterator
						.hasNext();) {
					String key = (String) iterator.next();
					Object obj = fileUpload.get(key);
					if (obj instanceof String) {
						builder.addTextBody(key, obj.toString(),
								ContentType.APPLICATION_JSON);
					} else if (obj instanceof File) {
						// Log.d("shh", "obj instanceof File");
						builder.addBinaryBody(key, (File) obj);
					} else if (obj instanceof File[]) {
						// Log.d("shh", "obj instanceof File[]");
						File[] files = (File[]) obj;
						for (int i = 0; i < files.length; i++) {
							builder.addBinaryBody(key, files[i]);
						}
					}

				}
			}
		}
		// Iterate the stringUploads
		Map<String, String> stringUpload = ((MultiPartRequest) request)
				.getStringUploads();
		if (stringUpload != null) {
			String json = stringUpload.get("value");
			 Log.d("cxd", "jsonRequest=" + json);
			try {
				StringEntity entity = new StringEntity(json);
				httpRequest.setEntity(entity);
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// for (Map.Entry<String, String> entry : stringUpload.entrySet()) {
			// Log.d("cxd", "stringUpload.entry.getKey()=" + entry.getKey()
			// + "  entry.getValue()=" + entry.getValue());
			// try {
			// builder.addPart(((String) entry.getKey()), new StringBody(
			// (String) entry.getValue()));
			// } catch (Exception e) {
			// e.printStackTrace();
			// }
			// }
			// Log.d("cxd", "builder.toString()=" + builder.toString());
			// httpRequest.setEntity(builder.build());
			// UrlEncodedFormEntity p_entity = new UrlEncodedFormEntity(pairs,
			// "utf-8");

		}
	}
}
