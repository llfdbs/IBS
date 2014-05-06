package com.victop.ibs.activity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import android.content.Intent;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.victop.ibs.adapter.ListImagesAdapter;
import com.victop.ibs.app.ibsApplication;
import com.victop.ibs.base.ActivityBase;
import com.victop.ibs.util.Container;
import com.victop.ibs.view.BaseSwipeListViewListener;
import com.victop.ibs.view.SwipeListView;

/**
 * 图片展示类
 * 
 * @author Administrator
 * 
 */
public class ImgShowActivity extends ActivityBase implements OnClickListener {
	private Button btn_back, btn_add, btn_notsearch;
	private TextView tv_title = null;
	private SwipeListView listView;
	private ArrayList<String> listfile = new ArrayList<String>();
	ListImagesAdapter adapter;
	private List<Map<String, String>> mData = new ArrayList<Map<String, String>>();
	public static int deviceWidth;
	public static final String EDIT = "edit";
	private String edit_mat = "";

	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		final View view = View.inflate(this, R.layout.imgshowlayout, null);
		setContentView(view);
		ibsApplication.getInstance().addActivity(this);
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
			Intent ii = new Intent(ImgShowActivity.this,
					MaterialAddActivity.class);
			startActivity(ii);
			finish();
			break;

		}
	}

	@Override
	protected void initData() {
		// TODO Auto-generated method stub
		btn_back = (Button) findViewById(R.id.back);
		tv_title = (TextView) findViewById(R.id.title);
		btn_add = (Button) findViewById(R.id.add);
		// btn_add.setBackgroundResource(R.drawable.btn_add);
		btn_add.setText("确定");
		// btn_add.setVisibility(View.GONE);
		btn_back.setOnClickListener(this);
		btn_add.setOnClickListener(this);

		btn_notsearch = (Button) findViewById(R.id.search);
		btn_notsearch.setVisibility(View.GONE);
	}

	@Override
	protected void initViews() {
		// TODO Auto-generated method stub
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
			sp.setSpan(new ForegroundColorSpan(0xff0079fa), 3, 4,
					Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
			tv_title.setText(sp);
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

			for (int position : reverseSortedPositions) {
				System.out.println(position);
				Container.newData.remove(position);

			}
			SpannableString sp = new SpannableString("已选择"
					+ Container.newData.size() + "张");
			sp.setSpan(new ForegroundColorSpan(0xff0079fa), 3, 4,
					Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
			tv_title.setText(sp);
			adapter.notifyDataSetChanged();
		}

		@Override
		public void onMove(int position, float x) {
			// TODO Auto-generated method stub

			System.out.println(x + ".....................");

			super.onMove(position, x);
		}

		EditText et;
		TextView text;

		@Override
		public void onOpened(int position, boolean toRight) {
			// TODO Auto-generated method stub
			super.onOpened(position, toRight);
			System.out.println("onOpened");
			et = (EditText) listView.getChildAt(position).findViewById(
					R.id.edit);
			text = (TextView) listView.getChildAt(position).findViewById(
					R.id.text);
			et.setVisibility(View.GONE);
			text.setVisibility(View.VISIBLE);
			text.setText(et.getText().toString());
		}

		@Override
		public void onClosed(int position, boolean fromRight) {
			// TODO Auto-generated method stub
			super.onClosed(position, fromRight);

			et.setVisibility(View.VISIBLE);
			text.setVisibility(View.GONE);
		}

		@Override
		public void onStartClose(int position, boolean right) {
			// TODO Auto-generated method stub
			super.onStartClose(position, right);
			System.out.println("onStartClose");
		}

	}
}
