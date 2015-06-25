package com.easylife.app_wash.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView;
import android.widget.ListAdapter;

import com.easylife.app_wash.R;
import com.easylife.app_wash.widget.BaseFragment;
import com.easylife.app_wash.widget.CustomOnClickListener;
import com.easylife.app_wash.widget.ListItemAdapter;
import com.easylife.app_wash.widget.ListViewForScrollView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

public abstract class ListBaseFragment extends BaseFragment {

	protected ListViewForScrollView listView;
	private List<String> listData;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = super.onCreateView(inflater, container, savedInstanceState);
		listView = (ListViewForScrollView) view.findViewById(R.id.myList1);
		ListAdapter adapter = new ListItemAdapter(mActivity, GetListData());
		listView.setAdapter(adapter);
		return view;
	}

	@Override
	public void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				OnItemClick(parent, view, position, id);
			}
		});
	}

	// 单击listview事件
	protected abstract void OnItemClick(AdapterView<?> parent, View view,
			int position, long id);

	// 设置list项数据
	public abstract List<String> SetListData();

	public List<Map<String, Object>> GetListData() {
		listData = SetListData();
		List<Map<String, Object>> mapListData = new ArrayList<Map<String, Object>>();
		Map<String, Object> map;
		if (listData != null && listData.size() > 0) {
			System.out.println(listData.size());
			for (String item : listData) {
				map = new HashMap<String, Object>();
				map.put("text", item);
				mapListData.add(map);
			}
		}
		return mapListData;
	}

	@Override
	protected void OnItemClick() {
		// TODO Auto-generated method stub
		super.OnItemClick();
	}

}
