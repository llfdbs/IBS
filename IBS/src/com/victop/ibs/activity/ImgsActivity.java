package com.victop.ibs.activity;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
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
import android.widget.TextView;

import com.victop.ibs.adapter.ImgsAdapter;
import com.victop.ibs.adapter.ImgsAdapter.OnItemClickClass;
import com.victop.ibs.app.ibsApplication;
import com.victop.ibs.util.FileTraversal;
import com.victop.ibs.util.ImgCallBack;
import com.victop.ibs.util.Util;

/**
 * 图片选择类
 * 
 * @author yao
 * 
 */
public class ImgsActivity extends Activity implements OnClickListener {

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
	private Button btn_back, btn_add, btn_notsearch;
	private TextView tv_title = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.photogrally);
		ibsApplication.getInstance().addActivity(this);
		imgGridView = (GridView) findViewById(R.id.gridView1);
		bundle = getIntent().getExtras();
		fileTraversal = bundle.getParcelable("data");
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
		initView();
	}

	private void initView() {
		btn_back = (Button) findViewById(R.id.back);
		tv_title = (TextView) findViewById(R.id.title);
		btn_add = (Button) findViewById(R.id.add);
		// btn_add.setBackgroundResource(R.drawable.btn_add);
		// btn_add.setVisibility(View.GONE);
		btn_add.setText("确定");
		btn_back.setOnClickListener(this);
		btn_add.setOnClickListener(this);
		tv_title.setText("请选择图片");
		btn_notsearch = (Button) findViewById(R.id.search);
		btn_notsearch.setVisibility(View.GONE);
		// ibtn_edit = (ImageButton) findViewById(R.id.ibtn_edit);
		// ibtn_edit.setOnClickListener(this);
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
				tv_title.setText("请选择图片");
			} else {
				SpannableString sp = new SpannableString("已选择"
						+ select_layout.getChildCount() + "张");
				sp.setSpan(new ForegroundColorSpan(0xff0079fa), 3, 4,
						Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
				tv_title.setText(sp);
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
					tv_title.setText("请选择图片");
				} else {

					SpannableString sp = new SpannableString("已选择"
							+ select_layout.getChildCount() + "张");
					sp.setSpan(new ForegroundColorSpan(0xff0079fa), 3, 4,
							Spannable.SPAN_EXCLUSIVE_INCLUSIVE);

					tv_title.setText(sp);
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
							tv_title.setText("请选择图片");
						} else {
							SpannableString sp = new SpannableString("已选择"
									+ select_layout.getChildCount() + "张");
							sp.setSpan(new ForegroundColorSpan(0xff0079fa), 3,
									4, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
							tv_title.setText(sp);
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
		switch (v.getId()) {
		case R.id.back:
			finish();
			break;
		case R.id.add:// 保存

			sendfiles(v);
			finish();
			break;
		}
	}

	/**
	 * FIXME
	 * 
	 * @param view
	 */
	public void sendfiles(View view) {
		Intent intent = new Intent(this, ImgShowActivity.class);
		Bundle bundle = new Bundle();
		bundle.putStringArrayList("files", filelist);
		intent.putExtras(bundle);
		startActivity(intent);

	}
}
