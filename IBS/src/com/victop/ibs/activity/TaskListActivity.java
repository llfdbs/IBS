package com.victop.ibs.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

import com.victop.ibs.adapter.TaskListAdapter;
import com.victop.ibs.app.ibsApplication;
import com.victop.ibs.base.ActivityBase;

public class TaskListActivity extends ActivityBase {
	private Button btn_search,btn_addtask;
	private RadioGroup radiogroup_task;
	private ListView mListView;
	private TaskListAdapter adapter;
	private List<Map<String,String>> listData = new ArrayList<Map<String,String>>();
	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		
		setContentView(R.layout.tasklist);
		ibsApplication.getInstance().addActivity(this);
		
		initViews();
		initData();
		initListeners();
		
	}
	@Override
	protected void initData() {
		// TODO Auto-generated method stub
      setData();
      adapter = new TaskListAdapter(TaskListActivity.this, listData);
      mListView.setAdapter(adapter);
	}
  public void setData(){
	  Map<String,String> map ;
	  for(int i=0;i<10;i++){
		  map = new HashMap<String, String>();
		  map.put("title", "IBS素材搜集流程及完成流程下发任务流程"+i);
		  map.put("tasknumber","121215663");
		  map.put("statue", "0"+i);
		  map.put("deadline", "2015-10-10");
		  map.put("type","紧急");
		  listData.add(map);
	  }
	 
  }
  public void setData1(){
	  Map<String,String> map ;
	  for(int i=0;i<10;i++){
		  map = new HashMap<String, String>();
		  map.put("title", "IT素材搜集流发任务流程"+i);
		  map.put("tasknumber","121215663");
		  map.put("statue", "0"+0);
		  map.put("deadline", "2015-10-10");
		  map.put("type","紧急");
		  listData.add(map);
	  }
	 
  }
  public void setData2(){
	  Map<String,String> map ;
	  for(int i=0;i<10;i++){
		  map = new HashMap<String, String>();
		  map.put("title", "WEb素材搜集流程及发任务流程"+i);
		  map.put("tasknumber","121215663");
		  map.put("statue", "0"+1);
		  map.put("deadline", "2015-10-10");
		  map.put("type","紧急");
		  listData.add(map);
	  }
	 
  }
	@Override
	protected void initViews() {
		// TODO Auto-generated method stub
	btn_search = (Button)findViewById(R.id.btn_search);
	btn_addtask = (Button)findViewById(R.id.btn_addtask);
	radiogroup_task = (RadioGroup)findViewById(R.id.radiogroup_task);
    mListView = (ListView)findViewById(R.id.taskList);
     
	}

	@Override
	protected void initListeners() {
		// TODO Auto-generated method stub
      btn_search.setOnClickListener(mOnClick);
      btn_addtask.setOnClickListener(mOnClick);
      radiogroup_task.setOnCheckedChangeListener(mOnChecked);
	}
	//控件点击事件
	OnClickListener mOnClick = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.btn_search:
			break;
		case R.id.btn_addtask:
			break;
		default:;
		}	
		}
	};
	OnCheckedChangeListener mOnChecked = new OnCheckedChangeListener() {
		
		@Override
		public void onCheckedChanged(RadioGroup group, int checkedId) {
			// TODO Auto-generated method stub
			switch(checkedId){
			case R.id.rbn_taskall:
				
				listData.clear();
				setData();
				 adapter = new TaskListAdapter(TaskListActivity.this, listData);
			     mListView.setAdapter(adapter);
			     adapter.notifyDataSetChanged();
				
				break;
			case R.id.rbn_taskunfinish:
				listData.clear();
				setData1();
				 adapter = new TaskListAdapter(TaskListActivity.this, listData);
			     mListView.setAdapter(adapter);
			     adapter.notifyDataSetChanged();
				
				break;
			case R.id.rbn_taskfinished:
				listData.clear();
				setData2();
				 adapter = new TaskListAdapter(TaskListActivity.this, listData);
			     mListView.setAdapter(adapter);
			     adapter.notifyDataSetChanged();
				
				break;
			default:;
			
			}
		}
	};

}
