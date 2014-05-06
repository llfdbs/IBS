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
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.victop.ibs.adapter.TaskDetail_MaterialAdapter;
import com.victop.ibs.app.ibsApplication;
import com.victop.ibs.base.ActivityBase;
import com.victop.ibs.view.MyListView;

/**
 * 任务详情类 任务详情业务逻辑
 * 
 * @author vv
 * 
 */
public class TaskDetailActivity extends ActivityBase {
	private MyListView mListView;// 素材列表
	private TaskDetail_MaterialAdapter adapter;
	private List<Map<String, String>> listData = new ArrayList<Map<String, String>>();
	private Button btn_finish;// 完工
	private ImageButton imgbtn_addmaterial;// 新增素材
	// 任务标题,任务详情,任务单号,截止时间,任务类型,分配对象,完成状态,素材数量
	private TextView tv_taskdetail_title, tv_taskdetail_detail,
			tv_taskdetail_tasknumber, tv_taskdetail_deadline,
			tv_taskdetail_tasktype, tv_taskdetail_taskobj,
			tv_taskdetail_taskstatue, tv_materialcount;
	private String str_taskdetail_title, str_taskdetail_detail,
			str_taskdetail_tasknumber, str_taskdetail_deadline,
			str_taskdetail_tasktype, str_taskdetail_taskobj,
			str_taskdetail_taskstatue, str_materialcount;

	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);

		setContentView(R.layout.taskdetail);
		ibsApplication.getInstance().addActivity(this);

		initViews();
		initData();
		initListeners();

	}

	@Override
	protected void initData() {
		// TODO Auto-generated method stub
		setData();
		adapter = new TaskDetail_MaterialAdapter(TaskDetailActivity.this,
				listData);
		mListView.setAdapter(adapter);
	}

	@Override
	protected void initViews() {
		// TODO Auto-generated method stub
		mListView = (MyListView) findViewById(R.id.taskdetail_materiallist);
		btn_finish = (Button) findViewById(R.id.btn_finish);
		imgbtn_addmaterial = (ImageButton) findViewById(R.id.imgbtn_addmaterial);
		tv_taskdetail_title = (TextView) findViewById(R.id.tv_taskdetail_title);
		tv_taskdetail_detail = (TextView) findViewById(R.id.tv_taskdetail_detail);
		tv_taskdetail_tasknumber = (TextView) findViewById(R.id.tv_taskdetail_tasknumber);
		tv_taskdetail_deadline = (TextView) findViewById(R.id.tv_taskdetail_deadline);
		tv_taskdetail_tasktype = (TextView) findViewById(R.id.tv_taskdetail_tasktype);
		tv_taskdetail_taskobj = (TextView) findViewById(R.id.tv_taskdetail_taskobj);
		tv_taskdetail_taskstatue = (TextView) findViewById(R.id.tv_taskdetail_taskstatue);
		tv_materialcount = (TextView) findViewById(R.id.tv_materialcount);

	}

	@Override
	protected void initListeners() {
		// TODO Auto-generated method stub
		mListView.setOnItemClickListener(mOnItemClick);
		btn_finish.setOnClickListener(mOnClick);
		imgbtn_addmaterial.setOnClickListener(mOnClick);
	}

	public void setData() {
		Map<String, String> map;
		for (int i = 0; i < 10; i++) {
			map = new HashMap<String, String>();
			map.put("title", "IBS素材搜集" + i);
			map.put("allocationobj", "策划部" + i);
			map.put("committime", "2015-10-10" + i);
			map.put("checkstatue", "0" + i);
			listData.add(map);
		}

	}

	public void setText() {

	}

	// 按钮点击事件
	OnClickListener mOnClick = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
			case R.id.btn_finish:
				finish();
				break;
			case R.id.imgbtn_addmaterial:
				openActivity(MaterialAddActivity.class, null);
				break;
			}
		}
	};
	// 列表点击事件
	OnItemClickListener mOnItemClick = new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			// TODO Auto-generated method stub
			openActivity(MaterialDetailActivity.class,null);
		}
	};

}
