package com.victop.ibs.bean;

 
import java.io.Serializable;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.victop.ibs.db.base.BaseBean;

/**
 * 属性
 * 
 * @author vv
 * 
 */
@XStreamAlias("PropertyBean")
 
public class PropertyBean extends BaseBean implements Serializable{
	 
	private static final long serialVersionUID = 1L;
 
	public static final String modelId = "IBS10217";// 属性数据模型编号
	public static final String formId = "10217";
	public static final String datasetId = "6";// 数据集编号
	private String viewtype;// 展示类型
	private String natureid;// 属性id
	private String naturename;// 属性名称
	private String naturedetailid;// 属性明细ID
	private String naturedetailname;// 属性明细名称

	public String getNaturedetailid() {
		return naturedetailid;
	}

	public void setNaturedetailid(String naturedetailid) {
		this.naturedetailid = naturedetailid;
	}

	public String getNaturedetailname() {
		return naturedetailname;
	}

	public void setNaturedetailname(String naturedetailname) {
		this.naturedetailname = naturedetailname;
	}

	public String getViewtype() {
		return viewtype;
	}

	public void setViewtype(String viewtype) {
		this.viewtype = viewtype;
	}

	public String getNatureid() {
		return natureid;
	}

	public void setNatureid(String natureid) {
		this.natureid = natureid;
	}

	public String getNaturename() {
		return naturename;
	}

	public void setNaturename(String naturename) {
		this.naturename = naturename;
	}

}
