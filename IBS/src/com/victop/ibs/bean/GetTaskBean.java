package com.victop.ibs.bean;

import com.victop.ibs.db.base.BaseBean;
/**
 * 接收的任务列表实体类
 * 
 * @author vv
 * 
 */
public class GetTaskBean extends BaseBean {
private String modelId = "IBS10224";//数据模型编号
private String datasetId = "1";//数据集编号
private String taskid;//任务id
private String taskname;//任务名称
private String taskstatus;//任务状态
private String tasklevel;//任务级别
private String taskcode;//任务编号
private String duedate;//截止时间
private String isfinish;//是否完工
public String getTaskid() {
	return taskid;
}
public void setTaskid(String taskid) {
	this.taskid = taskid;
}
public String getModelId() {
	return modelId;
}
public void setModelId(String modelId) {
	this.modelId = modelId;
}
public String getTaskname() {
	return taskname;
}
public void setTaskname(String taskname) {
	this.taskname = taskname;
}
public String getTaskstatus() {
	return taskstatus;
}
public void setTaskstatus(String taskstatus) {
	this.taskstatus = taskstatus;
}
public String getTasklevel() {
	return tasklevel;
}
public void setTasklevel(String tasklevel) {
	this.tasklevel = tasklevel;
}
public String getTaskcode() {
	return taskcode;
}
public void setTaskcode(String taskcode) {
	this.taskcode = taskcode;
}
public String getDuedate() {
	return duedate;
}
public void setDuedate(String duedate) {
	this.duedate = duedate;
}
public String getIsfinish() {
	return isfinish;
}
public void setIsfinish(String isfinish) {
	this.isfinish = isfinish;
}

}
