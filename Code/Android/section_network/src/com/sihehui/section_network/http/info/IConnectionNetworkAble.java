package com.sihehui.section_network.http.info;

import android.content.Context;

public interface IConnectionNetworkAble<T> extends ISuccessResultListener<T>, IFailureListener {
	Context getContext();
	
}
