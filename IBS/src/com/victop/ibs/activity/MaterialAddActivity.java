package com.victop.ibs.activity;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import com.victop.ibs.base.ActivityBase;

/**
 * 新增素材类
 * 
 * @author vv
 * 
 */
public class MaterialAddActivity extends ActivityBase implements
		OnClickListener {
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
