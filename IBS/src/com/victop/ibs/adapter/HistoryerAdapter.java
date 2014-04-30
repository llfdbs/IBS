package com.victop.ibs.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.victop.ibs.activity.R;
import com.victop.ibs.bean.SortModel;

public class HistoryerAdapter extends BaseAdapter {
	private Context context;
	private List<SortModel> list;
	private ViewHolder viewHolder;
	private int tag;
	final String[] data = { "erp", "互联网", "互联网", "erp", "互联网", "互联网", "erp",
			"互联网", "互联网", "erp", "互联网", "互联网", "erp", "互联网", "互联网" };

	public HistoryerAdapter(Context context, List<SortModel> list) {
		this.context = context;
		this.list = list;
		this.tag = tag;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return data.length;
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public View getView(int arg0, View convertView, ViewGroup arg2) {
		// TODO Auto-generated method stub

		if (convertView == null) {
			convertView = LayoutInflater.from(context).inflate(
					R.layout.historylistitem, null);
			viewHolder = new ViewHolder();
			viewHolder.iv_icon = (ImageView) convertView
					.findViewById(R.id.history_img);
			viewHolder.tv_name = (TextView) convertView
					.findViewById(R.id.othername);
			viewHolder.tv_info = (TextView) convertView
					.findViewById(R.id.otherinfo);
			viewHolder.tv_ver = (TextView) convertView
					.findViewById(R.id.h_version);
			viewHolder.tv_time = (TextView) convertView
					.findViewById(R.id.h_time);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		// viewHolder.cb.setVisibility(View.GONE);
		// viewHolder.tv_title.setText(data[arg0]);

		return convertView;
	}

	class ViewHolder {
		ImageView iv_icon;
		TextView tv_name, tv_info, tv_ver, tv_time;
	}

}
