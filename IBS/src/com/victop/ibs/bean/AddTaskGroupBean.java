package com.victop.ibs.bean;

import com.victop.ibs.db.base.BaseBean;

public class AddTaskGroupBean extends BaseBean {
	/**
	 * 小组实体类
	 * 
	 * @author vv
	 * 
	 * 
	 */
	private String modelId = "IBS10222";// 数据模型编号
	private String datasetId = "1";// 小组查询数据集编号
	private String groupname;//小组名称
	private String groupimage;//小组头像
	private String bmgroupid;//部门小组id
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
	public String getGroupname() {
		return groupname;
	}
	public void setGroupname(String groupname) {
		this.groupname = groupname;
	}
	public String getGroupimage() {
		return groupimage;
	}
	public void setGroupimage(String groupimage) {
		this.groupimage = groupimage;
	}
	public String getBmgroupid() {
		return bmgroupid;
	}
	public void setBmgroupid(String bmgroupid) {
		this.bmgroupid = bmgroupid;
	}
	
}
