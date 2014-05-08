package com.victop.ibs.activity;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.victop.ibs.adapter.PropertygridviewAdapter;
import com.victop.ibs.app.IBSApplication;
import com.victop.ibs.base.ActivityBase;

/**
 * 素材模块 属性接界面
 * 
 * @author vv
 * 
 */
public class PropertyActivity extends ActivityBase implements OnClickListener {

	private EditText et_product;
	private RadioButton rb_on, rb_off;
	private RadioGroup rg_come;

	private TextView tv_tagcontent, tv_comecontent;
	private int tag = 0;// 0代表编辑状态，1代表查看状态
	private Dialog dialog;
	private Button btn_choose;
	private View popView;

	private int temp;
	private String[] data = { "服饰", "旅游", "美食", "分享", "服饰", "旅游", "美食", "分享",
			"服饰", "旅游", "美食", "分享", "服饰", "旅游", "美食", "分享" };
	private ActionBar actionBar;//导航栏
	private MenuItem search, add, save;//搜索,添加，保存按钮
	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		final View view = View.inflate(this, R.layout.propertylayout, null);
		setContentView(view);
		IBSApplication.getInstance().addActivity(this);
		initViews();
		initData();
		
		initListeners();

	}

	@Override
	protected void initData() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void initViews() {
		// TODO Auto-generated method stub
		actionBar = getSupportActionBar();
		actionBar.setTitle("");
		actionBar.setHomeButtonEnabled(true);
		actionBar.setIcon(R.drawable.btn_back);
		et_product = (EditText) findViewById(R.id.et_product);
		rb_on = (RadioButton) findViewById(R.id.rbtn_no);
		rb_off = (RadioButton) findViewById(R.id.rbtn_off);
		rg_come = (RadioGroup) findViewById(R.id.radiogroup_type);

		tv_tagcontent = (TextView) findViewById(R.id.tagname);
		tv_comecontent = (TextView) findViewById(R.id.getname);
		if (tag == 0) {
			btn_choose = (Button) findViewById(R.id.btn_choose);
			btn_choose.setOnClickListener(this);
		} else if (tag == 1) {
			et_product.setEnabled(false);
			et_product.setBackgroundResource(R.drawable.login_bg);
		}
	}

	@Override
	protected void initListeners() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		
		case R.id.btn_choose:
			showDielog();
			break;
		}
	}

	private void showDielog() {
		LayoutInflater inflate = LayoutInflater.from(this);

		popView = inflate.inflate(R.layout.property_gridlayout, null);

		GridView gridview = (GridView) popView.findViewById(R.id.gridview);
		final PropertygridviewAdapter adapter = new PropertygridviewAdapter(
				this, data);

		gridview.setAdapter(adapter);
		gridview.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				temp = arg2;
				btn_choose.setText(data[arg2]);
				// adapter.setSeclection(arg2);
				// adapter.notifyDataSetChanged();
				dialog.dismiss();
			}
		});

		dialog = new Dialog(this, R.style.dialog);
		dialog.setContentView(popView);
		dialog.show();
		// WindowManager windowManager = getWindowManager();
		// Display display = windowManager.getDefaultDisplay();
		// WindowManager.LayoutParams lp = dialog.getWindow().getAttributes();
		// lp.width = (int) (display.getWidth() * 0.95);
		//
		// lp.height = (int) (display.getHeight() * 0.9);

		// dialog.getWindow().setAttributes(lp);
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
		if (tag == 0) {
			save.setTitle("完成");
			
		} else if (tag == 1) {
			save.setTitle("");
		}

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
			finish();
			break;
		}

		return super.onOptionsItemSelected(item);
	}
}
