package com.victop.ibs.bean;

import com.victop.ibs.db.base.BaseBean;
/**
 * 素材
 * 
 * @author vv
 * 
 */
public class AddMaterialBean extends BaseBean {
	private String modelId = "IBS11112";// 数据模型编号
	private String datasetId = "11";// 数据集编号
	private String ftpguid;//ftp批次号
	private String taskid;//任务id
	private String taskcode;//任务单号
	private String isdelete;//删除标识
	private String addman;//提交人
	private String addid;//提交人id
	private String adddate;//添加日期
	private String versioncode;//版本号
	private String materialid;//素材id
	private String materialmemo;//素材内容
	private String materialstatus;//素材状态
	private String materialcode;//素材编号
	public AddMaterialBean(){
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
	public String getFtpguid() {
		return ftpguid;
	}
	public void setFtpguid(String ftpguid) {
		this.ftpguid = ftpguid;
	}
	public String getTaskid() {
		return taskid;
	}
	public void setTaskid(String taskid) {
		this.taskid = taskid;
	}
	public String getTaskcode() {
		return taskcode;
	}
	public void setTaskcode(String taskcode) {
		this.taskcode = taskcode;
	}
	public String getIsdelete() {
		return isdelete;
	}
	public void setIsdelete(String isdelete) {
		this.isdelete = isdelete;
	}
	public String getAddman() {
		return addman;
	}
	public void setAddman(String addman) {
		this.addman = addman;
	}
	public String getAddid() {
		return addid;
	}
	public void setAddid(String addid) {
		this.addid = addid;
	}
	public String getAdddate() {
		return adddate;
	}
	public void setAdddate(String adddate) {
		this.adddate = adddate;
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
	public String getMaterialstatus() {
		return materialstatus;
	}
	public void setMaterialstatus(String materialstatus) {
		this.materialstatus = materialstatus;
	}
	public String getMaterialcode() {
		return materialcode;
	}
	public void setMaterialcode(String materialcode) {
		this.materialcode = materialcode;
	}
	
}
