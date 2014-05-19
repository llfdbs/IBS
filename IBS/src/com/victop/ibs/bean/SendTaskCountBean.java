package com.victop.ibs.bean;

import com.victop.ibs.db.base.BaseBean;
/**
 *发布的任务个数
 * 
 * @author vv
 * 
 */
public class SendTaskCountBean extends BaseBean {
	private String modelId_send = "IBS10211";//发布的任务个数数数据模型编号
	private String datasetId = "4";// 数据集编号
	private String sumtask;//任务总个数
	private String responsibleid;//负责人id
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
	public String getResponsibleid() {
		return responsibleid;
	}
	public void setResponsibleid(String responsibleid) {
		this.responsibleid = responsibleid;
	}
	
	
}
