package com.victop.ibs.adapter;

import java.util.HashMap;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.victop.ibs.activity.R;
import com.victop.ibs.bean.SortBean;

public class Sort_ListViewAdapter extends BaseAdapter {
	private Context context;
	private List<SortBean> list;
	// private HashMap<String, List<SortBean>> list1;
	// private HashMap<String, HashMap<String, List<SortBean>>> list2;
	private ViewHolder viewHolder;
	private HashMap<String, String> tag;// 二级分类内容

	// final String[] data = { "erp", "互联网", "互联网", "erp", "互联网", "互联网" };

	public Sort_ListViewAdapter(Context context, List<SortBean> list,
			HashMap<String, String> tag) {
		this.context = context;
		this.list = list;
		this.tag = tag;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		// System.out.println(list.size());
		return list.size();

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
					R.layout.sortlistview_item, null);
			viewHolder = new ViewHolder();
			viewHolder.tv_title = (TextView) convertView
					.findViewById(R.id.tv_title);
			viewHolder.tv_type = (TextView) convertView
					.findViewById(R.id.tv_type);

			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		if (null != tag) {
			viewHolder.tv_type.setText(tag.get(list.get(arg0).getClassid()));
		}

		if (null != list)
			viewHolder.tv_title.setText(list.get(arg0).getClassname());

		return convertView;
	}

	class ViewHolder {
		// CheckBox cb;
		TextView tv_title, tv_type;
	}

}
