package com.easylife.app_wash.view;

import java.util.HashMap;
import java.util.Map;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.easylife.app_wash.R;
import com.easylife.app_wash.widget.BaseActivity;
import com.easylife.app_wash.widget.ConnectionNetworkAbleUIBase;
import com.sihehui.section_network.http.info.ProcessException;
import com.sihehui.section_vo.vo.DefaultVO;

public class ForgetPassword extends BaseActivity {

	private EditText mPhoneNo;
	private Button btnnext, btnphone;

	private String strPhone;

	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);

		btnnext = (Button) findViewById(R.id.btn_next);
		mPhoneNo = (EditText) findViewById(R.id.txt_phone);
		btnphone = (Button) findViewById(R.id.btn_phone);
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();

		btnnext.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				strPhone = (String) mPhoneNo.getText().toString();
				if(strPhone==null || "".equals(strPhone))
				{
					Toast.makeText(getApplicationContext(), "手机号不得为空", Toast.LENGTH_SHORT).show();
					return ;
				}
				ConnectionNetworkAbleUIBase<DefaultVO> listener = new ConnectionNetworkAbleUIBase<DefaultVO>(
						ForgetPassword.this) {

					@Override
					public void onSuccessed(DefaultVO result)
							throws ProcessException {
						// TODO Auto-generated method stub
						Intent intent = new Intent(ForgetPassword.this,
								ResetPasswordActivity.class);
						intent.putExtra("phoneNo", strPhone);
						startActivity(intent);
					}
				};
				Map<String, Object> input = new HashMap<String, Object>();
				
				input.put("phoneNo", strPhone);
				mServerCxdAPI.getAuthCode(input, listener);
				// TODO Auto-generated method stub
			}
		});

		btnphone.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				final String phone = (String) btnphone.getText();
				// TODO Auto-generated method stub
				Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"
						+ phone));
				startActivity(intent);
			}
		});
	}

	@Override
	protected void setContentView() {
		// TODO Auto-generated method stub
		setContentView(R.layout.forget_password);
	}

	@Override
	protected String getHeaderTitle() {
		// TODO Auto-generated method stub
		return "忘记密码";
	}

}
