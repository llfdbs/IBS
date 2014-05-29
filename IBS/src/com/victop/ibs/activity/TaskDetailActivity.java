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
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageButton;
import android.widget.TextView;

import com.victop.ibs.adapter.TaskDetail_MaterialAdapter;
import com.victop.ibs.app.IBSApplication;
import com.victop.ibs.base.ActivityBase;
import com.victop.ibs.bean.TaskDetailBean;
import com.victop.ibs.bean.TaskMaterialsBean;
import com.victop.ibs.handler.TaskDetailHandler;
import com.victop.ibs.presenter.TaskDetailPresenter;
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
	private ActionBar actionBar;// 导航栏
	private MenuItem search, add, save;// 搜索,添加，保存按钮
	Bundle bundle;
	String status;
	String taskid;
	private TaskDetailHandler taskDetailHandler;
	private List<TaskDetailBean> taskDetailList = new ArrayList<TaskDetailBean>();
	TaskDetailBean mTaskDetailBean;// 任务详情
	List<TaskMaterialsBean> mTaskMaterialsBean = new ArrayList<TaskMaterialsBean>();// 任务素材相关
	Map<String, List> dataMap;
	Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			switch (msg.what) {
			case 0:
				dataMap = (Map<String, List>) msg.obj;
				taskDetailList = dataMap.get("1");
				mTaskDetailBean = taskDetailList.get(0);
				mTaskMaterialsBean = dataMap.get("2");
				if (null == taskDetailList) {
					taskDetailList = new ArrayList<TaskDetailBean>();
				}
				if (null == mTaskMaterialsBean) {
					mTaskMaterialsBean = new ArrayList<TaskMaterialsBean>();
				}

				tv_taskdetail_title.setText(mTaskDetailBean.getTaskname());
				tv_taskdetail_detail.setText(mTaskDetailBean.getTaskmemo());
				tv_taskdetail_tasknumber.setText(mTaskDetailBean.getTaskcode());
				tv_taskdetail_deadline.setText(mTaskDetailBean.getDuedate());
				tv_taskdetail_tasktype.setText(mTaskDetailBean.getTasklevel());
				tv_taskdetail_taskobj.setText(mTaskDetailBean.getReceptname());
				tv_taskdetail_taskstatue.setText(mTaskDetailBean
						.getTaskstatus());
				tv_materialcount.setText("("+mTaskMaterialsBean.size()+")");// 素材的数量

				adapter = new TaskDetail_MaterialAdapter(
						TaskDetailActivity.this, mTaskMaterialsBean,
						mTaskDetailBean.getTaskstatus());
				mListView.setAdapter(adapter);
				break;
			
			}

			super.handleMessage(msg);
		}

	};

	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);

		setContentView(R.layout.taskdetail);
		IBSApplication.getInstance().addActivity(this);

		initViews();
		initHandler(handler);
		initData();
		initListeners();

	}

	private void initHandler(Handler handler) {
		taskDetailHandler = new TaskDetailHandler(TaskDetailActivity.this,
				handler);
	}

	@Override
	protected void initData() {
		// TODO Auto-generated method stub
		TaskDetailPresenter taskDetailPresenter = new TaskDetailPresenter();
		taskDetailPresenter.getInitData(taskDetailHandler, taskid);

		// setData();
		// adapter = new TaskDetail_MaterialAdapter(TaskDetailActivity.this,
		// listData,status);
		// mListView.setAdapter(adapter);
	}

	@Override
	protected void initViews() {
		// TODO Auto-generated method stub
		actionBar = getSupportActionBar();
		actionBar.setTitle(getResources().getString(R.string.taskdetail));
		actionBar.setHomeButtonEnabled(true);
		actionBar.setIcon(R.drawable.btn_back);
		mListView = (MyListView) findViewById(R.id.taskdetail_materiallist);
		imgbtn_addmaterial = (ImageButton) findViewById(R.id.imgbtn_addmaterial);
		tv_taskdetail_title = (TextView) findViewById(R.id.tv_taskdetail_title);
		tv_taskdetail_detail = (TextView) findViewById(R.id.tv_taskdetail_detail);
		tv_taskdetail_tasknumber = (TextView) findViewById(R.id.tv_taskdetail_tasknumber);
		tv_taskdetail_deadline = (TextView) findViewById(R.id.tv_taskdetail_deadline);
		tv_taskdetail_tasktype = (TextView) findViewById(R.id.tv_taskdetail_tasktype);
		tv_taskdetail_taskobj = (TextView) findViewById(R.id.tv_taskdetail_taskobj);
		tv_taskdetail_taskstatue = (TextView) findViewById(R.id.tv_taskdetail_taskstatue);
		tv_materialcount = (TextView) findViewById(R.id.tv_materialcount);
		bundle = getIntent().getExtras();
		status = bundle.getString("statue");
		taskid = bundle.getString("taskid");

	}

	@Override
	protected void initListeners() {
		// TODO Auto-generated method stub
		mListView.setOnItemClickListener(mOnItemClick);
		imgbtn_addmaterial.setOnClickListener(mOnClick);
	}

	public void setData() {
		Map<String, String> map;
		for (int i = 0; i < 10; i++) {
			map = new HashMap<String, String>();
			map.put("title", "IBS素材搜集" + i);
			map.put("allocationobj", "策划部" + i);
			map.put("committime", "2015-10-10" + i);
			map.put("checkstatue", "00" + i);
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
			Bundle bundle = new Bundle();
			bundle.putString("materialid", mTaskMaterialsBean.get(arg2).getMaterialid());
			openActivity(MaterialDetailActivity.class, bundle);
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
		save.setTitle("完工");
		save.setIcon(null);
		// 未完成
		if (status.equals("1")) {
			imgbtn_addmaterial.setVisibility(View.VISIBLE);
			save.setVisible(true);
		} else {
			imgbtn_addmaterial.setVisibility(View.GONE);
			save.setVisible(false);
		}

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
			finish();
			break;
		}

		return super.onOptionsItemSelected(item);
	}
}
