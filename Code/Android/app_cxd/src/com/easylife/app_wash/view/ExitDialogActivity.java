package com.easylife.app_wash.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.easylife.app_wash.R;
import com.sihehui.section_network.util.StoreActivity;
import com.sihehui.section_network.util.UserCacheManager;

public class ExitDialogActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dialog_exit);
		StoreActivity.getInstance().addActivity(this);

		Button queding = (Button) findViewById(R.id.ensure);
		Button cancel = (Button) findViewById(R.id.cancel);
		Button dismiss = (Button) findViewById(R.id.dismiss);

		TextView content = (TextView) findViewById(R.id.content);
		String str = getIntent().getStringExtra("content");
		if (str != null && !"".equals(str)) {
			content.setText(str);
		}

		View guanbi = findViewById(R.id.title);
		queding.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (getIntent().getBooleanExtra("clearData", false)) {
					UserCacheManager user = UserCacheManager.getInstance();
					user.init(getBaseContext());
					user.clearData();
					Intent intent = new Intent(ExitDialogActivity.this,
							LoginActivity.class);
					startActivity(intent);
					finish();
				} else {
					StoreActivity.getInstance().exit();
				}
			}
		});

		cancel.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();
			}
		});

		dismiss.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();
			}
		});

		guanbi.setOnClickListener(new OnClickListener() {

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
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
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

}
