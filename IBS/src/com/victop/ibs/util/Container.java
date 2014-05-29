package com.victop.ibs.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import android.widget.EditText;
import android.widget.TextView;

import com.victop.ibs.bean.Entity;
import com.victop.ibs.bean.TagBean;

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
	public static ArrayList<String> listfiles = new ArrayList<String>();//

//	public static List<Map<String, String>> newData = new ArrayList<Map<String, String>>(); // x选择图片添加地址，描述为空
//	public static List<Map<String, String>> add_mData = new ArrayList<Map<String, String>>();// 素材新增
	private static Container container;
	private List<TagBean> mobileUrls = new ArrayList<TagBean>(); // html插件的url（标题，地址，图标，简介）
	List<Entity> mat_list = new ArrayList<Entity>(); // （标题，地址，图标，简介）
	HashMap<Integer, TagBean> hashMap = new HashMap<Integer, TagBean>();
	public static List<EditText> et_list = new ArrayList<EditText>();
	public static Map<Integer, EditText> et_Map = new HashMap<Integer, EditText>();
	public static Map<Integer, TextView> tv_Map = new HashMap<Integer, TextView>();
	public static List<TextView> tv_list = new ArrayList<TextView>();

	public static final int TAG = 0;
	public static final int SORT = 1;
	public static final int TASK = 2;
	public static final int PROPER = 3;
	public static final String STATUS_ALL = "0";//全部按钮选中标示
	public static final String STATUS_UNFINISH = "1";//未完成按钮选中标示
	public static final String STATUS_FINISH = "2";//已完成按钮选中标示
	public static final String STATUS_UNSEND = "3";//未发放按钮选中标示
	
	public static final int MODEL_ALL = 3;//任务全部标示
	public static final int MODEL_UNFINISH = 4;//任务未完成标示
	public static final int MODEL_FINISH = 5;//任务已完成标示
	public static final int MODEL_UNSEND = 6;//任务未发放标示
	public static final int S_MODEL_ALL = 7;
	public static final int S_MODEL_UNFINISH = 8;
	public static final int S_MODEL_FINISH = 9;
	public static final int PAGESIZE = 10;
	
	
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
	public List<TagBean> getTagSortModel() {
		return mobileUrls;
	}

	public void setTagSortModle(TagBean s) {
		mobileUrls.add(s);
	}

	/**
	 * 获取选择的对象集合
	 * 
	 * @return
	 */
	public HashMap<Integer, TagBean> getTaghashSortModel() {
		return hashMap;
	}

	public void setTaghashSortModle(int id, TagBean s) {
		hashMap.put(id, s);
	}

	public List<Entity> getEntityList() {
		return mat_list;
	}

	public void setEntityList(String url, String text) {
		Entity e = new Entity();
		e.setText(text);
		e.setURL(url);
		mat_list.add(e);
	}

	public Map<String, Object> getSession() {
		return session;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

}
