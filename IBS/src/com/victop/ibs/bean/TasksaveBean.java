package com.victop.ibs.bean;

import com.victop.ibs.db.base.BaseBean;

/**
 * 发布任务
 * 
 * @author Administrator
 * 
 */
public class TasksaveBean extends BaseBean {
	 private String modelId = "IBS11117";// 发布任务数据模型编号
	 private String datasetId = "8";// 未完成

	private String taskid;// 任务ID
	private String taskmemo;// 任务内容
	 private String taskcode;// 任务单号
	private String taskname;// 任务名称
	private String taskstatus;// 任务状态名
	 private String tasklevel;// 任务级别名
	private String finishtime;// 实际完成时间
	private String duedate;// 截止时间
	private String receptname;// 接收对象
	private String adddate;// 添加时间
	private String postdate;// 确定时间

	 private String responsibleid;// 负责人ID
	 private String responsiblename;// 负责人姓名
	 public TasksaveBean(String RawStatus) {
			setRowState(RawStatus);
		}

	public String getTaskid() {
		return taskid;
	}

	public void setTaskid(String taskid) {
		this.taskid = taskid;
	}

	public String getTaskmemo() {
		return taskmemo;
	}

	public void setTaskmemo(String taskmemo) {
		this.taskmemo = taskmemo;
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

	public String getFinishtime() {
		return finishtime;
	}

	public void setFinishtime(String finishtime) {
		this.finishtime = finishtime;
	}

	public String getDuedate() {
		return duedate;
	}

	public void setDuedate(String duedate) {
		this.duedate = duedate;
	}

	public String getReceptname() {
		return receptname;
	}

	public void setReceptname(String receptname) {
		this.receptname = receptname;
	}

	public String getAdddate() {
		return adddate;
	}

	public void setAdddate(String adddate) {
		this.adddate = adddate;
	}

	public String getPostdate() {
		return postdate;
	}

	public void setPostdate(String postdate) {
		this.postdate = postdate;
	}

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

	public String getTaskcode() {
		return taskcode;
	}

	public void setTaskcode(String taskcode) {
		this.taskcode = taskcode;
	}

	public String getTasklevel() {
		return tasklevel;
	}

	public void setTasklevel(String tasklevel) {
		this.tasklevel = tasklevel;
	}

	public String getResponsibleid() {
		return responsibleid;
	}

	public void setResponsibleid(String responsibleid) {
		this.responsibleid = responsibleid;
	}

	public String getResponsiblename() {
		return responsiblename;
	}

	public void setResponsiblename(String responsiblename) {
		this.responsiblename = responsiblename;
	}
	
	

}
