package com.easylife.app_wash.view;

import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

import com.easylife.app_wash.R;
import com.easylife.app_wash.widget.BaseActivity;
import com.easylife.app_wash.widget.ConnectionNetworkAbleUIBase;
import com.sihehui.section_network.http.info.ProcessException;
import com.sihehui.section_network.util.Contacts;
import com.sihehui.section_network.util.StoreActivity;
import com.sihehui.section_vo.vo.RealNameAuthVO;
import com.sihehui.section_vo.vo.UserLoginVO;

public class MyDetailActivity extends BaseActivity {
	private TextView mUserName, mRealname, mIdCard, mSex, mBirthday;
	private TextView realname_status, idcard_status, gendar_status,
			birthday_status;

	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		mUserName = (TextView) findViewById(R.id.txt_username);
		mRealname = (TextView) findViewById(R.id.txt_realname);
		mIdCard = (TextView) findViewById(R.id.txt_idCard);
		mSex = (TextView) findViewById(R.id.txt_sex);
		mBirthday = (TextView) findViewById(R.id.txt_birthday);

		realname_status = (TextView) findViewById(R.id.realname_status);
		idcard_status = (TextView) findViewById(R.id.idcard_status);
		gendar_status = (TextView) findViewById(R.id.gendar_status);
		birthday_status = (TextView) findViewById(R.id.birthday_status);
        loadData();
        loadIsAuth();
	}

	private void loadData() {
		MyDetailListener connection = new MyDetailListener(
				MyDetailActivity.this);
		Map<String, Object> input = new HashMap<String, Object>();
		input.put("name", mUser.getUserName());
		mServerCxdAPI.getObjectData(input,
				Contacts.ServiceURL.UserInfoAppAppService, connection,
				UserLoginVO.class);

	}

	private void loadIsAuth() {
		MyDetailIsAuthListener listener = new MyDetailIsAuthListener(
				MyDetailActivity.this);
		Map<String, Object> input = new HashMap<String, Object>();
		input.put("name", mUser.getUserName());
		mServerCxdAPI.getObjectData(input,
				Contacts.ServiceURL.IsRealnameAuthAppService, listener,
				RealNameAuthVO.class);
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		StoreActivity.getInstance().addActivity(MyDetailActivity.this);

	}

	@Override
	protected void setContentView() {
		// TODO Auto-generated method stub
		setContentView(R.layout.mydetail);
	}

	@Override
	protected String getHeaderTitle() {
		// TODO Auto-generated method stub
		return "个人信息";
	}

	private class MyDetailListener extends
			ConnectionNetworkAbleUIBase<UserLoginVO> {

		public MyDetailListener(Context context) {
			super(context);
			// TODO Auto-generated constructor stub
		}

		@Override
		public void onSuccessed(UserLoginVO result) throws ProcessException {
			// TODO Auto-generated method stub
			if (result == null) {
				return;
			}
			// 赋值
			mUserName.setText(result.getUsername());
			mRealname.setText(result.getRealname());
			mSex.setText(result.getSex());
			mBirthday.setText(result.getBirthday());
			String idCard=result.getIdCard();
			if(idCard!=null && idCard.length()>6)
			{
				idCard=idCard.substring(0,6)+" **** **** ****";
				mIdCard.setText(idCard);
			}
		}
	}

	private class MyDetailIsAuthListener extends
			ConnectionNetworkAbleUIBase<RealNameAuthVO> {

		public MyDetailIsAuthListener(Context context) {
			super(context);
			// TODO Auto-generated constructor stub
		}

		@Override
		public void onSuccessed(RealNameAuthVO result) throws ProcessException {
			// TODO Auto-generated method stub
			if (result == null) {
				result = new RealNameAuthVO();
			}
			String str = "未认证";
			if (result.getAuth() == "1") {
				str = "已认证";
			}
			realname_status.setText(str);
			idcard_status.setText(str);
			gendar_status.setText(str);
			birthday_status.setText(str);
		}
	}

}
