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
import com.victop.ibs.bean.MaterialDetailHistoryBean;
import com.victop.ibs.bean.SortModel;

public class HistoryerAdapter extends BaseAdapter {
	private Context context;
	private ViewHolder viewHolder;
	private List<MaterialDetailHistoryBean> mMaterialHistoryList;
	
	public HistoryerAdapter(Context context,List<MaterialDetailHistoryBean> mMaterialHistoryList) {
		this.context = context;
	    this.mMaterialHistoryList = mMaterialHistoryList;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mMaterialHistoryList.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return mMaterialHistoryList.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup arg2) {
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
        viewHolder.tv_name.setText(mMaterialHistoryList.get(position).getModifyman());
        viewHolder.tv_ver.setText(mMaterialHistoryList.get(position).getVersioncode());
        viewHolder.tv_time.setText(mMaterialHistoryList.get(position).getModifydate());
		return convertView;
	}

	class ViewHolder {
		ImageView iv_icon;
		TextView tv_name, tv_info, tv_ver, tv_time;
	}

}
