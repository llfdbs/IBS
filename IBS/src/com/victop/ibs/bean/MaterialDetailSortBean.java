package com.victop.ibs.bean;

import java.io.Serializable;

import com.victop.ibs.db.base.BaseBean;
/**
 * 素材详情分类查询
 * 
 * @author vv
 * 
 */
public class MaterialDetailSortBean extends BaseBean implements Serializable{
	private static final long serialVersionUID = -7160210544600464481L;
	private String modelId_send = "IBS10217";// 素材详情分类查询数据模型编号
	private String datasetId = "3";// 数据集编号
	private String id;//分类id
	private String classname;//分类名称
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
	
}
