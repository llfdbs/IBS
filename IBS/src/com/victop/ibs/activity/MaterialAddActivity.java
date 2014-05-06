package com.victop.ibs.activity;

import java.util.HashMap;
import java.util.Iterator;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.victop.ibs.adapter.Mat_add_ImagePagerAdapter;
import com.victop.ibs.adapter.MaterialAdd_girdViewAdapter;
import com.victop.ibs.base.ActivityBase;
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
		OnClickListener {
	private Button btn_back, btn_add, btn_notsearch;
	private TextView tv_title = null;
	private LinearLayout llt_sort, llt_property, llt_task, llt_tag;
	private TextView tv_sort, tv_task, tv_tag;
	private MyGridView mgv_material;
	private ImageButton ibtn_edit;
	MaterialAdd_girdViewAdapter mAdapter;

	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		final View view = View.inflate(this, R.layout.materialadd, null);
		setContentView(view);
		// ibsApplication.getInstance().addActivity(this);
		initData();
		initViews();
		initListeners();

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.back:
			Container.add_mData.clear();
			finish();
			break;
		case R.id.add:// 保存
			Container.add_mData.clear();
			finish();
			break;
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
			Container.newData.addAll(Container.add_mData);
			Container.add_mData.clear();
			openActivity(ImgShowActivity.class, null);
			break;

		}
	}

	@Override
	protected void onNewIntent(Intent intent) {
		// TODO Auto-generated method stub
		super.onNewIntent(intent);
		if (Container.add_mData.size() == 0)
			ibtn_edit.setVisibility(View.INVISIBLE);
		else
			ibtn_edit.setVisibility(View.VISIBLE);
		mAdapter = new MaterialAdd_girdViewAdapter(this, Container.add_mData, 0);
		mgv_material.setAdapter(mAdapter);

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

			break;
		case Container.TASK:// 任务编号
			if (data != null) {
				Bundle b = data.getExtras();
				String tasknumber = b.getString("tasknumber");
				tv_task.setText(tasknumber);
			}

		}
	}

	@Override
	protected void initData() {
		// TODO Auto-generated method stub
		btn_back = (Button) findViewById(R.id.back);
		tv_title = (TextView) findViewById(R.id.title);
		btn_add = (Button) findViewById(R.id.add);
		// btn_add.setBackgroundResource(R.drawable.btn_add);
		// btn_add.setVisibility(View.GONE);
		btn_add.setText("保存");
		btn_back.setOnClickListener(this);
		btn_add.setOnClickListener(this);
		tv_title.setText("新增素材");
		btn_notsearch = (Button) findViewById(R.id.search);
		btn_notsearch.setVisibility(View.GONE);
		ibtn_edit = (ImageButton) findViewById(R.id.ibtn_edit);
		if (Container.add_mData.size() == 0)
			ibtn_edit.setVisibility(View.INVISIBLE);
		ibtn_edit.setOnClickListener(this);
	}

	@Override
	protected void initViews() {
		// TODO Auto-generated method stub
		llt_sort = (LinearLayout) findViewById(R.id.llt_sort);
		llt_property = (LinearLayout) findViewById(R.id.llt_property);
		llt_tag = (LinearLayout) findViewById(R.id.llt_task);
		llt_task = (LinearLayout) findViewById(R.id.llt_tag);

		tv_sort = (TextView) findViewById(R.id.tv_sort1);
		tv_task = (TextView) findViewById(R.id.tv_task1);
		tv_tag = (TextView) findViewById(R.id.tv_tag1);

		mgv_material = (MyGridView) findViewById(R.id.mgv_material);
		mgv_material.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				showDialogs(arg2);
			}
		});
		mAdapter = new MaterialAdd_girdViewAdapter(this, Container.add_mData, 0);
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
				MaterialAddActivity.this, Container.add_mData));
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
}
