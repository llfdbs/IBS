package com.victop.ibs.adapter;

import java.util.List;
import java.util.Map;

import com.victop.ibs.activity.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class TaskDetail_MaterialAdapter extends BaseAdapter {
  private Context context;
  private List<Map<String,String>> list;
  private LayoutInflater layoutInflater;
  private String tag;
    public TaskDetail_MaterialAdapter(Context context,List<Map<String,String>> list,String tag){
    	this.context = context;
    	this.list = list;
    	layoutInflater = LayoutInflater.from(context);
    	this.tag = tag;
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
		Holder holder ;
		if(convertView==null){
			holder = new Holder();
			convertView = layoutInflater.inflate(R.layout.taskdetail_materiallistitem,null);
			holder.taskdetail_title = (TextView)convertView.findViewById(R.id.taskdetail_materialtitle);
			holder.taskdetail_object = (TextView)convertView.findViewById(R.id.taskdetail_object);
			holder.taskdetail_committime = (TextView)convertView.findViewById(R.id.taskdetail_committime);
			holder.img_taskmaterial_head = (ImageView)convertView.findViewById(R.id.img_taskmaterial_head);
			holder.img_checkstatue = (ImageView)convertView.findViewById(R.id.img_checkstatus);
			convertView.setTag(holder);
		}else{
			holder = (Holder) convertView.getTag();
		}
		holder.taskdetail_title.setText(list.get(position).get("title"));
		holder.taskdetail_object.setText(list.get(position).get("allocationobj"));
		holder.taskdetail_committime.setText(list.get(position).get("committime"));
		if(tag.equals("00")){
			holder.img_checkstatue.setVisibility(View.GONE);
		}else{
			holder.img_checkstatue.setVisibility(View.VISIBLE);
		if(list.get(position).get("checkstatue").equals("00")){
			holder.img_checkstatue.setImageResource(R.drawable.img_nopass);
			
		}else{
			
			holder.img_checkstatue.setImageResource(R.drawable.img_pass);
		}
		}
		return convertView;
	}
	
	class Holder{
		TextView taskdetail_title,taskdetail_object,taskdetail_committime;
		ImageView img_taskmaterial_head,img_checkstatue;
	}
  
}
