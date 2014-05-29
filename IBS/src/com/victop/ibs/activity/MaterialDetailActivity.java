package com.victop.ibs.activity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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
import android.support.v7.app.ActionBar;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.victop.ibs.adapter.MaterialDetail_ImageAdapter;
import com.victop.ibs.app.IBSApplication;
import com.victop.ibs.base.ActivityBase;
import com.victop.ibs.bean.MaterialDetailHistoryBean;
import com.victop.ibs.bean.MaterialDetailMessageBean;
import com.victop.ibs.bean.MaterialDetailPictureBean;
import com.victop.ibs.bean.MaterialDetailSortBean;
import com.victop.ibs.bean.MaterialDetailTagBean;
import com.victop.ibs.handler.MaterialDetailHandler;
import com.victop.ibs.presenter.MaterialDetailPresenter;
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
	private TextView tv_materialdetail_sort, tv_versioncode, tv_taskcode,
			tv_picname, tv_picdetail;// 分类,版本号,详情,图片名称,描述
	private Button btn_materialdetail_check;
	private LinearLayout pointLinear;
	private String str_tag, str_versioncode, str_taskcode;
	private ActionBar actionBar;// 导航栏
	private MenuItem search, add, save;// 搜索,添加，保存按钮
	private Map<String, List> dataMap;
	List<MaterialDetailMessageBean> mMaterialList = new ArrayList<MaterialDetailMessageBean>();// 任务详情
	List<MaterialDetailTagBean> mMaterialTagList;
	List<MaterialDetailSortBean> mMaterialSortList;
	List<MaterialDetailPictureBean> mMaterialPicList;
	List<MaterialDetailHistoryBean> mMaterialHistoryList;
	private MaterialDetailHandler materialDetailHandler;
	private String materialid = "";
	Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			switch (msg.what) {
			case 0:
				dataMap = (Map<String, List>) msg.obj;
				mMaterialList = dataMap.get("1");
				mMaterialTagList = dataMap.get("2");
				mMaterialSortList = dataMap.get("3");
				mMaterialPicList = dataMap.get("4");
				mMaterialHistoryList = dataMap.get("5");
				if (mMaterialList == null) {
					mMaterialList = new ArrayList<MaterialDetailMessageBean>();
				}
				if (mMaterialTagList == null) {
					mMaterialTagList = new ArrayList<MaterialDetailTagBean>();
				}
				if (mMaterialSortList == null) {
					mMaterialSortList = new ArrayList<MaterialDetailSortBean>();
				}
				if (mMaterialPicList == null) {
					mMaterialPicList = new ArrayList<MaterialDetailPictureBean>();
				}
				if (mMaterialHistoryList == null) {
					mMaterialHistoryList = new ArrayList<MaterialDetailHistoryBean>();
				}

				// 数据填充
				// tv_materialdetail_tag.setText(materialDetailMessageBean.getTaskcode());
				tv_versioncode.setText(mMaterialList.get(0).getVersioncode());
				tv_taskcode.setText(mMaterialList.get(0).getTaskcode());
				tv_picdetail.setText(mMaterialList.get(0).getMaterialmemo());
				if (mMaterialHistoryList.size()>0){
				tv_materialdetail_sort.setText(mMaterialSortList.get(0).getClassname());
				}else{
					tv_materialdetail_sort.setText("");	
				}
				if(mMaterialPicList.size()>0){
					tv_picname.setText(mMaterialPicList.get(0).getImgname());
				}else{
					tv_picname.setText("");
				}
				images_ga.setSelection(mMaterialPicList.size());
				// 轮播图片
				images_ga.setImageActivity(MaterialDetailActivity.this);
				if (mMaterialPicList.size() > 0) {
					MaterialDetail_ImageAdapter imageAdapter = new MaterialDetail_ImageAdapter(
							MaterialDetailActivity.this, mMaterialPicList);
					images_ga.setAdapter(imageAdapter);
					for (int i = 0; i < mMaterialPicList.size(); i++) {
						ImageView pointView = new ImageView(
								MaterialDetailActivity.this);
						if (i == 0) {
							pointView
									.setBackgroundResource(R.drawable.feature_point_cur);
						} else
							pointView
									.setBackgroundResource(R.drawable.feature_point);
						pointLinear.addView(pointView);
					}
				}

			}
			super.handleMessage(msg);
		}

	};

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
		Bundle bundle = getIntent().getExtras();
		materialid = bundle.getString("materialid");
		materialDetailHandler = new MaterialDetailHandler(MaterialDetailActivity.this,
				handler);
		MaterialDetailPresenter materialDetailPresenter = new MaterialDetailPresenter();
		materialDetailPresenter.getInitData(materialDetailHandler,materialid);
		// TODO Auto-generated method stub
//		images_ga.setImageActivity(this);
//
//		MaterialDetail_ImageAdapter imageAdapter = new MaterialDetail_ImageAdapter(
//				this);
//		images_ga.setAdapter(imageAdapter);
//		for (int i = 0; i < 3; i++) {
//			ImageView pointView = new ImageView(this);
//			if (i == 0) {
//				pointView.setBackgroundResource(R.drawable.feature_point_cur);
//			} else
//				pointView.setBackgroundResource(R.drawable.feature_point);
//			pointLinear.addView(pointView);
//		}
	}

	@Override
	protected void initViews() {
		// TODO Auto-generated method stub
		actionBar = getSupportActionBar();
		actionBar.setTitle(getResources().getString(R.string.materialdetail));
		actionBar.setHomeButtonEnabled(true);
		actionBar.setIcon(R.drawable.btn_back);
		images_ga = (MyGallery) findViewById(R.id.image_wall_gallery);
		pointLinear = (LinearLayout) findViewById(R.id.gallery_point_linear);
		imgbtn_historyversion = (ImageButton) findViewById(R.id.imgbtn_historyversion);
		imgbtn_edit = (ImageButton) findViewById(R.id.imgbtn_edit);
		imgbtn_detail = (ImageButton) findViewById(R.id.imgbtn_detail);
		tv_materialdetail_sort = (TextView) findViewById(R.id.tv_materialdetail_tag);
		tv_versioncode = (TextView) findViewById(R.id.tv_versioncode);
		tv_taskcode = (TextView) findViewById(R.id.tv_taskcode);
		tv_picname = (TextView) findViewById(R.id.tv_picname);
		tv_picdetail = (TextView) findViewById(R.id.tv_picdetail);
		btn_materialdetail_check = (Button) findViewById(R.id.btn_materialdetail_check);
		str_tag = tv_materialdetail_sort.getText().toString();
		str_versioncode = tv_taskcode.getText().toString();
		str_taskcode = tv_taskcode.getText().toString();
	}

	@Override
	protected void initListeners() {
		// TODO Auto-generated method stub
		// images_ga.setOnItemClickListener(mOnItemClick);
		imgbtn_historyversion.setOnClickListener(mOnClick);
		imgbtn_edit.setOnClickListener(mOnClick);
		imgbtn_detail.setOnClickListener(mOnClick);
		btn_materialdetail_check.setOnClickListener(mOnClick);
	}

	OnClickListener mOnClick = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
			case R.id.imgbtn_historyversion:
				Bundle bundle_his = new Bundle();
				bundle_his.putSerializable("history",(Serializable)mMaterialHistoryList);
				openActivityForResult(HistoryVerActivity.class, bundle_his, 100);
				// openActivity(HistoryVerActivity.class, null);
				break;
			case R.id.imgbtn_edit:
				Bundle bundle = new Bundle();
				bundle.putSerializable("sort",(Serializable)mMaterialSortList);//分类
				bundle.putSerializable("tag", (Serializable)mMaterialTagList);//标签
				//bundle.putSerializable("property","");//属性
				bundle.putSerializable("picture",(Serializable)mMaterialPicList);//图片
				bundle.putString("tasknumber", str_taskcode);
				//openActivity(MaterialAddActivity.class, bundle);
				openActivityForResult(HistoryVerActivity.class, bundle, 200);
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
//		pager.setAdapter(new ImagePagerAdapter(MaterialDetailActivity.this,
//				MaterialDetail_ImageAdapter.imgs));
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

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);

		search = menu.findItem(R.id.search);
		add = menu.findItem(R.id.add);
		save = menu.findItem(R.id.save);
		search.setVisible(false);
		add.setVisible(false);
		save.setVisible(false);

		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		switch (item.getItemId()) {
		case android.R.id.home:

			finish();

			break;

		}

		return super.onOptionsItemSelected(item);
	}
}
