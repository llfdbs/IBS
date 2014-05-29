package com.victop.ibs.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.victop.ibs.activity.R;
import com.victop.ibs.bean.TagBean;

public class Tag_GirdViewAdapter extends BaseAdapter {
	private Context context;
	private List<TagBean> list;
	private ViewHolder viewHolder;
	private int temp;
	private int int_temp = 0;

	public Tag_GirdViewAdapter(Context context, List<TagBean> list, int temp) {
		this.context = context;
		this.list = list;
		this.temp = temp;
	}

	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public boolean isEnabled(int position) {
		// TODO Auto-generated method stub
		if (list.get(position).getLablename().length() == 1)// 如果是字母索�?
			return false;// 表示不能点击
		return super.isEnabled(position);
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		String item = list.get(position).getLablename();

		if (convertView == null) {
			convertView = LayoutInflater.from(context).inflate(
					R.layout.griditem_tag, null);
			viewHolder = new ViewHolder();
			viewHolder.itemTv = (TextView) convertView
					.findViewById(R.id.itemTv);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}

		String rr = list.get(position).getLablename();
		byte[] b = rr.getBytes();
		if (b.length > 12) {
			rr = rr.substring(0, 4) + "..";
		}

		int last_temp = list.get(position).getTag();
		if (last_temp == 1) {
			viewHolder.itemTv.setBackgroundDrawable(context.getResources()
					.getDrawable(R.drawable.tag_et1));
			viewHolder.itemTv.setTextColor(0Xffffffff);
		} else {
			viewHolder.itemTv.setBackgroundDrawable(context.getResources()
					.getDrawable(R.drawable.tag_et));
			viewHolder.itemTv.setTextColor(0Xff000000);
		}
//		viewHolder.itemTv.setPadding(1, 1, 1, 1);
//		viewHolder.itemTv.setGravity(Gravity.CENTER);

	 

		viewHolder.itemTv.setText(rr);

		return convertView;
	}

	private class ViewHolder {
		private TextView indexTv;
		private TextView itemTv;

	}

}
