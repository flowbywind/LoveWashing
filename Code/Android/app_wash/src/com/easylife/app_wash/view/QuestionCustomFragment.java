package com.easylife.app_wash.view;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.easylife.app_wash.R;
import com.easylife.app_wash.widget.BaseFragment;
import com.easylife.app_wash.widget.ConnectionNetworkAbleUIBase;
import com.easylife.app_wash.widget.CustomOnClickListener;
import com.easylife.app_wash.widget.IdentityDialog;
import com.easylife.app_wash.widget.ImageCallBack;
import com.easylife.app_wash.widget.PullDownTemplateView;
import com.easylife.app_wash.widget.BaseFragment.CommentDialog;
import com.easylife.app_wash.widget.PullDownTemplateView.OnPullDownTemplateViewListener;
import com.sihehui.section_network.http.info.BaseClientAPI;
import com.sihehui.section_network.http.info.ProcessException;
import com.sihehui.section_network.util.Util;
import com.sihehui.section_vo.vo.DefaultVO;
import com.sihehui.section_vo.vo.QuestionVO;

public class QuestionCustomFragment extends BaseFragment implements
		OnPullDownTemplateViewListener<QuestionVO> {

	protected PullDownTemplateView<QuestionVO> pullDownTemplateView;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = super.onCreateView(inflater, container, savedInstanceState);
		pullDownTemplateView = (PullDownTemplateView<QuestionVO>) view
				.findViewById(R.id.tab_list_pull_down);
		pullDownTemplateView.setPullDownTemplateViewListener(this);
		pullDownTemplateView.pullDownView.mListView.setDivider(null);
		pullDownTemplateView.pullDownView.mListView
				.setVerticalScrollBarEnabled(true);

		return view;
	}

	protected String getTips() {
		return "暂无一问问题";
	}

	public void loadData(String type) {

	}

	public void refreshData() {
		if (isNeedRefresh) {
			onRefresh();
		}
	}

	@Override
	public void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
	}

	@Override
	public View setContentView(LayoutInflater inflater, ViewGroup container) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragment_question, container,
				false);
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
			ViewGroup viewGroup, final List<QuestionVO> dataList,
			final ListView listView) {
		// TODO Auto-generated method stub
		final ViewHolder viewHolder;
		if (view == null) {
			view = getActivity().getLayoutInflater().inflate(
					R.layout.list_item_question_normal, null);
			viewHolder = new ViewHolder();
			viewHolder.date = (TextView) view.findViewById(R.id.date);
			viewHolder.answer = (TextView) view.findViewById(R.id.answer);
			viewHolder.nickName = (TextView) view.findViewById(R.id.name);
			viewHolder.title = (TextView) view.findViewById(R.id.title);
			viewHolder.praise = (Button) view.findViewById(R.id.praise);
			viewHolder.image = (ImageView) view.findViewById(R.id.image);
			viewHolder.comment = (Button) view.findViewById(R.id.message);
			viewHolder.collection = (Button) view.findViewById(R.id.collection);
			viewHolder.experts = (Button) view.findViewById(R.id.expert);
			viewHolder.answer = (TextView) view.findViewById(R.id.answer);
			viewHolder.layout_comment = (RelativeLayout) view
					.findViewById(R.id.layout_comment);
			viewHolder.content_name = (TextView) view
					.findViewById(R.id.list_item_content_name);
			viewHolder.pic0 = (Button) view.findViewById(R.id.pic0);
			viewHolder.pic1 = (Button) view.findViewById(R.id.pic1);
			viewHolder.pic2 = (Button) view.findViewById(R.id.pic2);
			viewHolder.layout_pic = (LinearLayout) view
					.findViewById(R.id.layout_pic);
			view.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) view.getTag();
		}

		viewHolder.date.setText(Util.getShowTime(dataList.get(position)
				.getCreateTime()));
		if (dataList.get(position).getContentUserName() != null) {
			viewHolder.content_name.setText(dataList.get(position)
					.getContentUserName() + ": ");
			viewHolder.answer.setText(dataList.get(position)
					.getCommentContent());
			viewHolder.layout_comment.setVisibility(View.VISIBLE);
		} else {
			viewHolder.layout_comment.setVisibility(View.GONE);
		}

		viewHolder.image.setImageResource(R.drawable.default_img);
		// viewHolder.praise.setText(dataList.get(position).getPraiseNum() +
		// "");
		// viewHolder.comment.setText(dataList.get(position).getCommentNum() +
		// "");
		// viewHolder.collection.setText(dataList.get(position).getCollectionNum()
		// + "");
		viewHolder.title.setText(dataList.get(position).getContent());
		viewHolder.nickName.setText(dataList.get(position).getUsername());
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

		viewHolder.praise.setOnClickListener(new CustomOnClickListener(
				mActivity) {

			@Override
			public void doOnClick(View v) {
				// TODO Auto-generated method stub
				if (dataList.get(position).getHasPraise()) {
					Toast.makeText(mActivity, "您已点赞", Toast.LENGTH_SHORT)
							.show();
					return;
				}
				Map<String, String> input = new HashMap<String, String>();
				input.put("uid", mUser.getUid());
				input.put("token", mUser.getToken());
				input.put("askId", dataList.get(position).getAskId());
				mServerAPI.question_praise(input,
						new ConnectionNetworkAbleUIBase<DefaultVO>(
								getActivity()) {

							@Override
							public void onSuccessed(DefaultVO result)
									throws ProcessException {
								// TODO Auto-generated method stub
								if (result == null) {
									return;
								}
								if (result.getRetCode() == 1) {
									dataList.get(position).setHasPraise(true);
									Toast.makeText(getActivity(), "点赞成功",
											Toast.LENGTH_SHORT).show();
									// viewHolder.praise.setText(dataList.get(
									// position).getPraiseNum()
									// + 1 + "");
								}
							}
						});
			}
		});
		viewHolder.collection.setOnClickListener(new CustomOnClickListener(
				mActivity) {

			@Override
			public void doOnClick(View v) {
				// TODO Auto-generated method stub
				if (dataList.get(position).getHasCollection()) {
					Toast.makeText(mActivity, "您已收藏", Toast.LENGTH_SHORT)
							.show();
					return;
				}
				Map<String, String> input = new HashMap<String, String>();
				input.put("uid", mUser.getUid());
				input.put("token", mUser.getToken());
				input.put("askId", dataList.get(position).getAskId());
				mServerAPI.question_collection(input,
						new ConnectionNetworkAbleUIBase<DefaultVO>(
								getActivity()) {

							@Override
							public void onSuccessed(DefaultVO result)
									throws ProcessException {
								// TODO Auto-generated method stub
								if (result == null) {
									return;
								}
								if (result.getRetCode() == 1) {
									dataList.get(position).setHasPraise(true);
									Toast.makeText(getActivity(), "收藏成功",
											Toast.LENGTH_SHORT).show();
									// viewHolder.praise.setText(dataList.get(
									// position).getPraiseNum()
									// + 1 + "");
								}
							}
						});
			}
		});
		viewHolder.experts.setOnClickListener(new CustomOnClickListener(
				mActivity) {

			@Override
			public void doOnClick(View v) {
				// TODO Auto-generated method stub
			}
		});
		viewHolder.comment.setOnClickListener(new CustomOnClickListener(
				mActivity) {

			@Override
			public void doOnClick(View v) {
				// TODO Auto-generated method stub
				if (!mUser.isVip()) {
					IdentityDialog dialog = new IdentityDialog(mActivity,
							R.style.dialog, "请在认证信息后回答");
					dialog.show();
					return;
				}
				CommentDialog dialog = new CommentDialog(mActivity,
						R.style.dialog, "", dataList.get(position).getAskId(),
						"", position, v);
				setBottomDialogStyle(dialog);
			}
		});
		return view;
	}

	class ViewHolder {
		TextView title, commentName, commentContent, commentTime;
		TextView date;
		TextView answer, content_name;
		TextView nickName, content;
		// TextView jobName;
		Button pic0, pic1, pic2, praise;
		Button collection;
		Button experts;
		Button comment;
		ImageView image, commentPhoto;
		RelativeLayout layout_comment;
		LinearLayout layout_pic;
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id, List<QuestionVO> dataList) {
		// TODO Auto-generated method stub
		isNeedRefresh = true;
	}

	@Override
	protected void onSucessComment(final Dialog dialog, View v, String content,
			String itemid, String speakUserId, int position) {
		// TODO Auto-generated method stub
		Map<String, Object> input = new HashMap<String, Object>();
		input.put("askId", itemid);
		input.put("content", content);

		Map<String, String> params = new HashMap<String, String>();
		params.put("uid", mUser.getUid());
		params.put("token", mUser.getToken());
		params.put("data", Util.map2Json(input));
		progress.startProgress();
		mServerAPI
				.question_answer(params,
						new ConnectionNetworkAbleUIBase<DefaultVO>(mActivity,
								progress) {

							@Override
							public void onSuccessed(DefaultVO result)
									throws ProcessException {
								// TODO Auto-generated method stub
								// Log.d("shh", "article_comment result=" +
								// result);
								progress.stopProgress();
								if (result == null) {
									dialog.cancel();
									return;
								}
								// hasPraise = true;
								if (result.getRetCode() == 1) {
									Toast.makeText(mActivity, "回答成功",
											Toast.LENGTH_SHORT).show();

								}
							}
						});
		dialog.cancel();
	}
}
