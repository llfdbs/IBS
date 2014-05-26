package com.victop.ibs.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.victop.ibs.activity.R;
import com.victop.ibs.app.IBSApplication;
import com.victop.ibs.util.CreateBeanFactory;
import com.victop.ibs.util.ToastTools;
import com.victop.ibs.xml.DConverter;
import com.victop.ibs.xml.Datajson;

/**
 * Activity的基类，全站公用菜单、统计等代码编写
 * 
 */
public abstract class ActivityBase extends ActionBarActivity {
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
		overridePendingTransition(R.anim.new_in_from_right,
				R.anim.new_out_to_left);// 苹果的效果
		// overridePendingTransition(R.anim.left_in, R.anim.left_out);
		// overridePendingTransition(android.R.anim.fade_in,
		// android.R.anim.fade_out); // 淡进淡出
		// overridePendingTransition(android.R.anim.slide_out_right,
		// android.R.anim.slide_in_left); // 有劲做出
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
		// overridePendingTransition(R.anim.left_in, R.anim.left_out);// 左进右出
		overridePendingTransition(android.R.anim.fade_in,
				android.R.anim.fade_out); // 淡进淡出
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
	protected void openCameraActivityForResult(Bundle pBundle,
			int pRequestCode, String action) {
		Intent _Intent = new Intent(action);
		_Intent.setAction(action);
		if (pBundle != null) {
			_Intent.putExtras(pBundle);
		}
		startActivityForResult(_Intent, pRequestCode);
		// overridePendingTransition(R.anim.left_in, R.anim.left_out);// 左进右出
		overridePendingTransition(android.R.anim.fade_in,
				android.R.anim.fade_out); // 淡进淡出
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
			IBSApplication.getInstance().exit();
		}
	}

	/**
	 * 解析XMl
	 * 
	 * @param xmlname
	 * @param c
	 *            转化器
	 * @param cl
	 *            xml定义标签类
	 * @return
	 */
	public Object getXmlStream(String xmlname, Class c, Class cl) {
		Object obj;
		XStream _XStream = new XStream();
		_XStream.processAnnotations(cl);
		if (null != c) {
			DConverter dConverter = new DConverter(c);
			try {
				dConverter.setBean(CreateBeanFactory.getInstance(c
						.newInstance()));
			} catch (InstantiationException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IllegalAccessException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			if (null != dConverter) {
				_XStream.registerConverter(dConverter);
			}
		}

		try {
			InputStream _InputStream = IBSApplication.getInstance().getAssets()
					.open(xmlname);

			obj = (Object) _XStream.fromXML(_InputStream);
			_InputStream.close();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		return obj;
	}

	/**
	 * 先xml 解析， 然后Gson转对象，前提保存的是Gson格式
	 * 
	 * @param xmlname
	 * @param cl
	 * @return
	 */
	public Object getXMlGson(String xmlname, Class cl) {
		Datajson obj;
		obj = (Datajson) getXmlStream(xmlname, null, Datajson.class);
		String name = obj.getName();
		Gson gson = new Gson();
		return (Object) gson.fromJson(name, cl);
	}

	/**
	 * 解析XMl
	 * 
	 * @param xmlname
	 * @param c
	 *            转化器
	 * @param cl
	 *            xml定义标签类
	 * @return
	 */
	public Object getXmlFileStream(String xmlname, Class c, Class cl) {
		Object obj = null;
		XStream _XStream = new XStream();
		_XStream.processAnnotations(cl);
		if (null != c) {
			DConverter dConverter = new DConverter(c);
			try {
				dConverter.setBean(CreateBeanFactory.getInstance(c
						.newInstance()));
			} catch (InstantiationException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IllegalAccessException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			if (null != dConverter) {
				_XStream.registerConverter(dConverter);
			}
		}
		try {

			File file = new File(Environment.getExternalStorageDirectory(),
					xmlname);
			if (Environment.getExternalStorageState().equals(
					Environment.MEDIA_MOUNTED)) {
				// InputStream inputStream = new
				// FileInputStream("/sdcard/text1.xml");
				FileInputStream inputStream = new FileInputStream(file);// 读取本地

				obj = (Object) _XStream.fromXML(inputStream);
				inputStream.close();
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		return obj;
	}

	/**
	 * 保存xml文件
	 * 
	 * @param url
	 * @param xmlname
	 *            //保存路径
	 * @param b
	 *            //生成对象
	 * @param cl
	 *            //标签类
	 */
	public void saveXmlStream(String url, String xmlname, Object b, Class cl) {
		XStream xstream = new XStream(new DomDriver());
		xstream.processAnnotations(cl);

		try {

			File file = new File(url + "/" + xmlname);
			if (!file.exists()) {
				try {
					file.createNewFile();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			FileOutputStream file_out = new FileOutputStream(file);
			xstream.toXML(b, file_out);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * 获取保存的xml
	 * 
	 * @param b
	 * @param cl
	 * @return
	 */
	public String getSaveXml(Object b, Class cl) {
		XStream xstream = new XStream(new DomDriver());
		xstream.processAnnotations(cl);
		return xstream.toXML(b);
	}

	/**
	 * 获取时间
	 * 
	 * @return
	 */
	public String getTime() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date curDate = new Date(System.currentTimeMillis());// 获取当前时间
		return formatter.format(curDate);
	}

	/**
	 * 获取时间
	 * 
	 * @return
	 */
	public String getDate() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date curDate = new Date(System.currentTimeMillis());// 获取当前时间
		return formatter.format(curDate);
	}

	/**
	 * 
	 * 
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
