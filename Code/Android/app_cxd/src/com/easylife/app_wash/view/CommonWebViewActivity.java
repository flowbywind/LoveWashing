package com.easylife.app_wash.view;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.easylife.app_wash.R;
import com.easylife.app_wash.widget.CustomActivity;
import com.easylife.app_wash.widget.ProgressDialogSet;
import com.sihehui.section_network.util.StoreActivity;

public class CommonWebViewActivity extends CustomActivity {

	ProgressDialogSet progressDialogSet;
	CommonWebViewActivity selfActivity;
	String titleName;
	TextView txt_title;

	@SuppressLint("SetJavaScriptEnabled")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		txt_title = (TextView) findViewById(R.id.txt_title);
		selfActivity = this;
		titleName = getIntent().getStringExtra("title");
		txt_title.setText(titleName);
		String url = getIntent().getStringExtra("url");
		WebView wv = (WebView) findViewById(R.id.webview1);
		// Log.d(TAG,"url="+url);
		if (url.startsWith("http://")) {
			// 普通打开url
			wv.loadUrl(url);
		} else {
			// 易宝相关的使用
			wv.loadData(url, "text/html", "UTF-8");
			Log.d(TAG, "url=" + url);
		}
		WebSettings ws = wv.getSettings();
		ws.setBuiltInZoomControls(true);
		ws.setJavaScriptEnabled(true);

		wv.setWebViewClient(new WebViewClient() {
			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				// TODO Auto-generated method stub
				// view.loadUrl(url);
				return true;
			}

			@Override
			public void onPageStarted(WebView view, String url, Bitmap favicon) {
				// TODO Auto-generated method stub
				super.onPageStarted(view, url, favicon);
				progressDialogSet = new ProgressDialogSet();
				progressDialogSet.setCon(selfActivity);
				progressDialogSet.setMsg("正在请求数据，请稍后...");
				progressDialogSet.startProgress();
			}

			@Override
			public void onPageFinished(WebView view, String url) {
				// TODO Auto-generated method stub
				super.onPageFinished(view, url);
				if (progressDialogSet != null) {
					progressDialogSet.stopProgress();
				}
			}
		});

	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		StoreActivity.getInstance().addActivity(this);
	}

	@Override
	protected void setContentView() {
		// TODO Auto-generated method stub
		setContentView(R.layout.common_webview);
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
	}

	@Override
	protected String getHeaderTitle() {
		// TODO Auto-generated method stub
		return "";
	}
}
