package com.easylife.app_wash.view;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.easylife.app_wash.R;
import com.easylife.app_wash.widget.ConnectionNetworkAbleUIBase;
import com.easylife.app_wash.widget.CustomActivity;
import com.easylife.app_wash.widget.ImageCallBack;
import com.sihehui.section_network.http.info.BaseClientAPI;
import com.sihehui.section_network.http.info.ProcessException;
import com.sihehui.section_network.http.json.JSONClientAPI;
import com.sihehui.section_network.util.Util;
import com.sihehui.section_vo.vo.ListQuestionVO;
import com.sihehui.section_vo.vo.QuestionVO;

public class QuestionSpaceFragment extends QuestionCustomFragment {

	String type;

	@Override
	protected String getTips() {
		// TODO Auto-generated method stub
		if (isComment()) {
			return "暂无评论";
		} else if (isSpeak()) {
			return "暂无发言";
		} else if (isPraise()) {
			return "暂无点赞";
		} else if (isCollection()) {
			return "暂无收藏";
		} else if (isAt()) {
			return "暂无@我的信息";
		}
		return super.getTips();
	}

	int page = 1;

	private boolean isComment() {
		if ("mycomment".equals(type)) {
			return true;
		}
		return false;
	}

	private boolean isAt() {
		if ("myat".equals(type)) {
			return true;
		}
		return false;
	}

	private boolean isCollection() {
		if ("mycollection".equals(type)) {
			return true;
		}
		return false;
	}

	private boolean isSpeak() {
		if ("myspeak".equals(type)) {
			return true;
		}
		return false;
	}

	private boolean isPraise() {
		if ("mypraise".equals(type)) {
			return true;
		}
		return false;
	}

	@Override
	public void loadData(CustomActivity activity, String type) {
		// TODO Auto-generated method stub
		// super.loadData(isAnonymous);
		Log.d("shh", "type=" + type);
		this.type = type;
		pullDownTemplateView.emptyView.setText(getTips());
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
		if (isComment()) {
			mServerAPI.my_question_comment(params,
					new ConnectionNetworkAbleUIBase<ListQuestionVO>(mActivity) {

						@Override
						public void onSuccessed(ListQuestionVO result)
								throws ProcessException {
							// TODO Auto-generated method stub
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
		} else if (isSpeak()) {
			mServerAPI.my_question_speak(params,
					new ConnectionNetworkAbleUIBase<ListQuestionVO>(mActivity) {

						@Override
						public void onSuccessed(ListQuestionVO result)
								throws ProcessException {
							// TODO Auto-generated method stub
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
							pullDownTemplateView.load(result.getDataList(),
									result.getHasNextPage());

						}
					});
		} else if (isPraise()) {
			mServerAPI.my_question_praise(params,
					new ConnectionNetworkAbleUIBase<ListQuestionVO>(mActivity) {

						@Override
						public void onSuccessed(ListQuestionVO result)
								throws ProcessException {
							// TODO Auto-generated method stub
							if (result == null) {
								return;
							}
							isNeedRefresh = false;
							page = result.getNextPage() == null ? 1 : result
									.getNextPage();
							pullDownTemplateView.load(result.getDataList(),
									result.getHasNextPage());
							;

						}
					});
		} else if (isCollection()) {
			mServerAPI.my_question_collection(params,
					new ConnectionNetworkAbleUIBase<ListQuestionVO>(mActivity) {

						@Override
						public void onSuccessed(ListQuestionVO result)
								throws ProcessException {
							// TODO Auto-generated method stub
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
		} else if (isAt()) {
			mServerAPI.my_question_at(params,
					new ConnectionNetworkAbleUIBase<ListQuestionVO>(mActivity) {

						@Override
						public void onSuccessed(ListQuestionVO result)
								throws ProcessException {
							// TODO Auto-generated method stub
							if (result == null) {
								return;
							}
							isNeedRefresh = false;
							page = result.getNextPage() == null ? 1 : result
									.getNextPage();
							pullDownTemplateView.load(result.getDataList(),
									result.getHasNextPage());
							;

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
		if (isComment()) {
			mServerAPI.my_question_comment(params,
					new ConnectionNetworkAbleUIBase<ListQuestionVO>(mActivity) {

						@Override
						public void onSuccessed(ListQuestionVO result)
								throws ProcessException {
							// TODO Auto-generated method stub
							if (result == null) {
								return;
							}
							isNeedRefresh = false;
							page = result.getNextPage() == null ? 1 : result
									.getNextPage();
							pullDownTemplateView.refresh(result.getDataList(),
									getTips(), result.getHasNextPage());

						}
					});
		} else if (isSpeak()) {
			mServerAPI.my_question_speak(params,
					new ConnectionNetworkAbleUIBase<ListQuestionVO>(mActivity) {

						@Override
						public void onSuccessed(ListQuestionVO result)
								throws ProcessException {
							// TODO Auto-generated method stub
							if (result == null) {
								return;
							}
							isNeedRefresh = false;
							page = result.getNextPage() == null ? 1 : result
									.getNextPage();
							pullDownTemplateView.refresh(result.getDataList(),
									getTips(), result.getHasNextPage());

						}
					});
		} else if (isPraise()) {
			mServerAPI.my_question_praise(params,
					new ConnectionNetworkAbleUIBase<ListQuestionVO>(mActivity) {

						@Override
						public void onSuccessed(ListQuestionVO result)
								throws ProcessException {
							// TODO Auto-generated method stub
							if (result == null) {
								return;
							}
							isNeedRefresh = false;
							page = result.getNextPage() == null ? 1 : result
									.getNextPage();
							pullDownTemplateView.refresh(result.getDataList(),
									getTips(), result.getHasNextPage());

						}
					});
		} else if (isCollection()) {
			mServerAPI.my_question_collection(params,
					new ConnectionNetworkAbleUIBase<ListQuestionVO>(mActivity) {

						@Override
						public void onSuccessed(ListQuestionVO result)
								throws ProcessException {
							// TODO Auto-generated method stub
							if (result == null) {
								return;
							}
							isNeedRefresh = false;
							page = result.getNextPage() == null ? 1 : result
									.getNextPage();
							pullDownTemplateView.refresh(result.getDataList(),
									getTips(), result.getHasNextPage());

						}
					});
		} else if (isAt()) {
			mServerAPI.my_question_at(params,
					new ConnectionNetworkAbleUIBase<ListQuestionVO>(mActivity) {

						@Override
						public void onSuccessed(ListQuestionVO result)
								throws ProcessException {
							// TODO Auto-generated method stub
							if (result == null) {
								return;
							}
							isNeedRefresh = false;
							page = result.getNextPage() == null ? 1 : result
									.getNextPage();
							pullDownTemplateView.refresh(result.getDataList(),
									getTips(), result.getHasNextPage());

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
		if (isComment()) {
			mServerAPI.my_question_comment(params,
					new ConnectionNetworkAbleUIBase<ListQuestionVO>(mActivity) {

						@Override
						public void onSuccessed(ListQuestionVO result)
								throws ProcessException {
							// TODO Auto-generated method stub
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
		} else if (isSpeak()) {
			mServerAPI.my_question_speak(params,
					new ConnectionNetworkAbleUIBase<ListQuestionVO>(mActivity) {

						@Override
						public void onSuccessed(ListQuestionVO result)
								throws ProcessException {
							// TODO Auto-generated method stub
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
		} else if (isPraise()) {
			mServerAPI.my_question_praise(params,
					new ConnectionNetworkAbleUIBase<ListQuestionVO>(mActivity) {

						@Override
						public void onSuccessed(ListQuestionVO result)
								throws ProcessException {
							// TODO Auto-generated method stub
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
		} else if (isCollection()) {
			mServerAPI.my_question_collection(params,
					new ConnectionNetworkAbleUIBase<ListQuestionVO>(mActivity) {

						@Override
						public void onSuccessed(ListQuestionVO result)
								throws ProcessException {
							// TODO Auto-generated method stub
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
		} else if (isAt()) {
			mServerAPI.my_question_at(params,
					new ConnectionNetworkAbleUIBase<ListQuestionVO>(mActivity) {

						@Override
						public void onSuccessed(ListQuestionVO result)
								throws ProcessException {
							// TODO Auto-generated method stub
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

	@Override
	public View onRefreshItemView(int position, View view, ViewGroup viewGroup,
			List<QuestionVO> dataList, final ListView listView) {
		// TODO Auto-generated method stub
		if (!isComment()) {
			return super.onRefreshItemView(position, view, viewGroup, dataList,
					listView);
		}
		final ViewHolder viewHolder;
		if (view == null) {
			view = getActivity().getLayoutInflater().inflate(
					R.layout.list_item_my_commet, null);
			viewHolder = new ViewHolder();
			viewHolder.content = (TextView) view.findViewById(R.id.content);
			viewHolder.nickName = (TextView) view.findViewById(R.id.nickname);
			viewHolder.image = (ImageView) view.findViewById(R.id.image);

			viewHolder.commentContent = (TextView) view
					.findViewById(R.id.commentContent);
			viewHolder.commentName = (TextView) view
					.findViewById(R.id.commentName);
			viewHolder.commentTime = (TextView) view
					.findViewById(R.id.commentTime);
			viewHolder.commentPhoto = (ImageView) view
					.findViewById(R.id.commentPhoto);

			view.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) view.getTag();
		}
		viewHolder.nickName.setText(dataList.get(position).getUsername());
		viewHolder.content.setText(dataList.get(position).getContent());
		if (mUser.getHeadPic() != null) {

			String url = BaseClientAPI.getStandardURL(mActivity,
					mUser.getHeadPic());
			Log.d("shh", "url=" + url);
			viewHolder.commentPhoto.setTag(url);
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
				viewHolder.commentPhoto
						.setBackgroundResource(R.drawable.default_img);
			} else {
				viewHolder.commentPhoto.setBackgroundDrawable(drawable);
			}
		} else {
			viewHolder.commentPhoto
					.setBackgroundResource(R.drawable.default_img);
		}

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
				viewHolder.image.setBackgroundDrawable(drawable);
			}
		} else {
			viewHolder.image.setBackgroundResource(R.drawable.default_img);
		}
		viewHolder.commentContent.setText(dataList.get(position)
				.getCommentContent());
		viewHolder.commentName.setText(mUser.getUserName());
		viewHolder.commentTime.setText(Util.getShowTime(dataList.get(position)
				.getCommentCreateTime()));

		return view;
	}
}
