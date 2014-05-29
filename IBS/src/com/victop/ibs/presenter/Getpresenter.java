package com.victop.ibs.presenter;

import java.util.HashMap;
import java.util.Map;

import android.os.Handler;

import com.victop.android.datachannel.DataChannelManager;
import com.victop.android.datachannel.GetDataParam;

public class Getpresenter {
	private final String SYSTEMID = "100";
	public static Getpresenter getInstance() {
		return new Getpresenter();
	}
	
	public void getInitbData(Handler handler, Map<String, Class> clsMap,
			HashMap<String, String> Datamap, String modelId, String datasetId,
			HashMap<String, String> WhereMap,String formid) {
		GetDataParam getDataParam = new GetDataParam();
		getDataParam.setSystemId(SYSTEMID);
		getDataParam.setFormId(formid);
		getDataParam.setModelId(modelId);
		getDataParam.setDatasetId(datasetId);
		if (null != Datamap)
			getDataParam.setDataparamMap(Datamap);
		if (null != WhereMap)
			getDataParam.setWhereMap(WhereMap);
		if (null != clsMap)
			getDataParam.setClassMap(clsMap);
		DataChannelManager.getInstance().getData(getDataParam, handler);
	}
	public GetDataParam initGetData() {
		GetDataParam mGetDataParam = new GetDataParam();
		mGetDataParam.setSystemId(SYSTEMID);
		return mGetDataParam;
	}

	public void startGet(GetDataParam mGetDataParam, Handler handler) {
		DataChannelManager.getInstance().getData(mGetDataParam, handler);
	}
}
