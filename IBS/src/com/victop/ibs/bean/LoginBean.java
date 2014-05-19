package com.victop.ibs.bean;

import com.victop.ibs.db.base.BaseBean;
/**
 *员工登陆
 * 
 * @author vv
 * 
 */
public class LoginBean extends BaseBean {
	private String modelId_send = "IBS10231";// 员工登陆数据模型编号
	private String datasetId = "1";// 数据集编号
	private String hrid;//员工id
	private String hrname;//员工姓名
	private String hrpassword;//员工密码
	private String hrcode;//员工账号
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
	public String getHrpassword() {
		return hrpassword;
	}
	public void setHrpassword(String hrpassword) {
		this.hrpassword = hrpassword;
	}
	public String getHrcode() {
		return hrcode;
	}
	public void setHrcode(String hrcode) {
		this.hrcode = hrcode;
	}
	
	
}
