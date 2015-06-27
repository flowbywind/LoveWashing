package com.easylife.app_wash.view;

import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.easylife.app_wash.R;
import com.easylife.app_wash.widget.BaseActivity;
import com.easylife.app_wash.widget.ConnectionNetworkAbleUIBase;
import com.sihehui.section_network.http.info.ProcessException;
import com.sihehui.section_vo.vo.DefaultVO;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ResetPasswordActivity extends BaseActivity {

	Button btnGetCode, btnResetPwd;
	EditText mAuthCode, mNewPsw;
	Handler handler;
	private int Seconds;
	private int bluecolor;
	private int darkcolor;
	private String PhoneNo;

	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		btnGetCode = (Button) findViewById(R.id.btn_getcode);
		btnResetPwd = (Button) findViewById(R.id.btnok);
		mAuthCode = (EditText) findViewById(R.id.txt_authcode);
		mNewPsw = (EditText) findViewById(R.id.txt_newpsw);
		PhoneNo = getIntent().getStringExtra("phoneNo");
	}

	Runnable runnable = new Runnable() {
		@Override
		public void run() {
			// TODO Auto-generated method stub
			handler.sendEmptyMessage(1);
		}
	};

	@Override
	protected void onStart() {

		// TODO Auto-generated method stub
		super.onStart();
		bluecolor = R.color.fontcolor_skyblue;
		darkcolor = R.color.fontcolor_dark;
		handler = new Handler() {
			public void handleMessage(android.os.Message msg) {
				if (msg.what == 1) {
					if (Seconds > 0) {
						Seconds = Seconds - 1;
						btnGetCode.setText("获取验证码(" + Seconds + ")");
						handler.postDelayed(runnable, 1000);
					} else {
						btnGetCode.setText("获取验证码");
						btnGetCode.setTextColor(ResetPasswordActivity.this
								.getResources().getColor(bluecolor));
						btnGetCode.setEnabled(true);
					}
				}
			};
		};

		btnGetCode.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				btnGetCode.setEnabled(false);
				btnGetCode.setTextColor(darkcolor);
				Seconds = 60;
				runnable.run();

				ConnectionNetworkAbleUIBase<DefaultVO> listener = new ConnectionNetworkAbleUIBase<DefaultVO>(
						ResetPasswordActivity.this) {
					@Override
					public void onSuccessed(DefaultVO result)
							throws ProcessException {
						// TODO Auto-generated method stub
					}
				};
				Map<String, Object> input = new HashMap<String, Object>();
				input.put("phoneNo", PhoneNo);
				System.out.println("phoneNO:" + PhoneNo);
				mServerCxdAPI.getAuthCode(input, listener);
			}
		});
		btnResetPwd.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ResetPwdListener listener=new ResetPwdListener(ResetPasswordActivity.this);
				Map<String,Object> input=new HashMap<String,Object>();
				String strauthCode=mAuthCode.getText().toString().trim();
				String strnewpsw=mNewPsw.getText().toString().trim();
				input.put("authCode", strauthCode);
				input.put("newPsw", strnewpsw);
				input.put("phoneNo", PhoneNo);
				mServerCxdAPI.resetPsw(input, listener);
			}
		});
	}

	@Override
	protected void setContentView() {
		// TODO Auto-generated method stub
		setContentView(R.layout.reset_password);
	}

	@Override
	protected String getHeaderTitle() {
		// TODO Auto-generated method stub
		return "重置密码";
	}
	private class ResetPwdListener extends ConnectionNetworkAbleUIBase<DefaultVO>{

		public ResetPwdListener(Context context) {
			super(context);
			// TODO Auto-generated constructor stub
		}

		@Override
		public void onSuccessed(DefaultVO result) throws ProcessException {
			// TODO Auto-generated method stub
			String data=result.getData();
			Toast.makeText(getApplicationContext(), data, Toast.LENGTH_SHORT).show();
			if(data=="重置失败")
			{
				
			}
			else
			{
				Intent intent=new Intent(ResetPasswordActivity.this,LoginActivity.class);
				intent.putExtra("phoneNo", PhoneNo);
				startActivity(intent);
			}
		}
		@Override
		public void onError(String code, String message) {
			// TODO Auto-generated method stub
			super.onError(code, message);
		}
	}
}
