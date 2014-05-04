package com.victop.ibs.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.victop.ibs.adapter.ListImagesAdapter;
import com.victop.ibs.app.ibsApplication;
import com.victop.ibs.base.ActivityBase;
import com.victop.ibs.util.Container;

/**
 * 图片展示类
 * 
 * @author Administrator
 * 
 */
public class ImgShowActivity extends ActivityBase implements OnClickListener {
	private Button btn_back, btn_add, btn_notsearch;
	private TextView tv_title = null;
	private ListView listView;
	private ArrayList<String> listfiles = new ArrayList<String>();
	private ArrayList<String> listfile = new ArrayList<String>();
	ListImagesAdapter adapter;
	private List<Map<String, String>> mData = new ArrayList<Map<String, String>>();

	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		final View view = View.inflate(this, R.layout.imgshowlayout, null);
		setContentView(view);
		ibsApplication.getInstance().addActivity(this);
		Bundle bundle = getIntent().getExtras();

		// System.out.println(Container.listfiles.size()
		// + "====================================");

		initData();
		initViews();
		initListeners();
		if (bundle != null) {
			if (bundle.getStringArrayList("files") != null) {
				listfile = bundle.getStringArrayList("files");
				listView.setVisibility(View.VISIBLE);
				addData(Container.listfiles, listfile);
			}
		}
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.back:
			finish();
			break;
		case R.id.add:
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
		// btn_add.setBackgroundResource(R.drawable.btn_add);
		btn_add.setVisibility(View.GONE);
		btn_back.setOnClickListener(this);
		btn_add.setOnClickListener(this);
		// tv_title.setText("");
		btn_notsearch = (Button) findViewById(R.id.search);
		btn_notsearch.setVisibility(View.GONE);
	}

	@Override
	protected void initViews() {
		// TODO Auto-generated method stub
		listView = (ListView) findViewById(R.id.lv_imglist);
	}

	@Override
	protected void initListeners() {
		// TODO Auto-generated method stub

	}

	public void addData(ArrayList<String> listfiles, ArrayList<String> listfile) {
		adapter = new ListImagesAdapter(ImgShowActivity.this, listfiles);
		if (listfiles.size() > 0) {
			listfiles.addAll(listfile);
			for (int i = 0; i < listfile.size(); i++) {
				Map<String, String> item = new HashMap<String, String>();
				item.put("list_item_inputvalue", "");
				Container.mData.add(item);
			}
			if (adapter != null) {
				System.out
						.println(listfiles.size()
								+ "***************************************************");
				for (int i = 0; i < listfiles.size(); i++) {
					Map<String, String> item = new HashMap<String, String>();
					item.put("list_item_inputvalue", Container.mData.get(i)
							.get("list_item_inputvalue"));
					mData.add(item);
				}
				adapter.setData(mData);
				listView.setAdapter(adapter);
				adapter.notifyDataSetChanged();
			}
		} else {
			System.out.println("========dd============================");
			for (int j = 0; j < listfile.size(); j++) {
				listfiles.add(listfiles.size(), listfile.get(j));
			}

			for (int i = 0; i < listfiles.size(); i++) {
				Map<String, String> item = new HashMap<String, String>();
				item.put("list_item_inputvalue", "");
				mData.add(item);
			}
			adapter.setData(mData);
			listView.setAdapter(adapter);
		}

	}

}
