package com.easylife.app_wash.view;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import com.easylife.app_wash.R;
import com.easylife.app_wash.widget.BaseFragment;
import com.easylife.app_wash.widget.ConnectionNetworkAbleUIBase;
import com.easylife.app_wash.widget.ListItemAdapter;
import com.easylife.app_wash.widget.ListViewForScrollView;
import com.sihehui.section_network.http.info.ProcessException;
import com.sihehui.section_network.util.Contacts;
import com.sihehui.section_network.util.UserCacheManager;
import com.sihehui.section_network.util.Util;
import com.sihehui.section_vo.vo.VersionVO;

import android.webkit.WebView.FindListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.Toast;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;

public class MoreFragment extends ListBaseFragment {

	public MoreFragment() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = super.onCreateView(inflater, container, savedInstanceState);
		Button btnphone=(Button)view.findViewById(R.id.btn_servicephone);
		final String phone=(String)btnphone.getText();
		btnphone.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			         Intent intent=new Intent(Intent.ACTION_DIAL,Uri.parse("tel:"+phone));
			         startActivity(intent);
			}
		});
		return view;
	}

	@Override
	public void onStart() {
		// TODO Auto-generated method stub
		super.onStart();

	}

	@Override
	public View setContentView(LayoutInflater inflater, ViewGroup container) {
		// TODO Auto-generated method stub
		return inflater.inflate(R.layout.fragment_more, container, false);
	}

	@Override
	public List<String> SetListData() {
		// TODO Auto-generated method stub
		List<String> list = new ArrayList<String>();
		list.add("关于我们");
		list.add("常见问题");
		list.add("分享好友");
		list.add("意见反馈");
		list.add("检查更新");
		return list;
	}

	@Override
	protected void OnItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		final Intent intent=new Intent();
		if(position==0) //关于我们
		{
			intent.setClass(mActivity, AboutusActivity.class);
			intent.putExtra("url", "http://www.baidu.com");
			startActivity(intent);
		}
		else if(position==1)
		{
//			UserCacheManager user=UserCacheManager.getInstance();
//			user.init(mActivity.getBaseContext());
//			user.clearData();
//			Intent intents=new Intent(mActivity,LoginActivity.class);
//			startActivity(intents);
		}
		else if (position==2)
		{
			
		}
		else if(position==3)
		{
			intent.setClass(mActivity, SuggestActivity.class);
			startActivity(intent);
		}
		else if (position==4)
		{
			Map<String,Object> input=new HashMap<String,Object>();
			input.put("client","android");
			input.put("version",Contacts.getInstance(mActivity).VERSION);
			Map<String,String> params=new HashMap<String,String>();
	        System.out.println(Util.map2Json(input));
			params.put("data",Util.map2Json(input));
			mServerAPI.getVersion(params,new ConnectionNetworkAbleUIBase<VersionVO>(
					 mActivity,progress){

						@Override
						public void onSuccessed(VersionVO result)
								throws ProcessException {
							// TODO Auto-generated method stub
							if(result==null || "0".equals(result.getRetCode())){
								Toast.makeText(mActivity, "检查更新失败，请稍后重试", Toast.LENGTH_SHORT).show();
							}
							if(result.getDesc()==null)
							{
					
								//表示当前版本为最新版本
								final  Dialog dialog=new Dialog(
										 mActivity,
										 R.style.dialog
										);
								View view=mActivity.getLayoutInflater().inflate(
										R.layout.dialog_version,
										null
										);
								dialog.setContentView(view);
								dialog.setCanceledOnTouchOutside(true);
								dialog.show();
								Button queding=(Button)view.findViewById(R.id.queding);
								queding.setOnClickListener(new OnClickListener() {
									
									@Override
									public void onClick(View v) {
										// TODO Auto-generated method stub
										dialog.dismiss();
									}
								});
							}else
							{
								intent.setClass(mActivity, DownloadDialog.class);
								intent.putExtra("version", result.getCurversion());
								intent.putExtra("desc",result.getDesc());
								intent.putExtra("link", result.getLink());
								startActivity(intent);
							}
							
						}
			});
		}

	}

}
