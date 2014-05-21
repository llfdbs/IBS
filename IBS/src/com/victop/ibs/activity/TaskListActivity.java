package com.victop.ibs.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

import com.victop.ibs.adapter.TaskListAdapter;
import com.victop.ibs.app.IBSApplication;
import com.victop.ibs.base.ActivityBase;
import com.victop.ibs.bean.GetTaskBean;
import com.victop.ibs.handler.GetTaskHandler;
import com.victop.ibs.handler.HomeHandler;
import com.victop.ibs.presenter.GetTaskPresenter;

/**
 * 接受的任务类 接受的任务业务逻辑
 * 
 * @author vv
 * 
 */
public class TaskListActivity extends ActivityBase {
	private RadioGroup radiogroup_task;
	private ListView mListView;
	private TaskListAdapter adapter;
	private List<Map<String, String>> listData = new ArrayList<Map<String, String>>();
	private ActionBar actionBar;// 导航栏
	private MenuItem search, add, save;// 搜索,添加，保存按钮
	private GetTaskHandler taskHandler;//网络请求获取数据handler
	List<GetTaskBean> task_list;
	List<GetTaskBean> task_unlist;
	List<GetTaskBean> task_filist;
	private String status="0";
	Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			switch (msg.what) {
			case 0:
				task_list = (List<GetTaskBean>) msg.obj;
				adapter = new TaskListAdapter(TaskListActivity.this, task_list);
				mListView.setAdapter(adapter);
				adapter.notifyDataSetChanged();
				break;
			}
			super.handleMessage(msg);
		}

	};
	Handler handler_unfinish = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			switch (msg.what) {
			case 0:
				task_unlist = (List<GetTaskBean>) msg.obj;
				adapter = new TaskListAdapter(TaskListActivity.this, task_unlist);
				mListView.setAdapter(adapter);
				adapter.notifyDataSetChanged();
				break;
			}
			super.handleMessage(msg);
		}
	};
	Handler handler_finish = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			switch (msg.what) {
			case 0:
				task_filist = (List<GetTaskBean>) msg.obj;
				 
						adapter = new TaskListAdapter(TaskListActivity.this, task_filist);
				mListView.setAdapter(adapter);
				adapter.notifyDataSetChanged();
				break;
			}
			super.handleMessage(msg);
		}
	};

	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);

		setContentView(R.layout.tasklist);
		IBSApplication.getInstance().addActivity(this);

		initViews();
		initHandler(handler);
		initData();
		initListeners();

	}

	@Override
	protected void initData() {
		// TODO Auto-generated method stub
		GetTaskPresenter gettask = new GetTaskPresenter();
		gettask.getInitData(taskHandler, null);
		// task_list = taskHandler.getmGetTaskBean();
		task_filist = new ArrayList<GetTaskBean>();
		task_unlist = new ArrayList<GetTaskBean>();

//		adapter = new TaskListAdapter(TaskListActivity.this, task_list);
//		mListView.setAdapter(adapter);
	}

	private void initHandler(Handler handler) {
		taskHandler = new GetTaskHandler(TaskListActivity.this, handler);
	}

 

	@Override
	protected void initViews() {
		// TODO Auto-generated method stub
		actionBar = getSupportActionBar();
		actionBar.setTitle(getResources().getString(R.string.receivedtask)
				+ "(" + 10 + ")");
		actionBar.setHomeButtonEnabled(true);
		actionBar.setIcon(R.drawable.btn_back);
		radiogroup_task = (RadioGroup) findViewById(R.id.radiogroup_task);
		mListView = (ListView) findViewById(R.id.taskList);

	}

	@Override
	protected void initListeners() {
		// TODO Auto-generated method stub

		radiogroup_task.setOnCheckedChangeListener(mOnChecked);
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
			case R.id.rbn_taskall:

				status="0";
				initHandler(handler);
				GetTaskPresenter gettasks = new GetTaskPresenter();
				gettasks.getInitData(taskHandler, null);
			

				break;
			case R.id.rbn_taskunfinish:
				status="1";
				initHandler(handler_unfinish);
				GetTaskPresenter gettask1 = new GetTaskPresenter();
				gettask1.getInitData(taskHandler, "1");
//				adapter = new TaskListAdapter(TaskListActivity.this, listData);
//				mListView.setAdapter(adapter);
//				adapter.notifyDataSetChanged();

				break;
			case R.id.rbn_taskfinished:
				status="2";
				initHandler(handler_finish);
				GetTaskPresenter gettask2 = new GetTaskPresenter();
				gettask2.getInitData(taskHandler, "2");
//				adapter = new TaskListAdapter(TaskListActivity.this, listData);
//				mListView.setAdapter(adapter);
//				adapter.notifyDataSetChanged();

				break;
			default:
				;

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
			}else if(status.equals("1")){
				bundle.putString("statue", task_unlist.get(arg2).getTaskstatus());
			}else{
				bundle.putString("statue", task_filist.get(arg2).getTaskstatus());
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
