package com.victop.ibs.util;

import java.util.UUID;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * 常用的些共共用方法
 * 
 * @author vv
 * 
 */
public class Tools {
	/**
	 * 判断是否有网络连接
	 * 
	 * @param context
	 * @return
	 */
	static UUID uuid;
	public static boolean isConnection(Context context) {
		ConnectivityManager connectivity = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		if (connectivity == null) {
			return false;
		} else {
			NetworkInfo[] info = connectivity.getAllNetworkInfo();
			if (info != null) {
				for (int i = 0; i < info.length; i++) {
					// 网络连接的状态包括：CONNECTED/CONNECTING/DISCONNECTED/DISCONNECTING/SUSPENDED/UNKNOWN
					if (info[i].getState() == NetworkInfo.State.CONNECTED) {
						return true;
					}
				}
			}
		}
		return false;
	}

	private static long lastClickTime;

	/**
	 * 判断是否连续短时间操作
	 * 
	 * @param context
	 * @return
	 */
	public static boolean isFastDoubleClick() {
		long time = System.currentTimeMillis();
		long timeD = time - lastClickTime;
		if (0 < timeD && timeD < 800) {
			return true;
		}
		lastClickTime = time;
		return false;

	}
	/****
	 * 随机产生UUID
	 * ***/
	public static UUID generateUUid(){
		uuid = UUID.randomUUID();
		return uuid;
	}
}
