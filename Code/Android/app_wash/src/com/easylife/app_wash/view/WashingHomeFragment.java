package com.easylife.app_wash.view;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.easylife.app_wash.R;
import com.easylife.app_wash.widget.BaseWashingFragment;
import com.easylife.app_wash.widget.CustomPagerAdapter;

public class WashingHomeFragment extends BaseWashingFragment {

	@Override
	public View setContentView(LayoutInflater inflater, ViewGroup container) {
		// TODO Auto-generated method stub
		return inflater.inflate(R.layout.washing_home, container, false);
	}

	// implements IViewPagerChangeable
	private ViewPager mViewPager;
	private CustomPagerAdapter mAdapter;
	TextView mTabTitle1, mTabTitle2, mTabTitle3, mTabTitle4, mTabTitle5;
	List<TextView> mTextList = new ArrayList<TextView>();
	List<ImageView> mImageList = new ArrayList<ImageView>();
	final private String[] mTabFragmentNames = new String[] {
			"com.easylife.app_wash.view.TabListFragment",
			"com.easylife.app_wash.view.TabListFragment",
			"com.easylife.app_wash.view.TabListFragment",
			"com.easylife.app_wash.view.TabListFragment",
			"com.easylife.app_wash.view.TabListFragment" };

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = super.onCreateView(inflater, container, savedInstanceState);
		// Log.d("zhangyaobin", "view=" + view);
		mViewPager = (ViewPager) view.findViewById(R.id.viewpagerLayout);
		mTabTitle1 = (TextView) view.findViewById(R.id.tabTitle1);
		mTabTitle2 = (TextView) view.findViewById(R.id.tabTitle2);
		mTabTitle3 = (TextView) view.findViewById(R.id.tabTitle3);
		mTabTitle4 = (TextView) view.findViewById(R.id.tabTitle4);
		mTabTitle5 = (TextView) view.findViewById(R.id.tabTitle5);
		ImageView image1 = (ImageView) view.findViewById(R.id.tab_bottom1);
		ImageView image2 = (ImageView) view.findViewById(R.id.tab_bottom2);
		ImageView image3 = (ImageView) view.findViewById(R.id.tab_bottom3);
		ImageView image4 = (ImageView) view.findViewById(R.id.tab_bottom4);
		ImageView image5 = (ImageView) view.findViewById(R.id.tab_bottom5);
		mImageList.add(image1);
		mImageList.add(image2);
		mImageList.add(image3);
		mImageList.add(image4);
		mImageList.add(image5);
		// 每个tab包含对应的数据下载链接
		mTabTitle1.setTag("01");
		mTabTitle2.setTag("02");
		mTabTitle3.setTag("03");
		mTabTitle4.setTag("04");
		mTabTitle5.setTag("05");

		mTextList.add(mTabTitle1);
		mTextList.add(mTabTitle2);
		mTextList.add(mTabTitle3);
		mTextList.add(mTabTitle4);
		mTextList.add(mTabTitle5);
		setFocus(0);
		mAdapter = new CustomPagerAdapter(getChildFragmentManager(), mActivity);
		mAdapter.addFragmentName(mTabFragmentNames[0]);
		mAdapter.addFragmentName(mTabFragmentNames[1]);
		mAdapter.addFragmentName(mTabFragmentNames[2]);
		mAdapter.addFragmentName(mTabFragmentNames[3]);
		mAdapter.addFragmentName(mTabFragmentNames[4]);
		mViewPager.setAdapter(mAdapter);
		mViewPager.setOnPageChangeListener(new MyOnPageChangeListener());
		mViewPager.setOffscreenPageLimit(4);
		((TabListFragment) mAdapter.getItem(0)).downLoadData(
				(MainActivity) mActivity, (String) mTextList.get(0).getTag());
		mTabTitle1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				mViewPager.setCurrentItem(0);
			}
		});
		mTabTitle2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				mViewPager.setCurrentItem(1);
			}
		});
		mTabTitle3.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				mViewPager.setCurrentItem(2);
			}
		});
		mTabTitle4.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				mViewPager.setCurrentItem(3);
			}
		});
		mTabTitle5.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				mViewPager.setCurrentItem(4);
			}
		});

		// for (int i = 0; i < mTextList.size(); i++) {
		// if (mTextList.get(i).isPressed()) {
		// mViewPager.setCurrentItem(i);
		// }
		// }
		return view;
	}

	private void setFocus(int pos) {
		for (int i = 0; i < mTextList.size(); i++) {
			if (pos == i) {
				mTextList.get(i).setEnabled(false);
				mImageList.get(i).setVisibility(View.VISIBLE);
			} else {
				mTextList.get(i).setEnabled(true);
				mImageList.get(i).setVisibility(View.INVISIBLE);
			}
		}

	}

	@Override
	public void onStart() {
		// TODO Auto-generated method stub
		super.onStart();

	}

	private void setOnChanged(int position) {
		setFocus(position);
		((TabListFragment) mAdapter.getItem(position)).downLoadData(
				(MainActivity) mActivity, (String) mTextList.get(position)
						.getTag());
	}

	public class MyOnPageChangeListener implements OnPageChangeListener {

		@Override
		public void onPageSelected(int position) {
			setOnChanged(position);
		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
		}

		@Override
		public void onPageScrollStateChanged(int arg0) {
		}
	}

	// @Override
	// public void changeTo(int position) {
	// // TODO Auto-generated method stub
	//
	// }
}
