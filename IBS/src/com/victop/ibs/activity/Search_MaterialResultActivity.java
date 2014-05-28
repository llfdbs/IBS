package com.victop.ibs.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import me.maxwin.view.XListView;
import me.maxwin.view.XListView.IXListViewListener;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.victop.ibs.adapter.MUnCheckAdapter;
import com.victop.ibs.base.ActivityBase;
import com.victop.ibs.bean.Page;
import com.victop.ibs.bean.UnCheckedMaterialBean;
import com.victop.ibs.db.model.AddMaterialModel;
import com.victop.ibs.handler.MaterialunCheckHandler;
import com.victop.ibs.presenter.Materialpresenter;

//import com.dodola.model.DuitangInfo;

/**
 * 全部素材，未审核素材 搜素素材 未完成素材
 * 
 * @author vv
 * 
 */
public class Search_MaterialResultActivity extends ActivityBase implements
		IXListViewListener, OnClickListener, OnItemSelectedListener {
	private XListView mAdapterView = null; // 自定义控件类
	private MUnCheckAdapter muAdapter = null;
	private int currentPage = 0;
	private Button btn_search = null;
	private Spinner sp_newtime;
	private final String MATERIAL = "material_style";
	private final String AUDIT = "audit";
	private final String UNADIT = "unaudit";
	private final String CHECKED = "check";
	private final String NOTCOMPLETE = "notcomplete";
	private final String[] mCountries = { "最新时间", "按首字母排序" };
	private ActionBar actionBar;// 导航栏
	private MenuItem search, add, save;// 搜索,添加，保存按钮
	private int state = 0;
	private final int UNCOMPLETE = 1;// 未完成
	private final int UNCHECK = 2;// 未审核
	private final int CHECK = 3;// yi审核（）
	private final int ALLMATL = 4;// 全部（）
	List<AddMaterialModel> mAddmaterialmodel = new ArrayList<AddMaterialModel>();
	private int temp = 0;
	Materialpresenter mGetpresenter;
	MaterialunCheckHandler unCheckHandler;
	List<UnCheckedMaterialBean> mUnCheckedMaterialBean = new ArrayList<UnCheckedMaterialBean>();
	private final int PAGESIAE = 6;
	private final int ISPAGE = 1;
	private int pageno = 1;
	HashMap<String, String> map = new HashMap<String, String>();
	Map<String, Class> clsMap = new HashMap<String, Class>();
    String count ="" ;
	@SuppressWarnings("unchecked")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.materitalall);

		Bundle b = getIntent().getExtras();
		if (b != null) {
			String rr = b.getString(MATERIAL);
			count = b.getString("count");
			if (rr.equals(AUDIT)) {
				state = ALLMATL;

			} else if (rr.equals(UNADIT)) {
				state = UNCHECK;

			} else if (rr.equals(NOTCOMPLETE)) {
				state = UNCOMPLETE;

			} else if (rr.equals(CHECKED)) {
				state = CHECK;

			}
		}
		initViews();
	}

	 

	Handler mHandler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			List<UnCheckedMaterialBean> unCheckedMaterialBean;
			switch (msg.what) {

			case 0:// 获取到数据
				switch (state) {
				case UNCOMPLETE:
					unCheckedMaterialBean = (List<UnCheckedMaterialBean>) msg.obj;
					muAdapter = new MUnCheckAdapter(Search_MaterialResultActivity.this,
							mAdapterView, state);
					if (null != unCheckedMaterialBean)
						mUnCheckedMaterialBean.addAll(unCheckedMaterialBean);
					else
						Toast.makeText(getApplicationContext(), "没有更多数据！", 600)
								.show();
					muAdapter.addItemLast(mUnCheckedMaterialBean);
					actionBar.setTitle("素材(未完成" +count
							+ ")");
					mAdapterView.setAdapter(muAdapter);
					break;
				case UNCHECK:
					unCheckedMaterialBean = (List<UnCheckedMaterialBean>) msg.obj;
					muAdapter = new MUnCheckAdapter(Search_MaterialResultActivity.this,
							mAdapterView, state);
					if (null != unCheckedMaterialBean)
						mUnCheckedMaterialBean.addAll(unCheckedMaterialBean);
					else
						Toast.makeText(getApplicationContext(), "没有更多数据！", 600)
								.show();
					muAdapter.addItemLast(mUnCheckedMaterialBean);
					actionBar.setTitle("素材(未审核" +count
							+ ")");
					mAdapterView.setAdapter(muAdapter);
					break;
				case CHECK:
					unCheckedMaterialBean = (List<UnCheckedMaterialBean>) msg.obj;
					muAdapter = new MUnCheckAdapter(Search_MaterialResultActivity.this,
							mAdapterView, state);
					if (null != unCheckedMaterialBean)
						mUnCheckedMaterialBean.addAll(unCheckedMaterialBean);
					else
						Toast.makeText(getApplicationContext(), "没有更多数据！", 600)
								.show();
					muAdapter.addItemLast(mUnCheckedMaterialBean);
					actionBar.setTitle("素材(审核" +count
							+ ")");
					mAdapterView.setAdapter(muAdapter);
					break;
				case ALLMATL:

					unCheckedMaterialBean = (List<UnCheckedMaterialBean>) msg.obj;
					muAdapter = new MUnCheckAdapter(Search_MaterialResultActivity.this,
							mAdapterView, state);
					if (null != unCheckedMaterialBean)
						mUnCheckedMaterialBean.addAll(unCheckedMaterialBean);
					else
						Toast.makeText(getApplicationContext(), "没有更多数据！", 600)
								.show();
					muAdapter.addItemLast(mUnCheckedMaterialBean);
					actionBar.setTitle("全部素材(" + count
							+ ")");
					mAdapterView.setAdapter(muAdapter);

					for (UnCheckedMaterialBean m : mUnCheckedMaterialBean) {
						System.out.println(m.getMaterialstatus());
					}
					break;

				}

				break;
			case 1:// 获取失败

				break;
			}
		}

	};

	@Override
	public void onRefresh() {
		// AddItemToContainer(++currentPage, 1);
		System.out.println("1222211");
		mAdapterView.stopRefresh();
	}

	@Override
	public void onLoadMore() {
		// AddItemToContainer(++currentPage, 2);
		System.out.println("111111111");
		Page page;
		switch (state) {
		case UNCOMPLETE:
			unCheckHandler = new MaterialunCheckHandler(this, mHandler, "1");
			page = setPage(ISPAGE, ++pageno, PAGESIAE);
			mGetpresenter.GetUfMaterial(unCheckHandler, page);
			break;
		case UNCHECK:
			unCheckHandler = new MaterialunCheckHandler(this, mHandler, "1");
			page = setPage(ISPAGE, ++pageno, PAGESIAE);
			mGetpresenter.GetUCMaterial(unCheckHandler, page);
			break;
		case CHECK:
			unCheckHandler = new MaterialunCheckHandler(this, mHandler, "6");
			page = setPage(ISPAGE, ++pageno, PAGESIAE);
			mGetpresenter.GetCMaterial(unCheckHandler, page);
			break;
		case ALLMATL:
			unCheckHandler = new MaterialunCheckHandler(this, mHandler, "1");
			page = setPage(ISPAGE, ++pageno, PAGESIAE);
			mGetpresenter.GetAlMaterial(unCheckHandler, page);
			break;

		}
		mAdapterView.stopLoadMore();
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btn_search:

			openActivity(MaterialSearchActivity.class, null);
			// finish();

			break;
		case R.id.sp_newtime:
			break;

		}
	}

	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void initData() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void initViews() {
		// TODO Auto-generated method stub

		btn_search = (Button) findViewById(R.id.btn_search);
		sp_newtime = (Spinner) findViewById(R.id.sp_newtime);
		btn_search.setOnClickListener(this);
		ArrayAdapter<String> ad = new ArrayAdapter<String>(this,
				R.layout.simple_spinner, mCountries);
		sp_newtime.setAdapter(ad);

		actionBar = getSupportActionBar();
		actionBar.setTitle("");
		actionBar.setHomeButtonEnabled(true);
		actionBar.setIcon(R.drawable.btn_back);

		mAdapterView = (XListView) findViewById(R.id.list);
		mAdapterView.setPullLoadEnable(true);
		mAdapterView.setXListViewListener(this);

		// AddItemToContainer(currentPage, 2);
		mGetpresenter = new Materialpresenter();
		Page page;
		switch (state) {
		case UNCOMPLETE:
			unCheckHandler = new MaterialunCheckHandler(this, mHandler, "1");
			page = setPage(ISPAGE, pageno, PAGESIAE);
			mGetpresenter.GetUfMaterial(unCheckHandler, page);
			break;
		case UNCHECK:
			unCheckHandler = new MaterialunCheckHandler(this, mHandler, "1");
			page = setPage(ISPAGE, pageno, PAGESIAE);
			mGetpresenter.GetUCMaterial(unCheckHandler, page);
			break;
		case CHECK:
			unCheckHandler = new MaterialunCheckHandler(this, mHandler, "6");
			page = setPage(ISPAGE, pageno, PAGESIAE);
			mGetpresenter.GetCMaterial(unCheckHandler, page);
			break;
		case ALLMATL:
			unCheckHandler = new MaterialunCheckHandler(this, mHandler, "1");
			page = setPage(ISPAGE, pageno, PAGESIAE);
			mGetpresenter.GetAlMaterial(unCheckHandler, page);
			break;

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
		add.setVisible(true);
		save.setVisible(false);

		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {
		case android.R.id.home:
			finish();
			break;
		// case R.id.search:
		// break;
		case R.id.add:
			openActivity(MaterialAddActivity.class, null);
			break;
		// case R.id.save:
		// break;
		}

		return super.onOptionsItemSelected(item);
	}

	// 设置分页参数
	public Page setPage(int ispage, int pageno, int pagesize) {
		Page page = new Page();
		page.setIspage(ispage);// 是否分页（1分页，0不分页）
		page.setPageno(pageno);// 页码
		page.setPagesize(pagesize);// 页面显示条目
		return page;
	}

}// end of class

