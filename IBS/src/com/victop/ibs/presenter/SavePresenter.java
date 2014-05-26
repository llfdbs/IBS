package com.victop.ibs.presenter;

import java.util.List;
import java.util.Map;

import android.os.Handler;

import com.victop.android.datachannel.DataChannelManager;
import com.victop.android.datachannel.SaveDataParam;

public class SavePresenter {
	final String SYSTEMID = "100";
	public static SavePresenter getInstance() {
		return new SavePresenter();
	}

	public void SaveInitData(Handler handler, String FORMID, String modelId,
			String datasetId, Map<String, List> dataMap,
			Map<String, String> dataParamMap) {
		SaveDataParam mSaveDataParam = new SaveDataParam();
		mSaveDataParam.setSystemId(SYSTEMID);
		mSaveDataParam.setFormId(FORMID);
		mSaveDataParam.setModelId(modelId);
		mSaveDataParam.setDatasetId(datasetId);
		if (null != dataMap)
			mSaveDataParam.setDataMap(dataMap);
		if (null != dataParamMap)
			mSaveDataParam.setDataParamMap(dataParamMap);
		DataChannelManager.getInstance().saveData(mSaveDataParam, handler);
	}

	public SaveDataParam initSaveData() {
		SaveDataParam mSaveDataParam = new SaveDataParam();
		mSaveDataParam.setSystemId(SYSTEMID);
		return mSaveDataParam;
	}

	public void startSave(SaveDataParam mSaveDataParam, Handler handler) {
		DataChannelManager.getInstance().saveData(mSaveDataParam, handler);
	}
}
