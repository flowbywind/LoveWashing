package com.easylife.app_wash.view;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.easylife.app_wash.R;
import com.easylife.app_wash.widget.BaseActivity;
import com.easylife.app_wash.widget.PullDownTemplateView;
import com.easylife.app_wash.widget.PullDownTemplateView.OnPullDownTemplateViewListener;
import com.sihehui.section_vo.vo.ReferorVO;

public class MessageActivity extends BaseActivity implements
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
	}

	// List dataList = new ArrayList();

	@Override
	protected void setContentView() {
		// TODO Auto-generated method stub
		setContentView(R.layout.message);
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
			List<ReferorVO> dataList, ListView listView) {
		// TODO Auto-generated method stub
		ViewHolder viewHolder = null;
		if (view == null) {
			view = getLayoutInflater()
					.inflate(R.layout.list_item_message, null);
			viewHolder = new ViewHolder();
			viewHolder.type = (TextView) view.findViewById(R.id.type);
			viewHolder.content = (TextView) view.findViewById(R.id.content);
			viewHolder.date = (TextView) view.findViewById(R.id.date);
			view.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) view.getTag();
		}
		return view;
	}

	class ViewHolder {
		TextView type, content, date;
	}

	@Override
	protected String getHeaderTitle() {
		// TODO Auto-generated method stub
		return "系统消息";
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id, List<ReferorVO> dataList) {
		// TODO Auto-generated method stub

	}
}
