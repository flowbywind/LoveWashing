package com.easylife.app_wash.widget;

import java.util.List;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.easylife.app_wash.R;
import com.sihehui.section_vo.vo.ListViewItemVO;
import android.content.Context;

public class ListItemEntityAdapter extends BaseAdapter {

	List<ListViewItemVO> list;
    Context context;
    ListItemAdapterVO vo;
    ListViewItemVO model;
	public ListItemEntityAdapter(Context context,List<ListViewItemVO> list) {
		this.list = list;
		this.context=context;
		System.out.println("run listitementityadapter()");
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		if (list == null) {
			return 0;
		}
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		if(convertView==null)
		{
			vo=new ListItemAdapterVO();
			convertView=View.inflate(context, R.layout.list_item, null);
		    TextView text =(TextView)convertView.findViewById(R.id.txt_name);
		    if(text!=null)
		    {
		    	vo.TxtView=text;
		    }
		    ImageView img=(ImageView)convertView.findViewById(R.id.img_icon);
		    if(img!=null)
		    {
		    	vo.ImgView=img;
		    }
		    convertView.setTag(vo);
		}
		else
		{
			vo=(ListItemAdapterVO)convertView.getTag();
		}
		if(list==null && list.size()<=position)
		{
			return null;
		}
		if(vo!=null)
		{
		    model=list.get(position);	
			if(vo.TxtView!=null)
			{
				vo.TxtView.setText(model.getTitle());
			}
			System.out.println("imageid");
			if(vo.ImgView!=null && model.getImageId()!=null && model.getImageId()!=0 )
			{
				vo.ImgView.setImageResource(model.getImageId());
			    vo.ImgView.setVisibility(View.VISIBLE);
			}
		}
		return convertView;
	}
	
	public final class ListItemAdapterVO
	{
	   public	TextView TxtView;
	   public 	ImageView ImgView;
	}

}
