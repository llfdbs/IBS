package com.victop.ibs.presenter;

import java.util.HashMap;
import java.util.Map;
import com.victop.android.datachannel.DataChannelManager;
import com.victop.android.datachannel.GetDataParam;
import com.victop.ibs.bean.CheckedMaterailCountBean;
import com.victop.ibs.bean.GetTaskCountBean;
import com.victop.ibs.bean.MaterialCountBean;
import com.victop.ibs.bean.SendTaskCountBean;
import com.victop.ibs.bean.UnCheckedMaterialCountBean;
import com.victop.ibs.bean.UnfinishedMaterialCountBean;
import com.victop.ibs.bean.UserMessageBean;
import com.victop.ibs.db.model.PersonCenterModel;

import android.os.Handler;

public class PersonCenterPresenter {

	public void getInitData(Handler handler) {
		GetDataParam getDataParam = new GetDataParam();
		String systemId = "100";
		String formId = PersonCenterModel.formId;
		String modelId = PersonCenterModel.modelId;
		// String datasetId =
		// "10,3,13,11,12,8,9";//个人中心用户信息,我的素材个数,未完成的素材发布的任务个数,已经审核素材个数,未审核素材个数,接受的任务个数（我的任务个数）,发布的任务个数
		String datasetId = UserMessageBean.datasetId + ","
				+ MaterialCountBean.datasetId + ","
				+ UnfinishedMaterialCountBean.datasetId + ","
				+ CheckedMaterailCountBean.datasetId+","
				+ UnCheckedMaterialCountBean.datasetId + ","
				+ GetTaskCountBean.datasetId + ","
				+ SendTaskCountBean.datasetId;
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
		clsMap.put("10", UserMessageBean.class);
		clsMap.put("3", MaterialCountBean.class);
		clsMap.put("13", UnfinishedMaterialCountBean.class);
		clsMap.put("11", CheckedMaterailCountBean.class);
		clsMap.put("12", UnCheckedMaterialCountBean.class);
		clsMap.put("8", GetTaskCountBean.class);
		clsMap.put("9", SendTaskCountBean.class);

		getDataParam.setClassMap(clsMap);
		DataChannelManager.getInstance().getData(getDataParam, handler);
	}
}
