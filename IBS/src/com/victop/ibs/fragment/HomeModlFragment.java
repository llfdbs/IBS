package com.victop.ibs.fragment;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.victop.ibs.activity.AddTaskActivity;
import com.victop.ibs.activity.MainActivity;
import com.victop.ibs.activity.MaterialAllActivity;
import com.victop.ibs.activity.MaterialSearchActivity;
import com.victop.ibs.activity.R;
import com.victop.ibs.activity.SendedTaskListActivity;
import com.victop.ibs.activity.TaskListActivity;
import com.victop.ibs.bean.GetTaskCountBean;
import com.victop.ibs.bean.MaterialCountBean;
import com.victop.ibs.bean.SendTaskCountBean;
import com.victop.ibs.bean.UserMessageBean;
import com.victop.ibs.handler.HomeHandler;
import com.victop.ibs.presenter.HomePresenter;
import com.victop.ibs.presenter.PersonCenterPresenter;
import com.victop.ibs.util.Container;

/**
 * 首页模块 展示 首页的功能项
 * 
 * @author vv
 * 
 */
public class HomeModlFragment extends Fragment {
	private Button btn_manager_search, btn_manager_addmaterial,
			btn_manager_receivedtask, btn_manager_material,
			btn_manager_sendedtask, btn_employee_material, btn_employee_task,
			btn_addtask, btn_employee_addmateria;// 搜索按钮,新增素材,接受的任务,素材,发布的任务,员工素材,员工任务,新增任务,新增素材
	private View view;
	private ImageView img_userhead, img_manager_tag;// 用户头像,管理员标示
	private TextView tv_role;// 登陆人员身份
	private Map<String, List> dataMap;
	private List<UserMessageBean> userMessage = new ArrayList<UserMessageBean>();
	private List<MaterialCountBean> materialCount = new ArrayList<MaterialCountBean>();
	private List<GetTaskCountBean> getTaskCount = new ArrayList<GetTaskCountBean>();
	private List<SendTaskCountBean> sendTaskCount = new ArrayList<SendTaskCountBean>();
	private HomeHandler homeHandler;
	private HomePresenter hp;
	private String str_userName,str_headUrl,str_materialCout,str_getTaskCount,str_sendTaskCount;
	Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			switch (msg.what) {
			case 0:
				dataMap = (Map<String, List>) msg.obj;
				userMessage = dataMap.get("1");
				materialCount = dataMap.get("3");
				sendTaskCount = dataMap.get("4");
				getTaskCount = dataMap.get("5");
				if(null == userMessage){
					userMessage = new ArrayList<UserMessageBean>();
				}
				if(null == materialCount){
					materialCount = new ArrayList<MaterialCountBean>();
				}
				if(null == sendTaskCount){
					sendTaskCount = new ArrayList<SendTaskCountBean>();
				}
				if(null == getTaskCount){
					getTaskCount = new ArrayList<GetTaskCountBean>();
				}
				initData() ;
				

			}

			super.handleMessage(msg);
		}

	};
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.homemodl, null);
		initViews();
		initHandler(handler);
		initListeners();
		return view;
	}
	/**
	 * 请求网络数据装配方法
	 * */
	private void initHandler(Handler handler) {
		homeHandler = new HomeHandler((MainActivity) getActivity(), handler);
		hp = new HomePresenter();
		hp.getInitData(homeHandler);
	}

	public void initData() {
		if(userMessage.size()>0){
		str_userName = userMessage.get(0).getHrname();
		str_headUrl = userMessage.get(0).getHeadimage();
		tv_role.setText(str_userName);
		}else{
			tv_role.setText("");
		}
		if(userMessage.size()>0){
		str_materialCout = materialCount.get(0).getSummaterialid();
		btn_manager_material.setText("素材("+str_materialCout+")");
		}else{
			btn_manager_material.setText("素材");	
		}
		if(userMessage.size()>0){
		str_getTaskCount = getTaskCount.get(0).getSumtaskid();
		btn_manager_receivedtask.setText("接受的任务("+str_getTaskCount+")");
		}else{
		btn_manager_receivedtask.setText("接受的任务");
		}
		if(userMessage.size()>0){
		str_sendTaskCount = sendTaskCount.get(0).getSumtaskid();
		btn_manager_sendedtask.setText("发布的任务("+str_sendTaskCount+")");
		}else{
			btn_manager_sendedtask.setText("发布的任务");
		}
		
		
		
		
		
	}

	protected void initViews() {
		img_userhead = (ImageView) view.findViewById(R.id.img_userhead);
		img_manager_tag = (ImageView) view.findViewById(R.id.img_manager_tag);
		tv_role = (TextView) view.findViewById(R.id.tv_role);
		btn_manager_search = (Button) view
				.findViewById(R.id.btn_manager_search);
		btn_manager_addmaterial = (Button) view
				.findViewById(R.id.btn_manager_addmaterial);
		btn_manager_receivedtask = (Button) view
				.findViewById(R.id.btn_manager_receivedtask);
		btn_manager_material = (Button) view
				.findViewById(R.id.btn_manager_material);
		btn_manager_sendedtask = (Button) view
				.findViewById(R.id.btn_manager_sendedtask);

		btn_employee_material = (Button) view
				.findViewById(R.id.btn_employee_material);

		btn_employee_task = (Button) view.findViewById(R.id.btn_employee_task);

		btn_addtask = (Button) view.findViewById(R.id.btn_addtask);
		btn_employee_addmateria = (Button) view
				.findViewById(R.id.btn_employee_addmateria);

	}

	protected void initListeners() {
		img_userhead.setOnClickListener(mOnClick);
		btn_manager_search.setOnClickListener(mOnClick);
		btn_manager_addmaterial.setOnClickListener(mOnClick);
		btn_manager_receivedtask.setOnClickListener(mOnClick);
		btn_manager_material.setOnClickListener(mOnClick);
		btn_manager_sendedtask.setOnClickListener(mOnClick);
		btn_employee_material.setOnClickListener(mOnClick);
		btn_employee_task.setOnClickListener(mOnClick);
		btn_addtask.setOnClickListener(mOnClick);
		btn_employee_addmateria.setOnClickListener(mOnClick);
	}

	// 发布任务
	OnClickListener mOnClick = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
			case R.id.img_userhead://用户头头像
				((MainActivity) getActivity()).showMenuRight();
				break;
			case R.id.btn_manager_search://素材搜索
				//((MainActivity) getActivity()).rightToCenter(4,"");
				Intent intent = new Intent((MainActivity) getActivity(),MaterialSearchActivity.class);
				Bundle bundle = new Bundle();
				bundle.putString("modeobj", "material");
				bundle.putInt("tag",Container.MODEL_ALL);
				bundle.putString("title","全部素材搜索结果");
				intent.putExtras(bundle);
				startActivity(intent);
				break;
			case R.id.btn_manager_addmaterial:// 新增素材
				((MainActivity) getActivity()).rightToCenter(3,"");
				break;
			case R.id.btn_manager_receivedtask://接受的任务
				((MainActivity) getActivity()).rightToCenter(5,str_getTaskCount);
				break;

			case R.id.btn_manager_material://素材

				((MainActivity) getActivity()).rightToCenter(1,str_materialCout);
				break;
			case R.id.btn_manager_sendedtask://发布的任务
				((MainActivity) getActivity()).rightToCenter(6,str_sendTaskCount);
				break;
			case R.id.btn_employee_material:
				break;
			case R.id.btn_employee_task:
				break;
			case R.id.btn_addtask://新增任务
				((MainActivity) getActivity()).rightToCenter(7,"");

				break;
			case R.id.btn_employee_addmateria:
				break;
			default:
				;
			}

		}
	};
}
