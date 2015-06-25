package com.easylife.app_wash.view;

import java.util.ArrayList;
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
import com.sihehui.section_network.util.Util;
import com.sihehui.section_vo.vo.ListSquareVO;
import com.sihehui.section_vo.vo.SquareVO;

public class SquareFragment extends SquareCustomFragment {

	List<SquareVO> realNameList = new ArrayList<SquareVO>();
	List<SquareVO> anonymousNameList = new ArrayList<SquareVO>();

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = super.onCreateView(inflater, container, savedInstanceState);
		// 默认实名广场发言
		loadData("0");
		return view;
	}

	private boolean isAnonymous() {
		if ("1".equals(isAnonymous)) {
			return true;
		}
		return false;
	}

	public void loadData(String isAnonymous) {
		this.isAnonymous = isAnonymous;
		Log.d("shh", "anonymousNameList.size()=" + anonymousNameList.size());
		Log.d("shh", "realNameList.size()=" + realNameList.size());
		if (isAnonymous()) {
			if (anonymousNameList.size() > 0) {
				pullDownTemplateView.dataTabChanged(anonymousNameList);
				return;
			}
		} else {
			if (realNameList.size() > 0) {
				pullDownTemplateView.dataTabChanged(realNameList);
				return;
			}
		}
		Map<String, Object> input = new HashMap<String, Object>();
		input.put("isAnonymous", isAnonymous);
		input.put("pageNo", 1);
		input.put("pageSize", 15);
		Map<String, String> params = new HashMap<String, String>();
		params.put("data", Util.map2Json(input));
		params.put("uid", mUser.getUid());
		params.put("token", mUser.getToken());
		// Log.d("shh", "downLoadData");
		if (mServerAPI == null) {
			mServerAPI = JSONClientAPI.getInstance(mActivity);
		}
		mServerAPI.square_list(params,
				new ConnectionNetworkAbleUIBase<ListSquareVO>(mActivity) {

					@Override
					public void onSuccessed(ListSquareVO result)
							throws ProcessException {
						// TODO Auto-generated method stub
						Log.d("shh", "result=" + result);
						if (result == null) {
							return;
						}
						isNeedRefresh = false;
						if (isAnonymous()) {
							anonymousNameList.clear();
							anonymousNameList = result.getDataList();
						} else {
							realNameList.clear();
							realNameList = result.getDataList();
						}
						dataList.clear();
						dataList = result.getDataList();
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
		input.put("isAnonymous", isAnonymous);
		input.put("pageNo", 1);
		input.put("pageSize", 15);
		Map<String, String> params = new HashMap<String, String>();
		params.put("data", Util.map2Json(input));
		params.put("uid", mUser.getUid());
		params.put("token", mUser.getToken());
		// Log.d("shh", "downLoadData");
		if (mServerAPI == null) {
			mServerAPI = JSONClientAPI.getInstance(mActivity);
		}
		mServerAPI.square_list(params,
				new ConnectionNetworkAbleUIBase<ListSquareVO>(mActivity) {

					@Override
					public void onSuccessed(ListSquareVO result)
							throws ProcessException {
						// TODO Auto-generated method stub
						Log.d("shh", "result=" + result);
						if (result == null) {
							return;
						}
						isNeedRefresh = false;
						if (isAnonymous()) {
							anonymousNameList.clear();
							anonymousNameList = result.getDataList();
						} else {
							realNameList.clear();
							realNameList = result.getDataList();
						}
						dataList.clear();
						dataList = result.getDataList();
						page = result.getNextPage() == null ? 1 : result
								.getNextPage();
						pullDownTemplateView.refresh(result.getDataList(),
								"暂无广场发言", result.getHasNextPage());

					}
				});
	}

	@Override
	public void onMore() {
		// TODO Auto-generated method stub
		Map<String, Object> input = new HashMap<String, Object>();
		input.put("isAnonymous", isAnonymous);
		input.put("pageNo", page);
		input.put("pageSize", 15);
		Map<String, String> params = new HashMap<String, String>();
		params.put("data", Util.map2Json(input));
		params.put("uid", mUser.getUid());
		params.put("token", mUser.getToken());
		// Log.d("shh", "downLoadData");
		if (mServerAPI == null) {
			mServerAPI = JSONClientAPI.getInstance(mActivity);
		}
		mServerAPI.square_list(params,
				new ConnectionNetworkAbleUIBase<ListSquareVO>(mActivity) {

					@Override
					public void onSuccessed(ListSquareVO result)
							throws ProcessException {
						// TODO Auto-generated method stub
						Log.d("shh", "result=" + result);
						if (result == null) {
							return;
						}
						isNeedRefresh = false;
						if (isAnonymous()) {
							// anonymousNameList.clear();
							anonymousNameList.addAll(result.getDataList());
						} else {
							// realNameList.clear();
							realNameList.addAll(result.getDataList());
							// realNameList = result.getDataList();
						}
						dataList.clear();
						dataList = result.getDataList();
						page = result.getNextPage() == null ? 1 : result
								.getNextPage();
						pullDownTemplateView.more(result.getDataList(),
								result.getHasNextPage());

					}
				});
	}
}
