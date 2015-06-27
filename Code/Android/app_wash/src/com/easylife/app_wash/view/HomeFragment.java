package com.easylife.app_wash.view;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnKeyListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Scroller;
import android.widget.TextView;
import android.widget.Toast;

import com.easylife.app_wash.R;
import com.easylife.app_wash.widget.ConnectionNetworkAbleUIBase;
import com.easylife.app_wash.widget.CustomActivity;
import com.easylife.app_wash.widget.MD5;
import com.easylife.app_wash.widget.MyViewPager;
import com.easylife.app_wash.widget.StickyLayout;
import com.sihehui.section_network.http.info.ProcessException;
import com.sihehui.section_vo.enums.LoadDataType;
import com.sihehui.section_vo.vo.BannerVO;
import com.sihehui.section_vo.vo.LoanVO;
import com.sihehui.section_vo.vo.NormalDataVO;

public class HomeFragment extends TabListCustomFragment {
	private StickyLayout stickyLayout;
	LinearLayout adLayout;
	TextView sel_shouyi, sel_qixian, sel_zhuangtai;
	RelativeLayout relative_select;
	MyViewPager viewpager;
	ViewPagerScroller viewPagerScroller;
	SharedPreferences imagePreference;
	Editor editor;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = super.onCreateView(inflater, container, savedInstanceState);
		imagePreference = mActivity.getSharedPreferences("imagePreference",
				Activity.MODE_PRIVATE);
		stickyLayout = (StickyLayout) view.findViewById(R.id.sticky_layout);
		stickyLayout.setPullDownTemplateView(pullDownTemplateView);
		// stickyLayout.initData(true);
		sel_shouyi = (TextView) view.findViewById(R.id.sel_shouyi);
		sel_qixian = (TextView) view.findViewById(R.id.sel_qixian);
		sel_zhuangtai = (TextView) view.findViewById(R.id.sel_zhuangtai);
		relative_select = (RelativeLayout) view
				.findViewById(R.id.relative_select);
		viewpager = (MyViewPager) view.findViewById(R.id.imageView1);
		adLayout = (LinearLayout) view. // 存储顶部广告条对应的小圆点
				findViewById(R.id.adLayout);

		MyPagerChangeListener pagerListener = new MyPagerChangeListener();
		viewpager.setOnPageChangeListener(pagerListener);
		// 速度匀速，默认为加速
		LinearInterpolator interpolator = new LinearInterpolator();
		viewPagerScroller = new ViewPagerScroller(mActivity, interpolator);
		viewPagerScroller.initViewPagerScroll(viewpager);
		String allUrl = imagePreference.getString("url", "");
		// File[] files = cachefile.listFiles();
		if (!"".equals(allUrl)) {
			for (String url : allUrl.split(",")) {
				File file = new File(cachefile, MD5.getMD5(getDownLoadUrl(url)));
				if (file.exists()) {
					listUri.add(Uri.fromFile(file));
					listUrl.add(getDownLoadUrl(url));
				}
			}
			// Log.d("shh", "listUri.size()=" + listUri.size());
			if (listUri.size() > 0) {
				// hander.postDelayed(run, 5000);
				initImageView(listUri.size(), false);
			} else {
				initImageView(1, true);
			}
		} else {
			initImageView(1, true);
		}
		viewpager.setAdapter(mPagerAdapter);
		getBannerImage();
		loadData(mActivity, LoadDataType.Load);
		listener();
		return view;
	}

	int mCurrentIndex = 0;
	Handler hander = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			Log.d("shh", "handleMessage listUri.size()=" + listUri.size());
			if (listUri.size() <= 1) {
				hander.removeCallbacks(run);
			} else {
				mCurrentIndex++;
				hander.postDelayed(run, 5000);
				int position = mCurrentIndex % listUri.size();
				viewpager.setCurrentItem(position);
				setDotImage(position);
				Log.d("shh", "handleMessage mCurrentIndex=" + mCurrentIndex
						+ " position=" + position);
			}

		}
	};
	Runnable run = new Runnable() {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			hander.sendEmptyMessage(0);
		}
	};

	private void getBannerImage() {
		BannerImage banner = new BannerImage(mActivity);

		mServerCxdAPI.getBannerImage(null, banner);
	}

	private String getDownLoadUrl(String url) {
		String downloadUrl = "";
		// downloadUrl = "http://123.57.51.100:8088/cxd" + url;
		Log.d("shh", "downloadUrl=" + downloadUrl);
		downloadUrl = "http://d.hiphotos.baidu.com/image/pic/item/6d81800a19d8bc3eca7f03cb808ba61ea8d345bf.jpg";
		return downloadUrl;
	}

	private String getDetailUrl(String url) {
		String downloadUrl = "";
		downloadUrl = "http://123.57.51.100:8088/cxd" + url;
		Log.d("shh", "downloadUrl=" + downloadUrl);
		// downloadUrl =
		// "http://d.hiphotos.baidu.com/image/pic/item/6d81800a19d8bc3eca7f03cb808ba61ea8d345bf.jpg";
		return downloadUrl;
	}

	class BannerImage extends ConnectionNetworkAbleUIBase<List<BannerVO>> {

		public BannerImage(Context context) {
			super(context);
			// TODO Auto-generated constructor stub
		}

		@Override
		public void onSuccessed(List<BannerVO> result) throws ProcessException {
			// TODO Auto-generated method stub
			if (result == null) {
				return;
			}
			listUri.clear();
			listUrl.clear();
			String url = "";
			for (BannerVO vo : result) {
				url += vo.getLocation() + ",";
			}
			Log.d("shh", "url====>" + url);
			if (url != null && !"".equals(url) && url.endsWith(",")) {
				url = url.substring(0, url.length() - 1);
				String[] allImage = url.split(",");
				String allUrl = "";
				allUrlList.clear();
				for (int i = 0; i < allImage.length; i++) {
					allUrlList.add(allImage[i]);
					File file = new File(cachefile,
							MD5.getMD5(getDownLoadUrl(allImage[i])));
					Log.d("shh", "file.exists()=" + file.exists());
					if (file.exists()) {
						listUrl.add(getDetailUrl(result.get(i).getUrl()));
						listUri.add(Uri.fromFile(file));
					} else {
						allUrl += getDownLoadUrl(allImage[i]) + ",";
					}
				}
				Log.d("shh", "allUrl=" + allUrl);
				if (allUrl != null && allUrl.length() > 1
						&& allUrl.endsWith(",")) {
					allUrl = allUrl.substring(0, allUrl.length() - 1);
					ImageViewAsyncTask asyncTask = new ImageViewAsyncTask();
					asyncTask.execute(allUrl.split(","));
				} else {
					initImageView(listUri.size(), false);
					// viewpager.setAdapter(mPagerAdapter);
					mPagerAdapter.notifyDataSetChanged();
				}
			} else {
				initImageView(1, true);
				mPagerAdapter.notifyDataSetChanged();
			}
			editor = imagePreference.edit();
			editor.putString("url", url);
			editor.commit();
		}
	}

	public class ImageViewAsyncTask extends AsyncTask<String, Integer, Uri> {

		List<File> fileList = new ArrayList<File>();
		List<String> fileUrl = new ArrayList<String>();

		public ImageViewAsyncTask() {
		}

		protected Uri doInBackground(String... params) {
			Log.d("shh", "doInBackground");
			try {
				int current = 0;
				int length = params.length;// 如果是闪屏广告，则只需要下载第一张图片即可
				while (current < length) {

					String path = params[current];
					String name = MD5.getMD5(path);
					// 把新下载的资源放到缓存目录中
					File file = new File(cachefile, name);
					// 从网络上获取图片
					URL url = new URL(path);
					HttpURLConnection conn = (HttpURLConnection) url
							.openConnection();
					conn.setConnectTimeout(10000);
					conn.setRequestMethod("GET");
					conn.setDoInput(true);
					if (conn.getResponseCode() == 200) {

						InputStream is = conn.getInputStream();
						FileOutputStream fos = new FileOutputStream(file);
						byte[] buffer = new byte[1024];
						int len = 0;
						while ((len = is.read(buffer)) != -1) {
							fos.write(buffer, 0, len);
						}
						current++;
						is.close();
						fos.close();
						fileUrl.add(path);
						fileList.add(file);
					}
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			Log.d("shh", "onPreExecute");
		}

		@Override
		protected void onPostExecute(Uri result) {
			Log.d("shh", "onPostExecute");
			for (File file : fileList) {
				listUri.add(Uri.fromFile(file));
			}
			listUrl.addAll(fileUrl);
			initImageView(listUri.size(), false);
			// viewpager.setAdapter(mPagerAdapter);
			mPagerAdapter.notifyDataSetChanged();
			super.onPostExecute(result);
		}
	}

	private void listener() {
		sel_shouyi.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if (!isShowing) {
					showDialog(setListData1(), relative_select, sel_shouyi, 0);
				}
			}
		});
		sel_qixian.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if (!isShowing) {
					showDialog(setListData2(), relative_select, sel_qixian, 1);
				}
			}
		});
		sel_zhuangtai.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if (!isShowing) {
					showDialog(setListData3(), relative_select, sel_zhuangtai,
							2);
				}
			}
		});
	}

	private List<Map<String, Object>> setListData1() {

		List<Map<String, Object>> listData = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("text", "全部收益");
		map.put("min", 0);
		map.put("max", 0);
		listData.add(map);

		map = new HashMap<String, Object>();
		map.put("text", "10%以下");
		map.put("min", 0);
		map.put("max", 10);
		listData.add(map);

		map = new HashMap<String, Object>();
		map.put("text", "10%-15%");
		map.put("min", 10);
		map.put("max", 15);
		listData.add(map);

		map = new HashMap<String, Object>();
		map.put("text", "15%-20%");
		map.put("min", 15);
		map.put("max", 20);
		listData.add(map);

		map = new HashMap<String, Object>();
		map.put("text", "20%以上");
		map.put("min", 20);
		map.put("max", 0);
		listData.add(map);

		return listData;
	}

	private List<Map<String, Object>> setListData2() {

		List<Map<String, Object>> listData = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("text", "全部期限");
		map.put("min", 0);
		map.put("max", 0);
		listData.add(map);
		map = new HashMap<String, Object>();
		map.put("text", "3个月以内");
		map.put("min", 0);
		map.put("max", 3);
		listData.add(map);

		map = new HashMap<String, Object>();
		map.put("text", "3-6个月");
		map.put("min", 3);
		map.put("max", 6);
		listData.add(map);

		map = new HashMap<String, Object>();
		map.put("text", "6-12个月");
		map.put("min", 6);
		map.put("max", 12);
		listData.add(map);

		return listData;
	}

	private List<Map<String, Object>> setListData3() {

		List<Map<String, Object>> listData = new ArrayList<Map<String, Object>>();

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("text", "全部状态");
		map.put("status", "all");
		listData.add(map);

		map = new HashMap<String, Object>();
		map.put("text", "投资中");
		map.put("status", "rasing");
		listData.add(map);

		map = new HashMap<String, Object>();
		map.put("text", "还款中");
		map.put("status", "repaying");
		listData.add(map);

		map = new HashMap<String, Object>();
		map.put("text", "已完成");
		map.put("status", "complete");
		listData.add(map);

		return listData;
	}

	private boolean isShowing = false;

	private void showDialog(List<Map<String, Object>> dataList,
			View layout_select, TextView view, int type) {
		final SelectDialog dialog = new SelectDialog(getActivity(),
				R.style.dialog_no_dim, dataList, layout_select, view, type);
		dialog.show();
		dialog.setOnKeyListener(new OnKeyListener() {

			@Override
			public boolean onKey(DialogInterface dialo, int keyCode,
					KeyEvent event) {
				// TODO Auto-generated method stub
				if (keyCode == KeyEvent.KEYCODE_BACK
						&& event.getRepeatCount() == 0 && isShowing) {
					isShowing = false;
					// Log.d("zhangyaobin",
					// "keyCode=" + keyCode + " event.getRepeatCount()="
					// + event.getRepeatCount());
					// Toast.makeText(mActivity, " KeyEvent.KEYCODE_BACK", 1000)
					// .show();
					dialog.dialogExitAnimation();
					// dialog.dismiss();
					return true;
				}
				return false;
			}
		});
	}

	// int select_shouyi, select_qixian, select_zhuangtai;

	class SelectDialog extends Dialog {
		ListView select_listview;
		List<Map<String, Object>> dataList;
		View view;
		int type = 0;
		TextView performView;

		public SelectDialog(Context context, int theme,
				List<Map<String, Object>> dataList, View view,
				TextView performView, int type) {
			super(context, theme);
			// TODO Auto-generated constructor stub
			this.dataList = dataList;
			this.view = view;
			this.type = type;
			this.performView = performView;
			isShowing = true;
		}

		@Override
		public boolean onTouchEvent(MotionEvent event) {
			// If we've received a touch notification that the user has touched
			// outside the app, finish the activity.
			if (MotionEvent.ACTION_OUTSIDE == event.getAction()) {
				dialogExitAnimation();
				return true;
			}
			// Delegate everything else to Activity.
			return super.onTouchEvent(event);
		}

		Animation mAnimationFront, mAnimationExit;
		RelativeLayout layout_dialog;

		@Override
		protected void onCreate(Bundle savedInstanceState) {
			// TODO Auto-generated method stub
			super.onCreate(savedInstanceState);
			setContentView(R.layout.dialog_select);
			// setCanceledOnTouchOutside(true);
			setCancelable(false);
			getWindow().setFlags(LayoutParams.FLAG_NOT_TOUCH_MODAL,
					LayoutParams.FLAG_NOT_TOUCH_MODAL); // ...but notify us
														// that// it happened.
			getWindow().setFlags(LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH,
					LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH);

			mAnimationFront = AnimationUtils.loadAnimation(mActivity,
					R.anim.dialog_enter_top);
			mAnimationFront.setDuration(500);
			mAnimationExit = AnimationUtils.loadAnimation(mActivity,
					R.anim.dialog_exit_top);
			mAnimationExit.setDuration(500);
			layout_dialog = (RelativeLayout) findViewById(R.id.layout_dialog);
			layout_dialog.startAnimation(mAnimationFront);
			DisplayMetrics metric = new DisplayMetrics();
			Window window = getWindow();
			LayoutParams lp = window.getAttributes();
			((Activity) mActivity).getWindowManager().getDefaultDisplay()
					.getMetrics(metric);
			float density = ((Activity) mActivity).getResources()
					.getDisplayMetrics().density;
			// // 状态栏高度
			Rect frame = new Rect();
			((Activity) mActivity).getWindow().getDecorView()
					.getWindowVisibleDisplayFrame(frame);
			int statusBarHeight = frame.top;

			// lp.height = metric.heightPixels
			// - (view.getTop() + view.getHeight() + 1) - statusBarHeight;
			int location[] = new int[2];
			view.getLocationOnScreen(location);
			// lp.y = view.getTop() + view.getHeight() + 1;
			// Log.d("zhangyaobin", "location[1]=" + location[1]
			// + " statusBarHeight=" + statusBarHeight);

			int delta_height = (int) (density * 40);
			lp.y = location[1] - statusBarHeight + delta_height;
			// 边距是10dp
			int delta_width = (metric.widthPixels - delta_height / 2) / 3;
			lp.width = delta_width;
			if (type == 0) {
				lp.x = delta_height / 4;
			} else if (type == 1) {
				lp.x = delta_height / 4 + delta_width;
			} else if (type == 2) {
				lp.x = delta_height / 4 + 2 * delta_width;
			}

			// Log.d("shh", "view.getTop()=" + view.getTop()
			// + "  statusBarHeight=" + statusBarHeight + " ");
			lp.gravity = Gravity.LEFT | Gravity.TOP;
			window.setAttributes(lp);

			setOnDismissListener(dismissListener);

			if (performView != null) {
				((TextView) performView).setTextColor(getResources().getColor(
						R.color.fontcolor_red));
				// drawableRight设置
				Drawable drawable = getResources().getDrawable(
						R.drawable.indicator_up);
				// / 这一步必须要做,否则不会显示.
				drawable.setBounds(0, 0, drawable.getMinimumWidth(),
						drawable.getMinimumHeight());
				((TextView) performView).setCompoundDrawables(null, null,
						drawable, null);
			}
			select_listview = (ListView) findViewById(R.id.select_listview);
			SelectAdapter adapter = new SelectAdapter();
			select_listview.setAdapter(adapter);
			select_listview.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1,
						int arg2, long arg3) {
					// TODO Auto-generated method stub
					Toast.makeText(mActivity,
							"当前选择" + (String) dataList.get(arg2).get("text"),
							Toast.LENGTH_SHORT).show();
					performView
							.setText((String) dataList.get(arg2).get("text"));
					if (type == 0) {
						// 收益
						minRate = (Integer) dataList.get(arg2).get("min");
						maxRate = (Integer) dataList.get(arg2).get("max");
					} else if (type == 1) {
						// 期限
						minDeadline = (Integer) dataList.get(arg2).get("min");
						maxDeadline = (Integer) dataList.get(arg2).get("max");
					} else if (type == 2) {
						// 状态
						status = (String) dataList.get(arg2).get("status");
					}
					// 从第一页开始计算F
					page = 1;
					loadData(mActivity, LoadDataType.Load);
					dialogExitAnimation();
				}
			});
		}

		private void dialogExitAnimation() {
			layout_dialog.startAnimation(mAnimationExit);
			Handler handler = new Handler();
			handler.postDelayed(ran, 500);
		}

		Runnable ran = new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				dismiss();
			}
		};
		private OnDismissListener dismissListener = new OnDismissListener() {

			@Override
			public void onDismiss(DialogInterface arg0) {
				// TODO Auto-generated method stub
				if (performView != null) {
					((TextView) performView).setTextColor(getResources()
							.getColor(R.color.fontcolor_light));
					// drawableRight设置
					Drawable drawable = getResources().getDrawable(
							R.drawable.indicator_down);
					// / 这一步必须要做,否则不会显示.
					drawable.setBounds(0, 0, drawable.getMinimumWidth(),
							drawable.getMinimumHeight());
					((TextView) performView).setCompoundDrawables(null, null,
							drawable, null);
					isShowing = false;
				}
			}

		};

		@Override
		protected void onStart() {
			// TODO Auto-generated method stub
			super.onStart();
		}

		class SelectAdapter extends BaseAdapter {

			@Override
			public int getCount() {
				// TODO Auto-generated method stub
				return dataList == null ? 0 : dataList.size();
			}

			@Override
			public Object getItem(int arg0) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public long getItemId(int arg0) {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public View getView(int position, View convertView, ViewGroup arg2) {
				// TODO Auto-generated method stub
				if (convertView == null) {
					convertView = getLayoutInflater().inflate(
							R.layout.list_item_select, null);
				}
				TextView type = (TextView) convertView
						.findViewById(R.id.list_item_select_type);
				type.setText((String) dataList.get(position).get("text"));
				return convertView;
			}

		}

	}

	List data = new ArrayList();
	int page = 1;
	private int minRate, maxRate, minDeadline, maxDeadline;
	private String status = "all";
	LoadDataType loadType;

	public void loadData(CustomActivity activity, LoadDataType type) {
		LoanAppService loan = new LoanAppService(mActivity);
		Map<String, Object> input = new HashMap<String, Object>();
		input.put("page", page);
		input.put("pageSize", pageSize);
		input.put("status", status);

		input.put("minRate", minRate);
		input.put("maxRate", maxRate);
		input.put("minDeadline", minDeadline);
		input.put("maxDeadline", maxDeadline);
		loadType = type;
		mServerCxdAPI.getLoanService(input, loan);
		// for (int i = 0; i < 20; i++) {
		// data.add(i + "");
		// }
		// pullDownTemplateView.load(data, true);
	}

	class LoanAppService extends ConnectionNetworkAbleUIBase<List<LoanVO>> {

		public LoanAppService(Context context) {
			super(context);
			// TODO Auto-generated constructor stub
		}

		@Override
		public void onSuccessed(List<LoanVO> result) throws ProcessException {
			// TODO Auto-generated method stub
			if (result == null) {
				return;
			}
			if (loadType == LoadDataType.More) {
				page++;
				pullDownTemplateView.more(result, true);
			} else if (loadType == LoadDataType.Refresh) {
				page = 1;
				pullDownTemplateView.refresh(result, "暂无数据", true);
			} else {
				page = 1;
				pullDownTemplateView.load(result, true);
			}

		}
	}

	@Override
	public View setContentView(LayoutInflater inflater, ViewGroup container) {
		// TODO Auto-generated method stub
		return inflater.inflate(R.layout.fragment_home, container, false);
	}

	@Override
	public void refreshData() {
		// TODO Auto-generated method stub
		super.refreshData();
	}

	@Override
	protected String getTips() {
		// TODO Auto-generated method stub
		return super.getTips();
	}

	@Override
	public View onRefreshItemView(int position, View view, ViewGroup viewGroup,
			List<LoanVO> dataList, ListView listView) {
		// TODO Auto-generated method stub
		ViewHolder viewHolder = null;
		if (view == null) {
			viewHolder = new ViewHolder();
			view = getActivity().getLayoutInflater().inflate(
					R.layout.list_child, null);
			// TextView name, qishu, type, status, money, month, date, shengyu,
			// zhuanhuanlv, toubiaostatus, toubiaojindu;
			// ProgressBar progressbar;
			// LinearLayout layout_progressbar;
			viewHolder.name = (TextView) view.findViewById(R.id.name);
			viewHolder.qishu = (TextView) view.findViewById(R.id.qishu);
			viewHolder.type = (TextView) view.findViewById(R.id.type);
			viewHolder.status = (TextView) view.findViewById(R.id.status);
			viewHolder.money = (TextView) view.findViewById(R.id.money);
			viewHolder.month = (TextView) view.findViewById(R.id.month);
			viewHolder.date = (TextView) view.findViewById(R.id.date);
			viewHolder.shengyu = (TextView) view.findViewById(R.id.shengyu);
			viewHolder.zhuanhuanlv = (TextView) view
					.findViewById(R.id.zhuanhuanlv);
			viewHolder.toubiaostatus = (TextView) view
					.findViewById(R.id.toubiaostatus);
			viewHolder.toubiaojindu = (TextView) view
					.findViewById(R.id.toubiaojindu);
			viewHolder.progressbar = (ProgressBar) view
					.findViewById(R.id.progressbar);

			viewHolder.layout_progressbar = (LinearLayout) view
					.findViewById(R.id.layout_progressbar);

			view.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) view.getTag();
		}
		LoanVO vo = dataList.get(position);
		viewHolder.name.setText(vo.getName() + "");
		viewHolder.type.setText(vo.getLoanPurpose());
		viewHolder.money.setText(vo.getLoanMoney());
		viewHolder.month.setText(vo.getDeadline() + "月");
		viewHolder.status.setText(vo.getRepayType());
		viewHolder.shengyu.setText(vo.getRemainTime());
		// viewHolder.date.setText(vo.getDeadline());
		viewHolder.zhuanhuanlv.setText(vo.getRate());
		String progress = vo.getProgress();
		viewHolder.toubiaojindu.setText("已投标" + progress + "%");
		if (progress.contains(".")) {
			if (progress.startsWith("0")) {
				progress = "1";
			} else {
				int index = progress.indexOf(".");
				progress = progress.substring(0, index);
			}
		}
		viewHolder.progressbar.setProgress(Integer.valueOf(progress));
		viewHolder.toubiaostatus.setText("投标中");
		return view;
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id, List<LoanVO> dataList) {
		// TODO Auto-generated method stub
		super.onItemClick(parent, view, position, id, dataList);
		// Toast.makeText(getActivity(), "onItemClick " + position,
		// 1000).show();
		Intent intent = new Intent(mActivity, DetailHomeActivity.class);
		// intent.putExtra(name, value);
		intent.putExtra("dataVO", dataList.get(position));
		mActivity.startActivity(intent);

	}

	@Override
	public void onRefresh() {
		// TODO Auto-generated method stub
		super.onRefresh();
		// Toast.makeText(getActivity(), "onRefresh ", 1000).show();
		// pullDownTemplateView.refresh(data, "", true);
		loadData(mActivity, LoadDataType.Refresh);
	}

	@Override
	public void onMore() {
		// TODO Auto-generated method stub
		super.onMore();
		// Toast.makeText(getActivity(), "onMore ", 1000).show();
		loadData(mActivity, LoadDataType.More);
	}

	PagerAdapter mPagerAdapter = new PagerAdapter() {

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			// TODO Auto-generated method stub
			return arg0 == arg1;
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return mViewList.size();
		}

		@Override
		public void destroyItem(View container, int position, Object object) {
			((ViewPager) container).removeView(mViewList.get(position));
		}

		@Override
		public Object instantiateItem(View container, int position) {
			final ImageView view = mViewList.get(position);
			((ViewPager) container).addView(view);
			// if (allUrlList.size() == mViewList.size()) {
			// setDownload(allUrlList.get(position), null, view);
			// }
			view.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					Intent intent = new Intent(mActivity,
							CommonWebViewActivity.class);
					intent.putExtra("title", "活动详情");
					intent.putExtra("url", (String) view.getTag());
					startActivity(intent);
				}
			});
			return view;
		}
	};
	List<String> allUrlList = new ArrayList<String>();

	class MyPagerChangeListener implements OnPageChangeListener {

		@Override
		public void onPageScrollStateChanged(int arg0) {
			// TODO Auto-generated method stub
		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onPageSelected(int position) {
			// TODO Auto-generated method stub
			setDotImage(position);
			// if (arg0 - 1 >= 0) {
			// mPageList.get(arg0 - 1).setImageResource(R.drawable.icon01);
			// }
			// if (arg0 + 1 < mPageList.size()) {
			// mPageList.get(arg0 + 1).setImageResource(R.drawable.icon01);
			// }
			// mPageList.get(arg0).setImageResource(R.drawable.icon02);
		}

	}

	private void setDotImage(int position) {
		if (position - 1 >= 0) {
			mPageList.get(position - 1).setImageResource(R.drawable.icon01);
		}
		if (position + 1 < mPageList.size()) {
			mPageList.get(position + 1).setImageResource(R.drawable.icon01);
		}
		mPageList.get(position).setImageResource(R.drawable.icon02);
	}

	private ArrayList<ImageView> mPageList = new ArrayList<ImageView>();
	private ArrayList<ImageView> mViewList = new ArrayList<ImageView>();
	List<Uri> listUri = new ArrayList<Uri>();
	List<String> listUrl = new ArrayList<String>();

	// 初始化viewpager对应的image,dot
	private void initImageView(int size, boolean isInitial) {
		adLayout.removeAllViews();
		mViewList.clear();
		mPageList.clear();
		LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		lp.setMargins(10, 0, 10, 0);
		for (int i = 0; i < size; i++) {
			ImageView image = new ImageView(mActivity);
			if (isInitial) {
				// default_download_img9
				image.setImageResource(R.drawable.bg_ad);
			} else {
				image.setImageURI(listUri.get(i));
				image.setTag(listUrl.get(i));
			}
			image.setScaleType(ScaleType.CENTER_CROP);
			mViewList.add(image);
			if (size > 1) {
				ImageView dot = new ImageView(mActivity);
				if (i == 0) {
					dot.setImageResource(R.drawable.icon02);
				} else {
					dot.setImageResource(R.drawable.icon01);
				}
				dot.setLayoutParams(lp);
				dot.setScaleType(ScaleType.CENTER);
				adLayout.addView(dot);
				mPageList.add(dot);
			}
		}
	}

	/**
	 * ViewPager 滚动速度设置
	 * 
	 * @author Tercel
	 * 
	 */
	public class ViewPagerScroller extends Scroller {
		private int mScrollDuration = 500; // 滑动速度

		/**
		 * 设置速度速度
		 * 
		 * @param duration
		 */
		public void setScrollDuration(int duration) {
			this.mScrollDuration = duration;
		}

		public ViewPagerScroller(Context context) {
			super(context);
		}

		public ViewPagerScroller(Context context, Interpolator interpolator) {
			super(context, interpolator);
		}

		// public ViewPagerScroller(Context context, Interpolator interpolator,
		// boolean flywheel) {
		// super(context, interpolator, flywheel);
		// }

		@Override
		public void startScroll(int startX, int startY, int dx, int dy,
				int duration) {
			super.startScroll(startX, startY, dx, dy, mScrollDuration);
		}

		@Override
		public void startScroll(int startX, int startY, int dx, int dy) {
			super.startScroll(startX, startY, dx, dy, mScrollDuration);
		}

		public void initViewPagerScroll(ViewPager viewPager) {
			try {
				Field mScroller = ViewPager.class.getDeclaredField("mScroller");
				mScroller.setAccessible(true);
				mScroller.set(viewPager, this);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
