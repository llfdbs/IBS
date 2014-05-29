package com.victop.ibs.activity;

import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.victop.ibs.adapter.HistoryerAdapter;
import com.victop.ibs.app.IBSApplication;
import com.victop.ibs.base.ActivityBase;
import com.victop.ibs.bean.MaterialDetailHistoryBean;

/**
 * 历史版本
 * 
 * @author vv
 * 
 */
public class HistoryVerActivity extends ActivityBase implements OnClickListener {
	private ListView listView;
	private ActionBar actionBar;//导航栏
	private MenuItem search, add, save;//搜索,添加，保存按钮
	private List<MaterialDetailHistoryBean> mMaterialHistoryList;
	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		final View view = View.inflate(this, R.layout.historyver, null);
		setContentView(view);
		IBSApplication.getInstance().addActivity(this);
		initData();
		initViews();
		initListeners();

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
	
	}

	@Override
	protected void initData() {
		// TODO Auto-generated method stub
	Bundle bundle = getIntent().getExtras();
	mMaterialHistoryList = (List<MaterialDetailHistoryBean>)bundle.getSerializable("history");
	//System.out.println(mMaterialHistoryList.size()+"((((((((((((((((((((((((((((((((((()))))))))))))))))))))))");

	}

	@Override
	protected void initViews() {
		// TODO Auto-generated method stub
		actionBar = getSupportActionBar();
		actionBar.setTitle("历史版本");
		actionBar.setHomeButtonEnabled(true);
		actionBar.setIcon(R.drawable.btn_back);
		listView = (ListView) findViewById(R.id.lv_history);
		HistoryerAdapter adapter=new   HistoryerAdapter(this,mMaterialHistoryList);
		listView.setAdapter(adapter);
	}

	@Override
	protected void initListeners() {
		// TODO Auto-generated method stub
		listView.setOnItemClickListener(mOnItemClick);
	}
	OnItemClickListener mOnItemClick = new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int position,
				long arg3) {
			// TODO Auto-generated method stub
			Intent intent = new Intent(HistoryVerActivity.this,
					MaterialDetailActivity.class);
			Bundle bundle = new Bundle();
			bundle.putString("versionname", "11225556");
			intent.putExtras(bundle);
			setResult(RESULT_OK, intent);
			finish();
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
