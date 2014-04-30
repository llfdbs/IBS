package com.victop.ibs.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.victop.ibs.base.ActivityBase;
import com.victop.ibs.util.Container;

/**
 * 新增素材类
 * 
 * @author vv
 * 
 */
public class MaterialAddActivity extends ActivityBase implements
		OnClickListener {
	private Button btn_back, btn_add, btn_notsearch;
	private TextView tv_title = null;
	private LinearLayout llt_sort, llt_property, llt_task, llt_tag;
	private TextView tv_sort, tv_task, tv_tag;

	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		final View view = View.inflate(this, R.layout.materialadd, null);
		setContentView(view);
		// ibsApplication.getInstance().addActivity(this);
		initData();
		initViews();
		initListeners();

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.back:
			finish();
			break;
		case R.id.add:// 保存
			finish();
			break;
		case R.id.llt_sort:
			openActivityForResult(SortActivity.class, null, Container.SORT);

			break;
		case R.id.llt_property:
			openActivity(PropertyActivity.class, null);
			break;
		case R.id.llt_task:
			// openActivityForResult(TagActivity.class,null,Container.TASK);
			break;
		case R.id.llt_tag:
			openActivityForResult(TagActivity.class, null, Container.TAG);

			break;

		}
	}

	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		switch (requestCode) {
		// case REQUEST_CODE_01:
		// if (resultCode == Activity.RESULT_OK)
		// // do something
		// break;
		// case REQUEST_CODE_02:
		// // do something
		// break;
		}
	}

	@Override
	protected void initData() {
		// TODO Auto-generated method stub
		btn_back = (Button) findViewById(R.id.back);
		tv_title = (TextView) findViewById(R.id.title);
		btn_add = (Button) findViewById(R.id.add);
		// btn_add.setBackgroundResource(R.drawable.btn_add);
		// btn_add.setVisibility(View.GONE);
		btn_add.setText("保存");
		btn_back.setOnClickListener(this);
		btn_add.setOnClickListener(this);
		tv_title.setText("新增素材");
		btn_notsearch = (Button) findViewById(R.id.search);
		btn_notsearch.setVisibility(View.GONE);
	}

	@Override
	protected void initViews() {
		// TODO Auto-generated method stub
		llt_sort = (LinearLayout) findViewById(R.id.llt_sort);
		llt_property = (LinearLayout) findViewById(R.id.llt_property);
		llt_tag = (LinearLayout) findViewById(R.id.llt_task);
		llt_task = (LinearLayout) findViewById(R.id.llt_tag);

		tv_sort = (TextView) findViewById(R.id.tv_sort1);
		tv_task = (TextView) findViewById(R.id.tv_task1);
		tv_tag = (TextView) findViewById(R.id.tv_tag1);
		llt_sort.setOnClickListener(this);
		llt_property.setOnClickListener(this);
		llt_tag.setOnClickListener(this);
		llt_task.setOnClickListener(this);
	}

	@Override
	protected void initListeners() {
		// TODO Auto-generated method stub

	}

}
