package com.easylife.app_wash.widget;

import java.util.NoSuchElementException;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.LinearLayout;

public class StickyLayout extends LinearLayout {
	private static final String TAG = "StickyLayout";

	public interface OnGiveUpTouchEventListener {
		public boolean giveUpTouchEvent(MotionEvent event);
	}

	private View mHeader;
	private View mContent;
	private OnGiveUpTouchEventListener mGiveUpTouchEventListener;

	// header的高度 单位：px
	private int mOriginalHeaderHeight;
	private int mHeaderHeight;

	private int mStatus = STATUS_EXPANDED;
	public static final int STATUS_EXPANDED = 1;
	public static final int STATUS_COLLAPSED = 2;

	private int mTouchSlop;

	// 分别记录上次滑动的坐标
	private int mLastX = 0;
	private int mLastY = 0;

	// 分别记录上次滑动的坐标(onInterceptTouchEvent)
	private int mLastXIntercept = 0;
	private int mLastYIntercept = 0;

	// 用来控制滑动角度，仅当角度a满足如下条件才进行滑动：tan a = deltaX / deltaY > 2
	private static final int TAN = 2;

	public StickyLayout(Context context) {
		super(context);
	}

	public StickyLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	PullDownTemplateView mPullDownTemplateView;

	public void setPullDownTemplateView(
			PullDownTemplateView mPullDownTemplateView) {
		this.mPullDownTemplateView = mPullDownTemplateView;
	}

	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	public StickyLayout(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	@Override
	public void onWindowFocusChanged(boolean hasWindowFocus) {
		super.onWindowFocusChanged(hasWindowFocus);
		Log.d(TAG, "onWindowFocusChanged" + " hasWindowFocus=" + hasWindowFocus
				+ " mHeader=" + mHeader + " mContent=" + mContent);
		if (hasWindowFocus && (mHeader == null || mContent == null)) {
			initData();
		}
	}

	// boolean changeTab
	public void initData() {
		int headerId = getResources().getIdentifier("header", "id",
				getContext().getPackageName());
		int contentId = getResources().getIdentifier("content", "id",
				getContext().getPackageName());
		if (headerId != 0 && contentId != 0) {
			// if (changeTab && (mHeader != null && mContent != null)) {
			// return;
			// }
			mHeader = findViewById(headerId);
			mContent = findViewById(contentId);
			mOriginalHeaderHeight = mHeader.getMeasuredHeight();
			mHeaderHeight = mOriginalHeaderHeight;
			mTouchSlop = ViewConfiguration.get(getContext())
					.getScaledTouchSlop();
			Log.d(TAG, "mTouchSlop = " + mTouchSlop);
		} else {
			throw new NoSuchElementException(
					"Did your view with \"header\" or \"content\" exist?");
		}
	}

	// 指选择器在最上面
	private boolean isTop = false;

	public void setOnGiveUpTouchEventListener(OnGiveUpTouchEventListener l) {
		mGiveUpTouchEventListener = l;
	}

	@Override
	public boolean onInterceptTouchEvent(MotionEvent event) {
		int intercepted = 0;
		int x = (int) event.getX();
		int y = (int) event.getY();

		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN: {
			mLastXIntercept = x;
			mLastYIntercept = y;
			mLastX = x;
			mLastY = y;
			intercepted = 0;
			break;
		}
		case MotionEvent.ACTION_MOVE: {
			int deltaX = x - mLastXIntercept;
			int deltaY = y - mLastYIntercept;
			// Log.d("zhangyaobin", "isTop="
			// + isTop
			// + " deltaY="
			// + deltaY
			// + " mTouchSlop="
			// + mTouchSlop
			// + " firstVisiblePosition="
			// + mPullDownTemplateView.pullDownView.getListView()
			// .getFirstVisiblePosition());
			if (isTop) {
				if (deltaY > 0) {
					if (mPullDownTemplateView.pullDownView.getListView()
							.getFirstVisiblePosition() != 0) {
						intercepted = 0;
					} else {
						intercepted = 1;
					}
				} else {
					intercepted = 0;
				}
			} else {
				if (deltaY >= 0) {
					intercepted = 0;
				} else {
					intercepted = 1;
				}
			}
			// if (deltaY <= -mTouchSlop) {
			// intercepted = 1;
			// } else {
			// if (isTop) {
			// if (deltaY > 0) {
			// intercepted = 1;
			// } else {
			// intercepted = 0;
			// }
			//
			// } else if (deltaY >= mTouchSlop) {
			//
			// intercepted = 0;
			// }
			// }
			// intercepted=0;
			break;
		}
		case MotionEvent.ACTION_UP: {
			intercepted = 0;
			mLastXIntercept = mLastYIntercept = 0;
			break;
		}
		default:
			break;
		}

		// Log.d(TAG, "intercepted=" + intercepted);
		return intercepted != 0;
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		int x = (int) event.getX();
		int y = (int) event.getY();
		// Log.d(TAG, "x=" + x + "  y=" + y + "  mlastY=" + mLastY);
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN: {
			break;
		}
		case MotionEvent.ACTION_MOVE: {
			int deltaX = x - mLastX;
			int deltaY = y - mLastY;
			// Log.d(TAG, "mHeaderHeight=" + mHeaderHeight + "  deltaY=" +
			// deltaY
			// + "  mlastY=" + mLastY);
			mHeaderHeight += deltaY;
			// if(){
			//
			// }
			setHeaderHeight(mHeaderHeight);
			break;
		}
		case MotionEvent.ACTION_UP: {
			// 这里做了下判断，当松开手的时候，会自动向两边滑动，具体向哪边滑，要看当前所处的位置
			int destHeight = 0;
			if (mHeaderHeight <= mOriginalHeaderHeight * 0.5) {
				destHeight = 0;
				mStatus = STATUS_COLLAPSED;
				isTop = true;
			} else {
				isTop = false;
				destHeight = mOriginalHeaderHeight;
				mStatus = STATUS_EXPANDED;
			}
			// 慢慢滑向终点
			this.smoothSetHeaderHeight(mHeaderHeight, destHeight, 500);
			break;
		}
		default:
			break;
		}
		mLastX = x;
		mLastY = y;
		return true;
	}

	public void smoothSetHeaderHeight(final int from, final int to,
			long duration) {
		final int frameCount = (int) (duration / 1000f * 30) + 1;
		final float partation = (to - from) / (float) frameCount;
		new Thread("Thread#smoothSetHeaderHeight") {

			@Override
			public void run() {
				for (int i = 0; i < frameCount; i++) {
					final int height;
					if (i == frameCount - 1) {
						height = to;
					} else {
						height = (int) (from + partation * i);
					}
					post(new Runnable() {
						public void run() {
							setHeaderHeight(height);
						}
					});
					try {
						sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			};

		}.start();
	}

	private void setHeaderHeight(int height) {
		// Log.d(TAG, "setHeaderHeight height=" + height + " mHeaderHeight="
		// + mHeaderHeight);
		if (height < 0) {
			height = 0;
		} else if (height > mOriginalHeaderHeight) {
			height = mOriginalHeaderHeight;
		}
		//
		// Log.d(TAG, "mHeader=" + mHeader);
		if (mHeader == null) {
			initData();
		}
		// || true
		// if (mHeaderHeight != height ) {
		// Log.d(TAG, "setHeaderHeight");
		// mHeaderHeight = height;
		mHeader.getLayoutParams().height = height;
		mHeader.requestLayout();
		// }
	}

}
