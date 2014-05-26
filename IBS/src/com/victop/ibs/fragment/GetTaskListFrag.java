package com.victop.ibs.fragment;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import com.victop.ibs.activity.R;
import com.victop.ibs.activity.TaskDetailActivity;
import com.victop.ibs.adapter.TaskListAdapter;
import com.victop.ibs.bean.GetTaskBean;
import com.victop.ibs.bean.Page;
import com.victop.ibs.handler.GetTaskHandler;
import com.victop.ibs.presenter.GetTaskPresenter;
import com.victop.ibs.util.Container;
import com.victop.pulltorefreshui.PullToRefreshBase;
import com.victop.pulltorefreshui.PullToRefreshBase.OnRefreshListener;
import com.victop.pulltorefreshui.PullToRefreshListView;

/**
 * 接受的任务列表fragment
 * 
 * @author vv
 */
public class GetTaskListFrag extends Fragment implements
		OnRefreshListener<ListView> {

	public int Model = 0;
	private PullToRefreshListView mPullListView;// 上拉下拉控件
	private Page task_allPage = new Page();// 全部任务分页对象
	private Page task_unfinishPage = new Page();// 未完成分页对象
	private Page task_finishPage = new Page();// 已完成分页对象
	private int pageno_all = 1;// 全部任务页码
	private int pageno_unfinish = 1;// 未完成任务页码
	private int pageno_finish = 1;// 已完成任务页码
	private List<GetTaskBean> task_list = new ArrayList<GetTaskBean>();// 全部任务数据集合
	private List<GetTaskBean> task_unlist = new ArrayList<GetTaskBean>();// 未完成任务数据集合
	private List<GetTaskBean> task_filist = new ArrayList<GetTaskBean>();// 已完成任务数据集合
	private List<GetTaskBean> task_list_data = new ArrayList<GetTaskBean>();// 加载完毕的全部任务数据集合
	private List<GetTaskBean> task_unlist_data = new ArrayList<GetTaskBean>();// 加载完毕的未完成任务数据集合
	private List<GetTaskBean> task_filist_data = new ArrayList<GetTaskBean>();// 加载完毕的已完成任务数据集合
	private GetTaskHandler taskHandler;// 网络请求获取数据handler
	private ListView mListView;
	private TaskListAdapter adapter;
	private String status = "0";
	private GetTaskPresenter gettask;// 网络数据装配对象
	private Context context;
	private SimpleDateFormat mDateFormat = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");
	private int pageSize;// 页面显示的条目数量

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
				adapter = new TaskListAdapter(context, task_list_data);
				mListView.setAdapter(adapter);
				adapter.notifyDataSetChanged();
				mPullListView.onPullDownRefreshComplete();
				mPullListView.onPullUpRefreshComplete();
				setLastUpdateTime();
				// 当返回的数量大于页面显示的条目数量,页码加一,设置列表有更多数据
				if (task_list.size() >= pageSize) {
					mPullListView.setHasMoreData(true);
					pageno_all++;
				} else {
					mPullListView.setHasMoreData(false);
				}

				break;
			}
			super.handleMessage(msg);
		}

	};

	/**
	 * 未完成任务handler
	 * */
	Handler handler_unfinish = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			switch (msg.what) {
			case 0:
				task_unlist = (List<GetTaskBean>) msg.obj;
				task_unlist_data.addAll(task_unlist);
				adapter = new TaskListAdapter(context, task_unlist_data);
				mListView.setAdapter(adapter);
				adapter.notifyDataSetChanged();
				mPullListView.onPullDownRefreshComplete();
				mPullListView.onPullUpRefreshComplete();
				setLastUpdateTime();
				// 当返回的数量大于页面显示的条目数量,页码加一,设置列表有更多数据
				if (task_unlist.size() >= pageSize) {
					mPullListView.setHasMoreData(true);
					pageno_unfinish++;
				} else {
					mPullListView.setHasMoreData(false);
				}
				break;
			}
			super.handleMessage(msg);
		}
	};

	/**
	 * 已完成任务handler
	 * */
	Handler handler_finish = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			switch (msg.what) {
			case 0:
				task_filist = (List<GetTaskBean>) msg.obj;
				task_filist_data.addAll(task_filist);
				adapter = new TaskListAdapter(context, task_filist_data);
				mListView.setAdapter(adapter);
				adapter.notifyDataSetChanged();
				mPullListView.onPullDownRefreshComplete();
				mPullListView.onPullUpRefreshComplete();
				setLastUpdateTime();
				if (task_filist.size() >= pageSize) {
					mPullListView.setHasMoreData(true);
					pageno_finish++;
				} else {
					mPullListView.setHasMoreData(false);
				}

				break;
			}
			super.handleMessage(msg);
		}
	};

	public void setModel(int Model) {
		this.Model = Model;
	}

	public GetTaskListFrag(int Model, Context context, String status) {
		this.Model = Model;
		this.context = context;
		this.status = status;
	}

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.from(context).inflate(R.layout.tasklist, null);
		initView(view);
		initListener();
		mPullListView.setOnRefreshListener(this);
		return view;
	}

	/**
	 * 初始化控件
	 * */
	public void initView(View view) {
		mPullListView = (PullToRefreshListView) view
				.findViewById(R.id.taskList);
		mPullListView.doPullRefreshing(true, 500);// 自动刷新
		mListView = mPullListView.getRefreshableView();
		mPullListView.setScrollLoadEnabled(true);
		mListView.setDivider(null);
		mListView.setDividerHeight(5);
		mListView.setCacheColorHint(R.drawable.translates);
		pageSize = Container.PAGESIZE;// 设置页面显示的条目数量
	}

	/**
	 * 绑定控件的监听事件
	 * */
	public void initListener() {
		mListView.setOnItemClickListener(mOnItemClick);
	}

	/**
	 * 列表的点击事件
	 * */
	OnItemClickListener mOnItemClick = new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int arg2,
				long id) {
			// TODO Auto-generated method stub
			Intent intent = new Intent(context, TaskDetailActivity.class);
			Bundle bundle = new Bundle();
			if (status.equals(Container.STATUS_ALL)) {
				bundle.putString("statue", task_list_data.get(arg2)
						.getTaskstatus());
				bundle.putString("taskid", task_list_data.get(arg2).getTaskid());

			} else if (status.equals(Container.STATUS_UNFINISH)) {
				bundle.putString("statue", task_unlist_data.get(arg2)
						.getTaskstatus());
				bundle.putString("taskid", task_unlist_data.get(arg2)
						.getTaskid());
			} else {
				bundle.putString("statue", task_filist_data.get(arg2)
						.getTaskstatus());
				bundle.putString("taskid", task_filist_data.get(arg2)
						.getTaskid());
			}
			intent.putExtras(bundle);
			startActivity(intent);

		}
	};

	/**
	 * 上拉加载更多数据
	 * */
	@Override
	public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
		// TODO Auto-generated method stub
		switch (Model) {
		case Container.MODEL_ALL:

			task_allPage = setPage(1, pageno_all, pageSize);
			initHandler(handler, null, task_allPage);
			break;
		case Container.MODEL_UNFINISH:

			task_unfinishPage = setPage(1, pageno_unfinish, pageSize);
			initHandler(handler_unfinish, Container.STATUS_UNFINISH, task_unfinishPage);
			break;
		case Container.MODEL_FINISH:

			task_finishPage = setPage(1, pageno_finish, pageSize);
			initHandler(handler_finish,Container.STATUS_FINISH, task_finishPage);
			break;
		}
	}

	/**
	 * 下拉刷新页面
	 * */
	@Override
	public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
		// TODO Auto-generated method stub

		switch (Model) {
		case Container.MODEL_ALL:
			// 清空原有数据
			if (task_list_data.size() > 0) {
				task_list_data.clear();
			}
			// 设置页码数为1
			pageno_all = 1;
			task_allPage = setPage(1, pageno_all, pageSize);
			initHandler(handler, null, task_allPage);
			break;
		case Container.MODEL_UNFINISH:
			// 清空原有数据
			if (task_unlist_data.size() > 0) {
				task_unlist_data.clear();
			}
			// 设置页码数为1
			pageno_unfinish = 1;
			task_unfinishPage = setPage(1, pageno_unfinish, pageSize);
			initHandler(handler_unfinish, "1", task_unfinishPage);
			break;
		case Container.MODEL_FINISH:
			// 清空原有数据
			if (task_filist_data.size() > 0) {
				task_filist_data.clear();
			}
			// 设置页码数为1
			pageno_finish = 1;
			task_finishPage = setPage(1, 1, pageSize);
			initHandler(handler_finish, "2", task_finishPage);
			break;
		}

	}

	/**
	 * 设置分页参数
	 * 
	 * @ispage 是否分页（0不分页,1分页）
	 * @param pageno
	 *            页码
	 * @param pagesize
	 *            页面显示的条数
	 * */
	public Page setPage(int ispage, int pageno, int pagesize) {
		Page page = new Page();
		page.setIspage(ispage);// 是否分页（1分页，0不分页）
		page.setPageno(pageno);// 页码
		page.setPagesize(pagesize);// 页面显示条目
		return page;
	}

	/**
	 * 请求网络数据装配方法
	 * */
	private void initHandler(Handler handler, String taskstatus, Page page) {
		taskHandler = new GetTaskHandler(context, handler);
		gettask = new GetTaskPresenter();
		gettask.getInitData(taskHandler, taskstatus, page);
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

}
