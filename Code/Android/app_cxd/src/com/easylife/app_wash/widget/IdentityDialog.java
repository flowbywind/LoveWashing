package com.easylife.app_wash.widget;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.easylife.app_wash.R;
import com.easylife.app_wash.view.IdentificationActivity;

public class IdentityDialog extends Dialog {

	String content = "";
	CustomActivity activity;

	public IdentityDialog(Context context, int theme, String content) {
		super(context, theme);
		// TODO Auto-generated constructor stub
		this.activity = (CustomActivity) context;
		this.content = content;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dialog_identity);
		setCanceledOnTouchOutside(true);
		Button queding = (Button) findViewById(R.id.ensure);
		Button cancel = (Button) findViewById(R.id.cancel);
		Button dismiss = (Button) findViewById(R.id.dismiss);
		View guanbi = findViewById(R.id.title);
		TextView contentText = (TextView) findViewById(R.id.content);
		contentText.setText(content);
		queding.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(activity,
						IdentificationActivity.class);
				((CustomActivity) activity).startActivity(intent);
				dismiss();
			}
		});

		cancel.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				dismiss();
			}
		});

		dismiss.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				dismiss();
			}
		});

		guanbi.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				dismiss();
			}
		});
	}

}
