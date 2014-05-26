package com.victop.ibs.bean;

import com.victop.ibs.db.base.BaseBean;
/**
 * 任务选择
 * 
 * @author vv
 * 
 */
public class TaskBean extends BaseBean {
	public static final String modelId = "IBS10215";//任务查询数据模型编号
	public static final  String datasetId = "1";// 数据集编号
	public static final String formId = "10215";
	private String taskid;//任务id
	private String taskname;//任务名称
	private String taskstatus;//任务状态名
	private String tasklevel;//任务级别名
	private String taskcode;//任务编号
	private String duedate;//截止时间
	
	public String getTaskid() {
		return taskid;
	}
	public void setTaskid(String taskid) {
		this.taskid = taskid;
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
	
}
