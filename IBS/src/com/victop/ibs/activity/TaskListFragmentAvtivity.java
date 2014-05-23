package com.victop.ibs.activity;

import com.victop.ibs.app.IBSApplication;
import com.victop.ibs.base.ActivityBase;
import com.victop.ibs.fragment.GetTaskListFrag;
import com.victop.ibs.util.Container;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class TaskListFragmentAvtivity extends ActivityBase {
	private MenuItem search, add, save;// 搜索,添加，保存按钮
	private ActionBar actionBar;// 导航栏
	private RadioGroup radiogroup_task;

	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		setContentView(R.layout.tasklistfragment);
		IBSApplication.getInstance().addActivity(this);

		initViews();
		initData();
		initListeners();

	}

	OnCheckedChangeListener mOnChecked = new OnCheckedChangeListener() {

		@Override
		public void onCheckedChanged(RadioGroup group, int checkedId) {
			// TODO Auto-generated method stub
			switch (checkedId) {
			case R.id.rbn_taskall:
				getSupportFragmentManager()
						.beginTransaction()
						.replace(
								R.id.frame_content,
								new GetTaskListFrag(Container.MODEL_ALL,
										TaskListFragmentAvtivity.this))
						.commit();
				break;
			case R.id.rbn_taskunfinish:
				getSupportFragmentManager()
						.beginTransaction()
						.replace(
								R.id.frame_content,
								new GetTaskListFrag(Container.MODEL_UNFINISH,
										TaskListFragmentAvtivity.this))
						.commit();
				break;
			case R.id.rbn_taskfinished:
				getSupportFragmentManager()
						.beginTransaction()
						.replace(
								R.id.frame_content,
								new GetTaskListFrag(Container.MODEL_FINISH,
										TaskListFragmentAvtivity.this))
						.commit();

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
		search.setVisible(true);
		add.setVisible(true);
		save.setVisible(false);

		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

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

	@Override
	protected void initData() {
		// TODO Auto-generated method stub

		getSupportFragmentManager()
				.beginTransaction()
				.replace(
						R.id.frame_content,
						new GetTaskListFrag(Container.MODEL_ALL,
								TaskListFragmentAvtivity.this)).commit();
	}

	@Override
	protected void initViews() {
		// TODO Auto-generated method stub
		actionBar = getSupportActionBar();
		actionBar.setTitle(getResources().getString(R.string.receivedtask)
				+ "(" + 10 + ")");
		actionBar.setHomeButtonEnabled(true);
		actionBar.setIcon(R.drawable.btn_back);
		radiogroup_task = (RadioGroup) findViewById(R.id.radiogroup_task);

	}

	@Override
	protected void initListeners() {
		// TODO Auto-generated method stub
		radiogroup_task.setOnCheckedChangeListener(mOnChecked);
	}
}
