package com.victop.ibs.db.model;
/**
 * 素材标签实体类
 * 
 * @author vv
 * 
 */
public class MaterialSort {
private String  lableid;//标签库ID
private String materialid;//素材ID
private String matlableid;//素材标签关系ID
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
