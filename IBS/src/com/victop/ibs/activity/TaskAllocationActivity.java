package com.victop.ibs.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;

import com.victop.ibs.adapter.TaskAllocationAdapter;
import com.victop.ibs.app.ibsApplication;
import com.victop.ibs.base.ActivityBase;
import com.victop.ibs.view.ClearEditText;

public class TaskAllocationActivity extends ActivityBase {
private static TextView tv_allocationtask;
private Button btn_positive;
private ClearEditText filter_edit;
private ListView employeelistview;
private RadioGroup radioGroup;
private TaskAllocationAdapter employeeAdapter;
private List<Map<String,String>> list = new ArrayList<Map<String,String>>();
private List<Map<String,String>> employ_list=new ArrayList<Map<String, String>>();
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.taskallocation);
		ibsApplication.getInstance().addActivity(this);
		initViews();
		initData();
		initListeners();
	}
	@Override
	protected void initData() {
		// TODO Auto-generated method stub
		setData();
		employeeAdapter = new TaskAllocationAdapter(TaskAllocationActivity.this, list,"visiable");
		employeelistview.setAdapter(employeeAdapter);
	}
  
	@Override
	protected void initViews() {
		// TODO Auto-generated method stub
		tv_allocationtask = (TextView)findViewById(R.id.tv_allocationtask);
		btn_positive = (Button)findViewById(R.id.btn_positive);
		filter_edit = (ClearEditText)findViewById(R.id.filter_edit);
		employeelistview = (ListView)findViewById(R.id.employeelist);
		radioGroup = (RadioGroup)findViewById(R.id.allocation_group);
	}

	@Override
	protected void initListeners() {
		// TODO Auto-generated method stub
		btn_positive.setOnClickListener(mOnClick);
		radioGroup.setOnCheckedChangeListener(onCheckedChangeListener);
	}
	public static void setTitle(String name){
		tv_allocationtask.setText(name);
	}
	public void setData(){
		Map<String,String> map;
		for(int i=0;i<10;i++){
			map = new HashMap<String, String>();
			map.put("name", "张晓婉"+i);
			list.add(map);
			//employ_list.add(map);
			}
		//list.addAll(employ_list);
		}
	
	public void setDataCache(){
		list.addAll(employ_list);
	}
	public void setData1(){
		Map<String,String> map;
		for(int i=0;i<10;i++){
			map = new HashMap<String, String>();
			map.put("name", "安卓小组"+i);
			list.add(map);
			}
		}
    OnClickListener mOnClick = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent intent = new Intent(TaskAllocationActivity.this,AddTaskActivity.class);
			Bundle bundle = new Bundle();
			bundle.putString("name", TaskAllocationAdapter.isSelectedName.get(0));
			intent.putExtras(bundle);
			setResult(RESULT_OK, intent);
			finish();
		}
	};
   OnCheckedChangeListener onCheckedChangeListener = new OnCheckedChangeListener() {
	
	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		// TODO Auto-generated method stub
		switch(checkedId){
		case R.id.rbtn_employee:
			employeeAdapter.isSelectedName.clear();
			employeeAdapter.isSelectedCount.clear();
			setTitle("已选"+employeeAdapter.isSelectedCount.size()+"人");
			list.clear();
			setData();
			employeeAdapter = new TaskAllocationAdapter(TaskAllocationActivity.this, list,"visiable");
			employeelistview.setAdapter(employeeAdapter);
			employeeAdapter.notifyDataSetChanged();
			break;
		case R.id.rbtn_group:
			employeeAdapter.isSelectedName.clear();
			employeeAdapter.isSelectedCount.clear();
			setTitle("已选"+employeeAdapter.isSelectedCount.size()+"个小组");
			list.clear();
			setData1();
			employeeAdapter = new TaskAllocationAdapter(TaskAllocationActivity.this, list,"gone");
			employeelistview.setAdapter(employeeAdapter);
			employeeAdapter.notifyDataSetChanged();
			break;
		default:
			;
		}
	}
};
}
