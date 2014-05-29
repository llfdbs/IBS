package com.victop.ibs.bean;

import java.io.Serializable;

import com.victop.ibs.db.base.BaseBean;
/**
 * 素材详情分类查询
 * 
 * @author vv
 * 
 */
public class MaterialDetailSortBean extends BaseBean implements Serializable{
	private static final long serialVersionUID = -7160210544600464481L;
	public static final String modelId = "IBS10217";// 素材详情分类查询数据模型编号
	public static final String datasetId = "3";// 数据集编号
	private String classid;//分类id
	private String classname;//分类名称
	
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
	
}
