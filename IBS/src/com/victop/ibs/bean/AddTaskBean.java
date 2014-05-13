package com.victop.ibs.bean;

import com.victop.ibs.db.base.BaseBean;
/**
 * 新增任务实体类
 * 
 * @author vv
 * 
 */
public class AddTaskBean extends BaseBean {
private String taskid;//任务ID
private String taskmemo;//任务内容
private String taskcode;//任务单号
private String taskname;//任务名称
private String taskstatus;//任务状态名
private String tasklevel;//任务级别名
private String finishtime;//实际完成时间
private String duedate;//截止时间
private String receptname;//接收对象
private String adddate;//添加时间
private String postdate;//确定时间
private String responsibleid;//负责人ID
private String responsiblename;//负责人姓名
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
