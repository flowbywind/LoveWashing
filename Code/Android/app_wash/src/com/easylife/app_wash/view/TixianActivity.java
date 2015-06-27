package com.easylife.app_wash.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.easylife.app_wash.R;
import com.easylife.app_wash.view.MyBankCardActivity.ViewHolder;
import com.easylife.app_wash.widget.BaseActivity;
import com.easylife.app_wash.widget.ConnectionNetworkAbleUIBase;
import com.easylife.app_wash.widget.ScrollOverListView;
import com.sihehui.section_network.http.info.ProcessException;
import com.sihehui.section_network.util.Contacts;
import com.sihehui.section_vo.enums.LoadDataType;
import com.sihehui.section_vo.vo.AuthVO;
import com.sihehui.section_vo.vo.BankVO;
import com.sihehui.section_vo.vo.FormVO;
import com.sihehui.section_vo.vo.HistoryToubiaoVO;

public class TixianActivity extends BaseActivity {
	EditText tixian_money;
	TextView money;
	float totalMoney;
	Button tixian;
	String bankCardId;
	ScrollOverListView listview;

	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		tixian_money = (EditText) findViewById(R.id.tixian_money);
		money = (TextView) findViewById(R.id.money);
		tixian = (Button) findViewById(R.id.tixian);
		listview = (ScrollOverListView) findViewById(R.id.listview);
		String total_money = getIntent().getStringExtra("total_money");
		money.setText(total_money + "元");
		// total_money=total_money.replace(".", "");
		totalMoney = Float.valueOf(total_money);
		tixian.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				String input_money = tixian_money.getText().toString().trim();
				if (input_money == null || "".equals(input_money.length())
						|| "0".equals(input_money.length())) {
					Toast.makeText(getApplicationContext(), "提现金额必须大于0元", 1000)
							.show();
					return;
				}
				final float inputMoney = Integer.valueOf(input_money);
				if (inputMoney > totalMoney) {
					Toast.makeText(getApplicationContext(), "提现金额必须小于账户余额",
							1000).show();
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
									input.put("money", inputMoney);
									input.put("name", mUser.getUserName());
									input.put("bankCardId", bankCardId);
									mServerCxdAPI
									.auth(input,
											Contacts.ServiceURL.WithdrawAppService,
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
															"易宝提现");
													intent.putExtra(
															"url",
															result.getResult());
													startActivity(intent);
													// finish();
												}
											});
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

	List<BankVO> listData = new ArrayList<BankVO>();
	Adapter adapter;

	private void loadData(final LoadDataType type) {
		Map<String, Object> input = new HashMap<String, Object>();
		input.put("name", mUser.getUserName());
		mServerCxdAPI.getArrayData(input,
				Contacts.ServiceURL.BindCardQueryAppService,
				new ConnectionNetworkAbleUIBase<List<BankVO>>(
						getApplicationContext()) {

					@Override
					public void onSuccessed(List<BankVO> result)
							throws ProcessException {
						// TODO Auto-generated method stub

						// if (type == LoadDataType.Refresh) {
						//
						// } else if (type == LoadDataType.Refresh) {
						//
						// } else {
						// }
						adapter = new Adapter();
						listData = result;
						adapter.notifyDataSetChanged();
						listview.setAdapter(adapter);
					}
				}, BankVO.class);
	}

	class Adapter extends BaseAdapter {

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return listData == null ? 0 : listData.size();
			// return listData.size();
		}

		@Override
		public Object getItem(int arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long getItemId(int arg0) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public View getView(final int position, View view, ViewGroup arg2) {
			// TODO Auto-generated method stub
			final ViewHolder viewHolder;
			if (view == null) {
				view = getLayoutInflater().inflate(
						R.layout.list_item_my_bankcard, null);
				viewHolder = new ViewHolder();
				viewHolder.type = (ImageView) view.findViewById(R.id.type);
				viewHolder.name = (TextView) view.findViewById(R.id.name);
				viewHolder.number = (TextView) view.findViewById(R.id.number);
				view.setTag(viewHolder);
			} else {
				viewHolder = (ViewHolder) view.getTag();
			}
			BankVO vo = listData.get(position);
			viewHolder.name.setText(vo.getBankName());
			String num = vo.getBankNo();
			if (num.length() > 8) {
				num = num.substring(0, num.length() - 8) + "****"
						+ num.substring(num.length() - 4);
			}
			viewHolder.number.setText(num);

			return view;
		}

		class ViewHolder {
			// TextView money, name, date, status;
			// ImageView status_image;
			TextView name, number;
			ImageView type;
		}

	}

	@Override
	protected void setContentView() {
		// TODO Auto-generated method stub
		setContentView(R.layout.tixian);
	}

	@Override
	protected String getHeaderTitle() {
		// TODO Auto-generated method stub
		return "提  现";
	}

}
