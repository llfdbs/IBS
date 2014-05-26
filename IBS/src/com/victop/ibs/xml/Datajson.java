package com.victop.ibs.xml;

import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.victop.ibs.bean.PropertyBean;
import com.victop.ibs.bean.SortBean;

@XStreamAlias("Datajson")
public class Datajson {

	@XStreamAlias("Name")
	private String name;
	@XStreamAlias("list")
	List<SortBean> mSortBean;
	@XStreamAlias("userlist")
	List<PropertyBean> mPropertyBean;

	public List<PropertyBean> getmPropertyBean() {
		return mPropertyBean;
	}

	public void setmPropertyBean(List<PropertyBean> mPropertyBean) {
		this.mPropertyBean = mPropertyBean;
	}

	public List<SortBean> getmSortBean() {
		return mSortBean;
	}

	public void setmSortBean(List<SortBean> mSortBean) {
		this.mSortBean = mSortBean;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
