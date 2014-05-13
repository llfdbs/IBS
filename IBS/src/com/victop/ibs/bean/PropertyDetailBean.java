package com.victop.ibs.bean;

import com.victop.ibs.db.base.BaseBean;
/**
 * 属性明细
 * 
 * @author vv
 * 
 */
public class PropertyDetailBean extends BaseBean {
	private String modelId = "IBS11112";//属性明细数据模型编号
	private String datasetId = "7";//数据集编号
	private String natureid;//属性id
	private String naturedetailname;//明细名称
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
	public String getNatureid() {
		return natureid;
	}
	public void setNatureid(String natureid) {
		this.natureid = natureid;
	}
	public String getNaturedetailname() {
		return naturedetailname;
	}
	public void setNaturedetailname(String naturedetailname) {
		this.naturedetailname = naturedetailname;
	}
	
}
