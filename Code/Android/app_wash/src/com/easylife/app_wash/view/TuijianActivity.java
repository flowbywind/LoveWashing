package com.easylife.app_wash.view;

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
import com.sihehui.section_network.http.info.ConnectionNetworkAbleBase;
import com.sihehui.section_network.http.info.ProcessException;
import com.sihehui.section_network.util.Contacts;
import com.sihehui.section_vo.enums.LoadDataType;
import com.sihehui.section_vo.vo.ReferorVO;

public class TuijianActivity extends BaseActivity implements
		OnPullDownTemplateViewListener<ReferorVO> {
	protected PullDownTemplateView<ReferorVO> pullDownTemplateView;

	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		pullDownTemplateView = (PullDownTemplateView<ReferorVO>) findViewById(R.id.tab_list_pull_down);
		pullDownTemplateView.setPullDownTemplateViewListener(this);
		// dataList.add(1);
		// pullDownTemplateView.load(dataList, true);
		loadData(loadType.Load);
	}

	private void loadData(final LoadDataType type) {
		Map<String, Object> input = new HashMap<String, Object>();
		input.put("name", mUser.getUserName());
		input.put("page", page);
		input.put("pageSize", pageSize);
		mServerCxdAPI.getArrayData(input,
				Contacts.ServiceURL.ReferrerAppService,
				new ConnectionNetworkAbleUIBase<List<ReferorVO>>(getApplicationContext()) {

					@Override
					public void onSuccessed(List<ReferorVO> result)
							throws ProcessException {
						// TODO Auto-generated method stub
						if(type==LoadDataType.Refresh){
							pullDownTemplateView.refresh(result, "暂无数据", true);
						}else  if(type==LoadDataType.More){
							pullDownTemplateView.more(result,  true);
						}else{
							pullDownTemplateView.load(result,  true);
						}
					}

				}, ReferorVO.class);
	}

	// List dataList = new ArrayList();

	@Override
	protected void setContentView() {
		// TODO Auto-generated method stub
		setContentView(R.layout.tuijian);
	}

	@Override
	public void onRefresh() {
		// TODO Auto-generated method stub
		loadData(loadType.Refresh);
	}

	@Override
	public void onMore() {
		// TODO Auto-generated method stub
		loadData(loadType.More);
	}

	@Override
	public View onRefreshItemView(int position, View view, ViewGroup viewGroup,
			List<ReferorVO> dataList, ListView listView) {
		// TODO Auto-generated method stub
		ViewHolder viewHolder = null;
		if (view == null) {
			view = getLayoutInflater()
					.inflate(R.layout.list_item_tuijian, null);
			viewHolder = new ViewHolder();
			viewHolder.type = (TextView) view.findViewById(R.id.type);
			viewHolder.money = (TextView) view.findViewById(R.id.money);
			viewHolder.date = (TextView) view.findViewById(R.id.date);
			view.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) view.getTag();
		}
		return view;
	}

	class ViewHolder {
		TextView type, money, date;
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id, List<ReferorVO> dataList) {
		// TODO Auto-generated method stub

	}

	@Override
	protected String getHeaderTitle() {
		// TODO Auto-generated method stub
		return "推荐管理";
	}

}
