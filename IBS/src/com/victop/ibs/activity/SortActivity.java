package com.victop.ibs.activity;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.victop.ibs.adapter.Sort_ListViewAdapter;
import com.victop.ibs.base.ActivityBase;
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
	private Button btn_back, btn_add, btn_notsearch;
	private TextView tv_title = null;
	private ArrayList<View> mViews = null;

	private CustomViewPager pager;

	private MyPageAdapter myAdapter;
	View view;
	private String info = "";

	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		final View view = View.inflate(this, R.layout.sortlayout, null);
		setContentView(view);
		// ibsApplication.getInstance().addActivity(this);
		initData();
		initViews();
		initListeners();

	}

	@Override
	protected void initData() {
		// TODO Auto-generated method stub
		btn_back = (Button) findViewById(R.id.back);
		tv_title = (TextView) findViewById(R.id.title);
		btn_add = (Button) findViewById(R.id.add);
		// btn_add.setBackgroundResource(R.drawable.btn_add);
		btn_add.setVisibility(View.GONE);
		btn_back.setOnClickListener(this);
		btn_add.setOnClickListener(this);
		// tv_title.setText("");
		btn_notsearch = (Button) findViewById(R.id.search);
		btn_notsearch.setVisibility(View.GONE);
	}

	@Override
	protected void initViews() {
		// TODO Auto-generated method stub

		pager = (CustomViewPager) findViewById(R.id.viewpager);
		// pager.setOnPageChangeListener(pageChangeListener);// 设置页面滑动监听
		initListViews(0);
		myAdapter = new MyPageAdapter(mViews);// 构造adapter
		pager.setAdapter(myAdapter);// 设置适配器
	}

	private void initView(int tag, List list) {
		if (tag == 1) {// 只有两级
			if (mViews == null)
				mViews = new ArrayList<View>();
			view = getLayoutInflater().inflate(R.layout.sort_listview, null,
					false);
			ListView listView = (ListView) view.findViewById(R.id.lv_sorttype);

			adapter = new Sort_ListViewAdapter(this, null, 1);
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

			adapter = new Sort_ListViewAdapter(this, null, 0);
			listView.setAdapter(adapter);
			listView.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1,
						int arg2, long arg3) {
					TextView title = (TextView) arg1
							.findViewById(R.id.tv_title);
					info = info + ">" + title.getText();

					initView(1, null);
					pager.setCurrentItem(2);

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
		switch (v.getId()) {
		case R.id.back:
			finish();
			break;
		case R.id.add:
			finish();
			break;

		}
	}

	/**
	 * listViews添加view对象
	 * 
	 * @param count
	 */
	private void initListViews(int tag) {
		if (mViews == null)
			mViews = new ArrayList<View>();
		view = getLayoutInflater().inflate(R.layout.sort_listview, null, false);
		ListView listView = (ListView) view.findViewById(R.id.lv_sorttype);

		adapter = new Sort_ListViewAdapter(this, null, tag);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				TextView title = (TextView) arg1.findViewById(R.id.tv_title);
				switch (arg2) {
				case 0:
					info = ">" + title.getText();
					// btn_add.setVisibility(View.VISIBLE);
					// btn_add.setText("保存");
					initView(0, null);
					pager.setCurrentItem(1);
					break;
				case 1:
					info = ">" + title.getText();
					// btn_add.setVisibility(View.VISIBLE);
					// btn_add.setText("保存");
					initView(1, null);
					pager.setCurrentItem(1);
					break;
				default:
					info = ">" + title.getText();
					// btn_add.setVisibility(View.VISIBLE);
					// btn_add.setText("保存");
					initView(1, null);
					pager.setCurrentItem(1);

					break;
				}

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
}
