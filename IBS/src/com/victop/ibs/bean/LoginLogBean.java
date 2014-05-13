package com.victop.ibs.bean;

import com.victop.ibs.db.base.BaseBean;
/**
 *登陆日志
 * 
 * @author vv
 * 
 */
public class LoginLogBean extends BaseBean {
	private String modelId_send = "IBS10232";// 登陆日志数据模型编号
	private String datasetId = "";// 数据集编号
	private String loginid;//登陆id
	private String loginpassword;//登陆密码
	private String logindate;//登陆账号
	private String quitdate;//退出日期
	
	public String getModelId_send() {
		return modelId_send;
	}
	public void setModelId_send(String modelId_send) {
		this.modelId_send = modelId_send;
	}
	public String getDatasetId() {
		return datasetId;
	}
	public void setDatasetId(String datasetId) {
		this.datasetId = datasetId;
	}
	public String getLoginid() {
		return loginid;
	}
	public void setLoginid(String loginid) {
		this.loginid = loginid;
	}
	public String getLoginpassword() {
		return loginpassword;
	}
	public void setLoginpassword(String loginpassword) {
		this.loginpassword = loginpassword;
	}
	public String getLogindate() {
		return logindate;
	}
	public void setLogindate(String logindate) {
		this.logindate = logindate;
	}
	public String getQuitdate() {
		return quitdate;
	}
	public void setQuitdate(String quitdate) {
		this.quitdate = quitdate;
	}
	
}
