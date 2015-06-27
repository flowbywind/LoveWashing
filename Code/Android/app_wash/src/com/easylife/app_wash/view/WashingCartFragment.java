package com.easylife.app_wash.view;

import java.util.List;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.easylife.app_wash.widget.BaseWashingFragment;
import com.sihehui.section_vo.vo.WashingVO;

public class WashingCartFragment extends BaseWashingFragment{

	@Override
	public View setContentView(LayoutInflater inflater, ViewGroup container) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		return super.onCreateView(inflater, container, savedInstanceState);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id, List<WashingVO> dataList) {
		// TODO Auto-generated method stub
		super.onItemClick(parent, view, position, id, dataList);
	}

}
