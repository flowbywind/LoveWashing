package com.easylife.app_wash.widget;

import java.util.List;

import com.sihehui.section_vo.vo.HisitoryJiaoyiVO;

import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;

public abstract class BaseActivity extends CustomActivity {

	// // 开始启动另一个activity的动画,默认是返回动画
	// public void startActivityAnimation() {
	// onBackPressed();
	// overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
	// }

	protected abstract void setContentView();

	public float curX;
	

	public boolean dispatchTouchEvent(MotionEvent event) {
		if (event.getAction() == MotionEvent.ACTION_DOWN) {
			curX = event.getRawX();
		}
		if (event.getAction() == MotionEvent.ACTION_UP) {
			// Log.d("appallcat", "mCurrIndex=" + " curX=" + curX
			// + " event.getRawx()=" + event.getRawX() + " offset="
			// + (curX - event.getX()));
			if (event.getRawX() - curX > dp2Pixel()) {
				// 向右滑动返回上一级
				startActivityAnimation();
			}

		}
		return super.dispatchTouchEvent(event);
	}

	protected float dp2Pixel() {
		float pixel = getResources().getDisplayMetrics().density;
		return pixel * 100;
	}



}
