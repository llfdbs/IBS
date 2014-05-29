package com.victop.ibs.xml;

import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.victop.ibs.bean.GetTaskBean;

@XStreamAlias("Dataset")
public class Dataset {

	@XStreamAlias("Name")
	private String name;

	@XStreamAsAttribute
	private String code;

	@XStreamAlias("userList")
	private List<GetTaskBean> getTaskBean;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<GetTaskBean> getGetTaskBean() {
		return getTaskBean;
	}

	public void setGetTaskBean(List<GetTaskBean> getTaskBean) {
		this.getTaskBean = getTaskBean;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}