package com.victop.ibs.bean;

import com.victop.ibs.db.base.BaseBean;
/**
 * 素材修改记录
 * 
 * @author vv
 * 
 */
public class MaterialModifyBean extends BaseBean {
	private String modelId = "IBS11112";// 数据模型编号
	private String datasetId = "10";// 数据集编号
	private String modifyman;//修改人
	private String modifymanid;//修改人id
	private String modifydate;//修改日期
	private String materialid;//素材id
	private String matmodifyreid;//素材修改记录id
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
	public String getModifyman() {
		return modifyman;
	}
	public void setModifyman(String modifyman) {
		this.modifyman = modifyman;
	}
	public String getModifymanid() {
		return modifymanid;
	}
	public void setModifymanid(String modifymanid) {
		this.modifymanid = modifymanid;
	}
	public String getModifydate() {
		return modifydate;
	}
	public void setModifydate(String modifydate) {
		this.modifydate = modifydate;
	}
	public String getMaterialid() {
		return materialid;
	}
	public void setMaterialid(String materialid) {
		this.materialid = materialid;
	}
	public String getMatmodifyreid() {
		return matmodifyreid;
	}
	public void setMatmodifyreid(String matmodifyreid) {
		this.matmodifyreid = matmodifyreid;
	}
	
}
