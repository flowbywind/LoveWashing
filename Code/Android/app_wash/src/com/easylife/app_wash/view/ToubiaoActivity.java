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
import android.widget.TextView;
import android.widget.Toast;

import com.easylife.app_wash.R;
import com.easylife.app_wash.widget.ConnectionNetworkAbleUIBase;
import com.easylife.app_wash.widget.CustomActivity;
import com.sihehui.section_network.http.info.ProcessException;
import com.sihehui.section_network.util.Contacts;
import com.sihehui.section_vo.vo.AuthVO;
import com.sihehui.section_vo.vo.FormVO;
import com.sihehui.section_vo.vo.LoanVO;

public class ToubiaoActivity extends CustomActivity {
	TextView name, profit, date, money;
	Button btnok;
	TextView desc;
	LoanVO vo;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		vo = (LoanVO) getIntent().getSerializableExtra("dataVO");
		name = (TextView) findViewById(R.id.name);
		profit = (TextView) findViewById(R.id.profit);
		date = (TextView) findViewById(R.id.date);
		money = (EditText) findViewById(R.id.money);
		btnok = (Button) findViewById(R.id.btnok);
		desc = (TextView) findViewById(R.id.desc);
		name.setText(vo.getName() + "");
		profit.setText(vo.getRate());
		Log.d(TAG, "vo.getDeadline()=" + vo.getDeadline());
		date.setText(vo.getDeadline());

		listener();
	}

	private void listener() {
		btnok.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View view) {
				// TODO Auto-generated method stub
				final String textMoney = money.getText().toString().trim();
				if (textMoney == null || "".equals(textMoney)) {
					Toast.makeText(getApplicationContext(), "请输入投资金额", 1000)
							.show();
					return;
				} else if (Integer.valueOf(textMoney) < 100) {
					Toast.makeText(getApplicationContext(), "最小投资金额为100元", 1000)
							.show();
					return;
				} else if (Integer.valueOf(textMoney) > 50000) {
					Toast.makeText(getApplicationContext(), "最大投资金额为50000元",
							1000).show();
					return;
				} else if (Integer.valueOf(textMoney) % 100 != 0) {
					Toast.makeText(getApplicationContext(), "投资金额必须为100的整数倍",
							1000).show();
					return;
				}
				// 先判断是否是实名认证

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
									input.put("investMoney", textMoney);
									input.put("loanId", vo.getId());
									input.put("name", mUser.getUserName());
									mServerCxdAPI
											.auth(
													input,
													Contacts.ServiceURL.InvestAppService,
													new ConnectionNetworkAbleUIBase<AuthVO>(
															getApplicationContext()) {

														@Override
														public void onSuccessed(
																AuthVO result)
																throws ProcessException {
															// TODO
															// Auto-generated
															// method stub
															Intent intent = new Intent(
																	ToubiaoActivity.this,
																	CommonWebViewActivity.class);
															intent.putExtra(
																	"title",
																	"易宝支付");
															intent.putExtra(
																	"url",
																	result.getResult());
															startActivity(intent);
														}
													});
								} else {
									Toast.makeText(getApplicationContext(),
											"请进行实名认证", 1000).show();
									Intent intent=new Intent(getApplicationContext(),AuthActivity.class);
									startActivity(intent);
								}
							}
						}, FormVO.class);
				// auth

			}
		});
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
	}

	@Override
	protected void setContentView() {
		// TODO Auto-generated method stub
		setContentView(R.layout.toubiao);
	}

	@Override
	protected String getHeaderTitle() {
		// TODO Auto-generated method stub
		return "立即投标";
	}

}
