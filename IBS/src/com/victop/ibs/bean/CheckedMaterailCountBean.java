package com.victop.ibs.bean;

import com.victop.ibs.db.base.BaseBean;
/**
 * 手机个人中心已审核素材个数
 * 
 * @author vv
 * 
 */
public class CheckedMaterailCountBean extends BaseBean {
	private String modelId = "IBS10232";//已审核素材个数数数据模型编号
	public static final String datasetId = "11";// 数据集编号
	private String summaterialid;//已审核素材个数
	
	public String getSummaterialid() {
		return summaterialid;
	}
	public void setSummaterialid(String summaterialid) {
		this.summaterialid = summaterialid;
	}
	
}
