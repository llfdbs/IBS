package com.victop.ibs.activity;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

import com.victop.ibs.adapter.ImagePagerAdapter;
import com.victop.ibs.adapter.MaterialDetail_ImageAdapter;
import com.victop.ibs.app.IBSApplication;
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
	int gallerypisition = 0;
	private Thread timeThread = null;
	public static boolean timeFlag = true;
	private boolean isExit = false;
	public ImageTimerTask timeTaks = null;
	private ImageButton imgbtn_historyversion, imgbtn_edit, imgbtn_detail;// 历史版本,编辑,详情按钮
	private TextView tv_materialdetail_tag, tv_versioncode, tv_taskcode,
			tv_picname, tv_picdetail;// 分类,版本号,详情,图片名称,描述
	private Button btn_materialdetail_check;
	private LinearLayout pointLinear;
	private String str_tag,str_versioncode,str_taskcode;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.materialdetail);
		initViews();
		initData();
		initListeners();
		IBSApplication.getInstance().addActivity(this);
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
		images_ga.setImageActivity(this);

		MaterialDetail_ImageAdapter imageAdapter = new MaterialDetail_ImageAdapter(
				this);
		images_ga.setAdapter(imageAdapter);
		for (int i = 0; i < 3; i++) {
			ImageView pointView = new ImageView(this);
			if (i == 0) {
				pointView.setBackgroundResource(R.drawable.feature_point_cur);
			} else
				pointView.setBackgroundResource(R.drawable.feature_point);
			pointLinear.addView(pointView);
		}
	}

	@Override
	protected void initViews() {
		// TODO Auto-generated method stub
		images_ga = (MyGallery) findViewById(R.id.image_wall_gallery);
		pointLinear = (LinearLayout) findViewById(R.id.gallery_point_linear);
		imgbtn_historyversion = (ImageButton) findViewById(R.id.imgbtn_historyversion);
		imgbtn_edit = (ImageButton) findViewById(R.id.imgbtn_edit);
		imgbtn_detail = (ImageButton) findViewById(R.id.imgbtn_detail);
		tv_materialdetail_tag = (TextView)findViewById(R.id.tv_materialdetail_tag);
		tv_versioncode = (TextView)findViewById(R.id.tv_versioncode);
		tv_taskcode = (TextView)findViewById(R.id.tv_taskcode);
		tv_picname = (TextView)findViewById(R.id.tv_picname);
		tv_picdetail = (TextView)findViewById(R.id.tv_picdetail);
		btn_materialdetail_check = (Button)findViewById(R.id.btn_materialdetail_check);
		str_tag = tv_materialdetail_tag.getText().toString();
		str_versioncode = tv_taskcode.getText().toString();
		str_taskcode = tv_taskcode.getText().toString();
	}

	@Override
	protected void initListeners() {
		// TODO Auto-generated method stub
		//images_ga.setOnItemClickListener(mOnItemClick);
		imgbtn_historyversion.setOnClickListener(mOnClick);
		imgbtn_edit.setOnClickListener(mOnClick);
		imgbtn_detail.setOnClickListener(mOnClick);
		btn_materialdetail_check.setOnClickListener(mOnClick);
	}
    OnClickListener mOnClick = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch(v.getId()){
			case R.id.imgbtn_historyversion:
				openActivityForResult(HistoryVerActivity.class, null, 100);
				//openActivity(HistoryVerActivity.class, null);
				break;
			case R.id.imgbtn_edit:
				Bundle bundle = new Bundle();
				bundle.putString("info", str_tag);
				bundle.putString("tasknumber",str_versioncode);
				openActivity(MaterialAddActivity.class, bundle);
				break;
			case R.id.imgbtn_detail:
				openActivity(PropertyActivity.class);
				break;
			case R.id.btn_materialdetail_check:
				showCheckDialog();
				break;
			}
		}
	};
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		switch (requestCode) {
		case 100:
			if (data != null) {
				Bundle b = data.getExtras();
				String name = b.getString("versionname");
				tv_versioncode.setText(name);
			}

			break;
		}
	}
    OnItemClickListener mOnItemClick = new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			// TODO Auto-generated method stub
			showDialogs(arg2);
		}
	};
	// 审核弹出框
		public void showCheckDialog() {
			final Dialog dialog = new Dialog(MaterialDetailActivity.this,
					R.style.taskdialog);
			View view = LayoutInflater.from(MaterialDetailActivity.this).inflate(
					R.layout.checkdialog, null);
			dialog.setContentView(view);
			Button btn_pass = (Button) view.findViewById(R.id.btn_check_pass);
			Button btn_nopass = (Button) view.findViewById(R.id.btn_check_nopass);
			Button btn_taskpositive = (Button) view
					.findViewById(R.id.btn_taskpositive);
			dialog.show();
			btn_pass.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					dialog.dismiss();
					finish();
				}
			});
			btn_nopass.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					dialog.dismiss();
					finish();
					
				}
			});
			
		}
	// 弹出大图浏览
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
		LayoutParams lay = dialog.getWindow().getAttributes();
		setParams(lay);

		dialog.show();
	}

	// 自定义dialog全屏显示
	private void setParams(LayoutParams lay) {
		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		Rect rect = new Rect();
		View view = getWindow().getDecorView();
		view.getWindowVisibleDisplayFrame(rect);
		lay.height = dm.heightPixels - rect.top;
		lay.width = dm.widthPixels;
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
