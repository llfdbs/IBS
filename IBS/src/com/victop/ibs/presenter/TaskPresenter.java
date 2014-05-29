package com.victop.ibs.presenter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.os.Handler;

import com.victop.android.datachannel.SaveDataParam;
import com.victop.ibs.bean.TasksaveBean;

public class TaskPresenter {

	SavePresenter save;

	public static TaskPresenter getInstance() {
		return new TaskPresenter();
	}

	/**保存
	 * @param handler
	 * @param bean
	 */
	public void saveTask(Handler handler, List<TasksaveBean> bean) {
		save = SavePresenter.getInstance();
		SaveDataParam sdp = save.initSaveData();
		Map<String, List> dataMap = new HashMap<String, List>();
		dataMap.put("8", bean);
		sdp.setDataMap(dataMap);
		sdp.setFormId("11117");
		sdp.setModelId("IBS11117");
		sdp.setDatasetId("8");
		save.startSave(sdp, handler);

	}

	/**分配
	 * @param handler
	 * @param bean
	 */
	public void AllocationTask(Handler handler, List<TasksaveBean> bean) {
		save = SavePresenter.getInstance();
		SaveDataParam sdp = save.initSaveData();
		Map<String, List> dataMap = new HashMap<String, List>();
		dataMap.put("7", bean);
		sdp.setDataMap(dataMap);
		sdp.setFormId("11117");
		sdp.setModelId("IBS11117");
		sdp.setDatasetId("7");
		save.startSave(sdp, handler);

	}
}
