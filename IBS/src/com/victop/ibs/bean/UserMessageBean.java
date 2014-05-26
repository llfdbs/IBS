package com.victop.ibs.bean;

import com.victop.ibs.db.base.BaseBean;
/**
 *员工登陆显示信息
 * 
 * @author vv
 * 
 */
public class UserMessageBean extends BaseBean {
	private String modelId = "IBS10232";//数据模型编号
	public static final String datasetId = "10";// 数据集编号
	private String hrid;//员工id
	private String hrname;//员工姓名
	private String headimage;//头像
	public String getHrid() {
		return hrid;
	}
	public void setHrid(String hrid) {
		this.hrid = hrid;
	}
	public String getHrname() {
		return hrname;
	}
	public void setHrname(String hrname) {
		this.hrname = hrname;
	}
	public String getHeadimage() {
		return headimage;
	}
	public void setHeadimage(String headimage) {
		this.headimage = headimage;
	}
	
}
