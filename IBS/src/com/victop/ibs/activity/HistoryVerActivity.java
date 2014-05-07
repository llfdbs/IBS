package com.victop.ibs.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.victop.ibs.adapter.HistoryerAdapter;
import com.victop.ibs.app.IBSApplication;
import com.victop.ibs.base.ActivityBase;

/**
 * 历史版本
 * 
 * @author vv
 * 
 */
public class HistoryVerActivity extends ActivityBase implements OnClickListener {
	private Button btn_back, btn_add, btn_notsearch;
	private TextView tv_title = null;
	private ListView listView;

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
		switch (v.getId()) {
		case R.id.back:
			finish();
			break;

		}
	}

	@Override
	protected void initData() {
		// TODO Auto-generated method stub
		btn_back = (Button) findViewById(R.id.back);
		tv_title = (TextView) findViewById(R.id.title);
		btn_add = (Button) findViewById(R.id.add);

		btn_add.setVisibility(View.GONE);
		btn_back.setOnClickListener(this);

		// tv_title.setText("");
		btn_notsearch = (Button) findViewById(R.id.search);
		btn_notsearch.setVisibility(View.GONE);

	}

	@Override
	protected void initViews() {
		// TODO Auto-generated method stub
		listView = (ListView) findViewById(R.id.lv_history);
		HistoryerAdapter adapter=new   HistoryerAdapter(this,null);
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
}
