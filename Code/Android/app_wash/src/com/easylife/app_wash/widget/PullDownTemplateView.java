package com.easylife.app_wash.widget;

import java.util.LinkedList;
import java.util.List;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.easylife.app_wash.R;
import com.easylife.app_wash.widget.PullDownView.OnPullDownListener;

public class PullDownTemplateView<T> extends LinearLayout implements
		OnPullDownListener {

	private static final String TAG = PullDownTemplateView.class.getName();

	public TextView emptyView;
	public Button refresh;
	// public FrameLayout myFramelayout;
	public LinearLayout mylinear;
	public PullDownView pullDownView;
	public int listSize = 15;
	public PullDownViewAdapter mAdapter;
	private List<T> dataList;

	private OnPullDownTemplateViewListener<T> onPullDownListener;
	private Context context;

	public PullDownTemplateView(Context context) {
		this(context, null);
		this.context = context;
	}

	public PullDownTemplateView(Context context, AttributeSet attrs) {
		super(context, attrs);
		dataList = new LinkedList<T>();
		this.context = context;
	}

	// String text;

	@Override
	protected void onFinishInflate() {
		emptyView = (TextView) findViewById(R.id.listisnull);
		refresh = (Button) findViewById(R.id.refresh);
		mylinear = (LinearLayout) findViewById(R.id.mylinear);
		// myFramelayout = (FrameLayout) findViewById(R.id.myframelayout);
		pullDownView = (PullDownView) findViewById(R.id.pull_down_view);
		pullDownView.setOnPullDownListener(this);
		pullDownView.enableAutoFetchMore(false, 0);
		mAdapter = new PullDownViewAdapter();
		pullDownView.getListView().setAdapter(mAdapter);
		pullDownView.getListView().setOnItemClickListener(
				new OnItemClickListener() {
					@Override
					public void onItemClick(AdapterView<?> parent, View view,
							int position, long id) {
						if (!pullDownView.isRefresh) {
							if (onPullDownListener != null) {
								onPullDownListener.onItemClick(parent, view,
										position, id, dataList);
							}
						}
					}
				});
		// text = emptyView.getText().toString();
		refresh.setOnClickListener(new CustomOnClickListener(context) {

			@Override
			public void doOnClick(View v) {
				// TODO Auto-generated method stub
				onRefresh();
			}
		});
	}

	public void setOnPullDownListener(OnPullDownListener listener) {
		pullDownView.setOnPullDownListener(listener);
	}

	public void setOnItemClickListener(OnItemClickListener listener) {
		pullDownView.getListView().setOnItemClickListener(listener);
	}

	public void setAdapter(BaseAdapter adapter) {
		pullDownView.getListView().setAdapter(adapter);
	}

	public void setPullDownTemplateViewListener(
			OnPullDownTemplateViewListener<T> listener) {
		this.onPullDownListener = listener;
	}

	public void load(List<T> data, Boolean hasNextPage) {
		dataList.clear();
		dataChanged(data, hasNextPage);
		pullDownView.notifyDidLoad();

	}

	public void refresh(List<T> data, String text, Boolean hasNextPage) {
		emptyView.setText(text + "");
		dataList.clear();
		dataChanged(data, hasNextPage);
		pullDownView.notifyDidRefresh();
	}

	// public void refresh(List<T> data) {
	// dataList.clear();
	// dataChanged(data);
	// pullDownView.notifyDidRefresh();
	// }

	public void more(List<T> data, Boolean hasNextPage) {
		dataChanged(data, hasNextPage);
		pullDownView.notifyDidMore();
	}

	public void clearData() {
		dataList.clear();
		mAdapter.notifyDataSetChanged();
	}

	public int getItemCount() {
		return dataList.size();
	}

	public List<T> getDataList() {
		return dataList;
	}

	public void dataTabChanged(List<T> data) {
		if (data != null) {
			dataList.clear();
			dataList.addAll(data);
		}
		if (data != null && data.size() >= listSize) {
			pullDownView.mFooterView.setVisibility(View.VISIBLE);
		} else {
			pullDownView.mFooterView.setVisibility(View.GONE);
		}
		mAdapter.notifyDataSetChanged();
		if (dataList.isEmpty() || dataList.size() == 0) {
			mylinear.setVisibility(View.VISIBLE);
			pullDownView.setVisibility(View.VISIBLE);
		} else {
			mylinear.setVisibility(View.GONE);
			pullDownView.setVisibility(View.VISIBLE);
		}
	}

	private void dataChanged(List<T> data) {
		if (data != null) {
			dataList.addAll(data);
		}
		if (data != null && data.size() >= listSize) {
			pullDownView.mFooterView.setVisibility(View.VISIBLE);
		} else {
			pullDownView.mFooterView.setVisibility(View.GONE);
		}
		mAdapter.notifyDataSetChanged();
		if (dataList.isEmpty() || dataList.size() == 0) {
			mylinear.setVisibility(View.VISIBLE);
			pullDownView.setVisibility(View.VISIBLE);
		} else {
			mylinear.setVisibility(View.GONE);
			pullDownView.setVisibility(View.VISIBLE);
		}
	}

	private void dataChanged(List<T> data, Boolean hasNextPage) {
		if (data != null) {
			dataList.addAll(data);
		}
		if (data != null && data.size() >= listSize) {
			pullDownView.mFooterView.setVisibility(View.VISIBLE);
		} else {
			pullDownView.mFooterView.setVisibility(View.GONE);
		}
		// if (hasNextPage == null) {
		// hasNextPage = false;
		// }
		// // data != null && data.size() >= listSize
		// if (hasNextPage) {
		// pullDownView.mFooterView.setVisibility(View.VISIBLE);
		// } else {
		// pullDownView.mFooterView.setVisibility(View.GONE);
		// }
		mAdapter.notifyDataSetChanged();
		if (dataList.isEmpty() || dataList.size() == 0) {
			mylinear.setVisibility(View.VISIBLE);
			pullDownView.setVisibility(View.VISIBLE);
		} else {
			mylinear.setVisibility(View.GONE);
			pullDownView.setVisibility(View.VISIBLE);
		}
	}

	public class PullDownViewAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			return dataList.size();
		}

		@Override
		public Object getItem(int position) {
			return dataList.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View view, ViewGroup viewGroup) {
			if (dataList == null || dataList.isEmpty()
					|| position >= dataList.size()
					|| dataList.get(position) == null) {
				Log.d(TAG + "$PullDownViewAdapter",
						"The dataList is empty or not found object by position["
								+ position + "].");
				return view;
			}
			if (onPullDownListener == null) {
				Log.d(TAG + "$PullDownViewAdapter",
						"The OnPullDownViewTemplateListener is empty.");
				return view;
			}
			return onPullDownListener.onRefreshItemView(position, view,
					viewGroup, dataList, pullDownView.getListView());
		}

	}

	public interface OnPullDownTemplateViewListener<T> extends
			OnPullDownListener {
		// View onRefreshItemView(int position, View view, ViewGroup viewGroup,
		// List<T> dataList);

		View onRefreshItemView(int position, View view, ViewGroup viewGroup,
				List<T> dataList, ListView listView);

		void onItemClick(AdapterView<?> parent, View view, int position,
				long id, List<T> dataList);
	}

	private Boolean isRefreshed = false;

	@Override
	public void onRefresh() {
		Log.d("zhangyaobin", "isRefreshed=" + isRefreshed);
		// if (isRefreshed) {
		// emptyView.setText(text + "");
		// isRefreshed = false;
		// } else {
		emptyView.setText("正在刷新数据,请稍后");
		// isRefreshed = true;
		// }
		if (onPullDownListener != null) {
			onPullDownListener.onRefresh();
		}
	}

	@Override
	public void onMore() {
		if (onPullDownListener != null) {
			onPullDownListener.onMore();
		}
	}
}
