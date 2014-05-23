package com.victop.ibs.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.RadioGroup.OnCheckedChangeListener;

import com.victop.ibs.adapter.SendTaskListAdapter;
import com.victop.ibs.adapter.TaskListAdapter;
import com.victop.ibs.app.IBSApplication;
import com.victop.ibs.base.ActivityBase;
import com.victop.ibs.bean.GetTaskBean;
import com.victop.ibs.bean.SendTaskBean;
import com.victop.ibs.handler.GetTaskHandler;
import com.victop.ibs.handler.SendTaskHandler;
import com.victop.ibs.presenter.SendTaskPresenter;

/**
 * 发送的任务类 发送的任务业务逻辑
 * 
 * @author vv
 * 
 */
public class SendedTaskListActivity extends ActivityBase {
	
	private RadioGroup radiogroup_tasksended;
	private ListView mListView;
	private SendTaskListAdapter adapter;
	//private List<Map<String, String>> listData = new ArrayList<Map<String, String>>();
	private ActionBar actionBar;//导航栏
	private MenuItem search, add, save;//搜索,添加，保存按钮
	private List<SendTaskBean> task_list = new ArrayList<SendTaskBean>();//全部
	private List<SendTaskBean> task_unfinishList = new ArrayList<SendTaskBean>();//未完成
	private List<SendTaskBean> task_finishList = new ArrayList<SendTaskBean>();//已完成
	private List<SendTaskBean> task_unsendList = new ArrayList<SendTaskBean>();//未发放
	private String status="0";
	private SendTaskHandler sendTaskHandler;
	Handler handler = new Handler(){

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			switch(msg.what){
			case 0:
				task_list = (List<SendTaskBean>)msg.obj;
				adapter = new SendTaskListAdapter(SendedTaskListActivity.this, task_list);
				mListView.setAdapter(adapter);
				adapter.notifyDataSetChanged();
				
				break;
			}
			super.handleMessage(msg);
		}
		
	};
	Handler handler_unfinish = new Handler(){

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			switch(msg.what){
			case 0:
				task_unfinishList = (List<SendTaskBean>)msg.obj;
				adapter = new SendTaskListAdapter(SendedTaskListActivity.this, task_unfinishList);
				mListView.setAdapter(adapter);
				adapter.notifyDataSetChanged();
				break;
			}
			super.handleMessage(msg);
		}
		
	};
	Handler handler_finish = new Handler(){

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			switch(msg.what){
			case 0:
				task_finishList = (List<SendTaskBean>)msg.obj;
				adapter = new SendTaskListAdapter(SendedTaskListActivity.this, task_finishList);
				mListView.setAdapter(adapter);
				adapter.notifyDataSetChanged();
				break;
			}
			super.handleMessage(msg);
		}
		
	};
	Handler handler_unsend = new Handler(){

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			switch(msg.what){
			case 0:
				if(null!=msg.obj){
				task_unsendList = (List<SendTaskBean>)msg.obj;
				adapter = new SendTaskListAdapter(SendedTaskListActivity.this, task_unsendList);
				mListView.setAdapter(adapter);
				adapter.notifyDataSetChanged();
				}
				break;
			}
			super.handleMessage(msg);
		}
		
	};
	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);

		setContentView(R.layout.sendedtasklist);
		IBSApplication.getInstance().addActivity(this);

		initViews();
		initHandler(handler);
		initData();
		initListeners();

	}
    
	@Override
	protected void initData() {
		// TODO Auto-generated method stub
	   SendTaskPresenter sendTaskPresenter =  new SendTaskPresenter();
	   sendTaskPresenter.getInitData(sendTaskHandler,null);

	}

	@Override
	protected void initViews() {
		// TODO Auto-generated method stub
		actionBar = getSupportActionBar();
		actionBar.setTitle(getResources().getString(R.string.sendedtask)+"("+10+")");
		actionBar.setHomeButtonEnabled(true);
		actionBar.setIcon(R.drawable.btn_back);
		radiogroup_tasksended = (RadioGroup) findViewById(R.id.radiogroup_tasksended);
		mListView = (ListView) findViewById(R.id.sendedtaskList);
	}
	private void initHandler(Handler handler) {
		sendTaskHandler = new SendTaskHandler(SendedTaskListActivity.this, handler);
	}
	@Override
	protected void initListeners() {
		// TODO Auto-generated method stub
		
		radiogroup_tasksended.setOnCheckedChangeListener(mOnChecked);
		mListView.setOnItemClickListener(mOnItemClick);
	}

	
	// 控件点击事件
	OnClickListener mOnClick = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			
		}
	};
	OnCheckedChangeListener mOnChecked = new OnCheckedChangeListener() {

		@Override
		public void onCheckedChanged(RadioGroup group, int checkedId) {
			// TODO Auto-generated method stub
			switch (checkedId) {
			case R.id.rbn_sendedtask_all:
				 status="0";
				initHandler(handler);
				SendTaskPresenter sendTaskPresenter =  new SendTaskPresenter();
				sendTaskPresenter.getInitData(sendTaskHandler,null);

				break;
			case R.id.rbn_sendedtask_unfinish:
				 status="1";
				initHandler(handler_unfinish);
				SendTaskPresenter sendTaskPresenter1 =  new SendTaskPresenter();
				sendTaskPresenter1.getInitData(sendTaskHandler,"1");

				break;
			case R.id.rbn_sendedtask_finished:
				 status="2";
				initHandler(handler_finish);
				SendTaskPresenter sendTaskPresenter2 =  new SendTaskPresenter();
				sendTaskPresenter2.getInitData(sendTaskHandler,"2");

				break;
			case R.id.rbn_sendedtask_unsend:
				status="3";
				initHandler(handler_unsend);
				SendTaskPresenter sendTaskPresenter3 =  new SendTaskPresenter();
				sendTaskPresenter3.getInitData(sendTaskHandler,"0");
				break;
			default:
				break;
			}
		}
	};
	OnItemClickListener mOnItemClick = new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			// TODO Auto-generated method stub
			Bundle bundle = new Bundle();
			if(status.equals("0")){
				bundle.putString("statue", task_list.get(arg2).getTaskstatus());
				bundle.putString("taskid", task_list.get(arg2).getTaskid());
			}else if(status.equals("1")){
				bundle.putString("statue", task_unfinishList.get(arg2).getTaskstatus());
				bundle.putString("taskid", task_list.get(arg2).getTaskid());
			}else if(status.equals("2")){
				bundle.putString("statue", task_finishList.get(arg2).getTaskstatus());
				bundle.putString("taskid", task_list.get(arg2).getTaskid());
			}else{
				
				bundle.putString("statue", task_unsendList.get(arg2).getTaskstatus());
				bundle.putString("taskid", task_list.get(arg2).getTaskid());
				
			}
			openActivity(TaskDetailActivity.class, bundle);
		}
	};
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);

		search = menu.findItem(R.id.search);
		add = menu.findItem(R.id.add);
		save = menu.findItem(R.id.save);
		search.setVisible(true);
		add.setVisible(true);
		save.setVisible(false);
		

		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		switch (item.getItemId()) {
		case android.R.id.home:
			
			finish();
			
			break;
		case R.id.search:
			openActivity(MaterialSearchActivity.class, null);
			break;
		case R.id.add:
			openActivity(AddTaskActivity.class, null);
			break;
		
		}

		return super.onOptionsItemSelected(item);
	}
}
