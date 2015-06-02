package com.easylife.app_wash.view;

import java.util.List;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.easylife.app_wash.R;
import com.easylife.app_wash.widget.BaseFragment;
import com.easylife.app_wash.widget.CustomActivity;
import com.easylife.app_wash.widget.ImageCallBack;
import com.easylife.app_wash.widget.PullDownTemplateView;
import com.easylife.app_wash.widget.PullDownTemplateView.OnPullDownTemplateViewListener;
import com.sihehui.section_network.http.info.BaseClientAPI;
import com.sihehui.section_vo.vo.LoanVO;
import com.sihehui.section_vo.vo.NormalDataVO;

public class TabListCustomFragment extends BaseFragment implements
		OnPullDownTemplateViewListener<LoanVO> {
	protected PullDownTemplateView<LoanVO> pullDownTemplateView;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub pulldownviewtemplate
		View view = super.onCreateView(inflater, container, savedInstanceState);

		pullDownTemplateView = (PullDownTemplateView<LoanVO>) view
				.findViewById(R.id.tab_list_pull_down);
		pullDownTemplateView.setPullDownTemplateViewListener(this);
		pullDownTemplateView.pullDownView.mListView.setDivider(null);
		pullDownTemplateView.pullDownView.mListView
				.setVerticalScrollBarEnabled(true);
		pullDownTemplateView.emptyView.setText(getTips());
		return view;
	}

	public void loadData(CustomActivity activity, String type) {
		// pullDownTemplateView.emptyView.setText(getTips());
	}

	public void refreshData() {
		if (isNeedRefresh) {
			onRefresh();
		}
	}

	protected String getTips() {
		return "暂无文章";
	}

	// 下载类型；
	public void downLoadData(CustomActivity activity, final String type) {
	}

	// 图片下载设置
	protected void setDownload(ViewHolder viewHolder,
			final List<NormalDataVO> dataList, final ListView listView,
			int position) {
		String url = dataList.get(position).getPicUrl();
		Log.d("shh", "position=" + position + " url=" + url);
		if (url != null && !"".equals(url)) {
			if (url.contains(",")) {
				viewHolder.layout_image.setVisibility(View.VISIBLE);
				viewHolder.pic0.setVisibility(View.GONE);
				viewHolder.content.setVisibility(View.GONE);
				String[] urls = url.split(",");
				if (urls.length == 1) {
					viewHolder.pic1.setVisibility(View.VISIBLE);
					viewHolder.pic2.setVisibility(View.INVISIBLE);
					viewHolder.pic3.setVisibility(View.INVISIBLE);
					viewHolder.pic1.setTag(BaseClientAPI.getStandardURL(
							mActivity, urls[0]));
					Drawable drawable = asyncDrawableLoader.loadDrawable(
							BaseClientAPI.getStandardURL(mActivity, urls[0]),
							new ImageCallBack() {
								public void imageLoad(String strurl,
										Drawable drawable) {
									ImageView imageListView = (ImageView) listView
											.findViewWithTag(strurl);
									// Log.d("shh", "imageListView="
									// + imageListView);
									// Log.d("shh", "drawable=" + drawable);
									if (imageListView != null
											&& drawable != null) {
										System.out.println("imagecallBack");
										imageListView
												.setBackgroundDrawable(drawable);
									}
								}
							});
					if (drawable == null) {
						viewHolder.pic1
								.setBackgroundResource(R.drawable.default_download_img9);
					} else {
						viewHolder.pic1.setBackgroundDrawable(drawable);
					}
				} else if (urls.length == 2) {
					viewHolder.pic1.setTag(BaseClientAPI.getStandardURL(
							mActivity, urls[0]));
					viewHolder.pic2.setTag(BaseClientAPI.getStandardURL(
							mActivity, urls[1]));
					viewHolder.pic1.setVisibility(View.VISIBLE);
					viewHolder.pic2.setVisibility(View.VISIBLE);
					viewHolder.pic3.setVisibility(View.INVISIBLE);
					Drawable drawable1 = asyncDrawableLoader.loadDrawable(
							BaseClientAPI.getStandardURL(mActivity, urls[0]),
							new ImageCallBack() {
								public void imageLoad(String strurl,
										Drawable drawable) {
									ImageView imageListView = (ImageView) listView
											.findViewWithTag(strurl);
									// Log.d("shh", "imageListView="
									// + imageListView);
									// Log.d("shh", "drawable=" + drawable);
									if (imageListView != null
											&& drawable != null) {
										System.out.println("imagecallBack");
										imageListView
												.setBackgroundDrawable(drawable);
									}
								}
							});
					if (drawable1 == null) {
						viewHolder.pic1
								.setBackgroundResource(R.drawable.default_download_img9);
					} else {
						viewHolder.pic1.setBackgroundDrawable(drawable1);
					}
					Drawable drawable2 = asyncDrawableLoader.loadDrawable(
							BaseClientAPI.getStandardURL(mActivity, urls[1]),
							new ImageCallBack() {
								public void imageLoad(String strurl,
										Drawable drawable) {
									ImageView imageListView = (ImageView) listView
											.findViewWithTag(strurl);
									Log.d("shh", "imageListView="
											+ imageListView);
									Log.d("shh", "drawable=" + drawable);
									if (imageListView != null
											&& drawable != null) {
										System.out.println("imagecallBack");
										imageListView
												.setBackgroundDrawable(drawable);
									}
								}
							});
					if (drawable2 == null) {
						viewHolder.pic2
								.setBackgroundResource(R.drawable.default_download_img9);
					} else {
						viewHolder.pic2.setBackgroundDrawable(drawable2);
					}

				} else if (urls.length == 3) {
					viewHolder.pic1.setVisibility(View.VISIBLE);
					viewHolder.pic2.setVisibility(View.VISIBLE);
					viewHolder.pic3.setVisibility(View.VISIBLE);
					viewHolder.pic1.setTag(BaseClientAPI.getStandardURL(
							mActivity, urls[0]));
					viewHolder.pic2.setTag(BaseClientAPI.getStandardURL(
							mActivity, urls[1]));
					viewHolder.pic3.setTag(BaseClientAPI.getStandardURL(
							mActivity, urls[2]));
					Drawable drawable1 = asyncDrawableLoader.loadDrawable(
							BaseClientAPI.getStandardURL(mActivity, urls[0]),
							new ImageCallBack() {
								public void imageLoad(String strurl,
										Drawable drawable) {
									ImageView imageListView = (ImageView) listView
											.findViewWithTag(strurl);
									Log.d("shh", "imageListView="
											+ imageListView);
									Log.d("shh", "drawable=" + drawable);
									if (imageListView != null
											&& drawable != null) {
										System.out.println("imagecallBack");
										imageListView
												.setBackgroundDrawable(drawable);
									}
								}
							});
					if (drawable1 == null) {
						viewHolder.pic1
								.setBackgroundResource(R.drawable.default_download_img9);
					} else {
						viewHolder.pic1.setBackgroundDrawable(drawable1);
					}

					Drawable drawable2 = asyncDrawableLoader.loadDrawable(
							BaseClientAPI.getStandardURL(mActivity, urls[1]),
							new ImageCallBack() {
								public void imageLoad(String strurl,
										Drawable drawable) {
									ImageView imageListView = (ImageView) listView
											.findViewWithTag(strurl);
									// Log.d("shh", "imageListView="
									// + imageListView);
									// Log.d("shh", "drawable=" + drawable);
									if (imageListView != null
											&& drawable != null) {
										// System.out.println("imagecallBack");
										imageListView
												.setBackgroundDrawable(drawable);
									}
								}
							});
					if (drawable2 == null) {
						viewHolder.pic2
								.setBackgroundResource(R.drawable.default_download_img9);
					} else {
						viewHolder.pic2.setBackgroundDrawable(drawable2);
					}
					Drawable drawable3 = asyncDrawableLoader.loadDrawable(
							BaseClientAPI.getStandardURL(mActivity, urls[2]),
							new ImageCallBack() {
								public void imageLoad(String strurl,
										Drawable drawable) {
									ImageView imageListView = (ImageView) listView
											.findViewWithTag(strurl);
									// Log.d("shh", "imageListView="
									// + imageListView);
									// Log.d("shh", "drawable=" + drawable);
									if (imageListView != null
											&& drawable != null) {
										System.out.println("imagecallBack");
										imageListView
												.setBackgroundDrawable(drawable);
									}
								}
							});
					if (drawable3 == null) {
						viewHolder.pic3
								.setBackgroundResource(R.drawable.default_download_img9);
					} else {
						viewHolder.pic3.setBackgroundDrawable(drawable3);
					}
				}

			} else {
				viewHolder.layout_image.setVisibility(View.GONE);
				viewHolder.pic0.setVisibility(View.VISIBLE);
				viewHolder.content.setVisibility(View.VISIBLE);
				viewHolder.content.setText(dataList.get(position).getSummary());
				viewHolder.pic0.setTag(BaseClientAPI.getStandardURL(mActivity,
						dataList.get(position).getPicUrl()));

				Drawable drawable = asyncDrawableLoader.loadDrawable(
						BaseClientAPI.getStandardURL(mActivity,
								dataList.get(position).getPicUrl()),
						new ImageCallBack() {
							public void imageLoad(String strurl,
									Drawable drawable) {
								ImageView imageListView = (ImageView) listView
										.findViewWithTag(strurl);
								// Log.d("shh", "imageListView=" +
								// imageListView);
								// Log.d("shh", "drawable=" + drawable);
								if (imageListView != null && drawable != null) {
									System.out.println("imagecallBack");
									imageListView
											.setBackgroundDrawable(drawable);
								}
							}
						});
				if (drawable == null) {
					viewHolder.pic0
							.setBackgroundResource(R.drawable.default_download_img9);
				} else {
					viewHolder.pic0.setBackgroundDrawable(drawable);
				}
			}
		} else {
			viewHolder.layout_image.setVisibility(View.GONE);
			viewHolder.pic0.setVisibility(View.GONE);
			viewHolder.content.setVisibility(View.VISIBLE);
			viewHolder.content.setText(dataList.get(position).getSummary());
		}
	}

	@Override
	public View onRefreshItemView(final int position, View view,
			ViewGroup viewGroup, final List<LoanVO> dataList,
			final ListView listView) {
		// TODO Auto-generated method stub

		return null;
	}

	public class ViewHolder {
		TextView title;
		TextView content, commentName, commentContent, commentTime;
		TextView message_count;
		TextView praise_count;
		ImageView pic0, pic1, pic2, pic3, commentPhoto;
		Button message, praise;
		LinearLayout layout_image;

		TextView name, qishu, type, status, money, month, date, shengyu,
				zhuanhuanlv, toubiaostatus, toubiaojindu;
		ProgressBar progressbar;
		LinearLayout layout_progressbar;
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id, List<LoanVO> dataList) {
		// TODO Auto-generated method stub
		isNeedRefresh = true;
	}

	@Override
	public void onRefresh() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onMore() {
		// TODO Auto-generated method stub

	}

	@Override
	public View setContentView(LayoutInflater inflater, ViewGroup container) {
		// TODO Auto-generated method stub
		return inflater.inflate(R.layout.tablist_fragment, container, false);
	}
}
