package com.victop.ibs.activity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextWatcher;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.victop.ibs.adapter.ListImagesAdapter;
import com.victop.ibs.app.IBSApplication;
import com.victop.ibs.base.ActivityBase;
import com.victop.ibs.util.Constants;
import com.victop.ibs.util.Container;
import com.victop.ibs.util.ImgCallBack;
import com.victop.ibs.util.Util;
import com.victop.ibs.view.BaseSwipeListViewListener;
import com.victop.ibs.view.SwipeListView;

/**
 * 图片展示类
 * 
 * @author Administrator
 * 
 */
public class ImgShowActivity extends ActivityBase implements OnClickListener {
	private SwipeListView listView;
	private ArrayList<String> listfile = new ArrayList<String>();
	ListImagesAdapter adapter;
	private List<Map<String, String>> mData = new ArrayList<Map<String, String>>();
	public static int deviceWidth;
	public static final String EDIT = "edit";
	private String edit_mat = "";
	EditText et;
	TextView text;
	private ActionBar actionBar;//导航栏
	private MenuItem search, add, save;//搜索,添加，保存按钮
	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		final View view = View.inflate(this, R.layout.imgshowlayout, null);
		setContentView(view);
		IBSApplication.getInstance().addActivity(this);
		Bundle bundle = getIntent().getExtras();
		if (bundle != null) {
			if (bundle.getStringArrayList("files") != null) {
				listfile = bundle.getStringArrayList("files");
			}

			if (bundle.get(EDIT) != null) {
				edit_mat = bundle.get(EDIT).toString();
			}
		}

		initData();
		initViews();
		initListeners();

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.back:
			Container.newData.clear();
			finish();
			break;
		case R.id.add:

			Container.add_mData.addAll(Container.newData);
			Container.newData.clear();
			// Container.mData.clear();

			openActivity(MaterialAddActivity.class, null);
			finish();
			break;

		}
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		Container.newData.clear();
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
		// listView.setOnItemClickListener(new OnItemClickListener() {
		//
		// @Override
		// public void onItemClick(AdapterView<?> arg0, View view, int arg2,
		// long arg3) {
		// // TODO Auto-generated method stub
		// Button
		// bn_del=(Button)view.findViewById(R.id.example_row_b_action_del);
		// }
		//
		// });
		deviceWidth = getDeviceWidth();

		// listView.setVisibility(View.VISIBLE);
		if (listfile != null) {
			// addData(Container.listfiles, listfile);
			adddata(Container.newData);
			SpannableString sp = new SpannableString("已选择"
					+ Container.newData.size() + "张");
			if (Container.newData.size() < 10) {
				sp.setSpan(new ForegroundColorSpan(0xff0079fa), 3, 4,
						Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
			} else if (Container.newData.size() < 100) {
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

	private void adddata(List<Map<String, String>> data) {
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
			System.out.println("onDismiss");
			int position_ = 0;
			for (int position : reverseSortedPositions) {
				position_ = position;
				Container.newData.remove(position);
				Container.et_list.remove(position);
				Container.tv_list.remove(position);
			}
			SpannableString sp = new SpannableString("已选择"
					+ Container.newData.size() + "张");
			if (Container.newData.size() < 10) {
				sp.setSpan(new ForegroundColorSpan(0xff0079fa), 3, 4,
						Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
			} else if (Container.newData.size() < 100) {
				sp.setSpan(new ForegroundColorSpan(0xff0079fa), 3, 5,
						Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
			}
			int start = listView.getFirstVisiblePosition();
			int end = listView.getLastVisiblePosition();
			for (int i = start; i < end; i++) {
				// if (i != position_) {
				Container.et_list.get(i).setVisibility(View.VISIBLE);
				Container.tv_list.get(i).setVisibility(View.GONE);
				// listView.closeAnimate(i);
				// }

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

			Toast.makeText(
					ImgShowActivity.this,
					position + " " + listView.getFirstVisiblePosition() + " "
							+ listView.getLastVisiblePosition(), 500).show();

			// et.setVisibility(View.GONE);
			// text.setVisibility(View.VISIBLE);
			// text.setText(et.getText().toString());
			int start = listView.getFirstVisiblePosition();
			int end = listView.getLastVisiblePosition();
			for (int i = start; i < end; i++) {
				if (i != position) {
					listView.closeAnimate(i);
					// System.out.println(Container.et_list.get(i).getTag()
					// .toString()
					// + "---------");
					Container.et_list.get(i).setVisibility(View.VISIBLE);
					Container.tv_list.get(i).setVisibility(View.GONE);
				} else {
					Container.et_list.get(i).setVisibility(View.GONE);
					// System.out.println(Container.et_list.get(i).getTag()
					// .toString()
					// + "=======");
					Container.tv_list.get(i).setVisibility(View.VISIBLE);
					Container.tv_list.get(i).setText(
							Container.et_list.get(i).getText().toString());
				}

			}
		}

		@Override
		public void onStartOpen(int position, int action, boolean right) {
			// TODO Auto-generated method stub
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

			Container.et_list.get(position).setVisibility(View.VISIBLE);
			Container.tv_list.get(position).setVisibility(View.GONE);

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
			
			Container.newData.clear();
			finish();
			
			break;
		
		case R.id.save:
			Container.add_mData.addAll(Container.newData);
			Container.newData.clear();
			// Container.mData.clear();

			openActivity(MaterialAddActivity.class, null);
			finish();
			break;
		}

		return super.onOptionsItemSelected(item);
	}
}