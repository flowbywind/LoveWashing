package com.easylife.app_wash.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
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
import com.sihehui.section_vo.vo.HisitoryJiaoyiVO;

public class HistoryJiaoyiActivity extends BaseActivity implements
		OnPullDownTemplateViewListener<HisitoryJiaoyiVO> {
	protected PullDownTemplateView<HisitoryJiaoyiVO> pullDownTemplateView;
	protected int Page = 1;

	@SuppressWarnings("unchecked")
	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		pullDownTemplateView = (PullDownTemplateView<HisitoryJiaoyiVO>) findViewById(R.id.tab_list_pull_down);
		pullDownTemplateView.setPullDownTemplateViewListener(this);
		pullDownTemplateView.pullDownView.mListView
				.setVerticalScrollBarEnabled(true);
		pullDownTemplateView.pullDownView.mListView.setDivider(null);
		pullDownTemplateView.emptyView.setText("暂无交易记录");
		loadDataList(LoadDataType.Load);
	}

	// 加载数据
	private void loadDataList(LoadDataType type) {
		Map<String, Object> input = new HashMap<String, Object>();
		input.put("page", Page);
		input.put("pageSize", PageSize);
		input.put("name", mUser.getUserName());
		HistoryJiaoyiListener listener = new HistoryJiaoyiListener(
				HistoryJiaoyiActivity.this, type);
		mServerCxdAPI.getArrayData(input,
				Contacts.ServiceURL.TransactionRecordsAppService, listener,
				HisitoryJiaoyiVO.class);
	}

	@Override
	protected void setContentView() {
		// TODO Auto-generated method stub
		setContentView(R.layout.history_jiaoyi);
	}

	@Override
	public void onRefresh() {
		// TODO Auto-generated method stub
		loadDataList(LoadDataType.Refresh);
	}

	@Override
	public void onMore() {
		// TODO Auto-generated method stub
		loadDataList(LoadDataType.More);
	}

	@Override
	public View onRefreshItemView(int position, View view, ViewGroup viewGroup,
			List<HisitoryJiaoyiVO> dataList, ListView listView) {
		// TODO Auto-generated method stub
		ViewHolder viewHolder = null;
		if (view == null) {
			view = getLayoutInflater().inflate(
					R.layout.list_item_history_jiaoyi, null);
			viewHolder = new ViewHolder();
			viewHolder.type = (TextView) view.findViewById(R.id.type);
			viewHolder.money = (TextView) view.findViewById(R.id.money);
			viewHolder.id = (TextView) view.findViewById(R.id.id);
			viewHolder.status = (TextView) view.findViewById(R.id.status);
			viewHolder.date = (TextView) view.findViewById(R.id.date);
			view.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) view.getTag();
		}
		// if(dataList.size()>position)
		// {
		HisitoryJiaoyiVO vo = dataList.get(position);
		// if(vo!=null){
		viewHolder.type.setText(vo.getTypeInfo());
		viewHolder.money.setText(vo.getMoney() + "元");
		viewHolder.id.setText("ID:" + vo.getId());
		viewHolder.status.setText(vo.getDetail());
		viewHolder.date.setText(vo.getTime());
		// }
		// }

		return view;
	}

	class ViewHolder {
		TextView type, money, id, status, date;
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id, List<HisitoryJiaoyiVO> dataList) {
		// TODO Auto-generated method stub

	}

	@Override
	protected String getHeaderTitle() {
		// TODO Auto-generated method stub
		return "交易记录";
	}

	private class HistoryJiaoyiListener extends
			ConnectionNetworkAbleUIBase<List<HisitoryJiaoyiVO>> {

		private LoadDataType loadType;

		public HistoryJiaoyiListener(Context context, LoadDataType loadType) {
			super(context);
			this.loadType = loadType;
			// TODO Auto-generated constructor stub
		}

		@Override
		public void onSuccessed(List<HisitoryJiaoyiVO> result)
				throws ProcessException {

			// TODO Auto-generated method stub
			if (result == null) {
				return;
			}
			Boolean hasNextpage = result.size() >= Page;
			if (this.loadType == LoadDataType.More) {
				Page = Page + 1;
				pullDownTemplateView.more(result, hasNextpage);
			} else if (this.loadType == LoadDataType.Refresh) {
				Page = 1;
				pullDownTemplateView.refresh(result, "暂无交易记录", hasNextpage);
			} else {
				Page = 1;
				pullDownTemplateView.load(result, hasNextpage);
			}
		}
	}

}
