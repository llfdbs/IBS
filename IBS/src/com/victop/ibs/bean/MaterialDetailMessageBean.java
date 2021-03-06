package com.victop.ibs.bean;

import com.victop.ibs.db.base.BaseBean;
/**
 * 素材详情详细内容
 * 
 * @author vv
 * 
 */
public class MaterialDetailMessageBean extends BaseBean {
	private String modelId_send = "IBS10217";// 素材详情详细查询数据模型编号
	private String datasetId = "1";// 数据集编号
	private String taskcode;//任务单号
	private String versioncode;//版本号
	private String materialid;//素材id
	private String materialmemo;//素材内容
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
	public String getTaskcode() {
		return taskcode;
	}
	public void setTaskcode(String taskcode) {
		this.taskcode = taskcode;
	}
	public String getVersioncode() {
		return versioncode;
	}
	public void setVersioncode(String versioncode) {
		this.versioncode = versioncode;
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
	
	
}
