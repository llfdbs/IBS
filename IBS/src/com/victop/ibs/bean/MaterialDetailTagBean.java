package com.victop.ibs.bean;

import com.victop.ibs.db.base.BaseBean;
/**
 * 素材详情标签查询
 * 
 * @author vv
 * 
 */
public class MaterialDetailTagBean extends BaseBean {
	private String modelId = "IBS10217";// 素材详情标签查询数据模型编号
	public static final String datasetId = "2";// 数据集编号
	private String lableid;//标签id
	private String lablename;//标签名
	public String getLableid() {
		return lableid;
	}
	public void setLableid(String lableid) {
		this.lableid = lableid;
	}
	public String getLablename() {
		return lablename;
	}
	public void setLablename(String lablename) {
		this.lablename = lablename;
	}
	
}
