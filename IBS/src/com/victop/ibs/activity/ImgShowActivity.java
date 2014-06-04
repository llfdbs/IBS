package com.victop.ibs.activity;

import java.io.Serializable;
import java.util.List;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.victop.ibs.adapter.ListImagesAdapter;
import com.victop.ibs.app.IBSApplication;
import com.victop.ibs.base.ActivityBase;
import com.victop.ibs.bean.Entity;
import com.victop.ibs.util.MyContainer;
import com.victop.ibs.view.BaseSwipeListViewListener;
import com.victop.ibs.view.SwipeListView;

/**
 * 图片展示类
 * 
 * @author Administrator
 * 
 */
public class ImgShowActivity extends ActivityBase {
	private SwipeListView listView;
	// private ArrayList<String> listfile = new ArrayList<String>();
	ListImagesAdapter adapter;
	public static int deviceWidth;
	public static final String EDIT = "edit";
	EditText et;
	TextView text;

	private ActionBar actionBar;// 导航栏
	private MenuItem search, add, save;// 搜索,添加，保存按钮

	boolean isAPI = false;
	private final int API_4 = 14;
	public List<Entity> img_list = null;// 传递数据
	private String edit_icon = "";
	private int img_postion = -1;
	private String img_url = "";

	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		final View view = View.inflate(this, R.layout.imgshowlayout, null);
		setContentView(view);
		IBSApplication.getInstance().addActivity(this);
		Bundle bundle = getIntent().getExtras();
		if (bundle != null) {
			if (bundle.getSerializable("data") != null) {
				img_list = (List<Entity>) bundle.getSerializable("data");
			}

			if (bundle.get(EDIT) != null) {
				img_list = (List<Entity>) bundle.getSerializable("edit");
			}
			if (bundle.get("edit_icon") != null) {
				edit_icon = (String) bundle.getString("edit_icon");
			}

		}
		if (Build.VERSION.SDK_INT > API_4 || Build.VERSION.SDK_INT == API_4) {
			isAPI = true;
		}

		initData();
		initViews();
		initListeners();

	}

	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		MyContainer.et_Map.clear();
		MyContainer.tv_Map.clear();
		finish();

	}

	@Override
	protected void initData() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void initViews() {
		// TODO Auto-generated method stub
		actionBar = getSupportActionBar();
		actionBar.setTitle("");
		actionBar.setHomeButtonEnabled(true);
		actionBar.setIcon(R.drawable.btn_back);
		listView = (SwipeListView) findViewById(R.id.lv_imglist);

		deviceWidth = getDeviceWidth();

		// listView.setVisibility(View.VISIBLE);
		if (img_list != null) {
			// addData(Container.listfiles, listfile);
			adddata(img_list);
			SpannableString sp = new SpannableString("已选择" + img_list.size()
					+ "张");
			if (img_list.size() < 10) {
				sp.setSpan(new ForegroundColorSpan(0xff0079fa), 3, 4,
						Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
			} else if (img_list.size() < 100) {
				sp.setSpan(new ForegroundColorSpan(0xff0079fa), 3, 5,
						Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
			}

			actionBar.setTitle(sp);
		}

	}

	@Override
	protected void initListeners() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void onNewIntent(Intent intent) {
		// TODO Auto-generated method stub
		super.onNewIntent(intent);
		Bundle b = intent.getExtras();
		if (b.get("url") != null) {

			img_url = (String) b.get("url");
		}
		if (b.get("position") != null) {
			img_postion = Integer.valueOf(b.get("position").toString());
		}
		img_list.get(img_postion).setURL(img_url);
		adddata(img_list);
	}

	// public void addData(ArrayList<String> listfiles, ArrayList<String>
	// listfile) {
	// adapter = new ListImagesAdapter(ImgShowActivity.this, listfiles,
	// listView);
	// if (listfiles.size() > 0) {
	// listfiles.addAll(listfile);
	// for (int i = 0; i < listfile.size(); i++) {
	// Map<String, String> item = new HashMap<String, String>();
	// item.put("list_item_inputvalue", "");
	// Container.mData.add(item);
	// }
	// if (adapter != null) {
	// for (int i = 0; i < listfiles.size(); i++) {
	// Map<String, String> item = new HashMap<String, String>();
	// item.put("list_item_inputvalue", Container.mData.get(i)
	// .get("list_item_inputvalue"));
	// mData.add(item);
	// }
	// adapter.setData(mData);
	// listView.setAdapter(adapter);
	// listView.setSwipeListViewListener(new TestBaseSwipeListViewListener());
	// reload();
	// adapter.notifyDataSetChanged();
	// }
	// } else {
	// for (int j = 0; j < listfile.size(); j++) {
	// listfiles.add(listfiles.size(), listfile.get(j));
	// }
	//
	// for (int i = 0; i < listfiles.size(); i++) {
	// Map<String, String> item = new HashMap<String, String>();
	// item.put("list_item_inputvalue", "");
	// mData.add(item);
	// }
	// adapter.setData(mData);
	// listView.setAdapter(adapter);
	// listView.setSwipeListViewListener(new TestBaseSwipeListViewListener());
	// reload();
	// }
	//
	// }

	private void adddata(List<Entity> data) {
		adapter = new ListImagesAdapter(ImgShowActivity.this, data, listView);

		if (adapter != null) {

			listView.setAdapter(adapter);

			listView.setSwipeListViewListener(new TestBaseSwipeListViewListener());
			reload();
			adapter.notifyDataSetChanged();
		}

	}

	private int getDeviceWidth() {
		return getResources().getDisplayMetrics().widthPixels;
	}

	private void reload() {
		listView.setSwipeMode(SwipeListView.SWIPE_MODE_LEFT);
		listView.setSwipeActionLeft(SwipeListView.SWIPE_ACTION_REVEAL);
		// mSwipeListView.setSwipeActionRight(settings.getSwipeActionRight());
		listView.setOffsetLeft(deviceWidth * 1 / 3);
		// mSwipeListView.setOffsetRight(convertDpToPixel(settings.getSwipeOffsetRight()));
		listView.setAnimationTime(0);
		listView.setSwipeOpenOnLongPress(false);
	}

	class TestBaseSwipeListViewListener extends BaseSwipeListViewListener {

		@Override
		public void onClickFrontView(int position) {
			super.onClickFrontView(position);
			// Toast.makeText(ImgShowActivity.this,
			// Container.mData.get(position).toString(),
			// Toast.LENGTH_SHORT).show();
		}

		@Override
		public void onDismiss(int[] reverseSortedPositions) {

			for (int position : reverseSortedPositions) {
				MyContainer.et_Map.remove(position);
				MyContainer.tv_Map.remove(position);
				img_list.remove(position);
			}
			SpannableString sp = new SpannableString("已选择" + img_list.size()
					+ "张");
			if (img_list.size() < 10) {
				sp.setSpan(new ForegroundColorSpan(0xff0079fa), 3, 4,
						Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
			} else if (img_list.size() < 100) {
				sp.setSpan(new ForegroundColorSpan(0xff0079fa), 3, 5,
						Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
			}

			actionBar.setTitle(sp);

			adapter.notifyDataSetChanged();
		}

		@Override
		public void onMove(int position, float x) {
			// TODO Auto-generated method stub

			super.onMove(position, x);
		}

		@Override
		public void onOpened(int position, boolean toRight) {
			// TODO Auto-generated method stub
			super.onOpened(position, toRight);
			// if (isAPI)
			// return;

			int start = listView.getFirstVisiblePosition();
			int end = listView.getLastVisiblePosition();
			for (int i = start; i <= end; i++) {
				if (i != position) {
					listView.closeAnimate(i);
					MyContainer.et_Map.get(i).setVisibility(View.VISIBLE);
					// Container.et_list.get(i).setVisibility(View.VISIBLE);
					MyContainer.tv_Map.get(i).setVisibility(View.GONE);
				} else {
					// Container.et_list.get(i).setVisibility(View.GONE);
					MyContainer.et_Map.get(i).setVisibility(View.GONE);
					MyContainer.tv_Map.get(i).setVisibility(View.VISIBLE);
					MyContainer.tv_Map.get(i).setText(
							MyContainer.et_Map.get(i).getText().toString());
				}

			}

		}

		EditText et;
		TextView text;
		int pos_temp = -2;

		@Override
		public void onStartOpen(int position, int action, boolean right) {
			System.out.println("onStartOpen");
			// TODO Auto-generated method stub
			// int start = listView.getFirstVisiblePosition();
			// int end = listView.getLastVisiblePosition();
			// if (et != null && pos_temp != -2 && null != text) {
			// System.out.println("this is swipelistview potions ~~~"
			// + position + "  " + start + "  " + end + "  ");
			// if (pos_temp >= start && pos_temp - start <= end) {
			// listView.closeAnimate(pos_temp);
			// }
			// et.setVisibility(View.VISIBLE);
			// text.setVisibility(View.GONE);
			// et = null;
			// text = null;
			// pos_temp = -2;
			// }
			// pos_temp = position;
			// et = (EditText) listView.getChildAt(position -
			// start).findViewById(
			// R.id.edit);
			// text = (TextView) listView.getChildAt(position - start)
			// .findViewById(R.id.text);
			// et.setVisibility(View.GONE);
			// text.setVisibility(View.VISIBLE);
			super.onStartOpen(position, action, right);
		}

		@Override
		public int onChangeSwipeMode(int position) {
			// TODO Auto-generated method stub

			return super.onChangeSwipeMode(position);
		}

		@Override
		public void onClosed(int position, boolean fromRight) {
			// TODO Auto-generated method stub
			super.onClosed(position, fromRight);
			// if (isAPI)
			// return;
			MyContainer.et_Map.get(position).setVisibility(View.VISIBLE);
			MyContainer.tv_Map.get(position).setVisibility(View.GONE);

			// et.setVisibility(View.VISIBLE);
			// text.setVisibility(View.GONE);
		}

		@Override
		public void onListChanged() {
			// TODO Auto-generated method stub
			super.onListChanged();

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
		save.setVisible(true);
		save.setTitle("确定");
		save.setIcon(null);

		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		switch (item.getItemId()) {
		case android.R.id.home:

			MyContainer.et_Map.clear();
			MyContainer.tv_Map.clear();

			finish();
			break;

		case R.id.save:

			// Container.add_mData.addAll(Container.newData);
			// Container.newData.clear();
			// Container.mData.clear();
			if (edit_icon != null)
				if ("".equals(edit_icon)) {
					Bundle b = new Bundle();
					b.putSerializable("imgshow_data", (Serializable) img_list);
					openActivity(MaterialAddActivity.class, b);
				} else {// 编辑
					Bundle b = new Bundle();
					b.putSerializable("edit_imgshow_data",
							(Serializable) img_list);
					openActivity(MaterialAddActivity.class, b);

				}

			finish();
			break;
		}

		return super.onOptionsItemSelected(item);
	}
}