package com.easylife.app_wash.view;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.easylife.app_wash.R;
import com.sihehui.section_network.util.UserCacheManager;

public class HelpCenterActivity extends Activity {

	private ViewPager mViewPager;
	// private ArrayList<ImageView> mPageList;
	private ArrayList<View> mViewList;
	private int[] mResourcesIds;
	// private LinearLayout mPagerLayout;
	private int mTag; // 1时跳转到 软件首页 其他 直接finish

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.helpcenter);
		Intent intent = getIntent();
		mResourcesIds = intent.getIntArrayExtra("ids");
		mTag = intent.getIntExtra("tag", 0);
		mViewPager = (ViewPager) findViewById(R.id.help_viewpager);
		// mPagerLayout = (LinearLayout) findViewById(R.id.help_pagerlayout);
		// mPageList = new ArrayList<ImageView>();
		mViewList = new ArrayList<View>();
		LayoutInflater inflater = getLayoutInflater();
		if (mResourcesIds != null) {
			LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
					LinearLayout.LayoutParams.WRAP_CONTENT,
					LinearLayout.LayoutParams.WRAP_CONTENT);
			lp.setMargins(10, 10, 10, 10);
			for (int i = 0; i < mResourcesIds.length; i++) {
				View view;
				if (i == mResourcesIds.length - 1) {
					view = inflater
							.inflate(R.layout.helpcenter_view_last, null);
				} else {
					view = inflater.inflate(R.layout.helpcenter_view0, null);
				}
				view.setBackgroundResource(mResourcesIds[i]);
				mViewList.add(view);
				// ImageView imageView = new ImageView(this);
				// imageView.setImageResource(R.drawable.icon01);
				// imageView.setScaleType(ScaleType.MATRIX);
				// imageView.setLayoutParams(lp);
				// mPagerLayout.addView(imageView);
				// mPageList.add(imageView);
			}
		}
		mViewPager.setAdapter(mPagerAdapter);
		mViewPager.setOnPageChangeListener(new MyPagerChangeListener());
		// mPageList.get(0).setImageResource(R.drawable.icon02);
	}

	PagerAdapter mPagerAdapter = new PagerAdapter() {

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			// TODO Auto-generated method stub
			return arg0 == arg1;
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return mViewList.size();
		}

		@Override
		public void destroyItem(View container, int position, Object object) {
			((ViewPager) container).removeView(mViewList.get(position));
		}

		@Override
		public Object instantiateItem(View container, int position) {
			((ViewPager) container).addView(mViewList.get(position));
			return mViewList.get(position);
		}
	};

	class MyPagerChangeListener implements OnPageChangeListener {

		@Override
		public void onPageScrollStateChanged(int arg0) {
			// TODO Auto-generated method stub
		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onPageSelected(int arg0) {
			// TODO Auto-generated method stub
			// if (arg0 - 1 >= 0) {
			// mPageList.get(arg0 - 1).setImageResource(R.drawable.icon01);
			// }
			// if (arg0 + 1 < mPageList.size()) {
			// mPageList.get(arg0 + 1).setImageResource(R.drawable.icon01);
			// }
			// mPageList.get(arg0).setImageResource(R.drawable.icon02);
		}

	}

	private boolean isFirstRun() {
		UserCacheManager user = UserCacheManager.getInstance();
		user.init(getBaseContext());
		String uid = user.getUid();
		// Editor editor = mSharedPreferences.edit();
		boolean isFisrtRun = "".equals(uid);
		// if (isFisrtRun) {
		// editor.putBoolean("isfisrtRun", false);
		// editor.commit();
		// }
		return isFisrtRun;

	}

	public void startChlick(View view) {
		if (mTag == 1) {
			Intent intent = new Intent();
			// if (isFirstRun()) {
			intent.setClass(HelpCenterActivity.this, LoginActivity.class);
			// } else {
			// intent.setClass(HelpCenterActivity.this, MainActivity.class);
			// }

			startActivity(intent);
		}
		finish();
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		startChlick(null);
	}
}
