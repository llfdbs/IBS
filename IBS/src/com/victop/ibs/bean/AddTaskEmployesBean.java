package com.victop.ibs.bean;

import com.victop.ibs.db.base.BaseBean;

public class AddTaskEmployesBean extends BaseBean {
	/**
	 * 员工查询实体类
	 * 
	 * @author vv
	 * 
	 * 
	 */
	private String modelId_send = "IBS11117";// 数据模型编号
	private String datasetId = "4";// 数据集编号
    private String hrid;//员工id
    private String hrname;//员工姓名
    private String headimage;//头像
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
