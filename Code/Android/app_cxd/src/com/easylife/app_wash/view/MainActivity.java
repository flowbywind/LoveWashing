package com.easylife.app_wash.view;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

import com.easylife.app_wash.R;
import com.easylife.app_wash.widget.BaseFragment;
import com.easylife.app_wash.widget.CustomActivity;
import com.sihehui.section_network.util.StoreActivity;

//import android.support.v4.app.FragmentManager;

public class MainActivity extends CustomActivity {
	// mImageSquare,mHomeFragment,mSquareFragment,
	ImageView mImageHome, mImageActivity, mImageSpace;
	List<ImageView> mImageList = new ArrayList<ImageView>();
	BaseFragment mActivityFragment, mHomeFragment, mSpaceFragment,
			mMoreFragment;
	FragmentManager mFragmentManager;

	// FragmentTransaction mFragmentTransaction;
	// public UserCacheManager mUser;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		// mUser = UserCacheManager.getInstance();
		// mUser.init(MainActivity.this);
		mImageHome = (ImageView) findViewById(R.id.image_home);
		mImageActivity = (ImageView) findViewById(R.id.image_activity);
		mImageSpace = (ImageView) findViewById(R.id.image_space);
		mImageList.add(mImageHome);
		mImageList.add(mImageActivity);
		mImageList.add(mImageSpace);
		mImageHome.setEnabled(false);
		mFragmentManager = getSupportFragmentManager();
		repaceFragment(getHomeFragment());
	}

	private void repaceFragment(BaseFragment fragment) {
		// FragmentManager mFragmentManager = getSupportFragmentManager();
		FragmentTransaction mFragmentTransaction = mFragmentManager
				.beginTransaction();
		mFragmentTransaction.replace(R.id.container, fragment);
		mFragmentTransaction.commit();
	}

	private void setFocuseStatus() {
		for (ImageView image : mImageList) {
			if (image.isPressed()) {
				image.setEnabled(false);
			} else {
				image.setEnabled(true);
			}
		}
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		StoreActivity.getInstance().addActivity(this);
		mImageHome.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				repaceFragment(getHomeFragment());
				// ((HomeFragment) mHomeFragment).stickyLayout.initData();
				setFocuseStatus();
			}
		});
		mImageActivity.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				// if (mUser.isVip()) {
				repaceFragment(getActivityFragment());
				setFocuseStatus();
				// } else {
				// IdentityDialog dialog = new IdentityDialog(
				// MainActivity.this, R.style.dialog, "请在认证信息后查看活动");
				// dialog.show();
				// }
			}
		});
		
		mImageSpace.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub

				repaceFragment(getSpaceFragment());
				setFocuseStatus();

			}
		});
	}

	private BaseFragment getHomeFragment() {
		// if (mHomeFragment == null) {
		mHomeFragment = new HomeFragment();
		// }
		return mHomeFragment;
	}

	private BaseFragment getActivityFragment() {
		// if (mActivityFragment == null) {
		mActivityFragment = new ActivityBaseFragment();
		// }
		return mActivityFragment;
	}


	private BaseFragment getSpaceFragment() {
		// if (mSpaceFragment == null) {
		mSpaceFragment = new SpaceFragment();
		// }
		return mSpaceFragment;
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			Intent intent = new Intent(MainActivity.this,
					ExitDialogActivity.class);
			startActivity(intent);
		}
		return true;
	}

	@Override
	protected void setContentView() {
		// TODO Auto-generated method stub
		setContentView(R.layout.main);
	}

	@Override
	protected void onActivityResult(int requestcode, int resultcode, Intent data) {
		// TODO Auto-generated method stub
		// mImageList.get(2).isEnabled()判断是否处于“我”频道
		if (!mImageList.get(2).isEnabled()
				&& photoPick.onActivityResult(requestcode, resultcode, data)) {
			// 图片压缩处理
			photoPick.compress();
			// 图片添加到手机相册中，使可以在相册中查看到刚刚上传或选择的，并且压缩过的图片
			photoPick.addPictureToGallery();
			String mCurrentPhotoPath = photoPick.getCurrentPhotoPath();
			File iFile = new File(mCurrentPhotoPath);

			if (mSpaceFragment != null) {
				// ((SpaceFragment) mSpaceFragment).setImageBitmap();
				// PictureUtil.imagePreview(
				// ((SpaceFragment) mSpaceFragment).mImage,
				// mCurrentPhotoPath);
				// ((SpaceFragment) mSpaceFragment).mImage.setTag(iFile);

				// ((SpaceFragment) mSpaceFragment)
				// .setImageBitmap(bitmap);
				// mImageView2.setImageBitmap(bitmap);
				// mImageView2.setTag(bitmap);
			}
			// {
			// Uri uri = data.getData();
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
			// * * 这里加这样一个判断主要是为了第三方的软件选择，比如：使用第三方的文件管理器的话，你选择的文件就不一定是图片了，
			// * * 这样的话，我们判断文件的后缀名 如果是图片格式的话，那么才可以
			// */
			// if (path.endsWith("jpg") || path.endsWith("png")) {
			// // picPath = path;
			// Bitmap bitmap = BitmapFactory.decodeStream(cr
			// .openInputStream(uri));
			// if (mSpaceFragment != null) {
			// ((SpaceFragment) mSpaceFragment)
			// .setImageBitmap(bitmap);
			// // mImageView2.setImageBitmap(bitmap);
			// // mImageView2.setTag(bitmap);
			// }
			//
			// }
			// }
			// } catch (Exception e) {
			// }
		}
		super.onActivityResult(requestcode, resultcode, data);
	}

	@Override
	protected String getHeaderTitle() {
		// TODO Auto-generated method stub
		return "";
	}
}
