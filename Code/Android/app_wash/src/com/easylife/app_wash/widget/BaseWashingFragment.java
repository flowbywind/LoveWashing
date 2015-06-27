package com.easylife.app_wash.widget;

import java.util.List;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.easylife.app_wash.R;
import com.easylife.app_wash.widget.PullDownTemplateView.OnPullDownTemplateViewListener;
import com.sihehui.section_network.util.Contacts;
import com.sihehui.section_vo.vo.LoanVO;
import com.sihehui.section_vo.vo.WashingVO;

public abstract class BaseWashingFragment extends BaseFragment implements
		OnPullDownTemplateViewListener<WashingVO> {
	protected PullDownTemplateView<WashingVO> pullDownTemplateView;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = super.onCreateView(inflater, container, savedInstanceState);

		pullDownTemplateView = (PullDownTemplateView<WashingVO>) view
				.findViewById(R.id.tab_list_pull_down);
		if (pullDownTemplateView != null) {
			pullDownTemplateView.setPullDownTemplateViewListener(this);
			pullDownTemplateView.pullDownView.mListView.setDivider(null);
			pullDownTemplateView.pullDownView.mListView
					.setVerticalScrollBarEnabled(true);
			pullDownTemplateView.emptyView.setText(getTips());
		}
		return view;
	}

	protected String getTips() {
		return "暂无数据";
	}

	protected String getDownLoadUrl(String url) {
		String downloadUrl = "";
		downloadUrl = Contacts.getInstance(mActivity).SERVER_RESOURCE + url;
		Log.d("shh", "downloadUrl=" + downloadUrl);
		// downloadUrl =
		// "http://d.hiphotos.baidu.com/image/pic/item/6d81800a19d8bc3eca7f03cb808ba61ea8d345bf.jpg";
		return downloadUrl;
	}

	@Override
	public void loadData(CustomActivity activity, String type) {
		// TODO Auto-generated method stub
		super.loadData(activity, type);
	}

	@Override
	protected void OnItemClick() {
		// TODO Auto-generated method stub
		super.OnItemClick();
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
			List<WashingVO> dataList, ListView listView) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id, List<WashingVO> dataList) {
		// TODO Auto-generated method stub

	}

	public class ViewHolder {
		public Button image;
		public TextView name, discount, money, pay_money;
		public Button addcart;
	}

}
