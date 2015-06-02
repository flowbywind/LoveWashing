package com.easylife.app_wash.widget;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.sihehui.section_network.http.info.ConnectionNetworkAbleBase;

public abstract class ConnectionNetworkAbleUIBase<T> extends
		ConnectionNetworkAbleBase<T> {

	public ProgressDialogSet progressDialogSet;

	public ConnectionNetworkAbleUIBase(Context context) {
		super(context);
	}

	public ConnectionNetworkAbleUIBase(Context context,
			ProgressDialogSet progressDialogSet) {
		super(context);
		this.progressDialogSet = progressDialogSet;
	}

	public void stopProgress() {
		if (progressDialogSet != null) {
			progressDialogSet.stopProgress();
		}
	}

	@Override
	public void onConnectionTimeoutError() {
		stopProgress();
		if (context != null) {
			Toast.makeText(context, "请求超时,请稍后重试", Toast.LENGTH_SHORT).show();
		}
	}

	@Override
	public void onNetworkError() {
		stopProgress();
		if (context != null) {
			Toast.makeText(context, "当前网络不稳定或已断开,请稍后重试", Toast.LENGTH_SHORT)
					.show();
		}

	}

	@Override
	public void onError(String code, String message) {
		stopProgress();
		// Log.d("shh", "message=" + message);
		if (message != null && context != null) {
			Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
		}
	}
}
