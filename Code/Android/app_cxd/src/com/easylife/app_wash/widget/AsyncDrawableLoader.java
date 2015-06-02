package com.easylife.app_wash.widget;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.SoftReference;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;

public class AsyncDrawableLoader {
	CustomActivity activity;
	private File cachefile;
	private HashMap<String, SoftReference<Drawable>> imageCache;
	private HashMap<String, Boolean> imageFixed;
	private static ExecutorService executor = Executors.newFixedThreadPool(5);

	public AsyncDrawableLoader(CustomActivity activity) {
		System.out.println("AsyncDrawableLoader");
		this.activity = activity;
		if (cachefile == null) {
			cachefile = FileOperate.isExistsFile();
		}
		if (imageCache == null) {
			imageCache = new HashMap<String, SoftReference<Drawable>>();

		}
		if (imageFixed == null) {
			imageFixed = new HashMap<String, Boolean>();
		}
	}

	public Drawable loadDrawable(final String imageURL,
			final ImageCallBack imageCallBack) {
		final String strfilename = MD5.getMD5(imageURL)
				+ imageURL.substring(imageURL.lastIndexOf("."));

		if (imageCache.containsKey(imageURL)) {// 在内存缓存中，则返回drawable对象
			SoftReference<Drawable> cache = imageCache.get(imageURL);
			Drawable drawable = cache.get();
			if (drawable != null) {
				System.out.println("softReference");
				return drawable;
			} else {
				System.out.print("had jvm ");
				imageCache.remove(imageURL);
				drawable = getCacheImage(imageURL, strfilename);
				if (drawable != null)
					return drawable;
			}
		} else {
			System.out.print("getFileImage ");
			Drawable drawable = getCacheImage(imageURL, strfilename);
			if (drawable != null)
				return drawable;
		}
		final Handler handler = new Handler() {
			@SuppressLint("HandlerLeak")
			@Override
			public void handleMessage(Message msg) {
				imageCallBack.imageLoad(imageURL, (Drawable) msg.obj);
			}
		};

		executor.execute(generateDownloadRunnable(imageURL, strfilename,
				handler));
		return null;
	}

	private Runnable generateDownloadRunnable(final String imageURL,
			final String strfilename, final Handler handler) {
		return new Runnable() {
			public void run() {
				File file = FileOperate.createFile(cachefile, strfilename);
				try {
					URL url = new URL(imageURL);
					HttpURLConnection conn = (HttpURLConnection) url
							.openConnection();
					conn.setDoInput(true);
					conn.connect();
					InputStream stream = conn.getInputStream();
					Bitmap bitmap = BitmapFactory.decodeStream(stream);
					Drawable drawable = new BitmapDrawable(bitmap);

					imageCache.put(imageURL, new SoftReference<Drawable>(
							drawable));

					Message msg = handler.obtainMessage(0, drawable);
					handler.sendMessage(msg);

					FileOutputStream fos = new FileOutputStream(file);
					if (bitmap != null) {
						bitmap.compress(Bitmap.CompressFormat.JPEG, 60, fos);
					}
					fos.close();
					stream.close();
					System.out.println("url");

				} catch (MalformedURLException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				}

			}
		};
	}

	// 第一个参数是图片的路径，第二个参数是获取到的缩略图的宽度，第三个参数是获取到的缩略图的高度private static Bitmap
	// getImageThumbnail(String imagePath, int width,
	@SuppressLint("NewApi")
	private static Bitmap getImageThumbnail(String imagePath, int width,
			int height) {
		Bitmap bitmap = null;
		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inPreferredConfig = Bitmap.Config.ARGB_8888;
		options.inPurgeable = true;
		options.inInputShareable = true;
		options.inJustDecodeBounds = true;
		// 获取这个图片的宽和高，注意此处的bitmap为null
		bitmap = BitmapFactory.decodeFile(imagePath, options);
		options.inJustDecodeBounds = false; // 设为 false
		// 计算缩放比
		int h = options.outHeight;
		int w = options.outWidth;
		int beWidth = w / width;
		int beHeight = h / height;
		int be = 1;
		if (beWidth < beHeight) {
			be = beWidth;
		} else {
			be = beHeight;
		}
		if (be <= 0) {
			be = 1;
		}
		options.inSampleSize = be;
		// 重新读入图片，读取缩放后的bitmap，注意这次要把options.inJustDecodeBounds 设为 false
		bitmap = BitmapFactory.decodeFile(imagePath, options);
		// 利用ThumbnailUtils来创建缩略图，这里要指定要缩放哪个Bitmap对象
		bitmap = ThumbnailUtils.extractThumbnail(bitmap, width, height,
				ThumbnailUtils.OPTIONS_RECYCLE_INPUT);
		return bitmap;
	}

	private int getDisplayMetric(int value) {
		int metrics = (int) (value * activity.getResources()
				.getDisplayMetrics().density);
		return metrics;
	}

	private Drawable getFileImage(String imageURL, String strfilename) {
		File file = new File(cachefile, strfilename);

		if (file.exists()) {
			Bitmap bitmap = null;
			Drawable drawable = null;
			bitmap = getImageThumbnail(file.toString(), 190, 150);
			drawable = new BitmapDrawable(bitmap);
			imageCache.put(imageURL, new SoftReference<Drawable>(drawable));
			imageFixed.put(imageURL, true);
			return drawable;
		}
		return null;
	}

	private Drawable getCacheImage(String imageURL, String strfilename) {
		File file = new File(cachefile, strfilename);

		if (file.exists()) {
			Drawable drawable = null;
			Uri uri = Uri.fromFile(file);
			try {
				drawable = Drawable.createFromStream(activity
						.getContentResolver().openInputStream(uri), null);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			imageCache.put(imageURL, new SoftReference<Drawable>(drawable));
			imageFixed.put(imageURL, true);
			// System.out.println("come file");
			return drawable;
		}
		return null;
	}
}
