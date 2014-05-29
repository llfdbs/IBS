package com.victop.ibs.presenter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.os.Handler;

import com.victop.android.datachannel.GetDataParam;
import com.victop.android.datachannel.SaveDataParam;
import com.victop.android.session.Container;
import com.victop.ibs.bean.Page;
import com.victop.ibs.bean.TasksaveBean;
import com.victop.ibs.bean.UnCheckedMaterialBean;

public class Materialpresenter {
	Getpresenter get;
	SavePresenter save;
	/**
	 * 未完成
	 * 
	 * @param handler
	 * @param bean
	 */
	public void GetUfMaterial(Handler handler, Page page) {
		get = Getpresenter.getInstance();
		GetDataParam sdp = get.initGetData();
		Map<String, String> dataMap = new HashMap<String, String>();
		dataMap.put("addmanid", Container.getInstance().getUser().getUserCode());
		dataMap.put("ispage", page.getIspage() + "");
		dataMap.put("pageno", page.getPageno() + "");
		dataMap.put("pagesize", page.getPagesize() + "");
		sdp.setDataparamMap(dataMap);
		sdp.setWhereMap(null);
		Map<String, Class> clsMap = new HashMap<String, Class>();
		clsMap.put("1", UnCheckedMaterialBean.class);
		sdp.setClassMap(clsMap);
		sdp.setFormId("102324");
		sdp.setModelId("IBS102324");
		sdp.setDatasetId("1");
		get.startGet(sdp, handler);

	}

	/**
	 * 已审核
	 * 
	 * @param handler
	 * @param bean
	 */
	public void GetCMaterial(Handler handler, Page page) {
		get = Getpresenter.getInstance();
		GetDataParam sdp = get.initGetData();
		Map<String, String> dataMap = new HashMap<String, String>();
		dataMap.put("addmanid", Container.getInstance().getUser().getUserCode());
		dataMap.put("ispage", page.getIspage() + "");
		dataMap.put("pageno", page.getPageno() + "");
		dataMap.put("pagesize", page.getPagesize() + "");
		sdp.setDataparamMap(dataMap);
		sdp.setWhereMap(null);
		Map<String, Class> clsMap = new HashMap<String, Class>();
		clsMap.put("6", UnCheckedMaterialBean.class);
		sdp.setClassMap(clsMap);
		sdp.setFormId("10232");
		sdp.setModelId("IBS10232");
		sdp.setDatasetId("6");
		get.startGet(sdp, handler);

	}

	/**
	 * 未审核
	 * 
	 * @param handler
	 * @param bean
	 */
	public void GetUCMaterial(Handler handler, Page page) {
		get = Getpresenter.getInstance();
		GetDataParam sdp = get.initGetData();
		Map<String, String> dataMap = new HashMap<String, String>();
		dataMap.put("addmanid", Container.getInstance().getUser().getUserCode());
		dataMap.put("ispage", page.getIspage() + "");
		dataMap.put("pageno", page.getPageno() + "");
		dataMap.put("pagesize", page.getPagesize() + "");
		sdp.setDataparamMap(dataMap);
		sdp.setWhereMap(null);
		Map<String, Class> clsMap = new HashMap<String, Class>();
		clsMap.put("1", UnCheckedMaterialBean.class);
		sdp.setClassMap(clsMap);
		sdp.setFormId("102325");
		sdp.setModelId("IBS102325");
		sdp.setDatasetId("1");
		get.startGet(sdp, handler);

	}

	/**
	 * 全部
	 * 
	 * @param handler
	 * @param bean
	 */
	public void GetAlMaterial(Handler handler, Page page) {
		get = Getpresenter.getInstance();
		GetDataParam sdp = get.initGetData();
		Map<String, String> dataMap = new HashMap<String, String>();
		dataMap.put("addmanid", Container.getInstance().getUser().getUserCode());
		dataMap.put("ispage", page.getIspage() + "");
		dataMap.put("pageno", page.getPageno() + "");
		dataMap.put("pagesize", page.getPagesize() + "");
		sdp.setDataparamMap(dataMap);
		sdp.setWhereMap(null);
		Map<String, Class> clsMap = new HashMap<String, Class>();
		clsMap.put("1", UnCheckedMaterialBean.class);
		sdp.setClassMap(clsMap);
		sdp.setFormId("10213");
		sdp.setModelId("IBS10213");
		sdp.setDatasetId("1");
		get.startGet(sdp, handler);

	}

	/**
	 * @param handler
	 * @param bean
	 */
	public void saveMaterila(Handler handler, List<TasksaveBean> bean) {
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
}
