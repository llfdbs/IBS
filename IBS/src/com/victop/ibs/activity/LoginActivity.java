package com.victop.ibs.activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.victop.ibs.base.ActivityBase;

/**
 * 登录类 登录类业务逻辑
 * 
 * @author vv
 * 
 */
public class LoginActivity extends ActivityBase {
	private EditText edt_username, edt_password;
	private Button btn_login;
	private String userName, passWord;

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
		edt_username = (EditText) findViewById(R.id.edt_username);
		edt_password = (EditText) findViewById(R.id.edt_password);
		btn_login = (Button) findViewById(R.id.btn_login);
	}

	// 登陆按钮的点击事件
	OnClickListener mOnClick = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			if (validate()) {
				openActivity(MainActivity.class, null);
				finish();
			}
		}
	};

	@Override
	protected void initListeners() {
		// TODO Auto-generated method stub

		btn_login.setOnClickListener(mOnClick);
	}

	// 校验用户名密码
	public boolean validate() {
		userName = edt_username.getText().toString().trim();
		passWord = edt_password.getText().toString().trim();
		if ("".equals(userName) || null == userName) {
			Toast.makeText(LoginActivity.this, "用户名不能为空,请输入用户名!",
					Toast.LENGTH_SHORT).show();
			;
			return false;
		}
		if ("".equals(passWord) || null == passWord) {
			Toast.makeText(LoginActivity.this, "密码不能为空,请输入密码!",
					Toast.LENGTH_SHORT).show();
			;
			return false;
		}
		return true;
	}

}
