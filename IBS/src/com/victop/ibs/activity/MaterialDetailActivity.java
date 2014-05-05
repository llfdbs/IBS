package com.victop.ibs.activity;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import com.victop.ibs.adapter.ImagePagerAdapter;
import com.victop.ibs.adapter.MaterialDetail_ImageAdapter;
import com.victop.ibs.app.ibsApplication;
import com.victop.ibs.base.ActivityBase;
import com.victop.ibs.view.MyGallery;

/**
 * 素材详情类 素材详情类业务逻辑
 * 
 * @author vv
 * 
 */
public class MaterialDetailActivity extends ActivityBase {
	public List<String> urls;
	public MyGallery images_ga;
	private int positon = 0;
	Uri uri;
	Intent intent;
	int gallerypisition = 0;

	private Thread timeThread = null;
	public static boolean timeFlag = true;
	private boolean isExit = false;
	public ImageTimerTask timeTaks = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.materialdetail);
		initViews();
		ibsApplication.getInstance().addActivity(this);
		timeTaks = new ImageTimerTask();

		autoGallery.scheduleAtFixedRate(timeTaks, 5000, 5000);
		timeThread = new Thread() {
			public void run() {
				while (!isExit) {
					try {
						Thread.sleep(1500);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					synchronized (timeTaks) {
						if (!timeFlag) {
							timeTaks.timeCondition = true;
							timeTaks.notifyAll();
						}
					}
					timeFlag = true;
				}
			};
		};
		timeThread.start();
	}

	final Handler autoGalleryHandler = new Handler() {
		public void handleMessage(Message message) {
			super.handleMessage(message);
			switch (message.what) {
			case 1:
				images_ga.setSelection(message.getData().getInt("pos"));
				break;
			}
		}
	};

	@Override
	protected void initData() {
		// TODO Auto-generated method stub
	}

	@Override
	protected void initViews() {
		// TODO Auto-generated method stub
		Bitmap image = BitmapFactory.decodeResource(getResources(),
				R.drawable.ic_launcher);
		// imagesCache.put("background_non_load",image); //设置缓存中默认的图片

		images_ga = (MyGallery) findViewById(R.id.image_wall_gallery);
		images_ga.setImageActivity(this);

		MaterialDetail_ImageAdapter imageAdapter = new MaterialDetail_ImageAdapter(
				this);
		images_ga.setAdapter(imageAdapter);
		LinearLayout pointLinear = (LinearLayout) findViewById(R.id.gallery_point_linear);
		// pointLinear.setBackgroundColor(Color.argb(200, 135, 135, 152));
		for (int i = 0; i < 3; i++) {
			ImageView pointView = new ImageView(this);
			if (i == 0) {
				pointView.setBackgroundResource(R.drawable.feature_point_cur);
			} else
				pointView.setBackgroundResource(R.drawable.feature_point);
			pointLinear.addView(pointView);
		}
		images_ga.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				System.out.println(arg2 + "arg2");
				showDialogs(arg2);

			}
		});

	}

	public void showDialogs(int pagerPosition) {
		Dialog dialog = new Dialog(MaterialDetailActivity.this,
				R.style.taskdialog);
		View view = LayoutInflater.from(MaterialDetailActivity.this).inflate(
				R.layout.showpicbigdialog, null);
		dialog.setContentView(view);
		ViewPager pager;
		pager = (ViewPager) view.findViewById(R.id.pager);
		pager.setAdapter(new ImagePagerAdapter(MaterialDetailActivity.this,
				MaterialDetail_ImageAdapter.imgs));
		pager.setCurrentItem(pagerPosition);
		dialog.show();
	}

	public void changePointView(int cur) {
		LinearLayout pointLinear = (LinearLayout) findViewById(R.id.gallery_point_linear);
		View view = pointLinear.getChildAt(positon);
		View curView = pointLinear.getChildAt(cur);
		if (view != null && curView != null) {
			ImageView pointView = (ImageView) view;
			ImageView curPointView = (ImageView) curView;
			pointView.setBackgroundResource(R.drawable.feature_point);
			curPointView.setBackgroundResource(R.drawable.feature_point_cur);
			positon = cur;
		}
	}

	@Override
	protected void initListeners() {
		// TODO Auto-generated method stub

	}

	class ImageTimerTask extends TimerTask {
		public volatile boolean timeCondition = true;

		// int gallerypisition = 0;
		public void run() {
			synchronized (this) {
				while (!timeCondition) {
					try {
						Thread.sleep(100);
						wait();
					} catch (InterruptedException e) {
						Thread.interrupted();
					}
				}
			}
			try {
				gallerypisition = images_ga.getSelectedItemPosition() + 1;
				System.out.println(gallerypisition + "");
				Message msg = new Message();
				Bundle date = new Bundle();// 存放数据
				date.putInt("pos", gallerypisition);
				msg.setData(date);
				msg.what = 1;// 消息标识
				autoGalleryHandler.sendMessage(msg);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	Timer autoGallery = new Timer();
}
