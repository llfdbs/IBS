package com.victop.ibs.bean;

import com.victop.ibs.db.base.BaseBean;
/**
 * 新增素材分类
 * 
 * @author vv
 * 
 */
public class SortBean extends BaseBean {
	public static final String modelId = "IBS10212";// 分类查询数据模型编号
	public static final String formId = "10212";
	public static final String datasetId = "1";// 数据集编号
	private String classid;//分类id
	private String classname;//分类名称
	private String parentid;//父类id
	
	public String getClassid() {
		return classid;
	}
	public void setClassid(String classid) {
		this.classid = classid;
	}
	public String getClassname() {
		return classname;
	}
	public void setClassname(String classname) {
		this.classname = classname;
	}
	public String getParentid() {
		return parentid;
	}
	public void setParentid(String parentid) {
		this.parentid = parentid;
	}
	
}
