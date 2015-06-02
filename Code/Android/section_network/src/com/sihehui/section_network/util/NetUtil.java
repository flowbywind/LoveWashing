package com.sihehui.section_network.util;

import java.util.List;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;

public class NetUtil {

	public static void netSet(Context con) {
		if (android.os.Build.VERSION.SDK_INT > 10) {
			Intent intent = new Intent(
					android.provider.Settings.ACTION_SETTINGS);
			con.startActivity(intent);
		} else {
			Intent intent = new Intent("android.settings.WIRELESS_SETTINGS");
			con.startActivity(intent);
		}
	}

	// 判断当前activity最上层是否是网络设置界面
	public static boolean isNetSetOnForeground(Context con) {
		// SharedPreferences mSettingSharedPreferences =
		// con.getSharedPreferences(
		// "settingSharedPreferences", Activity.MODE_PRIVATE);
		// Editor editor = mSettingSharedPreferences.edit();
		// boolean isNetSetShowing = mSettingSharedPreferences.getBoolean(
		// "isNetSetShowing", false);
		// editor.putBoolean("isNetSetShowing", true);
		// editor.commit();
		// return isNetSetShowing;
		ActivityManager mActivityManager = (ActivityManager) con
				.getSystemService(Context.ACTIVITY_SERVICE);
		List<RunningTaskInfo> tasksInfo = mActivityManager.getRunningTasks(1);
		if (tasksInfo.size() > 0) {
			// 应用程序位于堆栈的顶层
			if ("com.iletou.caipiao.comm.activity.NetsetDialogActivity"
					.equals(tasksInfo.get(0).topActivity.getClassName())) {
				return true;
			}

		}
		return false;
	}
	// // 得到状态
	// public static int getRespStatus(final String url) {
	// Log.d("zhangyaobin", "getRespStatus url =" + url);
	// int status = -1;
	// if (url == null || "".equals(url)) {
	// return status;
	// }
	// // TODO Auto-generated method stub
	// HttpHead head = new HttpHead(url);
	// HttpClient client = new DefaultHttpClient();
	// HttpResponse resp = null;
	// try {
	// resp = client.execute(head);
	// } catch (ClientProtocolException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// } catch (IOException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// status = resp.getStatusLine().getStatusCode();
	// return status;
	// }
}