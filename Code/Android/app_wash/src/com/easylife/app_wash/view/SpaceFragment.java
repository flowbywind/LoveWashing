package com.easylife.app_wash.view;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.easylife.app_wash.R;

public class SpaceFragment extends ListBaseFragment {
	TextView txt_title1, txt_sum_money, txt_sure_money, txt_sum_money1,
			txt_money_unit1;
	RelativeLayout layout_title;
	Button btninputmoney, btngetmoney;

	// LinearLayout content_center1,
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = super.onCreateView(inflater, container, savedInstanceState);
		txt_title1 = (TextView) view.findViewById(R.id.txt_title1);
		txt_sum_money = (TextView) view.findViewById(R.id.txt_sum_money);
		txt_sure_money = (TextView) view.findViewById(R.id.txt_sure_money);
		txt_sum_money1 = (TextView) view.findViewById(R.id.txt_sum_money1);
		txt_money_unit1 = (TextView) view.findViewById(R.id.txt_money_unit1);
		layout_title = (RelativeLayout) view.findViewById(R.id.layout_title);
		btninputmoney = (Button) view.findViewById(R.id.btninputmoney);
		btngetmoney = (Button) view.findViewById(R.id.btngetmoney);
		String name = mUser.getMobileNumber();
		if (name != null && name.length() > 4) {
			name = name.substring(0, 3) + "****"
					+ name.substring(name.length() - 4);
		}
		txt_title1.setText(name);
		listener();
		return view;
	}

	private void listener() {
		layout_title.setOnClickListener(new OnClickListener() {
			// 个人中心
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(mActivity, MyDetailActivity.class);
				startActivity(intent);
			}
		});
		txt_sure_money.setOnClickListener(new OnClickListener() {
			// 交易记录
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(mActivity,
						HistoryJiaoyiActivity.class);
				startActivity(intent);
			}
		});
		btninputmoney.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(mActivity, ChongzhiActivity.class);
				startActivity(intent);
			}
		});
		btngetmoney.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(mActivity, TixianActivity.class);
				intent.putExtra("total_money", "1000");
				startActivity(intent);
			}
		});
	}

	@Override
	public void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		
	}

	@Override
	public View setContentView(LayoutInflater inflater, ViewGroup container) {
		// TODO Auto-generated method stub
		return inflater.inflate(R.layout.fragment_space, container, false);
	}

	@Override
	public List<String> SetListData() {
		// TODO Auto-generated method stub
		List<String> list = new ArrayList<String>();
		list.add("账户余额");
		list.add("我的银行卡");
		list.add("最近投资");
		list.add("推荐管理");
		list.add("系统消息");
		list.add("退出登录");
		return list;
	}

	@Override
	protected void OnItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		Intent intent = new Intent();
		if (position == 0) {
			intent.setClass(mActivity, AccountMoneyActivity.class);
		} else if (position == 1) {
			intent.setClass(mActivity, MyBankCardActivity.class);
		} else if (position == 2) {
			intent.setClass(mActivity, HistoryTouziActivity.class);
		} else if (position == 3) {
			intent.setClass(mActivity, TuijianActivity.class);
		} else if (position == 4) {
			intent.setClass(mActivity, MessageActivity.class);
		}else if(position == 5){
			intent.setClass(mActivity, ExitDialogActivity.class);
			intent.putExtra("clearData", true);
			intent.putExtra("content", "确定要退出登录吗?");
		}
		startActivity(intent);
	}
}
