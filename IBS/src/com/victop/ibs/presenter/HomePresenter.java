package com.victop.ibs.presenter;

import java.util.HashMap;
import java.util.Map;

import android.os.Handler;

import com.victop.android.datachannel.DataChannelManager;
import com.victop.android.datachannel.GetDataParam;
import com.victop.ibs.bean.GetTaskCountBean;
import com.victop.ibs.bean.MaterialCountBean;
import com.victop.ibs.bean.SendTaskCountBean;
import com.victop.ibs.bean.UserMessageBean;
import com.victop.ibs.db.model.HomeModel;


public class HomePresenter {
	public void getInitData(Handler handler) {
		GetDataParam getDataParam = new GetDataParam();
		String systemId = "100";
		String formId = HomeModel.formId;
		String modelId = HomeModel.modelId;
		String datasetId = "1,3,4,5";//个人信息,素材个数,发布的任务个数,接受的任务个数
		getDataParam.setSystemId(systemId);
		getDataParam.setFormId(formId);
		getDataParam.setModelId(modelId);
		getDataParam.setDatasetId(datasetId);
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("addmanid", "1");
		map.put("responsibleid", "1");
		map.put("hrid", "1");
		getDataParam.setDataparamMap(map);
		Map<String, Class> clsMap = new HashMap<String, Class>();
		clsMap.put("1", UserMessageBean.class);
		clsMap.put("3", MaterialCountBean.class);
		clsMap.put("4", SendTaskCountBean.class);
		clsMap.put("5", GetTaskCountBean.class);
		getDataParam.setClassMap(clsMap);
		DataChannelManager.getInstance().getData(getDataParam, handler);
	}
}
