package com.victop.ibs.bean;

import com.victop.ibs.db.base.BaseBean;
/**
 *接受的任务个数
 * 
 * @author vv
 * 
 */
public class GetTaskCountBean extends BaseBean {
	private String modelId_send = "IBS10211";//接受的任务个数数数据模型编号
	private String datasetId = "3";// 数据集编号
	private String sumtask;//任务个数
	private String id;//员工id
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
	public String getSumtask() {
		return sumtask;
	}
	public void setSumtask(String sumtask) {
		this.sumtask = sumtask;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
}
