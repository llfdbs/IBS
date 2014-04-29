package com.victop.ibs.util;

import android.content.Context;
import android.os.Handler;
import android.widget.Toast;
/**
 * 提示信息
 * @author vv
 *
 */

public class ToastTools {
	private static Toast mToast;
	private static Handler mHandler = new Handler();
	private static Runnable r = new Runnable() {
		public void run() {
			mToast.cancel();
		}
	};

	/**
	 * 提示信息
	 * 
	 * @param mContext
	 * @param text
	 * @param duration
	 */
	public static void showToast(Context mContext, String text, long duration) {

		mHandler.removeCallbacks(r);
		if (mToast != null)
			mToast.setText(text);
		else
			mToast = Toast.makeText(mContext, text, Toast.LENGTH_SHORT);
		mHandler.postDelayed(r, duration);
		mToast.show();
	}

	// 显示Toast代码：CustomToast.showToast(getBaseContext(), "提示信息", 1000);
	// 因为一般提示信息都是放在strings.xml中，所以为了方便使用，又写了个方法：
	public static void showToastResources(Context mContext, int resId,
			int duration) {
		showToast(mContext, mContext.getResources().getString(resId), duration);
	}
}
