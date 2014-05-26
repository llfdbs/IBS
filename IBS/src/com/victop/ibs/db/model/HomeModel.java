package com.victop.ibs.db.model;

import com.victop.ibs.bean.GetTaskCountBean;
import com.victop.ibs.bean.MaterialCountBean;
import com.victop.ibs.bean.SendTaskCountBean;
import com.victop.ibs.bean.UserMessageBean;

/**
 * 首页model
 * 
 * @author vv
 * 
 */
public class HomeModel {
	public final static String modelId="IBS10211";
	public final static String formId="10211";
	private UserMessageBean usermessagebean;//员工登陆显示数据
	private MaterialCountBean materialcountbean;//手机素材个数
	private GetTaskCountBean gettaskcountbean;//接收的任务个数
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
