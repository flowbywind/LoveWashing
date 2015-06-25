package com.easylife.app_wash.view;

import java.util.HashMap;
import java.util.Map;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
import com.sihehui.section_vo.vo.FormVO;

public class ChongzhiActivity extends BaseActivity {
	EditText money;
	Button btnok;

	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		money = (EditText) findViewById(R.id.money);
		btnok = (Button) findViewById(R.id.btnok);
		btnok.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				// 先判断是否是实名认证
				final String textMoney = money.getText().toString().trim();
				if (textMoney == null || "".equals(textMoney)) {
					Toast.makeText(getApplicationContext(), "请输入投资金额", 1000)
							.show();
					return;
				}
				Map<String, Object> input = new HashMap<String, Object>();
				input.put("name", mUser.getUserName());
				mServerCxdAPI.getObjectData(input,
						Contacts.ServiceURL.IsRealnameAuthAppService,
						new ConnectionNetworkAbleUIBase<FormVO>(
								getApplicationContext()) {

							@Override
							public void onSuccessed(FormVO result)
									throws ProcessException {
								// TODO Auto-generated method stub
								if ("1".equals(result.getAuth())) {
									Map<String, Object> input = new HashMap<String, Object>();
									Log.d(TAG, "textMoney=" + textMoney);
									input.put("money", textMoney);
									input.put("name", mUser.getUserName());
									mServerCxdAPI
											.auth(input,
													Contacts.ServiceURL.RechargeAppService,
													new ConnectionNetworkAbleUIBase<AuthVO>(
															getApplicationContext()) {

														@Override
														public void onSuccessed(
																AuthVO result)
																throws ProcessException {
															// TODO
															// Auto-generated
															// method stub
															// Toast.makeText(getApplicationContext(),
															// "认证成功" +
															// result.getResult(),
															// 1000)
															// .show();
															Intent intent = new Intent(
																	getApplicationContext(),
																	CommonWebViewActivity.class);
															intent.putExtra(
																	"title",
																	"易宝充值");
															intent.putExtra(
																	"url",
																	result.getResult());
															startActivity(intent);
															// finish();
														}
													});
									// mServerCxdAPI
									// .getObjectData(
									// input,
									// Contacts.ServiceURL.RechargeAppService,
									// new ConnectionNetworkAbleUIBase<FormVO>(
									// getApplicationContext()) {
									//
									// @Override
									// public void onSuccessed(
									// FormVO result)
									// throws ProcessException {
									// // TODO
									// // Auto-generated
									// // method stub
									// Intent intent = new Intent(
									// getApplicationContext(),
									// CommonWebViewActivity.class);
									// intent.putExtra(
									// "title",
									// "易宝提现");
									// intent.putExtra(
									// "url",
									// result.getForm());
									// startActivity(intent);
									// }
									// }, FormVO.class);
								} else {
									Toast.makeText(getApplicationContext(),
											"请进行实名认证", 1000).show();
									Intent intent = new Intent(
											getApplicationContext(),
											AuthActivity.class);
									startActivity(intent);
								}
							}
						}, FormVO.class);

			}
		});
	}

	@Override
	protected void setContentView() {
		// TODO Auto-generated method stub
		setContentView(R.layout.chongzhi);
	}

	@Override
	protected String getHeaderTitle() {
		// TODO Auto-generated method stub
		return "充 值";
	}

}
