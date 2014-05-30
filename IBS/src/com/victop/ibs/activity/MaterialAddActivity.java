package com.victop.ibs.activity;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Rect;
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
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.uploadfiles.UploadFiles;
import com.victop.ibs.adapter.Mat_add_ImagePagerAdapter;
import com.victop.ibs.adapter.MaterialAdd_girdViewAdapter;
import com.victop.ibs.app.IBSApplication;
import com.victop.ibs.base.ActivityBase;
import com.victop.ibs.bean.AddMaterialBean;
import com.victop.ibs.bean.Entity;
import com.victop.ibs.bean.MaterialPictureBean;
import com.victop.ibs.bean.MaterialPropertyBean;
import com.victop.ibs.bean.MaterialTagBean;
import com.victop.ibs.bean.TagBean;
import com.victop.ibs.handler.BaseHandler;
import com.victop.ibs.presenter.SavePresenter;
import com.victop.ibs.util.Container;
import com.victop.ibs.view.MyGridView;

/**
 * 新增素材类
 * 
 * @author vv
 * 
 */
public class MaterialAddActivity extends ActivityBase implements
		OnClickListener {
	private LinearLayout llt_sort, llt_property, llt_task, llt_tag;
	private TextView tv_sort, tv_task, tv_tag;
	private MyGridView mgv_material;
	private ImageButton ibtn_edit;
	MaterialAdd_girdViewAdapter mAdapter;
	private TextView tv_position, tv_detail;
	private ImageView iv_addimg;

	private ActionBar actionBar;// 导航栏
	private MenuItem search, add, save;// 搜索,添加，保存按钮
	public List<Entity> img_list = null;// 传递数据
	// Dataaddmaterial mDataaddmaterial;
	// List<MaterialPictureBean> mMaterialPictureBean;
	// AddMaterialBean mAddMaterialBean;
	private EditText et_memo;
	private String classid = "";

	// 保存的数据容器

	List<AddMaterialBean> tAddMaterialBean;
	List<MaterialPropertyBean> mMaterialPropertyBean;
	List<MaterialPictureBean> mMaterialPictureBean;
	List<MaterialTagBean> mMaterialTagBean;
	private String taskcode = "";
	private String taskid = "";
	private String taskcode_status = "";// 从未完成任务详情过来，添加
	private String taskid_status = "";

	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		final View view = View.inflate(this, R.layout.materialadd, null);
		setContentView(view);
		IBSApplication.getInstance().addActivity(this);
		initData();
		initViews();
		initListeners();

	}

	Handler mHandler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);

			switch (msg.what) {

			case 0:// 获取到数据
				Map<String, List> dataMap = (Map<String, List>) msg.obj;
				break;

			case 1:
				String rr = (String) msg.obj;
				Toast.makeText(getApplicationContext(), rr, 1000).show();
				if (null != taskcode_status && !"".equals(taskcode_status)) {
					Bundle b = new Bundle();
					b.putString("taskcode", "0");
					openActivity(TaskDetailActivity.class, b);
				}

				finish();

				break;
			case 2:
			case 3:
				String r2r = (String) msg.obj;
				Toast.makeText(getApplicationContext(), r2r, 1000).show();
				break;
			case 5:
				List<String> list = (List<String>) msg.obj;
				if (null != list)
					for (int i = 0; i < list.size(); i++) {
							MaterialPictureBean m =mMaterialPictureBean.get(i);
							m.setImgname(list.get(i));
					}

				BaseHandler bHandler = new BaseHandler(getApplicationContext(),
						mHandler);
				tAddMaterialBean = new ArrayList<AddMaterialBean>();

				String time = getTime();
				AddMaterialBean mAddMaterialBean = new AddMaterialBean();

				if (null != taskid_status && !"".equals(taskid_status))
					taskid = taskid_status;
				mAddMaterialBean.setTaskid(taskid);
				if (null != taskcode_status && !"".equals(taskcode_status)) {
					mAddMaterialBean.setTaskcode(taskcode_status);
				} else {
					mAddMaterialBean.setTaskcode(taskcode);
				}
				mAddMaterialBean.setIsdelete("0");
				mAddMaterialBean.setAddman(com.victop.android.session.Container
						.getInstance().getUser().getUserName());
				mAddMaterialBean
						.setAddmanid(com.victop.android.session.Container
								.getInstance().getUser().getUserCode());
				mAddMaterialBean.setAdddate(time);
				 mAddMaterialBean.setVersioncode("1.0");
				// mAddMaterialBean.setMaterialid("");
				mAddMaterialBean.setMaterialmemo(et_memo.getText().toString());
				mAddMaterialBean.setMaterialstatus("0");
				// mAddMaterialBean.setMaterialcode("");
				mAddMaterialBean.setMaterialguid(getMyUUID());
				tAddMaterialBean.add(mAddMaterialBean);
				Map<String, List> dataMap1 = new HashMap<String, List>();

				dataMap1.put("13", mMaterialPropertyBean);
				dataMap1.put("16", mMaterialPictureBean);
				dataMap1.put("11", tAddMaterialBean);
				dataMap1.put("18", mMaterialTagBean);
				SavePresenter.getInstance().SaveInitData(bHandler, "11112",
						"IBS11112", "11,13,16,18", dataMap1, null);
				break;
			case 6:
				break;
			}
		}
	};

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {

		case R.id.llt_sort:
			openActivityForResult(SortActivity.class, null, Container.SORT);

			break;
		case R.id.llt_property:

			if (!"".equals(tv_sort.getText()) && tv_sort.getText() != null) {
				Bundle b = new Bundle();
				b.putString("classid", classid);
				openActivity(PropertyActivity.class, b);
			} else {
				Toast.makeText(this, "请先填写分类", 500).show();
			}

			break;
		case R.id.llt_task:

			openActivityForResult(TaskSortActivity.class, null, Container.TASK);
			break;
		case R.id.llt_tag:

			if (!"".equals(tv_sort.getText()) && tv_sort.getText() != null) {

				Bundle b = new Bundle();
				b.putString("classid", classid);
				Container.getInstance().getTaghashSortModel().clear();
				openActivityForResult(TagActivity.class, b, Container.TAG);
			} else {
				Toast.makeText(this, "请先填写分类", 500).show();
			}

			break;
		case R.id.ibtn_edit:// 素材编辑按钮

			Bundle b = new Bundle();
			b.putString("edit_icon", "edit");
			b.putSerializable("edit", (Serializable) img_list);

			openActivity(ImgShowActivity.class, b);
			break;
		case R.id.img:
			openActivity(ImgFileListActivity.class, null);
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
			if (null != b.getSerializable("proper"))
				mMaterialPropertyBean = (List<MaterialPropertyBean>) b
						.getSerializable("proper");
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
								img_list, 0);
						mgv_material.setAdapter(mAdapter);
					}
				} else {
					img_list.addAll(ee);
					mgv_material.setVisibility(View.VISIBLE);
					iv_addimg.setVisibility(View.GONE);
					ibtn_edit.setVisibility(View.VISIBLE);
					mAdapter = new MaterialAdd_girdViewAdapter(this, img_list,
							0);
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
							0);
					mgv_material.setAdapter(mAdapter);
				}
			}
			if (null != img_list)
				for (Entity e : img_list) {
					MaterialPictureBean materialPictureBean = new MaterialPictureBean();

					materialPictureBean.setImgmemo(e.getText());
					materialPictureBean.setImgname(e.getURL());
					materialPictureBean.setMatimgid(getMyUUID());
					materialPictureBean.setImghigh(e.getHieght());
					materialPictureBean.setImgrule("");
					// materialPictureBean.setImgwidth(imgwidth);
					materialPictureBean.setMaterialguid(getMyUUID());

					mMaterialPictureBean.add(materialPictureBean);

				}

		}

	}

	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		switch (requestCode) {
		case Container.TAG:
			if (resultCode == RESULT_OK) {
				String nametext = "";
				HashMap<Integer, TagBean> hashMap = Container.getInstance()
						.getTaghashSortModel();
				Iterator iter = hashMap.keySet().iterator();
				while (iter.hasNext()) {
					Object key = iter.next();
					Object val = hashMap.get(key);

					MaterialTagBean m = new MaterialTagBean();
					TagBean t = (TagBean) val;
					m.setLableid(t.getLableid());
					// m.set
					m.setMaterialguid(getMyUUID());
					mMaterialTagBean.add(m);
					nametext = nametext + t.getLablename() + " ";

				}
				tv_tag.setText(nametext);
			}

			break;
		case Container.SORT:
			if (data != null) {
				Bundle b = data.getExtras();
				String tasknumber = b.getString("info");
				classid = b.getString("classid");
				tv_sort.setText(tasknumber);
				tv_tag.setText("");
				Container.getInstance().getTaghashSortModel().clear();
			}

			break;
		case Container.TASK:// 任务编号
			if (data != null) {
				Bundle b = data.getExtras();
				taskcode = b.getString("tasknumber");
				taskid = b.getString("taskid");
				tv_task.setText(taskcode);
			}
			break;
		// case Container.PROPER:// 属性
		// if (data != null) {
		// Bundle b = data.getExtras();
		// mMaterialPropertyBean = (List<MaterialPropertyBean>) b
		// .getSerializable("proper");
		//
		// }
		// break;
		}
	}

	@Override
	protected void initData() {
		// TODO Auto-generated method stub

		mMaterialPictureBean = new ArrayList<MaterialPictureBean>();
		mMaterialTagBean = new ArrayList<MaterialTagBean>();
		tAddMaterialBean = new ArrayList<AddMaterialBean>();
		mMaterialPropertyBean = new ArrayList<MaterialPropertyBean>();

		Bundle b = getIntent().getExtras();
		if (null != b) {
			taskcode_status = b.getString("taskcode");
			taskid_status = b.getString("taskid");
		}
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
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
		llt_task = (LinearLayout) findViewById(R.id.llt_task);
		llt_tag = (LinearLayout) findViewById(R.id.llt_tag);

		tv_sort = (TextView) findViewById(R.id.tv_sort1);
		tv_task = (TextView) findViewById(R.id.tv_task1);
		tv_tag = (TextView) findViewById(R.id.tv_tag1);

		et_memo = (EditText) findViewById(R.id.et_memo);

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
		mAdapter = new MaterialAdd_girdViewAdapter(this, img_list, 0);
		mgv_material.setAdapter(mAdapter);
		llt_sort.setOnClickListener(this);
		llt_property.setOnClickListener(this);
		llt_tag.setOnClickListener(this);

		if (null != taskcode_status && !"".equals(taskcode_status)) {
			tv_task.setText(taskcode_status);

		} else {
			llt_task.setOnClickListener(this);
		}

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
			if ("".equals(et_memo.getText().toString())
					|| et_memo.getText().toString() == null) {
				Toast.makeText(this, "请添加描述", 500).show();
			} else {
				List<File> fileList = new ArrayList<File>();
				for (MaterialPictureBean m : mMaterialPictureBean) {

					File f = new File(m.getImgname());
					fileList.add(f);
				}
				saveImage(fileList);

			}

			break;
		}
		return super.onOptionsItemSelected(item);
	}

	private void saveImage(final List<File> fileList) {
		String newName = getMyUUID() + ".jpg";
		System.out.println(newName + "--------------");
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub

				try {

					List<String> list = (new UploadFiles()).uploadFileClient(
							Container.ACTION, fileList);
					if (null != list) {
						Message msg = new Message();
						msg.what = 5;
						msg.obj = list;
						mHandler.sendMessage(msg);
					} else {
						Message msgs = new Message();
						msgs.what = 6;
						mHandler.sendMessage(msgs);
					}

				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}).start();
	}
}
