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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.easylife.app_wash.R;
import com.easylife.app_wash.widget.ConnectionNetworkAbleUIBase;
import com.easylife.app_wash.widget.CustomActivity;
import com.easylife.app_wash.widget.ListViewForScrollView;
import com.sihehui.section_network.http.info.ProcessException;
import com.sihehui.section_network.util.Contacts;
import com.sihehui.section_vo.enums.LoadDataType;
import com.sihehui.section_vo.vo.HistoryToubiaoVO;
import com.sihehui.section_vo.vo.LoanVO;

public class DetailHomeActivity extends CustomActivity {
	TextView name, qishu, type, status, money, month, date, shengyu,
			zhuanhuanlv, toubiaostatus, toubiaojindu;
	ProgressBar progressbar;
	LinearLayout layout_progressbar;
	RelativeLayout relative;
	TextView desc, comment;// 投标详情、投标记录
	TextView detail_desc;// 投标详情
	ListViewForScrollView listView;
	Button toubiao;// 立即投标
	LoanVO vo;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		vo = (LoanVO) getIntent().getSerializableExtra("dataVO");
		name = (TextView) findViewById(R.id.name);
		qishu = (TextView) findViewById(R.id.qishu);
		type = (TextView) findViewById(R.id.type);
		status = (TextView) findViewById(R.id.status);
		money = (TextView) findViewById(R.id.money);
		month = (TextView) findViewById(R.id.month);
		date = (TextView) findViewById(R.id.date);
		shengyu = (TextView) findViewById(R.id.shengyu);
		zhuanhuanlv = (TextView) findViewById(R.id.zhuanhuanlv);
		toubiaostatus = (TextView) findViewById(R.id.toubiaostatus);
		toubiaojindu = (TextView) findViewById(R.id.toubiaojindu);
		progressbar = (ProgressBar) findViewById(R.id.progressbar);

		layout_progressbar = (LinearLayout) findViewById(R.id.layout_progressbar);
		relative = (RelativeLayout) findViewById(R.id.relative);
		// relative.setBackgroundDrawable(null);

		desc = (TextView) findViewById(R.id.desc);
		comment = (TextView) findViewById(R.id.comment);
		toubiao = (Button) findViewById(R.id.toubiao);
		detail_desc = (TextView) findViewById(R.id.detail_desc);
		listView = (ListViewForScrollView) findViewById(R.id.detail_list);

		name.setText(vo.getName() + "");
		type.setText(vo.getLoanPurpose());
		money.setText(vo.getLoanMoney());
		month.setText(vo.getDeadline());
		status.setText(vo.getRepayType());
		shengyu.setText(vo.getRemainTime());
		date.setText(vo.getDeadline());
		zhuanhuanlv.setText(vo.getRate());
		String progress = vo.getProgress();
		toubiaojindu.setText("已投标" + progress + "%");
		if (progress.contains(".")) {
			if (progress.startsWith("0")) {
				progress = "1";
			} else {
				int index = progress.indexOf(".");
				progress = progress.substring(0, index);
			}
		}
		progressbar.setProgress(Integer.valueOf(progress));
		toubiaostatus.setText("投标中");

		detail_desc.setText(vo.getDescription());

		listener();
	}

	Adapter adapter;

	private void loadData(final LoadDataType type) {
		Map<String, Object> input = new HashMap<String, Object>();
		input.put("loanId", vo.getId());
		input.put("page", page);
		input.put("pageSize", pageSize);
		mServerCxdAPI.getArrayData(input,
				Contacts.ServiceURL.InvestRecordsAppService,
				new ConnectionNetworkAbleUIBase<List<HistoryToubiaoVO>>(
						getApplicationContext()) {

					@Override
					public void onSuccessed(List<HistoryToubiaoVO> result)
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
						listView.setAdapter(adapter);
					}
				}, HistoryToubiaoVO.class);
	}

	private void listener() {
		comment.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View view) {
				// TODO Auto-generated method stub
				view.setClickable(false);
				desc.setClickable(true);
				comment.setTextColor(getResources().getColor(
						R.color.fontcolor_white));
				desc.setTextColor(getResources().getColor(
						R.color.fontcolor_light));
				comment.setBackgroundColor(getResources().getColor(
						R.color.background_blue));
				desc.setBackgroundColor(getResources().getColor(
						R.color.background_white));
				listView.setVisibility(View.VISIBLE);
				detail_desc.setVisibility(View.GONE);
				if (listData == null || listData.size() == 0) {
					// Toast.makeText(getApplicationContext(), "暂无记录", 1000)
					// .show();

					loadData(null);
				} else {
					// listView.setAdapter(adapter);
				}
			}
		});
		desc.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View view) {
				// TODO Auto-generated method stub

				view.setClickable(false);
				comment.setTextColor(getResources().getColor(
						R.color.fontcolor_light));
				desc.setTextColor(getResources().getColor(
						R.color.fontcolor_white));
				comment.setClickable(true);
				listView.setVisibility(View.GONE);
				detail_desc.setVisibility(View.VISIBLE);
				desc.setBackgroundColor(getResources().getColor(
						R.color.background_blue));
				comment.setBackgroundColor(getResources().getColor(
						R.color.background_white));
			}
		});
		toubiao.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(DetailHomeActivity.this,
						ToubiaoActivity.class);
				intent.putExtra("dataVO", vo);
				startActivity(intent);
			}
		});
	}

	List<HistoryToubiaoVO> listData = new ArrayList<HistoryToubiaoVO>();

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
				viewHolder = new ViewHolder();
				view = View.inflate(DetailHomeActivity.this,
						R.layout.list_item_toubiao, null);
				viewHolder.money = (TextView) view.findViewById(R.id.money);
				viewHolder.status = (TextView) view.findViewById(R.id.status);
				viewHolder.date = (TextView) view.findViewById(R.id.date);
				viewHolder.name = (TextView) view.findViewById(R.id.name);
				viewHolder.status_image = (ImageView) view
						.findViewById(R.id.status_image);
				view.setTag(viewHolder);
			} else {
				viewHolder = (ViewHolder) view.getTag();
			}
			HistoryToubiaoVO vo = listData.get(position);
			viewHolder.money.setText(vo.getMoney() + "元");
			String status = vo.getStatus();
			viewHolder.status.setText(status);
			viewHolder.date.setText(vo.getTime());
			String name = vo.getInvestUser();
			if (name != null && !"".equals(name) && name.length() > 3) {
				name = name.substring(0, name.length() - 3) + "***";
			}
			viewHolder.name.setText(name);
			if (status.contains("失败")) {
				viewHolder.status_image
						.setImageResource(R.drawable.image_status);
			} else {
				viewHolder.status_image
						.setImageResource(R.drawable.image_status);
			}

			return view;
		}

		class ViewHolder {
			TextView money, name, date, status;
			ImageView status_image;
		}

	}

	@Override
	protected void setContentView() {
		// TODO Auto-generated method stub
		setContentView(R.layout.detail_home);
	}

	@Override
	protected String getHeaderTitle() {
		// TODO Auto-generated method stub
		return "交易记录";
	}

}
