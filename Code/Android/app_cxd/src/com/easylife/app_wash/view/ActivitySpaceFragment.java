package com.easylife.app_wash.view;

import java.util.HashMap;
import java.util.Map;

import android.util.Log;

import com.easylife.app_wash.widget.ConnectionNetworkAbleUIBase;
import com.easylife.app_wash.widget.CustomActivity;
import com.sihehui.section_network.http.info.ProcessException;
import com.sihehui.section_network.http.json.JSONClientAPI;
import com.sihehui.section_network.util.Util;
import com.sihehui.section_vo.vo.ListActivityVO;

public class ActivitySpaceFragment extends ActivityCustomFragment {
	String type;

	@Override
	protected String getTips() {
		// TODO Auto-generated method stub
		if (isCollection()) {
			return "暂无收藏";
		} else if (isApply()) {
			return "暂无参与";
		}
		return super.getTips();
	}

	int page = 1;

	private boolean isCollection() {
		if ("mycollection".equals(type)) {
			return true;
		}
		return false;
	}

	private boolean isApply() {
		if ("myapply".equals(type)) {
			return true;
		}
		return false;
	}

	@Override
	public void loadData(CustomActivity activity, String type) {
		// TODO Auto-generated method stub
		// super.loadData(isAnonymous);
		Log.d("shh", "ActivitySpaceFragment loadData");
		this.type = type;
		// pullDownTemplateView.emptyView.setText(getTips());
		Map<String, Object> input = new HashMap<String, Object>();
		input.put("page", 1);
		input.put("limit", 15);
		Map<String, String> params = new HashMap<String, String>();
		params.put("data", Util.map2Json(input));
		params.put("uid", ((CustomActivity) activity).mUser.getUid());
		params.put("token", ((CustomActivity) activity).mUser.getToken());
		// Log.d("shh", "downLoadData");
		if (mServerAPI == null) {
			mServerAPI = JSONClientAPI.getInstance(mActivity);
		}
		if (isCollection()) {
			mServerAPI.my_activity_collection_list(params,
					new ConnectionNetworkAbleUIBase<ListActivityVO>(mActivity) {

						@Override
						public void onSuccessed(ListActivityVO result)
								throws ProcessException {
							// TODO Auto-generated method stub
							// Log.d("shh", "result=" + result);
							if (result == null) {
								return;
							}
							isNeedRefresh = false;
							// Log.d("shh",
							// "result.getNextPage()="
							// + result.getNextPage());
							page = result.getNextPage() == null ? 1 : result
									.getNextPage();
//							pullDownTemplateView.load(result.getDataList(),
//									result.getHasNextPage());

						}
					});
		} else if (isApply()) {
			mServerAPI.my_activity_apply_list(params,
					new ConnectionNetworkAbleUIBase<ListActivityVO>(mActivity) {

						@Override
						public void onSuccessed(ListActivityVO result)
								throws ProcessException {
							// TODO Auto-generated method stub
							// Log.d("shh", "result=" + result);
							if (result == null) {
								return;
							}
							isNeedRefresh = false;
							// Log.d("shh",
							// "result.getNextPage()="
							// + result.getNextPage());
							page = result.getNextPage() == null ? 1 : result
									.getNextPage();
							// Log.d("shh", "pullDownTemplateView="
							// + pullDownTemplateView);
							// Log.d("shh",
							// "result.getDataList()="
							// + result.getDataList());
//							pullDownTemplateView.load(result.getDataList(),
//									result.getHasNextPage());

						}
					});
		}
	}

	@Override
	public void onRefresh() {
		// TODO Auto-generated method stub
		super.onRefresh();
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
		if (isCollection()) {
			mServerAPI.my_activity_collection_list(params,
					new ConnectionNetworkAbleUIBase<ListActivityVO>(mActivity) {

						@Override
						public void onSuccessed(ListActivityVO result)
								throws ProcessException {
							// TODO Auto-generated method stub
							Log.d("shh", "result=" + result);
							if (result == null) {
								return;
							}
							isNeedRefresh = false;
							page = result.getNextPage() == null ? 1 : result
									.getNextPage();
//							pullDownTemplateView.refresh(result.getDataList(),
//									getTips(), result.getHasNextPage());

						}
					});
		} else if (isApply()) {
			mServerAPI.my_activity_apply_list(params,
					new ConnectionNetworkAbleUIBase<ListActivityVO>(mActivity) {

						@Override
						public void onSuccessed(ListActivityVO result)
								throws ProcessException {
							// TODO Auto-generated method stub
							Log.d("shh", "result=" + result);
							if (result == null) {
								return;
							}
							isNeedRefresh = false;
							page = result.getNextPage() == null ? 1 : result
									.getNextPage();
							Log.d("shh", "pullDownTemplateView="
									+ pullDownTemplateView);
							Log.d("shh",
									"result.getDataList()="
											+ result.getDataList());
//							pullDownTemplateView.refresh(result.getDataList(),
//									getTips(), result.getHasNextPage());

						}
					});
		}
	}

	@Override
	public void onMore() {
		// TODO Auto-generated method stub
		super.onMore();
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
		if (isCollection()) {
			mServerAPI.my_activity_collection_list(params,
					new ConnectionNetworkAbleUIBase<ListActivityVO>(mActivity) {

						@Override
						public void onSuccessed(ListActivityVO result)
								throws ProcessException {
							// TODO Auto-generated method stub
							Log.d("shh", "result=" + result);
							if (result == null) {
								return;
							}
							isNeedRefresh = false;
							page = result.getNextPage() == null ? 1 : result
									.getNextPage();
//							pullDownTemplateView.more(result.getDataList(),
//									result.getHasNextPage());

						}
					});
		} else if (isApply()) {
			mServerAPI.my_activity_apply_list(params,
					new ConnectionNetworkAbleUIBase<ListActivityVO>(mActivity) {

						@Override
						public void onSuccessed(ListActivityVO result)
								throws ProcessException {
							// TODO Auto-generated method stub
							Log.d("shh", "result=" + result);
							if (result == null) {
								return;
							}
							isNeedRefresh = false;
							page = result.getNextPage() == null ? 1 : result
									.getNextPage();
							Log.d("shh", "pullDownTemplateView="
									+ pullDownTemplateView);
							Log.d("shh",
									"result.getDataList()="
											+ result.getDataList());
//							pullDownTemplateView.more(result.getDataList(),
//									result.getHasNextPage());

						}
					});
		}
	}

}
