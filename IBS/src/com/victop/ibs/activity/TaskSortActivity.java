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
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.victop.ibs.adapter.TaskSortAdapter;
import com.victop.ibs.base.ActivityBase;
import com.victop.ibs.bean.TaskBean;
import com.victop.ibs.handler.BaseHandler;
import com.victop.ibs.presenter.Getpresenter;
import com.victop.ibs.util.MyContainer;

/**
 * 素材模块，选择任务界面
 * 
 * @author vv
 * 
 */
public class TaskSortActivity extends ActivityBase implements OnClickListener {
	// public static final int STYP_NUM = 3;
	TaskSortAdapter adapter;
	private Spinner sper;
	private ListView listView;
	// private List<Map<String, String>> listData = new ArrayList<Map<String,
	// String>>();
	private static final String[] mCountries = { "最新时间", "按首字母排序" };
	private ActionBar actionBar;// 导航栏
	private MenuItem search, add, save;// 搜索,添加，保存按钮
	private List<TaskBean> mTaskBean = new ArrayList<TaskBean>();

	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		final View view = View.inflate(this, R.layout.tasksortlayout, null);
		setContentView(view);
		// ibsApplication.getInstance().addActivity(this);
		initData();

		initViews();
		initListeners();

	}

	Handler mHandler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			switch (msg.what) {
			case 0:// 获取到数据
				Map<String, List> dataMap = (Map<String, List>) msg.obj;
				mTaskBean = (List<TaskBean>) dataMap.get("1");
				if (null != mTaskBean) {
					adapter = new TaskSortAdapter(TaskSortActivity.this,
							mTaskBean);
					listView.setAdapter(adapter);
				}
 
				break;
			case 1:
				String rr = (String) msg.obj;
				Toast.makeText(getApplicationContext(), rr, 1000).show();
				finish();
				break;
			case 2:
			case 3:
				String r2r = (String) msg.obj;
				Toast.makeText(getApplicationContext(), r2r, 1000).show();
				break;
			}
		}
	};

	@Override
	protected void initData() {
		// TODO Auto-generated method stub
		BaseHandler bHandler = new BaseHandler(this, mHandler);

		HashMap<String, String> Datamap = new HashMap<String, String>();
		Datamap.put("hrid", "1");
		Map<String, Class> clsMap = new HashMap<String, Class>();
		clsMap.put(TaskBean.datasetId, TaskBean.class);
		Getpresenter.getInstance().getInitbData(bHandler, clsMap, Datamap,
				TaskBean.modelId, TaskBean.datasetId, null, TaskBean.formId);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void initViews() {
		actionBar = getSupportActionBar();
		actionBar.setTitle("任务");
		actionBar.setHomeButtonEnabled(true);
		actionBar.setIcon(R.drawable.btn_back);
		// TODO Auto-generated method stub
		sper = (Spinner) findViewById(R.id.sp_time);
		ArrayAdapter<String> ad = new ArrayAdapter<String>(this,
				R.layout.simple_spinner_task, mCountries);
		// ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		sper.setAdapter(ad);
		listView = (ListView) findViewById(R.id.tasksort_listView);

		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				Intent i = new Intent(TaskSortActivity.this,
						MaterialAddActivity.class);
				Bundle b = new Bundle();
				b.putString("tasknumber", mTaskBean.get(arg2).getTaskcode());
				b.putString("taskid", mTaskBean.get(arg2).getTaskid());
				i.putExtras(b);
				setResult(MyContainer.TASK, i);
				finish();
			}
		});
	}

	@Override
	protected void initListeners() {
		// TODO Auto-generated method stub

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
