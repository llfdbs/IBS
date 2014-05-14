package com.victop.ibs.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.victop.ibs.activity.LoginActivity;
import com.victop.ibs.activity.MainActivity;
import com.victop.ibs.activity.R;
import com.victop.ibs.activity.SendedTaskListActivity;
import com.victop.ibs.activity.TaskListActivity;

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
	private Button btn_exit;
	private Button num_uncomplete, num_audit, num_unaudit, num_gettask,
			num_settask;

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
		case R.id.lly_uncomplete://未完成的素材
			((MainActivity) getActivity()).rightToCenter(0);
			break;
		case R.id.lly_audit://已审核的素材
			((MainActivity) getActivity()).rightToCenter(1);
			break;
		case R.id.lly_unaudit://未审核的素材
			((MainActivity) getActivity()).rightToCenter(2);
			break;
		case R.id.lly_gettask://接受的任务
			((MainActivity) getActivity()).rightToCenter(5);
			break;
		case R.id.lly_settask://发布的任务
			((MainActivity) getActivity()).rightToCenter(6);
			break;
		case R.id.tv_mymaterial:
			((MainActivity) getActivity()).rightToCenter(0);
			break;
		case R.id.tv_mytask:
			((MainActivity) getActivity()).rightToCenter(5);
			break;
		case R.id.btn_exit://退出按钮
			((MainActivity) getActivity()).rightToCenter(8);
			getActivity().finish();
			break;

		}
	}

}
