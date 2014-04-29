package com.victop.ibs.activity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.victop.ibs.adapter.Tag_SortAdapter;
import com.victop.ibs.app.ibsApplication;
import com.victop.ibs.base.ActivityBase;
import com.victop.ibs.bean.SortModel;
import com.victop.ibs.util.CharacterParser;
import com.victop.ibs.util.PinyinComparator;
import com.victop.ibs.view.ClearEditText;
import com.victop.ibs.view.SideBar;
import com.victop.ibs.view.SideBar.OnTouchingLetterChangedListener;

/**
 * 素材标签类
 * 
 * @author vv
 * 
 */
public class TagActivity extends ActivityBase implements OnClickListener {
	private ListView sortListView;
	private SideBar sideBar;
	private TextView dialog;
	private Tag_SortAdapter adapter;
	private ClearEditText mClearEditText;

	private CharacterParser characterParser;
	private List<SortModel> SourceDateList;

	private PinyinComparator pinyinComparator;
	private Button btn_back, btn_ok, btn_other;
	private TextView tv_title;

	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		final View view = View.inflate(this, R.layout.taglayout, null);
		setContentView(view);
		ibsApplication.getInstance().addActivity(this);
		initData();
		initViews();
		initListeners();

	}

	@Override
	protected void initData() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void initViews() {
		// TODO Auto-generated method stub
		btn_back = (Button) findViewById(R.id.back);
		tv_title = (TextView) findViewById(R.id.title);
		btn_other = (Button) findViewById(R.id.search);
		btn_ok = (Button) findViewById(R.id.add);

		btn_back.setOnClickListener(this);
		btn_ok.setText("确定");
		btn_ok.setOnClickListener(this);
		btn_other.setVisibility(View.GONE);
		tv_title.setText("标签");

		characterParser = CharacterParser.getInstance();

		pinyinComparator = new PinyinComparator();

		sideBar = (SideBar) findViewById(R.id.sidrbar);
		dialog = (TextView) findViewById(R.id.dialog);
		sideBar.setTextView(dialog);

		sideBar.setOnTouchingLetterChangedListener(new OnTouchingLetterChangedListener() {

			@Override
			public void onTouchingLetterChanged(String s) {

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

				Toast.makeText(getApplication(),
						((SortModel) adapter.getItem(position)).getName(),
						Toast.LENGTH_SHORT).show();

			}
		});

		SourceDateList = filledData(getResources().getStringArray(R.array.date));

		Collections.sort(SourceDateList, pinyinComparator);
		// for (SortModel s : SourceDateList) {
		// System.out.println(s.getName() + "   " + s.getSortLetters());
		// }
		adapter = new Tag_SortAdapter(this, SourceDateList);
		sortListView.setAdapter(adapter);

		mClearEditText = (ClearEditText) findViewById(R.id.filter_edit);

		mClearEditText.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// ������������ֵΪ�գ�����Ϊԭ�����б?����Ϊ��������б�
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
	public void onClick(View arg0) {
		// TODO Auto-generated method stub

	}

	private void findString(String str) {
		if (TextUtils.isEmpty(str)) {
			sortListView.setSelection(0);
		} else {
			for (SortModel sortModel : SourceDateList) {
				String name = sortModel.getName();
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
	 * 
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
	 * 
	 * @param filterStr
	 */
	private void filterData(String filterStr) {
		List<SortModel> filterDateList = new ArrayList<SortModel>();

		if (TextUtils.isEmpty(filterStr)) {
			filterDateList = SourceDateList;
		} else {
			filterDateList.clear();
			for (SortModel sortModel : SourceDateList) {
				String name = sortModel.getName();
				if (name.indexOf(filterStr.toString()) != -1
						|| characterParser.getSelling(name).startsWith(
								filterStr.toString())) {
					filterDateList.add(sortModel);
				}
			}
		}

		Collections.sort(filterDateList, pinyinComparator);
		adapter.updateListView(filterDateList);
	}

	public void settitleName(String name) {
		tv_title.setText(name);
	}
}
