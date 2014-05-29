package com.victop.ibs.bean;

import java.io.Serializable;

import com.victop.ibs.db.base.BaseBean;

/**
 * 素材标签关系
 * 
 * @author vv
 * 
 */
public class MaterialTagBean extends BaseBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String modelId = "IBS11112";// 数据模型编号
	private String datasetId = "18";// 数据集编号
	private String lableid;// 标签库id
	private String matlableid;// 素材标签关系id
	private String materialguid;// GUID

	public MaterialTagBean() {
		setRowState("4");
	}

	public String getMaterialguid() {
		return materialguid;
	}

	public void setMaterialguid(String materialguid) {
		this.materialguid = materialguid;
	}

	public String getLableid() {
		return lableid;
	}

	public void setLableid(String lableid) {
		this.lableid = lableid;
	}

	public String getMatlableid() {
		return matlableid;
	}

	public void setMatlableid(String matlableid) {
		this.matlableid = matlableid;
	}

}
