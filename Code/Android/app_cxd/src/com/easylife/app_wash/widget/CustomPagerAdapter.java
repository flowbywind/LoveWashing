package com.easylife.app_wash.widget;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

public class CustomPagerAdapter extends FragmentPagerAdapter {

	protected List<FragmentItem> mFragmentList;
	private Fragment mOldFragment;
	private final Context mContext;
	private final FragmentManager fManager;
	private int mViewId;

	public CustomPagerAdapter(FragmentManager fragmentManager, Context context) {
		super(fragmentManager);
		// TODO Auto-generated constructor stub
		fManager = fragmentManager;
		mContext = context;
		mFragmentList = new ArrayList<FragmentItem>();
	}

	public void addFragmentName(String name) {
		FragmentItem item = new FragmentItem();
		item.mClazz = name;
		mFragmentList.add(item);
	}

	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		// TODO Auto-generated method stub
		mViewId = container.getId();
		return super.instantiateItem(container, position);
	}

	@Override
	public int getCount() {

		return mFragmentList.size();

	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		Fragment fg = (Fragment) arg1;
		return arg0 == fg.getView();
	}

	@Override
	public int getItemPosition(Object object) {
		if (object == mOldFragment) {
			Log.i("app11x5", "getItemPosition POSITION_NONE");
			return POSITION_NONE;
		} else
			return super.getItemPosition(object);
	}

	@Override
	public void destroyItem(View arg0, int arg1, Object arg2) {
		Log.i("app11x5", "destroyItem arg1:" + arg1);
		if (arg2 == mOldFragment) {
			((ViewPager) arg0).removeViewAt(arg1);
		}

	}

	public void replaceView(int pos, String name) {
		if (name == null || name.length() == 0) {
			return;
		}
		FragmentItem item = mFragmentList.remove(pos);
		mOldFragment = item.mFragment;
		item = new FragmentItem();
		item.mClazz = name;
		mFragmentList.add(pos, item);
		String tag = "android:switcher:" + mViewId + ":" + pos;
		FragmentTransaction ft = fManager.beginTransaction();
		ft.remove(mOldFragment);
		ft.add(mViewId, getItem(pos), tag);
		ft.commit();
		notifyDataSetChanged();
	}

	@Override
	public Fragment getItem(int arg0) {
		// TODO Auto-generated method stub
		FragmentItem item = mFragmentList.get(arg0);
		if (item.mFragment == null) {
			Log.i("app11x5", "pagerAdapter getItem:" + arg0 + " class:"
					+ item.mClazz);
			item.mFragment = Fragment.instantiate(mContext, item.mClazz);
		}
		return item.mFragment;
	}

	class FragmentItem {
		String mClazz;
		Fragment mFragment;

		FragmentItem() {
			mClazz = "";
			mFragment = null;
		}
	}

	public void clear() {
		for (int i = 0; i < mFragmentList.size(); i++) {
			FragmentItem fragment = mFragmentList.get(i);
			fragment.mFragment = null;
			fragment = null;
		}
		mFragmentList.clear();

	}
}
