package com.victop.ibs.bean;

import com.victop.ibs.db.base.BaseBean;
/**
 *发布的任务个数
 * 
 * @author vv
 * 
 */
public class SendTaskCountBean extends BaseBean {
	private String modelId = "IBS10232";//发布的任务个数数数据模型编号
	private String datasetId = "9";// 数据集编号
	private String sumtaskid;//任务总个数
	private String responsibleid;//负责人id
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

	public String getResponsibleid() {
		return responsibleid;
	}
	public void setResponsibleid(String responsibleid) {
		this.responsibleid = responsibleid;
	}
	public String getSumtaskid() {
		return sumtaskid;
	}
	public void setSumtaskid(String sumtaskid) {
		this.sumtaskid = sumtaskid;
	}
	
	
}
