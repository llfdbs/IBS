package com.victop.ibs.activity;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.widget.Button;

import com.victop.ibs.app.IBSApplication;
import com.victop.ibs.base.ActivityBase;

/**
 * 欢迎页
 * 
 * @author vv
 * 
 */
public class WelcomeActivity extends ActivityBase{
	private Button btn_jump = null;

	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		final View view = View.inflate(this, R.layout.welcome, null);
		// 设置无标题
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		// 设置全屏
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

		setContentView(view);
//		ibsApplication.getInstance().addActivity(this);
		initData();
		//initViews();
		//initListeners();
		MyAnimation(view);
//		new Handler().postDelayed(new Runnable() {
//			
//			@Override
//			public void run() {
//				// TODO Auto-generated method stub
//				Intent intent = new Intent(WelcomeActivity.this,LoginActivity.class);
//				startActivity(intent);
//				finish();
//				
//			}
//		}, 1000);

	}

	@Override
	protected void initData() {
		// TODO Auto-generated method stub

	}


	/**
	 * 启动动画类
	 */
	private void MyAnimation(View view) {
		// 渐变展示启动屏,从透明到不透明
		AlphaAnimation aa = new AlphaAnimation(0.3f, 1.0f);
		// 持续时间3秒
		aa.setDuration(1000);
		view.startAnimation(aa);
		aa.setAnimationListener(new AnimationListener() {
			@Override
			public void onAnimationEnd(Animation arg0) {
				// 动画结束时跳转页面
				redirectTo();
			}

			@Override
			public void onAnimationRepeat(Animation animation) {
			}

			@Override
			public void onAnimationStart(Animation animation) {
			}

		});
	}

	/**
	 * 跳转到... 销毁当前欢迎页
	 */
	private void redirectTo() {
		openActivity(LoginActivity.class);
		WelcomeActivity.this.finish();
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
