package com.victop.ibs.activity;

import android.os.Bundle;

import com.victop.ibs.app.ibsApplication;
import com.victop.ibs.base.ActivityBase;

public class AddTaskActivity extends ActivityBase {

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.addtask);
		ibsApplication.getInstance().addActivity(this);
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
