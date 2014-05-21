package com.victop.ibs.bean;

import com.victop.ibs.db.base.BaseBean;

/**
 * 手机个人素材个数
 * 
 * @author vv
 * 
 */
public class MaterialCountBean extends BaseBean {
	private String modelId = "IBS10232";// 手机个人素材个数数数据模型编号
	private String datasetId = "3";// 数据集编号
	private String summaterialid;// 素材个数

	public String getSummaterialid() {
		return summaterialid;
	}

	public void setSummaterialid(String summaterialid) {
		this.summaterialid = summaterialid;
	}

	public String getModelId() {
		return modelId;
	}

	public void setModelId(String modelId) {
		this.modelId = modelId;
	}

	public String getDatasetId() {
		return datasetId;
	}

	public void setDatasetId(String datasetId) {
		this.datasetId = datasetId;
	}

}
