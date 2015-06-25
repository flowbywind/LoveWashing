package com.easylife.app_wash.view;

import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.easylife.app_wash.R;
import com.easylife.app_wash.widget.BaseActivity;
import com.easylife.app_wash.widget.ConnectionNetworkAbleUIBase;
import com.easylife.app_wash.widget.CustomOnClickListener;
import com.sihehui.section_network.http.info.ProcessException;
import com.sihehui.section_network.util.StoreActivity;
import com.sihehui.section_vo.vo.DefaultVO;

public class RegistActivity extends BaseActivity {
	private int type = 0;// 判断是注册还是找回密码

	protected EditText mName, mPsd, mToken;
	Button btnnext;
	RelativeLayout mRelative;
	TextView mTitle;
    private String PhoneNo;
	// mQueding, mGetToken,
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		type = getIntent().getIntExtra("type", 0);
		// mTitle = (TextView) findViewById(R.id.title);
		mName = (EditText) findViewById(R.id.phone);
		// mPsd = (EditText) findViewById(R.id.password);
		// mToken = (EditText) findViewById(R.id.token);
		// mGetToken = (Button) findViewById(R.id.getToken);
		// mQueding = (Button) findViewById(R.id.queding);
		btnnext = (Button) findViewById(R.id.next);
	}

	// 判断是否为注册或者找回密码
	private boolean isRegist() {
		if (type == 0) {
			return true;
		}
		return false;
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		StoreActivity.getInstance().addActivity(this);
		btnnext.setOnClickListener(new CustomOnClickListener(this) {

			@Override
			public void doOnClick(View v) {
				// TODO Auto-generated method stub
				String name = mName.getText().toString().trim();
				if (name == null || "".equals(name)) {
					Toast.makeText(getApplicationContext(), "请输入手机号", 1000)
							.show();
					return;
				}
				PhoneNo=name;
				ApplyListener listener = new ApplyListener(RegistActivity.this);
				Map<String, Object> input = new HashMap<String, Object>();
				input.put("phoneNo", name);

			    mServerCxdAPI.getAuthCode(input, listener);

			}
		});
	}

	// 获取验证码
	private class ApplyListener extends ConnectionNetworkAbleUIBase<DefaultVO> {
        
		public ApplyListener(Context context) {
			super(context);
			// TODO Auto-generated constructor stub
		}

		@Override
		public void onSuccessed(DefaultVO result) throws ProcessException {
			// TODO Auto-generated method stub
			// 增加登录成功后的逻辑，即保存用户名和密码，在splashActivity中跳转使用
			Log.d("shh", "result=" + result);
			// initHandler();
			if (result == null) {
				return;
			}
			Intent intent = new Intent(RegistActivity.this,
					SetPasswordActivity.class);
			intent.putExtra("phoneNo", PhoneNo);
			startActivity(intent);

		}

		@Override
		public void onError(String code, String message) {
			// TODO Auto-generated method stub
			super.onError(code, message);
			// initHandler();
			Log.d("shh", "code=" + code + " message=" + message);
		}

		@Override
		public void onConnectionTimeoutError() {
			// TODO Auto-generated method stub
			super.onConnectionTimeoutError();
			// initHandler();
		}

		@Override
		public void onNetworkError() {
			// TODO Auto-generated method stub
			super.onNetworkError();
			// initHandler();
		}

	}

	// // 注册或找回密码
	// private class RegistListener extends
	// ConnectionNetworkAbleUIBase<RegistVO> {
	//
	// public RegistListener(Context context) {
	// super(context);
	// // TODO Auto-generated constructor stub
	// }
	//
	// @Override
	// public void onSuccessed(RegistVO result) throws ProcessException {
	// // TODO Auto-generated method stub
	// // 增加登录成功后的逻辑，即保存用户名和密码，在splashActivity中跳转使用
	// Log.d("shh", "result=" + result);
	// stopProgress();
	// if (result == null) {
	// return;
	// }
	// Log.d("shh", "result.getRetCode()=" + result.getRetCode());
	// Log.d("shh", "result.getUid()=" + result.getUid());
	// Log.d("shh", "result.getToken()=" + result.getToken());
	// if (result.getRetCode() == 1) {
	// if (isRegist()) {
	// UserCacheManager user = UserCacheManager.getInstance();
	// user.init(getBaseContext());
	// user.setData(result.getUid(), result.getToken());
	// Intent intent = new Intent(RegistActivity.this,
	// IdentificationActivity.class);
	// startActivity(intent);
	// } else {
	// // 找回密码处理
	// Toast.makeText(getApplicationContext(), "您已成功找回密码",
	// Toast.LENGTH_SHORT).show();
	// Intent intent = new Intent(RegistActivity.this,
	// MainActivity.class);
	// startActivity(intent);
	// }
	// }
	//
	// }
	// }

	// Handler handler = new Handler() {
	//
	// @Override
	// public void handleMessage(Message msg) {
	// // TODO Auto-generated method stub
	// super.handleMessage(msg);
	// // Log.d("zhangyaobin", "totalTime=" + totalTime);
	// if (totalTime > 0) {
	// mGetToken.setText(totalTime + "s");
	// handler.postDelayed(run, 1000);
	// if (mGetToken.isEnabled()) {
	// mGetToken.setEnabled(false);
	// }
	//
	// } else {
	// initHandler();
	// // totalTime = 60;
	// // handler.removeCallbacks(run);
	// // mGetToken.setEnabled(true);
	// // mGetToken.setText("重新获取验证码");
	// }
	// }
	//
	// };

	// 初始化handler
	// private void initHandler() {
	// totalTime = 60;
	// handler.removeCallbacks(run);
	// mGetToken.setEnabled(true);
	// mGetToken.setText("获取验证码");
	// }

	// int totalTime = 60;
	// Runnable run = new Runnable() {
	//
	// @Override
	// public void run() {
	// // TODO Auto-generated method stub
	// // try {
	// // Thread.sleep(1000l);
	// totalTime--;
	// // } catch (InterruptedException e) {
	// // // TODO Auto-generated catch block
	// // e.printStackTrace();
	// // }
	// handler.sendEmptyMessage(0);
	// }
	// };

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
	}

	@Override
	protected void setContentView() {
		// TODO Auto-generated method stub
		setContentView(R.layout.regist);
	}

	@Override
	protected String getHeaderTitle() {
		// TODO Auto-generated method stub
		return "注册";
	}

}
