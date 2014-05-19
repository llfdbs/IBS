package com.victop.ibs.bean;

import com.victop.ibs.db.base.BaseBean;
/**
 * 任务相关素材实体类
 * 
 * @author vv
 * 
 */
public class TaskMaterialsBean extends BaseBean {
	private String modelId = "IBS11118";// 任务素材查询数据模型编号
	private String datasetId = "2";// 任务素材查询数据集编号
	private String addman;//提交人
	private String adddate;//添加时间
	private String materialid;//素材ID
	private String materialmemo;//素材内容
	private String summaterial;//素材总数
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
	public String getAddman() {
		return addman;
	}
	public void setAddman(String addman) {
		this.addman = addman;
	}
	public String getAdddate() {
		return adddate;
	}
	public void setAdddate(String adddate) {
		this.adddate = adddate;
	}
	public String getMaterialid() {
		return materialid;
	}
	public void setMaterialid(String materialid) {
		this.materialid = materialid;
	}
	public String getMaterialmemo() {
		return materialmemo;
	}
	public void setMaterialmemo(String materialmemo) {
		this.materialmemo = materialmemo;
	}
	public String getSummaterial() {
		return summaterial;
	}
	public void setSummaterial(String summaterial) {
		this.summaterial = summaterial;
	}
	
	
}
