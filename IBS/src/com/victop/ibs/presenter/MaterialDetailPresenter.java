package com.victop.ibs.presenter;

import java.util.HashMap;
import java.util.Map;

import android.os.Handler;

import com.victop.android.session.Container;
import com.victop.ibs.bean.MaterialDetailHistoryBean;
import com.victop.ibs.bean.MaterialDetailMessageBean;
import com.victop.ibs.bean.MaterialDetailPictureBean;
import com.victop.ibs.bean.MaterialDetailSortBean;
import com.victop.ibs.bean.MaterialDetailTagBean;
import com.victop.ibs.db.model.MaterialDetailModel;
import com.victop.ibs.util.PresenterTools;

/**
 * 素材详情装配
 * 
 * @author vv
 * 
 */
public class MaterialDetailPresenter {

	String modelId = MaterialDetailModel.modelId;
	String formId = MaterialDetailModel.formId;
	// 素材详情,素材标签,素材分类,素材图片,素材历史版本
	String datasetId = MaterialDetailMessageBean.datasetId+","+MaterialDetailTagBean.datasetId+","+MaterialDetailSortBean.datasetId+","+
			MaterialDetailPictureBean.datasetId+","+MaterialDetailHistoryBean.datasetId;
	public void getInitData(Handler handler) {

		HashMap<String, String> map = new HashMap<String, String>();
		map.put("materialid","1");

		Map<String, Class> clsMap = new HashMap<String, Class>();
		clsMap.put("1", MaterialDetailMessageBean.class);
		clsMap.put("2", MaterialDetailTagBean.class);
		clsMap.put("3", MaterialDetailSortBean.class);
		clsMap.put("4", MaterialDetailPictureBean.class);
		clsMap.put("5", MaterialDetailHistoryBean.class);
		PresenterTools.getInitData(handler, modelId, formId,datasetId, map, null,
				clsMap);
	}

}
