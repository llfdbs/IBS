package com.victop.ibs.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.Menu;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.victop.ibs.base.ActivityBase;
import com.victop.ibs.fragment.HomeModlFragment;
import com.victop.ibs.fragment.PersonalCenterFragment;

public class MainActivity extends ActivityBase {
	private SlidingMenu menu;
	private ActionBar actionBar;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		initSlidingMenu();
	}

	/**
	 * 初始化滑动菜单
	 */
	private void initSlidingMenu() {
		// 设置主界面视图
		// setContentView(R.layout.content_frame);
		actionBar = getSupportActionBar();
		actionBar.hide();
		getSupportFragmentManager().beginTransaction()
				.replace(R.id.content_frame, new HomeModlFragment()).commit();

		// 设置滑动菜单的属性值
		menu = new SlidingMenu(this);
		menu.setMode(SlidingMenu.LEFT);
		// menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
		menu.setTouchModeAbove(SlidingMenu.LEFT);
		menu.setShadowWidthRes(R.dimen.shadow_width);
		// menu.setShadowDrawable(R.drawable.shadow);
		menu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
		menu.setFadeDegree(0.35f);
		menu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
		// 设置滑动菜单的视图界面
		menu.setMenu(R.layout.menu_frame);
		getSupportFragmentManager().beginTransaction()
				.replace(R.id.menu_frame, new PersonalCenterFragment())
				.commit();
	}

	@Override
	public void onBackPressed() {
		// 点击返回键关闭滑动菜单
		if (menu.isMenuShowing()) {
			menu.showContent();
		} else {
			super.onBackPressed();
		}
	}

	public void showMenuRight(){
		menu.showMenu();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	protected void initData() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void initViews() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void initListeners() {
		// TODO Auto-generated method stub

	}

	/**
	 * 跳转其他页面，
	 * 
	 * @param i
	 */
	public void rightToCenter(int i) {
		Bundle b = new Bundle();
		switch (i) {
		case 0://未完成
			b.putString("material_style", "notcomplete");
			openActivity(MaterialAllActivity.class, b);
			break;
		case 1://已审核
			b.putString("material_style", "audit");
			openActivity(MaterialAllActivity.class, b);
			break;
		case 2://未审核
			b.putString("material_style", "unaudit");
			openActivity(MaterialAllActivity.class, b);
			break;
		case 3://新增素材
//			b.putString("material_style", "unaudit");
			openActivity(MaterialAddActivity.class, null);
			break;
		case 4://素材搜索
			openActivity(MaterialSearchActivity.class, null);
			break;
		case 5://接受的任务
			openActivity(TaskListActivity.class, null);
			break;
		case 6://发布的任务
			openActivity(SendedTaskListActivity.class, null);
			break;
		case 7://新增的任务
			openActivity(AddTaskActivity.class, null);
			break;
		case 8://退出
			openActivity(LoginActivity.class, null);
			break;
		default:
			break;
		}
	}
}
