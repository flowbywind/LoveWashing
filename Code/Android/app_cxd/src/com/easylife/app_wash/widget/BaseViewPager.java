package com.easylife.app_wash.widget;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.widget.ImageView;

public abstract class BaseViewPager extends ViewPager {
	// 切换显示"..."控件
	protected ImageView mDotImageView;
	protected List<String> mFragmentNameList;
	protected IViewPagerChangeable mCallbacker;

	public BaseViewPager(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		mCallbacker = (IViewPagerChangeable) context;
		mFragmentNameList = new ArrayList<String>();
	}

	public BaseViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		mCallbacker = (IViewPagerChangeable) context;
		mFragmentNameList = new ArrayList<String>();
	}

	public void setDotImage(ImageView imageview) {
		mDotImageView = imageview;
	}

	// @Override
	// protected void onFinishInflate() {
	// // TODO Auto-generated method stub
	// super.onFinishInflate();
	//
	// // 初始化
	// this.setOnPageChangeListener(new CustomOnPageChangeListener(
	// getContext(), mDotImageView) {
	//
	// @Override
	// public void onPageSelected(int position) {
	// // TODO Auto-generated method stub
	// super.onPageSelected(position);
	// mCallbacker.changeTo(position);
	// }
	// });
	// // 设置viewpager保存当前page左右各两个page不被销毁
	// setOffscreenPageLimit(2);
	// }

}
