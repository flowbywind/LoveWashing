package com.easylife.app_wash.view;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import org.apache.http.client.ClientProtocolException;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.RemoteViews;
import android.widget.TextView;
import android.widget.Toast;

import com.easylife.app_wash.R;
import com.sihehui.section_network.util.Util;

/**
 * 下载对话框 初始化时展示更新说明 如果没有外部存储，提示用户安装sd卡再下载。 用户点击确定，开始使用DownloadService下载
 * 用户点击取消，dismiss
 * */

@SuppressLint("NewApi")
public class DownloadDialog extends Activity {
	private String version;
	private String link;
	private String mDesc;
	private boolean mIsLocal = false;
	// Context context;

	// File filea;
	String filePath;

	int length, downloadsize;
	NotificationManager mNotificationManager;
	String tickerText1;
	int icon1;

	PendingIntent contentIntent1;
	Notification notification1;
	RemoteViews contentView;

	public static final int START_DOWNLOAD = 1;
	public static final int DOWNLOADING = 2;
	public static final int FINISH_DOWNLOAD = 3;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dialog_version_new);
		if (android.os.Build.VERSION.SDK_INT >= 14) {
			setFinishOnTouchOutside(false);
		}
		this.version = getIntent().getStringExtra("version");
		this.mDesc = getIntent().getStringExtra("desc");
		this.link = getIntent().getStringExtra("link");
		this.mIsLocal = getIntent().getBooleanExtra("isLocal", false);

	}

	@Override
	public void onStart() {
		super.onStart();
		Button queding = (Button) findViewById(R.id.queding);
		Button cancel = (Button) findViewById(R.id.cancel);
		TextView versioncontent = (TextView) findViewById(R.id.content);
		TextView versionname = (TextView) findViewById(R.id.version);
		versionname.setText("v" + version);
		mDesc = mDesc.replace(";", "\n");
		versioncontent.setText(mDesc);
		queding.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// dismiss();
				finish();
				if(!mIsLocal){
					Uri uri = Uri.parse(link); 
					Intent it = new Intent(Intent.ACTION_VIEW, uri);   
					startActivity(it);
					return;
				}
				if (Environment.getExternalStorageState().equals(
						Environment.MEDIA_MOUNTED)) {
					new Thread() {

						public void run() {
							getDownload();

							super.run();
						}

					}.start();
				} else {
					Toast.makeText(DownloadDialog.this, "请加载SD卡后重新启动",
							Toast.LENGTH_LONG).show();
					return;
				}

			}

		});
		cancel.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// dismiss();
				finish();
			}
		});

	}

	public void getDownload() {

		// // 获取sdcard的路径 有可能出现错误！！！
		// String path = Environment.getDownloadCacheDirectory().getPath();
		// File file = new File(path + "/iletou.apk");

		// 判断是否有sd卡，若有择下载到sd卡上，没有则下载到手机内存上
		String apkname = link.substring(link.lastIndexOf("/") + 1);
		String dirPath = "";
		File dir;
		if (Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)) {
			String path = Environment.getExternalStorageDirectory().getPath();
			dirPath = path + "/iletou";
		} else {
			Toast.makeText(DownloadDialog.this, "请加载SD卡后重新启动",
					Toast.LENGTH_LONG).show();
			return;
		}
		dir = new File(dirPath);
		if (!dir.exists()) {
			dir.mkdirs();
		}

		filePath = dirPath + "/" + apkname;

		try {
			if (Util.checkNetwork(DownloadDialog.this)) {
				Log.d("URL", "link----->" + link);
				URL urls = new URL(link);
				URLConnection conn = urls.openConnection();
				conn.connect();
				length = conn.getContentLength();
				Log.d("URL", "length:" + length);
				sendMsg(START_DOWNLOAD);
				FileOutputStream os = new FileOutputStream(new File(filePath));
				InputStream is = conn.getInputStream();
				BufferedInputStream bis = new BufferedInputStream(is);

				byte[] aa = new byte[2048];
				int a;

				while ((a = bis.read(aa)) != -1) {
					downloadsize += a;
					sendMsg(DOWNLOADING);
					os.write(aa, 0, a);
				}
				sendMsg(FINISH_DOWNLOAD);
				is.close();
				os.close();
				
			} else {
				Intent intent = new Intent(DownloadDialog.this,
						NetsetDialogActivity.class);
				DownloadDialog.this.startActivity(intent);
			}
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_MENU) {
			finish();
		}

		if (keyCode == KeyEvent.KEYCODE_BACK) {
			finish();
		}
		return true;
	}

	public void sendMsg(int num) {
		Message msg = handler.obtainMessage();
		msg.what = num;
		handler.sendMessage(msg);
	}

	// Looper.getMainLooper()
	Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			switch (msg.what) {
			case START_DOWNLOAD:
				Log.d("URL", "START_DOWNLOADaaaa");
				// 得到NotificationManager
				mNotificationManager = (NotificationManager) DownloadDialog.this
						.getSystemService(DownloadDialog.this.NOTIFICATION_SERVICE);

				// Notification的滚动提示 "Download:iletou app_11x5 for android"
				tickerText1 = DownloadDialog.this
						.getString(R.string.desc_download);
				// Notification的图标，一般不要用彩色的
				icon1 = android.R.drawable.stat_sys_download;

				// Notification的Intent，即点击后转向的Activity
				// DownloadActivity.this,DownloadActivity.this.getClass()
				Intent notificationIntent1 = new Intent();
				// notificationIntent1.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
				contentIntent1 = PendingIntent.getActivity(DownloadDialog.this,
						0, notificationIntent1, 0);

				// 创建Notifcation
				notification1 = new Notification(icon1, tickerText1,
						System.currentTimeMillis());
				// // 设定Notification出现时的声音，一般不建议自定义
				// notification1.defaults = Notification.DEFAULT_SOUND;
				// // 设定是否振动
				// notification1.defaults = Notification.DEFAULT_VIBRATE;
				// notification.number=numbers++;
				// 指定Flag，Notification.FLAG_AUTO_CANCEL意指点击这个Notification后，立刻取消自身
				// notification1.flags = Notification.FLAG_AUTO_CANCEL;
				// // 这符合一般的Notification的运作规范 暂时取消该属性
				// notification1.flags = Notification.FLAG_ONGOING_EVENT;
				break;
			case DOWNLOADING:
				int percent = (downloadsize * 100 / length);

				// 创建RemoteViews用在Notification中
				contentView = new RemoteViews(
						DownloadDialog.this.getPackageName(),
						R.layout.download_progress);
				contentView.setTextViewText(R.id.notificationTitle,
						getString(R.string.desc_download));
				contentView.setTextViewText(R.id.notificationPercent, percent
						+ "%");
				contentView.setProgressBar(R.id.notificationProgress, length,
						downloadsize, false);

				notification1.contentView = contentView;

				notification1.contentIntent = contentIntent1;

				// 显示这个notification
				mNotificationManager.notify(icon1, notification1);
				break;
			case FINISH_DOWNLOAD:
				// 判断下载的内容是否完整
				if (downloadsize != length) {

					final Dialog dialog = new Dialog(DownloadDialog.this,
							R.style.dialog);
					View view = getLayoutInflater().inflate(
							R.layout.dialog_exit, null);
					dialog.setContentView(view);
					// dialog.setCanceledOnTouchOutside(true);
					dialog.show();
					RelativeLayout titlerelative = (RelativeLayout) view
							.findViewById(R.id.title);
					Button queding = (Button) view.findViewById(R.id.ensure);
					Button cancel = (Button) view.findViewById(R.id.cancel);
					Button dismiss = (Button) view.findViewById(R.id.dismiss);
					TextView title = (TextView) view.findViewById(R.id.ziliao);
					TextView content = (TextView) view
							.findViewById(R.id.content);
					title.setText("提示信息");
					content.setText("您当前下载的内容不完整，是否重新下载?");
					queding.setOnClickListener(new View.OnClickListener() {

						@Override
						public void onClick(View v) {
							// TODO Auto-generated method stub
							dialog.dismiss();
							new Thread() {

								public void run() {
									getDownload();

									super.run();
								}

							}.start();
						}
					});
					cancel.setOnClickListener(new View.OnClickListener() {

						@Override
						public void onClick(View v) {
							// TODO Auto-generated method stub
							dialog.dismiss();
						}
					});
					dismiss.setOnClickListener(new View.OnClickListener() {

						@Override
						public void onClick(View v) {
							// TODO Auto-generated method stub
							dialog.dismiss();
						}
					});
					titlerelative
							.setOnClickListener(new View.OnClickListener() {

								@Override
								public void onClick(View v) {
									// TODO Auto-generated method stub
									dialog.dismiss();
								}
							});
					return;
				}
				// 下载完毕后notification消失
				mNotificationManager.cancel(icon1);
				Toast.makeText(DownloadDialog.this, "已经下载完毕", Toast.LENGTH_LONG)
						.show();
				// // 保存版本信息

				new Thread() {
					@Override
					public void run() {
						super.run();
						try {
							Thread.sleep(1000);
							Intent i = new Intent(Intent.ACTION_VIEW);
							File filea = new File(filePath);
							i.setDataAndType(Uri.fromFile(filea),
									"application/vnd.android.package-archive");
							Log.d("URL", "filea  " + filea);
							i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
							((Activity) DownloadDialog.this).startActivity(i);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}

					}

				}.start();

				break;
			}
		}

	};

}
