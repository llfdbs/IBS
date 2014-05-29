package com.victop.ibs.activity;

import java.io.IOException;
import java.util.Properties;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.victop.android.bean.User;
import com.victop.android.datachannel.DataChannelManager;
import com.victop.android.session.MessageType;
import com.victop.ibs.app.IBSApplication;
import com.victop.ibs.base.ActivityBase;
import com.victop.ibs.handler.LoginHandler;
import com.victop.ibs.util.Container;




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
	private ActionBar actionBar;
	public static String TAG = LoginActivity.class.getSimpleName();
	public ProgressDialog loginProgressDialog;
	private Handler loginHandler;
	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		final View view = View.inflate(this, R.layout.loginlayout, null);
		setContentView(view);
		IBSApplication.getInstance().addActivity(this);
		
		initViews();
		initData();
		initListeners();

	}

	@Override
	protected void initData() {
		// TODO Auto-generated method stub
		initServer();
	}

	@Override
	protected void initViews() {
		// TODO Auto-generated method stub
		actionBar = getSupportActionBar();
		actionBar.hide();
		edt_username = (EditText) findViewById(R.id.edt_username);
		edt_password = (EditText) findViewById(R.id.edt_password);
		btn_login = (Button) findViewById(R.id.btn_login);
		loginProgressDialog = new ProgressDialog(this);
		loginProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);// 设置风格为圆形进度条
		loginProgressDialog.setTitle("请稍等");
		loginProgressDialog.setMessage("正在登录...");
		loginProgressDialog.setCancelable(true);
		loginProgressDialog.setIndeterminate(false);// 设置进度条是否为不明确
		loginHandler = new LoginHandler(this);
	}
	/**
	 * 初始化服务
	 */
	private void initServer() {
		initProperties();
		Container.getInstance();
		// SoundPoolManager.initSoundPool(getApplicationContext());
	}

	private void initProperties() {
		Properties properties = new Properties();
		try {
			properties.load(getAssets().open("msgType.properties"));
			Log.d(TAG, "获取配置文件msgType.properties 成功");
		} catch (IOException e) {
			Log.d(TAG, "获取配置文件msgType.properties 失败");
		}
		MessageType.getInstance().setProperties(properties);
	}
	// 登陆按钮的点击事件
	OnClickListener mOnClick = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			if (validate()) {
				loginProgressDialog.show();
				final User user = new User();
				user.setClientId("ibs");
				user.setUserCode(userName);
				user.setUserPwd(passWord);
				 new Thread(new Runnable() {
						
						@Override
						public void run() {
							// TODO Auto-generated method stub
							DataChannelManager.getInstance().login(user, loginHandler);
						}
					}).start();
				//openActivity(MainActivity.class, null);
				//finish();
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
			return false;
		}
		if ("".equals(passWord) || null == passWord) {
			Toast.makeText(LoginActivity.this, "密码不能为空,请输入密码!",
					Toast.LENGTH_SHORT).show();
			return false;
		}
		return true;
	}

}
