package com.victop.ibs.presenter;

import java.util.HashMap;
import java.util.Map;

import android.os.Handler;

import com.victop.android.datachannel.DataChannelManager;
import com.victop.android.datachannel.GetDataParam;
import com.victop.ibs.bean.SendTaskBean;
import com.victop.ibs.bean.TaskDetailBean;
import com.victop.ibs.bean.TaskMaterialsBean;
import com.victop.ibs.db.model.TaskDetailModel;

/**
 * 任务详情装配
 * 
 * @author vv
 * 
 */
public class TaskDetailPresenter {
	public void getInitData(Handler handler, String taskid) {
		GetDataParam getDataParam = new GetDataParam();
		String systemId = "100";
		String formId = TaskDetailModel.formId;
		String modelId = TaskDetailModel.modelId;
		String datasetId = TaskDetailBean.datasetId+","+TaskMaterialsBean.datasetId;
		getDataParam.setSystemId(systemId);
		getDataParam.setFormId(formId);
		getDataParam.setModelId(modelId);
		getDataParam.setDatasetId(datasetId);

		HashMap<String, String> map =new HashMap<String, String>();
		map.put("taskid",taskid);
		getDataParam.setDataparamMap(map);
		

		Map<String, Class> clsMap = new HashMap<String, Class>();
		clsMap.put("1",TaskDetailBean.class);
		clsMap.put("2",TaskMaterialsBean.class);
		getDataParam.setClassMap(clsMap);

		DataChannelManager.getInstance().getData(getDataParam, handler);
	}
}
