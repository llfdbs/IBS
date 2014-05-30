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
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.victop.ibs.adapter.TaskAllocationAdapter;
import com.victop.ibs.app.IBSApplication;
import com.victop.ibs.base.ActivityBase;
import com.victop.ibs.bean.AddTaskEmployesBean;
import com.victop.ibs.handler.BaseHandler;
import com.victop.ibs.presenter.Getpresenter;
import com.victop.ibs.util.CharacterParser;
import com.victop.ibs.util.PinyinComparator;
import com.victop.ibs.view.ClearEditText;

/**
 * 员工
 * 
 * @author Administrator
 * 
 */
public class TaskAllocationActivity extends ActivityBase {

	private ClearEditText filter_edit;
	private ListView employeelistview;
	//private RadioGroup radioGroup;
	private TaskAllocationAdapter employeeAdapter;
	private List<Map<String, String>> list = new ArrayList<Map<String, String>>();
	private List<Map<String, String>> employ_list = new ArrayList<Map<String, String>>();
	private static ActionBar actionBar;// 导航栏
	private MenuItem search, add, save;// 搜索,添加，保存按钮
	private CharacterParser characterParser;
	private PinyinComparator pinyinComparator;

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
		// 实例化汉字转拼音类
		characterParser = CharacterParser.getInstance();
		pinyinComparator = new PinyinComparator();

		BaseHandler bHandler = new BaseHandler(this, mHandler);
		Map<String, Class> clsMap = new HashMap<String, Class>();
		clsMap.put(AddTaskEmployesBean.datasetId, AddTaskEmployesBean.class);
		Getpresenter.getInstance().getInitbData(bHandler, clsMap, null,
				AddTaskEmployesBean.modelId, AddTaskEmployesBean.datasetId,
				null, AddTaskEmployesBean.fromId);

	}

	Handler mHandler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);

			switch (msg.what) {

			case 0:// 获取到数据
				Map<String, List> dataMap = (Map<String, List>) msg.obj;
				List<AddTaskEmployesBean> mUnCheckedMaterialBean = dataMap
						.get(AddTaskEmployesBean.datasetId);
				employeeAdapter = new TaskAllocationAdapter(
						TaskAllocationActivity.this, mUnCheckedMaterialBean,
						"visiable");
				employeelistview.setAdapter(employeeAdapter);
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
	protected void initViews() {
		// TODO Auto-generated method stub
		actionBar = getSupportActionBar();
		actionBar.setTitle(getResources().getString(R.string.allocationtask));
		actionBar.setHomeButtonEnabled(true);
		actionBar.setIcon(R.drawable.btn_back);
		filter_edit = (ClearEditText) findViewById(R.id.filter_edit);
		employeelistview = (ListView) findViewById(R.id.employeelist);
		//radioGroup = (RadioGroup) findViewById(R.id.allocation_group);
	}

	@Override
	protected void initListeners() {
		// TODO Auto-generated method stub
		// radioGroup.setOnCheckedChangeListener(onCheckedChangeListener);
	}

	public static void setTitle(String name) {
		actionBar.setTitle(name);
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

		case R.id.save:
			TranslateData();
			break;
		}

		return super.onOptionsItemSelected(item);
	}

	public void TranslateData() {

		AddTaskEmployesBean mUserMessageBean = TaskAllocationAdapter.isSelectedName
				.get("user");
		Intent intent = new Intent(TaskAllocationActivity.this,
				AddTaskActivity.class);
		if (null != mUserMessageBean) {
			Bundle bundle = new Bundle();
			bundle.putString("user", mUserMessageBean.getHrname());
			intent.putExtras(bundle);
		}
		setResult(RESULT_OK, intent);
		finish();
	}
}
