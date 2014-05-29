package com.victop.ibs.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.victop.ibs.activity.R;
import com.victop.ibs.bean.TaskBean;

/**
 * 新增素材的个人任务列表
 * 
 * @author Administrator
 * 
 */
public class TaskSortAdapter extends BaseAdapter {
	private Context context;
	private LayoutInflater layoutInflater;
	// private List<Map<String,String>> list;
	private List<TaskBean> list;

	public TaskSortAdapter(Context context, List<TaskBean> list) {
		this.context = context;
		this.layoutInflater = LayoutInflater.from(context);
		this.list = list;
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
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		Holder holder;
		if (convertView == null) {
			holder = new Holder();
			convertView = layoutInflater.inflate(R.layout.tasklist_item, null);
			holder.tv_receivedtask_title = (TextView) convertView
					.findViewById(R.id.tv_receivedtask_title);
			holder.tv_tasknumber = (TextView) convertView
					.findViewById(R.id.tv_tasknumber);
			holder.tv_taskstatue = (TextView) convertView
					.findViewById(R.id.tv_taskstatue);
			holder.tv_deadline = (TextView) convertView
					.findViewById(R.id.tv_deadline);
			holder.tv_tasktype = (TextView) convertView
					.findViewById(R.id.tv_tasktype);
			convertView.setTag(holder);
		} else {
			holder = (Holder) convertView.getTag();
		}
		holder.tv_receivedtask_title.setText(list.get(position).getTaskname());
		holder.tv_tasknumber.setText(list.get(position).getTaskcode());
		holder.tv_taskstatue.setText(list.get(position).getTaskstatus());
		if (list.get(position).getTaskstatus().equals("未完成")) {
			holder.tv_taskstatue.setTextColor(0xff53CCC5);
			holder.tv_taskstatue.setText("未完成");
		} else if (list.get(position).getTaskstatus().equals("已完成")) {
			holder.tv_taskstatue.setTextColor(0xffF8524B);
			holder.tv_taskstatue.setText("已完成");
		}
		holder.tv_deadline.setText(list.get(position).getDuedate());
		holder.tv_tasktype.setText(list.get(position).getTasklevel());
		return convertView;
	}

	class Holder {
		TextView tv_receivedtask_title, tv_tasknumber, tv_taskstatue,
				tv_deadline, tv_tasktype;
	}
}
