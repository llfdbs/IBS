package com.victop.ibs.db.model;

import com.victop.ibs.bean.CheckedMaterailCountBean;
import com.victop.ibs.bean.GetTaskCountBean;
import com.victop.ibs.bean.MaterialCountBean;
import com.victop.ibs.bean.SendTaskCountBean;
import com.victop.ibs.bean.UnCheckedMaterialCountBean;
import com.victop.ibs.bean.UnfinishedMaterialCountBean;
import com.victop.ibs.bean.UserMessageBean;
/**
 * 个人中心model
 * 
 * @author vv
 * 
 */
public class PersonCenterModel {
public final static String modelId="IBS10232";
public final static String formId="10232";
private UserMessageBean usermessagebean;//员工登陆显示数据
private MaterialCountBean materialcountbean;//手机素材个数
private UnfinishedMaterialCountBean unfinishedmaterialcountbean;//未完成素材个数
private CheckedMaterailCountBean checkedmaterialcountbean;//已审核素材个数
private UnCheckedMaterialCountBean uncheckedmaterialcountbean;//未审核素材个数
private GetTaskCountBean gettaskcountbean;//我的任务,接收的任务个数
private SendTaskCountBean sendtaskcountbean;//发布的任务个数
public UserMessageBean getUsermessagebean() {
	return usermessagebean;
}
public void setUsermessagebean(UserMessageBean usermessagebean) {
	this.usermessagebean = usermessagebean;
}
public MaterialCountBean getMaterialcountbean() {
	return materialcountbean;
}
public void setMaterialcountbean(MaterialCountBean materialcountbean) {
	this.materialcountbean = materialcountbean;
}
public UnfinishedMaterialCountBean getUnfinishedmaterialcountbean() {
	return unfinishedmaterialcountbean;
}
public void setUnfinishedmaterialcountbean(
		UnfinishedMaterialCountBean unfinishedmaterialcountbean) {
	this.unfinishedmaterialcountbean = unfinishedmaterialcountbean;
}
public CheckedMaterailCountBean getCheckedmaterialcountbean() {
	return checkedmaterialcountbean;
}
public void setCheckedmaterialcountbean(
		CheckedMaterailCountBean checkedmaterialcountbean) {
	this.checkedmaterialcountbean = checkedmaterialcountbean;
}
public UnCheckedMaterialCountBean getUncheckedmaterialcountbean() {
	return uncheckedmaterialcountbean;
}
public void setUncheckedmaterialcountbean(
		UnCheckedMaterialCountBean uncheckedmaterialcountbean) {
	this.uncheckedmaterialcountbean = uncheckedmaterialcountbean;
}
public GetTaskCountBean getGettaskcountbean() {
	return gettaskcountbean;
}
public void setGettaskcountbean(GetTaskCountBean gettaskcountbean) {
	this.gettaskcountbean = gettaskcountbean;
}
public SendTaskCountBean getSendtaskcountbean() {
	return sendtaskcountbean;
}
public void setSendtaskcountbean(SendTaskCountBean sendtaskcountbean) {
	this.sendtaskcountbean = sendtaskcountbean;
}

}
