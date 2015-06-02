package com.easylife.app_wash.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.os.Bundle;
import android.view.View;
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
import com.sihehui.section_network.util.Contacts;
import com.sihehui.section_vo.enums.LoadDataType;
import com.sihehui.section_vo.vo.PlanVO;

public class RepaymentPlanActivity extends BaseActivity implements
		OnPullDownTemplateViewListener<PlanVO> {

	protected PullDownTemplateView<PlanVO> pullDownTemplateView;
	// List<PlanVO> dataList = new ArrayList<PlanVO>();
	String id;

	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		id = getIntent().getStringExtra("id");
		pullDownTemplateView = (PullDownTemplateView<PlanVO>) findViewById(R.id.tab_list_pull_down);
		pullDownTemplateView.setPullDownTemplateViewListener(this);
		// pullDownTemplateView.load(dataList, true);
		loadData(LoadDataType.Load);
	}

	private void loadData(LoadDataType type) {
		Map<String, Object> input = new HashMap<String, Object>();
		input.put("name", "");
		input.put("loanId", id);
		mServerCxdAPI.getArrayData(input,
				Contacts.ServiceURL.RepayPlanAppService,
				new ConnectionNetworkAbleUIBase<List<PlanVO>>(
						getApplicationContext()) {

					@Override
					public void onSuccessed(List<PlanVO> result)
							throws ProcessException {
						// TODO Auto-generated method stub
						if (loadType == LoadDataType.More) {
							page++;
							pullDownTemplateView.more(result, true);
						} else if (loadType == LoadDataType.Refresh) {
							pullDownTemplateView.refresh(result, "暂无数据", true);
						} else {
							pullDownTemplateView.load(result, true);
						}
					}
				}, PlanVO.class);
	}

	@Override
	protected void setContentView() {
		// TODO Auto-generated method stub
		setContentView(R.layout.repayment_plan);
	}

	@Override
	protected String getHeaderTitle() {
		// TODO Auto-generated method stub
		return "还款计划";
	}

	@Override
	public void onRefresh() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onMore() {
		// TODO Auto-generated method stub

	}

	@Override
	public View onRefreshItemView(int position, View view, ViewGroup viewGroup,
			List<PlanVO> dataList, ListView listView) {
		// TODO Auto-generated method stub
		ViewHolder viewHolder = null;
		if (view == null) {
			view = getLayoutInflater().inflate(
					R.layout.list_item_repayment_plan, null);
			viewHolder = new ViewHolder();
			viewHolder.num = (TextView) view.findViewById(R.id.txt_num);
			viewHolder.status = (TextView) view.findViewById(R.id.txt_status);
			viewHolder.money = (TextView) view.findViewById(R.id.txt_money);
			viewHolder.lixi = (TextView) view.findViewById(R.id.txt_lixi);
			viewHolder.faxi = (TextView) view.findViewById(R.id.txt_faxi);
			viewHolder.huankuandate = (TextView) view
					.findViewById(R.id.txt_huankuandate);
			viewHolder.huankuantime = (TextView) view
					.findViewById(R.id.txt_huankuantime);
			view.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) view.getTag();
		}
		PlanVO vo = dataList.get(position);
		viewHolder.num.setText("第" + vo.getPeriod() + "期");
		viewHolder.status.setText("状态： " + vo.getStatus());
		viewHolder.money.setText("本金： " + vo.getCorpus() + "元");
		viewHolder.lixi.setText("利息： " + vo.getInterest() + "元");
		viewHolder.faxi.setText("罚息： " + vo.getDefaultInterest() + "元");
		viewHolder.huankuandate.setText("还款日： " + vo.getTime());
		viewHolder.huankuantime.setText("还款时间： " + vo.getRepayDay());

		return view;
	}

	class ViewHolder {
		TextView num, status, money, lixi, faxi, huankuandate, huankuantime;
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id, List<PlanVO> dataList) {
		// TODO Auto-generated method stub

	}

}
