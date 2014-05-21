package com.victop.ibs.presenter;

import java.util.HashMap;
import java.util.Map;

import android.os.Handler;

import com.victop.android.datachannel.DataChannelManager;
import com.victop.android.datachannel.GetDataParam;
import com.victop.ibs.activity.TaskListActivity;
import com.victop.ibs.bean.GetTaskBean;
/**
 * 接收的任务列表装配
 * 
 * @author vv
 * 
 */

public class GetTaskPresenter {
	public void getInitData(Handler handler,String taskstatus){
		GetDataParam getDataParam = new GetDataParam();
		String systemId = "100";
		String formId = "10211";
		String modelId = "IBS10224";
		String datasetId = "1";
		getDataParam.setSystemId(systemId);
		getDataParam.setFormId(formId);
		getDataParam.setModelId(modelId);
		getDataParam.setDatasetId(datasetId);
		HashMap<String, String> map =new HashMap<String, String>();
		map.put("hrid","1");
		getDataParam.setDataparamMap(map);
		
		if(null!=taskstatus){
			HashMap<String,String> whereMap = new HashMap<String, String>();
			whereMap.put("1","taskstatus="+taskstatus);
			getDataParam.setWhereMap(whereMap);
		}
		
		Map<String,Class> clsMap = new HashMap<String, Class>();
		clsMap.put("1",GetTaskBean.class);
		getDataParam.setClassMap(clsMap);
		
		
		DataChannelManager.getInstance().getData(getDataParam, handler);
	}
}
