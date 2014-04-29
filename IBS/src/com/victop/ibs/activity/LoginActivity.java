package com.victop.ibs.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.victop.ibs.base.ActivityBase;

/**
 * 登录类 登录类业务逻辑
 * 
 * @author vv
 * 
 */
public class LoginActivity extends ActivityBase{
    private EditText edt_username,edt_password;
    private Button btn_login;
	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		final View view = View.inflate(this, R.layout.loginlayout, null);
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
      edt_username = (EditText)findViewById(R.id.edt_username);
      edt_password = (EditText)findViewById(R.id.edt_password);
      btn_login = (Button)findViewById(R.id.btn_login);
	}
	//登陆按钮的点击事件
    OnClickListener mOnClick = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent intent = new Intent(LoginActivity.this,MainActivity.class);
			startActivity(intent);
			finish();
		}
	};
	@Override
	protected void initListeners() {
		// TODO Auto-generated method stub
		btn_login.setOnClickListener(mOnClick);
	}

}
