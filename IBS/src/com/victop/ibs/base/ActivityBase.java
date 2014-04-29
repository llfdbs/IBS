package com.victop.ibs.base;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout.LayoutParams;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.victop.ibs.activity.R;
import com.victop.ibs.app.ibsApplication;
import com.victop.ibs.util.ToastTools;

/**
 * Activity的基类，全站公用菜单、统计等代码编写
 * 
 */
public abstract class ActivityBase extends FragmentActivity {
	public static final int REQUEST_FIRST_USER = 1;

	private int mShowingDialogID;
	private long mLastPressBackTime;

	// private StartThread mStartThread = null;

	/**
	 * 显示Toast消息
	 * 
	 * @param pMsg
	 *            消息内容
	 */
	protected void showToast(String pMsg, int duration) {
		ToastTools.showToast(this, pMsg, duration);
	}

	/**
	 * 显示Toast消息
	 * 
	 * @param pMsg
	 *            消息资源ID
	 */
	protected void showToastResources(int pMsg, int duration) {
		ToastTools.showToastResources(this, pMsg, duration);
	}

	/**
	 * 运行其他Activity
	 * 
	 * @param pClass
	 *            目标Activity的类对象
	 */
	protected void openActivity(Class<?> pClass) {
		openActivity(pClass, null);
	}

	/**
	 * 打印输出
	 * 
	 * @param str
	 */
	protected void print(String str) {
		boolean isprint = true;
		if (isprint)
			System.out.println(str);

	}

	/**
	 * 运行其他Activity
	 * 
	 * @param pClass
	 *            目标Activity的类对象
	 * @param pBundle
	 *            额外信息
	 */
	protected void openActivity(Class<?> pClass, Bundle pBundle) {
		Intent _Intent = new Intent(this, pClass);
		if (pBundle != null) {
			_Intent.putExtras(pBundle);
		}

		startActivity(_Intent);
	}

	/**
	 * 运行其他Activity，并期待返回结果�?
	 * 
	 * @param pClass
	 *            目标Activity的类对象
	 * @param pBundle
	 *            存放数据的Bundle对象，若不需要传递数据，传入null�?
	 * @param pRequestCode
	 *            请求Code
	 */
	protected void openActivityForResult(Class<?> pClass, Bundle pBundle,
			int pRequestCode) {
		Intent _Intent = new Intent(this, pClass);
		if (pBundle != null) {
			_Intent.putExtras(pBundle);
		}
		startActivityForResult(_Intent, pRequestCode);
	}

	/**
	 * 加载Layout文件，生成View组件�?
	 * 
	 * @param pLayoutId
	 *            Layout文件的资源Id
	 * @param pRoot
	 *            生成的View�?��的ViewGroup，传入null表示没有父ViewGroup�?
	 * @return 若传入了pRoot，则返回pRoot；反之，则返回生成的View
	 */
	protected View inflateView(int pLayoutId, ViewGroup pRoot) {
		return getLayoutInflater().inflate(pLayoutId, pRoot);
	}

	/**
	 * 加载MenuLayout文件，生成菜单（选项菜单、上下文菜单等）
	 * 
	 * @param pMenuLayoutId
	 *            MenuLayout文件的资源Id，例如�?R.menu.main_activity�?
	 * @param pMenu
	 *            用来存放菜单项和子菜单的Menu对象
	 */
	protected void inflateMenu(int pMenuLayoutId, Menu pMenu) {
		getMenuInflater().inflate(pMenuLayoutId, pMenu);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// menu.add(0, MENU_CHANGE_USER, 1,
		// "切换用户").setIcon(android.R.drawable.ic_menu_revert);
		// menu.add(0, MENU_SETTING, 2,
		// "设置").setIcon(android.R.drawable.ic_menu_preferences);
		// menu.add(0, MENU_EXIT, 3,
		// "�?��").setIcon(android.R.drawable.ic_menu_close_clear_cancel);

		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		return false;
	}

	@Override
	protected void onPrepareDialog(int pId, Dialog pDialog, Bundle pArgs) {
		super.onPrepareDialog(pId, pDialog, pArgs);
		mShowingDialogID = pId;
	}

	protected int getShowingDialogID() {
		return mShowingDialogID;
	}

	/******************************************************************
	 */
	// public void exitPop() {
	// LayoutInflater mLayoutInflater = (LayoutInflater)
	// getSystemService(LAYOUT_INFLATER_SERVICE);
	// View menuView = (View) mLayoutInflater.inflate(R.layout.exitdialog,
	// null, true);
	// no = (ImageButton) menuView.findViewById(R.id.cancel);
	// yes = (ImageButton) menuView.findViewById(R.id.sure);
	// no.requestFocus();
	// no.setOnClickListener(new View.OnClickListener() {
	//
	// public void onClick(View v) {
	// if (popupwindow != null && popupwindow.isShowing()) {
	// popupwindow.dismiss();
	// }
	// }
	// });
	// yes.setOnClickListener(new View.OnClickListener() {
	// public void onClick(View v) {
	// ibsApplication.getInstance().exit();
	// if (popupwindow != null && popupwindow.isShowing()) {
	// popupwindow.dismiss();
	// }
	// }
	// });
	// popupwindow = new PopupWindow(menuView, LayoutParams.FILL_PARENT,
	// LayoutParams.FILL_PARENT, true);
	// popupwindow.showAtLocation(menuView, Gravity.CENTER | Gravity.CENTER,
	// 0, 0);
	// popupwindow.update();
	// }
	/**
	 * 退出
	 */
	public void exitPop() {
		if ((System.currentTimeMillis() - mLastPressBackTime) > 2000) {
			ToastTools.showToastResources(this, R.string.press_back_exit_app,
					500);

			mLastPressBackTime = System.currentTimeMillis();
		} else {
			ibsApplication.getInstance().exit();
		}
	}

	/**
	 * 初始化成员变量 本方法应该在子类的onCreate方法中，绑定Layout文件后被调用
	 * 
	 */
	protected abstract void initData();

	/**
	 * 初始化成员View 本方法应该在子类的onCreate方法中，绑定Layout文件后被调用
	 */
	protected abstract void initViews();

	/**
	 * 初始化监听器 本方法应该在子类的onCreate方法中，绑定Layout文件后被调用
	 */
	protected abstract void initListeners();

}
