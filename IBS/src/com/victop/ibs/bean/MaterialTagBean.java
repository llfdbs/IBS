package com.victop.ibs.bean;

import com.victop.ibs.db.base.BaseBean;
/**
 * 素材标签关系
 * 
 * @author vv
 * 
 */
public class MaterialTagBean extends BaseBean {

	private String modelId = "IBS11112";// 数据模型编号
	private String datasetId = "18";// 数据集编号
	private String lableid;//标签库id
	private String materialid;//素材id
	private String matlableid;//素材标签关系id
	public MaterialTagBean(){
		setRowState("4");
	}
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
	public String getLableid() {
		return lableid;
	}
	public void setLableid(String lableid) {
		this.lableid = lableid;
	}
	public String getMaterialid() {
		return materialid;
	}
	public void setMaterialid(String materialid) {
		this.materialid = materialid;
	}
	public String getMatlableid() {
		return matlableid;
	}
	public void setMatlableid(String matlableid) {
		this.matlableid = matlableid;
	}
	
}
