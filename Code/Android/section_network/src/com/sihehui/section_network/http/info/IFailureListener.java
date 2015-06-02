package com.sihehui.section_network.http.info;

public interface IFailureListener {
	void onConnectionTimeoutError();

	void onNetworkError();

	void onError(String code, String message);
}
