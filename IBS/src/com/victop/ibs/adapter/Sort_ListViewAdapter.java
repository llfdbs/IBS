package com.victop.ibs.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.victop.ibs.activity.R;
import com.victop.ibs.bean.SortModel;

public class Sort_ListViewAdapter extends BaseAdapter {
	private Context context;
	private List<SortModel> list;
	private ViewHolder viewHolder;
	private int tag;
	final String[] data = { "erp", "互联网", "互联网", "erp", "互联网", "互联网" 
			 };

	public Sort_ListViewAdapter(Context context, List<SortModel> list, int tag) {
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
		if (tag == 0) {
			if (convertView == null) {
				convertView = LayoutInflater.from(context).inflate(
						R.layout.sortlistview_item, null);
				viewHolder = new ViewHolder();
				viewHolder.tv_title = (TextView) convertView
						.findViewById(R.id.tv_title);
				viewHolder.tv_type = (TextView) convertView
						.findViewById(R.id.tv_type);
				viewHolder.cb = (CheckBox) convertView
						.findViewById(R.id.checkBox1);

				convertView.setTag(viewHolder);
			} else {
				viewHolder = (ViewHolder) convertView.getTag();
			}
			viewHolder.cb.setVisibility(View.GONE);
			viewHolder.tv_title.setText(data[arg0]);
		} else if (tag == 1) {
			if (convertView == null) {
				convertView = LayoutInflater.from(context).inflate(
						R.layout.sortlistview_item, null);
				viewHolder = new ViewHolder();
				viewHolder.tv_title = (TextView) convertView
						.findViewById(R.id.tv_title);
				viewHolder.tv_type = (TextView) convertView
						.findViewById(R.id.tv_type);
				viewHolder.cb = (CheckBox) convertView
						.findViewById(R.id.checkBox1);

				convertView.setTag(viewHolder);
			} else {
				viewHolder = (ViewHolder) convertView.getTag();
			}
			viewHolder.tv_title.setText(data[arg0]);
		}
		return convertView;
	}

	class ViewHolder {
		CheckBox cb;
		TextView tv_title, tv_type;
	}

}
