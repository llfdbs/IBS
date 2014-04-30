package com.victop.ibs.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.RadioGroup.OnCheckedChangeListener;

import com.victop.ibs.adapter.TaskListAdapter;
import com.victop.ibs.app.ibsApplication;
import com.victop.ibs.base.ActivityBase;
/**
 * 发送的任务类 发送的任务业务逻辑
 * 
 * @author vv
 * 
 */
public class SendedTaskListActivity extends ActivityBase {
	private Button btn_tasksend_search,btn_tasksend_addtask;
	private RadioGroup radiogroup_tasksended;
	private ListView mListView;
	private TaskListAdapter adapter;
	private List<Map<String,String>> listData = new ArrayList<Map<String,String>>();
	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		
		setContentView(R.layout.sendedtasklist);
		ibsApplication.getInstance().addActivity(this);
		
		initViews();
		initData();
		initListeners();
		
	}
	@Override
	protected void initData() {
		// TODO Auto-generated method stub
		setData();
	      adapter = new TaskListAdapter(SendedTaskListActivity.this, listData);
	      mListView.setAdapter(adapter);
	}

	@Override
	protected void initViews() {
		// TODO Auto-generated method stub
		btn_tasksend_search = (Button)findViewById(R.id.btn_tasksend_search);
		btn_tasksend_addtask = (Button)findViewById(R.id.btn_tasksend_addtask);
		radiogroup_tasksended = (RadioGroup)findViewById(R.id.radiogroup_tasksended);
	    mListView = (ListView)findViewById(R.id.sendedtaskList);
	}

	@Override
	protected void initListeners() {
		// TODO Auto-generated method stub
		btn_tasksend_search.setOnClickListener(mOnClick);
		btn_tasksend_addtask.setOnClickListener(mOnClick);
		radiogroup_tasksended.setOnCheckedChangeListener(mOnChecked);
		mListView.setOnItemClickListener(mOnItemClick);
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
			  map.put("title", "IT素材搜集流程"+i);
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
			  map.put("title", "WEB素材搜集流程发任务流程"+i);
			  map.put("tasknumber","121215663");
			  map.put("statue", "0"+1);
			  map.put("deadline", "2015-10-10");
			  map.put("type","紧急");
			  listData.add(map);
		  }
		 
	  }
	//控件点击事件
		OnClickListener mOnClick = new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			switch(v.getId()){
			case R.id.btn_tasksend_search:
				break;
			case R.id.btn_tasksend_addtask:
				Intent intent_addtask = new Intent(SendedTaskListActivity.this,AddTaskActivity.class);
				startActivity(intent_addtask);
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
				case R.id.rbn_sendedtask_all:
					
					listData.clear();
					setData();
					 adapter = new TaskListAdapter(SendedTaskListActivity.this, listData);
				     mListView.setAdapter(adapter);
				     adapter.notifyDataSetChanged();
					
					break;
				case R.id.rbn_sendedtask_unfinish:
					listData.clear();
					setData1();
					 adapter = new TaskListAdapter(SendedTaskListActivity.this, listData);
				     mListView.setAdapter(adapter);
				     adapter.notifyDataSetChanged();
					
					break;
				case R.id.rbn_sendedtask_finished:
					listData.clear();
					setData2();
					 adapter = new TaskListAdapter(SendedTaskListActivity.this, listData);
				     mListView.setAdapter(adapter);
				     adapter.notifyDataSetChanged();
					
					break;
				case R.id.rbn_sendedtask_unsend:
					listData.clear();
					setData1();
					 adapter = new TaskListAdapter(SendedTaskListActivity.this, listData);
				     mListView.setAdapter(adapter);
				     adapter.notifyDataSetChanged();
					
					break;
				default:;
				
				}
			}
		};
		OnItemClickListener mOnItemClick = new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
	         Intent intent = new Intent(SendedTaskListActivity.this,TaskDetailActivity.class);
	         startActivity(intent);
			}
		};
}
