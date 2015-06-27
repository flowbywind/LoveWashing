package com.easylife.app_wash.view;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.sihehui.section_network.http.info.ProcessException;
import com.sihehui.section_network.util.StoreActivity;
import com.sihehui.section_vo.vo.DefaultVO;
import com.easylife.app_wash.R;
import com.easylife.app_wash.widget.BaseActivity;
import com.easylife.app_wash.widget.ConnectionNetworkAbleUIBase;

public class SetPasswordActivity extends BaseActivity {

	Button btnok;
	Button btnrule;
	Button btnGetCode;
	Handler handler;
	private int Seconds;
	private int bluecolor;
	private int darkcolor;
	private String PhoneNo;

	private EditText mName, mPsw, mAuthCode, mReferrer;
	private TextView mPhone;
	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		btnok = (Button) findViewById(R.id.btnok);
		btnrule = (Button) findViewById(R.id.btn_rule);
		btnGetCode = (Button) findViewById(R.id.btn_getcode);
		mName = (EditText) findViewById(R.id.txt_username);
		mPsw = (EditText) findViewById(R.id.txt_psw);
		mAuthCode = (EditText) findViewById(R.id.txt_authcode);
		mReferrer = (EditText) findViewById(R.id.txt_referrer);
		mPhone = (TextView) findViewById(R.id.txt_phone);
		PhoneNo = getIntent().getStringExtra("phoneNo");
		System.out.println("获取信息");
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
		mPhone.setText("已向手机"+PhoneNo+"发送短信");
		System.out.println("获取信息2");
		StoreActivity.getInstance().addActivity(this);
		btnok.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String strName=mName.getText().toString().trim();
				String strPsw=mPsw.getText().toString().trim();
				String strAuthCode=mAuthCode.getText().toString().trim();
				String strReferrer=mReferrer.getText().toString().trim();
				if(strAuthCode==null || "".equals(strAuthCode))
				{
					Toast.makeText(getApplicationContext(), "验证码不得为空", Toast.LENGTH_SHORT).show();
					return;
				}
				if(strPsw==null || "".equals(strPsw) || strPsw.length()<6 || strPsw.length()>16)
				{
					Toast.makeText(getApplicationContext(), "密码不得为空，且长度不得小于6，大于16", Toast.LENGTH_SHORT).show();
					return ;
				}
				if(strName==null || "".equals(strName) || strName.length()>16)
				{
					Toast.makeText(getApplicationContext(), "用户名不得为空，且长度不得超过16", Toast.LENGTH_SHORT).show();
					return ;
				}
				Map<String,Object> input=new HashMap<String, Object>();
				input.put("name", strName);
				input.put("psw", strPsw);
				input.put("email", "");
				input.put("phoneNo",PhoneNo);
				input.put("authCode", strAuthCode);
				input.put("referrer", strReferrer);
				SetPasswordListener listener=new SetPasswordListener(SetPasswordActivity.this) {
				};
				mServerCxdAPI.registerService(input, listener);
				// TODO Auto-generated method stub

			}
		});
		btnrule.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(SetPasswordActivity.this,
						CommonWebViewActivity.class);
				intent.putExtra("url", "http://www.baidu.com");
				startActivity(intent);
			}
		});
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
						System.out.println("bluecolor:"+SetPasswordActivity.this.getResources().getColor(bluecolor));
						btnGetCode.setTextColor(SetPasswordActivity.this.getResources().getColor(bluecolor));
						btnGetCode.setText("再次获取");
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
						SetPasswordActivity.this) {

					@Override
					public void onSuccessed(DefaultVO result)
							throws ProcessException {
						// TODO Auto-generated method stub
						// Toast.makeText(getApplicationContext(), "获取成功", 1000).show();
					}
				};
				Map<String,Object> input=new HashMap<String,Object>();
				input.put("phoneNo",PhoneNo);
				System.out.println("phoneNO:"+PhoneNo);
				mServerCxdAPI.getAuthCode(input, listener);
			}
		});
	}

	@Override
	protected void setContentView() {
		// TODO Auto-generated method stub
		setContentView(R.layout.setpassword);
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
	}

	@Override
	protected String getHeaderTitle() {
		// TODO Auto-generated method stub
		return "设置密码";
	}
	
	private class SetPasswordListener extends ConnectionNetworkAbleUIBase<DefaultVO>{

		public SetPasswordListener(Context context) {
			super(context);
			// TODO Auto-generated constructor stub
		}
		@Override
		public void onSuccessed(DefaultVO result) throws ProcessException {
			// TODO Auto-generated method stub
			if(result.getData()==null || result.getData()=="")
			{
				Toast.makeText(getApplicationContext(), result.getError(), Toast.LENGTH_SHORT).show();
			}
			else
			{
				Toast.makeText(getApplicationContext(), "注册成功", Toast.LENGTH_SHORT).show();
				Intent intent = new Intent(SetPasswordActivity.this,
						LoginActivity.class);
				startActivity(intent);
			}
			
		}
	}
}
