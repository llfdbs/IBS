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
import android.widget.Toast;

import com.victop.ibs.activity.AddTaskActivity;
import com.victop.ibs.activity.R;
import com.victop.ibs.activity.TaskDetailActivity;
import com.victop.ibs.adapter.SendTaskListAdapter;
import com.victop.ibs.bean.Page;
import com.victop.ibs.bean.SendTaskBean;
import com.victop.ibs.handler.SendTaskHandler;
import com.victop.ibs.presenter.SendTaskPresenter;
import com.victop.ibs.util.MyContainer;
import com.victop.pulltorefreshui.PullToRefreshBase;
import com.victop.pulltorefreshui.PullToRefreshBase.OnRefreshListener;
import com.victop.pulltorefreshui.PullToRefreshListView;

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
	//private int pageno_all = 1;// 全部任务页码
	//private int pageno_unfinish = 1;// 未完成任务页码
	//private int pageno_finish = 1;// 已完成任务页码
	//private int pageno_unsend = 1;// 未发放任务页码
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
				if(null==task_list){
					task_list = new ArrayList<SendTaskBean>();
				}
				task_list_data.addAll(task_list);
				adapter = new SendTaskListAdapter(context, task_list_data);
				mListView.setAdapter(adapter);
				adapter.notifyDataSetChanged();
				mPullListView.onPullDownRefreshComplete();
				mPullListView.onPullUpRefreshComplete();
				setLastUpdateTime();
				if (task_list_data.size() <= 0) {
					Toast.makeText(context, "暂无相关数据", Toast.LENGTH_SHORT)
							.show();
					return;
				}
				// 当返回的数量大于页面显示的条目数量,页码加一,设置列表有更多数据
				if (task_list.size() >= pageSize) {
					mPullListView.setHasMoreData(true);
					task_allPage.setPageno(task_allPage.getPageno()+1);
					//pageno_all++;
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
				if(null==task_unfinishList){
					task_unfinishList = new ArrayList<SendTaskBean>();
				}
				task_unfinishList_data.addAll(task_unfinishList);
				adapter = new SendTaskListAdapter(context,
						task_unfinishList_data);
				mListView.setAdapter(adapter);
				adapter.notifyDataSetChanged();
				mPullListView.onPullDownRefreshComplete();
				mPullListView.onPullUpRefreshComplete();
				setLastUpdateTime();
				if (task_unfinishList_data.size() <= 0) {
					Toast.makeText(context, "暂无相关数据", Toast.LENGTH_SHORT)
							.show();
					return;
				}
				// 当返回的数量大于页面显示的条目数量,页码加一,设置列表有更多数据
				if (task_unfinishList.size() >= pageSize) {
					mPullListView.setHasMoreData(true);
					//pageno_unfinish++;
					task_unfinishPage.setPageno(task_unfinishPage.getPageno()+1);
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
				if(null==task_finishList){
					task_finishList = new ArrayList<SendTaskBean>();
				}
				task_finishList_data.addAll(task_finishList);
				adapter = new SendTaskListAdapter(context, task_finishList_data);
				mListView.setAdapter(adapter);
				adapter.notifyDataSetChanged();
				mPullListView.onPullDownRefreshComplete();
				mPullListView.onPullUpRefreshComplete();
				setLastUpdateTime();
				if (task_finishList_data.size() <= 0) {
					Toast.makeText(context, "暂无相关数据", Toast.LENGTH_SHORT)
							.show();
					return;
				}
				if (task_finishList.size() >= pageSize) {
					mPullListView.setHasMoreData(true);
					task_finishPage.setPageno(task_finishPage.getPageno()+1);
					//pageno_finish++;
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
					if(null==task_unsendList){
						task_unsendList = new ArrayList<SendTaskBean>();
					}
					task_unsendList_data.addAll(task_unsendList);
					adapter = new SendTaskListAdapter(context,
							task_unsendList_data);
					mListView.setAdapter(adapter);
					adapter.notifyDataSetChanged();
					mPullListView.onPullDownRefreshComplete();
					mPullListView.onPullUpRefreshComplete();
					setLastUpdateTime();
					if (task_unsendList_data.size() <= 0) {
						Toast.makeText(context, "暂无相关数据", Toast.LENGTH_SHORT)
								.show();
						return;
					}
					if (task_unsendList.size() >= pageSize) {
						mPullListView.setHasMoreData(true);
						task_unsendPage.setPageno(task_unsendPage.getPageno()+1);
						//pageno_unsend++;
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
		pageSize = MyContainer.PAGESIZE;// 设置页面显示的条目数量
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
			Intent intent;
			Bundle bundle = new Bundle();
			if (status.equals(MyContainer.STATUS_ALL)) {

				if ("0".equals(task_list_data.get(arg2).getTaskstatus())) {
					intent = new Intent(context, AddTaskActivity.class);
				} else {
					intent = new Intent(context, TaskDetailActivity.class);
				}
				bundle.putString("statue", task_list_data.get(arg2)
						.getTaskstatus());
				bundle.putString("taskid", task_list_data.get(arg2).getTaskid());
			} else if (status.equals(MyContainer.STATUS_UNFINISH)) {
				intent = new Intent(context, TaskDetailActivity.class);
				bundle.putString("statue", task_unfinishList_data.get(arg2)
						.getTaskstatus());
				bundle.putString("taskid", task_unfinishList_data.get(arg2)
						.getTaskid());
			} else if (status.equals(MyContainer.STATUS_FINISH)) {
				intent = new Intent(context, TaskDetailActivity.class);
				bundle.putString("statue", task_finishList_data.get(arg2)
						.getTaskstatus());
				bundle.putString("taskid", task_finishList_data.get(arg2)
						.getTaskid());
			} else {
				intent = new Intent(context, AddTaskActivity.class);
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
		case MyContainer.MODEL_ALL:
			// 清空原有数据
			if (task_list_data.size() > 0) {
				task_list_data.clear();
			}
			// 设置页码数为1
			task_allPage.setPageno(1);
			initHandler(handler, null, task_allPage);
			break;
		case MyContainer.MODEL_UNFINISH:
			// 清空原有数据
			if (task_unfinishList_data.size() > 0) {
				task_unfinishList_data.clear();
			}
			// 设置页码数为1
			task_unfinishPage.setPageno(1);
			initHandler(handler_unfinish, MyContainer.STATUS_UNFINISH,
					task_unfinishPage);
			break;
		case MyContainer.MODEL_FINISH:
			// 清空原有数据
			if (task_finishList_data.size() > 0) {
				task_finishList_data.clear();
			}
			// 设置页码数为1
			task_finishPage.setPageno(1);
			initHandler(handler_finish, MyContainer.STATUS_FINISH,
					task_finishPage);
			break;
		case MyContainer.MODEL_UNSEND:
			// 清空原有数据
			if (task_unsendList_data.size() > 0) {
				task_unsendList_data.clear();
			}
			// 设置页码数为1
			task_unsendPage.setPageno(1);
			initHandler(handler_unsend, MyContainer.STATUS_ALL, task_unsendPage);
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
		case MyContainer.MODEL_ALL:

			
			initHandler(handler, null, task_allPage);
			break;
		case MyContainer.MODEL_UNFINISH:

			
			initHandler(handler_unfinish, "1", task_unfinishPage);
			break;
		case MyContainer.MODEL_FINISH:

			
			initHandler(handler_finish, "2", task_finishPage);
			break;
		case MyContainer.MODEL_UNSEND:

			
			initHandler(handler_finish, "0", task_finishPage);
			break;
		}

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
