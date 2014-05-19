package com.victop.ibs.bean;

import com.victop.ibs.db.base.BaseBean;
/**
 * 属性
 * 
 * @author vv
 * 
 */
public class PropertyBean extends BaseBean{
	private String modelId = "IBS11112";//属性数据模型编号
	private String datasetId = "2";//数据集编号
	private String viewtype;//展示类型
	private String natureid;//属性id
	private String naturename;//属性名称
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
	public String getViewtype() {
		return viewtype;
	}
	public void setViewtype(String viewtype) {
		this.viewtype = viewtype;
	}
	public String getNatureid() {
		return natureid;
	}
	public void setNatureid(String natureid) {
		this.natureid = natureid;
	}
	public String getNaturename() {
		return naturename;
	}
	public void setNaturename(String naturename) {
		this.naturename = naturename;
	}
	
}
