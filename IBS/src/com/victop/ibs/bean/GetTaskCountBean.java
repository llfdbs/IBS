package com.victop.ibs.bean;

import com.victop.ibs.db.base.BaseBean;
/**
 *接收的任务个数
 * 
 * @author vv
 * 
 */
public class GetTaskCountBean extends BaseBean {
	private String modelId = "IBS10232";//接收的任务个数数数据模型编号
	private String datasetId = "8";// 数据集编号
	private String sumtaskid;//任务个数
	private String hrid;//员工id
	public String getModelId() {
		return modelId;
	}
	public void setModelId(String modelId_send) {
		this.modelId = modelId_send;
	}
	public String getDatasetId() {
		return datasetId;
	}
	public void setDatasetId(String datasetId) {
		this.datasetId = datasetId;
	}
	public String getSumtaskid() {
		return sumtaskid;
	}
	public void setSumtaskid(String sumtaskid) {
		this.sumtaskid = sumtaskid;
	}
	public String getHrid() {
		return hrid;
	}
	public void setHrid(String hrid) {
		this.hrid = hrid;
	}

	
}
