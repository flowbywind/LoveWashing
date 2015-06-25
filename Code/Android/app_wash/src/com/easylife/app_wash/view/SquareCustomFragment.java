package com.easylife.app_wash.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.easylife.app_wash.R;
import com.easylife.app_wash.widget.BaseFragment;
import com.easylife.app_wash.widget.ConnectionNetworkAbleUIBase;
import com.easylife.app_wash.widget.CustomActivity;
import com.easylife.app_wash.widget.CustomOnClickListener;
import com.easylife.app_wash.widget.IdentityDialog;
import com.easylife.app_wash.widget.ImageCallBack;
import com.easylife.app_wash.widget.PullDownTemplateView;
import com.easylife.app_wash.widget.BaseFragment.CommentDialog;
import com.easylife.app_wash.widget.PullDownTemplateView.OnPullDownTemplateViewListener;
import com.sihehui.section_network.http.info.BaseClientAPI;
import com.sihehui.section_network.http.info.ProcessException;
import com.sihehui.section_network.http.json.JSONClientAPI;
import com.sihehui.section_network.util.Util;
import com.sihehui.section_vo.vo.DefaultVO;
import com.sihehui.section_vo.vo.SquareVO;

public class SquareCustomFragment extends BaseFragment implements
		OnPullDownTemplateViewListener<SquareVO> {
	public void loadData(CustomActivity activity, String type) {
		// if (pullDownTemplateView != null
		// && pullDownTemplateView.emptyView != null) {
		// pullDownTemplateView.emptyView.setText(getTips());
		// }
	}

	protected List<SquareVO> dataList = new ArrayList<SquareVO>();
	// isAnonymous "0"表示实名，"1"表示匿名
	protected String isAnonymous = "0";

	public void refreshData() {
		Log.d("shh", "isNeedRefresh=" + isNeedRefresh);
		if (isNeedRefresh) {
			onRefresh();
		}
	}

	@Override
	protected void onSucessComment(final Dialog dialog, View v, String content,
			String itemid, String speakUserId, final int position) {
		// TODO Auto-generated method stub
		Map<String, Object> input = new HashMap<String, Object>();
		input.put("speakId", itemid);
		input.put("content", content);
		input.put("speakUserId", speakUserId);

		Map<String, String> params = new HashMap<String, String>();
		params.put("uid", mUser.getUid());
		params.put("token", mUser.getToken());
		params.put("data", Util.map2Json(input));
		progress.startProgress();
		mServerAPI
				.square_comment(params,
						new ConnectionNetworkAbleUIBase<DefaultVO>(mActivity,
								progress) {

							@Override
							public void onSuccessed(DefaultVO result)
									throws ProcessException {
								// TODO Auto-generated method stub
								Log.d("shh", "article_comment result=" + result);
								progress.stopProgress();
								if (result == null) {
									dialog.cancel();
									return;
								}
								// hasPraise = true;
								if (result.getRetCode() == 1) {
									Toast.makeText(mActivity, "评论成功",
											Toast.LENGTH_SHORT).show();
									startActivity(dataList, position);
								}
							}
						});
		dialog.cancel();
	}

	protected PullDownTemplateView<SquareVO> pullDownTemplateView;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = super.onCreateView(inflater, container, savedInstanceState);
		pullDownTemplateView = (PullDownTemplateView<SquareVO>) view
				.findViewById(R.id.tab_list_pull_down);
		pullDownTemplateView.setPullDownTemplateViewListener(this);
		pullDownTemplateView.pullDownView.mListView.setDivider(null);
		pullDownTemplateView.pullDownView.mListView
				.setVerticalScrollBarEnabled(true);
		pullDownTemplateView.emptyView.setText(getTips());
		return view;
	}

	protected String getTips() {
		return "暂无广场发言";
	}

	int page;

	@Override
	public void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
	}

	@Override
	public View setContentView(LayoutInflater inflater, ViewGroup container) {
		// TODO Auto-generated method stub
		View view = inflater
				.inflate(R.layout.fragment_square, container, false);
		return view;
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
	public View onRefreshItemView(final int position, View view,
			ViewGroup viewGroup, final List<SquareVO> dataList,
			final ListView listView) {
		// TODO Auto-generated method stub
		final ViewHolder viewHolder;
		if (view == null) {
			view = getActivity().getLayoutInflater().inflate(
					R.layout.list_item_square_normal, null);
			viewHolder = new ViewHolder();
			viewHolder.date = (TextView) view.findViewById(R.id.date);
			viewHolder.content = (TextView) view
					.findViewById(R.id.list_item_content);
			viewHolder.nickName = (TextView) view.findViewById(R.id.nickname);
			viewHolder.jobName = (TextView) view.findViewById(R.id.name);
			viewHolder.forward_user = (TextView) view
					.findViewById(R.id.forward_user);
			viewHolder.praise = (Button) view.findViewById(R.id.praise);
			viewHolder.image = (ImageButton) view.findViewById(R.id.image);
			viewHolder.comment = (Button) view.findViewById(R.id.message);
			viewHolder.forward = (Button) view.findViewById(R.id.forward);
			viewHolder.pic0 = (Button) view.findViewById(R.id.pic0);
			viewHolder.pic1 = (Button) view.findViewById(R.id.pic1);
			viewHolder.pic2 = (Button) view.findViewById(R.id.pic2);
			viewHolder.layout_pic = (LinearLayout) view
					.findViewById(R.id.layout_pic);
			viewHolder.layout_image = (RelativeLayout) view
					.findViewById(R.id.layout_image);
			viewHolder.cityName = (TextView) view.findViewById(R.id.cityname);
			viewHolder.userType = (TextView) view.findViewById(R.id.usertype);
			viewHolder.vip = (ImageView) view.findViewById(R.id.vip);
			view.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) view.getTag();
		}

		viewHolder.nickName.setText(dataList.get(position).getUsername());
		if (dataList.get(position).getContent() != null) {
			viewHolder.content.setVisibility(View.VISIBLE);
			viewHolder.content.setText(dataList.get(position).getContent());
		} else {
			viewHolder.content.setVisibility(View.GONE);
		}

		// if (dataList.get(position).getStatus()) {
		// viewHolder.vip.setVisibility(View.VISIBLE);
		// } else {
		// viewHolder.vip.setVisibility(View.GONE);
		// }

		if (dataList.get(position).getHeadPic() != null) {

			String url = BaseClientAPI.getStandardURL(mActivity,
					dataList.get(position).getHeadPic());
			Log.d("shh", "url=" + url);
			viewHolder.image.setTag(url);
			Drawable drawable = asyncDrawableLoader.loadDrawable(url,
					new ImageCallBack() {
						public void imageLoad(String strurl, Drawable drawable) {
							ImageButton imageListView = (ImageButton) listView
									.findViewWithTag(strurl);
							// Log.d("shh", "imageListView=" + imageListView);
							// Log.d("shh", "drawable=" + drawable);
							if (imageListView != null && drawable != null) {
								// System.out.println("imagecallBack");
								imageListView.setBackgroundDrawable(drawable);
							}
						}
					});
			if (drawable == null) {
				viewHolder.image.setBackgroundResource(R.drawable.default_img);
			} else {
				// toRoundBitmap(drawable)
				viewHolder.image.setBackgroundDrawable(drawable);
			}
		} else {
			viewHolder.image.setBackgroundResource(R.drawable.default_img);
		}
		if (dataList.get(position).getCityName() != null) {
			viewHolder.cityName.setText(dataList.get(position).getCityName());
		} else {
			viewHolder.cityName.setText("");
		}

		if (dataList.get(position).getUsertypeName() != null) {
			if (dataList.get(position).getBrandName() != null) {
				// 代理品牌
				viewHolder.jobName.setText(dataList.get(position)
						.getBrandName());
				viewHolder.userType.setText(dataList.get(position)
						.getUsertypeName());
			} else if (dataList.get(position).getMallName() != null) {
				// 卖场
				viewHolder.jobName
						.setText(dataList.get(position).getMallName());
				viewHolder.userType.setText(dataList.get(position)
						.getUsertypeName());
			} else {
				viewHolder.jobName.setText("");
				viewHolder.userType.setText(dataList.get(position)
						.getUsertypeName());
			}

		} else {
			viewHolder.jobName.setText("");
			viewHolder.userType.setText("");
		}

		// viewHolder.praise.setText(dataList.get(position).getPraiseNum() +
		// "");
		// viewHolder.comment.setText(dataList.get(position).getCommentNum() +
		// "");

		String url = dataList.get(position).getPicUrl();
		if (url != null && !"".equals(url)) {
			viewHolder.layout_pic.setVisibility(View.VISIBLE);
			String[] urls = url.split(",");
			if (urls.length == 1) {
				viewHolder.pic0.setVisibility(View.VISIBLE);
				viewHolder.pic1.setVisibility(View.INVISIBLE);
				viewHolder.pic2.setVisibility(View.INVISIBLE);
				viewHolder.pic0.setTag(BaseClientAPI.getStandardURL(mActivity,
						urls[0]));
				Drawable drawable = asyncDrawableLoader.loadDrawable(
						BaseClientAPI.getStandardURL(mActivity, urls[0]),
						new ImageCallBack() {
							public void imageLoad(String strurl,
									Drawable drawable) {
								Button imageListView = (Button) listView
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
			} else if (urls.length == 2) {
				viewHolder.pic0.setTag(BaseClientAPI.getStandardURL(mActivity,
						urls[0]));
				viewHolder.pic1.setTag(BaseClientAPI.getStandardURL(mActivity,
						urls[1]));
				viewHolder.pic0.setVisibility(View.VISIBLE);
				viewHolder.pic1.setVisibility(View.VISIBLE);
				viewHolder.pic2.setVisibility(View.INVISIBLE);
				Drawable drawable1 = asyncDrawableLoader.loadDrawable(
						BaseClientAPI.getStandardURL(mActivity, urls[0]),
						new ImageCallBack() {
							public void imageLoad(String strurl,
									Drawable drawable) {
								Button imageListView = (Button) listView
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
				if (drawable1 == null) {
					viewHolder.pic0
							.setBackgroundResource(R.drawable.default_download_img9);
				} else {
					viewHolder.pic0.setBackgroundDrawable(drawable1);
				}
				Drawable drawable2 = asyncDrawableLoader.loadDrawable(
						BaseClientAPI.getStandardURL(mActivity, urls[1]),
						new ImageCallBack() {
							public void imageLoad(String strurl,
									Drawable drawable) {
								Button imageListView = (Button) listView
										.findViewWithTag(strurl);
								if (imageListView != null && drawable != null) {
									System.out.println("imagecallBack");
									imageListView
											.setBackgroundDrawable(drawable);
								}
							}
						});
				if (drawable2 == null) {
					viewHolder.pic1
							.setBackgroundResource(R.drawable.default_download_img9);
				} else {
					viewHolder.pic1.setBackgroundDrawable(drawable2);
				}

			} else if (urls.length == 3) {
				viewHolder.pic0.setVisibility(View.VISIBLE);
				viewHolder.pic1.setVisibility(View.VISIBLE);
				viewHolder.pic2.setVisibility(View.VISIBLE);
				viewHolder.pic0.setTag(BaseClientAPI.getStandardURL(mActivity,
						urls[0]));
				viewHolder.pic1.setTag(BaseClientAPI.getStandardURL(mActivity,
						urls[1]));
				viewHolder.pic2.setTag(BaseClientAPI.getStandardURL(mActivity,
						urls[2]));
				Drawable drawable1 = asyncDrawableLoader.loadDrawable(
						BaseClientAPI.getStandardURL(mActivity, urls[0]),
						new ImageCallBack() {
							public void imageLoad(String strurl,
									Drawable drawable) {
								Button imageListView = (Button) listView
										.findViewWithTag(strurl);
								if (imageListView != null && drawable != null) {
									imageListView
											.setBackgroundDrawable(drawable);
								}
							}
						});
				if (drawable1 == null) {
					viewHolder.pic0
							.setBackgroundResource(R.drawable.default_download_img9);
				} else {
					viewHolder.pic0.setBackgroundDrawable(drawable1);
				}

				Drawable drawable2 = asyncDrawableLoader.loadDrawable(
						BaseClientAPI.getStandardURL(mActivity, urls[1]),
						new ImageCallBack() {
							public void imageLoad(String strurl,
									Drawable drawable) {
								Button imageListView = (Button) listView
										.findViewWithTag(strurl);
								if (imageListView != null && drawable != null) {
									imageListView
											.setBackgroundDrawable(drawable);
								}
							}
						});
				if (drawable2 == null) {
					viewHolder.pic1
							.setBackgroundResource(R.drawable.default_download_img9);
				} else {
					viewHolder.pic1.setBackgroundDrawable(drawable2);
				}
				Drawable drawable3 = asyncDrawableLoader.loadDrawable(
						BaseClientAPI.getStandardURL(mActivity, urls[2]),
						new ImageCallBack() {
							public void imageLoad(String strurl,
									Drawable drawable) {
								Button imageListView = (Button) listView
										.findViewWithTag(strurl);
								if (imageListView != null && drawable != null) {
									imageListView
											.setBackgroundDrawable(drawable);
								}
							}
						});
				if (drawable3 == null) {
					viewHolder.pic2
							.setBackgroundResource(R.drawable.default_download_img9);
				} else {
					viewHolder.pic2.setBackgroundDrawable(drawable3);
				}
			}

		} else {
			viewHolder.layout_pic.setVisibility(View.GONE);
		}
		if ("1".equals(isAnonymous)) {
			if (viewHolder.layout_image.getVisibility() == View.VISIBLE) {
				viewHolder.layout_image.setVisibility(View.GONE);
			}
			if (viewHolder.forward_user.getVisibility() == View.VISIBLE) {
				viewHolder.forward_user.setVisibility(View.GONE);
			}
			if (viewHolder.date.getVisibility() == View.VISIBLE) {
				viewHolder.date.setVisibility(View.GONE);
			}
		} else {
			if (viewHolder.layout_image.getVisibility() == View.GONE) {
				viewHolder.layout_image.setVisibility(View.VISIBLE);
			}
			viewHolder.date.setText(Util.getShowTime(dataList.get(position)
					.getCreateTime()));

			if (dataList.get(position).getForwardUserName() != null) {
				viewHolder.forward_user.setText("转自于 "
						+ dataList.get(position).getForwardUserName());
				if (viewHolder.forward_user.getVisibility() == View.GONE) {
					viewHolder.forward_user.setVisibility(View.VISIBLE);
				}
			} else {
				if (viewHolder.forward_user.getVisibility() == View.VISIBLE) {
					viewHolder.forward_user.setVisibility(View.GONE);
				}
			}

			if (viewHolder.date.getVisibility() == View.GONE) {
				viewHolder.date.setVisibility(View.VISIBLE);
			}
		}

		viewHolder.praise.setOnClickListener(new CustomOnClickListener(
				mActivity) {

			@Override
			public void doOnClick(View v) {
				// TODO Auto-generated method stub
				if (dataList.get(position).getHasPraise()) {
					Toast.makeText(mActivity, "您已点赞", Toast.LENGTH_SHORT)
							.show();
				} else {
					Map<String, String> input = new HashMap<String, String>();
					input.put("uid", mUser.getUid());
					input.put("token", mUser.getToken());
					input.put("speakId", dataList.get(position).getSpeakId());
					mServerAPI.square_praise(input,
							new ConnectionNetworkAbleUIBase<DefaultVO>(
									mActivity) {

								@Override
								public void onSuccessed(DefaultVO result)
										throws ProcessException {
									// TODO Auto-generated method stub
									if (result == null) {
										return;
									}
									if (result.getRetCode() == 1) {
										dataList.get(position).setHasPraise(
												true);
										Toast.makeText(mActivity, "点赞成功",
												Toast.LENGTH_SHORT).show();
										// viewHolder.praise.setText(dataList.get(
										// position).getPraiseNum()
										// + 1 + "");
									}
								}
							});
				}
			}
		});
		viewHolder.forward.setOnClickListener(new CustomOnClickListener(
				mActivity) {

			@Override
			public void doOnClick(View v) {
				forwardClick(position, dataList);
			}
		});
		viewHolder.comment.setOnClickListener(new CustomOnClickListener(
				mActivity) {

			@Override
			public void doOnClick(View v) {
				// TODO Auto-generated method stub
				if (!mUser.isVip()) {
					IdentityDialog dialog = new IdentityDialog(mActivity,
							R.style.dialog, "请在认证信息后评论");
					dialog.show();
					return;
				}
				CommentDialog dialog = new CommentDialog(mActivity,
						R.style.dialog, "",
						dataList.get(position).getSpeakId(), dataList.get(
								position).getUserId(), position, v);
				setBottomDialogStyle(dialog);
			}
		});
		return view;
	}

	protected void forwardClick(final int position,
			final List<SquareVO> dataList) {
		Map<String, Object> input = new HashMap<String, Object>();
		// if (dataList.get(position).getIsAnonymous()) {
		// input.put("isAnonymous", isAnonymous);
		// } else {
		// input.put("isAnonymous", "1");
		// }
		// input.put("content", dataList.get(position).getContent());
		// input.put("isForward", "1");
		input.put("speakId", dataList.get(position).getSpeakId());
		input.put("atUserId", dataList.get(position).getUserId());
		Map<String, String> params = new HashMap<String, String>();
		params.put("data", Util.map2Json(input));
		params.put("uid", mUser.getUid());
		params.put("token", mUser.getToken());

		if (mServerAPI == null) {
			mServerAPI = JSONClientAPI.getInstance(mActivity);
		}
		mServerAPI.square_forward(params,
				new ConnectionNetworkAbleUIBase<DefaultVO>(mActivity) {

					@Override
					public void onSuccessed(DefaultVO result)
							throws ProcessException {
						// TODO Auto-generated method stub
						// Log.d("shh", "result=" + result);
						if (result == null) {
							return;
						}
						if (result.getRetCode() == 1) {
							Toast.makeText(mActivity, "转发成功",
									Toast.LENGTH_SHORT).show();
							onRefresh();
						}
					}
				});
	}

	class ViewHolder {
		TextView cityName, userType, forward_user;
		TextView date, commentName, commentContent, commentTime;
		TextView content;
		TextView nickName;
		TextView jobName;
		Button praise;
		Button pic0, pic1, pic2, forward;
		Button comment;
		ImageButton image;
		ImageView commentPhoto, vip;
		RelativeLayout layout_image;
		LinearLayout layout_pic;
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id, List<SquareVO> dataList) {
		// TODO Auto-generated method stub
		startActivity(dataList, position);
	}

	protected void startActivity(List<SquareVO> dataList, int position) {
		isNeedRefresh = true;
	}
}
