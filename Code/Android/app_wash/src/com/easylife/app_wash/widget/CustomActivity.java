package com.easylife.app_wash.widget;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.FragmentActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.easylife.app_wash.R;
import com.sihehui.section_network.http.info.BaseClientAPI;
import com.sihehui.section_network.http.info.IServerAPI;
import com.sihehui.section_network.http.info.IServerCxdAPI;
import com.sihehui.section_network.http.json.JSONClientAPI;
import com.sihehui.section_network.http.json.JSONClientCxdAPI;
import com.sihehui.section_network.util.Contacts;
import com.sihehui.section_network.util.PhotoPick;
import com.sihehui.section_network.util.PictureUtil;
import com.sihehui.section_network.util.UserCacheManager;
import com.sihehui.section_vo.enums.LoadDataType;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.bean.SocializeEntity;
import com.umeng.socialize.bean.StatusCode;
import com.umeng.socialize.controller.UMServiceFactory;
import com.umeng.socialize.controller.UMSocialService;
import com.umeng.socialize.controller.listener.SocializeListeners.SnsPostListener;
import com.umeng.socialize.media.QQShareContent;
import com.umeng.socialize.media.QZoneShareContent;
import com.umeng.socialize.media.SinaShareContent;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.sso.QZoneSsoHandler;
import com.umeng.socialize.sso.SinaSsoHandler;
import com.umeng.socialize.sso.UMQQSsoHandler;
import com.umeng.socialize.weixin.controller.UMWXHandler;
import com.umeng.socialize.weixin.media.CircleShareContent;
import com.umeng.socialize.weixin.media.WeiXinShareContent;

public abstract class CustomActivity extends FragmentActivity {
	ImageView mBackBtn;
	protected IServerAPI mServerAPI;
	protected IServerCxdAPI mServerCxdAPI;
	public UserCacheManager mUser;
	protected SimpleDateFormat mYear = new SimpleDateFormat("yyyy-MM-dd");
	protected SimpleDateFormat mNormal = new SimpleDateFormat(
			"yyyy-MM-dd HH-mm-ss");
	public ProgressDialogSet progress;
	protected AsyncDrawableLoader asyncDrawableLoader;
	protected Contacts mContact;
	public File cachefile;
	protected int PageSize = 15;
	TextView mTitle;
	public LoadDataType loadType = LoadDataType.Load;
	public int page = 1;
	public int pageSize = 15;
	public static final String TAG = "cxd";

	// 得到标准的显示时间
	protected String getStandardTime(Long time) {
		String format = mNormal.format(new Date(time));
		if (format != null && format.endsWith("00-00-00")) {
			format = format.substring(0, format.length() - 8);
		}
		return format;
	}

	private void setHeaderTitle(String title) {
		if (mTitle != null) {
			mTitle.setText(title);
		}
	}

	protected abstract String getHeaderTitle();

	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		setContentView();
		mTitle = (TextView) findViewById(R.id.txt_title);
		setHeaderTitle(getHeaderTitle());
		// getWindow().setSoftInputMode(
		// WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
		mContact = Contacts.getInstance(CustomActivity.this);
		asyncDrawableLoader = new AsyncDrawableLoader(CustomActivity.this);
		if (cachefile == null) {
			cachefile = FileOperate.isExistsFile();
		}
		mUser = UserCacheManager.getInstance();
		mUser.init(CustomActivity.this);
		mServerAPI = JSONClientAPI.getInstance(this);
		mServerCxdAPI = JSONClientCxdAPI.getInstance(this);
		progress = new ProgressDialogSet();
		progress.setCon(CustomActivity.this);
		// 初始化图片工具类
		photoPick = new PhotoPick(this, PictureUtil.ALBUM_PREFIX, null);
		mBackBtn = (ImageView) findViewById(R.id.back_btn);
		if (mBackBtn != null) {
			mBackBtn.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					startActivityAnimation();
				}
			});
		}
	}

	public PhotoPick photoPick;

	/**
	 * 拍照获取图片
	 */
	public void pickPhotoFromCamera() {
		// 判断是否挂载了SD卡
		if (Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)) {
			startActivityForResult(photoPick.createPickPhotoFromCameraIntent(),
					PictureUtil.REQUEST_PICK_PHOTO_FROM_CAMERA);
		} else {
			Toast.makeText(this, "无法保存照片，请检查SD卡是否挂载", Toast.LENGTH_LONG).show();
		}
	}

	/***
	 * 从相册中取图
	 */
	public void pickPhotoFromAlbum() {
		startActivityForResult(photoPick.createPickPhotoFromAlbumIntent(),
				PictureUtil.REQUEST_PICK_PHOTO_FROM_ALBUM);
	}

	protected KeyEvent.DispatcherState dispatcher = new KeyEvent.DispatcherState();

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_MENU) {
			if (event.getRepeatCount() == 0) {
				// dispatcher.startTracking(event, this);
			} else if (event.isLongPress() && dispatcher.isTracking(event)) {
				// 长按Home键一般没必要截获吧。
				// System.out.println("Menu Key LongPress");
			}
			return true;
		} else if (keyCode == KeyEvent.KEYCODE_BACK) {
			Log.d("shh", "CustomActivity");
			startActivityAnimation();
		}
		return super.onKeyDown(keyCode, event);
	}

	// 开始启动另一个activity的动画,默认是返回动画
	public void startActivityAnimation() {
		onBackPressed();
		overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
	}

	protected abstract void setContentView();

	protected void setBottomDialogStyle(Dialog dialog) {
		dialog.setCanceledOnTouchOutside(true);
		dialog.show();
		Window window = dialog.getWindow();
		window.setWindowAnimations(R.style.dialog_bottom_animation);
		WindowManager.LayoutParams params = window.getAttributes();
		params.gravity = Gravity.BOTTOM | Gravity.CENTER;
		DisplayMetrics metric = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(metric);
		params.width = metric.widthPixels;
		window.setAttributes(params);

	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
	}

	public Bitmap toRoundBitmap(Bitmap bitmap) {
		int width = bitmap.getWidth();
		int height = bitmap.getHeight();
		if (width == height) {
			return bitmap;
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

		return output;
	}

	// 图片下载设置
	public void setDownload(String picUrl, LinearLayout layout_pic,
			final View pic1) {
		if (picUrl != null && !"".equals(picUrl)) {
			// if (picUrl.contains(",")) {
			// layout_pic.setVisibility(View.VISIBLE);
			String[] urls = picUrl.split(",");
			if (urls.length == 1) {
				// pic1.setVisibility(View.VISIBLE);
				pic1.setTag(BaseClientAPI.getStandardURL(
						getApplicationContext(), urls[0]));
				// Drawable drawable = asyncDrawableLoader.loadDrawable(
				// BaseClientAPI.getStandardURL(getApplicationContext(),
				// urls[0]), new ImageCallBack() {
				// public void imageLoad(String strurl,
				// Drawable drawable) {
				// // ImageView imageListView = (ImageView)
				// // listView
				// // .findViewWithTag(strurl);
				// // Log.d("shh", "imageListView="
				// // + imageListView);
				// // Log.d("shh", "drawable=" + drawable);
				// if (pic1 != null && drawable != null) {
				// System.out.println("imagecallBack");
				// pic1.setBackgroundDrawable(drawable);
				// }
				// }
				// });
				// if (drawable == null) {
				// pic1.setBackgroundResource(R.drawable.default_download_img9);
				// } else {
				// pic1.setBackgroundDrawable(drawable);
				// }
			}
		}
		// else {
		// layout_pic.setVisibility(View.GONE);
		// }
		pic1.setOnClickListener(new CustomOnClickListener(CustomActivity.this) {

			@Override
			public void doOnClick(View v) {
				// TODO Auto-generated method stub
				ImageDialog dialog = new ImageDialog(CustomActivity.this,
						R.style.dialog, v);
				dialog.show();
			}
		});

	}

	protected final UMSocialService mController = UMServiceFactory
			.getUMSocialService("com.umeng.share");

	/**
	 * 配置分享平台参数</br>
	 */
	public void configPlatforms() {
		// 添加新浪SSO授权
		mController.getConfig().setSsoHandler(new SinaSsoHandler());

		// // 添加QQ、QZone平台
		addQQQZonePlatform();

		// 添加微信、微信朋友圈平台
		addWXPlatform();
		// , SHARE_MEDIA.QQ
		mController.getConfig().setPlatforms(SHARE_MEDIA.WEIXIN_CIRCLE,
				SHARE_MEDIA.QZONE, SHARE_MEDIA.SINA, SHARE_MEDIA.QQ);

	}

	/**
	 * @功能描述 : 添加QQ平台支持 QQ分享的内容， 包含四种类型， 即单纯的文字、图片、音乐、视频. 参数说明 : title, summary,
	 *       image url中必须至少设置一个, targetUrl必须设置,网页地址必须以"http://"开头 . title :
	 *       要分享标题 summary : 要分享的文字概述 image url : 图片地址 [以上三个参数至少填写一个] targetUrl
	 *       : 用户点击该分享时跳转到的目标地址 [必填] ( 若不填写则默认设置为友盟主页 )
	 * @return
	 */
	String appId = "1103680412";
	String appKey = "NVudiYHECQCeLhNq";

	protected void addQQQZonePlatform() {

		// 添加QQ支持, 并且设置QQ分享内容的target url
		UMQQSsoHandler qqSsoHandler = new UMQQSsoHandler(CustomActivity.this,
				appId, appKey);
		// qqSsoHandler.setTargetUrl("http://www.umeng.com");
		qqSsoHandler.addToSocialSDK();

		// 添加QZone平台
		QZoneSsoHandler qZoneSsoHandler = new QZoneSsoHandler(
				CustomActivity.this, appId, appKey);
		qZoneSsoHandler.addToSocialSDK();

	}

	/**
	 * @功能描述 : 添加微信平台分享
	 * @return
	 */
	protected void addWXPlatform() {

		// 注意：在微信授权的时候，必须传递appSecret
		// wx967daebe835fbeac是你在微信开发平台注册应用的AppID, 这里需要替换成你注册的AppID
		String appId = "wx223877be0d8e63e6";
		String appSecret = "398259bd49ecabbe958a7dcd5bedff1b";
		// 添加微信平台
		UMWXHandler wxHandler = new UMWXHandler(CustomActivity.this, appId,
				appSecret);
		wxHandler.addToSocialSDK();

		// 支持微信朋友圈
		UMWXHandler wxCircleHandler = new UMWXHandler(CustomActivity.this,
				appId, appSecret);
		wxCircleHandler.setToCircle(true);
		wxCircleHandler.addToSocialSDK();
	}

	public void performShare(SHARE_MEDIA platform, final Dialog dialog) {
		mController.postShare(CustomActivity.this, platform,
				new SnsPostListener() {

					@Override
					public void onStart() {
						if (dialog != null) {
							dialog.dismiss();
						}
					}

					@Override
					public void onComplete(SHARE_MEDIA platform, int eCode,
							SocializeEntity entity) {
						if (dialog != null) {
							dialog.dismiss();
						}
						String showText = platform.toString();
						Log.d("shh", "onComplete");
						if (eCode == StatusCode.ST_CODE_SUCCESSED) {
							showText += "平台分享成功";
						} else {
							showText += "平台分享失败";
						}
						Toast.makeText(CustomActivity.this, showText,
								Toast.LENGTH_SHORT).show();
						// dialog.dismiss();
					}
				});
	}

	protected String sharecontent = "诚信贷也有自己的圈了 ，赶紧下载吧";
	protected String sharetitle = "诚信贷";

	protected String getUrl() {
		return BaseClientAPI.getStandardURL(CustomActivity.this,
				"/resources/version/app_shh.apk");
	}

	/**
	 * 根据不同的平台设置不同的分享内容</br>
	 */
	public void setShareContent() {
		Log.d("shh", "sharetitle=" + sharetitle + " sharecontent="
				+ sharecontent + " getUrl()=" + getUrl());
		if (sharecontent == null) {
			sharecontent = "诚信贷不错哦 ，赶紧下载吧";
		}

		// // 配置SSO
		// mController.getConfig().setSsoHandler(new SinaSsoHandler());
		//
		QZoneSsoHandler qZoneSsoHandler = new QZoneSsoHandler(
				CustomActivity.this, appId, appKey);
		qZoneSsoHandler.addToSocialSDK();
		mController.setShareContent(sharecontent);

		UMImage localImage = new UMImage(CustomActivity.this, R.drawable.icon);
		WeiXinShareContent weixinContent = new WeiXinShareContent();
		weixinContent.setShareContent(sharecontent);
		weixinContent.setTitle(sharetitle);
		weixinContent.setTargetUrl(getUrl());
		weixinContent.setShareMedia(localImage);
		mController.setShareMedia(weixinContent);

		// 设置朋友圈分享的内容
		CircleShareContent circleMedia = new CircleShareContent();
		circleMedia.setShareContent(sharecontent);
		circleMedia.setTitle(sharetitle);
		circleMedia.setShareImage(localImage);
		// circleMedia.setShareMedia(uMusic);
		// circleMedia.setShareMedia(video);
		circleMedia.setTargetUrl(getUrl());
		mController.setShareMedia(circleMedia);

		// 设置QQ空间分享内容
		// QZoneShareContent qzone = new QZoneShareContent();
		// qzone.setShareContent(sharecontent);
		// qzone.setTargetUrl(getUrl());
		// qzone.setTitle(sharetitle);
		// // qzone.setShareImage(localImage);
		// mController.setShareMedia(qzone);
		// 设置QQ空间分享内容
		QZoneShareContent qzone = new QZoneShareContent();
		Log.d("zhangyaobin", "sharetitle=" + sharetitle + " sharecontent="
				+ sharecontent + " getUrl()=" + getUrl());
		qzone.setShareContent(sharecontent);
		qzone.setTargetUrl(getUrl());
		qzone.setTitle(sharetitle);
		qzone.setShareMedia(localImage);
		// qzone.setShareMedia(uMusic);
		mController.setShareMedia(qzone);

		SinaShareContent sinaContent = new SinaShareContent();
		sinaContent.setShareContent(sharecontent);
		mController.setShareMedia(sinaContent);

		QQShareContent qqShareContent = new QQShareContent();
		Log.d("zhangyaobin", "sharetitle=" + sharetitle + " sharecontent="
				+ sharecontent);
		qqShareContent.setShareContent(sharecontent);
		qqShareContent.setTitle(sharetitle);
		// qqShareContent.setShareMusic(uMusic);
		// qqShareContent.setShareVideo(video);
		qqShareContent.setShareImage(localImage);
		qqShareContent.setTargetUrl(getUrl());
		mController.setShareMedia(qqShareContent);

		// // 设置邮件分享内容， 如果需要分享图片则只支持本地图片
		// MailShareContent mail = new MailShareContent(localImage);
		// mail.setTitle("share form umeng social sdk");
		// mail.setShareContent("来自友盟社会化组件（SDK）让移动应用快速整合社交分享功能，email");
		// // 设置tencent分享内容
		// mController.setShareMedia(mail);
		//
		// // 设置短信分享内容
		// SmsShareContent sms = new SmsShareContent();
		// sms.setShareContent("来自友盟社会化组件（SDK）让移动应用快速整合社交分享功能，短信");
		// sms.setShareImage(urlImage);
		// mController.setShareMedia(sms);

	}

	public class ImageDialog extends Dialog {

		public ImageDialog(Context context, int theme, View v) {
			super(context, theme);
			// TODO Auto-generated constructor stub
			this.v = v;
		}

		ImageView dialog_image;
		ProgressBar dialog_progress;
		View v;

		@Override
		protected void onCreate(Bundle savedInstanceState) {
			// TODO Auto-generated method stub
			super.onCreate(savedInstanceState);
			setContentView(R.layout.dialog_image);
			setCanceledOnTouchOutside(true);
			dialog_image = (ImageView) findViewById(R.id.dialog_image);
			dialog_progress = (ProgressBar) findViewById(R.id.dialog_progress);
			dialog_image.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					dismiss();
				}
			});
			File file = new File(cachefile, MD5.getMD5((String) v.getTag()));
			if (file.exists()) {
				dialog_progress.setVisibility(View.GONE);
				dialog_image.setImageURI(Uri.fromFile(file));
			} else {
				ImageViewAsyncTask asyncTask = new ImageViewAsyncTask();
				asyncTask.execute((String) v.getTag());
			}
		}

		public class ImageViewAsyncTask extends AsyncTask<String, Integer, Uri> {

			List<File> fileList = new ArrayList<File>();

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
				dialog_progress.setVisibility(View.VISIBLE);
			}

			@Override
			protected void onPostExecute(Uri result) {
				Log.d("shh", "onPostExecute");
				Log.d("shh", "fileList=" + fileList);
				dialog_progress.setVisibility(View.GONE);
				if (fileList != null && fileList.size() > 0) {
					dialog_image.setImageURI(Uri.fromFile(fileList.get(0)));
				} else {
					dismiss();
				}
				super.onPostExecute(result);
			}
		}

	}
}