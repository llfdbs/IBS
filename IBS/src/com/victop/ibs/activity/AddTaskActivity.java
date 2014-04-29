package com.victop.ibs.activity;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;

import com.victop.ibs.app.ibsApplication;
import com.victop.ibs.base.ActivityBase;
/**
 * 新增任务类 新增任务业务逻辑
 * 
 * @author vv
 * 
 */
public class AddTaskActivity extends ActivityBase {

	private Button btn_allocation,btn_addperson,btn_choosedate,btn_tasksave;//分配按钮,分配人按钮,选择日期,保存按钮
	private EditText edt_taskname,edt_taskdetail;//任务名称,任务描述
	private TextView tv_allocationname,tv_date,tv_datecount;//分配人名称,截止时间,天数
	private RadioGroup radiogroup_type;//类型
	private String str_taskName,str_allocationname,str_deadline,str_days,str_detail;//任务名称,分配人名称,截止时间,天数,任务详情
	private String type;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.addtask);
		ibsApplication.getInstance().addActivity(this);
		initViews();
		initData();
		initListeners();
	}

	@Override
	public void initData() {
		str_taskName = edt_taskname.getText().toString().trim();
		str_allocationname = tv_allocationname.getText().toString().trim();
		str_deadline = tv_date.getText().toString().trim();
		str_days = tv_datecount.getText().toString().trim();
		str_detail = edt_taskdetail.getText().toString().trim();
	}

	@Override
	public void initViews() {
		btn_allocation = (Button)findViewById(R.id.btn_allocation);
		btn_addperson = (Button)findViewById(R.id.btn_addperson);
		btn_choosedate = (Button)findViewById(R.id.btn_choosedate);
		btn_tasksave = (Button)findViewById(R.id.btn_tasksave);
		edt_taskname = (EditText)findViewById(R.id.edt_taskname);
		edt_taskdetail = (EditText)findViewById(R.id.edt_taskdetail);
		tv_allocationname = (TextView)findViewById(R.id.tv_allocationname);
		tv_date = (TextView)findViewById(R.id.tv_date);
		tv_datecount = (TextView)findViewById(R.id.tv_datecount);
		radiogroup_type = (RadioGroup)findViewById(R.id.radiogroup_type);
	}

	@Override
	public void initListeners() {
		btn_allocation.setOnClickListener(mOnClick);
		btn_addperson.setOnClickListener(mOnClick);
		btn_choosedate.setOnClickListener(mOnClick);
		btn_tasksave.setOnClickListener(mOnClick);
		radiogroup_type.setOnCheckedChangeListener(mOnChecked);
	}
	OnClickListener mOnClick = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch(v.getId()){
			case R.id.btn_allocation:
				break;
			case R.id.btn_addperson:
				break;
			case R.id.btn_choosedate:
				break;
			case R.id.btn_tasksave:
				break;
			}
		}
	};
	OnCheckedChangeListener mOnChecked = new OnCheckedChangeListener() {

		@Override
		public void onCheckedChanged(RadioGroup group, int checkedId) {
			// TODO Auto-generated method stub
			switch(checkedId){
			case R.id.rbtn_urgent:
				break;
			case R.id.rbtn_normal:
				break;
			default:;
			}
		}
	};

}
