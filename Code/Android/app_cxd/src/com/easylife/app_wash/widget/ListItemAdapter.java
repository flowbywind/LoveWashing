package com.easylife.app_wash.widget;


import com.easylife.app_wash.R;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.content.Context;
import java.util.List;
import java.util.Map;

public class ListItemAdapter extends BaseAdapter {

	Context con;
	ListItemAdapterVO vo;
	List<Map<String,Object>> list;
	public ListItemAdapter(Context context,List<Map<String,Object>> list) {
		this.con=context;
		this.list=list;
		// TODO Auto-generated constructor stub
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		if(list!=null)
		{
			return list.size();
		}
		return 0;
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
		if(convertView==null)
		{
			convertView=View.inflate(con, R.layout.list_item, null);
			vo=new ListItemAdapterVO();
			TextView txt=(TextView)convertView.findViewById(R.id.txt_name);
			if(txt!=null){
			  vo.txtView=txt;
			  convertView.setTag(vo);
			}
		}
		else
		{
			vo=(ListItemAdapterVO)convertView.getTag();
		}
		if(list==null || list.size()<position)
		{
			return null;
		}
	    Map<String,Object>	map =list.get(position);
	    Object objtxt =  map.get("text");
	    if(objtxt!=null && vo!=null && vo.txtView!=null)
	    {
	    	vo.txtView.setText(objtxt+"");
	    }
		return convertView;
		// TODO Auto-generated method stub
	}
	public final class ListItemAdapterVO {
		public    TextView  txtView;
	}

}
