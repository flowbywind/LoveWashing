package com.easylife.app_wash.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.easylife.app_wash.R;
import com.easylife.app_wash.widget.BaseFragment;
import com.easylife.app_wash.widget.IdentityDialog;

public class SquareBaseFragment extends BaseFragment {
	// SquareAdapter mAdapter;

	// ListView mListView;
	Button mSpeakType;
	TextView mSpeak;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = super.onCreateView(inflater, container, savedInstanceState);
		mSpeakType = (Button) view.findViewById(R.id.speakType);
		mSpeak = (TextView) view.findViewById(R.id.speak);
		// mAdapter = new SquareAdapter(mActivity);
		// mListView.setAdapter(mAdapter);
		FragmentManager fm = ((MainActivity) mActivity)
				.getSupportFragmentManager();
		FragmentTransaction ft = fm.beginTransaction();
		fragment = new SquareFragment();
		ft.replace(R.id.container_square, fragment);
		ft.commit();
		return view;
	}

	SquareFragment fragment = null;
	private boolean isRealName = true;

	@Override
	public void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		mSpeak.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if (!mUser.isVip()) {
					IdentityDialog dialog = new IdentityDialog(mActivity,
							R.style.dialog, "请在认证信息后发言");
					dialog.show();
					return;
				}
				// 发言回来自动刷新
				if (fragment != null) {
					fragment.isNeedRefresh = true;
				}
				// Intent intent = new Intent(mActivity, SpeakActivity.class);
				// startActivity(intent);
			}
		});
		mSpeakType.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Log.d("shh", "fragment=" + fragment);
				if (isRealName) {
					isRealName = false;
					mSpeakType.setBackgroundResource(R.drawable.tab_left);
					if (fragment != null) {
						fragment.loadData("1");
					}
				} else {
					isRealName = true;
					mSpeakType.setBackgroundResource(R.drawable.tab_right);
					if (fragment != null) {
						fragment.loadData("0");
					}
				}
			}
		});
	}

	@Override
	public View setContentView(LayoutInflater inflater, ViewGroup container) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragment_base_square, container,
				false);
		return view;
	}

}
