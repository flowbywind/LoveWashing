package com.easylife.app_wash.view;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.easylife.app_wash.R;
import com.easylife.app_wash.widget.BaseFragment;

public class ActivityBaseFragment extends BaseFragment {
	// TextView mTitle;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = super.onCreateView(inflater, container, savedInstanceState);
		FragmentManager fm = ((MainActivity) mActivity)
				.getSupportFragmentManager();
		FragmentTransaction ft = fm.beginTransaction();
		ActivityFragment fragment = new ActivityFragment();
		ft.replace(R.id.container_activity, fragment);
		ft.commit();
		// mTitle = (TextView) view.findViewById(R.id.title);
		return view;
	}

	@Override
	public void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
	}

	@Override
	public View setContentView(LayoutInflater inflater, ViewGroup container) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragment_base_activity,
				container, false);
		return view;
	}

	// public class Adapter extends BaseAdapter {
	//
	// @Override
	// public int getCount() {
	// // TODO Auto-generated method stub
	// return 15;
	// }
	//
	// @Override
	// public Object getItem(int arg0) {
	// // TODO Auto-generated method stub
	// return null;
	// }
	//
	// @Override
	// public long getItemId(int arg0) {
	// // TODO Auto-generated method stub
	// return 0;
	// }
	//
	// @Override
	// public View getView(int arg0, View convertView, ViewGroup arg2) {
	// // TODO Auto-generated method stub
	// if (convertView == null) {
	// convertView = View.inflate(mActivity,
	// R.layout.list_item_activity_normal, null);
	// }
	// return convertView;
	// }
	//
	// }

}
