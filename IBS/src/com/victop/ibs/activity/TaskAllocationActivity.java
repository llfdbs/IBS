package com.victop.ibs.activity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

import com.victop.ibs.adapter.TaskAllocationAdapter;
import com.victop.ibs.app.IBSApplication;
import com.victop.ibs.base.ActivityBase;
import com.victop.ibs.bean.AddTaskEmployesBean;
import com.victop.ibs.bean.TagBean;
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
	// private RadioGroup radioGroup;
	private TaskAllocationAdapter employeeAdapter;
	private List<Map<String, String>> list = new ArrayList<Map<String, String>>();
	private List<Map<String, String>> employ_list = new ArrayList<Map<String, String>>();
	private static ActionBar actionBar;// 导航栏
	private MenuItem search, add, save;// 搜索,添加，保存按钮
	private CharacterParser characterParser;
	private PinyinComparator pinyinComparator;
	List<AddTaskEmployesBean> mAddTaskEmployesBean;

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
				mAddTaskEmployesBean = dataMap
						.get(AddTaskEmployesBean.datasetId);
				if (null != mAddTaskEmployesBean
						&& mAddTaskEmployesBean.size() > 0) {

					for (AddTaskEmployesBean s : mAddTaskEmployesBean) {
						String pinyin = characterParser.getSelling(s
								.getHrname());
						String sortString = pinyin.substring(0, 1)
								.toUpperCase();

						if (sortString.matches("[A-Z]")) {
							s.setSortLetters(sortString.toUpperCase());
						} else {
							s.setSortLetters("#");
						}
						s.setTag(0);
					}
					Collections.sort(mAddTaskEmployesBean, pinyinComparator);
					employeeAdapter = new TaskAllocationAdapter(
							TaskAllocationActivity.this, mAddTaskEmployesBean,
							"visiable");
					employeelistview.setAdapter(employeeAdapter);
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
	protected void initViews() {
		// TODO Auto-generated method stub
		actionBar = getSupportActionBar();
		actionBar.setTitle(getResources().getString(R.string.allocationtask));
		actionBar.setHomeButtonEnabled(true);
		actionBar.setIcon(R.drawable.btn_back);
		filter_edit = (ClearEditText) findViewById(R.id.filter_edit);

		employeelistview = (ListView) findViewById(R.id.employeelist);
		// radioGroup = (RadioGroup) findViewById(R.id.allocation_group);
		filter_edit.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// 当输入框里面的值为空，更新为原来的列表，否则为过滤数据列表
				filterData(s.toString());
				// findString(s.toString());
				int position = employeeAdapter.getPositionForString(s + "");
				if (position != -1) {
					employeelistview.setSelection(position);
				}

			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {

			}

			@Override
			public void afterTextChanged(Editable s) {
			}
		});
	}

	@Override
	protected void initListeners() {
		// TODO Auto-generated method stub
		// radioGroup.setOnCheckedChangeListener(onCheckedChangeListener);
		employeelistview.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				TextView tv_employeename = (TextView) view
						.findViewById(R.id.tv_employeename);
//				System.out.println(tv_employeename.getText() + "--------------");
				Intent intent = new Intent(TaskAllocationActivity.this,
						AddTaskActivity.class);
				Bundle bundle = new Bundle();
				bundle.putString("user", tv_employeename.getText().toString());
				intent.putExtras(bundle);
				setResult(RESULT_OK, intent);
				finish();
				overridePendingTransition(R.anim.left_in, R.anim.left_out);
			}
		});
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
		save.setVisible(false);
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
			// TranslateData();
			break;
		}

		return super.onOptionsItemSelected(item);
	}

	private void findString(String str) {
		if (TextUtils.isEmpty(str)) {
			employeelistview.setSelection(0);
		} else {
			if (mAddTaskEmployesBean.size() > 0)
				for (AddTaskEmployesBean sortModel : mAddTaskEmployesBean) {
					String name = sortModel.getHrname();
					if (name.indexOf(str.toString()) != -1
							|| characterParser.getSelling(name).startsWith(
									str.toString())) {
						int position = employeeAdapter
								.getPositionForString(name);
						if (position != -1) {
							employeelistview.setSelection(position);

						}
					}
				}
		}
	}

	private void filterData(String filterStr) {
		List<AddTaskEmployesBean> filterDateList = new ArrayList<AddTaskEmployesBean>();

		if (TextUtils.isEmpty(filterStr)) {
			filterDateList = mAddTaskEmployesBean;
		} else {
			filterDateList.clear();
			for (AddTaskEmployesBean sortModel : mAddTaskEmployesBean) {
				String name = sortModel.getHrname();
				if (name.indexOf(filterStr.toString()) != -1
						|| characterParser.getSelling(name).startsWith(
								filterStr.toString())) {
					filterDateList.add(sortModel);
				}
			}
		}

		// 根据a-z进行排序
//		Collections.sort(filterDateList, pinyinComparator);
		employeeAdapter.updateListView(filterDateList);
	}

	class PinyinComparator implements Comparator<AddTaskEmployesBean> {

		public int compare(AddTaskEmployesBean o1, AddTaskEmployesBean o2) {
			if (o1.getSortLetters().equals("@")
					|| o2.getSortLetters().equals("#")) {
				return -1;
			} else if (o1.getSortLetters().equals("#")
					|| o2.getSortLetters().equals("@")) {
				return 1;
			} else {
				return o1.getSortLetters().compareTo(o2.getSortLetters());
			}
		}

	}
}