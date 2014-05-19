package com.victop.ibs.bean;
/**
 * 任务分配
 * 
 * @author vv
 * 
 */
public class TaskAllocationBean {
	private String modelId = "IBS11117";// 数据模型编号
	private String datasetId = "7";// 数据集编号
	private String taskid;// 任务id
	private String taskallotid;// 任务分配id
	private String hrid;// 员工id
	private String isfinish;// 是否完工
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
	public String getTaskallotid() {
		return taskallotid;
	}
	public void setTaskallotid(String taskallotid) {
		this.taskallotid = taskallotid;
	}
	public String getHrid() {
		return hrid;
	}
	public void setHrid(String hrid) {
		this.hrid = hrid;
	}
	public String getIsfinish() {
		return isfinish;
	}
	public void setIsfinish(String isfinish) {
		this.isfinish = isfinish;
	}
	
}
