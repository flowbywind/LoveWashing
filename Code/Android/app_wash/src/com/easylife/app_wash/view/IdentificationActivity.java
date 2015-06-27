package com.easylife.app_wash.view;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.easylife.app_wash.R;
import com.easylife.app_wash.widget.BaseActivity;
import com.easylife.app_wash.widget.ConnectionNetworkAbleUIBase;
import com.easylife.app_wash.widget.CustomOnClickListener;
import com.sihehui.section_network.http.info.ProcessException;
import com.sihehui.section_network.util.PictureUtil;
import com.sihehui.section_network.util.StoreActivity;
import com.sihehui.section_network.util.UserCacheManager;
import com.sihehui.section_network.util.Util;
import com.sihehui.section_vo.vo.LoginVO;

public class IdentificationActivity extends BaseActivity {
	Button mRight, mQueding;
	ImageView mPhoto;
	EditText mName, mMarket, mBrand, mDescribe;
	RelativeLayout mLayout_brand, mLayout_market, mLayout_describle;
	TextView mType;// 四大身份信息
	TextView mCity;// 所在城市
	TextView mHangye;// 所在行业
	TextView mPosition;// 职业
	String mUserTypeId = "03", mCityId = "", mIndustryId = "",
			mPositionId = "";

	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		mType = (TextView) findViewById(R.id.type);
		mCity = (TextView) findViewById(R.id.city);
		mHangye = (TextView) findViewById(R.id.business);
		mRight = (Button) findViewById(R.id.right_btn);
		mQueding = (Button) findViewById(R.id.queding);
		mPhoto = (ImageView) findViewById(R.id.photo);
		mName = (EditText) findViewById(R.id.name);
		if (mUser != null && mUser.getUserName() != null) {
			mName.setText(mUser.getUserName());
		}
		mMarket = (EditText) findViewById(R.id.market);
		mPosition = (TextView) findViewById(R.id.position);
		mDescribe = (EditText) findViewById(R.id.describe);
		mBrand = (EditText) findViewById(R.id.brand);
		mLayout_brand = (RelativeLayout) findViewById(R.id.layout_brand);
		mLayout_market = (RelativeLayout) findViewById(R.id.layout_market);
		mLayout_describle = (RelativeLayout) findViewById(R.id.layout_describe);
		mLayout_brand.setVisibility(View.GONE);
		mLayout_market.setVisibility(View.VISIBLE);
		mLayout_describle.setVisibility(View.GONE);
	}

	private void click2SelectActivity(int value) {
		click2SelectActivity(value, "");
		// Intent intent = new Intent(IdentificationActivity.this,
		// SelectActivity.class);
		// intent.putExtra("type", value);
		// startActivityForResult(intent, 10);
	}

	private void click2SelectActivity(int value, String usertype) {
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		StoreActivity.getInstance().addActivity(IdentificationActivity.this);
		
		mPhoto.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				PhotoDialog dialog = new PhotoDialog(
						IdentificationActivity.this, R.style.dialog);
				setBottomDialogStyle(dialog);
			}
		});
		mRight.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(IdentificationActivity.this,
						MainActivity.class);
				startActivity(intent);
				finish();
			}
		});
		mQueding.setOnClickListener(new CustomOnClickListener(
				IdentificationActivity.this) {

			@Override
			public void doOnClick(View v) {
				// TODO Auto-generated method stub
				Map<String, Object> input = new HashMap<String, Object>();
				UserConfirmListener listener = new UserConfirmListener(
						getApplicationContext());
				// UserCacheManager user = UserCacheManager.getInstance();
				// user.init(IdentificationActivity.this);
				String name = mName.getText().toString().trim();
				if (name == null || "".equals(name) || name.length() < 2) {
					Toast.makeText(getApplicationContext(), "请输入有效的姓名",
							Toast.LENGTH_SHORT).show();
					return;
				}
				if (mCityId == null || "".equals(mCityId)) {
					Toast.makeText(getApplicationContext(), "请选择所在城市",
							Toast.LENGTH_SHORT).show();
					return;
				}
				if (mUserTypeId == null || "".equals(mUserTypeId)) {
					Toast.makeText(getApplicationContext(), "请选择身份类型",
							Toast.LENGTH_SHORT).show();
					return;
				}
				if (mPositionId == null || "".equals(mPositionId)) {
					Toast.makeText(getApplicationContext(), "请选择职位",
							Toast.LENGTH_SHORT).show();
					return;
				}
				// String postion = mPosition.getText().toString().trim();
				// if (postion == null || "".equals(postion)) {
				// Toast.makeText(getApplicationContext(), "请输入职位",
				// Toast.LENGTH_SHORT).show();
				// return;
				// }
				input.put("jobId", mPositionId);
				// if ("卖场".equals(ShenfenDic.codeToName(mUserTypeId))) {
				// String market = mMarket.getText().toString().trim();
				// if (market == null || "".equals(market)) {
				// Toast.makeText(getApplicationContext(), "请输入卖场",
				// Toast.LENGTH_SHORT).show();
				// return;
				// }
				// input.put("mallName", market);
				// // if (mIndustryId == null || "".equals(mIndustryId)) {
				// // Toast.makeText(getApplicationContext(), "请选择所在行业",
				// // Toast.LENGTH_SHORT).show();
				// // return;
				// // }
				//
				// } else if ("其他".equals(ShenfenDic.codeToName(mUserTypeId))) {
				// String describe = mDescribe.getText().toString().trim();
				// if (describe == null || "".equals(describe)
				// || describe.trim().length() < 5) {
				// Toast.makeText(getApplicationContext(),
				// "请至少输入五个字的公司描述信息", Toast.LENGTH_SHORT).show();
				// return;
				// }
				// input.put("companyDesc", describe);
				// } else {
				// String brand = mBrand.getText().toString().trim();
				// if (brand == null || "".equals(brand)) {
				// Toast.makeText(getApplicationContext(), "请输入代理品牌",
				// Toast.LENGTH_SHORT).show();
				// return;
				// }
				// input.put("brandName", brand);
				// if (mIndustryId == null || "".equals(mIndustryId)) {
				// Toast.makeText(getApplicationContext(), "请选择所在行业",
				// Toast.LENGTH_SHORT).show();
				// return;
				// }
				// input.put("industryId", mIndustryId);
				// }

				// 必填
				input.put("userName", name);
				input.put("cityId", mCityId);
				input.put("usertypeId", mUserTypeId);

				// 图片
				Map<String, Object> fileMap = new HashMap<String, Object>();
				if (mPhoto.getTag() != null) {
					fileMap.put("avatarFile", (File) mPhoto.getTag());
				} else {
					Toast.makeText(getBaseContext(), "请选择名片",
							Toast.LENGTH_SHORT).show();
					return;
				}

				Map<String, String> params = new HashMap<String, String>();
				params.put("uid", mUser.getUid());
				params.put("token", mUser.getToken());
				params.put("data", Util.map2Json(input));
				mServerAPI.user_confirm(fileMap, params, listener);
				// ProgressDialogSet progress = new ProgressDialogSet();
				// progress.setCon(IdentificationActivity.this);
				listener.progressDialogSet = progress;
				progress.startProgress();
			}
		});
	}

	private class UserConfirmListener extends
			ConnectionNetworkAbleUIBase<LoginVO> {

		public UserConfirmListener(Context context) {
			super(context);
			// TODO Auto-generated constructor stub
		}

		@Override
		public void onSuccessed(LoginVO result) throws ProcessException {
			// TODO Auto-generated method stub
			// 增加登录成功后的逻辑，即保存用户名和密码，在splashActivity中跳转使用
			Log.d("shh", "result=" + result);
			progressDialogSet.stopProgress();
			if (result == null) {
				return;
			}
			Log.d("shh", "result.getRetCode=" + result.getRetCode());
			if (result.getRetCode() == 1) {
				UserCacheManager user = UserCacheManager.getInstance();
				user.init(getBaseContext());
				user.setData(result);
				Intent intent = new Intent(IdentificationActivity.this,
						MainActivity.class);
				startActivity(intent);

			}
		}
	}

	public class PhotoDialog extends Dialog {

		public PhotoDialog(Context context, int theme) {
			super(context, theme);
			// TODO Auto-generated constructor stub
		}

		Button callCamera, callPhoto, dismiss;

		@Override
		protected void onCreate(Bundle savedInstanceState) {
			// TODO Auto-generated method stub
			super.onCreate(savedInstanceState);
			View view = View.inflate(IdentificationActivity.this,
					R.layout.dialog_photo, null);
			callCamera = (Button) view.findViewById(R.id.callCamera);
			callPhoto = (Button) view.findViewById(R.id.callPhoto);
			dismiss = (Button) view.findViewById(R.id.dismiss);
			setContentView(view);

		}

		@Override
		protected void onStart() {
			// TODO Auto-generated method stub
			super.onStart();
			callCamera.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					pickPhotoFromCamera();
					dismiss();
				}
			});
			callPhoto.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					pickPhotoFromAlbum();
					// Intent intent = new Intent();
					// intent.setType("image/*");
					// intent.setAction(Intent.ACTION_GET_CONTENT);
					// startActivityForResult(intent, 1);
					dismiss();
				}
			});
			dismiss.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					dismiss();
				}
			});
		}

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		 if (resultCode == RESULT_OK) {
			// if (resultCode == 1) {
			// 读取相册
			Log.d("zhangyaobin", "执行到这里");
			if (photoPick.onActivityResult(requestCode, resultCode, data)) {
				// 图片压缩处理
				photoPick.compress();
				// 图片添加到手机相册中，使可以在相册中查看到刚刚上传或选择的，并且压缩过的图片
				photoPick.addPictureToGallery();
				String mCurrentPhotoPath = photoPick.getCurrentPhotoPath();
				File iFile = new File(mCurrentPhotoPath);

				PictureUtil.imagePreview(mPhoto, mCurrentPhotoPath);
				mPhoto.setTag(iFile);

				// String fileName = iFile.getAbsolutePath();
				// String fileSize =
				// PictureUtil.formetFileSize(PictureUtil.getFileSizes(iFile));
				// mImageInfoBar.setText(fileName + "\n" + fileSize);
				// }
				// Uri uri = data.getData();
				// Log.d("zhangyaobin", "uri.toString=" + uri.toString());
				// try {
				// String[] pojo = { MediaStore.Images.Media.DATA };
				// Cursor cursor = managedQuery(uri, pojo, null, null, null);
				// if (cursor != null) {
				// ContentResolver cr = this.getContentResolver();
				// int colunm_index = cursor
				// .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
				// cursor.moveToFirst();
				// String path = cursor.getString(colunm_index);
				// /***
				// * * 这里加这样一个判断主要是为了第三方的软件选择，比如：使用第三方的文件管理器的话，
				// * 你选择的文件就不一定是图片了， * 这样的话，我们判断文件的后缀名 如果是图片格式的话，那么才可以
				// */
				// if (path.endsWith("jpg") || path.endsWith("png")) {
				// // picPath = path;
				// bitmap = BitmapFactory.decodeStream(cr
				// .openInputStream(uri));
				// mPhoto.setImageBitmap(bitmap);
				// }
				// }
				// } catch (Exception e) {
				// }
			}
			// else if (requestCode == 2) {
			// Bundle bundle = data.getExtras();
			// bitmap = (Bitmap) bundle.get("data");
			// mPhoto.setImageBitmap(bitmap);
			// }
		}
		super.onActivityResult(requestCode, resultCode, data);
	}

	// Bitmap bitmap;

	// private File bitmap2File(Context context, String fileName, Bitmap bitmap)
	// {
	// // create a file to write bitmap data
	// File f = new File(context.getCacheDir(), fileName);
	// try {
	// f.createNewFile();
	// ByteArrayOutputStream bos = new ByteArrayOutputStream();
	// bitmap.compress(CompressFormat.JPEG, 50 /* ignored for PNG */, bos);
	// byte[] bitmapdata = bos.toByteArray();
	//
	// // write the bytes in file
	// FileOutputStream fos = new FileOutputStream(f);
	// fos.write(bitmapdata);
	// bos.close();
	// } catch (IOException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// return f;
	//
	// }

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}

	@Override
	protected void setContentView() {
		// TODO Auto-generated method stub
		setContentView(R.layout.identification);
	}

	@Override
	protected String getHeaderTitle() {
		// TODO Auto-generated method stub
		return null;
	}

}
