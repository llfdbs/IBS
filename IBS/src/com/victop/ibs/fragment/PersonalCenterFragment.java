package com.victop.ibs.fragment;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import android.app.Activity;
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
import android.widget.LinearLayout;
import android.widget.TextView;

import com.victop.ibs.activity.MainActivity;
import com.victop.ibs.activity.R;
import com.victop.ibs.bean.CheckedMaterailCountBean;
import com.victop.ibs.bean.GetTaskCountBean;
import com.victop.ibs.bean.MaterialCountBean;
import com.victop.ibs.bean.SendTaskCountBean;
import com.victop.ibs.bean.UnCheckedMaterialCountBean;
import com.victop.ibs.bean.UnfinishedMaterialCountBean;
import com.victop.ibs.bean.UserMessageBean;
import com.victop.ibs.handler.HomeHandler;
import com.victop.ibs.presenter.PersonCenterPresenter;

/**
 * 个人中心模块 功能描述：列表Fragment，用来显示滑动菜单打开后的个人中心内容
 * 
 * @author vv
 * 
 */

public class PersonalCenterFragment extends Fragment implements OnClickListener {
	private ImageView iv_icon, iv_head;// iv_icon iv_head 头像
	private LinearLayout lly_uncomplete, lly_audit, lly_unaudit, lly_gettask,
			lly_settask;
	private TextView tv_mymaterial, tv_username, tv_mytask;
	private Button btn_exit;// 退出按钮
	private Button num_uncomplete, num_audit, num_unaudit, num_gettask,
			num_settask;
	private String str_userName, str_headUrl, str_unfinishedMaterialCount,
			str_checkedMaterialCount, str_uncheckedMaterialCount,
			str_getTaskCount, str_sendTaskCount;
	private HomeHandler homeHandler;
	private PersonCenterPresenter pcp;
	private List<UserMessageBean> userMessage = new ArrayList<UserMessageBean>();
	private List<MaterialCountBean> materialCount = new ArrayList<MaterialCountBean>();
	private List<UnfinishedMaterialCountBean> unfinishedMaterialCount = new ArrayList<UnfinishedMaterialCountBean>();
	private List<CheckedMaterailCountBean> checkedMaterialCount = new ArrayList<CheckedMaterailCountBean>();
	private List<UnCheckedMaterialCountBean> uncheckedMaterialCount = new ArrayList<UnCheckedMaterialCountBean>();
	private List<GetTaskCountBean> getTaskCount = new ArrayList<GetTaskCountBean>();
	private List<SendTaskCountBean> sendTaskCount = new ArrayList<SendTaskCountBean>();
	private Map<String, List> dataMap;
	Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			switch (msg.what) {
			case 0:
				dataMap = (Map<String, List>) msg.obj;
				userMessage = dataMap.get("10");
				materialCount = dataMap.get("3");
				unfinishedMaterialCount = dataMap.get("13");
				checkedMaterialCount = dataMap.get("11");
				uncheckedMaterialCount = dataMap.get("12");
				getTaskCount = dataMap.get("8");
				sendTaskCount = dataMap.get("9");
				//getTaskCount = dataMap.get("8")!=null?dataMap.get(8):new ArrayList<GetTaskCountBean>();
				if(null == userMessage){
					userMessage = new ArrayList<UserMessageBean>();
				}
				if(null == materialCount){
					materialCount = new ArrayList<MaterialCountBean>();
				}
				if(null == sendTaskCount){
					unfinishedMaterialCount = new ArrayList<UnfinishedMaterialCountBean>();
				}
				if(null == getTaskCount){
					checkedMaterialCount = new ArrayList<CheckedMaterailCountBean>();
				}
				if(null == userMessage){
					uncheckedMaterialCount = new ArrayList<UnCheckedMaterialCountBean>();
				}
				if(null == materialCount){
					getTaskCount = new ArrayList<GetTaskCountBean>();
				}
				if(null == sendTaskCount){
					sendTaskCount = new ArrayList<SendTaskCountBean>();
				}
				
				initData() ;
				

			}

			super.handleMessage(msg);
		}

	};

	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.personalcenter, null);
		initView(view);
		initHandler(handler);
		return view;
	}

	private void initView(View view) {
		iv_icon = (ImageView) view.findViewById(R.id.img_manager_tag);
		iv_head = (ImageView) view.findViewById(R.id.img_usercenter_userhead); // 头像
		lly_uncomplete = (LinearLayout) view.findViewById(R.id.lly_uncomplete);
		lly_audit = (LinearLayout) view.findViewById(R.id.lly_audit);
		lly_unaudit = (LinearLayout) view.findViewById(R.id.lly_unaudit);
		lly_gettask = (LinearLayout) view.findViewById(R.id.lly_gettask);
		lly_settask = (LinearLayout) view.findViewById(R.id.lly_settask);
		tv_mymaterial = (TextView) view.findViewById(R.id.tv_mymaterial);
		tv_username = (TextView) view.findViewById(R.id.tv_username);
		tv_mytask = (TextView) view.findViewById(R.id.tv_mytask);
		btn_exit = (Button) view.findViewById(R.id.btn_exit);
		num_uncomplete = (Button) view.findViewById(R.id.btn_uncomplete);
		num_audit = (Button) view.findViewById(R.id.btn_audit);
		num_unaudit = (Button) view.findViewById(R.id.btn_unaudit);
		num_gettask = (Button) view.findViewById(R.id.btn_gettask);
		num_settask = (Button) view.findViewById(R.id.btn_settask);
	}

	/**
	 * 请求网络数据装配方法
	 * */
	private void initHandler(Handler handler) {
		homeHandler = new HomeHandler((MainActivity) getActivity(), handler);
		pcp = new PersonCenterPresenter();
		pcp.getInitData(homeHandler);
	}

	public void initData() {
		// TODO Auto-generated method stub
		if (userMessage.size() > 0) {
			str_userName = userMessage.get(0).getHrname();
			str_headUrl = userMessage.get(0).getHeadimage();
		} else {
			str_userName = "";
		}
		if (unfinishedMaterialCount.size() > 0) {
			str_unfinishedMaterialCount = unfinishedMaterialCount.get(0)
					.getSummaterialid();
		} else {
			str_unfinishedMaterialCount = "0";
		}

		if (checkedMaterialCount.size() > 0) {
			str_checkedMaterialCount = checkedMaterialCount.get(0)
					.getSummaterialid();
		} else {
			str_checkedMaterialCount = "0";
		}

		if (uncheckedMaterialCount.size() > 0) {
			str_uncheckedMaterialCount = uncheckedMaterialCount.get(0)
					.getSummaterialid();
		} else {
			str_uncheckedMaterialCount = "0";
		}

		if (getTaskCount.size() > 0) {
			str_getTaskCount = getTaskCount.get(0).getSumtaskid();
		} else {
			str_getTaskCount = "0";
		}

		if (sendTaskCount.size() > 0) {
			str_sendTaskCount = sendTaskCount.get(0).getSumtaskid();
		} else {
			str_sendTaskCount = "0";
		}
		num_uncomplete.setText(str_unfinishedMaterialCount);
		num_audit.setText(str_checkedMaterialCount);
		num_unaudit.setText(str_uncheckedMaterialCount);
		num_gettask.setText(str_getTaskCount);
		num_settask.setText(str_sendTaskCount);
		tv_username.setText(str_userName);

	}

	private void initListener() {
		lly_uncomplete.setOnClickListener(this);
		lly_audit.setOnClickListener(this);
		lly_unaudit.setOnClickListener(this);
		lly_gettask.setOnClickListener(this);
		lly_settask.setOnClickListener(this);
		tv_mymaterial.setOnClickListener(this);
		tv_mytask.setOnClickListener(this);
		btn_exit.setOnClickListener(this);

	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		initListener();
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.lly_uncomplete:// 未完成的素材
			((MainActivity) getActivity()).rightToCenter(0,
					str_unfinishedMaterialCount);
			break;
		case R.id.lly_audit:// 已审核的素材
			((MainActivity) getActivity()).rightToCenter(1,
					str_checkedMaterialCount);
			break;
		case R.id.lly_unaudit:// 未审核的素材
			((MainActivity) getActivity()).rightToCenter(2,
					str_uncheckedMaterialCount);
			break;
		case R.id.lly_gettask:// 接受的任务
			((MainActivity) getActivity()).rightToCenter(5, str_getTaskCount);
			break;
		case R.id.lly_settask:// 发布的任务
			((MainActivity) getActivity()).rightToCenter(6, str_sendTaskCount);
			break;
		case R.id.tv_mymaterial:
			((MainActivity) getActivity()).rightToCenter(0,
					str_unfinishedMaterialCount);
			break;
		case R.id.tv_mytask:
			((MainActivity) getActivity()).rightToCenter(5, str_getTaskCount);
			break;
		case R.id.btn_exit:// 退出按钮
			((MainActivity) getActivity()).rightToCenter(8, "");
			getActivity().finish();
			break;

		}
	}

}
