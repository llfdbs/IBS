package com.victop.ibs.bean;

import com.victop.ibs.db.base.BaseBean;
/**
 * 新增素材分类
 * 
 * @author vv
 * 
 */
public class SortBean extends BaseBean {
	private String modelId_send = "IBS10212";// 分类查询数据模型编号
	private String datasetId = "1";// 数据集编号
	private String id;//分类id
	private String classname;//分类名称
	private String parentid;//父类id
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
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getClassname() {
		return classname;
	}
	public void setClassname(String classname) {
		this.classname = classname;
	}
	public String getParentid() {
		return parentid;
	}
	public void setParentid(String parentid) {
		this.parentid = parentid;
	}
	
}
