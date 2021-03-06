package com.easylife.app_wash.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.easylife.app_wash.R;
import com.easylife.app_wash.widget.BaseWashingFragment;
import com.easylife.app_wash.widget.CustomActivity;
import com.easylife.app_wash.widget.CustomOnClickListener;
import com.easylife.app_wash.widget.ImageCallBack;
import com.sihehui.section_network.http.info.ConnectionNetworkAbleBase;
import com.sihehui.section_network.http.info.ProcessException;
import com.sihehui.section_network.http.json.JSONClientCxdAPI;
import com.sihehui.section_network.util.Contacts;
import com.sihehui.section_vo.vo.WashingVO;

public class TabListFragment extends BaseWashingFragment {
	// ListView mListView;

	private String type = "01";

	List<WashingVO> dataList1 = new ArrayList<WashingVO>();
	List<WashingVO> dataList2 = new ArrayList<WashingVO>();
	List<WashingVO> dataList3 = new ArrayList<WashingVO>();
	List<WashingVO> dataList4 = new ArrayList<WashingVO>();
	List<WashingVO> dataList5 = new ArrayList<WashingVO>();
	private int page1, page2, page3, page4, page5;

	// 下载类型；
	public void downLoadData(CustomActivity activity, final String type) {
		// TODO Auto-generated method stub
		this.type = type;
		Log.d("shh", "type=" + type);
		// 需要判断，当有数据的时候就不需要重新加载
		if ("01".equals(type)) {
			if (dataList1.size() > 0) {
				return;
			}
		} else if ("02".equals(type)) {
			if (dataList2.size() > 0) {
				return;
			}
		} else if ("03".equals(type)) {
			if (dataList3.size() > 0) {
				return;
			}
		} else if ("04".equals(type)) {
			if (dataList4.size() > 0) {
				return;
			}
		} else if ("05".equals(type)) {
			if (dataList5.size() > 0) {
				return;
			}
		}
		Map<String, Object> input = new HashMap<String, Object>();
		// input.put("tagId", type);
		input.put("pageNumber", 1);
		input.put("pageSize", 15);
        input.put("tagId", 1);
		// Map<String, String> params = new HashMap<String, String>();
		// params.put("data", Util.map2Json(input));
		// params.put("uid", (activity).mUser.getUid());
		// params.put("token", (activity).mUser.getToken());
		// Log.d("shh", "downLoadData");
		if (mServerCxdAPI == null) {
			mServerCxdAPI = JSONClientCxdAPI.getInstance(mActivity);
		}
		mServerCxdAPI.getArrayData(input, Contacts.ServiceURL.QueryGoods,
				new ConnectionNetworkAbleBase<List<WashingVO>>(mActivity) {

					@Override
					public void onSuccessed(List<WashingVO> result)
							throws ProcessException {
						// TODO Auto-generated method stub
						if (result == null) {
							return;
						}
						isNeedRefresh = false;
						int page = 0;
						// int page = result.getNextPage() == null ? 1 : result
						// .getNextPage();
						if ("01".equals(type)) {
							dataList1.clear();
							dataList1 = result;
							page1 = page;
							Log.d("shh", "page1===" + page1);
							pullDownTemplateView.load(dataList1, true);
						} else if ("02".equals(type)) {
							dataList2.clear();
							dataList2 = result;
							pullDownTemplateView.load(dataList2, true);
						} else if ("03".equals(type)) {
							dataList3.clear();
							page3 = page;
							dataList3 = result;
							pullDownTemplateView.load(dataList3, true);
						} else if ("04".equals(type)) {
							dataList4.clear();
							page4 = page;
							dataList4 = result;
							pullDownTemplateView.load(dataList4, true);
						} else if ("05".equals(type)) {
							dataList5.clear();
							page5 = page;
							dataList5 = result;
							pullDownTemplateView.load(dataList5, true);
						}
					}

					@Override
					public void onConnectionTimeoutError() {
						// TODO Auto-generated method stub

					}

					@Override
					public void onNetworkError() {
						// TODO Auto-generated method stub

					}

					@Override
					public void onError(String code, String message) {
						// TODO Auto-generated method stub

					}
				}, WashingVO.class);

	}

	@Override
	public void onRefresh() {
		// TODO Auto-generated method stub
		Map<String, Object> input = new HashMap<String, Object>();
		input.put("articleType", type);
		input.put("pageNumber", 1);
		input.put("pageSize", 15);
		mServerCxdAPI.getArrayData(input, Contacts.ServiceURL.QueryGoods,
				new ConnectionNetworkAbleBase<List<WashingVO>>(mActivity) {

					@Override
					public void onSuccessed(List<WashingVO> result)
							throws ProcessException {
						// TODO Auto-generated method stub
						if (result == null) {
							return;
						}
						isNeedRefresh = false;
						int page = 0;
						if ("01".equals(type)) {
							page1 = page;
							dataList1.clear();
							dataList1 = result;
							pullDownTemplateView.refresh(dataList1, "暂无文章",
									true);
						} else if ("02".equals(type)) {
							page2 = page;
							dataList2.clear();
							dataList2 = result;
							pullDownTemplateView.refresh(dataList2, "暂无文章",
									true);
						} else if ("03".equals(type)) {
							page3 = page;
							dataList3.clear();
							dataList3 = result;
							pullDownTemplateView.refresh(dataList3, "暂无文章",
									true);
						} else if ("04".equals(type)) {
							page4 = page;
							dataList4.clear();
							dataList4 = result;
							pullDownTemplateView.refresh(dataList4, "暂无文章",
									true);
						} else if ("05".equals(type)) {
							page5 = page;
							dataList5.clear();
							dataList5 = result;
							pullDownTemplateView.refresh(dataList5, "暂无文章",
									true);
						}
					}

					@Override
					public void onConnectionTimeoutError() {
						// TODO Auto-generated method stub

					}

					@Override
					public void onNetworkError() {
						// TODO Auto-generated method stub

					}

					@Override
					public void onError(String code, String message) {
						// TODO Auto-generated method stub

					}
				}, WashingVO.class);

	}

	@Override
	public void onMore() {
		// TODO Auto-generated method stub
		Map<String, Object> input = new HashMap<String, Object>();
		input.put("articleType", type);
		if ("01".equals(type)) {
			input.put("pageNumber", page1);
			Log.d("shh", "page1=" + page1);
		} else if ("02".equals(type)) {
			input.put("pageNumber", page2);
		} else if ("03".equals(type)) {
			input.put("pageNumber", page3);
		} else if ("04".equals(type)) {
			input.put("pageNumber", page4);
		} else if ("05".equals(type)) {
			input.put("pageNumber", page5);
		}

		input.put("pageSize", 15);
		mServerCxdAPI.getArrayData(input, Contacts.ServiceURL.QueryGoods,
				new ConnectionNetworkAbleBase<List<WashingVO>>(mActivity) {

					@Override
					public void onSuccessed(List<WashingVO> result)
							throws ProcessException {
						// TODO Auto-generated method stub
						if (result == null) {
							return;
						}
						isNeedRefresh = false;
						// int page = result.getNextPage() == null ? 1 : result
						// .getNextPage();
						int page = 0;
						if ("01".equals(type)) {
							page1 = page;
							dataList1.clear();
							dataList1 = result;
							pullDownTemplateView.more(dataList1, true);
						} else if ("02".equals(type)) {
							page2 = page;
							dataList2.clear();
							dataList2 = result;
							pullDownTemplateView.more(dataList2, true);
						} else if ("03".equals(type)) {
							page3 = page;
							dataList3.clear();
							dataList3 = result;
							pullDownTemplateView.more(dataList3, true);
						} else if ("04".equals(type)) {
							page4 = page;
							dataList4.clear();
							dataList4 = result;
							pullDownTemplateView.more(dataList4, true);
						} else if ("05".equals(type)) {
							page5 = page;
							dataList5.clear();
							dataList5 = result;
							pullDownTemplateView.more(dataList5, true);
						}
					}

					@Override
					public void onConnectionTimeoutError() {
						// TODO Auto-generated method stub

					}

					@Override
					public void onNetworkError() {
						// TODO Auto-generated method stub

					}

					@Override
					public void onError(String code, String message) {
						// TODO Auto-generated method stub

					}
				}, WashingVO.class);

	}

	@Override
	public View setContentView(LayoutInflater inflater, ViewGroup container) {
		// TODO Auto-generated method stub
		return inflater.inflate(R.layout.tablist_fragment, container, false);
	}

	@Override
	public View onRefreshItemView(final int position, View view, ViewGroup viewGroup,
			List<WashingVO> dataList, final ListView listView) {
		// TODO Auto-generated method stub
		ViewHolder viewHolder = null;
		if (view == null) {
			view = LayoutInflater.from(mActivity).inflate(
					R.layout.list_item_home, null);
			viewHolder = new ViewHolder();
			viewHolder.image = (Button) view.findViewById(R.id.image);
			viewHolder.addcart = (Button) view.findViewById(R.id.addcart);
			viewHolder.name = (TextView) view.findViewById(R.id.name);
			viewHolder.discount = (TextView) view.findViewById(R.id.discount);
			viewHolder.money = (TextView) view.findViewById(R.id.money);
			viewHolder.pay_money = (TextView) view.findViewById(R.id.pay_money);
			view.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) view.getTag();
		}
		WashingVO vo = dataList.get(position);
		viewHolder.name.setText(vo.getName());
		viewHolder.discount.setText(vo.getDiscount());
		viewHolder.money.setText(vo.getPrice());
		viewHolder.pay_money.setText(vo.getSave_money());
		viewHolder.addcart.setOnClickListener(new CustomOnClickListener(
				mActivity) {

			@Override
			public void doOnClick(View v) {
				// TODO Auto-generated method stub

			}
		});
		if (vo.getGoods_pic() != null && !"".equals(vo.getGoods_pic())) {

			// String url = BaseClientAPI.getStandardURL(mActivity,
			// dataList.get(position).getGoodsPic());
			String url = getDownLoadUrl(vo.getGoods_pic());
			Log.d("shh", "position=" + position + " url=" + url);
			viewHolder.image.setTag(url);
			Drawable drawable = asyncDrawableLoader.loadDrawable(url,
					new ImageCallBack() {
						public void imageLoad(String strurl, Drawable drawable) {
							ImageView imageListView = (ImageView) listView
									.findViewWithTag(strurl);
							// Log.d("shh", "imageListView=" +
							// imageListView);
							Log.d("shh", "position=" + position + "drawable="
									+ drawable);
							if (imageListView != null) {
								if (drawable != null) {
									// System.out.println("imagecallBack");
									imageListView
											.setBackgroundDrawable(drawable);
								} else {
									imageListView
											.setBackgroundResource(R.drawable.default_download_img9);
								}
							}
						}

					});
			if (drawable == null) {
				viewHolder.image
						.setBackgroundResource(R.drawable.default_download_img9);
			} else {
				viewHolder.image.setBackgroundDrawable(drawable);
			}
		} else {
			viewHolder.image
					.setBackgroundResource(R.drawable.default_download_img9);
		}
		return view;
	}

}
