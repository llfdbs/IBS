package com.victop.ibs.adapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.victop.ibs.activity.ImgFileListActivity;
import com.victop.ibs.activity.R;
import com.victop.ibs.bean.SortModel;
import com.victop.ibs.util.Constants;

public class MaterialAdd_girdViewAdapter extends BaseAdapter {
	private Context context;
	// private ArrayList<String> list;
	private ViewHolder viewHolder;
	private int temp;
	private int int_temp = 0;
	// =new ArrayList<Map<String, String>>()
	List<Map<String, String>> list;

	public MaterialAdd_girdViewAdapter(Context context,
			List<Map<String, String>> list, int temp) {
		this.context = context;
		this.temp = temp;
		if (list != null)
			this.list = list;
		else
			this.list = new ArrayList<Map<String, String>>();
	}

	@Override
	public int getCount() {
		return list.size() + 1;
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
	public View getView(final int position, View convertView, ViewGroup parent) {

		if (convertView == null) {
			convertView = LayoutInflater.from(context).inflate(
					R.layout.materialadd_griditem, null);
			viewHolder = new ViewHolder();
			viewHolder.iv_material = (ImageView) convertView
					.findViewById(R.id.img);

			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}

		if (list.size() != position) {
			String ff = list.get(position).keySet().iterator().next();
			Constants.imageLoader.displayImage("file://" + ff,
					viewHolder.iv_material, Constants.image_display_options,
					null);
		} else {
			viewHolder.iv_material
					.setBackgroundResource(R.drawable.addproperty);
			viewHolder.iv_material.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					Intent _Intent = new Intent(context,
							ImgFileListActivity.class);
					context.startActivity(_Intent);
				}
			});
		}

		// viewHolder.itemTv.setText(rr);

		return convertView;
	}

	private class ViewHolder {
		private ImageView iv_material;

	}

}
