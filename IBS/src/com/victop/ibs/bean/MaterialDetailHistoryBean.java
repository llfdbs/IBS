package com.victop.ibs.bean;

import java.io.Serializable;

import com.victop.ibs.db.base.BaseBean;
/**
 * 素材详情历史版本查询
 * 
 * @author vv
 * 
 */
public class MaterialDetailHistoryBean extends BaseBean implements Serializable{
	private static final long serialVersionUID = -7060210544600464481L;
	public static final String modelId = "IBS10217";// 素材详情历史版本查询数据模型编号
	public static final String datasetId = "5";// 数据集编号
	private String modifyman;//修改人
	private String modifydate;//修改时间
	private String versioncode;//版本号
	private String materialid;//素材id
	public String getModifyman() {
		return modifyman;
	}
	public void setModifyman(String modifyman) {
		this.modifyman = modifyman;
	}
	public String getModifydate() {
		return modifydate;
	}
	public void setModifydate(String modifydate) {
		this.modifydate = modifydate;
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
	
	
}
