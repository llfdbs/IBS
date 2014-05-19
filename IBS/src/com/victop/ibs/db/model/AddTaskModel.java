package com.victop.ibs.db.model;

import com.victop.ibs.bean.AddTaskBean;
import com.victop.ibs.bean.TaskAllocationBean;
/**
 * 新增任务model
 * 
 * @author vv
 * 
 */
public class AddTaskModel {
	AddTaskBean  addtaskbean;//新增任务实体类
	TaskAllocationBean taskallocation;//任务分配实体类
   public AddTaskBean getAddtaskbean() {
		return addtaskbean;
	}
	public void setAddtaskbean(AddTaskBean addtaskbean) {
		this.addtaskbean = addtaskbean;
	}
	public TaskAllocationBean getTaskallocation() {
		return taskallocation;
	}
	public void setTaskallocation(TaskAllocationBean taskallocation) {
		this.taskallocation = taskallocation;
	}

}
