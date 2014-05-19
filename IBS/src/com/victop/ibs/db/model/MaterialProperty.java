package com.victop.ibs.db.model;
/**
 * 素材属性实体类
 * 
 * @author vv
 * 
 */
public class MaterialProperty {
private String classid;//分类ID
private String natureid;//属性ID
private String materialid;//素材ID
private String matnatureid;//素材属性ID
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
