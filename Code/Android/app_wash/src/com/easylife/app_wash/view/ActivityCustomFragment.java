package com.easylife.app_wash.view;

import java.util.List;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.easylife.app_wash.R;
import com.easylife.app_wash.widget.BaseFragment;
import com.easylife.app_wash.widget.CustomActivity;
import com.easylife.app_wash.widget.ImageCallBack;
import com.easylife.app_wash.widget.PullDownTemplateView;
import com.easylife.app_wash.widget.PullDownTemplateView.OnPullDownTemplateViewListener;
import com.sihehui.section_vo.vo.BannerVO;

public class ActivityCustomFragment extends BaseFragment implements
		OnPullDownTemplateViewListener<BannerVO> {
	// TextView mTitle;
	protected PullDownTemplateView<BannerVO> pullDownTemplateView;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = super.onCreateView(inflater, container, savedInstanceState);
		pullDownTemplateView = (PullDownTemplateView<BannerVO>) view
				.findViewById(R.id.tab_list_pull_down);
		pullDownTemplateView.setPullDownTemplateViewListener(this);
		pullDownTemplateView.pullDownView.mListView.setDivider(null);
		pullDownTemplateView.pullDownView.mListView
				.setVerticalScrollBarEnabled(true);
		pullDownTemplateView.emptyView.setText(getTips());
		return view;
	}

	protected String getTips() {
		return "暂无活动";
	}

	public void loadData(CustomActivity activity, String type) {

	}

	// public void loadData(String type) {
	// }
	public void refreshData() {
		if (isNeedRefresh) {
			onRefresh();
		}
	}

	int page;

	@Override
	public View setContentView(LayoutInflater inflater, ViewGroup container) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragment_activity, container,
				false);
		return view;
	}

	@Override
	public void onRefresh() {
		// TODO Auto-generated method stub
	}

	@Override
	public void onMore() {
	}

	private String getDownLoadUrl(String url) {
		String downloadUrl = "";
		downloadUrl = "http://123.57.51.100:8088/cxd" + url;
		Log.d("shh", "downloadUrl=" + downloadUrl);
		// downloadUrl =
		// "http://d.hiphotos.baidu.com/image/pic/item/6d81800a19d8bc3eca7f03cb808ba61ea8d345bf.jpg";
		return downloadUrl;
	}

	@Override
	public View onRefreshItemView(final int position, View view,
			ViewGroup viewGroup, List<BannerVO> dataList,
			final ListView listView) {
		// TODO Auto-generated method stub
		ViewHolder viewHolder;
		if (view == null) {
			view = getActivity().getLayoutInflater().inflate(
					R.layout.list_item_activity_normal, null);
			viewHolder = new ViewHolder();
			// viewHolder.ad_date = (TextView) view.findViewById(R.id.ad_date);
			// viewHolder.type = (TextView) view.findViewById(R.id.type);
			viewHolder.ad_image = (ImageView) view.findViewById(R.id.ad_image);
			view.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) view.getTag();
		}
		// Log.d("shh",
		// "dataList.size=" + dataList.size() + "  postion=" + position
		// + "  dataList.get(position)="
		// + dataList.get(position).getBeginTime());
		BannerVO vo = dataList.get(position);
		if (vo.getLocation() != null && !"".equals(vo.getLocation())) {

			// String url = BaseClientAPI.getStandardURL(mActivity,
			// dataList.get(position).getLocation());
			String url = getDownLoadUrl(vo.getLocation());
			Log.d("shh", "position=" + position + " url=" + url);
			viewHolder.ad_image.setTag(url);
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
				viewHolder.ad_image
						.setBackgroundResource(R.drawable.default_download_img9);
			} else {
				viewHolder.ad_image.setBackgroundDrawable(drawable);
			}
		} else {
			viewHolder.ad_image
					.setBackgroundResource(R.drawable.default_download_img9);
		}
		return view;
	}

	class ViewHolder {
		TextView ad_date, type;
		ImageView ad_image;
	}

	private String getDetailUrl(String url) {
		String downloadUrl = "";
		downloadUrl = "http://123.57.51.100:8088/cxd" + url;
		Log.d("shh", "downloadUrl=" + downloadUrl);
		// downloadUrl =
		// "http://d.hiphotos.baidu.com/image/pic/item/6d81800a19d8bc3eca7f03cb808ba61ea8d345bf.jpg";
		return downloadUrl;
	}
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id, List<BannerVO> dataList) {
		// TODO Auto-generated method stub
		// isNeedRefresh = true;
		Intent intent = new Intent(mActivity, CommonWebViewActivity.class);
		intent.putExtra("title", "活动详情");
		intent.putExtra("url", getDetailUrl(dataList.get(position).getUrl()));
		startActivity(intent);
	}
}
