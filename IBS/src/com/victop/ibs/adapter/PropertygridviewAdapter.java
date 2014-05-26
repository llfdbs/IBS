package com.victop.ibs.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.victop.ibs.activity.R;
import com.victop.ibs.bean.PropertyBean;

public class PropertygridviewAdapter extends BaseAdapter {
	private Context mContext;
	private int clickTemp = -1;
	private ViewHolder viewHolder;
	List<PropertyBean> data;
	public PropertygridviewAdapter(Context c,List<PropertyBean> data) {
		mContext = c;
		this.data = data;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return data.size();
	}

	public void setSeclection(int position) {
		clickTemp = position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup arg2) {
		if (convertView == null) {
			convertView = LayoutInflater.from(mContext).inflate(
					R.layout.property_griditem, null);
			viewHolder = new ViewHolder();
			viewHolder.btn_name = (TextView) convertView
					.findViewById(R.id.btn_name);

			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		viewHolder.btn_name.setText(data.get(position).getNaturedetailname());

		return convertView;
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

	class ViewHolder {

		TextView btn_name;
	}
}
