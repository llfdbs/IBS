package com.victop.ibs.bean;

import com.victop.ibs.db.base.BaseBean;
/**
 * 手机未完成素材查询
 * 
 * @author vv
 * 
 */
public class UnFinishedMaterialBean extends BaseBean {
	
	private String modelId_send = "IBS102324";//手机未完成素材数据模型编号
	private String datasetId = "1";// 数据集编号
	private String addman;//提交人
	private String adddate;//添加日期
	private String materialid;//素材id
	private String materialmemo;//素材内容
	private String materialstatus;//素材状态
	public String getModelId_send() {
		return modelId_send;
	}
	public void setModelId_send(String modelId_send) {
		this.modelId_send = modelId_send;
	}
	public String getAdddate() {
		return adddate;
	}
	public void setAdddate(String adddate) {
		this.adddate = adddate;
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
	public String getMaterialstatus() {
		return materialstatus;
	}
	public void setMaterialstatus(String materialstatus) {
		this.materialstatus = materialstatus;
	}
	
	
}
