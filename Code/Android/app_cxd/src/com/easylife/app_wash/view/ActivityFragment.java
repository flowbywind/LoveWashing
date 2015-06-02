package com.easylife.app_wash.view;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.easylife.app_wash.widget.ConnectionNetworkAbleUIBase;
import com.sihehui.section_network.http.info.ProcessException;
import com.sihehui.section_network.http.json.JSONClientAPI;
import com.sihehui.section_network.util.Contacts;
import com.sihehui.section_network.util.Util;
import com.sihehui.section_vo.enums.LoadDataType;
import com.sihehui.section_vo.vo.BannerVO;
import com.sihehui.section_vo.vo.ListActivityVO;

public class ActivityFragment extends ActivityCustomFragment {
	// TextView mTitle;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = super.onCreateView(inflater, container, savedInstanceState);
		loadData(LoadDataType.Load);
		return view;
	}

	private void loadData(final LoadDataType type) {

		mServerCxdAPI.getArrayData(null,
				Contacts.ServiceURL.ActivityAppService,
				new ConnectionNetworkAbleUIBase<List<BannerVO>>(mActivity) {

					@Override
					public void onSuccessed(List<BannerVO> result)
							throws ProcessException {
						// TODO Auto-generated method stub
						if (type == LoadDataType.Refresh) {
							page = 1;
							pullDownTemplateView.refresh(result, "暂无活动", true);
						} else if (type == LoadDataType.More) {
							pullDownTemplateView.more(result, true);
							page++;
						} else {
							page = 1;
							pullDownTemplateView.load(result, true);
						}
					}
				}, BannerVO.class);

		// Map<String, Object> input = new HashMap<String, Object>();
		// input.put("page", 1);
		// input.put("limit", 15);
		// Map<String, String> params = new HashMap<String, String>();
		// params.put("data", Util.map2Json(input));
		// params.put("uid", mUser.getUid());
		// params.put("token", mUser.getToken());
		// // Log.d("shh", "downLoadData");
		// if (mServerAPI == null) {
		// mServerAPI = JSONClientAPI.getInstance(mActivity);
		// }

		// mServerAPI.activity_list(params,
		// new ConnectionNetworkAbleUIBase<ListActivityVO>(mActivity) {
		//
		// @Override
		// public void onSuccessed(ListActivityVO result)
		// throws ProcessException {
		// // TODO Auto-generated method stub
		// Log.d("shh", "result=" + result);
		// if (result == null) {
		// return;
		// }
		// isNeedRefresh = false;
		// page = result.getNextPage() == null ? 1 : result
		// .getNextPage();
		// pullDownTemplateView.load(result.getDataList(),
		// result.getHasNextPage());
		// }
		// });
	}

	@Override
	public void onRefresh() {
		// TODO Auto-generated method stub
		loadData(LoadDataType.Refresh);
	}

	@Override
	public void onMore() {
		// TODO Auto-generated method stub
		loadData(LoadDataType.More);
	}
}
