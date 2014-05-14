package com.victop.ibs.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Intent;
import android.os.Bundle;
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
	private ActionBar actionBar;//导航栏
	private MenuItem search, add, save;//搜索,添加，保存按钮
	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);

		setContentView(R.layout.tasklist);
		IBSApplication.getInstance().addActivity(this);

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

	public void setData() {
		Map<String, String> map;
		for (int i = 0; i < 10; i++) {
			map = new HashMap<String, String>();
			map.put("title", "IBS素材搜集流程及完成流程下发任务流程" + i);
			map.put("tasknumber", "121215663");
			map.put("statue", "0" + i);
			map.put("deadline", "2015-10-10");
			map.put("type", "紧急");
			listData.add(map);
		}

	}

	public void setData1() {
		Map<String, String> map;
		for (int i = 0; i < 10; i++) {
			map = new HashMap<String, String>();
			map.put("title", "IT素材搜集流发任务流程" + i);
			map.put("tasknumber", "121215663");
			map.put("statue", "0" + 0);
			map.put("deadline", "2015-10-10");
			map.put("type", "紧急");
			listData.add(map);
		}

	}

	public void setData2() {
		Map<String, String> map;
		for (int i = 0; i < 10; i++) {
			map = new HashMap<String, String>();
			map.put("title", "WEb素材搜集流程及发任务流程" + i);
			map.put("tasknumber", "121215663");
			map.put("statue", "0" + 1);
			map.put("deadline", "2015-10-10");
			map.put("type", "紧急");
			listData.add(map);
		}

	}

	@Override
	protected void initViews() {
		// TODO Auto-generated method stub
		actionBar = getSupportActionBar();
		actionBar.setTitle(getResources().getString(R.string.receivedtask)+"("+10+")");
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
			bundle.putString("statue",listData.get(arg2).get("statue"));
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
