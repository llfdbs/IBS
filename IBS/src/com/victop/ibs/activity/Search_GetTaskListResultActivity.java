package com.victop.ibs.activity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.victop.ibs.adapter.TaskListAdapter;
import com.victop.ibs.app.IBSApplication;
import com.victop.ibs.base.ActivityBase;
import com.victop.ibs.bean.GetTaskBean;
import com.victop.ibs.bean.Page;
import com.victop.ibs.handler.GetTaskHandler;
import com.victop.ibs.presenter.GetTaskListSearchResultPresenter;
import com.victop.ibs.presenter.SendTaskSearchResultPresenter;
import com.victop.ibs.util.Container;
import com.victop.pulltorefreshui.PullToRefreshBase;
import com.victop.pulltorefreshui.PullToRefreshBase.OnRefreshListener;
import com.victop.pulltorefreshui.PullToRefreshListView;

/**
 * 接受的任务搜索结果页面
 * 
 * @author vv
 * 
 */
public class Search_GetTaskListResultActivity extends ActivityBase implements
		OnRefreshListener<ListView> {
	private ActionBar actionBar;// 导航栏
	private MenuItem search, add, save;// 搜索,添加，保存按钮
	private PullToRefreshListView mPullListView;// 上拉下拉控件
	private ListView mListView;
	private Page task_allPage = new Page();// 全部任务分页对象
	private List<GetTaskBean> task_list = new ArrayList<GetTaskBean>();// 全部任务数据集合
	private List<GetTaskBean> task_list_data = new ArrayList<GetTaskBean>();// 加载完毕的全部任务数据集合
	private GetTaskHandler taskHandler;// 网络请求获取数据handler
	private TaskListAdapter adapter;
	private GetTaskListSearchResultPresenter taskResult;// 网络数据装配对象
	private SendTaskSearchResultPresenter send_taskResult;//发布的任务数据封装对象
	private SimpleDateFormat mDateFormat = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");
	private String keyword = "";
	private int model = 0;
	private String title = "";
	/**
	 * 全部任务handler
	 * */
	Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			switch (msg.what) {
			case 0:
				task_list = (List<GetTaskBean>) msg.obj;
				task_list_data.addAll(task_list);
				adapter = new TaskListAdapter(
						Search_GetTaskListResultActivity.this, task_list_data);
				mListView.setAdapter(adapter);
				adapter.notifyDataSetChanged();
				mPullListView.onPullDownRefreshComplete();
				mPullListView.onPullUpRefreshComplete();
				setLastUpdateTime();
				if (task_list_data.size() <= 0) {
					Toast.makeText(Search_GetTaskListResultActivity.this,
							"暂无相关数据", Toast.LENGTH_SHORT).show();
					return;
				}
				// 当返回的数量大于页面显示的条目数量,页码加一,设置列表有更多数据
				if (task_list.size() >= task_allPage.getPagesize()) {
					mPullListView.setHasMoreData(true);

					task_allPage.setPageno(task_allPage.getPageno() + 1);

				} else {
					mPullListView.setHasMoreData(false);
				}

				break;
			case 1:
				mPullListView.onPullDownRefreshComplete();
				mPullListView.onPullUpRefreshComplete();
				break;
			}
			super.handleMessage(msg);
		}

	};

	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);

		setContentView(R.layout.search_gettaskresult);
		IBSApplication.getInstance().addActivity(this);
		initData();
		initViews();
		initListeners();

	}

	@Override
	protected void initData() {
		// TODO Auto-generated method stub
		Bundle bundle = getIntent().getExtras();
		keyword = bundle.getString("keyword");
		model = bundle.getInt("model");
		title = bundle.getString("title");
	}

	@Override
	protected void initViews() {
		// TODO Auto-generated method stub
		actionBar = getSupportActionBar();

		actionBar.setTitle(title);
		actionBar.setHomeButtonEnabled(true);
		actionBar.setIcon(R.drawable.btn_back);
		mPullListView = (PullToRefreshListView) findViewById(R.id.search_taskList);
		mPullListView.doPullRefreshing(true, 500);// 自动刷新
		mListView = mPullListView.getRefreshableView();
		mPullListView.setScrollLoadEnabled(true);
		mListView.setDivider(null);
		mListView.setDividerHeight(5);
		mListView.setCacheColorHint(R.drawable.translates);

	}

	@Override
	protected void initListeners() {
		// TODO Auto-generated method stub
		mPullListView.setOnRefreshListener(this);
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
		case R.id.search:

			break;
		case R.id.add:

			break;

		}

		return super.onOptionsItemSelected(item);
	}

	/**
	 * 请求网络数据装配方法
	 * */
	private void initHandler(Handler handler, String taskstatus, Page page) {
		taskHandler = new GetTaskHandler(Search_GetTaskListResultActivity.this,
				handler);
		taskResult = new GetTaskListSearchResultPresenter();
		taskResult.getInitData(taskHandler, taskstatus, page, keyword);
	}

	/**
	 * 设置更新时间
	 * */
	private void setLastUpdateTime() {
		String text = formatDateTime(System.currentTimeMillis());
		mPullListView.setLastUpdatedLabel(text);
	}

	private String formatDateTime(long time) {
		if (0 == time) {
			return "";
		}

		return mDateFormat.format(new Date(time));
	}

	// 下拉刷新
	@Override
	public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
		// TODO Auto-generated method stub
		// 清空原有数据
		if (task_list_data.size() > 0) {
			task_list_data.clear();
		}

		// 设置页码数为1
		task_allPage.setPageno(1);
		switch (model) {
		case Container.MODEL_ALL:

			initHandler(handler, null, task_allPage);
			break;
		case Container.MODEL_UNFINISH:
			initHandler(handler, "1", task_allPage);
			break;
		case Container.MODEL_FINISH:
			initHandler(handler, "2", task_allPage);
		case Container.S_MODEL_ALL:
			
			break;
		case Container.S_MODEL_UNFINISH:
			break;
		case Container.S_MODEL_FINISH:
			break;
		case Container.MODEL_UNSEND:
			break;

		}
	}

	// 上拉加载更多
	@Override
	public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
		// TODO Auto-generated method stub
		switch (model) {
		case Container.MODEL_ALL:
			initHandler(handler, null, task_allPage);
			break;
		case Container.MODEL_UNFINISH:
			initHandler(handler, "1", task_allPage);
			break;
		case Container.MODEL_FINISH:
			initHandler(handler, "2", task_allPage);
			break;

		}
	}
}
