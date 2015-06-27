package com.easylife.app_wash.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListAdapter;

import com.easylife.app_wash.R;
import com.easylife.app_wash.widget.BaseActivity;
import com.easylife.app_wash.widget.ListItemEntityAdapter;
import com.easylife.app_wash.widget.ListViewForScrollView;
import com.sihehui.section_vo.vo.ListViewItemVO;

public class AboutusActivity extends BaseActivity {
	String url;
	private ListViewForScrollView listView;
	private ListViewItemVO listItemVO;
	private List<ListViewItemVO> listData;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		System.out.println("run on create");
		listView = (ListViewForScrollView) findViewById(R.id.myList1);
		listData = SetListData();
		ListAdapter adapter = new ListItemEntityAdapter(this, listData);
		listView.setAdapter(adapter);
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(AboutusActivity.this,
						CommonWebViewActivity.class);
				listItemVO = listData.get(position);
				String url = "http://www.baidu.com";
				if (listItemVO != null) {
					// url=listItemVO.getUrl();
				}
				intent.putExtra("title", listItemVO.getTitle());
				intent.putExtra("url", url);
				startActivity(intent);
			}
		});
	}

	@Override
	protected void setContentView() {
		// TODO Auto-generated method stub
		setContentView(R.layout.aboutus);
	}

	private List<ListViewItemVO> SetListData() {
		list = new ArrayList<ListViewItemVO>();
		list.add(new ListViewItemVO("公司简介"));
		list.add(new ListViewItemVO("团队介绍"));
		list.add(new ListViewItemVO("安全问题"));
		list.add(new ListViewItemVO("回款问题"));
		list.add(new ListViewItemVO("投资问题"));
		list.add(new ListViewItemVO("新手必读"));
		return list;
	}

	private List<ListViewItemVO> list;
	int nums = 300000;
	private List<Map<String, Object>> maplist;

	// 测试map和实体对象的效率
	private void run() {
		setlistmap();
		setlistobject();
		getlistmap();
		getlistobject();
	}

	private void setlistobject() {
		long startTime = System.currentTimeMillis(); // 获取开始时间
		list = new ArrayList<ListViewItemVO>();
		for (int i = 0; i < nums; i++) {
			ListViewItemVO model = new ListViewItemVO();
			model.setTitle("testData");
			model.setImageId(100);
			list.add(model);
		}
		long endTime = System.currentTimeMillis(); // 获取结束时间
		System.out.println("setlistobject程序运行时间：" + (endTime - startTime)
				+ "ms");
	}

	private void getlistobject() {
		long startTime = System.currentTimeMillis(); // 获取开始时间
		for (int i = 0; i < list.size(); i++) {
			ListViewItemVO model = (ListViewItemVO) list.get(i);
			String data = model.getTitle();
			int id = model.getImageId();
		}
		long endTime = System.currentTimeMillis(); // 获取结束时间
		System.out.println("getlistobject程序运行时间：" + (endTime - startTime)
				+ "ms");
	}

	private void setlistmap() {
		long startTime = System.currentTimeMillis(); // 获取开始时间
		maplist = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < nums; i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("Title", "testData");
			map.put("ImageId", 100);
			maplist.add(map);
		}
		long endTime = System.currentTimeMillis(); // 获取结束时间
		System.out.println("setlistmap程序运行时间：" + (endTime - startTime) + "ms");
	}

	private void getlistmap() {
		long startTime = System.currentTimeMillis(); // 获取开始时间
		for (int i = 0; i < maplist.size(); i++) {
			Map<String, Object> map = maplist.get(i);
			String title = (String) map.get("Title");
			int imageId = (Integer) map.get("ImageId");
		}
		long endTime = System.currentTimeMillis(); // 获取结束时间
		System.out.println("getlistmap程序运行时间：" + (endTime - startTime) + "ms");
	}

	@Override
	protected String getHeaderTitle() {
		// TODO Auto-generated method stub
		return "关于我们";
	}
}
