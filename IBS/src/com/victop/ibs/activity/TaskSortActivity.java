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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.victop.ibs.adapter.TaskListAdapter;
import com.victop.ibs.base.ActivityBase;
import com.victop.ibs.util.Container;

/**
 * 素材模块，选择任务界面
 * 
 * @author vv
 * 
 */
public class TaskSortActivity extends ActivityBase implements OnClickListener {
	// public static final int STYP_NUM = 3;
	TaskListAdapter adapter;
	private Spinner sper;
	private ListView listView;
	private List<Map<String, String>> listData = new ArrayList<Map<String, String>>();
	private static final String[] mCountries = { "最新时间", "按首字母排序" };
	private ActionBar actionBar;//导航栏
	private MenuItem search, add, save;//搜索,添加，保存按钮
	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		final View view = View.inflate(this, R.layout.tasksortlayout, null);
		setContentView(view);
		// ibsApplication.getInstance().addActivity(this);
		initData();
		setData();
		setData1();
		setData2();
		initViews();
		initListeners();

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void initData() {
		// TODO Auto-generated method stub
		actionBar = getSupportActionBar();
		actionBar.setTitle("任务");
		actionBar.setHomeButtonEnabled(true);
		actionBar.setIcon(R.drawable.btn_back);
		
		
	}

	@Override
	protected void initViews() {
		// TODO Auto-generated method stub
		sper = (Spinner) findViewById(R.id.sp_time);
		ArrayAdapter<String> ad = new ArrayAdapter<String>(this,
				R.layout.simple_spinner_task, mCountries);
		// ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		sper.setAdapter(ad);
		listView = (ListView) findViewById(R.id.tasksort_listView);
//		adapter = new TaskListAdapter(TaskSortActivity.this, listData);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				Intent i = new Intent(TaskSortActivity.this,
						MaterialAddActivity.class);
				Bundle b = new Bundle();
				b.putString("tasknumber", listData.get(arg2).get("tasknumber"));
				i.putExtras(b);
				setResult(Container.TASK, i);
				finish();
			}
		});
	}

	@Override
	protected void initListeners() {
		// TODO Auto-generated method stub

	}

	public void setData() {
		Map<String, String> map;
		// for (int i = 0; i < 10; i++) {
		map = new HashMap<String, String>();
		map.put("title", "IBS素材搜集流程及完成流程下发任务流程");
		map.put("tasknumber", "121215663");
		map.put("statue", "0");
		map.put("deadline", "2015-10-10");
		map.put("type", "紧急");
		listData.add(map);
		// }

	}

	public void setData1() {
		Map<String, String> map;
		// for (int i = 0; i < 10; i++) {
		map = new HashMap<String, String>();
		map.put("title", "IT素材搜集流发任务流程");
		map.put("tasknumber", "121215663");
		map.put("statue", "0" + 0);
		map.put("deadline", "2015-10-10");
		map.put("type", "紧急");
		listData.add(map);
		// }

	}

	public void setData2() {
		Map<String, String> map;
		// for (int i = 0; i < 10; i++) {
		map = new HashMap<String, String>();
		map.put("title", "WEb素材搜集流程及发任务流程");
		map.put("tasknumber", "121215663");
		map.put("statue", "0" + 1);
		map.put("deadline", "2015-10-10");
		map.put("type", "紧急");
		listData.add(map);
		// }

	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);

		search = menu.findItem(R.id.search);
		add = menu.findItem(R.id.add);
		save = menu.findItem(R.id.save);
		search.setVisible(false);
		add.setVisible(false);
		save.setVisible(true);
		save.setTitle("确定");
		save.setIcon(null);

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
			break;
		case R.id.add:
			
			break;
		case R.id.save:
			finish();
			break;
		}

		return super.onOptionsItemSelected(item);
	}
}
