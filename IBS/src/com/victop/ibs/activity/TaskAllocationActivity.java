package com.victop.ibs.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;

import com.victop.ibs.adapter.TaskAllocationAdapter;
import com.victop.ibs.app.IBSApplication;
import com.victop.ibs.base.ActivityBase;
import com.victop.ibs.view.ClearEditText;

public class TaskAllocationActivity extends ActivityBase {
	
	
	private ClearEditText filter_edit;
	private ListView employeelistview;
	private RadioGroup radioGroup;
	private TaskAllocationAdapter employeeAdapter;
	private List<Map<String, String>> list = new ArrayList<Map<String, String>>();
	private List<Map<String, String>> employ_list = new ArrayList<Map<String, String>>();
	private static ActionBar actionBar;//导航栏
	private MenuItem search, add, save;//搜索,添加，保存按钮
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.taskallocation);
		IBSApplication.getInstance().addActivity(this);
		initViews();
		initData();
		initListeners();
	}

	@Override
	protected void initData() {
		// TODO Auto-generated method stub
		setData();
		employeeAdapter = new TaskAllocationAdapter(
				TaskAllocationActivity.this, list, "visiable");
		employeelistview.setAdapter(employeeAdapter);
	}

	@Override
	protected void initViews() {
		// TODO Auto-generated method stub
		actionBar = getSupportActionBar();
		actionBar.setTitle(getResources().getString(R.string.allocationtask));
		actionBar.setHomeButtonEnabled(true);
		actionBar.setIcon(R.drawable.btn_back);
		filter_edit = (ClearEditText) findViewById(R.id.filter_edit);
		employeelistview = (ListView) findViewById(R.id.employeelist);
		radioGroup = (RadioGroup) findViewById(R.id.allocation_group);
	}

	@Override
	protected void initListeners() {
		// TODO Auto-generated method stub
		
		radioGroup.setOnCheckedChangeListener(onCheckedChangeListener);
	}

	public static void setTitle(String name) {
		actionBar.setTitle(name);
	}

	public void setData() {
		Map<String, String> map;
		for (int i = 0; i < 10; i++) {
			map = new HashMap<String, String>();
			map.put("name", "张晓婉" + i);
			list.add(map);
			// employ_list.add(map);
		}
		// list.addAll(employ_list);
	}

	public void setDataCache() {
		list.addAll(employ_list);
	}

	public void setData1() {
		Map<String, String> map;
		for (int i = 0; i < 10; i++) {
			map = new HashMap<String, String>();
			map.put("name", "安卓小组" + i);
			list.add(map);
		}
	}

	
	public void TranslateData(){
		String nametext = "";
		HashMap<Integer, String> hashMap = TaskAllocationAdapter.isSelectedName;
		Iterator iter = hashMap.keySet().iterator();
		while (iter.hasNext()) {
			Object key = iter.next();
			Object val = hashMap.get(key);
			nametext = nametext + ((String) val) + " ";
		}

		Intent intent = new Intent(TaskAllocationActivity.this,
				AddTaskActivity.class);
		Bundle bundle = new Bundle();
		bundle.putString("name", nametext);
		intent.putExtras(bundle);
		setResult(RESULT_OK, intent);
		finish();
	}
	OnCheckedChangeListener onCheckedChangeListener = new OnCheckedChangeListener() {

		@Override
		public void onCheckedChanged(RadioGroup group, int checkedId) {
			// TODO Auto-generated method stub
			switch (checkedId) {
			case R.id.rbtn_employee:
				employeeAdapter.isSelectedName.clear();
				employeeAdapter.isSelectedCount.clear();
				setTitle("已选" + employeeAdapter.isSelectedCount.size() + "人");
				list.clear();
				setData();
				employeeAdapter = new TaskAllocationAdapter(
						TaskAllocationActivity.this, list, "visiable");
				employeelistview.setAdapter(employeeAdapter);
				employeeAdapter.notifyDataSetChanged();
				break;
			case R.id.rbtn_group:
				employeeAdapter.isSelectedName.clear();
				employeeAdapter.isSelectedCount.clear();
				setTitle("已选" + employeeAdapter.isSelectedCount.size() + "个小组");
				list.clear();
				setData1();
				employeeAdapter = new TaskAllocationAdapter(
						TaskAllocationActivity.this, list, "gone");
				employeelistview.setAdapter(employeeAdapter);
				employeeAdapter.notifyDataSetChanged();
				break;
			default:
				break;
			}
		}
	};
	
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
	
		case R.id.save:
			TranslateData();
			break;
		}

		return super.onOptionsItemSelected(item);
	}
}
