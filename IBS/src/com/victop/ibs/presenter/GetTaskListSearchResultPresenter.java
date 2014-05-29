package com.victop.ibs.presenter;

import java.util.HashMap;
import java.util.Map;

import android.os.Handler;

import com.victop.android.datachannel.DataChannelManager;
import com.victop.android.datachannel.GetDataParam;
import com.victop.android.session.Container;
import com.victop.ibs.bean.GetTaskBean;
import com.victop.ibs.bean.Page;

public class GetTaskListSearchResultPresenter {
	public void getInitData(Handler handler,String taskstatus,Page page,String keyword){
		GetDataParam getDataParam = new GetDataParam();
		String systemId = "100";
		String formId = GetTaskBean.formId;
		String modelId = GetTaskBean.modelId;
		String datasetId = GetTaskBean.datasetId;
		getDataParam.setSystemId(systemId);
		getDataParam.setFormId(formId);
		getDataParam.setModelId(modelId);
		getDataParam.setDatasetId(datasetId);
		HashMap<String, String> map =new HashMap<String, String>();
		map.put("hrid",Container.getInstance().getUser().getUserCode());
		map.put("ispage", page.getIspage()+"");
		map.put("pageno", page.getPageno()+"");
		map.put("pagesize",page.getPagesize()+"");
		getDataParam.setDataparamMap(map);
		
		HashMap<String,String> whereMap = new HashMap<String, String>();
		if(null!=taskstatus){
			whereMap.put("1","taskstatus="+taskstatus+" and taskname like '%"+keyword+"%' ");
		}else {
			whereMap.put("1", "( taskstatus=1 or taskstatus=2 )"+" and taskname like '%"+keyword+"%' ");
		}
		getDataParam.setWhereMap(whereMap);
		
		Map<String,Class> clsMap = new HashMap<String, Class>();
		clsMap.put("1",GetTaskBean.class);
		getDataParam.setClassMap(clsMap);
		
		
		DataChannelManager.getInstance().getData(getDataParam, handler);
	}
}
