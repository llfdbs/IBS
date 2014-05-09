package com.victop.ibs.activity;

import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.victop.ibs.adapter.ImgsAdapter;
import com.victop.ibs.adapter.ImgsAdapter.OnItemClickClass;
import com.victop.ibs.app.IBSApplication;
import com.victop.ibs.base.ActivityBase;
import com.victop.ibs.bean.Entity;
import com.victop.ibs.util.Container;
import com.victop.ibs.util.FileTraversal;
import com.victop.ibs.util.ImgCallBack;
import com.victop.ibs.util.Util;

/**
 * 图片选择类
 * 
 * @author yao
 * 
 */
public class ImgsActivity extends ActivityBase implements OnClickListener {

	Bundle bundle;
	FileTraversal fileTraversal;
	GridView imgGridView;
	ImgsAdapter imgsAdapter;
	LinearLayout select_layout;
	Util util;
	RelativeLayout relativeLayout2;
	HashMap<Integer, ImageView> hashImage;
	Button choise_button;
	ArrayList<String> filelist;
	private ActionBar actionBar;// 导航栏
	private MenuItem search, add, save;// 搜索,添加，保存按钮

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.photogrally);
		IBSApplication.getInstance().addActivity(this);

		bundle = getIntent().getExtras();
		fileTraversal = bundle.getParcelable("data");
		initData();
		initViews();
	}

	@Override
	protected void initData() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void initViews() {
		actionBar = getSupportActionBar();
		actionBar.setTitle("请选择图片");
		actionBar.setHomeButtonEnabled(true);
		actionBar.setIcon(R.drawable.btn_back);
		imgGridView = (GridView) findViewById(R.id.gridView1);
		imgsAdapter = new ImgsAdapter(this, fileTraversal.filecontent,
				onItemClickClass);
		imgGridView.setAdapter(imgsAdapter);
		select_layout = (LinearLayout) findViewById(R.id.selected_image_layout);
		relativeLayout2 = (RelativeLayout) findViewById(R.id.relativeLayout2);
		choise_button = (Button) findViewById(R.id.button3);
		hashImage = new HashMap<Integer, ImageView>();
		filelist = new ArrayList<String>();
		// imgGridView.setOnItemClickListener(this);
		util = new Util(this);
	}

	class BottomImgIcon implements OnItemClickListener {

		int index;

		public BottomImgIcon(int index) {
			this.index = index;
		}

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {

		}
	}

	@SuppressLint("NewApi")
	public ImageView iconImage(String filepath, int index, CheckBox checkBox)
			throws FileNotFoundException {
		LinearLayout.LayoutParams params = new LayoutParams(
				relativeLayout2.getMeasuredHeight() - 10,
				relativeLayout2.getMeasuredHeight() - 10);
		ImageView imageView = new ImageView(this);
		imageView.setLayoutParams(params);
		imageView.setBackgroundResource(R.drawable.task_material_head);
		float alpha = 100;
		// imageView.setAlpha(alpha);
		util.imgExcute(imageView, imgCallBack, filepath);
		imageView.setOnClickListener(new ImgOnclick(filepath, checkBox));
		return imageView;
	}

	ImgCallBack imgCallBack = new ImgCallBack() {
		@Override
		public void resultImgCall(ImageView imageView, Bitmap bitmap) {
			imageView.setImageBitmap(bitmap);
		}
	};

	class ImgOnclick implements OnClickListener {
		String filepath;
		CheckBox checkBox;

		public ImgOnclick(String filepath, CheckBox checkBox) {
			this.filepath = filepath;
			this.checkBox = checkBox;
		}

		@Override
		public void onClick(View arg0) {
			checkBox.setChecked(false);
			select_layout.removeView(arg0);
			if (select_layout.getChildCount() == 0) {
				actionBar.setTitle("请选择图片");
			} else {
				SpannableString sp = new SpannableString("已选择"
						+ select_layout.getChildCount() + "张");

				if (select_layout.getChildCount() < 10) {
					sp.setSpan(new ForegroundColorSpan(0xff0079fa), 3, 4,
							Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
				} else if (select_layout.getChildCount() < 100) {
					sp.setSpan(new ForegroundColorSpan(0xff0079fa), 3, 5,
							Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
				}

				actionBar.setTitle(sp);
			}
			filelist.remove(filepath);
		}
	}

	ImgsAdapter.OnItemClickClass onItemClickClass = new OnItemClickClass() {
		@Override
		public void OnItemClick(View v, int Position, CheckBox checkBox) {
			String filapath = fileTraversal.filecontent.get(Position);
			if (checkBox.isChecked()) {
				checkBox.setChecked(false);
				select_layout.removeView(hashImage.get(Position));
				filelist.remove(filapath);
				if (select_layout.getChildCount() == 0) {
					actionBar.setTitle("请选择图片");
				} else {

					SpannableString sp = new SpannableString("已选择"
							+ select_layout.getChildCount() + "张");

					if (select_layout.getChildCount() < 10) {
						sp.setSpan(new ForegroundColorSpan(0xff0079fa), 3, 4,
								Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
					} else if (select_layout.getChildCount() < 100) {
						sp.setSpan(new ForegroundColorSpan(0xff0079fa), 3, 5,
								Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
					}

					actionBar.setTitle(sp);
				}
			} else {
				try {
					checkBox.setChecked(true);
					Log.i("img", "img choise position->" + Position);
					ImageView imageView = iconImage(filapath, Position,
							checkBox);
					if (imageView != null) {
						hashImage.put(Position, imageView);
						filelist.add(filapath);
						select_layout.addView(imageView);

						if (select_layout.getChildCount() == 0) {
							actionBar.setTitle("请选择图片");
						} else {
							SpannableString sp = new SpannableString("已选择"
									+ select_layout.getChildCount() + "张");

							if (select_layout.getChildCount() < 10) {
								sp.setSpan(new ForegroundColorSpan(0xff0079fa),
										3, 4,
										Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
							} else if (select_layout.getChildCount() < 100) {
								sp.setSpan(new ForegroundColorSpan(0xff0079fa),
										3, 5,
										Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
							}
							actionBar.setTitle(sp);
						}
					}
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
			}
		}
	};

	public void tobreak(View view) {
		finish();
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

	}

	Entity e;

	/**
	 * FIXME
	 * 
	 * @param view
	 */
	public void sendfiles() {
		if (filelist.size() > 0) {
			List<Entity> ee = new ArrayList<Entity>();
			for (String tt : filelist) {
				// Map<String, String> rr = new HashMap<String, String>();
				// rr.put(tt, "");
				// Container.newData.add(rr);
				e = new Entity();
				e.setText("");
				e.setURL(tt);
				ee.add(e);
			}
			Bundle b = new Bundle();
			b.putSerializable("data", (Serializable) ee);

			openActivity(ImgShowActivity.class, b);
		} else {
			Toast.makeText(getApplicationContext(), "请选择图片", 500).show();
		}
	}

	@Override
	protected void initListeners() {
		// TODO Auto-generated method stub

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
		save.setTitle("确定");
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

			finish();

			break;
		case R.id.search:
			break;
		case R.id.add:

			break;
		case R.id.save:
			sendfiles();// 保存
			finish();
			break;
		}

		return super.onOptionsItemSelected(item);
	}
}
