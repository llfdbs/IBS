package com.victop.ibs.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.victop.ibs.activity.R;

/**
 * 首页模块 展示 首页的功能项
 * 
 * @author vv
 * 
 */
public class HomeModlFragment extends Fragment {
	private Button btn = null;

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.homemodl, null);
		return view;
	}

}
