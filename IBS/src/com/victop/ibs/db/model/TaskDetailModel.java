package com.victop.ibs.db.model;

import com.victop.ibs.bean.TaskDetailBean;
import com.victop.ibs.bean.TaskMaterialsBean;

/**
 * 任务详情model
 * 
 * @author vv
 * 
 */
public class TaskDetailModel {
	public final static String modelId="IBS11118";
	public final static String formId="11118";
	TaskDetailBean taskDetailBean;
	TaskMaterialsBean taskMaterialsBean;
	public TaskDetailBean getTaskDetailBean() {
		return taskDetailBean;
	}
	public void setTaskDetailBean(TaskDetailBean taskDetailBean) {
		this.taskDetailBean = taskDetailBean;
	}
	public TaskMaterialsBean getTaskMaterialsBean() {
		return taskMaterialsBean;
	}
	public void setTaskMaterialsBean(TaskMaterialsBean taskMaterialsBean) {
		this.taskMaterialsBean = taskMaterialsBean;
	}
	
}
