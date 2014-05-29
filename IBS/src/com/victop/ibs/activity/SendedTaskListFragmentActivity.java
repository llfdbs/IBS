package com.victop.ibs.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

import com.victop.ibs.app.IBSApplication;
import com.victop.ibs.base.ActivityBase;
import com.victop.ibs.fragment.SendedTaskListFrag;
import com.victop.ibs.util.Container;

/**
 * 发布的任务列表fragmentActivity
 * 
 * @author vv
 * 
 */
public class SendedTaskListFragmentActivity extends ActivityBase {

	private MenuItem search, add, save;// 搜索,添加，保存按钮
	private ActionBar actionBar;// 导航栏
	private RadioGroup radiogroup_tasksended;
	private SendedTaskListFrag allFrag, unfinishFrag, finishFrag, unsendFrag;// 全部任务,未完成任务,已完成任务,未发放fragment
	private String status ="0";
	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		setContentView(R.layout.sendedlistfragment);
		IBSApplication.getInstance().addActivity(this);
		initFrag();
		initViews();
		initData();
		initListeners();

	}

	/**
	 * 初始化数据
	 */
	@Override
	protected void initData() {
		// TODO Auto-generated method stub

		CheckChange(allFrag, String.valueOf(Container.S_MODEL_ALL));
	}

	/**
	 * 初始化控件
	 */
	@Override
	protected void initViews() {
		// TODO Auto-generated method stub
		Bundle bundle = getIntent().getExtras();
		String count = bundle.getString("count");
		actionBar = getSupportActionBar();
		if(null!=count){
			actionBar.setTitle(getResources().getString(R.string.sendedtask)
					+ "(" + count + ")");
		}else{
			actionBar.setTitle(getResources().getString(R.string.sendedtask));
		}
		
		actionBar.setHomeButtonEnabled(true);
		actionBar.setIcon(R.drawable.btn_back);
		radiogroup_tasksended = (RadioGroup) findViewById(R.id.radiogroup_tasksended);
	}

	/**
	 * 绑定控件的监听事件
	 */
	@Override
	protected void initListeners() {
		// TODO Auto-generated method stub
		radiogroup_tasksended.setOnCheckedChangeListener(mOnChecked);
	}

	/**
	 * 初始化fragment
	 */
	private void initFrag() {
		allFrag = new SendedTaskListFrag(Container.MODEL_ALL,
				SendedTaskListFragmentActivity.this, Container.STATUS_ALL);
		unfinishFrag = new SendedTaskListFrag(Container.MODEL_UNFINISH,
				SendedTaskListFragmentActivity.this, Container.STATUS_UNFINISH);
		finishFrag = new SendedTaskListFrag(Container.MODEL_FINISH,
				SendedTaskListFragmentActivity.this, Container.STATUS_FINISH);
		unsendFrag = new SendedTaskListFrag(Container.MODEL_UNSEND,
				SendedTaskListFragmentActivity.this, Container.STATUS_UNSEND);

	}

	/**
	 * 控制fragment的显示与隐藏
	 * 
	 * @param fragment对象
	 * @param fragment标示
	 */
	private void CheckChange(Fragment frg, String tag) {
		FragmentManager manager = getSupportFragmentManager();
		FragmentTransaction trans = manager.beginTransaction();
		Fragment frt = manager.findFragmentByTag(tag);
		if (null != manager.getFragments()) {
			for (Fragment f : manager.getFragments()) {
				trans.hide(f);
			}
		}
		if (null == frt) {
			trans.add(R.id.frame_content, frg, tag);
			trans.show(frg);
		} else {
			trans.show(frt);
		}
		trans.commit();
	}

	/**
	 * 切换卡的点击事件
	 */
	OnCheckedChangeListener mOnChecked = new OnCheckedChangeListener() {

		@Override
		public void onCheckedChanged(RadioGroup group, int checkedId) {
			// TODO Auto-generated method stub
			switch (checkedId) {
			case R.id.rbn_sendedtask_all:
				status = Container.STATUS_ALL;
				CheckChange(allFrag, String.valueOf(Container.S_MODEL_ALL));

				break;
			case R.id.rbn_sendedtask_unfinish:
				status = Container.STATUS_UNFINISH;
				CheckChange(unfinishFrag,
						String.valueOf(Container.S_MODEL_UNFINISH));
				break;
			case R.id.rbn_sendedtask_finished:
				status = Container.STATUS_FINISH;
				CheckChange(finishFrag, String.valueOf(Container.S_MODEL_FINISH));
				break;
			case R.id.rbn_sendedtask_unsend:
				status = Container.STATUS_UNSEND;
				CheckChange(unsendFrag, String.valueOf(Container.MODEL_UNSEND));
				break;
			default:
				break;
			}
		}
	};

	/**
	 * actionbar样式控制
	 */
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

	/**
	 * actionbar控件的点击事件
	 */
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
			Bundle bundle = new Bundle();
			if(status.equals(Container.STATUS_ALL)){
				bundle.putString("modeobj", "task");
				bundle.putInt("tag",Container.S_MODEL_ALL);
				bundle.putString("title","全部任务搜索结果");
			}else if(status.equals(Container.STATUS_UNFINISH)){
				bundle.putString("modeobj", "task");
				bundle.putInt("tag",Container.S_MODEL_UNFINISH);
				bundle.putString("title","未完成任务搜索结果");
			}else if(status.equals(Container.STATUS_FINISH)){
				bundle.putString("modeobj", "task");
				bundle.putInt("tag",Container.S_MODEL_FINISH);
				bundle.putString("title","已完成任务搜索结果");
			}else{
				bundle.putString("modeobj", "task");
				bundle.putInt("tag",Container.MODEL_UNSEND);
				bundle.putString("title","未发放任务搜索结果");
			}
			
			openActivity(MaterialSearchActivity.class,bundle);
			break;
		case R.id.add:
			openActivity(AddTaskActivity.class, null);
			break;

		}

		return super.onOptionsItemSelected(item);
	}
}
