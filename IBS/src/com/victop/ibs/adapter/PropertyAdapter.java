package com.victop.ibs.adapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.victop.ibs.activity.R;
import com.victop.ibs.bean.PropertyBean;

public class PropertyAdapter extends BaseAdapter {
	private List<PropertyBean> list;
	private Context context;
	private LayoutInflater layoutInflater;
	public static HashMap<Integer, Boolean> isSelected;
	public static HashMap<Integer, Boolean> isSelectedCount = new HashMap<Integer, Boolean>();
	public static List<PropertyBean> isSelectedName = new ArrayList<PropertyBean>();

	public PropertyAdapter(Context context, List<PropertyBean> list) {
		this.context = context;
		this.list = list;
		this.layoutInflater = LayoutInflater.from(context);
		isSelectedName.clear();
		init();
	}

	private void init() {

		isSelected = new HashMap<Integer, Boolean>();
		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				isSelected.put(i, false);
			}
		}
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		Holder holder;
		if (convertView == null) {
			holder = new Holder();
			convertView = layoutInflater.inflate(
					R.layout.taskallocation_listitem, null);
			holder.img_employeehead = (ImageView) convertView
					.findViewById(R.id.img_employeehead);
			holder.tv_employeename = (TextView) convertView
					.findViewById(R.id.tv_employeename);
			holder.ckb_choise = (CheckBox) convertView
					.findViewById(R.id.ckb_choise);
			convertView.setTag(holder);
		} else {
			holder = (Holder) convertView.getTag();
		}
		holder.tv_employeename
				.setText(list.get(position).getNaturedetailname());
		holder.ckb_choise
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					@Override
					public void onCheckedChanged(CompoundButton buttonView,
							boolean isChecked) {
						// TODO Auto-generated method stub
						isSelected.put(position, isChecked);

						if (isChecked) {
							isSelectedCount.put(position, isChecked);
							isSelectedName.add(list.get(position));
						} else {
							isSelectedCount.remove(position);
							isSelectedName.remove(list.get(position));
						}

					}
				});
		holder.ckb_choise.setChecked(isSelected.get(position));
		holder.img_employeehead.setVisibility(View.GONE);

		return convertView;
	}

	class Holder {
		ImageView img_employeehead;
		TextView tv_employeename;
		CheckBox ckb_choise;

	}

}