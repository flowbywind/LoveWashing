package com.easylife.app_wash.view;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import android.content.Intent;
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
import com.sihehui.section_network.util.Contacts;
import com.sihehui.section_vo.vo.AuthVO;

public class AuthActivity extends BaseActivity {
	EditText name, realname, id_code, phone, email;
	Button auth;

	@Override
	protected String getHeaderTitle() {
		// TODO Auto-generated method stub
		return "实名认证";
	}

	@Override
	protected void setContentView() {
		// TODO Auto-generated method stub
		setContentView(R.layout.auth);
	}

	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		name = (EditText) findViewById(R.id.name);
		realname = (EditText) findViewById(R.id.realname);
		id_code = (EditText) findViewById(R.id.id_code);
		phone = (EditText) findViewById(R.id.phone);
		email = (EditText) findViewById(R.id.email);
		auth = (Button) findViewById(R.id.auth);
		auth.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				String mname = name.getText().toString().trim();
				if (mname == null || "".equals(mname)) {
					Toast.makeText(getApplicationContext(), "请输入用户名", 1000)
							.show();
					return;
				}
				String mrealname = realname.getText().toString().trim();
				if (mrealname == null || "".equals(mrealname)) {
					Toast.makeText(getApplicationContext(), "请输入真实姓名", 1000)
							.show();
					return;
				}
				String midnum = id_code.getText().toString().trim();
				if (midnum == null || "".equals(midnum)) {
					Toast.makeText(getApplicationContext(), "请输身份证号码", 1000)
							.show();
					return;
				}
				String mphone = phone.getText().toString().trim();
				if (mphone == null || "".equals(mphone)) {
					Toast.makeText(getApplicationContext(), "请输入手机号", 1000)
							.show();
					return;
				}
				String memail = email.getText().toString().trim();
				if (memail == null || "".equals(memail)) {
					Toast.makeText(getApplicationContext(), "请输入邮箱", 1000)
							.show();
					return;
				}

				Map<String, Object> input = new HashMap<String, Object>();
				input.put("name", mname);
				input.put("realname",mrealname);
//				try {
//					input.put("realname",URLEncoder.encode(mrealname,"utf-8"));
//				} catch (UnsupportedEncodingException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
				input.put("idCard", midnum);
				input.put("phoneNo", mphone);
				input.put("email", memail);
				mServerCxdAPI.auth(input,
						Contacts.ServiceURL.RealnameAuthAppService,
						new ConnectionNetworkAbleUIBase<AuthVO>(
								getApplicationContext()) {

							@Override
							public void onSuccessed(AuthVO result)
									throws ProcessException {
								// TODO Auto-generated method stub
								// Toast.makeText(getApplicationContext(),
								// "认证成功" + result.getResult(), 1000)
								// .show();
								Intent intent = new Intent(
										getApplicationContext(),
										CommonWebViewActivity.class);
								intent.putExtra("title", "易宝认证");
								intent.putExtra("url", result.getResult());
								startActivity(intent);
								finish();
							}
						});
				// mServerCxdAPI.getObjectData(input,
				// Contacts.ServiceURL.RealnameAuthAppService,
				// new ConnectionNetworkAbleUIBase<AuthVO>(
				// getApplicationContext()) {
				//
				// @Override
				// public void onSuccessed(AuthVO result)
				// throws ProcessException {
				// // TODO Auto-generated method stub
				// Toast.makeText(getApplicationContext(),
				// "认证成功" + result.getResult(), 1000)
				// .show();
				//
				// finish();
				// }
				// }, AuthVO.class);
			}
		});
	}

}
