package com.sihehui.section_network.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DecimalFormat;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Environment;
import android.widget.ImageView;

public class PictureUtil {
	
	public static final String APP_FOLDER = "imagecompress";
	
	public static final int REQUEST_PICK_PHOTO = 0;
	public static final int REQUEST_PICK_PHOTO_FROM_CAMERA = 1;
	public static final int REQUEST_PICK_PHOTO_FROM_ALBUM = 2;
	//图片前缀
	public static final String ALBUM_PREFIX = "ICOMPRESS";
	//压缩度
	public static final int QUALITY = 80;

	/**
	 * 计算图片的缩放值
	 * 
	 * @param options
	 * @param reqWidth
	 * @param reqHeight
	 * @return
	 */
	public static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
		final int height = options.outHeight;
		final int width = options.outWidth;
		int inSampleSize = 1;
		if (height > reqHeight || width > reqWidth) {
			final int heightRatio = Math.round((float) height / (float) reqHeight);
			final int widthRatio = Math.round((float) width / (float) reqWidth);
			inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
		}
		return inSampleSize;
	}
	
	/**
	 * 根据路径获得突破并压缩返回bitmap用于显示
	 * 
	 * @param imagesrc
	 * @return
	 */
	public static Bitmap getSmallBitmap(String filePath,int width,int height) {
		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
		BitmapFactory.decodeFile(filePath, options);
		options.inSampleSize = calculateInSampleSize(options, width, height);
		options.inJustDecodeBounds = false;
		return BitmapFactory.decodeFile(filePath, options);
	}
	
	/**
	 * 预览图片
	 */
	public static void imagePreview(ImageView mImageView, String mCurrentPhotoPath){
		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inSampleSize = 4;
		options.inJustDecodeBounds = false;
		Bitmap bitmap = BitmapFactory.decodeFile(mCurrentPhotoPath, options);
		//将图片显示在预览区
		mImageView.setImageBitmap(bitmap);
	}
	
	/**
	 * 读取图片属性：旋转的角度
	 * 
	 * @param path
	 *            图片绝对路径
	 * @return degree旋转的角度
	 */
	public static int readPictureDegree(String path) {
		int degree = 0;
		try {
			ExifInterface exifInterface = new ExifInterface(path);
			int orientation = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION,ExifInterface.ORIENTATION_NORMAL);
			switch (orientation) {
			case ExifInterface.ORIENTATION_ROTATE_90:
				degree = 90;
				break;
			case ExifInterface.ORIENTATION_ROTATE_180:
				degree = 180;
				break;
			case ExifInterface.ORIENTATION_ROTATE_270:
				degree = 270;
				break;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return degree;
	}
	
	/**
	 * 旋转图片
	 * @param angle
	 * @param bitmap
	 * @return Bitmap
	 */
	public static Bitmap rotaingImageView(int degree, Bitmap bitmap) {
		// 旋转图片动作
		Matrix matrix = new Matrix();
		matrix.postRotate(degree);
		//
		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inSampleSize = 4;
		options.inJustDecodeBounds = false;
		// 创建新的图片
		Bitmap rotatedBitmap = Bitmap.createBitmap(bitmap, 0, 0,bitmap.getWidth(), bitmap.getHeight(), matrix, true);
		return rotatedBitmap;
	}

	/**
	 * 添加到图库
	 * 拍照后的照片保存在内存中
	 * 在此，将内存中图片添加到图库（也就是SD卡中）,这样可以在手机的图库程序中看到程序拍摄的照片
	 */
	public static void addPictureToGallery(Context context, String path) {
		Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
		File f = new File(path);
		Uri contentUri = Uri.fromFile(f);
		mediaScanIntent.setData(contentUri);
		context.sendBroadcast(mediaScanIntent);
	}
	
	/**
	 * 根据路径删除图片
	 * 
	 * @param path
	 */
	public static void deleteTempFile(String path) {
		File file = new File(path);
		if (file.exists()) {
			file.delete();
		}
	}

	/**
	 * 获取保存图片的目录
	 * 
	 * @return
	 */
	public static File getAlbumDir() {
		String folder = Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + APP_FOLDER;
		File dir = new File(folder);
		if (!dir.exists()) {
			dir.mkdirs();
		}
		return dir;
	}
	
	/**
     * 取得文件大小
     * @param f
     * @return
     * @throws Exception
     */ 
    public static long getFileSizes(File f) { 
        long s = 0;
        if (f.exists()) {
            try {
            	FileInputStream fis = new FileInputStream(f);
				s = fis.available();
				fis.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
        }
        return s;
    } 
 
    /**
     * 转换文件大小
     * @param fileS
     * @return
     */
    public static String formetFileSize(long fileS) { 
        DecimalFormat df = new DecimalFormat("#.00"); 
        String fileSizeString = ""; 
        if (fileS < 1024) { 
            fileSizeString = df.format((double) fileS) + "B"; 
        } else if (fileS < 1048576) { 
            fileSizeString = df.format((double) fileS / 1024) + "K"; 
        } else if (fileS < 1073741824) { 
            fileSizeString = df.format((double) fileS / 1048576) + "M"; 
        } else { 
            fileSizeString = df.format((double) fileS / 1073741824) + "G"; 
        } 
        return fileSizeString; 
    }  
}