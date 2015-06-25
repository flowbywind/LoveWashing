package com.easylife.app_wash.view;

import android.content.Context;
import android.util.AttributeSet;

import com.easylife.app_wash.widget.BaseViewPager;
import com.easylife.app_wash.widget.CustomOnPageChangeListener;

public class HomeViewPager extends BaseViewPager {

	public HomeViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onFinishInflate() {
		// TODO Auto-generated method stub
		super.onFinishInflate();
		
		//初始化
		this.setOnPageChangeListener(new CustomOnPageChangeListener(getContext(), mDotImageView) {

			@Override
			public void onPageSelected(int position) {
				// TODO Auto-generated method stub
				super.onPageSelected(position);
				mCallbacker.changeTo(position);
			}
		});		
		//设置viewpager保存当前page左右各两个page不被销毁
		setOffscreenPageLimit(4);
	}
	
}
