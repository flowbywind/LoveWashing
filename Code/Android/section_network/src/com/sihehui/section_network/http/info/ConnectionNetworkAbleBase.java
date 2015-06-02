package com.sihehui.section_network.http.info;

import java.util.Map;

import org.json.JSONObject;

import android.content.Context;

public abstract class ConnectionNetworkAbleBase<T> implements IConnectionNetworkAble<T> {
	protected Context context;

	public ConnectionNetworkAbleBase(Context context) {
		this.context = context;
	}

	@Override
	public Context getContext() {
		return context;
	}

	@Override
	public void onSuccessed(Map<String, Object> headers, T result, JSONObject json) throws ProcessException {
		onSuccessed(result);
	}

}
