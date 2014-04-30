package com.victop.ibs.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.victop.ibs.bean.SortModel;

/**
 * android 的一个全局容器 用来存放全局数据
 * 
 * 
 */
public class Container {

	/**
	 * 单例变量
	 */
	public static String SESSION_ID = UUID.randomUUID().toString();

	private static Container container;

	private List<SortModel> mobileUrls = new ArrayList<SortModel>(); // html插件的url（标题，地址，图标，简介）
	private List<String> mobileApks = new ArrayList<String>(); // app 插件的内容
																// （标题，地址，图标，简介）
	HashMap<Integer, SortModel> hashMap = new HashMap<Integer, SortModel>();
	private List list;

	private String currentUrl; // 当前页面显示的URL地址

	public static final int TAG = 0;
	public static final int SORT = 0;
	public static final int TASK = 0;
	/**
	 * 用来存放一些键值对
	 */
	private Map<String, Object> session;

	public static Container getInstance() {
		if (container == null) {
			container = new Container();
		}
		return container;
	}

	/**
	 * 获取选择的对象集合
	 * 
	 * @return
	 */
	public List<SortModel> getTagSortModel() {
		return mobileUrls;
	}

	public void setTagSortModle(SortModel s) {
		mobileUrls.add(s);
	}

	/**
	 * 获取选择的对象集合
	 * 
	 * @return
	 */
	public HashMap<Integer, SortModel> getTaghashSortModel() {
		return hashMap;
	}

	public void setTaghashSortModle(int id, SortModel s) {
		hashMap.put(id, s);
	}

	public Map<String, Object> getSession() {
		return session;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

}
