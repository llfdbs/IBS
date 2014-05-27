package com.victop.ibs.fragment;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.victop.ibs.activity.R;
import com.victop.ibs.activity.TaskDetailActivity;
import com.victop.ibs.adapter.SendTaskListAdapter;
import com.victop.ibs.bean.Page;
import com.victop.ibs.bean.SendTaskBean;
import com.victop.ibs.handler.SendTaskHandler;
import com.victop.ibs.presenter.SendTaskPresenter;
import com.victop.ibs.util.Container;
import com.victop.pulltorefreshui.PullToRefreshBase;
import com.victop.pulltorefreshui.PullToRefreshListView;
import com.victop.pulltorefreshui.PullToRefreshBase.OnRefreshListener;

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
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

/**
 * 发布的任务列表fragment
 * 
 * @author vv
 */
public class SendedTaskListFrag extends Fragment implements
		OnRefreshListener<ListView> {
	public int Model = 0;
	private Context context;
	private List<SendTaskBean> task_list = new ArrayList<SendTaskBean>();// 全部数据
	private List<SendTaskBean> task_unfinishList = new ArrayList<SendTaskBean>();// 未完成数据
	private List<SendTaskBean> task_finishList = new ArrayList<SendTaskBean>();// 已完成数据
	private List<SendTaskBean> task_unsendList = new ArrayList<SendTaskBean>();// 未发放数据
	private List<SendTaskBean> task_list_data = new ArrayList<SendTaskBean>();// 加载完毕的全部数据
	private List<SendTaskBean> task_unfinishList_data = new ArrayList<SendTaskBean>();// 加载完毕的未完成数据
	private List<SendTaskBean> task_finishList_data = new ArrayList<SendTaskBean>();// 加载完毕的已完成数据
	private List<SendTaskBean> task_unsendList_data = new ArrayList<SendTaskBean>();// 加载完毕的未发放数据
	private Page task_allPage = new Page();// 全部任务分页对象
	private Page task_unfinishPage = new Page();// 未完成分页对象
	private Page task_finishPage = new Page();// 已完成分页对象
	private Page task_unsendPage = new Page();// 未发放分页对象
	private int pageno_all = 1;// 全部任务页码
	private int pageno_unfinish = 1;// 未完成任务页码
	private int pageno_finish = 1;// 已完成任务页码
	private int pageno_unsend = 1;// 未发放任务页码
	private int pageSize;// 页面显示的条目数量
	private SendTaskListAdapter adapter;
	private ListView mListView;
	private SendTaskHandler sendTaskHandler;// 请求网络数据handler
	private PullToRefreshListView mPullListView;// 下拉刷新控件
	private SendTaskPresenter sendTaskPresenter;// 网络数据装配对象
	private String status = "0";
	private SimpleDateFormat mDateFormat = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");

	public void setModel(int Model) {
		this.Model = Model;
	}

	public SendedTaskListFrag(int Model, Context context, String status) {
		this.Model = Model;
		this.context = context;
		this.status = status;
	}

	/**
	 * 全部任务handler
	 * */
	Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			switch (msg.what) {
			case 0:
				task_list = (List<SendTaskBean>) msg.obj;
				task_list_data.addAll(task_list);
				adapter = new SendTaskListAdapter(context, task_list_data);
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
			case 1:
				mPullListView.onPullDownRefreshComplete();
				mPullListView.onPullUpRefreshComplete();
				break;
			}
			super.handleMessage(msg);
		}

	};
	/**
	 * 未完成的任务handler
	 * */
	Handler handler_unfinish = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			switch (msg.what) {
			case 0:
				task_unfinishList = (List<SendTaskBean>) msg.obj;
				task_unfinishList_data.addAll(task_unfinishList);
				adapter = new SendTaskListAdapter(context,
						task_unfinishList_data);
				mListView.setAdapter(adapter);
				adapter.notifyDataSetChanged();
				mPullListView.onPullDownRefreshComplete();
				mPullListView.onPullUpRefreshComplete();
				setLastUpdateTime();
				// 当返回的数量大于页面显示的条目数量,页码加一,设置列表有更多数据
				if (task_unfinishList.size() >= pageSize) {
					mPullListView.setHasMoreData(true);
					pageno_unfinish++;
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
	/**
	 * 已完成的任务handler
	 * */
	Handler handler_finish = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			switch (msg.what) {
			case 0:
				task_finishList = (List<SendTaskBean>) msg.obj;
				task_finishList_data.addAll(task_finishList);
				adapter = new SendTaskListAdapter(context, task_finishList_data);
				mListView.setAdapter(adapter);
				adapter.notifyDataSetChanged();
				mPullListView.onPullDownRefreshComplete();
				mPullListView.onPullUpRefreshComplete();
				setLastUpdateTime();
				if (task_finishList.size() >= pageSize) {
					mPullListView.setHasMoreData(true);
					pageno_finish++;
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
	/**
	 * 未发放的任务handler
	 * */
	Handler handler_unsend = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			switch (msg.what) {
			case 0:
				if (null != msg.obj) {
					task_unsendList = (List<SendTaskBean>) msg.obj;
					task_unsendList_data.addAll(task_unsendList);
					adapter = new SendTaskListAdapter(context,
							task_unsendList_data);
					mListView.setAdapter(adapter);
					adapter.notifyDataSetChanged();
					mPullListView.onPullDownRefreshComplete();
					mPullListView.onPullUpRefreshComplete();
					setLastUpdateTime();
					if (task_unsendList.size() <= 0) {
						Toast.makeText(context, "暂无相关数据", Toast.LENGTH_SHORT)
								.show();
						return;
					}
					if (task_unsendList.size() >= pageSize) {
						mPullListView.setHasMoreData(true);
						pageno_unsend++;
					} else {
						mPullListView.setHasMoreData(false);

					}
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

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = LayoutInflater.from(context).inflate(
				R.layout.sendedtasklist, null);
		initView(view);
		initListener();
		mPullListView.setOnRefreshListener(this);
		return view;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);

	}

	/**
	 * 初始化控件
	 * */
	public void initView(View view) {
		mPullListView = (PullToRefreshListView) view
				.findViewById(R.id.sendedtaskList);
		mPullListView.doPullRefreshing(true, 500);
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

	OnItemClickListener mOnItemClick = new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			// TODO Auto-generated method stub
			Intent intent = new Intent(context, TaskDetailActivity.class);
			Bundle bundle = new Bundle();
			if (status.equals(Container.STATUS_ALL)) {
				bundle.putString("statue", task_list_data.get(arg2)
						.getTaskstatus());
				bundle.putString("taskid", task_list_data.get(arg2).getTaskid());
			} else if (status.equals(Container.STATUS_UNFINISH)) {
				bundle.putString("statue", task_unfinishList_data.get(arg2)
						.getTaskstatus());
				bundle.putString("taskid", task_unfinishList_data.get(arg2)
						.getTaskid());
			} else if (status.equals(Container.STATUS_FINISH)) {
				bundle.putString("statue", task_finishList_data.get(arg2)
						.getTaskstatus());
				bundle.putString("taskid", task_finishList_data.get(arg2)
						.getTaskid());
			} else {

				bundle.putString("statue", task_unsendList_data.get(arg2)
						.getTaskstatus());
				bundle.putString("taskid", task_unsendList_data.get(arg2)
						.getTaskid());

			}
			intent.putExtras(bundle);
			startActivity(intent);
		}
	};

	/**
	 * 下拉刷新
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
			setPage(task_allPage, 1, pageno_all, pageSize);
			initHandler(handler, null, task_allPage);
			break;
		case Container.MODEL_UNFINISH:
			// 清空原有数据
			if (task_unfinishList_data.size() > 0) {
				task_unfinishList_data.clear();
			}
			// 设置页码数为1
			pageno_unfinish = 1;
			setPage(task_unfinishPage, 1, pageno_unfinish, pageSize);
			initHandler(handler_unfinish, Container.STATUS_UNFINISH,
					task_unfinishPage);
			break;
		case Container.MODEL_FINISH:
			// 清空原有数据
			if (task_finishList_data.size() > 0) {
				task_finishList_data.clear();
			}
			// 设置页码数为1
			pageno_finish = 1;
			setPage(task_finishPage, 1, 1, pageSize);
			initHandler(handler_finish, Container.STATUS_FINISH,
					task_finishPage);
			break;
		case Container.MODEL_UNSEND:
			// 清空原有数据
			if (task_unsendList_data.size() > 0) {
				task_unsendList_data.clear();
			}
			// 设置页码数为1
			pageno_finish = 1;
			setPage(task_unsendPage, 1, 1, pageSize);
			initHandler(handler_unsend, Container.STATUS_ALL, task_unsendPage);
			break;
		}
	}

	/**
	 * 上拉加载更多数据
	 * */
	@Override
	public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
		// TODO Auto-generated method stub
		switch (Model) {
		case Container.MODEL_ALL:

			setPage(task_allPage, 1, pageno_all, pageSize);
			initHandler(handler, null, task_allPage);
			break;
		case Container.MODEL_UNFINISH:

			setPage(task_unfinishPage, pageno_unfinish, 1, pageSize);
			initHandler(handler_unfinish, "1", task_unfinishPage);
			break;
		case Container.MODEL_FINISH:

			setPage(task_finishPage, 1, pageno_finish, pageSize);
			initHandler(handler_finish, "2", task_finishPage);
			break;
		case Container.MODEL_UNSEND:

			setPage(task_finishPage, 1, pageno_unsend, pageSize);
			initHandler(handler_finish, "0", task_finishPage);
			break;
		}

	}

	/**
	 * 设置分页参数
	 * 
	 * @page 分页对象
	 * @ispage 是否分页（0不分页,1分页）
	 * @param pageno页码
	 * @param pagesize
	 *            页面显示的条数
	 * */
	public void setPage(Page page, int ispage, int pageno, int pagesize) {
		page.setIspage(ispage);// 是否分页（1分页，0不分页）
		page.setPageno(pageno);// 页码
		page.setPagesize(pagesize);// 页面显示条目
	}

	/**
	 * 请求网络数据装配方法
	 * */
	private void initHandler(Handler handler, String taskstatus, Page page) {
		sendTaskHandler = new SendTaskHandler(context, handler);
		sendTaskPresenter = new SendTaskPresenter();
		sendTaskPresenter.getInitData(sendTaskHandler, taskstatus, page);
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
