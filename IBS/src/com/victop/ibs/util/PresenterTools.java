package com.victop.ibs.util;

import java.util.HashMap;
import java.util.Map;
import com.victop.android.datachannel.DataChannelManager;
import com.victop.android.datachannel.GetDataParam;
import android.os.Handler;

public class PresenterTools {
	final static String SYSTEMID = "100";
	final static String FORMID = "10211";

	public static void getInitData(Handler handler, String modelId, String datasetId,
			HashMap<String, String> Datamap, HashMap<String, String> WhereMap,
			Map<String, Class> clsMap) {
		GetDataParam getDataParam = new GetDataParam();
		getDataParam.setSystemId(SYSTEMID);
		getDataParam.setFormId(FORMID);
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

}
