package com.victop.ibs.activity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.victop.ibs.adapter.Tag_SortAdapter;
import com.victop.ibs.app.IBSApplication;
import com.victop.ibs.base.ActivityBase;
import com.victop.ibs.bean.SortModel;
import com.victop.ibs.bean.TagBean;
import com.victop.ibs.handler.BaseHandler;
import com.victop.ibs.presenter.Getpresenter;
import com.victop.ibs.util.CharacterParser;
import com.victop.ibs.view.ClearEditText;
import com.victop.ibs.view.SideBar;
import com.victop.ibs.view.SideBar.OnTouchingLetterChangedListener;

/**
 * 素材标签类
 * 
 * @author vv
 * 
 */
@SuppressLint("HandlerLeak")
public class TagActivity extends ActivityBase implements OnClickListener {
	private ListView sortListView;
	private SideBar sideBar;
	private TextView dialog;
	private Tag_SortAdapter adapter;
	private ClearEditText mClearEditText;
	private CharacterParser characterParser;
	private List<SortModel> SourceDateList;
	private PinyinComparator pinyinComparator;
	private ActionBar actionBar;// 导航栏
	private MenuItem search, add, save;// 搜索,添加，保存按钮
	private List<TagBean> mTagBean = new ArrayList<TagBean>();

	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		final View view = View.inflate(this, R.layout.taglayout, null);
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
				mTagBean = (List<TagBean>) dataMap.get("3");
				if (null != mTagBean)
					for (TagBean s : mTagBean) {
						String pinyin = characterParser.getSelling(s
								.getLablename());
						String sortString = pinyin.substring(0, 1)
								.toUpperCase();

						if (sortString.matches("[A-Z]")) {
							s.setSortLetters(sortString.toUpperCase());
						} else {
							s.setSortLetters("#");
						}
						s.setTag(0);
					}

				// 根据a-z进行排序源数据
				Collections.sort(mTagBean, pinyinComparator);

				adapter = new Tag_SortAdapter(TagActivity.this, mTagBean);
				sortListView.setAdapter(adapter);

				break;
			case 1:
				String rr = (String) msg.obj;
				Toast.makeText(getApplicationContext(), rr, 1000).show();
				finish();
				break;
			case 2:
			case 3:
				String r2r = (String) msg.obj;
				Toast.makeText(getApplicationContext(), r2r, 1000).show();
				break;
			}
		}
	};

	@Override
	protected void initData() {
		// TODO Auto-generated method stub
		BaseHandler bHandler = new BaseHandler(this, mHandler);

		HashMap<String, String> Datamap = new HashMap<String, String>();
		Datamap.put("classid", "2");
		Map<String, Class> clsMap = new HashMap<String, Class>();
		clsMap.put(TagBean.datasetId, TagBean.class);
		Getpresenter.getInstance().getInitbData(bHandler, clsMap, Datamap,
				TagBean.modelId, TagBean.datasetId, null ,TagBean.formId);
	}

	@Override
	protected void initViews() {
		// TODO Auto-generated method stub
		actionBar = getSupportActionBar();
		actionBar.setTitle("标签");
		actionBar.setHomeButtonEnabled(true);
		actionBar.setIcon(R.drawable.btn_back);
		// 实例化汉字转拼音类
		characterParser = CharacterParser.getInstance();

		pinyinComparator = new PinyinComparator();

		sideBar = (SideBar) findViewById(R.id.sidrbar);
		dialog = (TextView) findViewById(R.id.dialog);
		sideBar.setTextView(dialog);

		sideBar.setOnTouchingLetterChangedListener(new OnTouchingLetterChangedListener() {

			@Override
			public void onTouchingLetterChanged(String s) {
				// 该字母首次出现的位置
				int position = adapter.getPositionForSection(s.charAt(0));
				if (position != -1) {
					sortListView.setSelection(position);
				}

			}
		});

		sortListView = (ListView) findViewById(R.id.country_lvcountry);
		sortListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// 这里要利用adapter.getItem(position)来获取当前position所对应的对象
				Toast.makeText(getApplication(),
						((SortModel) adapter.getItem(position)).getName(),
						Toast.LENGTH_SHORT).show();

			}
		});

		mClearEditText = (ClearEditText) findViewById(R.id.filter_edit);
		// 根据输入框输入值的改变来过滤搜索
		mClearEditText.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// 当输入框里面的值为空，更新为原来的列表，否则为过滤数据列表
				// filterData(s.toString());
				// int position = adapter.getPositionForString(s+"");
				// if (position != -1) {
				// sortListView.setSelection(position);
				// }
				findString(s.toString());
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {

			}

			@Override
			public void afterTextChanged(Editable s) {
			}
		});
	}

	@Override
	protected void initListeners() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
	}

	private void findString(String str) {
		if (TextUtils.isEmpty(str)) {
			sortListView.setSelection(0);
		} else {
			if (mTagBean.size() > 0)
				for (TagBean sortModel : mTagBean) {
					String name = sortModel.getLablename();
					if (name.indexOf(str.toString()) != -1
							|| characterParser.getSelling(name).startsWith(
									str.toString())) {
						int position = adapter.getPositionForString(name);
						if (position != -1) {
							sortListView.setSelection(position);

						}
					}
				}
		}
	}

	/**
	 * 为ListView填充数据
	 * 
	 * @param date
	 * @return
	 */
	private List<SortModel> filledData(String[] date) {
		List<SortModel> mSortList = new ArrayList<SortModel>();

		for (int i = 0; i < date.length; i++) {
			SortModel sortModel = new SortModel();
			sortModel.setName(date[i]);

			String pinyin = characterParser.getSelling(date[i]);
			String sortString = pinyin.substring(0, 1).toUpperCase();

			if (sortString.matches("[A-Z]")) {
				sortModel.setSortLetters(sortString.toUpperCase());
			} else {
				sortModel.setSortLetters("#");
			}
			sortModel.setTag(0);
			mSortList.add(sortModel);
		}
		return mSortList;

	}

	/**
	 * 
	 * 根据输入框中的值来过滤数据并更新ListView
	 * 
	 * @param filterStr
	 */
	// private void SetfilterData(String filterStr) {
	// List<SortModel> filterDateList = new ArrayList<SortModel>();
	//
	// if (TextUtils.isEmpty(filterStr)) {
	// filterDateList = SourceDateList;
	// } else {
	// filterDateList.clear();
	// for (SortModel sortModel : SourceDateList) {
	// String name = sortModel.getName();
	// if (name.indexOf(filterStr.toString()) != -1
	// || characterParser.getSelling(name).startsWith(
	// filterStr.toString())) {
	// filterDateList.add(sortModel);
	// }
	// }
	// }
	//
	// Collections.sort(filterDateList, pinyinComparator);
	// adapter.updateListView(filterDateList);
	// }

	public void settitleName(String name) {
		actionBar.setTitle(name);
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
			setResult(RESULT_OK);
			finish();
			break;
		}

		return super.onOptionsItemSelected(item);
	}

	class PinyinComparator implements Comparator<TagBean> {

		public int compare(TagBean o1, TagBean o2) {
			if (o1.getSortLetters().equals("@")
					|| o2.getSortLetters().equals("#")) {
				return -1;
			} else if (o1.getSortLetters().equals("#")
					|| o2.getSortLetters().equals("@")) {
				return 1;
			} else {
				return o1.getSortLetters().compareTo(o2.getSortLetters());
			}
		}

	}

}
