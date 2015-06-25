package com.easylife.app_wash.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.easylife.app_wash.R;
import com.easylife.app_wash.widget.ConnectionNetworkAbleUIBase;
import com.easylife.app_wash.widget.CustomActivity;
import com.easylife.app_wash.widget.PullDownTemplateView;
import com.easylife.app_wash.widget.PullDownTemplateView.OnPullDownTemplateViewListener;
import com.sihehui.section_network.http.info.ProcessException;
import com.sihehui.section_network.util.Contacts;
import com.sihehui.section_vo.enums.LoadDataType;
import com.sihehui.section_vo.vo.BankVO;
import com.sihehui.section_vo.vo.FormVO;

public class MyBankCardActivity extends CustomActivity implements
		OnPullDownTemplateViewListener<BankVO> {
	protected PullDownTemplateView<BankVO> pullDownTemplateView;
	ImageView right_btn;
	List dataList = new ArrayList();

	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		pullDownTemplateView = (PullDownTemplateView<BankVO>) findViewById(R.id.tab_list_pull_down);
		pullDownTemplateView.setPullDownTemplateViewListener(this);
		pullDownTemplateView.emptyView.setText("暂无银行卡");
		// dataList.add(1);
		// pullDownTemplateView.load(dataList, true);
		// right_btn = (ImageView) findViewById(R.id.right_btn);
		// if (right_btn != null) {
		// right_btn.setImageResource(R.drawable.btn_add);
		// right_btn.setVisibility(View.GONE);
		// right_btn.setOnClickListener(new OnClickListener() {
		//
		// @Override
		// public void onClick(View arg0) {
		// // TODO Auto-generated method stub
		// // Intent intent = new Intent(MyBankCardActivity.this,
		// // BankAddActivity.class);
		// // startActivity(intent);
		// }
		// });
		// }
		loadData(LoadDataType.Load);
	}

	private void loadData(LoadDataType tpe) {
		final Map<String, Object> input = new HashMap<String, Object>();
		input.put("name", mUser.getUserName());
		mServerCxdAPI
				.getObjectData(input,
						Contacts.ServiceURL.IsRealnameAuthAppService,
						new ConnectionNetworkAbleUIBase<FormVO>(
								getApplicationContext()) {

							@Override
							public void onSuccessed(FormVO result)
									throws ProcessException {
								// TODO Auto-generated method stub
								if ("1".equals(result.getAuth())) {
									mServerCxdAPI
											.getArrayData(
													input,
													Contacts.ServiceURL.BindCardQueryAppService,
													new ConnectionNetworkAbleUIBase<List<BankVO>>(
															getApplicationContext()) {

														@Override
														public void onSuccessed(
																List<BankVO> result)
																throws ProcessException {
															// TODO
															// Auto-generated
															// method stub
															pullDownTemplateView
																	.load(result,
																			false);
														}
													}, BankVO.class);
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

	@Override
	protected void setContentView() {
		// TODO Auto-generated method stub
		setContentView(R.layout.my_bankcard);
	}

	@Override
	public void onRefresh() {
		// TODO Auto-generated method stub
		loadData(LoadDataType.Refresh);
	}

	@Override
	public void onMore() {
		// TODO Auto-generated method stub
		loadData(LoadDataType.More);
	}

	@Override
	public View onRefreshItemView(int position, View view, ViewGroup viewGroup,
			List<BankVO> dataList, ListView listView) {
		// TODO Auto-generated method stub
		ViewHolder viewHolder = null;
		if (view == null) {
			view = getLayoutInflater().inflate(R.layout.list_item_my_bankcard,
					null);
			viewHolder = new ViewHolder();
			viewHolder.type = (ImageView) view.findViewById(R.id.type);
			viewHolder.name = (TextView) view.findViewById(R.id.name);
			viewHolder.number = (TextView) view.findViewById(R.id.number);
			view.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) view.getTag();
		}
		BankVO vo = dataList.get(position);
		viewHolder.name.setText(vo.getBankName());
		String num = vo.getBankNo();
		if (num.length() > 8) {
			num = num.substring(0, num.length() - 8) + "****"
					+ num.substring(num.length() - 4);
		}
		viewHolder.number.setText(num);
		// if(vo.getCardNo()){
		//
		// }
		// viewHolder.type
		return view;
	}

	class ViewHolder {
		TextView name, number;
		ImageView type;
	}

	@Override
	public void onItemClick(AdapterView parent, View view, int position,
			long id, List dataList) {
		// TODO Auto-generated method stub

	}

	@Override
	protected String getHeaderTitle() {
		// TODO Auto-generated method stub
		return "我的银行卡";
	}

}
