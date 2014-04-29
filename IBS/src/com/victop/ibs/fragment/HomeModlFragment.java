package com.victop.ibs.fragment;

import android.content.Intent;
import android.os.Bundle;
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
import com.victop.ibs.activity.R;

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

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.homemodl, null);
		initViews();
		initListeners();
		return view;
	}

	public void initData() {

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
			case R.id.btn_manager_search:
				break;
			case R.id.btn_manager_addmaterial:
				break;
			case R.id.btn_manager_receivedtask:
				break;

			case R.id.btn_manager_material:

				((MainActivity) getActivity()).rightToCenter(1);
				break;
			case R.id.btn_manager_sendedtask:
				break;
			case R.id.btn_employee_material:
				break;
			case R.id.btn_employee_task:
				break;
			case R.id.btn_addtask:
				Intent intent1 = new Intent((MainActivity) getActivity(),
						AddTaskActivity.class);
				startActivity(intent1);

				break;
			case R.id.btn_employee_addmateria:
				break;
			default:
				;
			}

		}
	};
}
