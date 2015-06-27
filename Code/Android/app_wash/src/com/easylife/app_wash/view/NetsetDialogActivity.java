package com.easylife.app_wash.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.easylife.app_wash.R;
import com.sihehui.section_network.util.NetUtil;

public class NetsetDialogActivity extends Activity {

	Button set, cancel;

	// SharedPreferences mSettingSharedPreferences;
	// Editor mSettingEditor;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.netset);
		set = (Button) findViewById(R.id.set);
		cancel = (Button) findViewById(R.id.cancel);
	}

	private void listener() {
		set.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				NetUtil.netSet(NetsetDialogActivity.this);
				finish();
			}
		});
		cancel.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				finish();
			}
		});
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		listener();
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

	@Override
	protected void onNewIntent(Intent intent) {
		// TODO Auto-generated method stub
		super.onNewIntent(intent);
		finish();
	}

}
