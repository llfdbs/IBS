package com.victop.ibs.db.model;

import com.victop.ibs.bean.AddMaterialBean;
import com.victop.ibs.bean.MaterialCheckBean;
import com.victop.ibs.bean.MaterialDeleteBean;
import com.victop.ibs.bean.MaterialModifyBean;
import com.victop.ibs.bean.MaterialPictureBean;
import com.victop.ibs.bean.MaterialPropertyBean;
import com.victop.ibs.bean.MaterialTagBean;

/**
 * 新增素材model
 * 
 * @author vv
 * 
 */
public class AddMaterialModel {
private MaterialTagBean materialtagbean;//素材标签实体类
private MaterialPictureBean materialpicturebean;//素材图片实体类
private AddMaterialBean addmaterialbean;//素材实体类
private MaterialPropertyBean materialpropertybean;//素材属性类
private MaterialModifyBean materialmodifybean;//素材修改记录实体类
private MaterialDeleteBean materialdeletebean;//素材删除记录实体类
private MaterialCheckBean materialcheckbean;//素材审核实体类
public MaterialTagBean getMaterialtagbean() {
	return materialtagbean;
}
public void setMaterialtagbean(MaterialTagBean materialtagbean) {
	this.materialtagbean = materialtagbean;
}
public MaterialPictureBean getMaterialpicturebean() {
	return materialpicturebean;
}
public void setMaterialpicturebean(MaterialPictureBean materialpicturebean) {
	this.materialpicturebean = materialpicturebean;
}
public AddMaterialBean getAddmaterialbean() {
	return addmaterialbean;
}
public void setAddmaterialbean(AddMaterialBean addmaterialbean) {
	this.addmaterialbean = addmaterialbean;
}
public MaterialPropertyBean getMaterialpropertybean() {
	return materialpropertybean;
}
public void setMaterialpropertybean(MaterialPropertyBean materialpropertybean) {
	this.materialpropertybean = materialpropertybean;
}
public MaterialModifyBean getMaterialmodifybean() {
	return materialmodifybean;
}
public void setMaterialmodifybean(MaterialModifyBean materialmodifybean) {
	this.materialmodifybean = materialmodifybean;
}
public MaterialDeleteBean getMaterialdeletebean() {
	return materialdeletebean;
}
public void setMaterialdeletebean(MaterialDeleteBean materialdeletebean) {
	this.materialdeletebean = materialdeletebean;
}
public MaterialCheckBean getMaterialcheckbean() {
	return materialcheckbean;
}
public void setMaterialcheckbean(MaterialCheckBean materialcheckbean) {
	this.materialcheckbean = materialcheckbean;
}

}
