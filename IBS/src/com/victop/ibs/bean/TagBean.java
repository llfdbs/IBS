package com.victop.ibs.bean;

import com.victop.ibs.db.base.BaseBean;

/**
 * 标签
 * 
 * @author vv
 * 
 */
public class TagBean extends BaseBean {
	public static final String modelId = "IBS11112";// 标签询数据模型编号
	public static final String formId = "11112";
	public static final String datasetId = "3";// 数据集编号
	private String lableid;// 标签id
	private String lablename;// 标签名
	private String sortLetters; // 手写字母
	private int tag; // 标识
	public String getSortLetters() {
		return sortLetters;
	}

	public void setSortLetters(String sortLetters) {
		this.sortLetters = sortLetters;
	}

	public int getTag() {
		return tag;
	}

	public void setTag(int tag) {
		this.tag = tag;
	}

	
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
