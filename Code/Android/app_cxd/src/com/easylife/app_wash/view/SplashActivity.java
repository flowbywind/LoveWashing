package com.easylife.app_wash.view;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.easylife.app_wash.R;
import com.sihehui.section_network.util.Contacts;
import com.sihehui.section_network.util.UserCacheManager;

public class SplashActivity extends Activity {
	AnimationDrawable rocketAnimation;
	// PhoneReceiver receiver;
	public static final int EVERYTHING_OK = 1; // 环境一切OK
	TextView showVersion;

	@Override
	public void onWindowFocusChanged(boolean hasFocus) {
		super.onWindowFocusChanged(hasFocus);
		Log.d("zhangyaobin", "hasFocus=" + hasFocus);
		/*
		 * if (hasFocus) { final ImageView rocketImage = (ImageView)
		 * findViewById(R.id.loading_animation);
		 * rocketImage.setBackgroundResource(R.drawable.animation_loading);
		 * rocketAnimation = (AnimationDrawable) rocketImage.getBackground();
		 * rocketAnimation.start(); }
		 */

	}

	Dialog warningDialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash);
		RelativeLayout layout_splash = (RelativeLayout) findViewById(R.id.layout_splash);
		if (isDifferentVersion()) {
			Intent intent = new Intent();
			intent.setClass(SplashActivity.this, HelpCenterActivity.class);
			intent.putExtra("ids", new int[] { R.drawable.bg_guide1});
			intent.putExtra("tag", 1);
			startActivity(intent);
			finish();
		} else {
			layout_splash.setBackgroundResource(R.drawable.bg_splash);
			mHandler.postDelayed(run, 1000);
			showVersion = (TextView) findViewById(R.id.showVersionNum);
			Contacts contacts = Contacts.getInstance(this);
			if (showVersion != null) {
				showVersion.setText("版本 " + contacts.VERSION);
			}
		}
		// Signature[] sigs;
		// try {
		// sigs = getPackageManager().getPackageInfo(getPackageName(),
		// PackageManager.GET_SIGNATURES).signatures;
		// int signCode = sigs[0].hashCode();
		// Log.d("shh", "signCode=" + signCode);
		//
		// if (signCode == -801120786) {
		// isOfficial = true;
		//
		// } else {
		// isOfficial = false;
		// if (warningDialog == null) {
		// warningDialog = new Dialog(SplashActivity.this,
		// R.style.dialog);
		// }
		// View view = getLayoutInflater().inflate(
		// R.layout.dialog_warning, null);
		// warningDialog.setContentView(view);
		// warningDialog.setCancelable(false);
		// if (!warningDialog.isShowing()) {
		// warningDialog.show();
		// }
		// Button download = (Button) view.findViewById(R.id.queding);
		// download.setOnClickListener(new CustomOnClickListener(
		// SplashActivity.this) {
		//
		// @Override
		// protected void doInOnClick(View v) {
		// // TODO Auto-generated method stub
		// Uri uri = Uri
		// .parse("http://3g.iletou.com/android.html");
		// Intent intent = new Intent(Intent.ACTION_VIEW, uri);
		// startActivity(intent);
		// finish();
		// }
		// });
		// return;
		// }

		// for (Signature sig : sigs)
		// {
		// Log.i("App", "Signature : " + sig.hashCode());
		// }
		// } catch (NameNotFoundException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }

	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();

		// IntentFilter filter = new IntentFilter(
		// "android.intent.action.PHONE_STATE");
		// receiver = new PhoneReceiver();
		// registerReceiver(receiver, filter);
		// isLoading = false;

	}

	private void performNextActivity() {
		Intent intent = new Intent();
		// intent.setClass(getApplicationContext(),
		// IdentificationActivity.class);
		if (isFirstRun()) {
			intent.setClass(getApplicationContext(), LoginActivity.class);
		} else {
			intent.setClass(getApplicationContext(), MainActivity.class);
		}
		startActivity(intent);
		finish();
	}

	Handler mHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case EVERYTHING_OK: //

				// if (isDifferentVersion()) {
				// Intent intent = new Intent();
				// intent.setClass(SplashActivity.this,
				// HelpCenterActivity.class);
				// intent.putExtra("ids", new int[] { R.drawable.bg_guide1,
				// R.drawable.bg_guide2, R.drawable.bg_guide3,
				// R.drawable.bg_guide4 });
				// intent.putExtra("tag", 1);
				// startActivity(intent);
				// finish();
				// } else {
				performNextActivity();
				// }

				break;
			}
		}
	};
	Runnable run = new Runnable() {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			// 1秒以后自动启动主程序
			// try {
			// Thread.sleep(2350);
			mHandler.sendEmptyMessage(EVERYTHING_OK);
			// } catch (InterruptedException e) {
			// // TODO Auto-generated catch block
			// e.printStackTrace();
			// }

		}
	};

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();

		if (rocketAnimation != null) {
			rocketAnimation.stop();
		}
		Log.d("Main", "onStopsplash");
	}

	/**
	 * 判断 用户是否登录注册
	 * 
	 * @return
	 */
	private boolean isFirstRun() {
		UserCacheManager user = UserCacheManager.getInstance();
		user.init(getBaseContext());
		String uid = user.getUid();
		// Editor editor = mSharedPreferences.edit();
		boolean isFisrtRun = "".equals(uid);
		// if (isFisrtRun) {
		// editor.putBoolean("isfisrtRun", false);
		// editor.commit();
		// }
		return isFisrtRun;

	}

	/**
	 * 判断是否为不同的版本
	 * 
	 * @return
	 */
	private boolean isDifferentVersion() {
		SharedPreferences mSharedPreferences = getSharedPreferences(
				"isFisrtRun", Activity.MODE_PRIVATE);
		String oldVersion = mSharedPreferences.getString("version", "0.0");
		String version = Contacts.getInstance(this).VERSION;
		if (version.equals(oldVersion)) {
			return false;
		} else {
			Editor editor = mSharedPreferences.edit();
			editor.putString("version", version);
			editor.commit();
			return true;
		}

	}

}
