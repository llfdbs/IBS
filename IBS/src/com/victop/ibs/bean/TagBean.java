package com.victop.ibs.bean;

import com.victop.ibs.db.base.BaseBean;
/**
 * 标签
 * 
 * @author vv
 * 
 */
public class TagBean extends BaseBean {
	private String modelId_send = "IBS11112";// 标签询数据模型编号
	private String datasetId = "3";// 数据集编号
	private String lableid;//标签id
	private String lablename;//标签名
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
	public String getLableid() {
		return lableid;
	}
	public void setLableid(String lableid) {
		this.lableid = lableid;
	}
	public String getLablename() {
		return lablename;
	}
	public void setLablename(String lablename) {
		this.lablename = lablename;
	}
	
}
