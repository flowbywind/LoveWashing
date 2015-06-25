package com.easylife.app_wash.view;

import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.easylife.app_wash.R;
import com.easylife.app_wash.widget.BaseActivity;
import com.easylife.app_wash.widget.ConnectionNetworkAbleUIBase;
import com.easylife.app_wash.widget.CustomOnClickListener;
import com.sihehui.section_network.http.info.ProcessException;
import com.sihehui.section_network.util.StoreActivity;
import com.sihehui.section_network.util.UserCacheManager;
import com.sihehui.section_network.util.Util;
import com.sihehui.section_vo.vo.LoginVO;
import com.sihehui.section_vo.vo.UserLoginVO;

public class LoginActivity extends BaseActivity {
	protected EditText mName, mPsd;
	protected Button mLogin,mRegist;
	protected TextView  mFindPsd;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		mName = (EditText) findViewById(R.id.name);
		mPsd = (EditText) findViewById(R.id.password);
		mLogin = (Button) findViewById(R.id.login);
		mRegist = (Button) findViewById(R.id.regist);
		mFindPsd = (TextView) findViewById(R.id.findpsd);
		mName.setText(mUser.getMobileNumber());
		mPsd.setText("");
	}

	// ProgressDialogSet progress;

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		StoreActivity.getInstance().addActivity(this);
		// 登录
		mLogin.setOnClickListener(new CustomOnClickListener(LoginActivity.this) {

			@Override
			public void doOnClick(View v) {
				// TODO Auto-generated method stub
				String name = mName.getText().toString();
				String psd = mPsd.getText().toString();
				if (name == null || "".equals(name) ) {
					Toast.makeText(getApplicationContext(), "请输入有效的用户名",
							Toast.LENGTH_SHORT).show();
					return;
				}
				if (psd == null || "".equals(psd) || psd.length() < 6
						|| psd.length() > 16) {
					Toast.makeText(getApplicationContext(),
							"请输入6~16位的有效密码,密码可以包含字母和数字", Toast.LENGTH_SHORT)
							.show();
					return;
				}
				Map<String, Object> input = new HashMap<String, Object>();
				input.put("name", name);
				input.put("psw", psd);

				LoginListener longinListener = new LoginListener(
						LoginActivity.this);
				longinListener.progressDialogSet = progress;
				mServerCxdAPI.login(input,longinListener);
				progress.startProgress();
				// progress.setMsg("正在请求数据,请稍后");
			}
		});

		// 注册
		mRegist.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub

				Intent intent = new Intent(getApplicationContext(),
						RegistActivity.class);
				intent.putExtra("type", 0);
				startActivity(intent);
			}
		});
		mFindPsd.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getApplicationContext(),
						ForgetPassword.class);
				intent.putExtra("type", 1);
				startActivity(intent);
			}
		});

	}

	public class LoginListener extends ConnectionNetworkAbleUIBase<UserLoginVO> {

		public LoginListener(Context context) {
			super(context);
			// TODO Auto-generated constructor stub
		}

		@Override
		public void onSuccessed(UserLoginVO result) throws ProcessException {
			// TODO Auto-generated method stub
			// 增加登录成功后的逻辑，即保存用户名和密码，在splashActivity中跳转使用
			progress.stopProgress();
			if (result == null) {
				return;
			}
//			Log.d("shh", "result.getRetCode()=" + result.getRetCode());
//			Log.d("shh", "result.getUid()=" + result.getUid());
//			Log.d("shh", "result.getToken()=" + result.getToken());
			if (result!=null) {
				UserCacheManager user = UserCacheManager.getInstance();
				user.init(getBaseContext());
				user.setData(result);
				// user.setUser(result.getUserId(), result.getUserAccout(),
				// result.getUserName(), result.getPassword(),
				// result.getCityId(), result.getUsertypeId(),
				// result.getIndustryId(), result.getBrandName(),
				// result.getMallName());
				Intent intent = new Intent(LoginActivity.this,
						MainActivity.class);
				startActivity(intent);
			}

		}
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
	}

	@Override
	protected void setContentView() {
		// TODO Auto-generated method stub
		setContentView(R.layout.login);
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		// super.onBackPressed();
		StoreActivity.getInstance().exit();
	}

	@Override
	protected String getHeaderTitle() {
		// TODO Auto-generated method stub
		return "登  录";
	}

}
