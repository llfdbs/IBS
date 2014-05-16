package com.victop.ibs.bean;

import com.victop.ibs.db.base.BaseBean;
/**
 *手机个人素材个数
 * 
 * @author vv
 * 
 */
public class MaterialCountBean extends BaseBean {
	private String modelId_send = "IBS10211";//手机个人素材个数数数据模型编号
	private String datasetId = "3";// 数据集编号
    private String summaterial;//素材个数
    public String getModelId_send() {
		return modelId_send;
	}
	public void setModelId_send(String modelId_send) {
		this.modelId_send = modelId_send;
	}
	public String getDatasetId() {
		return datasetId;
	}
	public void setDatasetId(String datasetId) {
		this.datasetId = datasetId;
	}
	public String getSummaterial() {
		return summaterial;
	}
	public void setSummaterial(String summaterial) {
		this.summaterial = summaterial;
	}


}
