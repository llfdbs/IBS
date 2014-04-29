package com.victop.ibs.activity;

import java.util.List;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.victop.ibs.adapter.Sort_ListViewAdapter;
import com.victop.ibs.base.ActivityBase;

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
		ListView listview = (ListView) findViewById(R.id.lv_sorttype);
		adapter = new Sort_ListViewAdapter(this, null, 0);
		listview.setAdapter(adapter);
		listview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {

				btn_add.setVisibility(View.VISIBLE);
				btn_add.setText("保存");
				initView(1, null);

			}

		});
	}

	private void initView(int tag, List list) {
		ListView listview = (ListView) findViewById(R.id.lv_sorttype);
		adapter = new Sort_ListViewAdapter(this, null, tag);
		listview.setAdapter(adapter);
		listview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {

			}

		});
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
}
