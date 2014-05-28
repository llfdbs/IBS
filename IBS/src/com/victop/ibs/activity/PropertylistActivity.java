package com.victop.ibs.activity;

import java.io.Serializable;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.victop.ibs.adapter.PropertyAdapter;
import com.victop.ibs.app.IBSApplication;
import com.victop.ibs.base.ActivityBase;
import com.victop.ibs.bean.PropertyBean;

/**
 * 属性界面多选界面
 * 
 * @author vv
 * 
 */
public class PropertylistActivity extends ActivityBase {
	private static ActionBar actionBar;// 导航栏
	private MenuItem search, add, save;// 搜索,添加，保存按钮
	private ListView listView;
	private PropertyAdapter adapter;
	List<PropertyBean> pro_morem;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.propertylist);
		IBSApplication.getInstance().addActivity(this);
		Bundle b = getIntent().getExtras();
		if (null != b) {
			pro_morem = (List<PropertyBean>) b.getSerializable("morem");
		}
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
		actionBar.setTitle(getResources().getString(R.string.allocationtask));
		actionBar.setHomeButtonEnabled(true);
		actionBar.setIcon(R.drawable.btn_back);
		listView = (ListView) findViewById(R.id.listview);
		adapter = new PropertyAdapter(this, pro_morem);
		listView.setAdapter(adapter);
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
		case R.id.save:
			TranslateData();
			break;
		}
		return super.onOptionsItemSelected(item);
	}

	public void TranslateData() {
		List<PropertyBean> mUserMessageBean = PropertyAdapter.isSelectedName;
		Intent intent = new Intent(this, AddTaskActivity.class);
		Bundle bundle = new Bundle();
		bundle.putSerializable("morem", (Serializable) mUserMessageBean);
		intent.putExtras(bundle);
		setResult(RESULT_OK, intent);
		finish();
	}
}
