package com.easylife.app_wash.widget;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Handler;
import android.os.Message;

public class ProgressDialogSet {
	private String msg = "正在请求数据,请稍后";
	private Context con;

	int progress = 0;
	Handler progressHandler;
	ProgressDialog progressDialog;

	// public void setKeyDownDisable() {
	// ((CustomActivity) con).setKeyDownDisable();
	// ((CustomActivity) con).onBackPressed();
	// // 复写系统的onKeyDown方法
	// KeyEvent event = new KeyEvent(KeyEvent.ACTION_DOWN,
	// KeyEvent.KEYCODE_BACK);
	// // onKeyDown返回值为布尔值
	// ((CustomActivity) con).onKeyDown(KeyEvent.KEYCODE_BACK, event);
	// }
	// public class MyProgressDialog extends ProgressDialog {
	//
	// public MyProgressDialog(Context context) {
	// super(context);
	// this.getWindow().setType(
	// WindowManager.LayoutParams.TYPE_KEYGUARD_DIALOG);
	// // TYPE_KEYGUARD_DIALOG:disable any key,eg:back key,home key etc.
	// }
	//
	// @Override
	// public boolean onSearchRequested() {
	// return false;
	// }
	//
	// }

	public void startProgress() {

		progressDialog = new ProgressDialog(getCon());
		progressDialog.setMessage(getMsg());
		progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		progressDialog.setMax(16);
		// progressDialog.setCanceledOnTouchOutside(true);
		// progressDialog.setCancelable(false);
		progressDialog.show();
		// progressDialog.getWindow().setType(
		// WindowManager.LayoutParams.TYPE_KEYGUARD_DIALOG);
		// ((CustomActivity) con).setKeyDownDisable(true);
		progressHandler = new Handler() {

			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				super.handleMessage(msg);
				if (progress >= Integer.MAX_VALUE) {
					progress = 0;
					progressDialog.dismiss();
				} else {
					progress++;
					progressDialog.incrementProgressBy(1);
					// 自己回调自己
					progressHandler.sendEmptyMessageDelayed(3, 500);
				}
			}
		};
		// 第一次传值，使其运行
		progress = (progress > 0) ? progress : 0;
		progressDialog.setProgress(progress);
		progressHandler.sendEmptyMessage(1);

	}

	public void stopProgress() {
		// ((CustomActivity) con).setKeyDownDisable(false);
		progress = 0;
		if (progressDialog != null) {
			progressDialog.dismiss();
		}

	}

	public Context getCon() {
		return con;
	}

	public void setCon(Context con) {
		this.con = con;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
}
