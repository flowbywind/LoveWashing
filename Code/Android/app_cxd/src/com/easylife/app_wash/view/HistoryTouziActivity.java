package com.easylife.app_wash.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.easylife.app_wash.R;
import com.easylife.app_wash.widget.BaseActivity;
import com.easylife.app_wash.widget.ConnectionNetworkAbleUIBase;
import com.easylife.app_wash.widget.PullDownTemplateView;
import com.easylife.app_wash.widget.PullDownTemplateView.OnPullDownTemplateViewListener;
import com.sihehui.section_network.http.info.ProcessException;
import com.sihehui.section_vo.enums.LoadDataType;
import com.sihehui.section_vo.vo.LoanVO;

public class HistoryTouziActivity extends BaseActivity implements
		OnPullDownTemplateViewListener<LoanVO> {
	protected PullDownTemplateView<LoanVO> pullDownTemplateView;
	TextView huankuan, jiexin, toubiao, shibai;
	List<TextView> list = new ArrayList<TextView>();
	
	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		status="repaying";
		pullDownTemplateView = (PullDownTemplateView<LoanVO>) findViewById(R.id.tab_list_pull_down);
		pullDownTemplateView.setPullDownTemplateViewListener(this);
		huankuan = (TextView) findViewById(R.id.huankuan);
		jiexin = (TextView) findViewById(R.id.jiexin);
		huankuan = (TextView) findViewById(R.id.huankuan);
		toubiao = (TextView) findViewById(R.id.toubiao);
		shibai = (TextView) findViewById(R.id.shibai);
		huankuan.setTag("repaying");
		jiexin.setTag("complete");
		toubiao.setTag("rasing");
		shibai.setTag("cancel");
		list.add(huankuan);
		list.add(jiexin);
		list.add(toubiao);
		list.add(shibai);
		loadData(LoadDataType.Load);
		huankuan.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				click();
			}
		});
		jiexin.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				click();
			}
		});
		toubiao.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				click();
			}
		});
		shibai.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				click();
			}
		});
	}

	private void click() {
		for (TextView textview : list) {
			if (textview.isPressed()) {
				textview.setTextColor(getResources().getColor(
						R.color.fontcolor_blue));
				status = (String) textview.getTag();
				loadData(LoadDataType.Load);
			} else {
				textview.setTextColor(getResources().getColor(
						R.color.fontcolor_light));
			}
		}
	}

	List<LoanVO> dataList = new ArrayList<LoanVO>();
	private int minRate, maxRate, minDeadline, maxDeadline;
	private String status = "all";

	public void loadData(LoadDataType type) {
		LoanAppService loan = new LoanAppService(HistoryTouziActivity.this);
		Map<String, Object> input = new HashMap<String, Object>();
		input.put("page", page);
		input.put("pageSize", pageSize);
		input.put("status", status);

		input.put("minRate", minRate);
		input.put("maxRate", maxRate);
		input.put("minDeadline", minDeadline);
		input.put("maxDeadline", maxDeadline);
		loadType = type;
		mServerCxdAPI.getLoanService(input, loan);
		// for (int i = 0; i < 20; i++) {
		// data.add(i + "");
		// }
		// pullDownTemplateView.load(data, true);
	}

	class LoanAppService extends ConnectionNetworkAbleUIBase<List<LoanVO>> {

		public LoanAppService(Context context) {
			super(context);
			// TODO Auto-generated constructor stub
		}

		@Override
		public void onSuccessed(List<LoanVO> result) throws ProcessException {
			// TODO Auto-generated method stub
			if (result == null) {
				return;
			}
			if (loadType == LoadDataType.More) {
				page++;
				pullDownTemplateView.more(result, true);
			} else if (loadType == LoadDataType.Refresh) {
				pullDownTemplateView.refresh(result, "暂无数据", true);
			} else {
				pullDownTemplateView.load(result, true);
			}

		}
	}

	@Override
	protected void setContentView() {
		// TODO Auto-generated method stub
		setContentView(R.layout.history_touzi);
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
			List<LoanVO> dataList, ListView listView) {
		// TODO Auto-generated method stub
		ViewHolder viewHolder = null;
		if (view == null) {
			view = getLayoutInflater().inflate(
					R.layout.list_item_history_touzi, null);
			viewHolder = new ViewHolder();
			viewHolder.name = (TextView) view.findViewById(R.id.name);
			viewHolder.qishu = (TextView) view.findViewById(R.id.qishu);
			viewHolder.type = (TextView) view.findViewById(R.id.type);
			viewHolder.status = (TextView) view.findViewById(R.id.status);
			viewHolder.money = (TextView) view.findViewById(R.id.money);
			viewHolder.month = (TextView) view.findViewById(R.id.month);
			viewHolder.date = (TextView) view.findViewById(R.id.date);
			viewHolder.profit = (TextView) view.findViewById(R.id.profit);
			viewHolder.huankuan_jihua = (TextView) view
					.findViewById(R.id.huankuan_jihua);
			viewHolder.huankuan_hetong = (TextView) view
					.findViewById(R.id.huankuan_hetong);
			viewHolder.image_status = (TextView) view
					.findViewById(R.id.image_status);
			view.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) view.getTag();
		}
		final LoanVO vo = dataList.get(position);
		viewHolder.name.setText(vo.getName() + "");
		viewHolder.qishu.setText("");
		viewHolder.type.setText(vo.getLoanPurpose());
		viewHolder.money.setText(vo.getLoanMoney());
		viewHolder.month.setText(vo.getDeadline());
		viewHolder.status.setText(vo.getRepayType());
		viewHolder.date.setText(vo.getDeadline());
		viewHolder.profit.setText(vo.getRate());
		// viewHolder.image_status.setText();
		viewHolder.huankuan_jihua.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(HistoryTouziActivity.this,
						RepaymentPlanActivity.class);
				intent.putExtra("id", vo.getId());
				startActivity(intent);
			}
		});
		viewHolder.huankuan_hetong.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(HistoryTouziActivity.this,
						CommonWebViewActivity.class);
				intent.putExtra("title", "");
				intent.putExtra("url", "");
				startActivity(intent);

			}
		});
		return view;
	}

	class ViewHolder {
		TextView name, qishu, type, status, money, month, profit, date,
				huankuan_jihua, huankuan_hetong;
		TextView image_status;
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id, List<LoanVO> dataList) {
		// TODO Auto-generated method stub
		Intent intent = new Intent(HistoryTouziActivity.this,
				DetailHomeActivity.class);
		intent.putExtra("dataVO", dataList.get(position));
		startActivity(intent);
	}

	@Override
	protected String getHeaderTitle() {
		// TODO Auto-generated method stub
		return "最近投资";
	}

}
