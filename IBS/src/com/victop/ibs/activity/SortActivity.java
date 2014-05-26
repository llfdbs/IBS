package com.victop.ibs.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.victop.ibs.adapter.Sort_ListViewAdapter;
import com.victop.ibs.app.IBSApplication;
import com.victop.ibs.base.ActivityBase;
import com.victop.ibs.bean.SortBean;
import com.victop.ibs.handler.BaseHandler;
import com.victop.ibs.presenter.Getpresenter;
import com.victop.ibs.util.Container;
import com.victop.ibs.view.CustomViewPager;

/**
 * 素材分类
 * 
 * @author vv
 * 
 */
public class SortActivity extends ActivityBase implements OnClickListener {
	public static final int STYP_NUM = 3;
	Sort_ListViewAdapter adapter;

	final String[] data = { "erp", "互联网", "互联网" };
	private ArrayList<View> mViews = null;

	private CustomViewPager pager;

	private MyPageAdapter myAdapter;
	View view;
	private String info = "";
	private ActionBar actionBar;// 导航栏
	private MenuItem search, add, save;// 搜索,添加，保存按钮
	private List<SortBean> mSortBean = new ArrayList<SortBean>();

	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		final View view = View.inflate(this, R.layout.sortlayout, null);
		setContentView(view);
		IBSApplication.getInstance().addActivity(this);
		initData();
		initViews();
		initListeners();

	}

	Handler mHandler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			switch (msg.what) {
			case 0:// 获取到数据
				Map<String, List> dataMap = (Map<String, List>) msg.obj;
				mSortBean = (List<SortBean>) dataMap.get("1");

				// Datajson obj = null;
				// obj = (Datajson) getXmlStream("sort.xml", SortBean.class,
				// Datajson.class);
				// mSortBean = obj.getmSortBean();

				final String parentid = "0";
				List<SortBean> firstList_ = new ArrayList<SortBean>();
				for (SortBean s : mSortBean) {
					if (s.getParentid().equals(parentid)) {
						firstList_.add(s);
					}
				}
				HashMap<String, String> text = new HashMap<String, String>();
				HashMap<String, List<SortBean>> twoList = new HashMap<String, List<SortBean>>();
				String yy = "";
				for (SortBean ss : firstList_) {
					List<SortBean> sort = new ArrayList<SortBean>();
					for (SortBean s : mSortBean) {

						if (s.getParentid().equals(ss.getClassid())) {
							sort.add(s);
							if (!"".equals(yy))
								yy = yy + "/" + s.getClassname();
							else {
								yy = s.getClassname();
							}
						}
						text.put(ss.getClassid(), yy);
						twoList.put(ss.getClassid(), sort);
					}
				}

				HashMap<String, HashMap<String, List<SortBean>>> threeList = new HashMap<String, HashMap<String, List<SortBean>>>();
				Set<String> keys = twoList.keySet();
				Iterator<String> iterator = keys.iterator();
				while (iterator.hasNext()) {
					String key = iterator.next();
					HashMap<String, List<SortBean>> map = new HashMap<String, List<SortBean>>();
					for (SortBean s : twoList.get(key)) {
						List<SortBean> sort = new ArrayList<SortBean>();
						for (SortBean ss : mSortBean) {
							if (ss.getParentid().equals(s.getClassid())) {
								sort.add(ss);
							}
						}
						map.put(s.getClassid(), sort);
					}
					threeList.put(key, map);
				}

				initListViews(firstList_, twoList, threeList, text);
				myAdapter = new MyPageAdapter(mViews);// 构造adapter
				pager.setAdapter(myAdapter);// 设置适配器
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
	protected void initData() {
		// TODO Auto-generated method stub

		BaseHandler bHandler = new BaseHandler(this, mHandler);
		Map<String, Class> clsMap = new HashMap<String, Class>();
		clsMap.put(SortBean.datasetId, SortBean.class);
		Getpresenter.getInstance().getInitbData(bHandler, clsMap, null,
				SortBean.modelId, SortBean.datasetId, null,SortBean.formId);
	}

	@Override
	protected void initViews() {
		// TODO Auto-generated method stub
		actionBar = getSupportActionBar();
		actionBar.setTitle("");
		actionBar.setHomeButtonEnabled(true);
		actionBar.setIcon(R.drawable.btn_back);
		pager = (CustomViewPager) findViewById(R.id.viewpager);
		// pager.setOnPageChangeListener(pageChangeListener);// 设置页面滑动监听

	}

	private void initView(final List<SortBean> list,
			final HashMap<String, List<SortBean>> tlist) {
		int tag;
		if (null != tlist && tlist.size() > 0) {
			tag = 0;
		} else {
			tag = 1;
		}

		if (tag == 1) {// 只有两级
			if (mViews == null)
				mViews = new ArrayList<View>();
			view = getLayoutInflater().inflate(R.layout.sort_listview, null,
					false);
			ListView listView = (ListView) view.findViewById(R.id.lv_sorttype);

			adapter = new Sort_ListViewAdapter(this, list, null);
			listView.setAdapter(adapter);
			listView.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1,
						int arg2, long arg3) {
					TextView title = (TextView) arg1
							.findViewById(R.id.tv_title);
					info = info + ">" + title.getText();
					Intent i = new Intent(SortActivity.this,
							MaterialAddActivity.class);
					Bundle b = new Bundle();
					b.putString("info", info);
					i.putExtras(b);
					setResult(Container.SORT, i);
					finish();

					// pager.setCurrentItem(2);
					// initView(1, null);
				}

			});
			mViews.add(view);// 添加view
		} else if (tag == 0) {// 三级
			if (mViews == null)
				mViews = new ArrayList<View>();
			view = getLayoutInflater().inflate(R.layout.sort_listview, null,
					false);
			ListView listView = (ListView) view.findViewById(R.id.lv_sorttype);

			adapter = new Sort_ListViewAdapter(this, list, null);
			listView.setAdapter(adapter);
			listView.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1,
						int arg2, long arg3) {
					TextView title = (TextView) arg1
							.findViewById(R.id.tv_title);
					info = info + ">" + title.getText();
					List<SortBean> slist = tlist.get(list.get(arg2)
							.getClassid());

					if (slist.size() > 0) {
						initView(slist, null);
						pager.setCurrentItem(2);
					} else {

						Intent i = new Intent(SortActivity.this,
								MaterialAddActivity.class);
						Bundle b = new Bundle();
						b.putString("info", info);
						i.putExtras(b);
						setResult(Container.SORT, i);
						finish();

					}

				}

			});
			mViews.add(view);// 添加view
		}

		myAdapter.setListViews(mViews);// 重构adapter对象
		myAdapter.notifyDataSetChanged();// 刷新
	}

	@Override
	protected void initListeners() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

	}

	/**
	 * listViews添加view对象
	 * 
	 * @param count
	 */
	private void initListViews(final List<SortBean> list,
			final HashMap<String, List<SortBean>> list1,
			final HashMap<String, HashMap<String, List<SortBean>>> threeList,
			HashMap<String, String> tag) {
		if (mViews == null)
			mViews = new ArrayList<View>();
		view = getLayoutInflater().inflate(R.layout.sort_listview, null, false);
		ListView listView = (ListView) view.findViewById(R.id.lv_sorttype);

		adapter = new Sort_ListViewAdapter(this, list, tag);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				TextView title = (TextView) arg1.findViewById(R.id.tv_title);
				List<SortBean> mlist = list1.get(list.get(arg2).getClassid());// 二级列表
				HashMap<String, List<SortBean>> tlist = threeList.get(list.get(
						arg2).getClassid());

				info = ">" + title.getText();
				initView(mlist, tlist);
				pager.setCurrentItem(1);

			}

		});
		mViews.add(view);// 添加view
	}

	/**
	 * 页面监听事件
	 */
	private OnPageChangeListener pageChangeListener = new OnPageChangeListener() {

		public void onPageSelected(int arg0) {// 页面选择响应函数
			// 如果需要实现页面滑动时动态添加 请在此判断arg0的值
			// 当然此方式在必须在初始化ViewPager的时候给的页数必须>2
			// 因为给1页的话 ViewPager是响应不了此函数的
			// 例：
			// if (arg0 == pager.getAdapter().getCount() - 1) {// 滑动到最后一页
			// // count++;
			// initListViews(count);// listViews添加数据
			// adapter.setListViews(listViews);// 重构adapter对象
			// adapter.notifyDataSetChanged();// 刷新
			// }

		}

		public void onPageScrolled(int arg0, float arg1, int arg2) {// 滑动中。。。

		}

		public void onPageScrollStateChanged(int arg0) {// 滑动状态改变

		}
	};

	/**
	 * ViewPager适配器
	 * 
	 * @author zhou
	 * 
	 */
	class MyPageAdapter extends PagerAdapter {

		private ArrayList<View> listViews;// content

		private int size;// 页数

		public MyPageAdapter(ArrayList<View> listViews) {// 构造函数
															// 初始化viewpager的时候给的一个页面
			this.listViews = listViews;
			size = listViews == null ? 0 : listViews.size();
		}

		public void setListViews(ArrayList<View> listViews) {// 自己写的一个方法用来添加数据
			this.listViews = listViews;
			size = listViews == null ? 0 : listViews.size();
		}

		@Override
		public int getCount() {// 返回数量
			return size;
		}

		@Override
		public void destroyItem(View arg0, int arg1, Object arg2) {// 销毁view对象
			((ViewPager) arg0).removeView(listViews.get(arg1 % size));
		}

		@Override
		public void finishUpdate(View arg0) {
		}

		@Override
		public Object instantiateItem(View arg0, int arg1) {// 返回view对象
			try {
				((ViewPager) arg0).addView(listViews.get(arg1 % size), 0);
			} catch (Exception e) {
				Log.e("zhou", "exception：" + e.getMessage());
			}
			return listViews.get(arg1 % size);
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == arg1;
		}

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
		case R.id.search:
			break;
		case R.id.add:

			break;
		case R.id.save:

			break;
		}

		return super.onOptionsItemSelected(item);
	}
}
