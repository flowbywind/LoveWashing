package com.easylife.app_wash.widget;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.easylife.app_wash.R;
import com.sihehui.section_network.http.info.IServerAPI;
import com.sihehui.section_network.http.info.IServerCxdAPI;
import com.sihehui.section_network.http.json.JSONClientAPI;
import com.sihehui.section_network.http.json.JSONClientCxdAPI;
import com.sihehui.section_network.util.Contacts;
import com.sihehui.section_network.util.UserCacheManager;

public abstract class BaseFragment extends Fragment {

	protected CustomActivity mActivity;
	protected ListView mListView;
	protected IServerAPI mServerAPI;
	protected IServerCxdAPI mServerCxdAPI;
	protected Calendar mCalendar = Calendar.getInstance();
	public UserCacheManager mUser;
	public ProgressDialogSet progress;
	// protected EditText commentinput;
	protected SimpleDateFormat mYear = new SimpleDateFormat("yyyy-MM-dd");
	protected AsyncDrawableLoader asyncDrawableLoader;
	protected Contacts mContact;
	public boolean isNeedRefresh = false;
	public boolean isFirstEnter = true;
	public File cachefile;
	/**
	 * 一屏中第一个item的位置
	 */
	public int mFirstVisibleItem;

	/**
	 * 一屏中所有item的个数
	 */
	public int mVisibleItemCount;
	/**
	 * Image 下载器
	 */
	public ImageDownLoader mImageDownLoader;

	/**
	 * 取消下载任务
	 */
	public void cancelTask() {
		mImageDownLoader.cancelTask();
	}

	protected int pageSize = 15;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = setContentView(inflater, container);
		mActivity = (CustomActivity) getActivity();
		mContact = Contacts.getInstance(mActivity);
		mImageDownLoader = new ImageDownLoader(mActivity);
		asyncDrawableLoader = new AsyncDrawableLoader(mActivity);
		mUser = UserCacheManager.getInstance();
		mUser.init(mActivity);
		mServerAPI = JSONClientAPI.getInstance(mActivity);
		mServerCxdAPI = JSONClientCxdAPI.getInstance(mActivity);
		progress = new ProgressDialogSet();
		progress.setCon(mActivity);
		if (cachefile == null) {
			cachefile = FileOperate.isExistsFile();
		}
		return view;
	}

	public void loadData(CustomActivity activity, String type) {
	}

	protected void OnItemClick() {
		Toast.makeText(mActivity, "跳转到对应的详情界面", Toast.LENGTH_LONG).show();
	}

	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}

	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}

	public void refreshData() {
	}

	@Override
	public void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		refreshData();
	}

	@Override
	public void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
	}

	public abstract View setContentView(LayoutInflater inflater,
			ViewGroup container);

	public class CommentDialog extends Dialog implements OnDismissListener {
		Button dismiss, complete;
		EditText commentinput;
		String currentContent;
		String itemid;
		String speakUserId;
		int position;
		View vv;

		// @SuppressLint("InlinedApi")
		public CommentDialog(Context context, int theme, String currentContent,
				String itemid, String speakUserId, int position, View vv) {
			super(context, theme);
			// TODO Auto-generated constructor stub
			// View view = View.inflate(mActivity, R.layout.dialog_comment,
			// null);
			// setContentView(view);
			// this.currentContent = currentContent;
			// this.itemid = itemid;
			// this.speakUserId = speakUserId;
			// this.position = position;
			// this.vv = vv;
			// commentinput = (EditText) findViewById(R.id.commentinput);
			// commentinput.setFocusable(true);
			// commentinput.setFocusableInTouchMode(true);
			// commentinput.requestFocus();
			// dismiss = (Button) findViewById(R.id.dismiss);
			// complete = (Button) findViewById(R.id.complete);
			// setOnDismissListener(this);
			// Timer timer = new Timer();
			// timer.schedule(new TimerTask() {
			// @Override
			// public void run() {
			// // InputMethodManager imm = (InputMethodManager) mActivity
			// // .getSystemService(Context.INPUT_METHOD_SERVICE);
			// // imm.toggleSoftInput(0,
			// // InputMethodManager.HIDE_NOT_ALWAYS);
			// InputMethodManager inputManager = (InputMethodManager) mActivity
			// .getSystemService(mActivity.INPUT_METHOD_SERVICE);
			// inputManager.showSoftInput(commentinput, 0);
			// }
			//
			// }, 100); // 在一秒后打开

		}

		@Override
		protected void onStart() {
			// TODO Auto-generated method stub
			super.onStart();
			commentinput.setText(currentContent);

			// InputMethodManager inputManager = (InputMethodManager) mActivity
			// .getSystemService(mActivity.INPUT_METHOD_SERVICE);
			// inputManager.showSoftInput(commentinput, 0);
			dismiss.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					dismiss();
				}
			});
			complete.setOnClickListener(new CustomOnClickListener(mActivity) {

				@Override
				public void doOnClick(View v) {
					// TODO Auto-generated method stub
					// 成功后跳转位置，评论列表还是回到首页？
					// 暂定关闭当前页，回到上一页
					Log.d("shh", "comment="
							+ commentinput.getText().toString().trim());
					if (commentinput.getText().toString().trim() == null
							|| "".equals(commentinput.getText().toString()
									.trim())) {
						Toast.makeText(mActivity, "请输入评论内容", Toast.LENGTH_SHORT)
								.show();
						return;
					}
					onSucessComment(CommentDialog.this, vv, commentinput
							.getText().toString().trim(), itemid, speakUserId,
							position);
				}
			});
		}

		@Override
		public void onDismiss(DialogInterface arg0) {
			// TODO Auto-generated method stub
			// 把输入的内容传递到上一个输入框中
			// String content = commentinput.getText().toString();
			// Log.d("zhangyaobin", "content");
			// mConmentInput.setText(content);
		}
	}

	// 评论处理
	protected void onSucessComment(Dialog dialog, View v, String content,
			String itemid, String speakUserId, int position) {

	}

	protected void setBottomDialogStyle(Dialog dialog) {
		dialog.setCanceledOnTouchOutside(true);
		dialog.show();
		Window window = dialog.getWindow();
		window.setWindowAnimations(R.style.dialog_bottom_animation);
		WindowManager.LayoutParams params = window.getAttributes();
		params.gravity = Gravity.BOTTOM | Gravity.CENTER;
		DisplayMetrics metric = new DisplayMetrics();
		((Activity) mActivity).getWindowManager().getDefaultDisplay()
				.getMetrics(metric);
		params.width = metric.widthPixels;
		window.setAttributes(params);

	}

	public Drawable toRoundBitmap(Drawable drawable) {
		BitmapDrawable bd = (BitmapDrawable) drawable;
		Bitmap bitmap = bd.getBitmap();
		int width = bitmap.getWidth();
		int height = bitmap.getHeight();
		if (width == height) {
			return drawable;
		}
		float roundPx;
		float left, top, right, bottom, dst_left, dst_top, dst_right, dst_bottom;
		if (width <= height) {
			roundPx = width / 2;
			left = 0;
			top = 0;
			right = width;
			bottom = width;
			height = width;
			dst_left = 0;
			dst_top = 0;
			dst_right = width;
			dst_bottom = width;
		} else {
			roundPx = height / 2;
			float clip = (width - height) / 2;
			left = clip;
			right = width - clip;
			top = 0;
			bottom = height;
			width = height;
			dst_left = 0;
			dst_top = 0;
			dst_right = height;
			dst_bottom = height;
		}

		Bitmap output = Bitmap.createBitmap(width, height, Config.ARGB_4444);
		Canvas canvas = new Canvas(output);

		final int color = 0xff424242;
		final Paint paint = new Paint();
		final Rect src = new Rect((int) left, (int) top, (int) right,
				(int) bottom);
		final Rect dst = new Rect((int) dst_left, (int) dst_top,
				(int) dst_right, (int) dst_bottom);
		final RectF rectF = new RectF(dst);

		paint.setAntiAlias(true);// 设置画笔无锯齿

		canvas.drawARGB(0, 0, 0, 0); // 填充整个Canvas
		paint.setColor(color);

		// 以下有两种方法画圆,drawRounRect和drawCircle
		// canvas.drawRoundRect(rectF, roundPx, roundPx, paint);//
		// 画圆角矩形，第一个参数为图形显示区域，第二个参数和第三个参数分别是水平圆角半径和垂直圆角半径。
		canvas.drawCircle(roundPx, roundPx, roundPx, paint);

		paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));// 设置两张图片相交时的模式,参考http://trylovecatch.iteye.com/blog/1189452
		canvas.drawBitmap(bitmap, src, dst, paint); // 以Mode.SRC_IN模式合并bitmap和已经draw了的Circle
		BitmapDrawable draw = new BitmapDrawable(output);
		return draw;
	}

	private Bitmap compressBmpFromBmp(Bitmap image) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		int options = 100;
		image.compress(Bitmap.CompressFormat.JPEG, 100, baos);
		while (baos.toByteArray().length / 1024 > 100) {
			baos.reset();
			options -= 10;
			image.compress(Bitmap.CompressFormat.JPEG, options, baos);
		}
		ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());
		Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null);
		return bitmap;
	}

	public Bitmap toRoundBitmap(Bitmap bp) {
		// BitmapDrawable bd = (BitmapDrawable) drawable;
		// Bitmap bitmap = bd.getBitmap();
		int width = bp.getWidth();
		int height = bp.getHeight();
		// if (width == height) {
		// return bitmap;
		// }
		Bitmap bitmap = compressBmpFromBmp(bp);
		float roundPx;
		float left, top, right, bottom, dst_left, dst_top, dst_right, dst_bottom;
		if (width <= height) {
			roundPx = width / 2;
			left = 0;
			top = 0;
			right = width;
			bottom = width;
			height = width;
			dst_left = 0;
			dst_top = 0;
			dst_right = width;
			dst_bottom = width;
		} else {
			roundPx = height / 2;
			float clip = (width - height) / 2;
			left = clip;
			right = width - clip;
			top = 0;
			bottom = height;
			width = height;
			dst_left = 0;
			dst_top = 0;
			dst_right = height;
			dst_bottom = height;
		}

		Bitmap output = Bitmap.createBitmap(width, height, Config.ARGB_8888);
		Canvas canvas = new Canvas(output);

		final int color = 0xff424242;
		final Paint paint = new Paint();
		final Rect src = new Rect((int) left, (int) top, (int) right,
				(int) bottom);
		final Rect dst = new Rect((int) dst_left, (int) dst_top,
				(int) dst_right, (int) dst_bottom);
		final RectF rectF = new RectF(dst);

		paint.setAntiAlias(true);// 设置画笔无锯齿

		canvas.drawARGB(0, 0, 0, 0); // 填充整个Canvas
		paint.setColor(color);

		// 以下有两种方法画圆,drawRounRect和drawCircle
		// canvas.drawRoundRect(rectF, roundPx, roundPx, paint);//
		// 画圆角矩形，第一个参数为图形显示区域，第二个参数和第三个参数分别是水平圆角半径和垂直圆角半径。
		canvas.drawCircle(roundPx, roundPx, roundPx, paint);

		paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));// 设置两张图片相交时的模式,参考http://trylovecatch.iteye.com/blog/1189452
		canvas.drawBitmap(bitmap, src, dst, paint); // 以Mode.SRC_IN模式合并bitmap和已经draw了的Circle
		// BitmapDrawable draw = new BitmapDrawable(output);
		return output;
	}

}
