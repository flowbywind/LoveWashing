package com.easylife.app_wash.view;

import java.util.HashMap;
import java.util.Map;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.easylife.app_wash.widget.ConnectionNetworkAbleUIBase;
import com.sihehui.section_network.http.info.ProcessException;
import com.sihehui.section_network.http.json.JSONClientAPI;
import com.sihehui.section_network.util.Util;
import com.sihehui.section_vo.vo.ListQuestionVO;

public class QuestionFragment extends QuestionCustomFragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = super.onCreateView(inflater, container, savedInstanceState);
		loadData();
		return view;
	}

	private void loadData() {
		Map<String, Object> input = new HashMap<String, Object>();
		input.put("page", 1);
		input.put("limit", 15);
		Map<String, String> params = new HashMap<String, String>();
		params.put("data", Util.map2Json(input));
		params.put("uid", mUser.getUid());
		// ((MainActivity) mActivity).
		params.put("token", mUser.getToken());
		// Log.d("shh", "downLoadData");
		if (mServerAPI == null) {
			mServerAPI = JSONClientAPI.getInstance(mActivity);
		}
		mServerAPI.question_list(params,
				new ConnectionNetworkAbleUIBase<ListQuestionVO>(mActivity) {

					@Override
					public void onSuccessed(ListQuestionVO result)
							throws ProcessException {
						// TODO Auto-generated method stub
						Log.d("shh", "result=" + result);
						if (result == null) {
							return;
						}
						isNeedRefresh = false;
						page = result.getNextPage() == null ? 1 : result
								.getNextPage();
						pullDownTemplateView.load(result.getDataList(),
								result.getHasNextPage());

					}
				});

	}

	int page;

	@Override
	public void onRefresh() {
		// TODO Auto-generated method stub
		Map<String, Object> input = new HashMap<String, Object>();
		input.put("page", 1);
		input.put("limit", 15);
		Map<String, String> params = new HashMap<String, String>();
		params.put("data", Util.map2Json(input));
		params.put("uid", mUser.getUid());
		params.put("token", mUser.getToken());
		// Log.d("shh", "downLoadData");
		if (mServerAPI == null) {
			mServerAPI = JSONClientAPI.getInstance(mActivity);
		}
		mServerAPI.question_list(params,
				new ConnectionNetworkAbleUIBase<ListQuestionVO>(mActivity) {

					@Override
					public void onSuccessed(ListQuestionVO result)
							throws ProcessException {
						// TODO Auto-generated method stub
						Log.d("shh", "result=" + result);
						if (result == null) {
							return;
						}
						isNeedRefresh = false;
						page = result.getNextPage() == null ? 1 : result
								.getNextPage();
						pullDownTemplateView.refresh(result.getDataList(),
								"暂无一问问题", result.getHasNextPage());

					}
				});
	}

	@Override
	public void onMore() {
		// TODO Auto-generated method stub
		Map<String, Object> input = new HashMap<String, Object>();
		input.put("page", page);
		input.put("limit", 15);
		Map<String, String> params = new HashMap<String, String>();
		params.put("data", Util.map2Json(input));
		params.put("uid", mUser.getUid());
		params.put("token", mUser.getToken());
		// Log.d("shh", "downLoadData");
		if (mServerAPI == null) {
			mServerAPI = JSONClientAPI.getInstance(mActivity);
		}
		mServerAPI.question_list(params,
				new ConnectionNetworkAbleUIBase<ListQuestionVO>(mActivity) {

					@Override
					public void onSuccessed(ListQuestionVO result)
							throws ProcessException {
						// TODO Auto-generated method stub
						Log.d("shh", "result=" + result);
						if (result == null) {
							return;
						}
						isNeedRefresh = false;
						page = result.getNextPage() == null ? 1 : result
								.getNextPage();
						pullDownTemplateView.more(result.getDataList(),
								result.getHasNextPage());

					}
				});
	}

}
