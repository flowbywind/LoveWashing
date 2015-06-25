package com.easylife.app_wash.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.easylife.app_wash.R;

public class DialogAddAndSubtractView extends LinearLayout {
	private String mStartString;
	private String mEndString;
	private int mStartNum;
	private int maxNum, minNum = 0;
	private LinearLayout mSubtractBtn, mAddBtn;
	private TextView mTextView;
	private Context mContext;
	private TextViewChangeListener mTextViewChangeListener;

	public DialogAddAndSubtractView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		this.mContext = context;
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		inflater.inflate(R.layout.dialog_addandsubtractview, this);
		mSubtractBtn = (LinearLayout) findViewById(R.id.subtractbtn);
		mAddBtn = (LinearLayout) findViewById(R.id.addbtn);
		mTextView = (TextView) findViewById(R.id.addandsubtracttextview);
		mSubtractBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (mStartNum > minNum) {
					mStartNum--;
					if (mStartString == null)
						mStartString = "";
					if (mEndString == null)
						mEndString = "";
					mTextView.setText(mStartString + mStartNum + mEndString);
					if (mTextViewChangeListener != null)
						mTextViewChangeListener.textChange(mStartNum);
				}
			}
		});
		mAddBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (mStartNum < maxNum) {
					mStartNum++;
					if (mStartString == null)
						mStartString = "";
					if (mEndString == null)
						mEndString = "";
					mTextView.setText(mStartString + mStartNum + mEndString);
					if (mTextViewChangeListener != null)
						mTextViewChangeListener.textChange(mStartNum);
				}
			}
		});
		mTextView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// mTextView.setBackgroundResource(R.drawable.dialog_middle_focus);
				if (mShowMenualDialogListener != null) {
					mShowMenualDialogListener.showMenualDialog();
				}
			}
		});

	}

	/**
	 * 设置此view的前后描述语 和原数值
	 * 
	 * @param mStartString
	 *            数值前描述
	 * @param mStartNum
	 *            数值
	 * @param mEndString
	 *            数值后描述
	 * @param maxNum
	 *            最大值
	 * @param minNum
	 *            最小值 默认为0
	 */
	public void initSetting(String mStartString, int mStartNum,
			String mEndString, int maxNum, int minNum, int textColor) {
		this.mStartString = mStartString;
		this.mStartNum = mStartNum;
		this.mEndString = mEndString;
		this.maxNum = maxNum;
		this.minNum = minNum;
		if (this.mStartString == null)
			this.mStartString = "";
		if (this.mEndString == null)
			this.mEndString = "";
		mTextView.setTextColor(mContext.getResources().getColor(textColor));
		mTextView.setText(mStartString + mStartNum + mEndString);
	}

	public int getNum() {
		return mStartNum;
	}

	public void setTextViewChangeListener(TextViewChangeListener listener) {
		this.mTextViewChangeListener = listener;
	}

	// 更新倍数
	public void setNum(String mStartNum) {
		if (mStartNum == null || "".equals(mStartNum) || "0".equals(mStartNum)) {
			mStartNum = "1";
		}
		this.mStartNum = Integer.valueOf(mStartNum);
		if (this.mStartString == null)
			this.mStartString = "";
		if (this.mEndString == null)
			this.mEndString = "";
		mTextView.setText(mStartString + mStartNum + mEndString);
		if (mTextViewChangeListener != null)
			mTextViewChangeListener.textChange(Integer.valueOf(mStartNum));
	}

	// 控制加减倍数接口
	public interface TextViewChangeListener {
		abstract void textChange(int num);
	}

	ShowMenualDialogListener mShowMenualDialogListener;

	public void setShowMenualDialogListener(ShowMenualDialogListener listener) {
		this.mShowMenualDialogListener = listener;
	}

	// 控制显示倍数键盘接口
	public interface ShowMenualDialogListener {
		abstract void showMenualDialog();
	}

	public void finishInput() {
		mTextView.setBackgroundResource(R.drawable.dialog_middle);
	}
	// public void updateBeishu(int beishu){
	//
	// }
}
