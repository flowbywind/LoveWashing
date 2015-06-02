package com.easylife.app_wash.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

import com.easylife.app_wash.R;
import com.easylife.app_wash.widget.BaseFragment;
import com.easylife.app_wash.widget.IdentityDialog;

public class QuestionBaseFragment extends BaseFragment {
	// QuestionAdapter mAdapter;

	// ListView mListView;
	TextView mAsk;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = super.onCreateView(inflater, container, savedInstanceState);
		mAsk = (TextView) view.findViewById(R.id.ask);
		FragmentManager fm = ((MainActivity) mActivity)
				.getSupportFragmentManager();
		FragmentTransaction ft = fm.beginTransaction();
		QuestionFragment fragment = new QuestionFragment();
		ft.replace(R.id.container_question, fragment);
		ft.commit();
		// mAdapter = new QuestionAdapter(mActivity);
		// mListView.setAdapter(mAdapter);
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
		View view = inflater.inflate(R.layout.fragment_base_question,
				container, false);

		return view;
	}

	// public class QuestionAdapter extends BaseItemAdapter {
	//
	// public QuestionAdapter(Context context) {
	// super(context);
	// // TODO Auto-generated constructor stub
	// }
	//
	// @Override
	// protected View getConvertView() {
	// // TODO Auto-generated method stub
	// View convertView = View.inflate(mActivity,
	// R.layout.list_item_question_normal, null);
	// return convertView;
	// }
	// }
}
