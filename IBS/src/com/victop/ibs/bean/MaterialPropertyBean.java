package com.victop.ibs.bean;

import com.victop.ibs.db.base.BaseBean;
/**
 * 素材属性关系
 * 
 * @author vv
 * 
 */
public class MaterialPropertyBean extends BaseBean {
	private String modelId = "IBS11112";// 数据模型编号
	private String datasetId = "13";// 数据集编号
	private String classid;//分类id
	private String natureid;//属性id
	private String naturevalue;//属性值
	private String materialid;//素材id
	private String matnatureid;//素材属性id
	
	private String materialguid;// 素材id
	private String naturedetailid;// 属性明细ID
	
	public String getMaterialguid() {
		return materialguid;
	}
	public void setMaterialguid(String materialguid) {
		this.materialguid = materialguid;
	}
	public String getNaturedetailid() {
		return naturedetailid;
	}
	public void setNaturedetailid(String naturedetailid) {
		this.naturedetailid = naturedetailid;
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
	public String getClassid() {
		return classid;
	}
	public void setClassid(String classid) {
		this.classid = classid;
	}
	public String getNatureid() {
		return natureid;
	}
	public void setNatureid(String natureid) {
		this.natureid = natureid;
	}
	public String getNaturevalue() {
		return naturevalue;
	}
	public void setNaturevalue(String naturevalue) {
		this.naturevalue = naturevalue;
	}
	public String getMaterialid() {
		return materialid;
	}
	public void setMaterialid(String materialid) {
		this.materialid = materialid;
	}
	public String getMatnatureid() {
		return matnatureid;
	}
	public void setMatnatureid(String matnatureid) {
		this.matnatureid = matnatureid;
	}
	
}
