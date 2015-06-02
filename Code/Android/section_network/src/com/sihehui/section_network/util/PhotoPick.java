package com.sihehui.section_network.util;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.widget.ImageView;

@SuppressLint("SimpleDateFormat")
public class PhotoPick{

	private Context context;
	//选中或拍摄的图片路径
	private String mCurrentPhotoPath;
	//选中或拍摄的图片来源
	private int mCurrentPhotoFrom;
	//用来预览图片的ImageView
	private ImageView mImageView;
	//生成图片的前缀
	private String prefix;
	//缩放宽
	public static int scaleWidth = 480;
	//缩放高
	public static int scaleHeight = 800;
	
	public PhotoPick(Context context, String prefix) {
		this.context = context;
		this.prefix = prefix;
	}
	
	public PhotoPick(Context context, String prefix, ImageView preview){
		this.context = context;
		this.prefix = prefix;
		this.mImageView = preview;
	}

	public boolean onActivityResult(int requestCode, int resultCode, Intent intent) {
		if (resultCode == Activity.RESULT_OK) {
			if (requestCode == PictureUtil.REQUEST_PICK_PHOTO_FROM_CAMERA) {
				mCurrentPhotoFrom = PictureUtil.REQUEST_PICK_PHOTO_FROM_CAMERA;
			} else if (requestCode == PictureUtil.REQUEST_PICK_PHOTO_FROM_ALBUM) {
				//获取用户选择的图片相对路径
				Uri uri = intent.getData();
				//根据相对路径获取图片的物理路径
				String[] proj = { MediaStore.Images.Media.DATA };
				Cursor cursor = context.getContentResolver().query(uri, proj, null,null, null);
				int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
				cursor.moveToFirst();
				mCurrentPhotoPath = cursor.getString(column_index);
				mCurrentPhotoFrom = PictureUtil.REQUEST_PICK_PHOTO_FROM_ALBUM;
			}
			//判断如果需要展示预览，则对图片进行缩放并预览
			if(mImageView!=null){
				PictureUtil.imagePreview(mImageView,mCurrentPhotoPath);
			}
			return true;
		}else{
			mCurrentPhotoPath = "";
			return false;
		}
	}

	/***
	 * 从相册中取图
	 */
	public Intent createPickPhotoFromAlbumIntent() {
		Intent intent = new Intent();
		intent.setType("image/*");
		intent.setAction(Intent.ACTION_GET_CONTENT);
		return intent;
	}

	/**
	 * 拍照
	 */
	public Intent createPickPhotoFromCameraIntent() {
		Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		try {
			//把拍摄的照片存放到 SD卡上的应用指定的图片文件夹中
			File image = new File(PictureUtil.getAlbumDir(), newFileName());
			mCurrentPhotoPath = image.getAbsolutePath();
			takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(image));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return takePictureIntent;
	}
	
	/**
	 * 将图片设置可在手机相册中查看到
	 */
	public void addPictureToGallery(){
		//拍照后的照片保存在sdcard的项目指定目录中，用户是无法在相册中看到的
		//因此，将sdcard的项目指定中的图片添加到图库（也就是SD卡中）,这样可以在手机的图库程序中看到程序拍摄的照片
		PictureUtil.addPictureToGallery(context, mCurrentPhotoPath);
	}

	/**
	 * 保存图片
	 */
	public boolean compress() {
		if (mCurrentPhotoPath != null) {
			try {
				//读取原始图片的旋转角度
				int degree = PictureUtil.readPictureDegree(mCurrentPhotoPath);
				Bitmap bm = PictureUtil.getSmallBitmap(mCurrentPhotoPath,scaleWidth,scaleHeight);
				File newFile = null;
				//判断标识为PictureUtil.REQUEST_PICK_PHOTO_FROM_ALBUM时，表示是从相册提取的图片，此里需要将该图片压缩后转存到项目指定的目录中，以免影响原图
				if(mCurrentPhotoFrom==PictureUtil.REQUEST_PICK_PHOTO_FROM_ALBUM){
					//所在在此需要新建一个文件路径来存放压缩后的图片
					newFile = new File(PictureUtil.getAlbumDir(), newFileName());
					//并将新的路径赋值给mCurrentPhotoPath变量
					mCurrentPhotoPath = newFile.getAbsolutePath();
				}else if(mCurrentPhotoFrom==PictureUtil.REQUEST_PICK_PHOTO_FROM_CAMERA){
					newFile = new File(mCurrentPhotoPath);
				}
				FileOutputStream fos = new FileOutputStream(newFile);
				//判断当图片的旋转角度为0时，表示不需要反转，相反，需要对图片进行反转处理，以防止拍的照片是纵向的照片
				if(degree!=0){
					PictureUtil.rotaingImageView(degree,bm);
				}
				//对图片进行压缩处理
				if(bm.compress(Bitmap.CompressFormat.JPEG, PictureUtil.QUALITY, fos)){
					fos.flush();
					fos.close();
				}
				return true;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	
	/**
	 * 以指定前缀+时间戳为规则，生成新的文件名
	 * @return
	 */
	public String newFileName(){
		//照片的命名规则为：prefix_20130125_173729.jpg
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd_HHmmss");
		String timeStamp = format.format(new Date());
		String imageFileName = prefix + "_" + timeStamp + ".jpg";
		return imageFileName;
	}
	
	/**
	 * 返回当前上传或选择的图片的路径
	 * @return
	 */
	public String getCurrentPhotoPath(){
		return mCurrentPhotoPath;
	}
}