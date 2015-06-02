package com.easylife.app_wash.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.easylife.app_wash.R;
import com.easylife.app_wash.widget.BaseActivity;
import com.easylife.app_wash.widget.ConnectionNetworkAbleUIBase;
import com.sihehui.section_network.http.info.ProcessException;
import com.sihehui.section_network.util.Contacts;
import com.sihehui.section_network.util.StoreActivity;
import com.sihehui.section_network.util.Util;
import com.sihehui.section_vo.vo.VersionVO;

public class SetActivity extends BaseActivity {
	ListView mSetList;
	MyAdapter mAdapter;
	Button mLogout;

	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		mSetList = (ListView) findViewById(R.id.list_set);
		mLogout = (Button) findViewById(R.id.logout);
		mAdapter = new MyAdapter(getApplicationContext(), setListData(),
				mSetList);
		mSetList.setAdapter(mAdapter);
		mLogout = (Button) findViewById(R.id.logout);
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		StoreActivity.getInstance().addActivity(this);
		mSetList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,
					int position, long arg3) {
				// TODO Auto-generated method stub
				final Intent intent = new Intent();
				if (position == 0) {
					// Toast.makeText(getApplicationContext(), "关于我们",
					// Toast.LENGTH_LONG).show();
//					intent.setClass(SetActivity.this, AboutusActivity.class);
					startActivity(intent);
				} else if (position == 1) {
					// Toast.makeText(getApplicationContext(), "版本升级",
					// Toast.LENGTH_LONG).show();
					Map<String, Object> input = new HashMap<String, Object>();
					input.put("client", "android");
					input.put("version",
							Contacts.getInstance(SetActivity.this).VERSION);
					Map<String, String> params = new HashMap<String, String>();
					params.put("data", Util.map2Json(input));
					mServerAPI.getVersion(params,
							new ConnectionNetworkAbleUIBase<VersionVO>(
									SetActivity.this, progress) {

								@Override
								public void onSuccessed(VersionVO result)
										throws ProcessException {
									// TODO Auto-generated method stub
									if (result == null
											|| "0".equals(result.getRetCode())) {
										Toast.makeText(getApplicationContext(),
												"检查更新失败,请稍后重试",
												Toast.LENGTH_SHORT).show();
									}
									if (result.getDesc() == null) {
										// 表示当前版本为最新版本
										final Dialog dialog = new Dialog(
												SetActivity.this,
												R.style.dialog);
										View view = getLayoutInflater()
												.inflate(
														R.layout.dialog_version,
														null);
										dialog.setContentView(view);
										dialog.setCanceledOnTouchOutside(true);
										dialog.show();
										Button queding = (Button) view
												.findViewById(R.id.queding);

										queding.setOnClickListener(new OnClickListener() {

											@Override
											public void onClick(View v) {
												// TODO Auto-generated method
												// stub
												dialog.dismiss();
											}
										});
									} else {
										intent.setClass(SetActivity.this,
												DownloadDialog.class);
										intent.putExtra("version",
												result.getCurversion());
										intent.putExtra("desc",
												result.getDesc());
										intent.putExtra("link",
												result.getLink());
										startActivity(intent);
									}
								}
							});
				} else {
					// Toast.makeText(getApplicationContext(), "用户反馈",
					// Toast.LENGTH_LONG).show();
					// intent.setClass(SetActivity.this,
					// FeedBackActivity.class);
					// startActivity(intent);
				}

			}
		});
		mLogout.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(SetActivity.this,
						ExitDialogActivity.class);
				intent.putExtra("clearData", true);
				intent.putExtra("content", "确定要退出登录吗?");
				startActivity(intent);
			}
		});
	}

	@Override
	protected void setContentView() {
		// TODO Auto-generated method stub
		setContentView(R.layout.set);
	}

	private List<Map<String, Object>> setListData() {

		List<Map<String, Object>> listData = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();

		map = new HashMap<String, Object>();
		// map.put("image", R.drawable.myac_icon);
		map.put("text", "关于");
		map.put("text1", "");
		listData.add(map);

		map = new HashMap<String, Object>();
		// map.put("image", R.drawable.myac_icon);
		map.put("text", "版本升级");
		map.put("text1", "");
		listData.add(map);

		map = new HashMap<String, Object>();
		// map.put("image", R.drawable.myspacexit);
		map.put("text", "用户反馈");
		map.put("text1", "");
		listData.add(map);

		return listData;
	}

	class MyAdapter extends BaseAdapter {
		Context con;
		List<Map<String, Object>> listData;
		ListView cornerList;

		MyAdapter(Context con, List<Map<String, Object>> listData,
				ListView cornerList) {
			this.con = con;
			this.listData = listData;
			this.cornerList = cornerList;
		}

		@Override
		public int getCount() {
			return listData.size();
		}

		@Override
		public Object getItem(int position) {
			return null;
		}

		@Override
		public long getItemId(int position) {
			return 0;
		}

		ViewHolder viewHolder = null;

		@Override
		public View getView(int position, View convertView, ViewGroup arg2) {
			if (convertView == null) {
				convertView = View.inflate(con, R.layout.list_item_set, null);
				viewHolder = new ViewHolder();
				viewHolder.text = (TextView) convertView
						.findViewById(R.id.text);

				// viewHolder.text1 = (TextView) convertView
				// .findViewById(R.id.text1);

				// viewHolder.image = (ImageView) convertView
				// .findViewById(R.id.image);
				convertView.setTag(viewHolder);
			} else {
				viewHolder = (ViewHolder) convertView.getTag();
			}
			if (listData == null || listData.size() == 0) {
				return null;
			}

			viewHolder.text.setText(listData.get(position).get("text") + "");
			// viewHolder.image.setCompoundDrawablesWithIntrinsicBounds(
			// (Integer) listData.get(position).get("image"), 0, 0, 0);
			// viewHolder.text1.setText(listData.get(position).get("text1") +
			// "");
			// viewHolder.image.setImageResource((Integer)
			// listData.get(position)
			// .get("image"));
			if (position == (listData.size() - 1)) {
				// cornerList.getBottom().setDivider(null);
			}
			return convertView;
		}

		public final class ViewHolder {
			TextView text;
			// ImageView image;
			// Button image;, text1
		}
	}

	@Override
	protected String getHeaderTitle() {
		// TODO Auto-generated method stub
		return "设  置";
	}

}
