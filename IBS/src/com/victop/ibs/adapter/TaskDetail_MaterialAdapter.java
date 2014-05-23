package com.victop.ibs.adapter;

import java.util.List;
import java.util.Map;

import com.victop.ibs.activity.R;
import com.victop.ibs.bean.TaskMaterialsBean;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class TaskDetail_MaterialAdapter extends BaseAdapter {
  private Context context;
  private List<TaskMaterialsBean> list;
  private LayoutInflater layoutInflater;
  private String tag;
    public TaskDetail_MaterialAdapter(Context context,List<TaskMaterialsBean> list,String tag){
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
		holder.taskdetail_title.setText(list.get(position).getMaterialmemo());
		holder.taskdetail_object.setText(list.get(position).getAddman());
		holder.taskdetail_committime.setText(list.get(position).getAdddate());
		if(tag.equals("1") || tag.equals("0")){//未完成任务,未发放任务
			holder.img_checkstatue.setVisibility(View.GONE);
		}else{ 
			holder.img_checkstatue.setVisibility(View.VISIBLE);
		if( list.get(position).getMaterialstatus().equals("1")){//素材未审核
			holder.img_checkstatue.setImageResource(R.drawable.img_nopass);	
		}else if( tag.equals("2")){//素材已审核
			
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
