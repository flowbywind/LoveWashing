package com.easylife.app_wash.widget;

import java.util.List;

import android.R;
import android.content.Context;
import android.graphics.Color;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.widget.ImageView;

public class CustomOnPageChangeListener implements OnPageChangeListener {
	
	Context context;
	ImageView imageview;
	
	public CustomOnPageChangeListener(Context context, ImageView imageview){
		this.context = context;
		this.imageview = imageview;
	}
	// 确定选择的哪一页OnPageSelected
	@Override
	public void onPageSelected(int position) {
		//设置imageview的图片
//		imageview.setBackgroundResource(R.drawable.dot1);
	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {

		// 从1到2滑动，在1滑动前调用

	}

	@Override
	public void onPageScrollStateChanged(int arg0) {

		// 状态有三个0空闲，1是增在滑行中，2目标加载完毕

		/**
		 * 
		 * Indicates that the pager is in an idle, settled state. The current
		 * page
		 * 
		 * is fully in view and no animation is in progress.
		 */

		// public static final int SCROLL_STATE_IDLE = 0;

		/**
		 * 
		 * Indicates that the pager is currently being dragged by the user.
		 */

		// public static final int SCROLL_STATE_DRAGGING = 1;

		/**
		 * 
		 * Indicates that the pager is in the process of settling to a final
		 * position.
		 */

		// public static final int SCROLL_STATE_SETTLING = 2;

	}
}
