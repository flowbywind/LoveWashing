package com.easylife.app_wash.view;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.easylife.app_wash.R;
import com.easylife.app_wash.widget.BaseActivity;
import com.sihehui.section_network.util.StoreActivity;

public class MyActivity extends BaseActivity {
	ActivityFragment fragment = null;

	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		FragmentManager fm = getSupportFragmentManager();
		FragmentTransaction ft = fm.beginTransaction();
		fragment = new ActivityFragment();
		ft.replace(R.id.container, fragment);
		ft.commit();
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		StoreActivity.getInstance().addActivity(this);
		// if (fragment != null && fragment.mTitle != null) {
		// fragment.mTitle.setText("我的活动");
		// }
	}

	@Override
	protected void setContentView() {
		// TODO Auto-generated method stub
		setContentView(R.layout.myactivity);
	}

	@Override
	protected String getHeaderTitle() {
		// TODO Auto-generated method stub
		return "我的活动";
	}

}
