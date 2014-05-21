package com.victop.ibs.bean;

import com.victop.ibs.db.base.BaseBean;
/**
 * 手机个人中心未完成素材个数
 * 
 * @author vv
 * 
 */
public class UnfinishedMaterialCountBean extends BaseBean {
	private String modelId = "IBS10232";//未完成素材个数数数据模型编号
	private String datasetId = "13";// 数据集编号
	private String summaterialid;//未完成素材个数
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
	public String getSummaterialid() {
		return summaterialid;
	}
	public void setSummaterialid(String summaterialid) {
		this.summaterialid = summaterialid;
	}
	
}
