package com.sihehui.section_network.util;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;

public class StoreActivity {
	static StoreActivity storeActivity;

	private List<Activity> list = new ArrayList<Activity>();

	public static StoreActivity getInstance() {
		if (storeActivity == null) {
			storeActivity = new StoreActivity();
		}
		return storeActivity;
	}

	public void addActivity(Activity activity) {
		list.add(activity);
	}

	public void exit() {
		for (int i = 0; i < list.size(); i++) {
			list.get(i).finish(); // 销毁activity

		}

		System.exit(0);
	}
}
