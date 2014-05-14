package com.victop.ibs.activity;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.victop.ibs.adapter.Mat_add_ImagePagerAdapter;
import com.victop.ibs.adapter.MaterialAdd_girdViewAdapter;
import com.victop.ibs.adapter.MaterialAdd_girdViewAdapter.ShowUploadWayClass;
import com.victop.ibs.app.IBSApplication;
import com.victop.ibs.base.ActivityBase;
import com.victop.ibs.bean.Entity;
import com.victop.ibs.bean.SortModel;
import com.victop.ibs.util.Container;
import com.victop.ibs.view.MyGridView;

/**
 * 新增素材类
 * 
 * @author vv
 * 
 */
public class MaterialAddActivity extends ActivityBase implements
		OnClickListener, ShowUploadWayClass {
	private LinearLayout llt_sort, llt_property, llt_task, llt_tag;
	private TextView tv_sort, tv_task, tv_tag;
	private MyGridView mgv_material;
	private ImageButton ibtn_edit;
	MaterialAdd_girdViewAdapter mAdapter;
	private TextView tv_position, tv_detail;
	private ImageView iv_addimg;

	private ActionBar actionBar;// 导航栏
	private MenuItem search, add, save;// 搜索,添加，保存按钮
	private AlertDialog dialog;
	public static final int PHOTOHRAPH = 3;// 拍照
	public File picture;
	ArrayList<String> filelist;
	public List<Entity> img_list = null;// 传递数据

	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		final View view = View.inflate(this, R.layout.materialadd, null);
		setContentView(view);
		IBSApplication.getInstance().addActivity(this);
		Bundle bundle = getIntent().getExtras();
		// if (bundle != null) {
		// if (bundle.getSerializable("imgshow_data") != null) {
		// img_list = extracted(bundle);
		// }
		// }
		initData();
		initViews();
		initListeners();

	}

	private List<Entity> extracted(Bundle bundle) {
		return (List<Entity>) bundle.getSerializable("data");
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {

		case R.id.llt_sort:
			openActivityForResult(SortActivity.class, null, Container.SORT);

			break;
		case R.id.llt_property:
			openActivity(PropertyActivity.class, null);
			break;
		case R.id.llt_task:
			openActivityForResult(TaskSortActivity.class, null, Container.TASK);
			break;
		case R.id.llt_tag:
			Container.getInstance().getTaghashSortModel().clear();
			openActivityForResult(TagActivity.class, null, Container.TAG);

			break;
		case R.id.ibtn_edit:// 素材编辑按钮

			Bundle b = new Bundle();
			b.putString("edit_icon", "edit");
			b.putSerializable("edit", (Serializable) img_list);

			openActivity(ImgShowActivity.class, b);
			break;
		case R.id.img:
			showUploadWayDialogs();
			break;
		}
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		// Container.add_mData.clear();
		finish();
	}

	@Override
	protected void onNewIntent(Intent intent) {
		// TODO Auto-generated method stub
		super.onNewIntent(intent);
		Bundle b = intent.getExtras();
		if (b != null) {

			List<Entity> ee = (List<Entity>) b.getSerializable("imgshow_data");// 非编辑状态
			List<Entity> ee1 = (List<Entity>) b
					.getSerializable("edit_imgshow_data");// 编辑状态
			if (null != ee) {// 非编辑状态
				if (img_list == null) {
					img_list = ee;
					if (null != img_list && img_list.size() == 0) {
						iv_addimg.setVisibility(View.VISIBLE);
						ibtn_edit.setVisibility(View.INVISIBLE);
						mgv_material.setVisibility(View.GONE);
					} else {

						mgv_material.setVisibility(View.VISIBLE);
						iv_addimg.setVisibility(View.GONE);
						ibtn_edit.setVisibility(View.VISIBLE);
						mAdapter = new MaterialAdd_girdViewAdapter(this,
								img_list, 0, this);
						mgv_material.setAdapter(mAdapter);
					}
				} else {
					img_list.addAll(ee);
					mgv_material.setVisibility(View.VISIBLE);
					iv_addimg.setVisibility(View.GONE);
					ibtn_edit.setVisibility(View.VISIBLE);
					mAdapter = new MaterialAdd_girdViewAdapter(this, img_list,
							0, this);
					mgv_material.setAdapter(mAdapter);

				}

			} else if (null != ee1) {
				img_list.clear();
				img_list.addAll(ee1);
				if (img_list.size() == 0) {
					iv_addimg.setVisibility(View.VISIBLE);
					ibtn_edit.setVisibility(View.INVISIBLE);
					mgv_material.setVisibility(View.GONE);
				} else {
					mgv_material.setVisibility(View.VISIBLE);
					iv_addimg.setVisibility(View.GONE);
					ibtn_edit.setVisibility(View.VISIBLE);
					mAdapter = new MaterialAdd_girdViewAdapter(this, img_list,
							0, this);
					mgv_material.setAdapter(mAdapter);
				}
			}

			// } else {
			// mgv_material.setVisibility(View.VISIBLE);
			// iv_addimg.setVisibility(View.GONE);
			// ibtn_edit.setVisibility(View.VISIBLE);
			// mAdapter = new MaterialAdd_girdViewAdapter(this,
			// Container.add_mData, 0,new ShowUploadWayClass() {
			//
			// @Override
			// public void showUploadWayDialog() {
			// // TODO Auto-generated method stub
			// showUploadWayDialogs();
			// }
			// });
			// mgv_material.setAdapter(mAdapter);
			//
		}

	}

	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		switch (requestCode) {
		case Container.TAG:
			if (resultCode == RESULT_OK) {
				String nametext = "";
				HashMap<Integer, SortModel> hashMap = Container.getInstance()
						.getTaghashSortModel();
				Iterator iter = hashMap.keySet().iterator();
				while (iter.hasNext()) {
					Object key = iter.next();
					Object val = hashMap.get(key);
					nametext = nametext + ((SortModel) val).getName() + " ";
				}
				tv_tag.setText(nametext);
			}

			break;
		case Container.SORT:
			if (data != null) {
				Bundle b = data.getExtras();
				String tasknumber = b.getString("info");
				tv_sort.setText(tasknumber);
			}

			break;
		case Container.TASK:// 任务编号
			if (data != null) {
				Bundle b = data.getExtras();
				String tasknumber = b.getString("tasknumber");
				tv_task.setText(tasknumber);
			}
			break;
		case PHOTOHRAPH:
			// 拍照

			// 设置文件保存路径这里放在跟目录下
			picture = new File(Environment.getExternalStorageDirectory()
					+ "/temp.jpg");
			// startPhotoZoom(Uri.fromFile(picture));
			// Bitmap map = getBitmapByPath(picture.getPath().toString());

			openActivity(ImgShowActivity.class, null);

		}
	}

	@Override
	protected void initData() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void initViews() {
		// TODO Auto-generated method stub
		actionBar = getSupportActionBar();
		actionBar.setTitle("新增素材");
		actionBar.setHomeButtonEnabled(true);
		actionBar.setIcon(R.drawable.btn_back);
		llt_sort = (LinearLayout) findViewById(R.id.llt_sort);
		llt_property = (LinearLayout) findViewById(R.id.llt_property);
		llt_tag = (LinearLayout) findViewById(R.id.llt_task);
		llt_task = (LinearLayout) findViewById(R.id.llt_tag);

		tv_sort = (TextView) findViewById(R.id.tv_sort1);
		tv_task = (TextView) findViewById(R.id.tv_task1);
		tv_tag = (TextView) findViewById(R.id.tv_tag1);

		iv_addimg = (ImageView) findViewById(R.id.img);
		iv_addimg.setOnClickListener(this);

		mgv_material = (MyGridView) findViewById(R.id.mgv_material);
		ibtn_edit = (ImageButton) findViewById(R.id.ibtn_edit);

		ibtn_edit.setOnClickListener(this);
		if (null == img_list || null != img_list && img_list.size() == 0) {
			ibtn_edit.setVisibility(View.INVISIBLE);
			mgv_material.setVisibility(View.GONE);

		} else {
			iv_addimg.setVisibility(View.GONE);
			ibtn_edit.setVisibility(View.VISIBLE);

		}
		mgv_material.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				showDialogs(arg2);
			}
		});
		mAdapter = new MaterialAdd_girdViewAdapter(this, img_list, 0, this);

		mgv_material.setAdapter(mAdapter);
		llt_sort.setOnClickListener(this);
		llt_property.setOnClickListener(this);
		llt_tag.setOnClickListener(this);
		llt_task.setOnClickListener(this);
	}

	@Override
	protected void initListeners() {
		// TODO Auto-generated method stub

	}

	// 弹出大图浏览
	public void showDialogs(int pagerPosition) {
		Dialog dialog = new Dialog(MaterialAddActivity.this, R.style.taskdialog);
		View view = LayoutInflater.from(MaterialAddActivity.this).inflate(
				R.layout.showpicbigdialog, null);
		dialog.setContentView(view);
		ViewPager pager;
		pager = (ViewPager) view.findViewById(R.id.pager);
		pager.setAdapter(new Mat_add_ImagePagerAdapter(
				MaterialAddActivity.this, img_list));
		pager.setCurrentItem(pagerPosition);
		pager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
			@Override
			public void onPageSelected(int position) {
				String rr = position + 1 + "/" + img_list.size();
				String dd = img_list.get(position).getText();
				tv_position.setText(rr);
				tv_detail.setText(dd);
				// ((RadioButton) dotGroupButton.getChildAt(position))
				// .setChecked(true);
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
			}
		});
		tv_position = (TextView) view.findViewById(R.id.image_position);
		tv_detail = (TextView) view.findViewById(R.id.image_detail);
		String rr = pagerPosition + 1 + "/" + img_list.size();
		String dd = img_list.get(pagerPosition).getText();

		tv_position.setText(rr);
		tv_detail.setText(dd);
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

	public void setText(String rr, String dd) {
		tv_position.setText(rr);
		tv_detail.setText(dd);
	}

	// 弹出上传图片方式对话框
	public void showUploadWayDialogs() {
		dialog = new AlertDialog.Builder(MaterialAddActivity.this).create();
		dialog.show();
		Window window = dialog.getWindow();
		WindowManager.LayoutParams lp = window.getAttributes();
		lp.x = 0;
		lp.y = 330;
		dialog.onWindowAttributesChanged(lp);
		window.setContentView(R.layout.uploadimageways);
		Button uploadbycream = (Button) window.findViewById(R.id.uploadbycream);
		Button uploadbyphotos = (Button) window
				.findViewById(R.id.uploadbyphotos);
		Button uploadcancle = (Button) window.findViewById(R.id.uploadcancle);
		uploadbycream.setOnClickListener(btn_uploadByCream);
		uploadbyphotos.setOnClickListener(btn_uploadByPhotos);
		uploadcancle.setOnClickListener(btn_uploadcancle);
	}

	// 拍照上传图片
	OnClickListener btn_uploadByCream = new OnClickListener() {

		public void onClick(View v) {
			Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
			intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(
					Environment.getExternalStorageDirectory(), "temp.jpg")));
			startActivityForResult(intent, PHOTOHRAPH);

			dialog.dismiss();

		}
	};
	// 从图片库中选择图片
	OnClickListener btn_uploadByPhotos = new OnClickListener() {

		public void onClick(View v) {

			dialog.dismiss();
			openActivity(ImgFileListActivity.class, null);

		}
	};
	// 取消上传图片
	OnClickListener btn_uploadcancle = new OnClickListener() {

		public void onClick(View v) {

			dialog.dismiss();

		}
	};

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);

		search = menu.findItem(R.id.search);
		add = menu.findItem(R.id.add);
		save = menu.findItem(R.id.save);
		search.setVisible(false);
		add.setVisible(false);
		save.setVisible(true);
		save.setTitle("保存");
		save.setIcon(null);

		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		switch (item.getItemId()) {
		case android.R.id.home:
			// Container.add_mData.clear();
			finish();

			break;
		case R.id.search:
			break;
		case R.id.add:

			break;
		case R.id.save:
			// Container.add_mData.clear();
			finish();
			break;
		}

		return super.onOptionsItemSelected(item);
	}

	@Override
	public void showUploadWayDialog() {
		// TODO Auto-generated method stub
		showUploadWayDialogs();
	}

}
