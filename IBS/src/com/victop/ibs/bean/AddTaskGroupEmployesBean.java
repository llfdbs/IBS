package com.victop.ibs.bean;

import com.victop.ibs.db.base.BaseBean;

public class AddTaskGroupEmployesBean extends BaseBean {
	/**
	 * 小组包含人员实体类
	 * 
	 * @author vv
	 * 
	 */
	private String modelId = "IBS10222";// 新增任务数据模型编号
	private String datasetId = "2";// 小组人员数据集编号
	private String hrid;// 员工id
	private String hrname;// 员工姓名
	private String headimage;// 员工头像
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
	public String getHrid() {
		return hrid;
	}
	public void setHrid(String hrid) {
		this.hrid = hrid;
	}
	public String getHrname() {
		return hrname;
	}
	public void setHrname(String hrname) {
		this.hrname = hrname;
	}
	public String getHeadimage() {
		return headimage;
	}
	public void setHeadimage(String headimage) {
		this.headimage = headimage;
	}
	
}
