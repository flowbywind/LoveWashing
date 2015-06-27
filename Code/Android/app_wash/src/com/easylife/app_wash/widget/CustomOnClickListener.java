package com.easylife.app_wash.widget;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;

import com.sihehui.section_network.util.Util;

public abstract class CustomOnClickListener implements OnClickListener {
	Context context;

	public CustomOnClickListener(Context context) {
		this.context = context;

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if (Util.checkNetwork(context)) {
			doOnClick(v);
		} else {
			Intent intent = new Intent();
			intent.setClassName(context,
					"com.yunbizhi.app_ybz.view.NetsetDialogActivity");
			context.startActivity(intent);
		}
	}

	public abstract void doOnClick(View v);
}
