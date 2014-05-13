package com.victop.ibs.bean;

import com.victop.ibs.db.base.BaseBean;
/**
 * 查询任务分配状态实体类
 * 
 * @author vv
 * 
 */
public class TaskStatusBean extends BaseBean {
	private String modelId = "IBS11118";// 查询任务分配状态数据模型编号
	private String datasetId = "3";// 数据集编号
	private String taskid;//任务id
	private String isfinish;//是否完成
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
	public String getTaskid() {
		return taskid;
	}
	public void setTaskid(String taskid) {
		this.taskid = taskid;
	}
	public String getIsfinish() {
		return isfinish;
	}
	public void setIsfinish(String isfinish) {
		this.isfinish = isfinish;
	}
	
}
