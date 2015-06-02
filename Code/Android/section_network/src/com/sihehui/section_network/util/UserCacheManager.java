package com.sihehui.section_network.util;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.sihehui.section_vo.vo.LoginVO;
import com.sihehui.section_vo.vo.UserLoginVO;

public class UserCacheManager {
	static UserCacheManager userCacheManager;
	SharedPreferences mSharedPreferences;

	// Context con;
	Editor editor;

	public static UserCacheManager getInstance() {
		if (userCacheManager == null) {
			userCacheManager = new UserCacheManager();
		}
		return userCacheManager;
	}

	public void init(Context con) {
		if (mSharedPreferences == null) {
			mSharedPreferences = con.getSharedPreferences("userData",
					Activity.MODE_PRIVATE);
			editor = mSharedPreferences.edit();
		}
	}

	public void setData(String uid, String token) {
		editor.putString("uid", uid);
		editor.putString("token", token);
		editor.commit();
	}

	public void setData(UserLoginVO vo) {

		editor.putString("uid", vo.getId());
		editor.putString("mobileNumber", vo.getMobileNumber());
		editor.putString("userName", vo.getUsername());
		editor.putString("password", vo.getPassword());
		editor.putString("headPic", vo.getPhoto());
		editor.putString("status", vo.getStatus());
		editor.putString("referrer", vo.getReferrer());
		editor.putString("cityId", "");
		editor.putString("cityName", "");
		editor.putString("usertypeId", "");
		editor.putString("industryId", "");
		editor.putString("brandName", "");
		editor.putString("mallName", "");
		editor.putString("jobId", "");
		editor.putString("token", "");
		editor.commit();
	}

	public void setData(LoginVO vo) {

		editor.putString("uid", vo.getUid());
		editor.putString("token", vo.getToken());
		editor.putString("userId", vo.getUserId());
		editor.putString("userAccout", vo.getUserAccout());
		editor.putString("userName", vo.getUserName());
		editor.putString("password", vo.getPassword());
		editor.putString("headPic", vo.getHeadPic());
		editor.putString("cityId", vo.getCityId());
		editor.putString("cityName", vo.getCityName());
		editor.putString("usertypeId", vo.getUsertypeId());
		editor.putString("industryId", vo.getIndustryId());
		editor.putString("brandName", vo.getBrandName());
		editor.putString("mallName", vo.getMallName());
		editor.putString("jobId", vo.getJobId());
		editor.putString("status", vo.getStatus());
		editor.putString("describle", vo.getCompanyDesc());
		editor.putString("score", vo.getScore());
		editor.commit();
	}

	// 更新头像
	public void updateHeadPic(String headPic) {
		editor.putString("photo", headPic);
		editor.commit();
	}

	public String getUid() {
		return mSharedPreferences.getString("uid", "");
	}

	public String getMobileNumber() {
		return mSharedPreferences.getString("mobileNumber", null);
	}

	public String getUserName() {
		return mSharedPreferences.getString("userName", null);
	}

	public String getPassword() {
		return mSharedPreferences.getString("password", null);
	}

	public String getHeadPic() {
		return mSharedPreferences.getString("photo", null);
	}

	public String getStatus() {
		return mSharedPreferences.getString("status", null);
	}

	public String getReferrer() {
		return mSharedPreferences.getString("referrer", null);
	}
	
	public String getToken() {
		return mSharedPreferences.getString("token", null);
	}
    
	public Boolean isVip()
	{
		return mSharedPreferences.getBoolean("isVip", false);
	}
	public void clearData() {
		editor.clear();
		editor.commit();
	}
}
