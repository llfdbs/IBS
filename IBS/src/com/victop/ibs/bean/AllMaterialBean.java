package com.victop.ibs.bean;

import com.victop.ibs.db.base.BaseBean;
/**
 * 全部素材
 * 
 * @author vv
 * 
 */
public class AllMaterialBean extends BaseBean {
	private String modelId = "IBS10213";// 素材详情图片查询数据模型编号
	private String datasetId = "1";// 数据集编号
	private String addman;//提交人
	private String materialstatus;//提交状态
	private String adddate;//添加日期
	private String materialid;//素材id
	private String materialmemo;//素材内容
	private String materialcode;//素材编号
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
	public String getMaterialstatus() {
		return materialstatus;
	}
	public void setMaterialstatus(String materialstatus) {
		this.materialstatus = materialstatus;
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
	public String getMaterialcode() {
		return materialcode;
	}
	public void setMaterialcode(String materialcode) {
		this.materialcode = materialcode;
	}
	
}
