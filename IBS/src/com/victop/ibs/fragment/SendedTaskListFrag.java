package com.victop.ibs.fragment;

import java.util.ArrayList;
import java.util.List;
import com.victop.ibs.adapter.SendTaskListAdapter;
import com.victop.ibs.bean.SendTaskBean;
import com.victop.pulltorefreshui.PullToRefreshBase;
import com.victop.pulltorefreshui.PullToRefreshBase.OnRefreshListener;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.widget.ListView;

public class SendedTaskListFrag extends Fragment implements
OnRefreshListener<ListView>{
	public int Model = 0;
	private Context context;
	private List<SendTaskBean> task_list = new ArrayList<SendTaskBean>();// 全部
	private List<SendTaskBean> task_unfinishList = new ArrayList<SendTaskBean>();// 未完成
	private List<SendTaskBean> task_finishList = new ArrayList<SendTaskBean>();// 已完成
	private List<SendTaskBean> task_unsendList = new ArrayList<SendTaskBean>();// 未发放
	private SendTaskListAdapter adapter;
	private ListView mListView;

	public void setModel(int Model) {
		this.Model = Model;
	}

	public SendedTaskListFrag(int Model, Context context) {
		this.Model = Model;
		this.context = context;
	}

	Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			switch (msg.what) {
			case 0:
				task_list = (List<SendTaskBean>) msg.obj;
				adapter = new SendTaskListAdapter(context, task_list);
				mListView.setAdapter(adapter);
				adapter.notifyDataSetChanged();

				break;
			}
			super.handleMessage(msg);
		}

	};
	Handler handler_unfinish = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			switch (msg.what) {
			case 0:
				task_unfinishList = (List<SendTaskBean>) msg.obj;
				adapter = new SendTaskListAdapter(context, task_unfinishList);
				mListView.setAdapter(adapter);
				adapter.notifyDataSetChanged();
				break;
			}
			super.handleMessage(msg);
		}

	};
	Handler handler_finish = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			switch (msg.what) {
			case 0:
				task_finishList = (List<SendTaskBean>) msg.obj;
				adapter = new SendTaskListAdapter(context, task_finishList);
				mListView.setAdapter(adapter);
				adapter.notifyDataSetChanged();
				break;
			}
			super.handleMessage(msg);
		}

	};
	Handler handler_unsend = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			switch (msg.what) {
			case 0:
				if (null != msg.obj) {
					task_unsendList = (List<SendTaskBean>) msg.obj;
					adapter = new SendTaskListAdapter(context, task_unsendList);
					mListView.setAdapter(adapter);
					adapter.notifyDataSetChanged();
				}
				break;
			}
			super.handleMessage(msg);
		}

	};
    //下拉刷新
	@Override
	public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
		// TODO Auto-generated method stub
		
	}
    //上拉加载更多数据
	@Override
	public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
		// TODO Auto-generated method stub
		
	}

}
