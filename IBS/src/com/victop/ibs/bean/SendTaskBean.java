package com.victop.ibs.bean;

import com.victop.ibs.db.base.BaseBean;
/**
 * 发布的任务列表实体类
 * 
 * @author vv
 * 
 */
public class SendTaskBean extends BaseBean {
	private String modelId_send = "IBS10223";// 发布的任务数据模型编号
	private String datasetId = "1";// 数据集编号
	private String taskid;// 任务id
	private String taskcode;// 任务单号
	private String taskname;// 任务名称
	private String taskstatus;// 任务状态
	private String tasklevel;// 任务级别名
	private String duedate;// 截止时间
	
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
	public String getTaskid() {
		return taskid;
	}
	public void setTaskid(String taskid) {
		this.taskid = taskid;
	}
	public String getTaskcode() {
		return taskcode;
	}
	public void setTaskcode(String taskcode) {
		this.taskcode = taskcode;
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
	public String getDuedate() {
		return duedate;
	}
	public void setDuedate(String duedate) {
		this.duedate = duedate;
	}
	
}
