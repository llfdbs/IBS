package com.victop.ibs.activity;

import android.os.Bundle;
import android.view.View;

import com.victop.ibs.base.ActivityBase;

/**
 * 素材分类
 * 
 * @author vv
 * 
 */
public class SortActivity extends ActivityBase {
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

	}

	@Override
	protected void initViews() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void initListeners() {
		// TODO Auto-generated method stub

	}

}
