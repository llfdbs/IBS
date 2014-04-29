package com.victop.ibs.adapter;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.victop.ibs.activity.R;
import com.victop.ibs.activity.TagActivity;
import com.victop.ibs.bean.SortModel;
import com.victop.ibs.util.Container;

public class Tag_GirdViewAdapter extends BaseAdapter {
	private Context context;
	private List<SortModel> list;
	private ViewHolder viewHolder;
	private int temp;
	private int int_temp = 0;

	public Tag_GirdViewAdapter(Context context, List<SortModel> list, int temp) {
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
		if (list.get(position).getName().length() == 1)// 如果是字母索�?
			return false;// 表示不能点击
		return super.isEnabled(position);
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		String item = list.get(position).getName();

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

		String rr = list.get(position).getName();
		byte[] b = rr.getBytes();
		if (b.length > 12) {
			rr = rr.substring(0, 4) + "..";
		}

		int last_temp = list.get(position).getTag();
		if (last_temp == 1) {
			viewHolder.itemTv.setBackgroundDrawable(context.getResources()
					.getDrawable(R.drawable.usebutton2));
		} else {
			viewHolder.itemTv.setBackgroundDrawable(context.getResources()
					.getDrawable(R.drawable.usebutton1));
		}
		viewHolder.itemTv.setPadding(1, 1, 1, 1);
		viewHolder.itemTv.setGravity(Gravity.CENTER);

		// viewHolder.itemTv.setOnClickListener(new OnClickListener() {
		//
		// @Override
		// public void onClick(View v) {
		// // TODO Auto-generated method stub
		//
		// int_temp = int_temp == 0 ? 1 : 0;
		// if (int_temp == 1) {
		// viewHolder.itemTv.setBackgroundDrawable(context
		// .getResources().getDrawable(R.drawable.usebutton2));
		// } else {
		// viewHolder.itemTv.setBackgroundDrawable(context
		// .getResources().getDrawable(R.drawable.usebutton1));
		// }
		// viewHolder.itemTv.setPadding(1, 1, 1, 1);
		// viewHolder.itemTv.setGravity(Gravity.CENTER);
		//
		// HashMap<Integer, SortModel> hashMap = Container.getInstance()
		// .getTaghashSortModel();
		//
		// int m_temp = temp + position;
		// System.out.println(m_temp + "---------");
		// if (int_temp == 1) {
		// Container.getInstance().setTaghashSortModle(m_temp,
		// list.get(m_temp));
		// list.get(m_temp).setTag(1);
		// } else {
		// if (null != hashMap.get(m_temp))
		// hashMap.remove(m_temp);
		// list.get(m_temp).setTag(0);
		// }
		//
		// if (null != hashMap && hashMap.size() != 0) {
		// String name = "标签（选中" + hashMap.size() + "个）";
		// ((TagActivity) context).settitleName(name);
		// Iterator iter = hashMap.keySet().iterator();
		// while (iter.hasNext()) {
		// Object key = iter.next();
		// Object val = hashMap.get(key);
		// System.out.println(key + ((SortModel) val).getName());
		// }
		// } else {
		// ((TagActivity) context).settitleName("标签");
		// }
		//
		// }
		// });

		viewHolder.itemTv.setText(rr);

		return convertView;
	}

	private class ViewHolder {
		private TextView indexTv;
		private TextView itemTv;

	}

}
